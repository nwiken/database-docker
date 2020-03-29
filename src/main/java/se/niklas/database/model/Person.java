package se.niklas.database.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Person {

    public Person() {
        // Hibernate requires this
    }

    @Id
    private String id;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "surname")
    private String surname;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "Person{" +
            "id='" + id + '\'' +
            ", firstName='" + firstName + '\'' +
            ", surname='" + surname + '\'' +
            '}';
    }
}
