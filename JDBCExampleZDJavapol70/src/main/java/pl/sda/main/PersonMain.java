package pl.sda.main;

import pl.sda.service.PersonService;

public class PersonMain {

    public static void main(String[] args) {

        PersonService personService = new PersonService();

        personService.getPersonBornedBefore1990()
                .stream()
                .forEach(System.out::println);

    }

}
