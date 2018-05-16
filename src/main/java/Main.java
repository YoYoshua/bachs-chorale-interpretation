package main.java;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        File dataFile = new File("resources/jsbach_chorals_harmony.data");
        FileHandler fileHandler = new FileHandler();
        Parser parser = new Parser();
        Interpreter interpreter = new Interpreter();
        Transposer transposer = new Transposer();
        Normalizer normalizer = new Normalizer();

        List<String> dataList;
        List<List<String>> parsedList;
        List<List<Float>> interpretedList;
        List<List<Float>> inputSet;
        List<List<Float>> targetSet;

        //Data preparation phase
        dataList = fileHandler.readFile(dataFile);
        parsedList = parser.parseInput(dataList);
        interpretedList = interpreter.interpretData(parsedList);

        //Normalisation phase
        inputSet = transposer.getInputSet(interpretedList);
        System.out.println(inputSet);
        targetSet = transposer.getTargetSet(interpretedList);
        inputSet = normalizer.normalize(inputSet);
        System.out.println(inputSet);

        //Saving to file

        //Learning phase

        //Saving to file phase
        inputSet = transposer.transpose(inputSet);
        final String interpretedData = "resources/interpreted.txt";
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(interpretedData));
            for (List<Float> set: interpretedList) {
                for(int i = 0; i < set.size(); i++) {
                    writer.write(set.get(i).toString());
                    if(i != set.size() - 1) {
                        writer.write(",");
                    }
                }
                writer.write("\n");
            }
        } catch(IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if(writer != null)
                    writer.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        inputSet = transposer.transpose(inputSet);
        final String plik = "resources/output.txt";
        writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(plik));
            for (List<Float> set: inputSet) {
                for(int i = 0; i < set.size(); i++) {
                    writer.write(set.get(i).toString());
                    if(i != set.size() - 1) {
                        writer.write(",");
                    }
                }
                writer.write("\n");
            }
        } catch(IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if(writer != null)
                    writer.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        final String classes = "resources/classes.txt";
        writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(classes));
            for(Map.Entry<String, Float> entry : interpreter.classes.entrySet()) {
                writer.write(entry.getKey() + " - " + entry.getValue());
                writer.write("\n");
            }
        } catch(IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if(writer != null)
                    writer.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
