/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.mera.nngu.iot.common;

/**
 *
 * @author macbookair
 */
public interface Decoder 
{
    public GatewayMeasurement decode(String encoded);
}
