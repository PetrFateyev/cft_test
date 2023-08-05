package org.example.argument;

import org.example.exceptions.InvalidArgumentException;
import java.util.ArrayList;
import java.util.Arrays;

public final class SortArgumentsUtils {

    private SortArgumentsUtils(){}

    public static SortArguments build(String[] args) {
        if (args.length < 3) {
            throw new InvalidArgumentException("Количество аргументов должно быть не менее трёх.");
        }
        int index = 0;
        SortArguments arguments = new SortArguments();

        if (args[index].equals("-a") || args[index].equals("-d")) {
            arguments.setTypeOfSort(args[index]);
            index++;
        } else {
            arguments.setTypeOfSort("-a");
        }

        if (args[index].equals("-s") || args[index].equals("-i")) {
            arguments.setTypeOfData(args[index]);
            index++;
        } else {
            throw new InvalidArgumentException("Аргументы для типа данных могут быть только:" +
                    " -s(для строк) или -i(для чисел)!");
        }

        arguments.setOutputFileName(args[index++]);

        arguments.setInputFileNames(new ArrayList<>(Arrays.asList(args).subList(index, args.length)));
        return arguments;
    }
}
