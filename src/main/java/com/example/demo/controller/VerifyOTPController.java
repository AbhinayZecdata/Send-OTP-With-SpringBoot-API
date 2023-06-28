package com.example.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.StoreOTP;
import com.example.demo.dto.TempOTP;

@RestController
public class VerifyOTPController {
	
	@PostMapping("/otp")
	public String verifyOTP(@RequestBody TempOTP sms) {
		
		if(sms.getOtp()==StoreOTP.getOtp()) {
		return "Correct OTP !";
		}
		else {
			return "Incorrect OTP !";
		}
	}

}
