package main.java.FileManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Parser {

    public List<List<String>> parseInput(List<String> strings) {
        List<List<String>> result = new ArrayList<>();
        String delims = ",";

        for (String string : strings) {
            Scanner scanner = new Scanner(string).useDelimiter(delims);
            List<String> tempList = new ArrayList<>();

            while(scanner.hasNext()) {
                tempList.add(scanner.next().trim());
            }
            result.add(tempList);
        }
        System.out.println(result.size());
        return result;
    }
}
