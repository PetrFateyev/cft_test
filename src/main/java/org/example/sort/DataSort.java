package org.example.sort;

import org.example.argument.SortArguments;
import org.example.utils.Validator;
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public abstract class DataSort<T extends Comparable<T>> implements Validator {

    private final PrintWriter writer;
    private final List<BufferedReader> inputFiles;
    private final Comparator<T> comparator;
    private final List<T> elements;

    public DataSort(SortArguments sortArguments){
        inputFiles = new ArrayList<>();
        elements = new ArrayList<>();
        try {
            for (String input : sortArguments.getInputFileNames()) {
                inputFiles.add(new BufferedReader(new FileReader(input)));
            }
            writer = new PrintWriter(sortArguments.getOutputFileName());
        } catch (IOException e) {
            throw new RuntimeException("Файл не может быть создан", e);
        }

        comparator = "-a".equals(sortArguments.getTypeOfSort()) ?
                Comparator.naturalOrder() : Comparator.reverseOrder();
    }


    protected abstract Optional<T> getNext(BufferedReader reader) throws IOException;

    private Optional<T> getElement(BufferedReader reader, T prevElement) throws IOException {
        Optional<T> t = Optional.empty();
        while (reader.ready()) {
            t = getNext(reader);
            if (t.isPresent() && comparator.compare(prevElement, t.get()) <= 0) {
                return t;
            } else if (t.isPresent()){
                System.out.println("Нарушен порядок сортировки значение " + t.get() + " будет пропущено");
                t = Optional.empty();
            }
        }
        return t;
    }

    private Optional<T> getElement(BufferedReader reader) throws IOException{
        Optional<T> t = Optional.empty();
        while (reader.ready()) {
            t = getNext(reader);
            if (t.isPresent()) {
                return t;
            }
        }
        return t;
    }

    public void mergeSort()  {
        try {
            List<Integer> wrongFiles = new ArrayList<>();
            List<T> prevElements = new ArrayList<>();
            for (int i = 0; i < inputFiles.size(); i++) {
                Optional<T> t = getElement(inputFiles.get(i));
                if (t.isPresent()){
                    elements.add(t.get());
                    prevElements.add(t.get());
                } else {
                    wrongFiles.add(i);
                }
            }

            for (Integer wrongFile : wrongFiles) {
                inputFiles.get(wrongFile).close();
                inputFiles.remove((int)wrongFile);
            }

            while (inputFiles.size() != 0) {
                int index = writeElements();

                if (inputFiles.get(index).ready()) {
                    Optional<T> t = getElement(inputFiles.get(index), prevElements.get(index));
                    if (t.isPresent()){
                        prevElements.set(index, t.get());
                        elements.set(index, t.get());
                    } else {
                        closeResourses(index);
                    }
                } else {
                    closeResourses(index);
                }
            }
        } catch (IOException e){
            System.out.println("Возникли проблемы с чтением значения в файле.");
            e.printStackTrace();
        } finally {
            writer.close();
        }
    }


    private int writeElements() {

        T element = elements.get(0);

        for (int i = 0; i < elements.size() - 1; i++) {
            element = comparator.compare(element, elements.get(i + 1)) < 0 ? element : elements.get(i + 1);
        }
        writer.println(element);
        writer.flush();

        return elements.indexOf(element);
    }

    private void closeResourses(int index){
        try {
            inputFiles.get(index).close();
            inputFiles.remove(index);
            elements.remove(index);
        } catch (IOException e) {
            throw new RuntimeException("Возникли проблемы с закртытием ресурсов",e);
        }
    }
}
