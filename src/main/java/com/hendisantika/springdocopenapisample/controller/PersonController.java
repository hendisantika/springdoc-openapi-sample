package com.hendisantika.springdocopenapisample.controller;

/**
 * Created by IntelliJ IDEA.
 * Project : springdoc-openapi-sample
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 26/04/20
 * Time: 13.29
 */

import com.hendisantika.springdocopenapisample.model.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
public class PersonController {
    private Random ran = new Random();

    @PostMapping(path = "/person")
    public Person person(@Valid @RequestBody Person person) {
        int nxt = ran.nextInt(10);
        if (nxt >= 5) {
            throw new RuntimeException("Breaking Logic!");
        }
        return person;
    }

    @GetMapping(path = "/personByLastName")
    public List<Person> findByLastName(@RequestParam(name = "lastName", required = true) @NotNull @NotBlank @Size(max = 10) String lastName) {
        List<Person> hardCoded = new ArrayList<>();
        Person person = Person.builder()
                .id(ran.nextInt(5))
                .firstName("Uchiha")
                .lastName("Sasuke")
                .email("uchiha_sasuke@konohagakure.co.jp")
                .email1("uchiha@konohagakure.co.jp")
                .age(32)
                .creditCardNumber("4111422231112111")
                .build();

        hardCoded.add(person);
        return hardCoded;
    }


}
