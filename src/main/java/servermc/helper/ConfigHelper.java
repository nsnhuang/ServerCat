package servermc.helper;

import servermc.ConfigConstant;
import servermc.util.PropsUtil;

import java.util.Properties;
import java.util.Set;

/**
 * 描述:
 *
 * @author huang
 * @create 2019-10-03 1:41 PM
 */
public class ConfigHelper {


    private static final Properties CONFIG_PROPS = PropsUtil.loadProps(ConfigConstant.CONFIG_FILE);

    public static String getAppBasePackage() {
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.APP_ASSET_PATH);
    }
}