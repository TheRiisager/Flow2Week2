package entities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Tester {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();

        Person p1 = new Person("Børge", "Børgesen", "918471542");
        Person p2 = new Person("Lars", "Larsen", "25235235");

        Address a1 = new Address("Lars Tyndskids Markvej", 1234, "bølandet");
        Address a2 = new Address("Boomergade", 4321, "Oldsville");

        p1.setAddress(a1);
        p2.setAddress(a2);

        em.getTransaction().begin();
        em.persist(p1);
        em.persist(p2);
        em.getTransaction().commit();
    }
}
