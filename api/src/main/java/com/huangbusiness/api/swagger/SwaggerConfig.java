package com.huangbusiness.api.swagger;

import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.Parameter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.http.HttpHeaders;
import org.springframework.web.method.HandlerMethod;

@Configuration
public class SwaggerConfig {
    @Bean
    public OperationCustomizer customGlobalHeaders() {

        return (Operation operation, HandlerMethod handlerMethod) -> {

            Parameter missingParam1 = new Parameter()
                    .in(ParameterIn.HEADER.toString())
                    .schema(new StringSchema())
                    .name(HttpHeaders.AUTHORIZATION)
                    .description("Jwt Token")
                    .required(false);

            operation.addParametersItem(missingParam1);
            return operation;
        };
    }
}
