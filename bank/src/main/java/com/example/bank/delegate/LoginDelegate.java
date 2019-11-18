package com.example.bank.delegate;

import com.example.bank.dao.ParticipantDetails;
import com.example.bank.request.LoginRequest;
import com.example.bank.response.LoginResponse;
import com.example.bank.vo.ParticipantDetailsVO;

public class LoginDelegate {
	
	public LoginResponse loginParticipant(LoginRequest loginRequest) {
		
		LoginResponse loginResponse = new LoginResponse();
		
		ParticipantDetails participantDetails = new ParticipantDetails();
		ParticipantDetailsVO participantDetailsVO = participantDetails.getParticipantDetails(loginRequest);
		if(participantDetailsVO != null) {
			loginResponse.setStatus("Success");
			loginResponse.setStatusText("Login Successful.");
			loginResponse.setId(participantDetailsVO.getId());
			loginResponse.setAccountNumberOne(participantDetailsVO.getAccountNumberOne());
			loginResponse.setAccountOneBalance(Double.parseDouble(participantDetailsVO.getAccountOneBalance()));
			loginResponse.setAccountNumberTwo(participantDetailsVO.getAccountNumberTwo());
			loginResponse.setAccountTwoBalance(Double.parseDouble(participantDetailsVO.getAccountTwoBalance()));
			loginResponse.setFirstName(participantDetailsVO.getFirstName());
			loginResponse.setLastName(participantDetailsVO.getLastName());
			loginResponse.setUserName(participantDetailsVO.getUserName());
		} else {
			loginResponse.setStatus("Failure");
			loginResponse.setStatusText("Invalid username/password");
		}
		
		return loginResponse;
	}

}
