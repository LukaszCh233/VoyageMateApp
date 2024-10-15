package VoyageMate.VoyageMateApp.flight.service;

import VoyageMate.VoyageMateApp.flight.entity.Guidelines;
import VoyageMate.VoyageMateApp.flight.repository.GuidelinesRepository;
import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.FlightOfferSearch;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FlightService {
    private final Amadeus amadeus;
    private final GuidelinesRepository guidelinesRepository;

    public FlightService(Amadeus amadeus, GuidelinesRepository guidelinesRepository) {
        this.amadeus = amadeus;
        this.guidelinesRepository = guidelinesRepository;
    }

    public List<FlightOfferSearch> searchFlights(Long id) throws ResponseException {
        Guidelines guidelines = guidelinesRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Not found"));

        FlightOfferSearch[] flightOffers = amadeus.shopping.flightOffersSearch.get(Params
                .with("originLocationCode", guidelines.getAirport())
                .and("destinationLocationCode", guidelines.getDestination())
                .and("departureDate", guidelines.getDepartureDate())
                .and("adults", guidelines.getAdults()));
        List<FlightOfferSearch> affordableOffers = new ArrayList<>(); // Lista do przechowywania dostępnych ofert

        for (FlightOfferSearch offer : flightOffers) {
            FlightOfferSearch.SearchPrice price = offer.getPrice();
            double totalPrice = price.getTotal();
            if (totalPrice <= guidelines.getMaxPrice()) {
                affordableOffers.add(offer);
            }
        }
        return affordableOffers;
    }
}
