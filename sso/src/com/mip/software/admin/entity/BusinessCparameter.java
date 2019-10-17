package com.mip.software.admin.entity;

import java.io.Serializable;

/**
 * 客户报价参数
 */
public class BusinessCparameter implements Serializable {

	private static final long serialVersionUID = 8073421778677293722L;
	private Long cparametid;
	private String cparametname;
	private String cparametdetail;
	private Double cparametprice;
	private Integer cparametstatus;
	private Long customerid;
	private Long branchshopid;
	public Long getCparametid() {
		return cparametid;
	}
	public void setCparametid(Long cparametid) {
		this.cparametid = cparametid;
	}
	public String getCparametname() {
		return cparametname;
	}
	public void setCparametname(String cparametname) {
		this.cparametname = cparametname;
	}
	public String getCparametdetail() {
		return cparametdetail;
	}
	public void setCparametdetail(String cparametdetail) {
		this.cparametdetail = cparametdetail;
	}
	public Double getCparametprice() {
		return cparametprice;
	}
	public void setCparametprice(Double cparametprice) {
		this.cparametprice = cparametprice;
	}
	public Integer getCparametstatus() {
		return cparametstatus;
	}
	public void setCparametstatus(Integer cparametstatus) {
		this.cparametstatus = cparametstatus;
	}
	public Long getCustomerid() {
		return customerid;
	}
	public void setCustomerid(Long customerid) {
		this.customerid = customerid;
	}
	public Long getBranchshopid() {
		return branchshopid;
	}
	public void setBranchshopid(Long branchshopid) {
		this.branchshopid = branchshopid;
	}
	
}
