package main.java.NeuralNetwork;

import main.java.Neuron.Neuron;

import java.util.ArrayList;
import java.util.List;

public class NeuronLayer {
    private List<Neuron> neurons;

    private NeuronLayer prevLayer;
    private NeuronLayer nextLayer;

    private int numberOfInputs; //ilosc neuronow w poprzedniej warstwie
    private int numberOfOutputs; //ilosc neuronow w obecnej warstwie;

    public enum Type {
        FIRST, HIDDEN, LAST
    }
    Type type;

    private List<Float> inputs;
    private List<Float> outputs;

    private List<Float> errors;
    private List<Float> gamma;


    NeuronLayer(List<Neuron> neurons, NeuronLayer prevLayer, NeuronLayer nextLayer) {
        this.neurons = neurons;
        this.prevLayer = prevLayer;
        this.nextLayer = nextLayer;

        if(prevLayer == null) this.type = Type.FIRST;
        else if(nextLayer == null) this.type = Type.LAST;
        else this.type = Type.HIDDEN;

        if(this.type == Type.FIRST) {
            this.numberOfInputs = 0; //do inicjalizacji pozniej
            this.inputs = new ArrayList<>();
        } else {
            this.numberOfInputs = prevLayer.getNeuronAmount();
            this.inputs = prevLayer.getOutputs();
        }

        this.outputs = new ArrayList<>();
        this.numberOfOutputs = this.neurons.size();
    }

    public List<Float> calculateOutput() {
        outputs.clear();

        for(Neuron neuron: neurons) {
            outputs.add(neuron.calculateOutput(inputs));
        }
        return outputs;
    }


    public void setInputs(List<Float> inputs) {
        this.inputs = inputs;
    }

    public void setNextLayer(NeuronLayer nextLayer) {
        this.type = Type.HIDDEN;
        this.nextLayer = nextLayer;
    }

    public List<Float> getInputs() {
        return inputs;
    }

    public List<Float> getOutputs() {
        return outputs;
    }

    public int getNeuronAmount() {
        return neurons.size();
    }

    public NeuronLayer getPrevLayer() {
        return prevLayer;
    }

    public NeuronLayer getNextLayer() {
        return nextLayer;
    }

    public void setNumberOfInputs(int numberOfInput) {
        this.numberOfInputs = numberOfInput;
    }

    public int getNumberOfInputs() {
        return numberOfInputs;
    }

    public void setNumberOfOutputs(int numberOfOutputs) {
        this.numberOfOutputs = numberOfOutputs;
    }

    public int getNumberOfOutputs() {
        return numberOfOutputs;
    }

    public List<Neuron> getNeurons() {
        return neurons;
    }


    public void setErrors(List<Float> errors) {
        this.errors = errors;
    }

    public List<Float> getErrors() {
        return errors;
    }

    public void setGamma(List<Float> gamma) {
        this.gamma = gamma;
    }

    public List<Float> getGamma() {
        return gamma;
    }
}
