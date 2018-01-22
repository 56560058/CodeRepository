package com.eric.mybatis;

import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * 结果处理类
 * 把结果转为map
 */
public class MapResultHandler  implements ResultHandler {

    //结果集合
    private final Map mappedResults = new HashMap();

    /**
     * 处理方法
     * @param context
     */
    public void handleResult(ResultContext context) {
        @SuppressWarnings("rawtypes")
        Map map = (Map)context.getResultObject();
        mappedResults.put(map.get("key"), map.get("value"));  // xml 配置里面的property的值，对应的列
    }

    public Map getMappedResults() {
        return mappedResults;
    }

}
