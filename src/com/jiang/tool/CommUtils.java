package com.jiang.tool;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 
 * 封装基础的工具方�?(如加载配置文�?,Json序列�?)
 */
public class CommUtils {
	//mysql8.0和之前版本的区别，首先驱动换了，不是com.mysql.jdbc.Driver而是'com.mysql.cj.jdbc.Driver',
	//此外mysql8.0是不�?要建立ssl连接的，�?要显示关闭�?�最后需要设置CST.
	private CommUtils() { }
    /**
     * 根据指定文件名加载配置文�?
     * @param fileNames
     * @return
     */
    public static Properties loadProperties(String fileNames) {
        Properties properties = new Properties();
        // 获取到当前配置文件夹下的文件输入�?
        // 获取输入流步�?: 获取反射对象--->获取类加载器--->获取类加载器下所有的同目录文�?
        InputStream inputStream = CommUtils.class.getClassLoader().getResourceAsStream(fileNames);
        // 加载配置文件中的�?有内�?
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
