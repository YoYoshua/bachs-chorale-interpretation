package main.java.Neuron;

public class Perceptron extends Neuron {

    @Override
    public void calculateError(Float target) {
        this.globalError = 0.0F;
        for(int i = 0; i < this.inputData.size(); i++) {
            this.localError = target - this.output;
            this.weights.set(i, this.weights.get(i) +
                    (this.learningRate*this.localError*this.inputData.get(i)));
            this.globalError += (this.localError*this.localError);
        }
    }

    @Override
    protected Float activationFunction(Float x) {
        if(x > 0) return 1.0F;
        else return -1.0F;
    }

    @Override
    protected Float activationDerivative(Float x) {
        return null;
    }
}
