package com.neu.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * <p>Title: SpringContextUtil
 * <p>Description: 静态变量保存Spring ApplicationContext,SpringContextUtil.getBean("")取出
 * @author LXL
 * @date 2018/2/2 10:39
 */
public class SpringContextUtil implements ApplicationContextAware,
        DisposableBean {

    private static Log logger = LogFactory.getLog(SpringContextUtil.class);

    private  static ApplicationContext applicationContext = null;

    /**
     * <p>Title: setApplicationContext
     * <p>Description:实现ApplicationContextAware接口, 注入Context到静态变量中
     * @author LXL
     * @date 2018/2/2 10:39
     * @param applicationContext 上下文环境
     * @throws BeansException 加载异常
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        if (SpringContextUtil.applicationContext != null) {
            logger.error("SpringContextHolder中的ApplicationContext被覆盖, 原有ApplicationContext为:"
                    + SpringContextUtil.applicationContext);
        }
        SpringContextUtil.applicationContext = applicationContext;
    }

    /**
     * <p>Title: getApplicationContext
     * <p>Description:取得存储在静态变量中的ApplicationContext
     * @author LXL
     * @date 2018/2/2 10:39
     */
    public static ApplicationContext getApplicationContext() {
        assertContextInjected();
        return applicationContext;
    }

    /**
     * <p>Title: getBean
     * <p>Description:从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.
     * @author LXL
     * @date 2018/2/2 10:39
     * @param name bean名称
     * @param <T> 转换类型
     * @return 转换类型值
     */
    public static <T> T getBean(String name) {
        assertContextInjected();
        return (T) applicationContext.getBean(name);
    }

    /**
     * <p>Title: getBean
     * <p>Description:从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.
     * @author LXL
     * @date 2018/2/2 10:39
     * @param requiredType bean类型
     * @param <T> bean类型
     * @return 注入组件
     */
    public static <T> T getBean(Class<T> requiredType) {
        assertContextInjected();
        return applicationContext.getBean(requiredType);
    }

    /**
     * <p>Title: destroy
     * <p>Description:销毁 清除SpringContextHolder中的ApplicationContext为Null
     * @author LXL
     * @date 2018/2/2 10:39
     */
    @Override
    public void destroy() {
        clear();
    }

    /**
     * <p>Title: clear
     * <p>Description:清除SpringContextHolder中的ApplicationContext为Null
     * @author LXL
     * @date 2018/2/2 10:39
     */
    private static void clear() {
        applicationContext = null;
    }

    /**
     * <p>Title: assertContextInjected
     * <p>Description:检查ApplicationContext不为空
     * @author LXL
     * @date 2018/2/2 10:39
     */
    private static void assertContextInjected() {
        if (applicationContext == null) {
            throw new IllegalStateException(
                    "applicaitonContext未注入,请在applicationContext.xml中定义SpringContextHolder");
        }
    }



}