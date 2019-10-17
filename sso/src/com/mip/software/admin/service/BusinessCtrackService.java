package com.mip.software.admin.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mip.software.admin.dao.MyBatisDao;
import com.mip.software.admin.entity.BusinessCtrack;
import com.mip.software.admin.entity.BusinessCustomer;
import com.mip.software.common.util.Dto;

/**
 * 客户跟踪
 */
@Service
public class BusinessCtrackService {
	
	@Autowired
	private MyBatisDao myBatisDao;
	@Autowired
	private  BusinessCustomerService businessCustomerService;
	/**
	 * 客户跟踪列表
	 * @param dto
	 * @return
	 */
	public List<BusinessCtrack> findListPage(Dto dto){
		return myBatisDao.getList("businessCtrackMapper.findListPage",dto);
	}

	/**
	 * 客户跟踪列表---不分页
	 * @param map
	 * @return
	 */
	public List<BusinessCtrack> findList(Map<String,Object> map){
		return myBatisDao.getList("businessCtrackMapper.findList",map);
	}
	/**
	 * 根据ID找客户跟踪
	 * @param ctrackid
	 * @return
	 */
	public BusinessCtrack findById(Long ctrackid){
		return myBatisDao.get("businessCtrackMapper.findById",ctrackid);
	}
	
	/**
	 * 保存或者修改信息
	 * @return
	 */
	public String saveBusinessCtrack(BusinessCtrack businessCtrack,HttpServletRequest request){
		String result = "0";//失败
		//更新合同到期时间
				if(!"".equals(businessCtrack.getNextdate())&&businessCtrack.getNextdate()!=null){
					BusinessCustomer businessCustomer=businessCustomerService.findById(businessCtrack.getCustomerid());
					businessCustomer.setCtracktime(businessCtrack.getNextdate());
					businessCustomerService.saveBusinessCustomer(businessCustomer, request);
				}
				
		if (businessCtrack.getCtrackid()== null){//增加
				myBatisDao.save("businessCtrackMapper.save",businessCtrack);
				result = "1";
		}else{//修改
			myBatisDao.update("businessCtrackMapper.update", businessCtrack);
				result = "1";
		}
		return 	result;
	}
	
	/**
	 * 删除操作
	 * 
	 */
	public void del(Long ctrackid){
		myBatisDao.delete("businessCtrackMapper.del",ctrackid);
	}

}
