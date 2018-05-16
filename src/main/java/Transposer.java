package main.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Transposer {

    private Scanner scanner = new Scanner(System.in);

    public List<List<Float>> getInputSet(List<List<Float>> fullSet) {
        List<List<Float>> resultList = new ArrayList<>();

        System.out.println("Podaj ilosc cech w zbiorze wejsciowym:");
        int inputLength = scanner.nextInt();

        for(List<Float> set: fullSet) {
            List<Float> tempList = new ArrayList<>();
            for (int i = 0; i < inputLength; i++) {
                tempList.add(set.get(i));
            }
            resultList.add(tempList);
        }
        resultList = transpose(resultList);
        return resultList;
    }

    public List<List<Float>> getTargetSet(List<List<Float>> fullSet) {
        List<List<Float>> resultList = new ArrayList<>();

        for(List<Float> set: fullSet) {
            List<Float> tempList = new ArrayList<>();
            tempList.add(set.get(set.size() - 1));
            resultList.add(tempList);
        }
        resultList = transpose(resultList);
        return resultList;
    }

    protected List<List<Float>> transpose(List<List<Float>> matrix) {
        List<List<Float>> result = new ArrayList<>();

        for(int i = 0; i < matrix.get(0).size(); i++) {
            List<Float> tempList = new ArrayList<>();
            for(List<Float> set: matrix) {
                tempList.add(set.get(i));
            }
            result.add(tempList);
        }
        return result;
    }
}
