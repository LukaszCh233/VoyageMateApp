package VoyageMate.VoyageMateApp.flight.dto;

public record GuidelinesDTO(Long id, String continent, String country, String airport, Long adults, Double maxPrice,
                            Double averageTemperature, String weatherPreferences, String departureDate,
                            String returnDate) {
}
