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
    private Float learningRateDecrement = 0.7F;
    private Float learningRateIncrement = 1.05F;
    private Float er = 1.04F;
    private Float beta = 0.5F;
    private Float alpha = 0.2F;
    private Float minimalError = 0.25F;
    private int epochs;

    private NeuralNetwork network;

    public void start(List<List<Float>> inputSet, List<List<Float>> targetSet) {
        System.out.println("***********************************************************");
        System.out.println("Neural Network Back-Propagation Algorith");
        System.out.println();
        System.out.println();

        System.out.println("Insert learning rate:");
        learningRate = scanner.nextFloat();

//        System.out.println();
//        System.out.println("Insert beta parameter: ");
//        beta = scanner.nextFloat();

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
            List<Float> output;
            Float good = 0.F;
            Float bad = 0.F;
            Float error;
            System.out.println("Insert error (value lower than 0,5!):");
            error = scanner.nextFloat();

            for(int i = 0; i < network.getInputData().size(); i++) {
                output = feedForward(network.getInputData().get(i));
                if(Math.abs(output.get(0) - network.getTargetData().get(i).get(0)) < error) {
                    good++;
                }
                else {
                    bad++;
                }
            }
            Float result = (good / (good + bad)) * 100;
            System.out.println("Learn percentage: " + result);
        }
    }

    public void experiment(List<List<Float>> inputSet, List<List<Float>> targetSet) {
        System.out.println("***********************************************************");
        System.out.println("Neural Network Back-Propagation Algorith - Experiments");
        System.out.println();
        System.out.println();

        learningRate = 0.01F;
        beta = 0.5F;
        epochs = 10000;

        //Create new network

        for(int S1 = 0; S1 < 20; S1++) {
            for(int S2 = 0; S2 < 20; S2++) {
                network = new NeuralNetwork();
                network.createNewNetwork(inputSet, targetSet, learningRate, beta, S1, S2);
                train();
                test();
            }
        }

        network.testNetwork();
        train();
    }

    private NeuralNetwork train() {
        List<Float> output;
        Float epochSSE = 0.F;
        Float prevSSE;
        for(int x = 0; x < epochs; x++) {
            prevSSE = epochSSE;
            epochSSE = 0.F;
//            System.out.println("Epoch #" + x);
            for(int i = 0; i < network.getInputData().size(); i++) {
//                System.out.println("Input data: " + network.getInputData().get(i));
//                System.out.println("Target data: " + network.getTargetData().get(i));
                output = feedForward(network.getInputData().get(i));
//                System.out.println("Output:" + output);
//                System.out.println("Epoch #" + x);
                epochSSE += 0.5F*((network.getTargetData().get(i).get(0) -
                        output.get(0))*(network.getTargetData().get(i).get(0) - output.get(0)));
//                System.out.println();
                backPropagation(network.getTargetData().get(i), output);
            }
            adaptLearningRate(epochSSE, prevSSE);
//            System.out.println("Learning rate: " + learningRate);
            System.out.println("Epoch #" + x + " - SSE: " + epochSSE);
            if(epochSSE < minimalError) break;
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

        List<Float> delta = new ArrayList<>();
        for(int i = 0; i < network.getOutputLayer().getNeurons().size(); i++) {
            delta.add(i, errors.get(i));
        }
        network.getOutputLayer().setDelta(delta);

        for(int i = 0; i < network.getOutputLayer().getNumberOfOutputs(); i++) {
            List<Float> weightsCorrections = new ArrayList<>();
            for(int j = 0; j < network.getOutputLayer().getNumberOfInputs(); j++) {
                weightsCorrections.add(network.getOutputLayer().getDelta().get(i) *
                        network.getOutputLayer().getInputs().get(j));
            }
            updateWeights(network.getOutputLayer().getNeurons().get(i), weightsCorrections);
        }
    }


    private void backPropagationHidden(NeuronLayer currentLayer, NeuronLayer forwardLayer) {
        List<Float> delta = new ArrayList<>();
        for(int i = 0; i < currentLayer.getNumberOfOutputs(); i++) {
            delta.add(i, 0.F);
            for(int j = 0; j < forwardLayer.getDelta().size(); j++) {
                delta.set(i, delta.get(i) + (forwardLayer.getDelta().get(j) * forwardLayer.getNeurons().get(j).getWeights().get(i)));
            }
            delta.set(i, delta.get(i) * tanhDerivative(currentLayer.getOutputs().get(i)));
        }
        currentLayer.setDelta(delta);

        for(int i = 0; i < currentLayer.getNumberOfOutputs(); i++) {
            List<Float> weightsDelta = new ArrayList<>();
            for(int j = 0; j < currentLayer.getNumberOfInputs(); j++) {
                weightsDelta.add(currentLayer.getDelta().get(i) *
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

    private void adaptLearningRate(Float SSE, Float prevSSE) {
        if(SSE > er * prevSSE) learningRate *= learningRateDecrement;
        else if(SSE < prevSSE) learningRate *= learningRateIncrement;
        else if(SSE >= prevSSE && SSE <= er * prevSSE) return;
    }

    private Float sech(Float x) {
        return (float)(1/Math.cosh(x));
    }

    private Float tanhDerivative(Float x) {
        return (float)(beta*(1 - (x*x)));
    }
}

