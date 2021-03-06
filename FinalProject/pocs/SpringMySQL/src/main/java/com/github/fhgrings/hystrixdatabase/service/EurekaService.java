package com.github.fhgrings.hystrixdatabase.service;

import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

public class EurekaService {
	
	private static class ServiceHolder {
		static final EurekaService INSTANCE = new EurekaService();
	}
	
	private final RestTemplate REST_TEMPLATE;
	private final String URL;
	
	private EurekaService() {
		HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(
	            HttpClientBuilder.create().build());
		REST_TEMPLATE = new RestTemplate(clientHttpRequestFactory);
		URL = "http://localhost:8080/eureka/v2/apps/";
	}
	
	public static EurekaService getInstance() { return ServiceHolder.INSTANCE; }
	
	private HttpHeaders jsonHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    return headers;
	}
	
	public String get(String appID) {
	    HttpEntity<String> entity = new HttpEntity<>(jsonHeaders());
	    ResponseEntity<String> response =
	    		REST_TEMPLATE.exchange(URL + appID, HttpMethod.GET, entity, String.class);
		return response.getBody();
	}
	
	public int post(String appID) {
		HttpEntity<String> entity = new HttpEntity<String>(postJsonBody(appID), jsonHeaders());
		ResponseEntity<String> response =
		    		REST_TEMPLATE.exchange(URL + appID, HttpMethod.POST, entity, String.class);
		return response.getStatusCodeValue();
	}
	
	private String postJsonBody(String appID) {
		return "{\n" + 
				"    \"instance\": {\n" + 
				"        \"hostName\": \"WKS-SOF-L011\",\n" + 
				"        \"app\": \""+ appID + "\",\n" + 
				"        \"vipAddress\": \"com.automationrhapsody.eureka.app\",\n" + 
				"        \"secureVipAddress\": \"com.automationrhapsody.eureka.app\",\n" + 
				"        \"ipAddr\": \"10.0.0.20\",\n" + 
				"        \"status\": \"STARTING\",\n" + 
				"        \"port\": {\"$\": \"8080\", \"@enabled\": \"true\"},\n" + 
				"        \"securePort\": {\"$\": \"8443\", \"@enabled\": \"true\"},\n" + 
				"        \"healthCheckUrl\": \"<a class=\\\"vglnk\\\" href=\\\"http://WKS-SOF-L011:8080/healthcheck\\\" rel=\\\"nofollow\\\"><span>http</span><span>://</span><span>WKS</span><span>-</span><span>SOF</span><span>-</span><span>L011</span><span>:</span><span>8080</span><span>/</span><span>healthcheck</span></a>\",\n" + 
				"        \"statusPageUrl\": \"<a class=\\\"vglnk\\\" href=\\\"http://WKS-SOF-L011:8080/status\\\" rel=\\\"nofollow\\\"><span>http</span><span>://</span><span>WKS</span><span>-</span><span>SOF</span><span>-</span><span>L011</span><span>:</span><span>8080</span><span>/</span><span>status</span></a>\",\n" + 
				"        \"homePageUrl\": \"<a class=\\\"vglnk\\\" href=\\\"http://WKS-SOF-L011:8080\\\" rel=\\\"nofollow\\\"><span>http</span><span>://</span><span>WKS</span><span>-</span><span>SOF</span><span>-</span><span>L011</span><span>:</span><span>8080</span></a>\",\n" + 
				"        \"dataCenterInfo\": {\n" + 
				"            \"@class\": \"com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo\", \n" + 
				"            \"name\": \"MyOwn\"\n" + 
				"        }\n" + 
				"    }\n" + 
				"}";
	}
	
}
