package hello.controller;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import hello.value.weather.OpenWeather;
import hello.value.weatherResponse.WeatherResponse;
@RestController
public class WeatherController {
	// 1: estrarre la localita e apk in variabili
	// 2: ottenere la localita come parametro della request
	// 3: doppia interrogazione
	
	
	private static final Logger log= LoggerFactory.getLogger(WeatherController.class); 
	
	@GetMapping("/weather")
	 WeatherResponse getWeather(@RequestParam ("city1") String city1, @RequestParam("city2")String city2) {
		
		RestTemplate restTemplate = new RestTemplate();

		final String appId="c6af8931add934fd3a1e4ae81ad8f903";

		String url1= "https://api.openweathermap.org/data/2.5/weather?q="+ city1 + "&appId" + appId;
		String url2= "https://api.openweathermap.org/data/2.5/weather?q="+ city2 + "&appId" + appId;
		
		log.debug("Fetch url: "+ url1);
		log.debug("Fetch url: "+ url2);
		
		OpenWeather weather1= restTemplate.getForObject(url1, OpenWeather.class);
		OpenWeather weather2= restTemplate.getForObject(url2, OpenWeather.class);
		
		WeatherResponse response= new WeatherResponse();
		
	response.setFirstcity(weather1.getName());
	response.setSecondcity(weather2.getName());
	response.setFisttemp(weather1.getMain().getTemp()- 273.15);
	response.setSecondtemp(weather2.getMain().getTemp()- 273.15);
	
	return response;
	}

	

}



