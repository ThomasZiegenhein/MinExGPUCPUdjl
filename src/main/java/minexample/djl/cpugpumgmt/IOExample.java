/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minexample.djl.cpugpumgmt;

import ai.djl.Device;
import ai.djl.MalformedModelException;
import ai.djl.Model;
import ai.djl.engine.Engine;
import ai.djl.repository.zoo.ModelNotFoundException;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author TZ
 */
public class IOExample {
            
    public Model loadResource(Device device) throws URISyntaxException, IOException, MalformedModelException, ModelNotFoundException {
        return loadResource(this, "/minexample/djl/cpugpumgmt/net/", "OneBlock_Empty", "OneBlock_Empty", device);        
    }
    
    public Model loadResource(Object ref, String resource, String networkFolder, String modelName, Device device) throws URISyntaxException, IOException, MalformedModelException, ModelNotFoundException {
        File netFile;
        URL res = ref.getClass().getResource(resource);
        netFile = new File(res.getFile(), networkFolder);
        return load(netFile, modelName, device);
    }
    
    public Model load(File path, String modelName, Device device) throws IOException, MalformedModelException, ModelNotFoundException {        
        
        Model mod = Model.newInstance(modelName, device, Engine.getInstance().getEngineName());
//        Model mod = Engine.getInstance().newModel(modelName);  
        Path main = Paths.get(path.toURI());
        mod.load(main, modelName);
        return mod;
    }
    

}
