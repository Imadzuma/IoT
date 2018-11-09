/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.mera.nngu.iot.common;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.mera.nngu.iot.AppStarter;
import ru.mera.nngu.iot.util.GlobalConstants;

/**
 *
 * @author Маргарита
 */
public class DecoderXML implements Decoder{
    final static Logger log = LoggerFactory.getLogger(AppStarter.class);
    
    @Override
    public GatewayMeasurement decode(String encoded) {
        log.info("Will decode string " + encoded + " to xMeasurement");
        return new GatewayMeasurement() {
            @Override
            public String getGatewayId() {
                return "Gateway_XXX";
            }
    
            @Override
            public long getMeasurementTimestamp() {
                return 123456789;
            }
    
            @Override
            public Object getSensorValue(String sensor) {
                return 123;
            }
    
            @Override
            public Map<String, Object> getSensorsValues() {
                Map<String, Object> readings = new HashMap();
                readings.put(GlobalConstants.SENSOR_KEY_TEMPERATURE, 25);
                readings.put(GlobalConstants.SENSOR_KEY_HUMIDITY, 700);
                return readings;
            }
        };
    }
}
