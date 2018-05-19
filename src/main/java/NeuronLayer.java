package main.java;

import java.util.List;

public class NeuronLayer {
    private List<Neuron> neurons;

    NeuronLayer(List<Neuron> neurons) {
        this.neurons = neurons;
    }

    public int getNeuronAmount() {
        return neurons.size();
    }
}
