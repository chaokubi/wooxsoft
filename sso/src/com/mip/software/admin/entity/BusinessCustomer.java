package com.mip.software.admin.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * 客户信息
 */
public class BusinessCustomer implements Serializable {

	private static final long serialVersionUID = 8073421778677293722L;
	private Long customerid;
	private String customername;
	private String customeraddress;
	private String customergis;
	private Integer customertype;
	private Long frontuserid;
	private Date adddate;
	private Long branchshopid;
	private String branchshopname;
	private String customerlink;
	private String customertel;
	private String ssouser_name;
	private Date conractendtime;
	private Date ctracktime;
	private Integer isfast;
	private Integer changenum;
	private Long maintainfrontid;
	private String maintainuser;
	private Long ywfrontid;
	private String ywuser;
	List<BusinessChangeInfo> changelist=new ArrayList<BusinessChangeInfo>();
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
	public String getCustomeraddress() {
		return customeraddress;
	}
	public void setCustomeraddress(String customeraddress) {
		this.customeraddress = customeraddress;
	}
	public String getCustomergis() {
		return customergis;
	}
	public void setCustomergis(String customergis) {
		this.customergis = customergis;
	}
	public Integer getCustomertype() {
		return customertype;
	}
	public void setCustomertype(Integer customertype) {
		this.customertype = customertype;
	}
	public Long getFrontuserid() {
		return frontuserid;
	}
	public void setFrontuserid(Long frontuserid) {
		this.frontuserid = frontuserid;
	}
	public Date getAdddate() {
		return adddate;
	}
	public void setAdddate(Date adddate) {
		this.adddate = adddate;
	}
	public Long getBranchshopid() {
		return branchshopid;
	}
	public void setBranchshopid(Long branchshopid) {
		this.branchshopid = branchshopid;
	}
	public String getBranchshopname() {
		return branchshopname;
	}
	public void setBranchshopname(String branchshopname) {
		this.branchshopname = branchshopname;
	}
	public String getCustomerlink() {
		return customerlink;
	}
	public void setCustomerlink(String customerlink) {
		this.customerlink = customerlink;
	}
	public String getCustomertel() {
		return customertel;
	}
	public void setCustomertel(String customertel) {
		this.customertel = customertel;
	}
	public String getSsouser_name() {
		return ssouser_name;
	}
	public void setSsouser_name(String ssouser_name) {
		this.ssouser_name = ssouser_name;
	}
	public Date getConractendtime() {
		return conractendtime;
	}
	public void setConractendtime(Date conractendtime) {
		this.conractendtime = conractendtime;
	}
	public Date getCtracktime() {
		return ctracktime;
	}
	public void setCtracktime(Date ctracktime) {
		this.ctracktime = ctracktime;
	}
	public Integer getIsfast() {
		return isfast;
	}
	public void setIsfast(Integer isfast) {
		this.isfast = isfast;
	}
	public Integer getChangenum() {
		return changenum;
	}
	public void setChangenum(Integer changenum) {
		this.changenum = changenum;
	}
	public List<BusinessChangeInfo> getChangelist() {
		return changelist;
	}
	public void setChangelist(List<BusinessChangeInfo> changelist) {
		this.changelist = changelist;
	}
	public Long getMaintainfrontid() {
		return maintainfrontid;
	}
	public void setMaintainfrontid(Long maintainfrontid) {
		this.maintainfrontid = maintainfrontid;
	}
	public String getMaintainuser() {
		return maintainuser;
	}
	public void setMaintainuser(String maintainuser) {
		this.maintainuser = maintainuser;
	}
	public Long getYwfrontid() {
		return ywfrontid;
	}
	public void setYwfrontid(Long ywfrontid) {
		this.ywfrontid = ywfrontid;
	}
	public String getYwuser() {
		return ywuser;
	}
	public void setYwuser(String ywuser) {
		this.ywuser = ywuser;
	}
}
