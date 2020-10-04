package interfaces;

import dto.PersonDTO;
import dto.PersonsDTO;
import exceptions.PersonNotFoundException;

public interface IPersonFacade{
    public PersonDTO addPerson(PersonDTO personDTO);
    public PersonDTO deletePerson(int id) throws PersonNotFoundException;
    public PersonDTO getPerson(int id) throws PersonNotFoundException;
    public PersonsDTO getAllPersons();
    public PersonDTO editPerson(PersonDTO p) throws PersonNotFoundException;
}
