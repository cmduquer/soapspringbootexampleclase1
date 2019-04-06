package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import io.spring.guides.gs_producing_web_service.GetCountryRequest;
import io.spring.guides.gs_producing_web_service.GetCountryResponse;
import io.spring.guides.gs_producing_web_service.GetCountryPopulationRequest;
import io.spring.guides.gs_producing_web_service.GetCountryPopulationResponse;
import io.spring.guides.gs_producing_web_service.NewCountryRequest;
import io.spring.guides.gs_producing_web_service.NewCountryResponse;


@Endpoint
public class CountryEndpoint {
	private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

	private CountryRepository countryRepository;

	@Autowired
	public CountryEndpoint(CountryRepository countryRepository) {
		this.countryRepository = countryRepository;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
	@ResponsePayload
	public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
		GetCountryResponse response = new GetCountryResponse();
		response.setCountry(countryRepository.findCountry(request.getName()));

		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryPopulationRequest")
	@ResponsePayload
	public GetCountryPopulationResponse getCountryPopulation(@RequestPayload GetCountryPopulationRequest request) {
		GetCountryPopulationResponse response = new GetCountryPopulationResponse();
		response.setPopulation(countryRepository.findCountry(request.getName()).getPopulation());

		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "newCountryRequest")
	@ResponsePayload
	public NewCountryResponse newCountry(@RequestPayload NewCountryRequest request) {
		NewCountryResponse response = new NewCountryResponse();
		response.setCountry(countryRepository.addCountry(request));

		return response;
	}
	
}
