package pl.sda.manytomany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Arrays;

public class ParentMain {

    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure()
                .buildSessionFactory();

        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        Child child1 = new Child(null, "Jaś", "Nowak", null);
        Child child2 = new Child(null, "Małgosia", "Nowak", null);

        Parent parent1 = new Parent(null, "Michał", "Nowak", Arrays.asList(child1, child2));
        Parent parent2 = new Parent(null, "Anna", "Nowak", Arrays.asList(child1, child2));

        child1.setParents(Arrays.asList(parent1, parent2));
        child2.setParents(Arrays.asList(parent1, parent2));

        session.save(child1);
        session.save(child2);
        session.save(parent1);
        session.save(parent2);

        Parent parentFromDB = session.get(Parent.class, 1L);

        System.out.println(parentFromDB.getFirstName() + " " + parentFromDB.getLastName());

        for (Child c : parentFromDB.getChildren()) {
            System.out.println(c.getFirstName() + " " + c.getLastName());

            for (Parent p : c.getParents()) {
                System.out.println(p.getFirstName() + " " + p.getLastName());
            }
        }


        transaction.commit();
        session.close();
        sessionFactory.close();

    }
}
