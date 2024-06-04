package ru.inferno_geek.criminal_judicial_practice_analysis;

import org.deeplearning4j.datasets.iterator.impl.ListDataSetIterator;
import org.deeplearning4j.nn.api.OptimizationAlgorithm;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.conf.layers.RnnOutputLayer;
import org.deeplearning4j.nn.conf.layers.recurrent.SimpleRnn;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.nn.weights.WeightInit;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.nd4j.linalg.dataset.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.learning.config.Adam;
import org.nd4j.linalg.learning.config.Sgd;
import org.nd4j.linalg.lossfunctions.LossFunctions;

import java.util.Arrays;
import java.util.List;

public class BasicNeuralNetwork {

    public static void main(String[] args) {
        int numInputs = 4;
        int numOutputs = 3;
        int numHiddenNodes = 10;
        int batchSize = 1;
        int totalExamples = 1000;

        List<INDArray> inputList = createRandomInputData(numInputs, batchSize, totalExamples);
        List<INDArray> outputList = createRandomOutputData(numOutputs, batchSize, totalExamples);

        DataSetIterator dataSetIterator = new ListDataSetIterator<>(Arrays.asList(inputList, outputList), batchSize);

        MultiLayerConfiguration configuration = new NeuralNetConfiguration.Builder()
                .seed(123)
                .optimizationAlgo(OptimizationAlgorithm.STOCHASTIC_GRADIENT_DESCENT)
                .updater(new Sgd(0.01))
                .list()
                .layer(new DenseLayer.Builder().nIn(numInputs).nOut(numHiddenNodes)
                        .activation(Activation.RELU)
                        .weightInit(WeightInit.XAVIER)
                        .build())
                .layer(new OutputLayer.Builder().nIn(numHiddenNodes).nOut(numOutputs)
                        .activation(Activation.SOFTMAX)
                        .lossFunction(LossFunctions.LossFunction.MCXENT)
                        .weightInit(WeightInit.XAVIER)
                        .build())
                .build();

        MultiLayerNetwork model = new MultiLayerNetwork(configuration);
        model.init();
        model.setListeners(new ScoreIterationListener(10));

        for( int i=0; i < 100; i++ ) {
            model.fit(dataSetIterator);
        }

        INDArray output = model.output(Nd4j.create(new double[][]{{1.0, 2.0, 3.0, 4.0}}));
        System.out.println("Output: " + output);
    }

    private static List<INDArray> createRandomInputData(int numInputs, int batchSize, int totalExamples) {
        List<INDArray> result = Lists.newArrayList();

        for( int i=0; i < totalExamples; i++ ) {
            double[] inputArray = new double[numInputs];
            for( int j=0; j < numInputs; j++ ) {
                inputArray[j] = Math.random();
            }
            INDArray input = Nd4j.create(inputArray, new int[]{batchSize, numInputs});
            result.add(input);
        }

        return result;
    }

    private static List<INDArray> createRandomOutputData(int numOutputs, int batchSize, int totalExamples) {
        List<INDArray> result = Lists.newArrayList();

        for( int i=0; i < totalExamples; i++ ) {
            double[] outputArray = new double[numOutputs];
            for( int j=0; j < numOutputs; j++ ) {
                outputArray[j] = Math.random();
            }
            INDArray output = Nd4j.create(outputArray, new int[]{batchSize, numOutputs});
            result.add(output);
        }

        return result;
    }
}
