package pl.sda.main;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import pl.sda.entity.Person;

public class PersonMain {

    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure()
                .buildSessionFactory();

        Session session = sessionFactory.openSession();

        //transakcja jest wymagana do operacji CREATE, UPDATE, DELETE
        //rozpoczynamy transakcję
        Transaction transaction = session.beginTransaction();

        session.save(new Person(null, "Michał", "Nowak", 34));

        //stan managed
        //w przyadku braku wiersza o danym id metoda get zwraca null
        Person personFromDB = session.get(Person.class, 100L); //select * from persons where id = 1

        //w przyadku braku wiersza o danym id metoda load rzuca wyjątek ObjectNotFoundException
        //Person personFromDB = session.load(Person.class, 100L); //select * from persons where id = 1

        if (personFromDB != null) {

            personFromDB.setAge(70);

            System.out.println(personFromDB);

            //stan detached
            //brak propagacji na stan bazy danych
            session.detach(personFromDB);
        } else {
            System.out.println("Nie istnieje wiersz w tabeli persons o id = 100");
        }

        transaction.commit();

        session.close();
        sessionFactory.close();
    }

}
