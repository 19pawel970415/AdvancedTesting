package pl.sda.singletable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class SingleTableMain {

    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure()
                .buildSessionFactory();

        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        EmployeeV2 officeEmployee = new OfficeEmployeeV2(null, "Jan", "Kowalski", "excel");

        EmployeeV2 director = new DirectorV2(null, "Anna", "Mucha", "kadry");

        session.save(officeEmployee);
        session.save(director);

        transaction.commit();
        session.close();
        sessionFactory.close();


    }
}
