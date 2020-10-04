package facades;

import dto.PersonDTO;
import dto.PersonsDTO;
import entities.Address;
import entities.Person;
import interfaces.IPersonFacade;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class PersonFacade implements IPersonFacade {

    private static PersonFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private PersonFacade() {}

    public static PersonFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new PersonFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }


    @Override
    public PersonDTO addPerson(PersonDTO personDTO) {
        EntityManager em = emf.createEntityManager();
        Person newPerson = new Person(personDTO);
        newPerson.getAddress().setPerson( newPerson );

        try {
            em.getTransaction().begin();
            em.persist(newPerson);
            em.getTransaction().commit();

            return new PersonDTO(newPerson);
        } finally {
            em.close();
        }

    }

    @Override
    public PersonDTO deletePerson(int id) {
        EntityManager em = emf.createEntityManager();

        try {
            Person person = em.find(Person.class,id);
            PersonDTO personDTO = new PersonDTO( person );
            Address address = em.find( Address.class,person.getAddress().getId() );

            em.getTransaction().begin();
            em.remove( person );
            em.remove( address );
            em.getTransaction().commit();


            return personDTO;

        } finally {
            em.close();
        }
    }

    @Override
    public PersonDTO getPerson(int id) {
        EntityManager em = emf.createEntityManager();

        try {
            TypedQuery<Person> query = em.createNamedQuery("Person.getByID", Person.class);
            return new PersonDTO( query.setParameter("id", id).getSingleResult() );
        } finally {
            em.close();
        }
    }

    @Override
    public PersonsDTO getAllPersons() {
        EntityManager em = emf.createEntityManager();

        try {
            TypedQuery<Person> query = em.createNamedQuery("Person.getAll", Person.class);
            return new PersonsDTO( query.getResultList() );
        } finally {
            em.close();
        }
    }

    @Override
    public PersonDTO editPerson(PersonDTO p) {
        EntityManager em = emf.createEntityManager();
        Person person = em.find( Person.class,p.getId() );
        if(person == null) {
            throw new IllegalArgumentException("Person with ID: " + p.getId() + " was not found");
        }

        person.setFirstName( p.getfName() );
        person.setLastName( p.getlName() );
        person.setPhone( p.getPhone() );
        person.setLastEdited( new Date() );

        person.getAddress().setStreet( p.getStreet() );
        person.getAddress().setCity( p.getCity() );
        person.getAddress().setZip( p.getZip() );

        try{
            em.getTransaction().begin();
            em.merge(person);
            em.getTransaction().commit();
        } finally {
            em.close();
        }

        return new PersonDTO( person );
    }

}
