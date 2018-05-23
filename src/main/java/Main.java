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

import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        CLI cli = new CLI();
        cli.startCLI();
    }
}
