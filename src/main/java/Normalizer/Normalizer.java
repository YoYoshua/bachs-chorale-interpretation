package main.java.Normalizer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Normalizer extends Transposer{

    public List<List<Float>> normalize(List<List<Float>> inputData) {
        List<List<Float>> normalizedList = new ArrayList<>();
        for (List<Float> set: inputData) {
            List<Float> temp = new ArrayList<>();
            for (Float number : set) {
                //Normalizacja [-1, 1]
                Float norm = number;
                if(set.get(set.indexOf(Collections.min(set))) < set.get(set.indexOf(Collections.max(set)))) {
                    norm = ((number - set.get(set.indexOf(Collections.min(set)))) /
                            (set.get(set.indexOf(Collections.max(set))) - set.get(set.indexOf(Collections.min(set))))) * 2 - 1;
                }
                temp.add(norm);
            }
            normalizedList.add(temp);
        }
        normalizedList = transpose(normalizedList);
        return normalizedList;
    }

    public List<Float> normalizeList(List<Float> inputData) {
        List<List<Float>> preparedList = new ArrayList<>();
        List<Float> result;

        preparedList.add(inputData);
        preparedList = normalize(preparedList);
        preparedList = transpose(preparedList);
        result = preparedList.get(0);
        return result;
    }
}
