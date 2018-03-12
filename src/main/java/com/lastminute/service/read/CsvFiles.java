package com.lastminute.service.read;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class CsvFiles
{

    public List<List<String>> readAllRecords(String fileName) throws UncheckedIOException
    {
        try (Stream<List<String>> records = records(fileName))
        {
            return records.collect(toList());
        }
    }

    private Stream<List<String>> records(String fileName) throws UncheckedIOException
    {
        return splitByCommaIgnoringHeader(linesOf(fileName));
    }

    private Stream<List<String>> splitByCommaIgnoringHeader(Stream<String> lines)
    {
        return lines.skip(1)
                .map(line -> line.split(","))
                .map(Arrays::asList);
    }

    private Stream<String> linesOf(String fileName) throws UncheckedIOException
    {
//        try
//        {
            InputStream in = CsvFiles.class.getClassLoader().getResourceAsStream(fileName);
            return new BufferedReader(new InputStreamReader(in)).lines();

            //return Files.lines(Paths.get(fileName));
//        }
//        catch (IOException e)
//        {
//            throw new UncheckedIOException("IO error accessing the CSV file " + fileName, e);
//        }
    }

}


