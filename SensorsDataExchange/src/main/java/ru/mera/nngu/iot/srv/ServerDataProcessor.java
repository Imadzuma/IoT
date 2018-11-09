/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.mera.nngu.iot.srv;

import ru.mera.nngu.iot.common.Decoder;

/**
 *
 * @author macbookair
 */
public interface ServerDataProcessor 
{
    public void start();
    
    public void stop();
    
    //TODO: remove after all stubs are resolved
    public void setDecoder(Decoder dec);
}
