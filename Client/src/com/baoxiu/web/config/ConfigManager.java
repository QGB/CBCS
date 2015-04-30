package com.baoxiu.web.config;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;
import android.content.res.AssetManager;

public class ConfigManager {	
	private static ConfigManager instance;
	private ConfigServer config;
	
	/**
	 * 获取实例
	 * @return
	 */
	public synchronized static final ConfigManager getInstance() {
		return instance == null ? instance = new ConfigManager() : instance;
	}
	
	/**
	 * 加载配置文件
	 * @return
	 */
	public boolean loader(AssetManager assets){
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();
			ConfigHandler configHandler = null;
			parser.parse(assets.open("config.xml"), configHandler = new ConfigHandler(){});
			config = configHandler.getConfig();
			return true;
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			return false;
		} catch (SAXException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 在获取配置对象前，必须先执行loader方法
	 * @return
	 */
	public ConfigServer getConfig(){
		return config;
	}
	
}
