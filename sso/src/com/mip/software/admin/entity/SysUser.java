package com.mip.software.admin.entity;

import java.io.Serializable;

public class SysUser implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5155783925064485289L;
	private Long userid;
	private String user_login;
	private String user_pass;
	private Integer user_status;
	private String last_login_time;
	private String create_time;
	private String role_name;
	private Long[] role;
	private Integer cnt;
	private String username;
	private Long distrid;
	private String distrname;
	private Long branchshopid;
	private String branchshopname;
	private Long areaid;
	
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public String getUser_login() {
		return user_login;
	}
	public void setUser_login(String user_login) {
		this.user_login = user_login;
	}
	public String getUser_pass() {
		return user_pass;
	}
	public void setUser_pass(String user_pass) {
		this.user_pass = user_pass;
	}
	public Integer getUser_status() {
		return user_status;
	}
	public void setUser_status(Integer user_status) {
		this.user_status = user_status;
	}
	public String getLast_login_time() {
		return last_login_time;
	}
	public void setLast_login_time(String last_login_time) {
		this.last_login_time = last_login_time;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	public Long[] getRole() {
		return role;
	}
	public void setRole(Long[] role) {
		this.role = role;
	}
	public Integer getCnt() {
		return cnt;
	}
	public void setCnt(Integer cnt) {
		this.cnt = cnt;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Long getDistrid() {
		return distrid;
	}
	public void setDistrid(Long distrid) {
		this.distrid = distrid;
	}
	public String getDistrname() {
		return distrname;
	}
	public void setDistrname(String distrname) {
		this.distrname = distrname;
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
	public Long getAreaid() {
		return areaid;
	}
	public void setAreaid(Long areaid) {
		this.areaid = areaid;
	}
	
}
