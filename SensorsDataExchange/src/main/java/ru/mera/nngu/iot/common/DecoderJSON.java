/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.mera.nngu.iot.common;

import com.alibaba.fastjson.JSON;
import java.io.IOException;
import java.io.StringReader;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import ru.mera.nngu.iot.AppStarter;
import ru.mera.nngu.iot.util.GlobalConstants;

/**
 *
 * @author user
 */
public class DecoderJSON implements Decoder {
    final static Logger log = LoggerFactory.getLogger(AppStarter.class);
    
    @Override
    public GatewayMeasurement decode(String encoded) {
            Map<String, Object> arr = new HashMap<String, Object>();
            arr = JSON.parseObject(encoded, arr.getClass());
        return new GatewayMeasurementImpl(arr);
    }
}

    
