package com.example.cryptorjfxv2;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class FileInputOutput {

    private Path filePath;

    public void setFilePath(Path filePath) {
        this.filePath = filePath;
    }

    public FileInputOutput(){

    }
    public FileInputOutput(Path filePath){
        this.filePath = filePath;
    }

//    public Path inputFilePath (){
//        Scanner scanner = new Scanner(System.in);
//        Path filePath = Paths.get(scanner.nextLine());
//        if (!Files.isRegularFile(filePath)){
//            System.out.println(Warnings.FILENOTEXIST);
//        }
//        return filePath;
//    }

//    public String fileTextToString2() throws IOException {
//        StringBuilder builder = new StringBuilder();
//        try (BufferedReader buffer = Files.newBufferedReader(filePath)) {
//            String fullString;
//            while ((fullString = buffer.readLine()) != null) {
//                builder.append(fullString).append("\n");
//            }
//        }
//        catch (IOException e) {
//            //  System.out.println("исключение в fileTextToString - FILE NOT RECOGNIZED");
//            throw new IOException(e);
//            //e.printStackTrace();
//        }
//        return builder.toString();
//    }

    public String fileTextToString(Path filePath) throws IOException {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader buffer = Files.newBufferedReader(filePath)) {
            String fullString;
            while ((fullString = buffer.readLine()) != null) {
                builder.append(fullString).append("\n");
            }
        }
        catch (IOException e) {
            //  System.out.println("исключение в fileTextToString - FILE NOT RECOGNIZED");
            throw new IOException(e);
            //e.printStackTrace();
        }
        return builder.toString();
    }

    public void writeToFileWithChosenDirectory(Path directory, Path pathOfIncomingFile, String textToWrite) throws IOException {
        String fname = pathOfIncomingFile.getFileName().toString();
        int pos = fname.lastIndexOf(".");
        if (pos > 0) {
            fname = fname.substring(0, pos);
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH_mm_ss");
        Path pathOfWriteFile = Files.createFile(Path.of(directory.toString(),fname+"_"
                +LocalTime.now().format(dtf)+"_proceed.txt"));
        Files.write(pathOfWriteFile, textToWrite.getBytes());
    }
    public void writeToFile(Path pathOfIncomingFile, String textToWrite) throws IOException {
        String fname = pathOfIncomingFile.getFileName().toString();
        int pos = fname.lastIndexOf(".");
        if (pos > 0) {
            fname = fname.substring(0, pos);
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH_mm_ss");
        Path pathOfWriteFile = Files.createFile(Path.of(pathOfIncomingFile.getParent().toString(),fname+"_"
                +LocalTime.now().format(dtf)+"_proceed.txt"));
        Files.write(pathOfWriteFile, textToWrite.getBytes());
    }

    public String getExtension(String fileName) {
        String extension = "";
        int i = fileName.lastIndexOf('.');
        int p = Math.max(fileName.lastIndexOf('/'), fileName.lastIndexOf('\\'));
        if (i > p) {
            extension = fileName.substring(i + 1);
        }
        return extension;
    }
}

