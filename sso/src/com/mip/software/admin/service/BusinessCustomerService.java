package com.mip.software.admin.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mip.software.admin.dao.MyBatisDao;
import com.mip.software.admin.entity.BusinessCustomer;
import com.mip.software.admin.entity.SysUser;
import com.mip.software.common.util.Dto;

/**
 * 客户信息
 */
@Service
public class BusinessCustomerService {
	
	@Autowired
	private MyBatisDao myBatisDao;
	
	/**
	 * 客户信息列表
	 * @param dto
	 * @return
	 */
	public List<BusinessCustomer> findListPage(Dto dto){
		return myBatisDao.getList("businessCustomerMapper.findListPage",dto);
	}

	/**
	 * 客户信息列表---不分页
	 * @param map
	 * @return
	 */
	public List<BusinessCustomer> findList(Map<String,Object> map){
		return myBatisDao.getList("businessCustomerMapper.findList",map);
	}
	
	/**
	 * 符合条件得客户id组合
	 * @param map
	 * @return
	 */
	public String getDistinctCustomerList(String str){
		return myBatisDao.get("businessCustomerMapper.getDistinctCustomerList",str);
	}
	
	/**
	 * 判断是否有该客户下面得数据
	 * @param map
	 * @return
	 */
	public Integer getSelectCountOf(Map<String,Object> map){
		return myBatisDao.get("businessCustomerMapper.getSelectCountOf",map);
	}
	
	/**
	 * 获取当前有更换记录得客户列表---不分页
	 * @param map
	 * @return
	 */
	public List<BusinessCustomer> findChangeList(Map<String,Object> map){
		return myBatisDao.getList("businessCustomerMapper.findChangeList",map);
	}
	
	/**
	 * 获取当前配送得客户列表---不分页
	 * @param map
	 * @return
	 */
	public List<BusinessCustomer> findPsList(Map<String,Object> map){
		return myBatisDao.getList("businessCustomerMapper.findPsList",map);
	}
	/**
	 * 根据ID找客户信息
	 * @param customerid
	 * @return
	 */
	public BusinessCustomer findById(Long customerid){
		return myBatisDao.get("businessCustomerMapper.findById",customerid);
	}
	
	/**
	 * 保存或者修改信息
	 * @return
	 */
	public String saveBusinessCustomer(BusinessCustomer businessCustomer,HttpServletRequest request){
		String result = "0";//失败
		SysUser t=(SysUser)request.getSession().getAttribute("loginuser");
		if (businessCustomer.getCustomerid()== null){//增加
			businessCustomer.setBranchshopid(t.getBranchshopid());
				myBatisDao.save("businessCustomerMapper.save",businessCustomer);
				result = "1";
		}else{//修改
			myBatisDao.update("businessCustomerMapper.update", businessCustomer);
				result = "1";
		}
		return 	result;
	}
	
	/**
	 * 删除操作
	 * 
	 */
	public void del(Long customerid){
		myBatisDao.delete("businessCustomerMapper.del",customerid);
	}

}
