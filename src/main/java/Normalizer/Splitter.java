package main.java.Normalizer;

import java.util.ArrayList;
import java.util.List;

public class Splitter {

    private List<List<Float>> inputData = new ArrayList<>();
    private List<List<Float>> testData = new ArrayList<>();

    private List<List<Float>> targetData = new ArrayList<>();
    private List<List<Float>> targetTestData = new ArrayList<>();

    public void splitData(List<List<Float>> inputSet, List<List<Float>> targetSet) {
        for(int i = 0; i < inputSet.size(); i++) {
            if(i % 3 != 0) {
                inputData.add(inputSet.get(i));
                targetData.add(targetSet.get(i));
            } else {
                testData.add(inputSet.get(i));
                targetTestData.add(targetSet.get(i));
            }
        }
    }

    public List<List<Float>> getInputData() {
        return inputData;
    }

    public List<List<Float>> getTestData() {
        return testData;
    }

    public List<List<Float>> getTargetData() {
        return targetData;
    }

    public List<List<Float>> getTargetTestData() {
        return targetTestData;
    }
}
