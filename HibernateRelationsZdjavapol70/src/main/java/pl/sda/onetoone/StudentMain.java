package pl.sda.onetoone;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.time.LocalDate;

public class StudentMain {

    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure()
                .buildSessionFactory();

        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        StudentIndex studentIndex = new StudentIndex(null, "12345", null);
        Student student =
                new Student(null, "Jan", "Kowalski", LocalDate.now().minusYears(22), studentIndex);

        studentIndex.setStudent(student);

        session.save(studentIndex);
        session.save(student);

        Student studentFromDB = session.get(Student.class, 1L);
        System.out.println(studentFromDB.getLastName());

        System.out.println(studentFromDB.getStudentIndex().getNumber());

        System.out.println(studentFromDB.getStudentIndex().getStudent().getLastName());

        String hql = "FROM Student WHERE lastName = :param";
        Query query = session.createQuery(hql);
        query.setParameter("param", "Kowalski");
        query.getResultStream().forEach(s -> System.out.println(((Student) s).getFirstName()));


        transaction.commit();
        session.close();
        sessionFactory.close();


    }

}
