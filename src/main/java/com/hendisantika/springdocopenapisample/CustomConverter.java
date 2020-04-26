package com.hendisantika.springdocopenapisample;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.core.jackson.ModelResolver;

/**
 * Created by IntelliJ IDEA.
 * Project : springdoc-openapi-sample
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 26/04/20
 * Time: 14.36
 */
class CustomConverter extends ModelResolver {
    public CustomConverter(ObjectMapper mapper) {
        super(mapper, new QualifiedTypeNameResolver());
    }

    public static void add(ObjectMapper ojectMapper) {
        ModelConverters.getInstance().addConverter(new CustomConverter(ojectMapper));
    }

}
