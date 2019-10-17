package com.mip.software.admin.entity;

import java.io.Serializable;

/**
 * 租赁记录
 */
public class BusinessClease implements Serializable {

	private static final long serialVersionUID = 8073421778677293722L;
	
	private Long cleaseid;
	private Long customerid;
	private Long branchshopid;
	private Long modelid;
	private Double cleaseprice;
	private Long cparametid;
	private String cleasestime;
	private String cleaseetime;
	private Long floorid;
	private String cleaseposition;
	private Integer cleasestatus;
	private Integer changecount;
	private Long penqiid;
	private Integer penqitype;
	private String customername;
	private String modelname;
	private String productName;
	private String penqi;
	private String floorinfo;
	public Long getCleaseid() {
		return cleaseid;
	}
	public void setCleaseid(Long cleaseid) {
		this.cleaseid = cleaseid;
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
	public Long getModelid() {
		return modelid;
	}
	public void setModelid(Long modelid) {
		this.modelid = modelid;
	}
	public Double getCleaseprice() {
		return cleaseprice;
	}
	public void setCleaseprice(Double cleaseprice) {
		this.cleaseprice = cleaseprice;
	}
	public Long getCparametid() {
		return cparametid;
	}
	public void setCparametid(Long cparametid) {
		this.cparametid = cparametid;
	}
	public String getCleasestime() {
		return cleasestime;
	}
	public void setCleasestime(String cleasestime) {
		this.cleasestime = cleasestime;
	}
	public String getCleaseetime() {
		return cleaseetime;
	}
	public void setCleaseetime(String cleaseetime) {
		this.cleaseetime = cleaseetime;
	}
	public Long getFloorid() {
		return floorid;
	}
	public void setFloorid(Long floorid) {
		this.floorid = floorid;
	}
	public String getCleaseposition() {
		return cleaseposition;
	}
	public void setCleaseposition(String cleaseposition) {
		this.cleaseposition = cleaseposition;
	}
	public Integer getCleasestatus() {
		return cleasestatus;
	}
	public void setCleasestatus(Integer cleasestatus) {
		this.cleasestatus = cleasestatus;
	}
	public Integer getChangecount() {
		return changecount;
	}
	public void setChangecount(Integer changecount) {
		this.changecount = changecount;
	}
	public Long getPenqiid() {
		return penqiid;
	}
	public void setPenqiid(Long penqiid) {
		this.penqiid = penqiid;
	}
	public Integer getPenqitype() {
		return penqitype;
	}
	public void setPenqitype(Integer penqitype) {
		this.penqitype = penqitype;
	}
	public String getCustomername() {
		return customername;
	}
	public void setCustomername(String customername) {
		this.customername = customername;
	}
	public String getModelname() {
		return modelname;
	}
	public void setModelname(String modelname) {
		this.modelname = modelname;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getPenqi() {
		return penqi;
	}
	public void setPenqi(String penqi) {
		this.penqi = penqi;
	}
	public String getFloorinfo() {
		return floorinfo;
	}
	public void setFloorinfo(String floorinfo) {
		this.floorinfo = floorinfo;
	}
}
