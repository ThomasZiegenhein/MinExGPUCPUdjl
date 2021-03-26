/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minexample.djl.cpugpumgmt;

import ai.djl.Device;
import ai.djl.MalformedModelException;
import ai.djl.Model;
import ai.djl.repository.zoo.ModelNotFoundException;
import ai.djl.training.DefaultTrainingConfig;
import ai.djl.training.EasyTrain;
import ai.djl.training.Trainer;
import ai.djl.training.evaluator.Accuracy;
import ai.djl.training.optimizer.Sgd;
import ai.djl.training.tracker.Tracker;
import ai.djl.translate.TranslateException;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 *
 * @author Thomas Ziegenhein
 */
public class Run {

    /**
     * @param args the command line arguments
     * @throws java.net.URISyntaxException
     * @throws java.io.IOException
     * @throws ai.djl.MalformedModelException
     * @throws ai.djl.translate.TranslateException
     */
    public static void main(String[] args) throws URISyntaxException, IOException, MalformedModelException, TranslateException, ModelNotFoundException {
        // set the device
        Device dev =Device.cpu();
        // load the MXNet model provided with the example
        Model mod = (new IOExample()).loadResource(dev);
        // set the arguments
        Arguments arguments = new Arguments(5);
        // set the configs using the device
        DefaultTrainingConfig config = setupTrainingConfig(arguments, dev);
        // create the trainer
        Trainer trainer = mod.newTrainer(config);
        // use EasyTrain provided in the example class for demonstration
        EasyTrain.fit(trainer, arguments.getEpoch(), Data.generateData(trainer.getManager(), 10), null);
    }

    public static DefaultTrainingConfig setupTrainingConfig(Arguments arguments, Device device) {
        Tracker learningRateTracker = Tracker.fixed(arguments.getLearnRate());
        Sgd opt = Sgd.sgd().optMomentum(arguments.getMomentum()).setLearningRateTracker(learningRateTracker).build();
        return new DefaultTrainingConfig(new Loss2D())
                .addEvaluator(new Accuracy())
                .optOptimizer(opt)
                .optDevices(new Device[]{device});
    }    

}
