/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.mera.nngu.iot.gtw;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import ru.mera.nngu.iot.common.GatewayMeasurement;
import ru.mera.nngu.iot.hal.HardwareManager;

/**
 *
 * @author Маргарита
 */
public class GatewaySensorsMonitorImpl extends AbstractGatewaySensorsMonitor {

    public GatewaySensorsMonitorImpl(BlockingQueue<String> queue, HardwareManager mgr) {
        super(queue, mgr);
    }
    
    @Override
    protected GatewayMeasurement createMeasurement(Map<String, Object> values) {
        log.info("Will create Measurement from sensor data");
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
                        return values.get(sensor);
                    }
    
                    @Override
                    public Map<String, Object> getSensorsValues() {
                        return values;
                    }
    };
                        }
}
