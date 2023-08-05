package org.example.argument;

import java.util.List;

public class SortArguments {
    private String typeOfSort;
    private String typeOfData;
    private String outputFileName;
    private List<String> inputFileNames;

    public String getTypeOfSort() {
        return typeOfSort;
    }

    public void setTypeOfSort(String typoOfSort) {
        this.typeOfSort = typoOfSort;
    }

    public String getTypeOfData() {
        return typeOfData;
    }

    public void setTypeOfData(String typeOfData) {
        this.typeOfData = typeOfData;
    }

    public String getOutputFileName() {
        return outputFileName;
    }

    public void setOutputFileName(String outputFileName) {
        this.outputFileName = outputFileName;
    }

    public List<String> getInputFileNames() {
        return inputFileNames;
    }

    public void setInputFileNames(List<String> inputFileNames) {
        this.inputFileNames = inputFileNames;
    }
}
