/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.mera.nngu.iot.db;

import java.util.List;
import ru.mera.nngu.iot.common.GatewayMeasurement;

/**
 *
 * @author macbookair
 */
public interface DBManager 
{
    public void batchSaveToDB(List<GatewayMeasurement> events);
    
    public void singleSaveToDB(GatewayMeasurement event);
}
