package pl.sda.joined;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class JoinedMain {

    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure()
                .buildSessionFactory();

        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        EmployeeV3 officeEmployee = new OfficeEmployeeV3(null, "Jan", "Kowalski", "word");

        EmployeeV3 director = new DirectorV3(null, "Karol", "Nowak", "bezpieczeństwo");

        session.save(officeEmployee);
        session.save(director);


        transaction.commit();
        session.close();
        sessionFactory.close();

    }
}
