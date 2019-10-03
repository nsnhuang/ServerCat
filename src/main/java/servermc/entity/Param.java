package servermc.entity;

import org.apache.commons.collections4.MapUtils;

import java.util.Map;

/**
 * 描述:
 *
 * @author huang
 * @create 2019-10-03 3:52 PM
 */
public class Param {

    private Map<String, Object> paramMap;

    public Param() {
    }

    public Param(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }

    public Map<String, Object> getParamMap() {
        return paramMap;
    }

    public boolean isEmpty() {
        return MapUtils.isEmpty(paramMap);
    }
}