package main.java.NeuralNetwork;

import main.java.Neuron.Neuron;
import main.java.Neuron.SigmoidalNeuron;
import main.java.Normalizer.Normalizer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BackPropagation {
    private Scanner scanner = new Scanner(System.in);

    private Float learningRate;
    private Float beta;
    private Float alpha = 0.2F;
    private int epochs;

    private NeuralNetwork network;

    public void start(List<List<Float>> inputSet, List<List<Float>> targetSet) {
        System.out.println("***********************************************************");
        System.out.println("Neural Network Back-Propagation Algorith");
        System.out.println();
        System.out.println();

        System.out.println("Insert learning rate:");
        learningRate = scanner.nextFloat();

        System.out.println();
        System.out.println("Insert beta parameter: ");
        beta = scanner.nextFloat();

        System.out.println();
        System.out.println("Insert epochs: ");
        epochs = scanner.nextInt();

        //Create new network
        network = new NeuralNetwork();
        network.createNewNetwork(inputSet, targetSet, learningRate, beta);

        network.testNetwork();
        train();
    }

    public void test() {
        if(network == null) {
            System.out.println("You need to train your network first!");
            return;
        } else {
            List<Float> inputList = new ArrayList<>();

            for(int i = 1; i < network.getInputData().get(0).size(); i++) {
                System.out.println("Insert input #" + i + ":");
                inputList.add(scanner.nextFloat());
            }

            System.out.println(inputList);
            Normalizer normalizer = new Normalizer();
            inputList = normalizer.normalizeList(inputList);
            inputList.add(1.F);
            System.out.println(inputList);

            System.out.println("Output: " + feedForward(inputList));
        }
    }

    private NeuralNetwork train() {
        List<Float> output;
        Float epochSSE;
        for(int x = 0; x < epochs; x++) {
            epochSSE = 0.F;
//            System.out.println("Epoch #" + x);
            for(int i = 0; i < network.getInputData().size(); i++) {
//                System.out.println("Input data: " + network.getInputData().get(i));
//                System.out.println("Target data: " + network.getTargetData().get(i));
                output = feedForward(network.getInputData().get(i));
//                System.out.println("Output:" + output);
//                System.out.println("Epoch #" + x);
                epochSSE += ((network.getTargetData().get(i).get(0) -
                        output.get(0))*(network.getTargetData().get(i).get(0) - output.get(0)));
//                System.out.println();
                backPropagation(network.getTargetData().get(i), output);
            }
            System.out.println("Epoch #" + x + " - SSE: " + epochSSE);
        }
        return network;
    }

    private List<Float> feedForward(List<Float> input) {
        List<Float> outputs;
        network.getLayers().get(0).setInputs(input);
        for (int i = 0; i < network.getLayers().size() - 1; i++) {
            network.getLayers().get(i).calculateOutput();
            outputs = network.getLayers().get(i).getOutputs();
//            System.out.println(outputs);
            network.getLayers().get(i + 1).setInputs(outputs);
        }
        return network.getOutputLayer().calculateOutput();
    }

    private void backPropagation(List<Float> target, List<Float> outputs) {
        for(int i = network.getLayers().size() - 1; i >= 0; i--) {
            if(network.getLayers().get(i).type == NeuronLayer.Type.LAST) {
                backPropagationOutput(target, outputs);
            } else {
                backPropagationHidden(network.getLayers().get(i), network.getLayers().get(i + 1));
            }
        }
    }

    private void backPropagationOutput(List<Float> targetList, List<Float> outputs) {
        List<Float> errors = new ArrayList<>();
        for(int i = 0; i < network.getOutputLayer().getNeurons().size(); i++) {
            errors.add(outputs.get(i) - targetList.get(i));
        }
        network.getOutputLayer().setErrors(errors);

        List<Float> gamma = new ArrayList<>();
        for(int i = 0; i < network.getOutputLayer().getNeurons().size(); i++) {
            gamma.add(network.getOutputLayer().getErrors().get(i)/* *
                    tanhDerivative(outputs.get(i))*/);
        }
        network.getOutputLayer().setGamma(gamma);

        for(int i = 0; i < network.getOutputLayer().getNeurons().size(); i++) {
            List<Float> weightsDelta = new ArrayList<>();
            for(int j = 0; j < network.getOutputLayer().getNumberOfInputs(); j++) {
                weightsDelta.add(network.getOutputLayer().getGamma().get(i) *
                        network.getOutputLayer().getInputs().get(j));
            }
            updateWeights(network.getOutputLayer().getNeurons().get(i), weightsDelta);
        }
    }


    private void backPropagationHidden(NeuronLayer currentLayer, NeuronLayer forwardLayer) {
        List<Float> gamma = new ArrayList<>();
        for(int i = 0; i < currentLayer.getNeurons().size(); i++) {
            gamma.add(0.F);
            for(int j = 0; j < forwardLayer.getGamma().size(); j++) {
                gamma.set(i, gamma.get(i) + (forwardLayer.getGamma().get(j) * forwardLayer.getNeurons().get(j).getWeights().get(i)));
            }
            gamma.set(i, gamma.get(i) * tanhDerivative(currentLayer.getOutputs().get(i)));
        }
        currentLayer.setGamma(gamma);

        for(int i = 0; i < currentLayer.getNeurons().size(); i++) {
            List<Float> weightsDelta = new ArrayList<>();
            for(int j = 0; j < currentLayer.getNumberOfInputs(); j++) {
                weightsDelta.add(currentLayer.getGamma().get(i) *
                        currentLayer.getInputs().get(j));
            }
            updateWeights(currentLayer.getNeurons().get(i), weightsDelta);
        }
    }

    private void updateWeights(Neuron neuron, List<Float> weightsDelta) {
        List<Float> updatedWeights = new ArrayList<>();
        for(int i = 0; i < weightsDelta.size(); i++) {
            updatedWeights.add(neuron.getWeights().get(i) - (weightsDelta.get(i)*learningRate) +
                    (alpha * (neuron.getWeights().get(i) - neuron.getPreviousWeights().get(i))));
        }
        neuron.updateWeights(updatedWeights);
    }

    private Float sech(Float x) {
        return (float)(1/Math.cosh(x));
    }

    private Float tanhDerivative(Float x) {
        return (float)Math.tanh(beta*x);
    }
}

