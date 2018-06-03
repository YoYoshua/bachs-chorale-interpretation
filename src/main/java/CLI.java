package main.java;

import main.java.FileManager.FileHandler;
import main.java.FileManager.FileSaver;
import main.java.FileManager.Interpreter;
import main.java.FileManager.Parser;
import main.java.NeuralNetwork.BackPropagation;
import main.java.NeuralNetwork.NeuralNetwork;
import main.java.Normalizer.Normalizer;
import main.java.Normalizer.Splitter;
import main.java.Normalizer.Transposer;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class CLI {
    private Scanner scanner = new Scanner(System.in);

    //private File dataFile = new File("resources/data.txt");
    private File dataFile = new File("resources/iris.data.txt");
    //private File dataFile = new File("resources/jsbach_chorals_harmony.data");
    //private File dataFile = new File("resources/jsbach_chorals_harmony_2.data");
    private FileHandler fileHandler = new FileHandler();
    private Parser parser = new Parser();
    private Interpreter interpreter = new Interpreter();
    private Transposer transposer = new Transposer();
    private Normalizer normalizer = new Normalizer();
    private Splitter splitter = new Splitter();
    private FileSaver fileSaver = new FileSaver();

    private Date date = new Date();

    private List<String> results;

    private NeuralNetwork network;

    private List<List<Float>> inputSet;
    private List<List<Float>> testSet;

    private List<List<Float>> targetSet;
    private List<List<Float>> targetTestSet;

    private boolean dataPrepared = false;
    private boolean exit = true;


    public void startCLI() {
        BackPropagation backPropagation = new BackPropagation();

        while(exit) {
            System.out.println("What do you want to do? (data, train, test, experiment, exit)");
            String choice = scanner.nextLine();

            switch (choice) {
                case "test":
                    backPropagation.test(testSet, targetTestSet);
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

                case "experiment":
                    if(!dataPrepared) {
                        System.out.println("You need to prepare data first!");
                        break;
                    } else {
                        results = backPropagation.experiment(inputSet, testSet, targetSet, targetTestSet);
                        fileSaver.saveDataToFile(results, "resources/results.txt");
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

        testSet = inputSet;
        targetTestSet = targetSet;

//        splitter.splitData(inputSet, targetSet);
//        inputSet = splitter.getInputData();
//        testSet = splitter.getTestData();

//        targetSet = splitter.getTargetData();
//        targetTestSet = splitter.getTargetTestData();

        System.out.println("Input set size: " + inputSet.size());
        System.out.println("Test set size: " + testSet.size());
        System.out.println("Target set size: " + targetSet.size());
        System.out.println("Test target set size: " + targetTestSet.size());
    }
}
