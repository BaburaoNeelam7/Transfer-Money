package com.example.bank.dao;

import java.util.List;

import com.example.bank.request.LoginRequest;
import com.example.bank.request.RegisterRequest;
import com.example.bank.vo.AccountDetailsVO;
import com.example.bank.vo.ParticipantDetailsVO;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.WriteResult;

public class ParticipantDetails {
	
	private DB connectToDB() {
		
		DB bankDB = null;

		try {
			bankDB = new MongoClient(new MongoClientURI("mongodb://localhost:27017")).getDB("Bank");
		} catch(Exception exp) {
			// Log the  error and shouldn't throw stack trace ideally.
			exp.printStackTrace();
		}
		
		
		 return bankDB;		
	}
	
	public boolean save(RegisterRequest registerRequest, AccountDetailsVO accountDetailsVO) {
		boolean result = false;
		DB bankDB = connectToDB();
		DBCollection participantCollection = bankDB.getCollection("Participant");
		
		DBObject dbObject = 
				new BasicDBObject()
				.append("first_name", registerRequest.getFirstName())
				.append("last_name", registerRequest.getLastName())
				.append("phone", registerRequest.getPhoneNumber())
				.append("account_number_1", accountDetailsVO.getAccountNumberOne())
				.append("account_number_2", accountDetailsVO.getAccountNumberTwo())
				.append("account_one_balance", accountDetailsVO.getAmount())
				.append("account_two_balance", accountDetailsVO.getAmount())
				.append("user_name", registerRequest.getUserName())
				.append("password", registerRequest.getPassword());
		
		try {
			participantCollection.insert(dbObject);
			result = true;
		} catch(Exception exp) {
			result = false;
		}
		return result;
	}
	
	public ParticipantDetailsVO getParticipantDetails(LoginRequest loginRequest) {
		ParticipantDetailsVO participantDetailsVO = null;
		DB bankDB = connectToDB();
				
		DBCollection participantCollection = bankDB.getCollection("Participant");
		
		List<DBObject> dbObjectList = participantCollection.find().toArray();
		
		if(dbObjectList.size() > 0) {
			for(DBObject dbObject: dbObjectList) {
				if((dbObject.get("user_name").toString().equals(loginRequest.getUserName()))
						&& (dbObject.get("password").toString().equals(loginRequest.getPassword()))) {
					
					participantDetailsVO = new ParticipantDetailsVO();
					setParticipantDetailsVO(dbObject, participantDetailsVO);
				}
			}
		}
		return participantDetailsVO;
	}
	
	public ParticipantDetailsVO getParticipantDetailsWithId(String id) {
		ParticipantDetailsVO participantDetailsVO = null;
		DB bankDB = connectToDB();
				
		DBCollection participantCollection = bankDB.getCollection("Participant");
		
		List<DBObject> dbObjectList = participantCollection.find().toArray();
		
		// we can also directly query to mongo db to get details using id
		if(dbObjectList.size() > 0) {
			for(DBObject dbObject: dbObjectList) {
				if(dbObject.get("_id").toString().equals(id)) {
					
					participantDetailsVO = new ParticipantDetailsVO();
					setParticipantDetailsVO(dbObject, participantDetailsVO);
				}
			}
		}
		return participantDetailsVO;
	}
	
	public boolean updateAmountsInDB(ParticipantDetailsVO participantDetailsVO) {
		DB bankDB = connectToDB();
		boolean result = false;
		DBCollection participantCollection = bankDB.getCollection("Participant");
		
		BasicDBObject updateFields = new BasicDBObject();
		updateFields.append("account_one_balance", participantDetailsVO.getAccountOneBalance());
		updateFields.append("account_two_balance", participantDetailsVO.getAccountTwoBalance());
		BasicDBObject setQuery = new BasicDBObject();
		setQuery.append("$set", updateFields);
		
		BasicDBObject searchQuery = new BasicDBObject().append("_id", participantDetailsVO.getId());
		try {
			WriteResult writeResult = participantCollection.update(searchQuery, setQuery);
			result = true;
		} catch(Exception exp) {
			result = false;
		}		
		return result;
	}
	
	private void setParticipantDetailsVO(DBObject dbObject, ParticipantDetailsVO participantDetailsVO) {
		if(dbObject.get("_id") != null) {
			participantDetailsVO.setId(dbObject.get("_id").toString());
		}
		if(dbObject.get("first_name") != null) {
			participantDetailsVO.setFirstName(dbObject.get("first_name").toString());
		}
		if(dbObject.get("last_name") != null) {
			participantDetailsVO.setLastName(dbObject.get("last_name").toString());
		}
		if(dbObject.get("user_name") != null) {
			participantDetailsVO.setUserName(dbObject.get("user_name").toString());
		}
		if(dbObject.get("account_number_1") != null) {
			participantDetailsVO.setAccountNumberOne(dbObject.get("account_number_1").toString());
		}
		if(dbObject.get("account_number_2") != null) {
			participantDetailsVO.setAccountNumberTwo(dbObject.get("account_number_2").toString());
		}
		if(dbObject.get("account_one_balance") != null) {
			participantDetailsVO.setAccountOneBalance(dbObject.get("account_one_balance").toString());
		}
		if(dbObject.get("account_two_balance") != null) {
			participantDetailsVO.setAccountTwoBalance(dbObject.get("account_two_balance").toString());
		}
	}


}
