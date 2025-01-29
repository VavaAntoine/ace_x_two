package com.accepted.acextwo;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Ace X Two: Match and Odds Management API",
                version = "v1.0",
                description = """
                        This API provides CRUD operations for managing matches and their associated odds.
                        
                        **Key Features**:
                        - **Match Management**: Create, retrieve, update, and delete matches.
                        - **Odds Management**: Manage odds for matches, ensuring unique specifiers and valid associations.
                        - **Validation**: Enforces constraints like unique matches, valid sports, and proper odds specifiers.

                        **Constraints**:
                        - Matches must adhere to unique identifiers based on sport, date, and teams.
                        - Odds must include one of each specifier: `1`, `X`, and `2`.
                        - Only valid sports (`1` for Football, `2` for Basketball) are supported.
                        """,
                contact = @Contact(
                        email = "antoinevava@outlook.com"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "Ace X Two App Documentation",
                url = "https://github.com/VavaAntoine/nearest-Points-of-Interest"
        )
)
public class AcextwoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AcextwoApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
