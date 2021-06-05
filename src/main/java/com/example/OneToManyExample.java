package com.example;

import com.example.models.Brand;
import com.example.models.Product;
import com.example.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.HashSet;
import java.util.Set;

public class OneToManyExample {

    public static void main(String[] args) {
        Transaction transaction = null;

        try(Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Brand b1 = new Brand();
            b1.setName("Samsung");

            Set<Product> products = new HashSet<>();

            products.add(new Product("Samsung Galaxy", b1, 1000));
            products.add(new Product("Samsung Pro", b1, 2000));
            products.add(new Product("Samsung Note", b1, 1500));

            b1.setProducts(products);

            session.save(b1);
//            session.save(products);

            transaction.commit();

        } catch (Exception ex) {
            if(transaction != null)
                transaction.rollback();
            System.out.println(ex.getMessage());
        }

    }
}
