package com.ghosta3.ocr;

import java.io.Serializable;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;


public class SMS implements Serializable {
	private static final long serialVersionUID = 1915359481985722847L;
	
	
	
	private String mMessage;
	private String mPhoneNo;
	
	public String getMessage() {
		return mMessage;
	}
	public void setMessage(String message) {
		mMessage = message;
	}
	public String getPhoneNo() {
		return mPhoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		mPhoneNo = phoneNo;
	}
	
	
}
