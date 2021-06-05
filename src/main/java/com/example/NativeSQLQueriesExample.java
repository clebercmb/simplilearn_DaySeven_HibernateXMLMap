package com.example;

import com.example.models.Brand;
import com.example.models.Group;
import com.example.models.User;
import com.example.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.type.StringType;

import java.util.List;

public class NativeSQLQueriesExample {
    public static void main(String[] args) {

        Transaction transaction = null;

        try(Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            // To retrieve all collumns
            /*
            List<Object[]> brands = session.createNativeQuery("SELECT * FROM brands").list();
            brands.forEach(objs->{
                Integer id = (Integer) objs[0];
                String name = (String) objs[1];
                Brand brand = new Brand();
                brand.setId(id);
                brand.setName(name);
                System.out.println(brand);
            });
            */


            // To retrieve custom collumns
            /*
            System.out.println(">>>>>>");
            List<Object[]> brands2 = session.createNativeQuery("SELECT * FROM brands")
                    .addScalar("name", StringType.INSTANCE).list();
            brands2.forEach(objs->{
                String name = (String) objs[0];
                Brand brand = new Brand();
                brand.setName(name);
                System.out.println(brand);
            });
            */
            /*
            System.out.println(">>>>>>>>>>");
            List<Object[]> result = session.createNativeQuery("SELECT p.name as ProductName, p.price as ProductPrice, b.name as BrandName FROM products p inner join brands b on p.brand_id = b.id").list();
            result.forEach(objs->{
                String productName = (String) objs[0];
                float productPrice  = (Float) objs[1];
                String brandName = (String) objs[2];
                System.out.println("productName="+productName + " : productPrice=" + productPrice + " : productBrand=" + brandName);
            });
*/
            /* Mapping Native Query to Entity */
            System.out.println("#########");

            List<Brand> result = session.createNativeQuery("SELECT * FROM brands", Brand.class).list();
            for (Brand brand : result) {
                System.out.println(">"+ brand);
            }


            System.out.println("%%%%%%%%%%");
            System.out.println("%%%%%%%%%%");

            List<Brand> result4 = session.createNativeQuery("SELECT * FROM brands where  name like :brandName")
                    .addEntity(Brand.class).setParameter("brandName", "S%").list();
            for (Brand brand : result4) {
                System.out.println(brand);
            }


            transaction.commit();

        } catch (Exception ex) {
            if(transaction != null)
                transaction.rollback();
            System.out.println(ex.getMessage());
        }



    }
}
