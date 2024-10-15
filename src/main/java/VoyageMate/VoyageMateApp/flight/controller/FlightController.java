package VoyageMate.VoyageMateApp.flight.controller;

import VoyageMate.VoyageMateApp.flight.entity.Guidelines;
import VoyageMate.VoyageMateApp.flight.service.FlightService;
import VoyageMate.VoyageMateApp.flight.service.GuidelinesService;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.FlightOfferSearch;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class FlightController {
    private final FlightService flightService;
    private final GuidelinesService guidelinesService;

    public FlightController(FlightService flightService, GuidelinesService guidelinesService) {
        this.flightService = flightService;
        this.guidelinesService = guidelinesService;
    }


    @GetMapping("/flights/{id}")
    public ResponseEntity<List<FlightOfferSearch>> displayFlightsList(@PathVariable Long id) throws ResponseException {
        List<FlightOfferSearch> flightOfferSearchList = flightService.searchFlights(id);
        return ResponseEntity.ok(flightOfferSearchList);
    }

    @PostMapping("/guidelines")
    public ResponseEntity<?> displayFlightsList(@RequestBody Guidelines guidelines) {
        Guidelines guidelines1 = guidelinesService.saveUserGuidelines(guidelines);
        return ResponseEntity.ok(guidelines1);
    }
}
