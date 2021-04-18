package pl.sda.onetomany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

public class OrderMain {

    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure()
                .buildSessionFactory();

        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        Order order1 = new Order(null, BigDecimal.valueOf(10.25), "szkolenie", LocalDateTime.now(), null);
        Order order2 = new Order(null, BigDecimal.valueOf(1066.77), "porada", LocalDateTime.now(), null);

        Client client = new Client(null, "michal100", Arrays.asList(order1, order2));

        order1.setClient(client);
        order2.setClient(client);

        session.save(order1);
        session.save(order2);
        session.save(client);

        Client clientFromDB = session.get(Client.class, 1L);

        System.out.println(clientFromDB.getNick());

        clientFromDB.getOrders().get(0).setName("terapia");

        for (Order o : clientFromDB.getOrders()) {
            System.out.println(o.getName() + " " + o.getPrice().toString());
        }

        session.remove(clientFromDB);


        transaction.commit();
        session.close();
        sessionFactory.close();



    }

}
