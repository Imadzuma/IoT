/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.mera.nngu.iot;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.mera.nngu.iot.common.GatewayMeasurement;
import ru.mera.nngu.iot.hal.HardwareManager;
import ru.mera.nngu.iot.hal.AbstractHardwareManager;
import ru.mera.nngu.iot.hal.HardwareManagerImpl;
import ru.mera.nngu.iot.gtw.GatewaySensorsMonitor;
import ru.mera.nngu.iot.gtw.AbstractGatewaySensorsMonitor;
import ru.mera.nngu.iot.gtw.GatewaySensorsMonitorImpl;
import ru.mera.nngu.iot.util.GlobalConstants;
import ru.mera.nngu.iot.common.Encoder;
import ru.mera.nngu.iot.common.EncoderXML;
import ru.mera.nngu.iot.common.Decoder;
import ru.mera.nngu.iot.common.DecoderXML;
import ru.mera.nngu.iot.db.DBManager;
import ru.mera.nngu.iot.db.DBManagerImpl;
import ru.mera.nngu.iot.srv.ServerDataProcessor;
import ru.mera.nngu.iot.srv.ServerDataProcessorImpl;

/**
 *
 * @author macbookair
 */
public class AppStarter 
{
    final static Logger log = LoggerFactory.getLogger(AppStarter.class);
    
    public static void main(String [] args)
    {
        BasicConfigurator.configure();
        log.info("Starting application..");
        
        BlockingQueue<String> dataBus = new LinkedBlockingQueue();
        
        //NOTE: everything below is just a TEST. Created objects are STUBS and 
        //should  be replaced with actual implementations
        
        HardwareManager hwMgr = new HardwareManagerImpl();
        
        GatewaySensorsMonitor sensorsMon = new GatewaySensorsMonitorImpl(dataBus, hwMgr);
        sensorsMon.setEncoder(new EncoderXML());
        
        sensorsMon.start();
        hwMgr.start();
        
        DBManager dbMgr = new DBManagerImpl();
        
        ServerDataProcessor server = new ServerDataProcessorImpl(dataBus, dbMgr);
        server.setDecoder(new DecoderXML());
        server.start();
        
        Runtime.getRuntime().addShutdownHook(new Thread() {
                @Override
                public void run() 
                {
                    log.info("Shutting down...");
                    hwMgr.stop();
                    sensorsMon.stop();
                    server.stop();
                }
            });
    }
}
