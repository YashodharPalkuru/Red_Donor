package com.reddonor.entity;

public class UserBean {
	
	private int id;
	private String fName;
	private String lName;
	private String email;
	private long mobile;
	private String facebookId;
	private String bloodGroup;
	private String status;
	private Address address;
	private String currLocation;
	private String originaLocation;
	
	
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getMobile() {
		return mobile;
	}
	public void setMobile(long mobile) {
		this.mobile = mobile;
	}
	public String getFacebookId() {
		return facebookId;
	}
	public void setFacebookId(String facebookId) {
		this.facebookId = facebookId;
	}
	public String getBloodGroup() {
		return bloodGroup;
	}
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getCurrLocation() {
		return currLocation;
	}
	public void setCurrLocation(String currLocation) {
		this.currLocation = currLocation;
	}
	public String getOriginaLocation() {
		return originaLocation;
	}
	public void setOriginaLocation(String originaLocation) {
		this.originaLocation = originaLocation;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	

}
