package se.niklas.database.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import se.niklas.database.model.Person;
import se.niklas.database.model.PersonDto;
import se.niklas.database.service.PersonService;

import java.util.List;


@RestController
public class PersonController implements PersonApi {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }


    @Override
    public List<Person> getPersons() {
        return personService.findAllPersons();
    }

    @Override
    public Person findPersonById(String id) {
        return personService.findPerson(id);
    }

    @Override
    public String savePerson(@RequestBody PersonDto personDto) {
        return personService.savePerson(personDto);
    }
}
