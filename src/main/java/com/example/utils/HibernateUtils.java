package com.example.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtils {

    private static StandardServiceRegistry registry;
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {

        try {
            if(sessionFactory == null) {

                //Create registry
                registry =  new StandardServiceRegistryBuilder().configure().build();

                // Create Metadata
                MetadataSources sources = new MetadataSources(registry);
                Metadata metadata = sources.getMetadataBuilder().build();

                // Create SessionFactory
                sessionFactory = metadata.getSessionFactoryBuilder().build();

            }
        } catch (Exception ex) {
            if(registry != null) {
                StandardServiceRegistryBuilder.destroy(registry);
            }
            System.out.println(ex.getMessage());
        }
        return sessionFactory;
    }

    public static void shutDown() {
        if(registry != null) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

}
