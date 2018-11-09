/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.mera.nngu.iot.hal;

import ru.mera.nngu.iot.gtw.GatewaySensorsMonitor;

/**
 *
 * @author macbookair
 */
public interface HardwareManager 
{
    public void start();
    
    public void stop();
    
    public void registerMonitor(GatewaySensorsMonitor monitor);
    
    public void unregisterMonitor();
}
