package main.java.FileManager;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class FileSaver {
    public void saveDataToFile(List<String> data, String filename) {
        PrintWriter writer = null;
        try {
            writer =  new PrintWriter(filename, "UTF-8");
            for (String line : data) {
                writer.write(line);
                writer.write("\n");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if(writer != null) {
                writer.close();
            }
        }
    }
}
