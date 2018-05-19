package main.java.Neuron;

import java.util.List;

public abstract class Neuron {
    protected Float learningRate;
    protected Float beta;

    protected List<Float> weights;
    protected List<Float> inputData;

    protected Float sum;
    protected Float output;
    protected Float localError;
    protected Float globalError;

    Neuron() {
        this.learningRate = 0.1F;
        this.beta = 2.F;
    }

    Neuron(Float learningRate, Float beta) {
        this.learningRate = learningRate;
        this.beta = beta;
    }

    public void calculateOutput(List<Float> inputData) {
        this.inputData = inputData;
        this.sum = 0.0F;

        for(int i = 0; i < inputData.size(); i++) {
            this.sum += inputData.get(i)*weights.get(i);
        }
        this.output = activationFunction(this.sum);
    }

    public abstract void calculateError(Float target);
    protected abstract Float activationFunction(Float x);
    protected abstract Float activationDerivative(Float x);

    protected Float sech(Float x) {
        return (float)(1/Math.cosh(x));
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