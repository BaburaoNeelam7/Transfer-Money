package com.example.bank.delegate;

import java.util.Random;

import org.apache.commons.lang3.math.NumberUtils;

import com.example.bank.dao.ParticipantDetails;
import com.example.bank.request.RegisterRequest;
import com.example.bank.response.RegisterResponse;
import com.example.bank.vo.AccountDetailsVO;

public class RegisterDelegate {
	
	public RegisterResponse registerParticipant(RegisterRequest registerRequest) throws Exception {
		RegisterResponse registerResponse = new RegisterResponse();
		
		validatePassword(registerRequest.getPassword());
		validatePhoneNumber(registerRequest.getPhoneNumber());
		
		AccountDetailsVO accountDetailsVO = new AccountDetailsVO();
		accountDetailsVO.setAccountNumberOne(generateAccountNumber(9));
		accountDetailsVO.setAccountNumberTwo(generateAccountNumber(9));
		accountDetailsVO.setAmount(500);
		
		ParticipantDetails participantDetails = new ParticipantDetails();
		boolean result = participantDetails.save(registerRequest, accountDetailsVO);
		if(result) {
			registerResponse.setStatus("Success");
			registerResponse.setStatusText("Registration Successful");
		} else {
			registerResponse.setStatus("Failed");
			registerResponse.setStatusText("Failed to register. Please try again or contact call center");
		}
		return registerResponse;
	}
	
	private void validatePassword(String password) throws Exception{
		if(password == null) {
			throw new Exception("Password is required");
		}
	}
	
	private void validatePhoneNumber(String phone) throws Exception {
		
		if(phone == null) {
			throw new Exception("Phone Number is Mandatory");
		} else if(phone.length() != 10) {
			throw new Exception("Length of Phone Number should be exactly 10.");
		} else if(!NumberUtils.isDigits(phone)) {
			throw new Exception("Phone Number should be Numeric");
		}
	}
	
	public String generateAccountNumber(int n) {
	    int m = (int) Math.pow(10, n - 1);
	    int number = m + new Random().nextInt(9 * m);
	    return String.valueOf(number);
	}

}
