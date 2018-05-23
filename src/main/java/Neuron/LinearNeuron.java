package main.java.Neuron;

public class LinearNeuron extends Neuron {
    public LinearNeuron(Float learningRate) {
        this.learningRate = learningRate;
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
        return x;
    }

    @Override
    protected Float activationDerivative(Float x) {
        return 1.F;
    }
}
