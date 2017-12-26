package com.cdsxt.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.lang.ThreadLocal;
import java.util.Objects;

public class HibUtils {

    private static final SessionFactory FACTORY;

    private static ThreadLocal<Session> cache = new ThreadLocal<>();

    static {
        //获取配置信息
        Configuration configuration = new Configuration().configure();
        //创建一个ServiceRegistry的实例
        //首先回去其标准的建造器  用建造者模式进行构建对象
        //创建一个标准的建造器
        StandardServiceRegistryBuilder ssb = new StandardServiceRegistryBuilder();
        //得到ServiceRegistry的实例   一定要传如configuration.getProperties()
        ServiceRegistry registry = ssb.applySettings(configuration.getProperties()).build();
        //得到 session工厂   一个数据库对应一个SessionFactory
        FACTORY = configuration.buildSessionFactory(registry);
    }

    /**
     * 构造SessionFactory
     *
     * @return
     */

    // 从线程中获取 session
    public static Session getSession() {
        // 先判断当前线程中是否存在 Session
        Session session = cache.get();
        // 如果不存在或已经关闭, 则创建 Session
        if (Objects.isNull(session) || !session.isOpen()) {
            cache.set(FACTORY.openSession());
        }
        // 重新获取 Session 并返回
        return cache.get();
    }

    // 关闭 Session
    public static void closeSession() {
        Session session = cache.get();
        if (Objects.nonNull(session) && session.isOpen()) {
            session.close();
        }
    }

}
