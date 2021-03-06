package com.example;

import com.example.models.Brand;
import com.example.models.Group;
import com.example.models.Product;
import com.example.models.User;
import com.example.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.HashSet;
import java.util.Set;

public class ManyToManyExample {

    public static void main(String[] args) {
        Transaction transaction = null;

        try(Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Group groupAdmin = new Group("Administrator Group");
            Group groupGuest = new Group("Guest Group");

            User user1 = new User("Tom", "tomcat", "tom@gmail.com");
            User user2 = new User("Mary", "mary", "mary@gmail.com");

            groupAdmin.addUser(user1);
            groupAdmin.addUser(user2);

            groupGuest.addUser(user1);

            user1.addGroup(groupAdmin);
            user2.addGroup(groupAdmin);
            user1.addGroup(groupGuest);

            session.save(groupAdmin);
            session.save(groupGuest);

            transaction.commit();

        } catch (Exception ex) {
            if(transaction != null)
                transaction.rollback();
            System.out.println(ex.getMessage());
        }

    }

}
