package com.lastminute.service.read;


import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.List;



public abstract class ReadFileAbstract<T> implements ReadFile<T> {

    private final String cvsFile;

    private CsvFiles csvFiles;

    public ReadFileAbstract(String CVSFile){
        csvFiles = new CsvFiles();
        cvsFile = CVSFile;
    }

    public List<T> getAllData() throws ReadCSVException {
        List<List<String>> listData = readFile();
        return transformData(listData);
    }

    private List<List<String>> readFile() throws ReadCSVException {
        try {
            return csvFiles.readAllRecords(cvsFile);
        } catch(UncheckedIOException e) {
            throw new ReadCSVException(e.getMessage(), e);

        }

    }

    private List<T> transformData(List<List<String>> listData) {
        List<T> tList = new ArrayList<T>();

        for (List<String> listBean : listData){
            try {
                T t = createDataBeanByList(listBean);
                tList.add(t);
            } catch (NumberFormatException e) {
                //TODO: launch log???
            }

        }
        return tList;
    }

    abstract T createDataBeanByList(List<String> listBean);



}
