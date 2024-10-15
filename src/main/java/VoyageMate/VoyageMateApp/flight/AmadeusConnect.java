package VoyageMate.VoyageMateApp.flight;

import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.referenceData.Locations;

import javax.xml.stream.Location;

public enum AmadeusConnect {
    INSTANCE;
    private final Amadeus amadeus;

    AmadeusConnect() {
        this.amadeus = Amadeus
                .builder("YOUR_API_KEY", "YOUR_API_SECRET")
                .build();
    }

    public Location[] location(String keyword) throws ResponseException {
        return (Location[]) amadeus.referenceData.locations.get(Params
                .with("keyword", keyword)
                .and("subType", Locations.AIRPORT));
    }
}
