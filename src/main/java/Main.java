package main.java;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        File dataFile = new File("resources/jsbach_chorals_harmony.data");
        FileHandler fileHandler = new FileHandler();
        Parser parser = new Parser();
        Interpreter interpreter = new Interpreter();

        List<String> dataList;
        List<List<String>> parsedList;
        List<List<Float>> interpretedList;

        dataList = fileHandler.readFile(dataFile);
        parsedList = parser.parseInput(dataList);
        interpretedList = interpreter.interpretData(parsedList);
        System.out.println(interpretedList);
    }
}
