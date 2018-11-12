/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.mera.nngu.iot.srv;

import java.util.List;
import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.CompletableFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.mera.nngu.iot.common.Decoder;
import ru.mera.nngu.iot.common.GatewayMeasurement;
import ru.mera.nngu.iot.db.DBManager;

/**
 *
 * @author macbookair
 */
public class ServerDataProcessorImpl implements ServerDataProcessor
{
    private final static Logger log = LoggerFactory.getLogger(ServerDataProcessorImpl.class);
    private final BlockingQueue<String> dataBus;
    private ExecutorService eventsProcessor;
    private ScheduledExecutorService dbUpdater;
    private ScheduledFuture<?> dbUpdateResult;
    private Decoder decoder;
    private final DBManager mgr;
    private final BlockingQueue<GatewayMeasurement> internalCache;
    private volatile boolean running;
    
    
    public ServerDataProcessorImpl(BlockingQueue<String> queue, DBManager db)
    {
        dataBus = queue;
        mgr = db;
        internalCache = new LinkedBlockingQueue();
        running = false;
    }
    
    @Override
    public void start()
    {
        if(decoder != null)
        {
            eventsProcessor = Executors.newFixedThreadPool(Runtime.getRuntime()
                    .availableProcessors() + 1);
            dbUpdater = Executors.newSingleThreadScheduledExecutor();
            dbUpdateResult = dbUpdater.scheduleWithFixedDelay(() -> {
                List<GatewayMeasurement> cachedEvents = new LinkedList();
                int drained = internalCache.drainTo(cachedEvents);
                mgr.batchSaveToDB(cachedEvents);
                log.info(drained + " events were read from the cache and saved "
                        + "to DB");
            }, 
            3000, 
            2000, 
            TimeUnit.MILLISECONDS);
            running = true;
            
            log.info("Started. Number of available processors: " 
                + Runtime.getRuntime().availableProcessors());
            
            CompletableFuture.runAsync(() -> {
                while(running)
                {
                    eventsProcessor.execute(() -> {
                        try {
                            String data = dataBus.take();
                            log.info("Retrieved event from Data Bus: " + data);
                            GatewayMeasurement event = decoder.decode(data);
                            internalCache.put(event);
                        }
                        catch(InterruptedException e) {
                            log.warn("Thread was interrupted while retrieving event from"
                                + " Data Bus. Exception: " + e);
                        }
                    });
                }
                log.info("Finished monitoring Data Bus for new events");
            });
        }
        else
            log.error("You should specify decoder before start");
    }
    
    @Override
    public void stop()
    {
        running = false;
        eventsProcessor.shutdown();
        dbUpdateResult.cancel(true);
        dbUpdater.shutdown();
        log.info("Stopped");
    }
    
    public void setDecoder(Decoder dec)
    {
        this.decoder = dec;
    }
}
