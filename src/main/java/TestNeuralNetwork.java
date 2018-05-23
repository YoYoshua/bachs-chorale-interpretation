package main.java;

import main.java.FileManager.FileHandler;
import main.java.FileManager.Interpreter;
import main.java.FileManager.Parser;
import main.java.NeuralNetwork.NeuralNetwork;
import main.java.NeuralNetwork.NeuronLayer;
import main.java.Neuron.Neuron;
import main.java.Neuron.SigmoidalNeuron;
import main.java.Normalizer.Normalizer;
import main.java.Normalizer.Transposer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TestNeuralNetwork {
    public void testNeuralNetwork() {
        //Data
        File dataFile = new File("resources/jsbach_chorals_harmony.data");
        FileHandler fileHandler = new FileHandler();
        Parser parser = new Parser();
        Interpreter interpreter = new Interpreter();
        Transposer transposer = new Transposer();
        Normalizer normalizer = new Normalizer();

        List<String> dataList;
        List<List<String>> parsedList;
        List<List<Float>> interpretedList;
        List<List<Float>> inputSet;
        List<List<Float>> targetSet;

        //Data preparation phase
        dataList = fileHandler.readFile(dataFile);
        parsedList = parser.parseInput(dataList);
        interpretedList = interpreter.interpretData(parsedList);

        //Normalisation phase
        inputSet = transposer.getInputSet(interpretedList);
        targetSet = transposer.getTargetSet(interpretedList);
        inputSet = normalizer.normalize(inputSet);
        targetSet = normalizer.normalize(targetSet);

        //Neural network test
        NeuralNetwork network = new NeuralNetwork();

        List<Neuron> inputLayer = new ArrayList<>();
        List<Neuron> hiddenLayer1 = new ArrayList<>();
        List<Neuron> hiddenLayer2 = new ArrayList<>();
        List<Neuron> outputLayer = new ArrayList<>();

        Float learningRate = 2.5F;
        Float beta = 0.5F;

        //Input layer
        for (int i = 0; i < 8; i++) {
            inputLayer.add(new SigmoidalNeuron(learningRate, beta));
        }

        //Hidden layers
        for (int i = 0; i < 30; i++) {
            hiddenLayer1.add(new SigmoidalNeuron(learningRate, beta));
        }

        for (int i = 0; i < 40; i++) {
            hiddenLayer2.add(new SigmoidalNeuron(learningRate, beta));
        }

        //Output layer
        outputLayer.add(new SigmoidalNeuron(learningRate, beta));

        //Add layers
        network.addLayer(inputLayer);
        network.addLayer(hiddenLayer1);
        network.addLayer(hiddenLayer2);
        network.addLayer(outputLayer);

        //Set data
        network.setData(inputSet, targetSet);
    }
}
