package pl.sda.tableperclass;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class TablePerClassMain {

    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure()
                .buildSessionFactory();

        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        Employee officeEmployee =
                new OfficeEmployee(null, "Michał", "Nowak", "programowanie");

        Employee director = new Director(null, "Jan", "Kowalski", "finanse");

        session.save(officeEmployee);
        session.save(director);

        transaction.commit();
        session.close();
        sessionFactory.close();

    }

}
