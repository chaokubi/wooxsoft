package com.mip.software.admin.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mip.software.admin.dao.MyBatisDao;
import com.mip.software.admin.entity.BusinessCparameter;
import com.mip.software.admin.entity.SysUser;

/**
 * 客户报价参数信息
 */
@Service
public class BusinessCparameterService {
	
	@Autowired
	private MyBatisDao myBatisDao;
	
	/**
	 * 报价参数列表---不分页
	 * @param map
	 * @return
	 */
	public List<BusinessCparameter> findList(Map<String,Object> map){
		return myBatisDao.getList("businessCparameterMapper.findList",map);
	}
	
	/**
	 * 根据ID找报价参数信息
	 * @param customerid
	 * @return
	 */
	public BusinessCparameter findById(Long customerid){
		return myBatisDao.get("businessCparameterMapper.findById",customerid);
	}
	
	/**
	 * 保存或者修改信息
	 * @return
	 */
	public String saveBusinessCustomer(BusinessCparameter businessCparameter,HttpServletRequest request){
		String result = "0";//失败
		SysUser t=(SysUser)request.getSession().getAttribute("loginuser");
		if (businessCparameter.getCparametid()== null){//增加
			businessCparameter.setBranchshopid(t.getBranchshopid());
				myBatisDao.save("businessCparameterMapper.save",businessCparameter);
				result = "1";
		}else{//修改
			myBatisDao.update("businessCparameterMapper.update", businessCparameter);
				result = "1";
		}
		return 	result;
	}

}
