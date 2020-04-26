package com.hendisantika.springdocopenapisample;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.core.jackson.ModelResolver;
import io.swagger.v3.core.jackson.TypeNameResolver;
import org.apache.commons.lang3.StringUtils;

import java.util.Set;

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

    static class QualifiedTypeNameResolver extends TypeNameResolver {
        @Override
        protected String nameForClass(Class<?> cls, Set<Options> options) {
            String className = cls.getName().startsWith("java.") ? cls.getSimpleName() : cls.getName();
            if (options.contains(Options.SKIP_API_MODEL)) {
                return className;
            }
            final io.swagger.v3.oas.annotations.media.Schema model =
                    cls.getAnnotation(io.swagger.v3.oas.annotations.media.Schema.class);
            final String modelName = model == null ? null : StringUtils.trimToNull(model.name());
            return modelName == null ? className : modelName;
        }

    }


}
