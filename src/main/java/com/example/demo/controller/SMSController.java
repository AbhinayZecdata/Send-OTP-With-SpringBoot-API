package com.example.demo.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.condition.ConsumesRequestCondition;

import com.example.demo.dto.SmsPojo;
import com.example.demo.service.SmsService;

import jakarta.annotation.PostConstruct;

@RestController
public class SMSController {
	
	@Autowired
	SmsService service;
	
	@Autowired
	private SimpMessagingTemplate webSocket;
	
	private final String TOPIC_DESTINATION = "/lesson/sms";
	
	@PostMapping("/mobileNo")
	public ResponseEntity<String> smsSubmit(@RequestBody SmsPojo sms){
		try {
			System.out.println("hello");
			service.send(sms);
			System.out.println("hello");
			
		} catch (Exception e) {
			return new ResponseEntity<String>("Something went wrong !",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		webSocket.convertAndSend(TOPIC_DESTINATION,getTimeStamp() + ": SMS has been Sent ! :" + sms.getPhoneNo());
		return new ResponseEntity<String>("OTP has been sent",HttpStatus.OK);
	}
	
	
	private String getTimeStamp() {
		return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
	}
	
	
}
