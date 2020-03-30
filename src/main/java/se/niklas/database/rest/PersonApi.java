package se.niklas.database.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import se.niklas.database.model.Person;
import se.niklas.database.model.PersonDto;

import java.util.List;

@Api(tags = "Person")
public interface PersonApi {

  @ApiOperation(value = "Get all persons",
      nickname = "getPersons")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "OK", response = List.class)})
  @GetMapping(value = "/person", produces = MediaType.APPLICATION_JSON_VALUE)
  List<Person> getPersons();

  @ApiOperation(value = "Get person by id",
      nickname = "findPersonById")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "OK", response = List.class),
      @ApiResponse(code = 404, message = "Not found", response = List.class)})
  @GetMapping(value = "/person/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  Person findPersonById(@PathVariable("id") String id);

  @ApiOperation(value = "Save person",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      nickname = "findPersonById")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "OK", response = List.class),
      @ApiResponse(code = 400, message = "Bad request")})
  @PostMapping(value = "/person", produces = MediaType.TEXT_PLAIN_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  String savePerson(@RequestBody PersonDto person);
}
