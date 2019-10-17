package com.mip.software.admin.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mip.software.admin.dao.MyBatisDao;
import com.mip.software.admin.entity.BusinessCcontract;
import com.mip.software.admin.entity.BusinessCustomer;
import com.mip.software.admin.entity.SysUser;
import com.mip.software.common.util.Dto;

/**
 * 客户合同
 */
@Service
public class BusinessCcontractService {
	
	@Autowired
	private MyBatisDao myBatisDao;
	@Autowired
	private  BusinessCustomerService businessCustomerService;
	/**
	 * 客户合同列表
	 * @param dto
	 * @return
	 */
	public List<BusinessCcontract> findListPage(Dto dto){
		return myBatisDao.getList("businessCcontractMapper.findListPage",dto);
	}

	/**
	 * 客户合同列表---不分页
	 * @param map
	 * @return
	 */
	public List<BusinessCcontract> findList(Map<String,Object> map){
		return myBatisDao.getList("businessCcontractMapper.findList",map);
	}
	/**
	 * 根据ID找客户合同
	 * @param ccontractid
	 * @return
	 */
	public BusinessCcontract findById(Long ccontractid){
		return myBatisDao.get("businessCcontractMapper.findById",ccontractid);
	}
	
	/**
	 * 保存或者修改信息
	 * @return
	 */
	public String saveBusinessCcontract(BusinessCcontract businessCcontract,HttpServletRequest request){
		String result = "0";//失败
		SysUser t=(SysUser)request.getSession().getAttribute("loginuser");
		
		//更新合同到期时间
		if(!"".equals(businessCcontract.getCcontractetime())&&businessCcontract.getCcontractetime()!=null){
			BusinessCustomer businessCustomer=businessCustomerService.findById(businessCcontract.getCustomerid());
			businessCustomer.setConractendtime(businessCcontract.getCcontractetime());
			businessCustomerService.saveBusinessCustomer(businessCustomer, request);
		}
		if (businessCcontract.getCcontractid()== null){//增加
			businessCcontract.setBranchshopid(t.getBranchshopid());
			businessCcontract.setUserid(t.getUserid());
			//更新该客户其他合同状态
			
				myBatisDao.save("businessCcontractMapper.save",businessCcontract);
				result = "1";
		}else{//修改
			myBatisDao.update("businessCcontractMapper.update", businessCcontract);
				result = "1";
		}
		return 	result;
	}
	
	/**
	 * 删除操作
	 * 
	 */
	public void del(Long ccontractid){
		myBatisDao.delete("businessCcontractMapper.del",ccontractid);
	}

}
