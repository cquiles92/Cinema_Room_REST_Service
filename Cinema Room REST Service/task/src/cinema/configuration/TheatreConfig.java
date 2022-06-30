package cinema.configuration;

import cinema.model.Theatre;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TheatreConfig {
    @Bean
    Theatre theatre(@Value("${theatre.total_rows}") int rows, @Value("${theatre.total_columns}") int columns) {
        return new Theatre(rows, columns);
    }
}
