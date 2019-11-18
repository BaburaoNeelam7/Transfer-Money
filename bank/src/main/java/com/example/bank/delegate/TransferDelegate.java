package com.example.bank.delegate;

import com.example.bank.dao.ParticipantDetails;
import com.example.bank.request.TransferRequest;
import com.example.bank.response.TransferResponse;
import com.example.bank.vo.ParticipantDetailsVO;

public class TransferDelegate {
	
	public TransferResponse transfer(TransferRequest transferRequest) throws Exception {
		ParticipantDetails participantDetails = new ParticipantDetails();
		TransferResponse transferResponse = new TransferResponse();
		ParticipantDetailsVO participantDetailsVO = participantDetails.getParticipantDetailsWithId(transferRequest.getId());
		if(participantDetailsVO.getAccountNumberOne().equals(transferRequest.getFromAccountNumber())) {
			if(Double.parseDouble(participantDetailsVO.getAccountOneBalance()) < transferRequest.getAmount()) {
				throw new Exception("Insufficient funds in the from Account");
			}
			updateAmounts(transferRequest, participantDetailsVO, "accountOne");
		} else if(participantDetailsVO.getAccountNumberTwo().equals(transferRequest.getFromAccountNumber())) {
			if(Double.parseDouble(participantDetailsVO.getAccountTwoBalance()) < transferRequest.getAmount()) {
				throw new Exception("Insufficient funds in the from Account");
			}
			updateAmounts(transferRequest, participantDetailsVO, "accountTwo");
		}
		boolean updated = participantDetails.updateAmountsInDB(participantDetailsVO);
		if(updated) {
			transferResponse.setStatus("Success");
			transferResponse.setStatusText("Amount transferred successfully.");
		} else {
			transferResponse.setStatus("Failure");
			transferResponse.setStatusText("Failed to transfer");
		}
		return transferResponse;
	}
	
	private void updateAmounts(TransferRequest transferRequest, ParticipantDetailsVO participantDetailsVO, String accountSource) {
		if(accountSource.equals("accountOne")) {
			double fromAccountBalance = Double.parseDouble(participantDetailsVO.getAccountOneBalance()) - transferRequest.getAmount();
			double toAccountBalance = Double.parseDouble(participantDetailsVO.getAccountTwoBalance()) + transferRequest.getAmount();
			
			participantDetailsVO.setAccountOneBalance(Double.toString(fromAccountBalance));
			participantDetailsVO.setAccountTwoBalance(Double.toString(toAccountBalance));
			
		}
		else if(accountSource.equals("accountTwo")) {
			double fromAccountBalance = Double.parseDouble(participantDetailsVO.getAccountTwoBalance()) - transferRequest.getAmount();
			double toAccountBalance = Double.parseDouble(participantDetailsVO.getAccountOneBalance()) + transferRequest.getAmount();
			
			participantDetailsVO.setAccountTwoBalance(Double.toString(fromAccountBalance));
			participantDetailsVO.setAccountOneBalance(Double.toString(toAccountBalance));
			
		}
	}

}
