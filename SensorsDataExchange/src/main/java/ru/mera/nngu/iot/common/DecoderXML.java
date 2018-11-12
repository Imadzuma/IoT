/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.mera.nngu.iot.common;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
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
/**
 *
 * @author Маргарита
 */
public class DecoderXML implements Decoder{
    final static Logger log = LoggerFactory.getLogger(AppStarter.class);
    
    @Override
    public GatewayMeasurement decode(String encoded) {
        log.info("Will decode string " + encoded + " to xMeasurement");
        Map<String, Object> arr = new HashMap<String, Object>();

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(encoded));
            Document doc = builder.parse(is);
            Node root = doc.getDocumentElement();
            NodeList values = root.getChildNodes();
                for(int j=0; j<values.getLength(); j++){
                    arr.put(values.item(j).getAttributes().item(0).toString(),values.item(j).getAttributes().item(1).toString());
                }
        } catch (ParserConfigurationException ex) {
            java.util.logging.Logger.getLogger(DecoderJSON.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            java.util.logging.Logger.getLogger(DecoderJSON.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(DecoderJSON.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new GatewayMeasurementImpl(arr);
    }
}