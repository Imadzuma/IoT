/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.mera.nngu.iot.db;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.mera.nngu.iot.AppStarter;
import ru.mera.nngu.iot.common.GatewayMeasurement;
import ru.mera.nngu.iot.util.GlobalConstants;

/**
 *
 * @author Маргарита
 */
public class DBManagerImpl implements DBManager {
    final static Logger log = LoggerFactory.getLogger(AppStarter.class);
    
    @Override
    public void batchSaveToDB(List<GatewayMeasurement> events) {
        log.info("Saving events to DB..");
            events.forEach(e -> {
            log.info("Event info: \n" + "Temperature: " 
                + e.getSensorValue(GlobalConstants.SENSOR_KEY_TEMPERATURE)
                + "Humidity: " + e.getSensorValue(GlobalConstants.SENSOR_KEY_HUMIDITY));
        });
    }
    
    @Override
    public void singleSaveToDB(GatewayMeasurement event) {}
}
