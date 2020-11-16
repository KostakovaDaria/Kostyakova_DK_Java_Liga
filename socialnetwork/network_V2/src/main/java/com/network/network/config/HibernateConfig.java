package com.network.network.config;

import com.network.network.domain.Profile;
import com.network.network.domain.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.cfg.Configuration;

public class HibernateConfig {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {

            Configuration configuration = new Configuration();

            configuration.addAnnotatedClass(com.network.network.domain.User.class);
            configuration.addAnnotatedClass(com.network.network.domain.Profile.class);
            configuration.addAnnotatedClass(com.network.network.domain.Country.class);
            configuration.addAnnotatedClass(com.network.network.domain.Region.class);
            configuration.addAnnotatedClass(com.network.network.domain.City.class);
            configuration.addAnnotatedClass(com.network.network.domain.Gender.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }

        return sessionFactory;
    }
}
