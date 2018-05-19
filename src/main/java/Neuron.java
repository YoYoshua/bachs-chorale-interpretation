package main.java;

import java.util.List;

public class Neuron {
    private Float learningRate;
    private List<Float> weights;
    private List<Float> inputData;

    private Float sum;
    private Float output;
    private Float localError;
    private Float globalError;

    Neuron(Float learningRate) {
        this.learningRate = learningRate;
    }

    public void calculateOutput(List<Float> inputData) {
        this.inputData = inputData;
        this.sum = 0.0F;

        for(int i = 0; i < inputData.size(); i++) {
            this.sum += inputData.get(i)*weights.get(i);
        }
        this.output = activationFunction(this.sum);
    }

    public void calculateError(Float target) {
        this.globalError = 0.0F;
        for(int i = 0; i < this.inputData.size(); i++) {
            this.localError = target - this.output;
            this.weights.set(i, this.weights.get(i) + (this.learningRate*this.localError*this.inputData.get(i)));
            this.globalError += (this.localError*this.localError);
        }
    }

    private Float activationFunction(Float x) {
        if(x > 0) return 1.0F;
        else return -1.0F;
    }

    public void setWeights(List<Float> weights) {
        this.weights = weights;
    }

    public Float getGlobalError() {
        return globalError;
    }

    public Float getOutput() {
        return output;
    }
}