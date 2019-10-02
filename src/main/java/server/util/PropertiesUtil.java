package server.util;

import java.io.*;
import java.util.Properties;

import lombok.extern.slf4j.Slf4j;
/**
 * 描述:
 * 处理配置文件的工具类
 *
 * @author huang
 * @create 2019-09-22 4:07 PM
 */
@Slf4j
public class PropertiesUtil {

    private static Properties properties = new Properties();

    static {
        load();
    }
    
    private static void load() {
        log.info("加载properties配置");
        InputStream in = null;
        try {

            in = PropertiesUtil.class.getClassLoader().getResourceAsStream("server.properties");
            properties.load(in);
        } catch (FileNotFoundException e) {
            log.error("文件未找到");
        } catch (IOException e) {
            log.error("IO Exception");
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    log.error("文件流关闭异常");
                }
            }
        }
        log.info("文件加载完成：{}", properties);
    }

    public static String getProperty(String key) {
        if (null == properties) {
            load();
        }
        return properties.getProperty(key);
    }
}