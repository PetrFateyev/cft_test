package org.example.sort;

import org.example.argument.SortArguments;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Optional;


public class IntegerSort extends DataSort<Integer> {

    public IntegerSort(SortArguments sortArguments) {
        super(sortArguments);
    }

    @Override
    public boolean isValid(String element) {
        try {
            Integer.parseInt(element);
        } catch (NumberFormatException e) {
            System.out.println("Элемент " + element + " не является целым числом и будет пропущен");
            return false;
        }
        return true;
    }

    @Override
    protected Optional<Integer> getNext(BufferedReader reader) throws IOException {
        String number = reader.readLine();
        if (!isValid(number)){
            return Optional.empty();
        }
        return Optional.of(Integer.parseInt(number));
    }
}
