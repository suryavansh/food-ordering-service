package com.bits.orderingservice.application.web;

import com.bits.orderingservice.application.request.CreateOrderRequest;
import com.bits.orderingservice.application.response.OrderResponse;
import com.bits.orderingservice.domain.service.IOrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@RestController
@Log4j2

public class OrderResource {
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${authTokenURL}")
	private String authTokenURL;

	@Value("${authURL}")
	private String authURL;

	@Value("${tokenUserName}")
	private String getTokenUserName;

	@Value("${tokenpassword}")
	private String getTokenPassword;
	
	@Autowired
    private  IOrderService orderService;

    @PostMapping(value = "/orders/create",consumes = "application/json", produces = "application/json")
    public ResponseEntity<? extends Object> createOrder(@RequestBody CreateOrderRequest request,@RequestHeader(name="user")String userName) {
        OrderResponse response =null;
        String err=null;
      
        try {
        	if (!isAuthorized(authURL, userName, "Order Food")) {
    			throw new Exception("Not Authorised");
    		}
    		response= orderService.createOrder(request);
        } catch (Exception e) {
            err=e.getMessage();
          //  log.error(e.getMessage());
        }
        if (err==null){
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }
        return ResponseEntity.ok(err);
    }
	/**
	 * 
	 * @param url
	 * @param userName
	 * @param password
	 * @return
	 */
	public String getToken(String url, String userName, String password) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		Map<String, Object> jsonDataMap = new HashMap<>();
		jsonDataMap.put("username", userName);
		jsonDataMap.put("password", password);

		// Convert map to JSON string
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonData = null;
		try {
			jsonData = objectMapper.writeValueAsString(jsonDataMap);
		} catch (JsonProcessingException e) {
			//log.error("Error in authorize call" + e.getMessage());
			return null;
		}

		HttpEntity<Object> requestEntity = new HttpEntity<>(jsonData, headers);
		ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, requestEntity, String.class);
		// You can handle the response here if needed
		String jsonString = responseEntity.getBody();
		//log.info("Response: " + jsonString);
		try {
			// Parse the JSON string
			JsonNode jsonNode = objectMapper.readTree(jsonString);

			// Get the value of the "token" field
			String token = jsonNode.get("token").asText();
			return token;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * @param url
	 * @param userName
	 * @param operation
	 * @return
	 */
	public boolean isAuthorized(String url, String userName,String operation) {
		String token = getToken(authTokenURL, getTokenUserName, getTokenPassword);
		if(token == null) {
			//log.error("Error in gettting token");
			return false;
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Authorization", "Token " + token);
		Map<String, Object> jsonDataMap = new HashMap<>();
		jsonDataMap.put("username", userName);
		jsonDataMap.put("action", operation);

		// Convert map to JSON string
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonData = null;
		try {
			jsonData = objectMapper.writeValueAsString(jsonDataMap);
		} catch (JsonProcessingException e) {
			//log.error("Error in authorize call" + e.getMessage());
			return false;
		}

		HttpEntity<Object> requestEntity = new HttpEntity<>(jsonData, headers);
		ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, requestEntity, String.class);
		// You can handle the response here if needed
		String jsonString = responseEntity.getBody();
		//log.info("Response: " + jsonString);
		try {
			// Parse the JSON string
			JsonNode jsonNode = objectMapper.readTree(jsonString);

			// Get the value of the "token" field
			String isPermitted = jsonNode.get("message").asText();
			if(isPermitted != null && isPermitted.equals("Permitted")) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
}