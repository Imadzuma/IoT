/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.mera.nngu.iot.gtw;

import java.util.Map;
import ru.mera.nngu.iot.common.Encoder;

/**
 *
 * @author macbookair
 */
public interface GatewaySensorsMonitor 
{
    public void start();
    
    public void stop();
    
    public void sensorsReadingAvailable(Map<String, Object> values);
    
    //TODO: remove when all stubs are resolved
    public void setEncoder(Encoder enc);
}
