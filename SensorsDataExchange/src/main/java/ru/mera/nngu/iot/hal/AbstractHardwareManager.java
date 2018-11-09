/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.mera.nngu.iot.hal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.mera.nngu.iot.gtw.GatewaySensorsMonitor;

/**
 *
 * @author macbookair
 */
public abstract class AbstractHardwareManager implements HardwareManager
{
    final static Logger log = LoggerFactory.getLogger(AbstractHardwareManager.class);
    protected GatewaySensorsMonitor monitor;
    
    @Override
    public abstract void start();
    
    @Override
    public abstract void stop();
    
    @Override
    public void registerMonitor(GatewaySensorsMonitor monitor)
    {
        this.monitor = monitor;
        log.info("Monitor registered");
    }
    
    @Override
    public void unregisterMonitor()
    {
        this.monitor = null;
        log.info("Monitor unregistered");
    }
}
