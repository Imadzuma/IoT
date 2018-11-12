/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.mera.nngu.iot.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.mera.nngu.iot.gtw.AbstractGatewaySensorsMonitor;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.OutputKeys;
import java.io.StringWriter;
import java.util.Map;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
/**
 *
 * @author Маргарита
 */
public class EncoderXML implements Encoder {
    static final Logger log = LoggerFactory.getLogger(AbstractGatewaySensorsMonitor.class);
    
    @Override
    public String encode(GatewayMeasurement measurement) {
        log.info("Will encode Measurement");
        String  xml = "";
        try {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        Document doc = factory.newDocumentBuilder().newDocument();
        
        Element root = doc.createElement("events");
        doc.appendChild(root);
   
        Element event = doc.createElement("event");
            for(Map.Entry<String, Object> item : measurement.getSensorsValues().entrySet()) {
                Element pair = doc.createElement("Item");
                pair.setAttribute("key", item.getKey());
                pair.setAttribute("value", item.getValue().toString());
                event.appendChild(pair);
            }
            root.appendChild(event);
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        StringWriter writer = new StringWriter();
        transformer.transform(new DOMSource(doc), new StreamResult(writer));
        xml = writer.getBuffer().toString();
        } catch (ParserConfigurationException | TransformerException ex) {
            ex.printStackTrace(System.out);
        }
        return xml;
    };
}
