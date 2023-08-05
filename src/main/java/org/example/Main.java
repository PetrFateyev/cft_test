package org.example;

import org.example.argument.SortArguments;
import org.example.argument.SortArgumentsUtils;
import org.example.sort.IntegerSort;
import org.example.sort.StringSort;
import org.example.utils.Menu;

public class Main {
    public static void main(String[] args) {
        Menu.show();
        SortArguments sortArguments = SortArgumentsUtils.build(args);
        switch (sortArguments.getTypeOfData()){
            case "-i" -> {
                IntegerSort integerSort = new IntegerSort(sortArguments);
                integerSort.mergeSort();
            }
            case "-s" -> {
                StringSort stringSort = new StringSort(sortArguments);
                stringSort.mergeSort();
            }
        }
    }
}