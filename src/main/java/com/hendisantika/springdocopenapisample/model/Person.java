package com.hendisantika.springdocopenapisample.model;

/**
 * Created by IntelliJ IDEA.
 * Project : springdoc-openapi-sample
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 26/04/20
 * Time: 13.27
 */

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.CreditCardNumber;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "person")
@XmlAccessorType(XmlAccessType.FIELD)
public class Person {
    private long id;

    private String firstName;

    @NotNull
    @NotBlank
    private String lastName;

    @Pattern(regexp = ".+@.+\\..+", message = "Please provide a valid email address")
    private String email;

    @Email()
    private String email1;

    @Min(18)
    @Max(30)
    private int age;

    @CreditCardNumber
    private String creditCardNumber;

}
