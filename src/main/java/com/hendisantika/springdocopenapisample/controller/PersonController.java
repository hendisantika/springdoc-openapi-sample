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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Random;

@RestController
public class PersonController {
    private Random ran = new Random();

    @RequestMapping(path = "/person", method = RequestMethod.POST)
    public Person person(@Valid @RequestBody Person person) {
        int nxt = ran.nextInt(10);
        if (nxt >= 5) {
            throw new RuntimeException("Breaking Logic!");
        }
        return person;
    }


}
