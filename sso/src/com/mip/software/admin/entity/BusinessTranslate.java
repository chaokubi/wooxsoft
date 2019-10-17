package com.mip.software.admin.entity;

import java.io.Serializable;

public class BusinessTranslate implements Serializable {

	private static final long serialVersionUID = 8073421778677293722L;
	
	private Long translateid;
	private String transdate;
	private Long carid;
	private Long frontuserid;
	private String jobstr;
	private String changinfoids;
	private Integer isfinish;
	private Long branchshopid;
	private Integer totalnum;
    private Integer finishnum;
    private String carno;
    private String ssouser_name;
    private Integer notfinish;
    private String nochangeids;
    private String signincustomer;
    private String signoutcustomer;
    private String customers;
	public Long getTranslateid() {
		return translateid;
	}
	public void setTranslateid(Long translateid) {
		this.translateid = translateid;
	}
	public String getTransdate() {
		return transdate;
	}
	public void setTransdate(String transdate) {
		this.transdate = transdate;
	}
	public Long getCarid() {
		return carid;
	}
	public void setCarid(Long carid) {
		this.carid = carid;
	}
	public Long getFrontuserid() {
		return frontuserid;
	}
	public void setFrontuserid(Long frontuserid) {
		this.frontuserid = frontuserid;
	}
	public String getJobstr() {
		return jobstr;
	}
	public void setJobstr(String jobstr) {
		this.jobstr = jobstr;
	}
	public String getChanginfoids() {
		return changinfoids;
	}
	public void setChanginfoids(String changinfoids) {
		this.changinfoids = changinfoids;
	}
	public Integer getIsfinish() {
		return isfinish;
	}
	public void setIsfinish(Integer isfinish) {
		this.isfinish = isfinish;
	}
	public Long getBranchshopid() {
		return branchshopid;
	}
	public void setBranchshopid(Long branchshopid) {
		this.branchshopid = branchshopid;
	}
	public Integer getTotalnum() {
		return totalnum;
	}
	public void setTotalnum(Integer totalnum) {
		this.totalnum = totalnum;
	}
	public Integer getFinishnum() {
		return finishnum;
	}
	public void setFinishnum(Integer finishnum) {
		this.finishnum = finishnum;
	}
	public String getCarno() {
		return carno;
	}
	public void setCarno(String carno) {
		this.carno = carno;
	}
	public String getSsouser_name() {
		return ssouser_name;
	}
	public void setSsouser_name(String ssouser_name) {
		this.ssouser_name = ssouser_name;
	}
	public Integer getNotfinish() {
		return notfinish;
	}
	public void setNotfinish(Integer notfinish) {
		this.notfinish = notfinish;
	}
	public String getNochangeids() {
		return nochangeids;
	}
	public void setNochangeids(String nochangeids) {
		this.nochangeids = nochangeids;
	}
	public String getSignincustomer() {
		return signincustomer;
	}
	public void setSignincustomer(String signincustomer) {
		this.signincustomer = signincustomer;
	}
	public String getSignoutcustomer() {
		return signoutcustomer;
	}
	public void setSignoutcustomer(String signoutcustomer) {
		this.signoutcustomer = signoutcustomer;
	}
	public String getCustomers() {
		return customers;
	}
	public void setCustomers(String customers) {
		this.customers = customers;
	}
}
