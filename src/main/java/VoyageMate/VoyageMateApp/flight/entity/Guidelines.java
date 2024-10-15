package VoyageMate.VoyageMateApp.flight.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Guidelines {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String continent;
    private String country;
    private String airport;
    private String destination;
    private Long adults;
    private Double maxPrice;
    private Double averageTemperature;
    private String weatherPreferences;
    private String departureDate;
    private String returnDate;
}
