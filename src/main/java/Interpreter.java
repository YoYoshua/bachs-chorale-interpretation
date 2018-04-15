package main.java;

import java.util.*;

public class Interpreter {
    private Map<String, Float> classes = new HashMap<>();
    private Map<String, Float> knownParameters = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);

    public List<List<Float>> interpretData(List<List<String>> stringsList) {
        List<List<Float>> resultList = new ArrayList<>();

        for (List<String> strings: stringsList) {
            List<Float> tempList = new ArrayList<>();
            Float tempFloat = 0.0f;

            for (String string: strings) {
                try {
                    tempFloat.parseFloat(string);
                }
                catch(NumberFormatException e) {
                    if(string.equals("YES")) tempFloat = 1.0f;
                    else if(string.equals("NO")) tempFloat = 0.0f;
                    else {
                        tempFloat = insertCustomValue(string);
                    }
                }
                tempList.add(tempFloat);
            }
            resultList.add(tempList);
        }
        return resultList;
    }

    private Float insertCustomValue (String string) {
        Float tempFloat = 0.0f;

        if(knownParameters.containsKey(string)) {
            return knownParameters.get(string);
        }
        else {
            while(true) {
                System.out.println("Napotkano nieznany parametr: " + string + ". Jaka ma być jego wartość?");
                try {
                    tempFloat = scanner.nextFloat();
                    knownParameters.put(string, tempFloat);
                    break;
                }
                catch(InputMismatchException e) {
                    System.out.println("Wprowadzono niewłaściwy parametr!");
                    scanner.next();
                }
            }
            return tempFloat;
        }
    }
}
