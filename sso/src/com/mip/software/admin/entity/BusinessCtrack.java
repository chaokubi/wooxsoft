package com.mip.software.admin.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 客户跟踪信息
 */
public class BusinessCtrack implements Serializable {

	private static final long serialVersionUID = 8073421778677293722L;
	
	private Long ctrackid;
	private Date ctrackdate;
	private String ctrackresult;
	private Long frontuserid;
	private Date nextdate;
	private Long customerid;
	private String ssouser_name;
	public Long getCtrackid() {
		return ctrackid;
	}
	public void setCtrackid(Long ctrackid) {
		this.ctrackid = ctrackid;
	}
	public Date getCtrackdate() {
		return ctrackdate;
	}
	public void setCtrackdate(Date ctrackdate) {
		this.ctrackdate = ctrackdate;
	}
	public String getCtrackresult() {
		return ctrackresult;
	}
	public void setCtrackresult(String ctrackresult) {
		this.ctrackresult = ctrackresult;
	}
	public Long getFrontuserid() {
		return frontuserid;
	}
	public void setFrontuserid(Long frontuserid) {
		this.frontuserid = frontuserid;
	}
	public Date getNextdate() {
		return nextdate;
	}
	public void setNextdate(Date nextdate) {
		this.nextdate = nextdate;
	}
	public Long getCustomerid() {
		return customerid;
	}
	public void setCustomerid(Long customerid) {
		this.customerid = customerid;
	}
	public String getSsouser_name() {
		return ssouser_name;
	}
	public void setSsouser_name(String ssouser_name) {
		this.ssouser_name = ssouser_name;
	}
	
}
