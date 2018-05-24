package main.java.Neuron;

import com.sun.javafx.scene.control.skin.VirtualFlow;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Neuron {
    protected Float learningRate;
    protected Float beta;

    protected List<Float> weights;
    protected List<Float> previousWeights;
    protected List<Float> inputData;

    protected Float sum;
    protected Float output;
    protected Float localError;
    protected Float globalError;

    Neuron() {
        this.learningRate = 0.1F;
        this.beta = 2.F;

        this.weights = new ArrayList<>();
        this.inputData = new ArrayList<>();
    }

    Neuron(Float learningRate, Float beta) {
        this.learningRate = learningRate;
        this.beta = beta;

        this.weights = new ArrayList<>();
        this.previousWeights = new ArrayList<>();
        this.inputData = new ArrayList<>();
    }

    public Float calculateOutput(List<Float> inputData) {
        this.inputData = inputData;
        this.sum = 0.0F;

        for(int i = 0; i < inputData.size(); i++) {
            this.sum += inputData.get(i)*weights.get(i);
        }
        this.output = activationFunction(this.sum);
        return this.output;
    }

    public void initialiseWeights(int inputsAmount) {
        weights.clear();
        Random random = new Random();
        for(int i = 0; i < inputsAmount; i++) {
            weights.add(random.nextFloat());
        }
        this.previousWeights = weights;
    }

    public void updateWeights (List<Float> newWeights) {
        this.previousWeights = this.weights;
        this.weights = newWeights;
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

    public List<Float> getWeights() {
        return weights;
    }

    public Float getLearningRate() {
        return learningRate;
    }

    public Float getGlobalError() {
        return globalError;
    }

    public Float getOutput() {
        return output;
    }

    public List<Float> getPreviousWeights() {
        return previousWeights;
    }

    public void setPreviousWeights(List<Float> previousWeights) {
        this.previousWeights = previousWeights;
    }
}