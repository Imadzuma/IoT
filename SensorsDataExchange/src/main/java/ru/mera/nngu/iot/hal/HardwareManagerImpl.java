/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.mera.nngu.iot.hal;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import ru.mera.nngu.iot.util.GlobalConstants;

/**
 *
 * @author Маргарита
 */
public class HardwareManagerImpl extends AbstractHardwareManager {
    private ScheduledExecutorService sensorsReader;
    
   @Override
    public void start(){
        log.info("Hardware Manager started");
        AtomicLong fakeTemp = new AtomicLong(1);
        AtomicLong fakeHum = new AtomicLong(1);
        sensorsReader = Executors.newSingleThreadScheduledExecutor();
        sensorsReader.scheduleWithFixedDelay(() -> {
            Map<String, Object> readings = new HashMap();
            readings.put(GlobalConstants.SENSOR_KEY_TEMPERATURE, fakeTemp.getAndIncrement());
            readings.put(GlobalConstants.SENSOR_KEY_HUMIDITY, fakeHum.getAndIncrement());
            monitor.sensorsReadingAvailable(readings);
            }, 
            500, 
            500,
            TimeUnit.MILLISECONDS);
    }
    
    @Override
    public void stop() {
        sensorsReader.shutdown();
        log.info("Hardware Manager stopped");
    };
    
}
