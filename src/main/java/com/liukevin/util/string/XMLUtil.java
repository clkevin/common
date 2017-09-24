package com.liukevin.util.string;

import java.io.IOException;
import java.util.*;

import org.jdom.Element;
import org.jdom.JDOMException;

/**
 * xml工具类
 * @author miklchen
 *
 */
public class XMLUtil {

	/**
	 * 解析xml,返回第一级元素键值对。如果第一级元素有子节点，则此节点的值是子节点的xml数据。
	 * @param strxml
	 * @return
	 * @throws JDOMException
	 * @throws IOException
	 * @因中文问题使用xmlConverUtil类解析
	 */
	public static Map<String,String> doXMLParse(String strxml) throws JDOMException, IOException {
		if(null == strxml || "".equals(strxml)) {
			return null;
		}


		return XmlConverUtil.xmltoMap(strxml);
	}

	/**
	 * 获取子结点的xml
	 * @param children
	 * @return String
	 */
	public static String getChildrenText(List<?> children) {
		StringBuffer sb = new StringBuffer();
		if(!children.isEmpty()) {
			Iterator<?> it = children.iterator();
			while(it.hasNext()) {
				Element e = (Element) it.next();
				String name = e.getName();
				String value = e.getTextNormalize();
				List<?> list = e.getChildren();
				sb.append("<" + name + ">");
				if(!list.isEmpty()) {
					sb.append(XMLUtil.getChildrenText(list));
				}
				sb.append(value);
				sb.append("</" + name + ">");
			}
		}
		return sb.toString();
	}

	/**
	 * 将map转换为SortedMap，并对值进行去空处理
	 * @author 李锋镝
	 * @date 2016-12-1 下午5:32:30
	 * @param map
	 * @return
	 */
	public static SortedMap<?, ?> getSortedMapFromMap(Map<?, ?> map) {
		SortedMap<Object,Object> param = new TreeMap<Object,Object>();
		Set<?> set = map.entrySet();
		Iterator<?> iterator = set.iterator();
		while (iterator.hasNext()) {
			Map.Entry<?, ?> entry1 = (Map.Entry<?, ?>) iterator.next();
			String key = (String) entry1.getKey();
			String value =  entry1.getValue().toString();
			if(value != null && !value.equals("")){
				param.put(key,value);
			}
		}
		return param;
	}

	/**
	 * 微信支付获取发送的XML报文
	 * @author 李锋镝
	 * @date 2016-12-1 下午4:39:49
	 * @param map
	 * @return XML
	 */
	public static String getXmlFromMap(Map<?, ?> map) {
		SortedMap<Object,Object> param = new TreeMap<Object,Object>();
		Set<?> set = map.entrySet();
		Iterator<?> iterator = set.iterator();
		while (iterator.hasNext()) {
			Map.Entry<?, ?> entry1 = (Map.Entry<?, ?>) iterator.next();
			String key = (String) entry1.getKey();
			String value =  entry1.getValue().toString();
			if(value != null && !value.equals("")){
				param.put(key,value);
			}
		}
		StringBuffer sb = new StringBuffer();
		sb.append("<xml>");
		Set<Map.Entry<Object, Object>> set2 = param.entrySet();
		Iterator<Map.Entry<Object, Object>> it2 = set2.iterator();
		while (it2.hasNext()) {
			Map.Entry<Object, Object> entry = (Map.Entry<Object, Object>) it2.next();
			String key = (String) entry.getKey();
			Object value =  entry.getValue();
			if ("attach".equalsIgnoreCase(key) || "body".equalsIgnoreCase(key)
					|| "sign".equalsIgnoreCase(key)) {
				sb.append("<" + key + ">" + "<![CDATA[" + value + "]]></" + key + ">");
			} else {
				sb.append("<" + key + ">" + value + "</" + key + ">");
			}

		}
		sb.append("</xml>");
		return sb.toString();
	}
}
