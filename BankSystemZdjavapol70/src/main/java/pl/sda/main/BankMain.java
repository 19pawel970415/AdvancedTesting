package pl.sda.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import pl.sda.entity.Account;
import pl.sda.entity.Client;
import pl.sda.service.BankService;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class BankMain {

    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure()
                .buildSessionFactory();

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String pesel = "12345678910";
        Account account = new Account(null, "12345", BigDecimal.valueOf(1000.0), null);
        Client client = new Client(null, "Jan", "Kowalski", "Warszawa",pesel, Arrays.asList(account));
        account.setClient(client);

        session.save(account);
        session.save(client);

        transaction.commit();
        session.close();

        //****************************************************

        BankService bankService = new BankService(sessionFactory);

        bankService.getAccountsByClientPesel(pesel)
                .stream()
                .forEach(a -> System.out.println(a.getAmount()));

        sessionFactory.close();
    }
}
