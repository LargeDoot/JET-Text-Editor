package com.company;

import java.io.*;

public class ReaderWriter {

    final String writeToURI;
    String writeToText;
    EditorWindow editorWindow;

    //Writer constructor
    public ReaderWriter(JETFile fileToWrite) {

        writeToURI = fileToWrite.getFileLocation();
        writeToText = fileToWrite.getTextContents();

        try {
            writeFile();
        } catch (IOException e) {
            System.err.printf("Write to location %s failed!%n", writeToURI);
        }


    }

    //Reader constructor
    public ReaderWriter(String pURI, EditorWindow editorWindow) {

        this.editorWindow = editorWindow;

        writeToURI = pURI;

        try {
            fileReader();
        } catch (IOException e) {

            System.err.printf("File read at %s failed!%n", writeToURI);
        }

    }

    //File writer
    private void writeFile() throws IOException {

        try {
            FileWriter writer = new FileWriter(writeToURI);
            BufferedWriter buffer = new BufferedWriter(writer);

            buffer.write(writeToText);

            buffer.close();

            System.out.printf("Write to location %s successful!%n", writeToURI);
        } catch (NullPointerException e) {

            System.err.println("User cancelled save operation.");

        }

    }

    //File reader
    private void fileReader() throws IOException {

        String nextLine;
        StringBuilder readString = new StringBuilder();

        BufferedReader br;

        //Try to read - null URI will throw error
        try {

            br = new BufferedReader(new FileReader(writeToURI));

            while ((nextLine = br.readLine()) != null) {
                readString.append(nextLine);
                readString.append("\n");
            }

            br.close();

        } catch (NullPointerException e) {
            System.err.println("User cancelled open operation.");
        }

        JETFile createdFile = new JETFile(readString.toString(), writeToURI);

        editorWindow.setFile(createdFile);


    }

}
