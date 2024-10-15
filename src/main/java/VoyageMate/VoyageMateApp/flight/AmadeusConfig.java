package VoyageMate.VoyageMateApp.flight;

import com.amadeus.Amadeus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmadeusConfig {

    @Value("${amadeus.apiKey}")
    private String apiKey;

    @Value("${amadeus.apiSecret}")
    private String apiSecret;

    @Bean
    public Amadeus amadeus() {
        return Amadeus.builder(apiKey, apiSecret).build();
    }
}
