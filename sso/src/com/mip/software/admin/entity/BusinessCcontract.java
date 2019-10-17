package com.mip.software.admin.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 合同管理
 */
public class BusinessCcontract implements Serializable {

	private static final long serialVersionUID = 8073421778677293722L;
	
	private Long ccontractid;
	private String ccontractno;
	private Long customerid;
	private String customername;
	private Long branchshopid;
	private Double ccontractmoney;
	private String ccontractname;
	private String ccontractfile;
	private Date ccontractstime;
	private Date ccontractetime;
	private Date ccontractadddate;
	private Long userid;
	private Integer ccontractstatus;
	private String username;
	public Long getCcontractid() {
		return ccontractid;
	}
	public void setCcontractid(Long ccontractid) {
		this.ccontractid = ccontractid;
	}
	public String getCcontractno() {
		return ccontractno;
	}
	public void setCcontractno(String ccontractno) {
		this.ccontractno = ccontractno;
	}
	public Long getCustomerid() {
		return customerid;
	}
	public void setCustomerid(Long customerid) {
		this.customerid = customerid;
	}
	public String getCustomername() {
		return customername;
	}
	public void setCustomername(String customername) {
		this.customername = customername;
	}
	public Long getBranchshopid() {
		return branchshopid;
	}
	public void setBranchshopid(Long branchshopid) {
		this.branchshopid = branchshopid;
	}
	public Double getCcontractmoney() {
		return ccontractmoney;
	}
	public void setCcontractmoney(Double ccontractmoney) {
		this.ccontractmoney = ccontractmoney;
	}
	public String getCcontractname() {
		return ccontractname;
	}
	public void setCcontractname(String ccontractname) {
		this.ccontractname = ccontractname;
	}
	public String getCcontractfile() {
		return ccontractfile;
	}
	public void setCcontractfile(String ccontractfile) {
		this.ccontractfile = ccontractfile;
	}
	public Date getCcontractstime() {
		return ccontractstime;
	}
	public void setCcontractstime(Date ccontractstime) {
		this.ccontractstime = ccontractstime;
	}
	public Date getCcontractetime() {
		return ccontractetime;
	}
	public void setCcontractetime(Date ccontractetime) {
		this.ccontractetime = ccontractetime;
	}
	public Date getCcontractadddate() {
		return ccontractadddate;
	}
	public void setCcontractadddate(Date ccontractadddate) {
		this.ccontractadddate = ccontractadddate;
	}
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public Integer getCcontractstatus() {
		return ccontractstatus;
	}
	public void setCcontractstatus(Integer ccontractstatus) {
		this.ccontractstatus = ccontractstatus;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
}
