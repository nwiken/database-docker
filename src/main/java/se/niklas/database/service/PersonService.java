package se.niklas.database.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.niklas.database.exception.ResourceNotFoundException;
import se.niklas.database.model.Person;
import se.niklas.database.model.PersonDto;
import se.niklas.database.repository.PersonRepository;

import java.util.List;
import java.util.UUID;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> findAllPersons() {
        return personRepository.findAll()   ;
    }

    public Person findPerson(String id) {
        return personRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Could not find person with id "+ id));
    }

    public String savePerson(PersonDto person) {
        Person personEntity = new Person();
        personEntity.setId(UUID.randomUUID().toString());
        personEntity.setFirstName(person.getFirstName());
        personEntity.setSurname(person.getSurname());
        return personRepository.save(personEntity).getId();
    }
}
