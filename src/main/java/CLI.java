package main.java;

import javafx.collections.transformation.TransformationList;
import main.java.FileManager.FileHandler;
import main.java.FileManager.Interpreter;
import main.java.FileManager.Parser;
import main.java.NeuralNetwork.BackPropagation;
import main.java.NeuralNetwork.NeuralNetwork;
import main.java.Normalizer.Normalizer;
import main.java.Normalizer.Transposer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CLI {
    private Scanner scanner = new Scanner(System.in);

    private File dataFile = new File("resources/iris.data.txt");
    //private File dataFile = new File("resources/jsbach_chorals_harmony.data");
    private FileHandler fileHandler = new FileHandler();
    private Parser parser = new Parser();
    private Interpreter interpreter = new Interpreter();
    private Transposer transposer = new Transposer();
    private Normalizer normalizer = new Normalizer();

    private NeuralNetwork network;

    private List<List<Float>> inputSet;
    private List<List<Float>> targetSet;

    private boolean dataPrepared = false;
    private boolean exit = true;


    public void startCLI() {
        BackPropagation backPropagation = new BackPropagation();

        while(exit) {
            System.out.println("What do you want to do? (data, train, test, exit)");
            String choice = scanner.nextLine();

            switch (choice) {
                case "test":
                    backPropagation.test();
                    break;

                case "data":
                    prepareData();
                    dataPrepared = true;
                    break;

                case "train":
                    if(!dataPrepared) {
                        System.out.println("You need to prepare data first!");
                        break;
                    } else {
                        backPropagation.start(inputSet, targetSet);
                        break;
                    }

                case "exit":
                    exit = false;
                    break;

                default:
                    System.out.println("Insert correct option!");
                    break;
            }

        }
    }

    private void prepareData() {

        //Data preparation phase
        List<String> dataList = fileHandler.readFile(dataFile);
        List<List<String>> parsedList = parser.parseInput(dataList);
        List<List<Float>> interpretedList = interpreter.interpretData(parsedList);
        System.out.println(interpretedList);

        //Normalisation phase
        inputSet = transposer.getInputSet(interpretedList);
        targetSet = transposer.getTargetSet(interpretedList);

        inputSet = normalizer.normalize(inputSet);
        //targetSet = normalizer.transpose(targetSet);
        targetSet = normalizer.normalize(targetSet);

        System.out.println(inputSet);
        System.out.println(targetSet);
//
//        //XOR Data
//        List<Float> bias = new ArrayList<>();
//        bias.add(1.0F);
//        bias.add(1.0F);
//        bias.add(1.0F);
//        bias.add(1.0F);
//        inputSet.add(bias);
//
//        List<Float> temp = new ArrayList<>();
//        temp.add(0.0F);
//        temp.add(0.0F);
//        temp.add(1.0F);
//        temp.add(1.0F);
//        inputSet.add(temp);
//
//        List<Float> temp2 = new ArrayList<>();
//        temp2.add(0.0F);
//        temp2.add(1.0F);
//        temp2.add(0.0F);
//        temp2.add(1.0F);
//        inputSet.add(temp2);
//        inputSet = normalizer.normalize(inputSet);
//        System.out.println(inputSet);
//
//        List<Float> temp3 = new ArrayList<>();
//        temp3.add(0.0F);
//        temp3.add(1.0F);
//        temp3.add(1.0F);
//        temp3.add(0.0F);
//        targetSet.add(temp3);
//        targetSet = normalizer.normalize(targetSet);
//        System.out.println(targetSet);
    }
}
