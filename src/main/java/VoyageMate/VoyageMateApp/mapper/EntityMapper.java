package VoyageMate.VoyageMateApp.mapper;

import VoyageMate.VoyageMateApp.flight.dto.GuidelinesDTO;
import VoyageMate.VoyageMateApp.flight.entity.Guidelines;
import VoyageMate.VoyageMateApp.user.User;
import VoyageMate.VoyageMateApp.user.UserDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EntityMapper {
    public UserDTO mapUserToUserDTO(User user) {
        return new UserDTO(user.getId(), user.getEmail());
    }

    public GuidelinesDTO mapGuideLinesToGuidelinesDTO(Guidelines guidelines) {
        return new GuidelinesDTO(guidelines.getId(), guidelines.getContinent(), guidelines.getCountry(),
                guidelines.getAirport(), guidelines.getAdults(), guidelines.getMaxPrice(), guidelines.getAverageTemperature(),
                guidelines.getWeatherPreferences(), guidelines.getDepartureDate(), guidelines.getReturnDate());
    }

    public List<GuidelinesDTO> mapGuideLinesListToGuidelinesListDTO(List<Guidelines> guidelines) {
        return guidelines.stream()
                .map(this::mapGuideLinesToGuidelinesDTO)
                .collect(Collectors.toList());
    }
}
