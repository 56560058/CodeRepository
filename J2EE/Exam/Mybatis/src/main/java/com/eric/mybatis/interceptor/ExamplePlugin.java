package com.eric.mybatis.interceptor;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.lang.reflect.Method;
import java.util.Properties;

/**
 * 定义插件
 * 拦截在 Executor 实例中所有的 “select” 方法调用， 这里的 Executor 是负责执行低层映射语句的内部对象
 */
@Intercepts({
        @Signature(
        type= Executor.class,
        method = "query",
        args={MappedStatement.class,Object.class,RowBounds.class,ResultHandler.class,
                CacheKey.class,BoundSql.class}),
        @Signature(
                type= Executor.class,
                method = "query",
                args={MappedStatement.class,Object.class,RowBounds.class,ResultHandler.class})
        })
public class ExamplePlugin implements Interceptor {
    /**
     * 拦截方法
     * @param invocation
     * @return
     * @throws Throwable
     */
    public Object intercept(Invocation invocation) throws Throwable {
        Object target = invocation.getTarget();
        Object result = null;
        if (target instanceof Executor) {
            long start = System.currentTimeMillis();
            Method method = invocation.getMethod();
            /**执行方法*/
            result = invocation.proceed();
            long end = System.currentTimeMillis();
            System.out.println("[TimerInterceptor] execute [" + method.getName() + "] cost [" + (end - start) + "] ms");
        }
        return result;
    }
    public Object plugin(Object target) {
        System.out.println("plugin2");
        return Plugin.wrap(target, this);
    }
    public void setProperties(Properties properties) {
    }
}