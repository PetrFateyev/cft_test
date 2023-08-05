package org.example.sort;

import org.example.argument.SortArguments;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Optional;

public class StringSort extends DataSort<String> {

    public StringSort(SortArguments sortArguments) {
        super(sortArguments);
    }

    @Override
    protected Optional<String> getNext(BufferedReader reader) throws IOException{
        String line = reader.readLine();
        if(!isValid(line)) {
            return Optional.empty();
        }
        return Optional.of(line);
    }

    @Override
    public boolean isValid(String element) {
        try {
            if (element.contains(" ")) {
                throw new IllegalArgumentException("Строка не должна содержать пробелов");
            }
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }
}
