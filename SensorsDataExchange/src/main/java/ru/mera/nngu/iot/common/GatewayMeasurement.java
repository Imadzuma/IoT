/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.mera.nngu.iot.common;

import java.util.Map;

/**
 *
 * @author macbookair
 */
public interface GatewayMeasurement
{
    public String getGatewayId();
    
    public long getMeasurementTimestamp();
    
    public Object getSensorValue(String sensor);
    
    public Map<String, Object> getSensorsValues();
}
