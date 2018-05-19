package main.java;

import java.util.List;

public class Neuron {
    private List<Float> weightList;
    private List<Float> inputList;

    private Float beta;
    private Float learningRate;
    private Float result = 0.0F;
    private Float sum = 0.0F;

    private Float error;

    Neuron(Float beta, Float learningRate) {
        this.beta = beta;
        this.learningRate = learningRate;
    }

    public Float calculateResult(List<Float> inputList) {
        sum = 0.0F;
        this.inputList = inputList;

        for(int i = 0; i < weightList.size(); i++) {
            sum += weightList.get(i)*inputList.get(i);
        }
        result = sendThroughActivationFunction(sum);
        return result;
    }

    private Float sendThroughActivationFunction(Float result) {
        return (float)Math.tanh(beta*result);
    }

    private Float sendThroughDerivative(Float result) {
        Float derivative = beta*sech(result)*sech(result);
        return derivative;
    }

    public void calculateError(Float target) {
        Float delta = 0.0F;
        for(int i = 0; i < weightList.size(); i++) {
            delta = -(target - result)*sendThroughDerivative(sum);
            error = learningRate*delta*inputList.get(i);
            weightList.set(i, adjustWeight(error, weightList.get(i)));
        }
    }

    private Float sech(Float x) {
        return (float)(1/Math.cosh(x));
    }

    private Float adjustWeight(Float error, Float weight) {
        return weight - error;
    }

    public List<Float> getWeightList() {
        return weightList;
    }

    public void setWeightList(List<Float> weightList) {
        this.weightList = weightList;
    }

    public List<Float> getInputList() {
        return inputList;
    }

    public void setInputList(List<Float> inputList) {
        this.inputList = inputList;
    }

    public Float getError() {
        return error;
    }

    public void setError(Float error) {
        this.error = error;
    }

    public Float getResult() {
        return result;
    }

}
