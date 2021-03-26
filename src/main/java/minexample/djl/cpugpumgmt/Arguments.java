/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minexample.djl.cpugpumgmt;

/**
 *
 * @author Thomas Ziegenhein
 */
import java.util.Map;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class Arguments {

    private int epoch;
    private float lr = 0.00001f;
    private float momentum = 0.9f;
    private int batchSize;
    private int maxGpus;
    private boolean isSymbolic;
    private boolean preTrained;
    private String outputDir;
    private long maxIterations;
    private String modelDir;
    private Map<String, String> criteria;

    public Arguments() {
        this(30);
    }

    public Arguments(int epochs) {

        epoch = epochs;
        maxGpus = 0; //Device.getGpuCount();
        batchSize = 2; //maxGpus > 0 ? 32 * maxGpus : 100;
        isSymbolic = false;
        preTrained = false;
        outputDir = null;
        maxIterations = 50;
        modelDir = null;
    }

    public static Options getOptions() {
        Options options = new Options();
        options.addOption(
                Option.builder("e")
                        .longOpt("epoch")
                        .hasArg()
                        .argName("EPOCH")
                        .desc("Numbers of epochs user would like to run")
                        .build());
        options.addOption(
                Option.builder("b")
                        .longOpt("batch-size")
                        .hasArg()
                        .argName("BATCH-SIZE")
                        .desc("The batch size of the training data.")
                        .build());
        options.addOption(
                Option.builder("g")
                        .longOpt("max-gpus")
                        .hasArg()
                        .argName("MAXGPUS")
                        .desc("Max number of GPUs to use for training")
                        .build());
        options.addOption(
                Option.builder("s")
                        .longOpt("symbolic-model")
                        .argName("SYMBOLIC")
                        .desc("Use symbolic model, use imperative model if false")
                        .build());
        options.addOption(
                Option.builder("p")
                        .longOpt("pre-trained")
                        .argName("PRE-TRAINED")
                        .desc("Use pre-trained weights")
                        .build());
        options.addOption(
                Option.builder("o")
                        .longOpt("output-dir")
                        .hasArg()
                        .argName("OUTPUT-DIR")
                        .desc("Use output to determine directory to save your model parameters")
                        .build());
        options.addOption(
                Option.builder("m")
                        .longOpt("max-iterations")
                        .hasArg()
                        .argName("max-iterations")
                        .desc(
                                "Limit each epoch to a fixed number of iterations to test the training script")
                        .build());
        options.addOption(
                Option.builder("d")
                        .longOpt("model-dir")
                        .hasArg()
                        .argName("MODEL-DIR")
                        .desc("pre-trained model file directory")
                        .build());
        options.addOption(
                Option.builder("r")
                        .longOpt("criteria")
                        .hasArg()
                        .argName("CRITERIA")
                        .desc("The criteria used for the model.")
                        .build());
        options.addOption(
                Option.builder("l")
                        .longOpt("learn-rate")
                        .hasArg()
                        .argName("LEARN-RATE")
                        .desc("Learning rate")
                        .build());
        options.addOption(
                Option.builder("m")
                        .longOpt("momentum")
                        .hasArg()
                        .argName("MOMENTUM")
                        .desc("Momentum")
                        .build());
        return options;
    }

    public int getBatchSize() {
        return batchSize;
    }

    public int getEpoch() {
        return epoch;
    }

    public int getMaxGpus() {
        return maxGpus;
    }
    
    public boolean isSymbolic() {
        return isSymbolic;
    }

    public boolean isPreTrained() {
        return preTrained;
    }

    public String getModelDir() {
        return modelDir;
    }

    public String getOutputDir() {
        return outputDir;
    }

    public long getMaxIterations() {
        return maxIterations;
    }

    public Map<String, String> getCriteria() {
        return criteria;
    }
    
    public float getLearnRate(){
        return lr;
    }
    
    public float getMomentum(){
        return momentum;
    }
    
}
