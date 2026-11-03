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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.hendisantika.springdocopenapisample.model.PersonRequest.PersonRequestExample;

@RestController
public class PersonController {
    private Random ran = new Random();

    @Operation(summary = "Create a new person")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Person created successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Person.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid input provided") })
    @PostMapping(path = "/person")
    public Person person(@io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Person to create", required = true,
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Person.class),
                    examples = @ExampleObject(value = PersonRequestExample)))

            @Valid @RequestBody Person person) {
        int nxt = ran.nextInt(10);
        if (nxt >= 50) {
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
