package hello.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import hello.InvalidCountryCodeException;
import hello.model.Event;

public class RemoteEventsController {
	
	private static final Logger log= LoggerFactory.getLogger(RemoteEventsController.class);
	
	private Map<String, String> urls = new HashMap<>();
	
	@GetMapping("/remote/events/{country}")
	public List<Event> fetchCountryEvents(@PathVariable String country){
		RestTemplate restTemplate = new RestTemplate();
		
		initUrls();
		
		String url=urls.get(country);
		log.info("Using url " + url);
		
		
		if(url!=null) {
			
			ResponseEntity<List<Event>> response=restTemplate.exchange(url, HttpMethod.GET, 
					null, new ParameterizedTypeReference<List<Event>>() {});
			
			List<Event> events=response.getBody();
			return events;
			
			
		}else {
			throw new InvalidCountryCodeException();
		}
	}
	
	private void initUrls() {
		urls.put("us","https://alfo.cloud-lab.it/hello/events");
		urls.put("it","https://danilo.cloud-lab.it/hello/events");
		urls.put("fr","https://pasquale.cloud-lab.it/hello/events");
		urls.put("nl","https://franacesco.cloud-lab.it/hello/events");
		urls.put("nz","https://stefano.cloud-lab.it/hello/events");
		urls.put("ma","https://souad.cloud-lab.it/hello/events");
		urls.put("pe","https://ana.cloud-lab.it/hello/events");
		urls.put("ph","https://rose.cloud-lab.it/hello/events");
		urls.put("no","https://luca.cloud-lab.it/hello/events");
	}

}
