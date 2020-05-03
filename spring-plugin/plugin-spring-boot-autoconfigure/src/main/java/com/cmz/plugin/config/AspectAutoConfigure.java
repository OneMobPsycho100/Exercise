package com.cmz.plugin.config;

import com.cmz.plugin.constant.Constants;
import com.cmz.plugin.factory.SpringPluginFactory;
import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ConstPool;
import javassist.bytecode.MethodInfo;
import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.annotation.StringMemberValue;
import org.springframework.beans.BeansException;
import org.springframework.context.*;
import org.springframework.core.env.Environment;

/**
 * @Author: chenmingzhe
 * @Date: 2020/5/1 20:06
 */
public class AspectAutoConfigure implements EnvironmentAware, ApplicationContextAware, ApplicationContextInitializer {

    private Environment environment;
    private ApplicationContext applicationContext;

//    @Bean
//    public AspectJExpressionPointcutAdvisor pointcutAdvisor() {
//        AspectJExpressionPointcutAdvisor advisor = new AspectJExpressionPointcutAdvisor();
//       advisor.setAdvice((Advice) applicationContext.getBean(SpringPluginFactory.class));
//       advisor.setExpression(environment.getProperty(Constants.PLUGIN_POINTCUT));
//        return advisor;
//    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        this.aspectPointcutScan();
    }

    private void aspectPointcutScan() {
        try {
            ClassPool pool = ClassPool.getDefault();
            // 添加包的扫描路径
            ClassClassPath classPath = new ClassClassPath(SpringPluginFactory.class);
            pool.insertClassPath(classPath);
            //获取要修改的Class
            CtClass ct = pool.get("com.cmz.plugin.factory.impl.DefaultSpringPluginFactory");
            for (CtMethod cm : ct.getDeclaredMethods()) {
                //找到@pointcut 注解的方法
                if ("pointcut".equals(cm.getName())) {
                    MethodInfo methodInfo = cm.getMethodInfo();
                    ConstPool cPool = methodInfo.getConstPool();
                    AnnotationsAttribute attribute =
                            new AnnotationsAttribute(cPool, AnnotationsAttribute.visibleTag);
                    //获取@pointcut 注解，修改其value值
                    Annotation annotation =
                            new Annotation("org.aspectj.lang.annotation.Pointcut", cPool);
                    annotation.addMemberValue("value",
                            new StringMemberValue(environment.getProperty(Constants.PLUGIN_POINTCUT), cPool));
                    attribute.setAnnotation(annotation);
                    methodInfo.addAttribute(attribute);
                    //覆盖原有类
                    ct.toClass();
                    //使用类加载器重新加载Aop类
                    pool.getClassLoader()
                            .loadClass("com.cmz.plugin.factory.impl.DefaultSpringPluginFactory");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
