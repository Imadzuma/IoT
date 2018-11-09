/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.mera.nngu.iot.gtw;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.BlockingQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.mera.nngu.iot.common.Encoder;
import ru.mera.nngu.iot.common.GatewayMeasurement;
import ru.mera.nngu.iot.hal.HardwareManager;

/**
 *
 * @author macbookair
 */
public abstract class AbstractGatewaySensorsMonitor implements GatewaySensorsMonitor
{
    static final Logger log = LoggerFactory.getLogger(AbstractGatewaySensorsMonitor.class);
    private ExecutorService processor;
    private final BlockingQueue<String> dataBus;
    private Encoder encoder;
    private final HardwareManager hwMgr;
            
    //TODO: don't pass HardwareManager as parameter, but create inside the constructor
    public AbstractGatewaySensorsMonitor(BlockingQueue<String> queue, HardwareManager mgr)
    {
        dataBus = queue;
        hwMgr = mgr;
    }
    
    @Override
    public void start()
    {
        log.info("Starting..");
        if(encoder != null)
        {
            processor = Executors.newSingleThreadExecutor();
            hwMgr.registerMonitor(this);
            log.info("Started");
        }
        else
            log.error("You should specify encoder before start");
    }
    
    @Override
    public void stop()
    {
        hwMgr.unregisterMonitor();
        processor.shutdown();
        log.info("Stopped");
    }
    
    @Override
    public void sensorsReadingAvailable(Map<String, Object> values)
    {
        processor.execute(() -> {
            try {
                String event = encoder.encode(createMeasurement(values));
                dataBus.put(event);
                log.info("Successfully put event " + event + " to the Data Bus");
            }
            catch(InterruptedException e) {
                log.warn("Thread was interrupted while storing event "
                        + "to the Data Bus. Exception: " + e);
            }
        });
    }
    
    public void setEncoder(Encoder enc)
    {
        this.encoder = enc;
    }
    
    protected abstract GatewayMeasurement createMeasurement(Map<String, Object> values);
}
