package com.company;

import java.io.*;

public class ReaderWriter {

    String writeToURI, writeToText;
    EditorWindow editorWindow;

    //Writer constructor
    public ReaderWriter(String pURI, String pText) {

        writeToURI = pURI;
        writeToText = pText;

        try {
            writeFile();
            System.out.printf("Write to location %s successful!", writeToURI);
        } catch (IOException e) {
            System.err.printf("Write to location %s failed!", writeToURI);
        }


    }

    //Reader constructor
    public ReaderWriter(String pURI, EditorWindow editorWindow) {

        this.editorWindow = editorWindow;

        writeToURI = pURI;

        try {
            fileReader();
        } catch (IOException e) {

            System.err.printf("File read at %s failed!", writeToURI);
        }

    }

    //File writer
    private void writeFile() throws IOException {

        FileWriter writer = new FileWriter(writeToURI);
        BufferedWriter buffer = new BufferedWriter(writer);

        buffer.write(writeToText);

        buffer.close();

    }

    //File reader
    private void fileReader() throws IOException {

        String nextLine;
        StringBuilder readString = new StringBuilder("");

        BufferedReader br = null;

        //Try to read - null URI will throw error
        try {

            br = new BufferedReader(new FileReader(writeToURI));

            while ((nextLine = br.readLine()) != null) {

                readString.append(nextLine);
                readString.append("\n");

            }
        } catch (NullPointerException e) {

            System.err.println("User cancelled open operation.");
        }

        editorWindow.setText(readString.toString());

    }

}
