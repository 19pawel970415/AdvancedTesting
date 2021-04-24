package pl.sda.book;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import pl.sda.embedded.Animal;
import pl.sda.embedded.Mammal;

public class BookMain {

    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure()
                .buildSessionFactory();

        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

       BookType bookType = new BookType(1, "powieść");

       Book book = new Book(1, "Ogniem i Mieczem");

       session.save(bookType);
       session.save(book);

       transaction.commit();
       session.close();

       //***************************************
       //do odczytywania zapisanych obiektów używamy osobnej sesji i transakcji
        session = sessionFactory.openSession();
       transaction = session.beginTransaction();

        Book bookFromDB = session.get(Book.class, 1);
        System.out.println(bookFromDB);

        transaction.commit();
        session.close();
        sessionFactory.close();

    }
}
