/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minexample.djl.cpugpumgmt;

import ai.djl.ndarray.NDArray;
import ai.djl.ndarray.NDManager;
import ai.djl.ndarray.index.NDIndex;
import ai.djl.ndarray.types.DataType;
import ai.djl.ndarray.types.Shape;
import ai.djl.training.dataset.ArrayDataset;

/**
 *
 * @author TZ
 */
public class Data {

    public static ArrayDataset generateData(NDManager ndm, int num_examples) {
        int iCounterData = 0;
        int batchSize = 2;
        NDArray dataInput = ndm.create(new Shape(num_examples, 1, 10, 10), DataType.FLOAT32);
        NDArray dataLabel = ndm.create(new Shape(num_examples, 1, 10, 10), DataType.FLOAT32);        
        for (int i = 0; i < num_examples; i++) {
            NDArray train = ndm.ones(new Shape(10, 10), DataType.FLOAT32);
            NDArray label = ndm.randomNormal(new Shape(10, 10), DataType.FLOAT32);
            dataInput.set(new NDIndex(iCounterData, 0), train);
            dataLabel.set(new NDIndex(iCounterData, 0), label);
        }
        return new ArrayDataset(
                new ArrayDataset.Builder()
                .setData(dataInput)
                .optLabels(dataLabel)
                .setSampling(batchSize, false));
    }

}
