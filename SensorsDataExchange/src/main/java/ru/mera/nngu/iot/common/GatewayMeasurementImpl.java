/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.mera.nngu.iot.common;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import ru.mera.nngu.iot.util.GlobalConstants;

/**
 *
 * @author user
 */
public class GatewayMeasurementImpl implements GatewayMeasurement {
        Map<String, Object> values;

        GatewayMeasurementImpl(Map<String, Object> arr) {
            this.values = arr;
        }

        @Override
        public String getGatewayId() {
            return "Gateway_XXX";
        }

        @Override
        public long getMeasurementTimestamp() {
            return new Date().getTime();
        }

        @Override
        public Object getSensorValue(String sensor) {
            return values.get(sensor);
        }

        @Override
        public Map<String, Object> getSensorsValues() {
            return values;
        }
    }
