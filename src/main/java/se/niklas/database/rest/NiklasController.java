package se.niklas.database.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import se.niklas.database.model.Person;
import se.niklas.database.service.PersonService;

import java.util.List;


@RestController
public class NiklasController {

    private final PersonService personService;

    @Autowired
    public NiklasController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(value = "/person", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Person> persons() {
        return personService.findPersons();
    }
}
