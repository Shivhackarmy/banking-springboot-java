package com.pro.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="account")
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int accno;
	private int uid;
	private String accname;
	private String acctype;
	private double bal;
	private String accdate;
	private String accstatus;
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Account(int accno, int uid, String accname, String acctype, double bal, String accdate, String accstatus) {
		super();
		this.accno = accno;
		this.uid = uid;
		this.accname = accname;
		this.acctype = acctype;
		this.bal = bal;
		this.accdate = accdate;
		this.accstatus = accstatus;
	}
	public int getAccno() {
		return accno;
	}
	public void setAccno(int accno) {
		this.accno = accno;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getAccname() {
		return accname;
	}
	public void setAccname(String accname) {
		this.accname = accname;
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
	public String getAccdate() {
		return accdate;
	}
	public void setAccdate(String accdate) {
		this.accdate = accdate;
	}
	public String getAccstatus() {
		return accstatus;
	}
	public void setAccstatus(String accstatus) {
		this.accstatus = accstatus;
	}
	@Override
	public String toString() {
		return "Account [accno=" + accno + ", uid=" + uid + ", accname=" + accname + ", acctype=" + acctype + ", bal="
				+ bal + ", accdate=" + accdate + ", accstatus=" + accstatus + "]";
	}
		
}
