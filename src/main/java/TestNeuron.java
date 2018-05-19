package main.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TestNeuron {

    private List<List<Float>> inputData = new ArrayList<>();
    private List<List<Float>> target = new ArrayList<>();
    private Normalizer normalizer = new Normalizer();
    private Scanner scanner = new Scanner(System.in);

    public void start() {

        //data init
        //Bias
        List<Float> bias = new ArrayList<>();
        bias.add(1.0F);
        bias.add(1.0F);
        bias.add(1.0F);
        bias.add(1.0F);
        inputData.add(bias);

        List<Float> temp = new ArrayList<>();
        temp.add(0.0F);
        temp.add(0.0F);
        temp.add(1.0F);
        temp.add(1.0F);
        inputData.add(temp);

        List<Float> temp2 = new ArrayList<>();
        temp2.add(0.0F);
        temp2.add(1.0F);
        temp2.add(0.0F);
        temp2.add(1.0F);
        inputData.add(temp2);
        inputData = normalizer.normalize(inputData);
        System.out.println(inputData);

        List<Float> temp3 = new ArrayList<>();
        temp3.add(0.0F);
        temp3.add(0.0F);
        temp3.add(0.0F);
        temp3.add(1.0F);
        target.add(temp3);
        target = normalizer.transpose(target);
        System.out.println(target);

        //Learning
        Neuron neuron = new Neuron(15.0F, 0.1F);
        List<Float> weightList = new ArrayList<>();
        Random random = new Random();
        for(int i = 0; i < inputData.get(0).size(); i++) {
            weightList.add(random.nextFloat());
        }
        System.out.println(weightList);

        neuron.setWeightList(weightList);
        int epoch = 10;
        for(int i = 0; i < epoch; i++) {
            System.out.println("Epoch #" + i);
            for(int j = 0; j < inputData.size(); j++) {
                neuron.calculateResult(inputData.get(j));
                neuron.calculateError(target.get(j).get(0));

                //Show results
                System.out.println("Target: " + target.get(j).get(0));
                System.out.println("Result: " + neuron.getResult());
                System.out.println("Neuron error: " + neuron.getError());
            }
        }

        //Testing
        List<Float> testData = new ArrayList<>();
        while(true) {
            for(int i = 0; i < inputData.get(0).size(); i++) {
                System.out.println("Input #" + i);
                testData.add(scanner.nextFloat());
            }
            System.out.println(neuron.calculateResult(testData));
        }
    }
}
