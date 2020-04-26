package com.hendisantika.springdocopenapisample.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by IntelliJ IDEA.
 * Project : springdoc-openapi-sample
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 26/04/20
 * Time: 15.48
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Problem {
    private String logRef;
    private String message;
}
