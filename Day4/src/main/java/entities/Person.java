package entities;

import dto.PersonDTO;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


@Entity
@NamedQueries({
        @NamedQuery(name = "Person.deleteAllRows", query = "DELETE from Person p"),
        @NamedQuery(name = "Person.getByID", query = "SELECT p FROM Person p WHERE  p.id = :id"),
        @NamedQuery(name = "Person.getAll", query = "SELECT p FROM Person p"),
        @NamedQuery(name = "Person.deleteByID", query = "DELETE from Person p WHERE p.id = :id")
})
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private String phone;

    @Temporal(TemporalType.DATE)
    private Date created;

    @Temporal(TemporalType.DATE)
    private Date lastEdited;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Address address;

    
    public Person() {
    }

    public Person(String firstName, String lastName, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.created = new Date();
        this.lastEdited = new Date();
    }

    public Person(PersonDTO personDTO) {
        this.firstName = personDTO.getfName();
        this.lastName = personDTO.getlName();
        this.phone = personDTO.getPhone();
        this.created = new Date();
        this.lastEdited = new Date();

        this.address = new Address( personDTO.getStreet(),personDTO.getZip(),personDTO.getCity() );
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getLastEdited() {
        return lastEdited;
    }

    public void setLastEdited(Date lastEdited) {
        this.lastEdited = lastEdited;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        if(address != null) {
            this.address = address;
            address.setPerson(this);
        }
    }

}
