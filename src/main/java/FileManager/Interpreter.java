package main.java.FileManager;

import java.util.*;

public class Interpreter {
    public Map<String, Float> classes = new HashMap<>();
    private Float classID = 0.0f;

    public List<List<Float>> interpretData(List<List<String>> stringsList) {
        List<List<Float>> resultList = new ArrayList<>();
        int i;

        for (List<String> strings: stringsList) {
            List<Float> tempList = new ArrayList<>();
            Float tempFloat = 0.0f;
            i = 1;

            for (String string: strings) {
                if(i == 1 || i == 2 || i == 15 || i == 16) {
                    i++;
                    continue;
                }
                try {
                    tempFloat.parseFloat(string);
                }
                catch(NumberFormatException e) {
                    if(string.equals("YES")) tempFloat = 1.0f;
                    else if(string.equals("NO")) tempFloat = 0.0f;
                    else if(classes.containsKey(string)) tempFloat = classes.get(string);
                    else {
                        tempFloat = insertCustomValue(string);
                    }
                }
                tempList.add(tempFloat);
                i++;
            }
            if(!resultList.contains(tempList)) {
                resultList.add(tempList);
            }
        }
        System.out.println(resultList.size());
        System.out.println("Output classes: " + classID);
        return resultList;
    }

    private Float insertCustomValue (String string) {
        Float tempFloat = 0.0f;

        if(classes.containsKey(string)) {
            return classes.get(string);
        }
        else {
            classes.put(string, classID);
            tempFloat = classID;
            classID++;
            return tempFloat;
        }
    }
}
