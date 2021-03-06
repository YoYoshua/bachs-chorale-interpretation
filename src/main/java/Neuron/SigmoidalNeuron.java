package main.java.Neuron;

public class SigmoidalNeuron extends Neuron {

    public SigmoidalNeuron(Float learningRate, Float beta) {
        this.learningRate = learningRate;
        this.beta = beta;
    }

    @Override
    public void calculateError(Float target) {
        this.globalError = 0.0F;
        for(int i = 0; i < this.inputData.size(); i++) {
            this.localError = target - this.output;
            this.weights.set(i, this.weights.get(i) +
                    (this.learningRate*this.localError*activationDerivative(sum)*this.inputData.get(i)));
            this.globalError += (this.localError*this.localError);
        }
    }

    @Override
    protected Float activationFunction(Float x) {
        return (float)Math.tanh(beta*x);
    }

    @Override
    protected Float activationDerivative(Float x) {
        return (beta*(sech(x)*sech(x)));
    }
}
