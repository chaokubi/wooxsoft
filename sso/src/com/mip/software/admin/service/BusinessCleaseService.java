package com.mip.software.admin.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mip.software.admin.dao.MyBatisDao;
import com.mip.software.admin.entity.BusinessClease;
import com.mip.software.common.util.Dto;

/**
 * 客户租赁信息
 */
@Service
public class BusinessCleaseService {
	
	@Autowired
	private MyBatisDao myBatisDao;
	
	/**
	 * 客户租赁信息列表
	 * @param dto
	 * @return
	 */
	public List<BusinessClease> findListPage(Dto dto){
		return myBatisDao.getList("businessCleaseMapper.findListPage",dto);
	}

	/**
	 * 客户租赁信息列表---不分页
	 * @param map
	 * @return
	 */
	public List<BusinessClease> findList(Map<String,Object> map){
		return myBatisDao.getList("businessCleaseMapper.findList",map);
	}
	/**
	 * 根据ID找租赁信息
	 * @param cleaseid
	 * @return
	 */
	public BusinessClease findById(Long cleaseid){
		return myBatisDao.get("businessCleaseMapper.findById",cleaseid);
	}
	
	/**
	 * 保存或者修改信息
	 * @return
	 */
	public String saveBusinessClease(BusinessClease businessClease,HttpServletRequest request){
		String result = "0";//失败
		if (businessClease.getCleaseid()== null){//增加
				myBatisDao.save("businessCleaseMapper.save",businessClease);
				result = "1";
		}
		return 	result;
	}

}
