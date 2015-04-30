package com.baoxiu.web.config;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ConfigHandler extends DefaultHandler {

	private ConfigServer config;
	private String tagname;
	
	@Override
	public void endDocument() throws SAXException {
	}

	@Override
	public void endElement(String uri, String localName, String name)
			throws SAXException {
		tagname = null;
	}

	@Override
	public void startDocument() throws SAXException {
		config = new ConfigServer();
	}

	@Override
	public void startElement(String uri, String localName, String name,
			Attributes attributes) throws SAXException {
		tagname = localName;
	}

	@SuppressWarnings("static-access")
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		if("webhost".equals(tagname)){
			config.WEB_HOST = new String(ch, start, length);
		}
//		if ("sdcardtemp".equals(tagname)) {
//			config.sdcardtemp = new String(ch, start, length);
//		}
//		if ("sdcardtemppic".equals(tagname)) {
//			config.sdcardtemppic = new String(ch, start, length);
//		}
//		if ("sdcardtempapk".equals(tagname)) {
//			config.sdcardtempapk = new String(ch, start, length);
//		}
//		if ("sdcardtemptmp".equals(tagname)) {
//			config.sdcardtemptmp = new String(ch, start, length);
//		}
		
	}

	public ConfigServer getConfig() {
		return config;
	}
	
}
