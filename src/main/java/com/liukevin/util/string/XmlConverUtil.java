package com.liukevin.util.string;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

/**
 * common xml conver utility 
 *
 * @author viruscodecn@gmail.com 
 * @version Framework 2010.10.26 
 * @url http://blog.csdn.net/arjick/article/details/6251777 
 */
public class XmlConverUtil {
    /**
     * map to xml xml <node><key label="key1">value1</key><key 
     * label="key2">value2</key>......</node> 
     *
     * @param map
     * @return
     */
    public static String maptoXml(Map<String,String> map) {
        Document document = DocumentHelper.createDocument();
        Element nodeElement = document.addElement("node");
        for (String str : map.keySet()) {
            Element keyElement = nodeElement.addElement("key");
            keyElement.addAttribute("label", str);
            keyElement.setText(str);
        }
        return doc2String(document);
    }

    /**
     * list to xml xml <nodes><node><key label="key1">value1</key><key 
     * label="key2">value2</key>......</node><node><key 
     * label="key1">value1</key><key 
     * label="key2">value2</key>......</node></nodes> 
     *
     * @param list
     * @return
     */
    public static String listtoXml(List<Map<String,String>> list) throws Exception {
        Document document = DocumentHelper.createDocument();
        Element nodesElement = document.addElement("nodes");
        int i = 0;
        for (Map<String,String> o : list) {
            Element nodeElement = nodesElement.addElement("node");
            if (o instanceof Map) {
                for (Object obj :  o.keySet()) {
                    Element keyElement = nodeElement.addElement("key");
                    keyElement.addAttribute("label", String.valueOf(obj));
                    keyElement.setText(o.get(obj));
                }
            } else {
                Element keyElement = nodeElement.addElement("key");
                keyElement.addAttribute("label", String.valueOf(i));
                keyElement.setText(String.valueOf(o));
            }
            i++;
        }
        return doc2String(document);
    }

    /**
     * json to xml {"node":{"key":{"@label":"key1","#text":"value1"}}} conver 
     * <o><node class="object"><key class="object" 
     * label="key1">value1</key></node></o> 
     *
     * @param json
     * @return
     */  
   /* public static String jsontoXml(String json) {  
        try {  
            XMLSerializer serializer = new XMLSerializer();  
            JSON jsonObject = JSONSerializer.toJSON(json);  
            return serializer.write(jsonObject);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return null;  
    }  */

    /**
     * xml to map xml <node><key label="key1">value1</key><key 
     * label="key2">value2</key>......</node> 
     *
     * @param xml
     * @return
     */
    public static Map<String,String> xmltoMap(String xml) {
        try {
            Map<String,String> map = new HashMap<String,String>();
            Document document = DocumentHelper.parseText(xml);
            Element nodeElement = document.getRootElement();
            List<?> node = nodeElement.elements();
            for (Iterator<?> it = node.iterator(); it.hasNext();) {
                Element elm = (Element) it.next();
                map.put(elm.getName(), elm.getText());
                elm = null;
            }
            node = null;
            nodeElement = null;
            document = null;
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * xml to list xml <nodes><node><key label="key1">value1</key><key 
     * label="key2">value2</key>......</node><node><key 
     * label="key1">value1</key><key 
     * label="key2">value2</key>......</node></nodes> 
     *
     * @param xml
     * @return
     */
    public static List<Map<String,String>> xmltoList(String xml) {
        try {
            List<Map<String,String>> list = new ArrayList<Map<String,String>>();
            Document document = DocumentHelper.parseText(xml);
            Element nodesElement = document.getRootElement();
            List<?> nodes = nodesElement.elements();
            for (Iterator<?> its = nodes.iterator(); its.hasNext();) {
                Element nodeElement = (Element) its.next();
                Map<String,String> map = xmltoMap(nodeElement.asXML());
                list.add(map);
                map = null;
            }
            nodes = null;
            nodesElement = null;
            document = null;
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * xml to json <node><key label="key1">value1</key></node> 转化为 
     * {"key":{"@label":"key1","#text":"value1"}}
     *
     * @param xml
     * @return
     */  
   /* public static String xmltoJson(String xml) {  
        XMLSerializer xmlSerializer = new XMLSerializer();  
        return xmlSerializer.read(xml).toString();  
    }  */

    /**
     *
     * @param document
     * @return
     */
    public static String doc2String(Document document) {
        String s = "";
        try {
            // 使用输出流来进行转化  
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            // 使用UTF-8编码  
            OutputFormat format = new OutputFormat("   ", true, "UTF-8");
            XMLWriter writer = new XMLWriter(out, format);
            writer.write(document);
            s = out.toString("UTF-8");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return s;
    }
}