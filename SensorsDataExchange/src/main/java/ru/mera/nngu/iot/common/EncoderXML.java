/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.mera.nngu.iot.common;

import java.util.concurrent.atomic.AtomicReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.mera.nngu.iot.gtw.AbstractGatewaySensorsMonitor;

/**
 *
 * @author Маргарита
 */
public class EncoderXML implements Encoder {
    static final Logger log = LoggerFactory.getLogger(AbstractGatewaySensorsMonitor.class);
    
    @Override
    public String encode(GatewayMeasurement measurement) {
        log.info("Will encode Measurement");
                AtomicReference<String> result = new AtomicReference("");
                measurement.getSensorsValues().forEach((k, v) -> {
                result.set(result.get() + "; " + k + "=" + v);
                });
                return result.get();
    };
}
