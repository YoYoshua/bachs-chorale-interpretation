package main.java;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        File dataFile = new File("resources/jsbach_chorals_harmony.data");
        FileHandler fileHandler = new FileHandler();
        Parser parser = new Parser();

        List<String> dataList;
        List<List<String>> parsedList;

        dataList = fileHandler.readFile(dataFile);
        parsedList = parser.parseInput(dataList);
        System.out.println(parsedList);
    }
}
