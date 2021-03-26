/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minexample.djl.cpugpumgmt;

import ai.djl.ndarray.NDArray;
import ai.djl.ndarray.NDList;
import ai.djl.training.loss.Loss;

/**
 *
 * @author TZ
 */
public class Loss2D extends Loss {

    private final int classAxis = 1;

    public Loss2D() {
        this("2DLoss");
    }

    public Loss2D(String name) {
        super(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NDArray evaluate(NDList label, NDList prediction) {
        //dummy loss for example
        NDArray logpredneg = prediction.singletonOrThrow().logSoftmax(classAxis).neg();
        NDArray loss = logpredneg.mean();
        return loss;
    }

}
