package com.company;

import java.io.*;

/*******************************************************************************
 * Copyright (c) Ethan Wilson 2020.
 *
 * The readerWriter class is a multifunctional class that functions either as a reader or a writer, depending on
 * which constructor is used. It is responsible for interfacing between JETFile objects and the file system.
 *
 * @author Ethan Wilson
 * @since 13-02-2020
 ******************************************************************************/
public class ReaderWriter {

    final String writeToURI;
    String writeToText;
    EditorWindow editorWindow;

    /**
     * The constructor for creating a writer.
     *
     * @param fileToWrite the JET File to write
     */
    public ReaderWriter(JETFile fileToWrite) {

        writeToURI = fileToWrite.getFileLocation();
        writeToText = fileToWrite.getTextContents();

        try {
            writeFile();
        } catch (IOException e) {
            System.err.printf("Write to location %s failed!%n", writeToURI);
        }


    }

    /**
     * The constructor for creating a reader.
     *
     * @param pURI         URI to read from
     * @param editorWindow parent window where the text will be placed once read
     */
    public ReaderWriter(String pURI, EditorWindow editorWindow) {

        this.editorWindow = editorWindow;

        writeToURI = pURI;

        try {
            fileReader();
        } catch (IOException e) {

            System.err.printf("File read at %s failed!%n", writeToURI);
        }

    }

    /**
     * File writer which takes a URI, text and then writes the text to a file.
     *
     * @throws IOException when there is a problem reading the file
     */
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

    /**
     * File reader which takes a URI and reads the file into a String variable.
     *
     * @throws IOException when there is a problem reading the file
     */
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

            JETFile createdFile = new JETFile(readString.toString(), writeToURI);

            editorWindow.setFile(createdFile);

        } catch (NullPointerException e) {
            System.err.println("User cancelled open operation.");
        }

    }

}
