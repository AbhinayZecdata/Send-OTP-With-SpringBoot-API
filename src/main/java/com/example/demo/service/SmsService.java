package com.example.demo.service;

import java.text.ParseException;

import org.apache.catalina.Store;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import com.example.demo.dto.SmsPojo;
import com.example.demo.dto.StoreOTP;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Component
public class SmsService {
	
	private final String ACCOUNT_SID="AC623536e2b4daa2fbbf8d5cc5c0e870a0";
	
	private final String AUTH_TOKEN="f38148123e103c0f318f3bf3448697dc";
	
	private final String FROM_NUMBER="+12179163764";
	
	public void send(SmsPojo sms) throws ParseException{
		
		//The method initializes the Twilio API using the account SID and authentication token
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		
		
		//It generates a random OTP (One-Time Password) between 100,000 and 999,999
		int min=100000;
		int max=999999;
		int number=(int)(Math.random()*(max-min+1)+min);
		
		String msg="Do not share- OTP "+number+ " -for transfer of Rs. 1500.0 to Abhinay Soni Axis -xxxx3959.Valid for 5 mins.Axis Bank employees never ask for OTP- AxisBank";
		
		Message message = Message.creator(new PhoneNumber(sms.getPhoneNo()),new PhoneNumber(FROM_NUMBER),msg)
            .create();
		
		
		//OTP is stored using the StoreOTP.setOtp(number) method.
		StoreOTP.setOtp(number);
	}
	public void receive(MultiValueMap<String,String> smscallback) {
		
	}
}
