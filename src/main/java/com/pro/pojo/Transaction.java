package com.pro.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="transaction")
public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int tid;
	private int accno;
	private double tammount;
	private String ttype;
	private String tstatus;
	private String tdate;
	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Transaction(int tid, int accno, double tammount, String ttype, String tstatus, String tdate) {
		super();
		this.tid = tid;
		this.accno = accno;
		this.tammount = tammount;
		this.ttype = ttype;
		this.tstatus = tstatus;
		this.tdate = tdate;
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public int getAccno() {
		return accno;
	}
	public void setAccno(int accno) {
		this.accno = accno;
	}
	public double getTammount() {
		return tammount;
	}
	public void setTammount(double tammount) {
		this.tammount = tammount;
	}
	public String getTtype() {
		return ttype;
	}
	public void setTtype(String ttype) {
		this.ttype = ttype;
	}
	public String getTstatus() {
		return tstatus;
	}
	public void setTstatus(String tstatus) {
		this.tstatus = tstatus;
	}
	public String getTdate() {
		return tdate;
	}
	public void setTdate(String tdate) {
		this.tdate = tdate;
	}
	@Override
	public String toString() {
		return "Transaction [tid=" + tid + ", accno=" + accno + ", tammount=" + tammount + ", ttype=" + ttype
				+ ", tstatus=" + tstatus + ", tdate=" + tdate + "]";
	}
		
}
