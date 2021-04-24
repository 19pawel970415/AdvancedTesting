package pl.sda.embedded;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {

    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure()
                .buildSessionFactory();

        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        Mammal mammal = new Mammal(1, new Animal("Słoń", "kręgowiec"));

        session.save(mammal);


        transaction.commit();
        session.close();
        sessionFactory.close();


    }
}
