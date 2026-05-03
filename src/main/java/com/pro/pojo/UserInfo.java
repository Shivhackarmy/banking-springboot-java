package com.pro.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="userinfo")
public class UserInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int uid;
	private String username;
	@Column(unique = true)
	private String email;
	private String mobile;
	private String gender;
	private String branch;
	private String dob;
	private String address;
	private String acctype;
	private double bal;
	private String pan;
	private String password;
	private String dp;
	private String role;
	public UserInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserInfo(int uid, String username, String email, String mobile, String gender, String branch, String dob,
			String address, String acctype, double bal, String pan, String password, String dp, String role) {
		super();
		this.uid = uid;
		this.username = username;
		this.email = email;
		this.mobile = mobile;
		this.gender = gender;
		this.branch = branch;
		this.dob = dob;
		this.address = address;
		this.acctype = acctype;
		this.bal = bal;
		this.pan = pan;
		this.password = password;
		this.dp = dp;
		this.role = role;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAcctype() {
		return acctype;
	}
	public void setAcctype(String acctype) {
		this.acctype = acctype;
	}
	public double getBal() {
		return bal;
	}
	public void setBal(double bal) {
		this.bal = bal;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDp() {
		return dp;
	}
	public void setDp(String dp) {
		this.dp = dp;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return uid + "\t" + username + "\t" + email + "\t" + mobile
				+ "\t" + gender + "\t" + branch + "\t=" + dob + "\t" + address + "\t"
				+ acctype + "\t" + bal + "\t" + pan ;
	}
}
