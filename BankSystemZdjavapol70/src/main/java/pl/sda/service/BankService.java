package pl.sda.service;

import lombok.AllArgsConstructor;
import lombok.Setter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import pl.sda.entity.Account;
import pl.sda.entity.Client;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class BankService {

    private SessionFactory sessionFactory;

    public List<Account> getAccountsByClientPesel(String pesel) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "FROM Client WHERE pesel = :param";
        Query query = session.createQuery(hql);
        query.setParameter("param", pesel);

        Client client = (Client) query.getSingleResult();

        List<Account> result = new ArrayList<>();

        //deep copy głęboka kopia
        for (Account a : client.getAccounts()) {
            Account newAccount = new Account(a.getId(), a.getNumber(), a.getAmount(), null);
            result.add(newAccount);
        }

        transaction.commit();
        session.close();

        return result;
    }
}
