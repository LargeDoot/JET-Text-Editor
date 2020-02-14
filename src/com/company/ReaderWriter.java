package com.company;

import java.io.*;

public class ReaderWriter {

    String writeToURI, writeToText;

    //Writer constructor
    public boolean ReaderWriter(String pText, String pURI) {

        boolean writeSuccess = false;

        writeToURI = pURI;
        writeToText = pText;

        try {
            writeFile();
            writeSuccess = true;
        } catch (IOException e) {
            System.err.printf("Write to location %s failed!", writeToURI);
        }

        return writeSuccess;

    }

    //Reader constructor
    public String ReaderWriter(String pURI) {

        String output;

        writeToURI = pURI;

        try {
            output = fileReader();
        } catch (IOException e) {

            System.err.printf("File read at %s failed!", writeToURI);
            output = null;
        }
        return output;


    }

    //File writer
    private void writeFile() throws IOException {

        FileWriter writer = new FileWriter(writeToURI);
        BufferedWriter buffer = new BufferedWriter(writer);

        buffer.write(writeToText);

        buffer.close();

    }

    //File reader
    private String fileReader() throws IOException {

        String nextLine, readString = null;
        BufferedReader br = new BufferedReader(new FileReader(writeToURI));

        while ((nextLine = br.readLine()) != null) {

            readString += nextLine;
        }

        return readString;

    }

}
