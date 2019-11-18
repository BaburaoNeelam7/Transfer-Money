package com.example.bank.resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.bank.delegate.LoginDelegate;
import com.example.bank.delegate.RegisterDelegate;
import com.example.bank.delegate.TransferDelegate;
import com.example.bank.request.LoginRequest;
import com.example.bank.request.RegisterRequest;
import com.example.bank.request.TransferRequest;
import com.example.bank.response.LoginResponse;
import com.example.bank.response.RegisterResponse;
import com.example.bank.response.TransferResponse;

@RestController
public class BankResource {
	
	@PostMapping("/register")
	public RegisterResponse register(@RequestBody RegisterRequest registerRequest) throws Exception {
		
		RegisterDelegate registerDelegate = new RegisterDelegate();
		
		return registerDelegate.registerParticipant(registerRequest);
	}
	
	@PostMapping("/login")
	public LoginResponse login(@RequestBody LoginRequest loginRequest) {
		
		LoginDelegate loginDelegate = new LoginDelegate();
		
		return loginDelegate.loginParticipant(loginRequest);
	}
	
	@PostMapping("/transfer")
	public TransferResponse transfer(@RequestBody TransferRequest transferRequest) throws Exception {
		
		TransferDelegate transferDelegate = new TransferDelegate();
		
		return transferDelegate.transfer(transferRequest);
	}
}
