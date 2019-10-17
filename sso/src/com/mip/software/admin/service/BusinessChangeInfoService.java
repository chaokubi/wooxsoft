package com.mip.software.admin.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mip.software.admin.dao.MyBatisDao;
import com.mip.software.admin.entity.BusinessChangeInfo;
import com.mip.software.common.util.Dto;

/**
 * 调整信息
 */
@Service
public class BusinessChangeInfoService {
	@Autowired
	private MyBatisDao myBatisDao;
	/**
	 * 调整信息列表
	 * @param dto
	 * @return
	 */
	public List<BusinessChangeInfo> findListPage(Dto dto){
		return myBatisDao.getList("businessChangeInfoMapper.findListPage",dto);
	}

	/**
	 * 调整信息列表---不分页
	 * @param map
	 * @return
	 */
	public List<BusinessChangeInfo> findList(Map<String,Object> map){
		return myBatisDao.getList("businessChangeInfoMapper.findList",map);
	}
	
	/**
	 * 判断是否其他配送单包含了---不分页
	 * @param map
	 * @return
	 */
	public List<BusinessChangeInfo> findIsSelectList(Map<String,Object> map){
		return myBatisDao.getList("businessChangeInfoMapper.findIsSelectList",map);
	}
	
	/**
	 * 花卉分组统计列表---不分页
	 * @param map
	 * @return
	 */
	public List<BusinessChangeInfo> findtypeHHList(Map<String,Object> map){
		return myBatisDao.getList("businessChangeInfoMapper.findtypeHHList",map);
	}
	
	/**
	 * 盆器分组统计列表---不分页
	 * @param map
	 * @return
	 */
	public List<BusinessChangeInfo> findtypePqList(Map<String,Object> map){
		return myBatisDao.getList("businessChangeInfoMapper.findtypePqList",map);
	}
	
	/**
	 * 调整客户汇总列表---不分页
	 * @param map
	 * @return
	 */
	public List<BusinessChangeInfo> findChangeList(Map<String,Object> map){
		return myBatisDao.getList("businessChangeInfoMapper.findChangeList",map);
	}
	
	/**
	 * 根据ID找调整信息
	 * @param changinfoid
	 * @return
	 */
	public BusinessChangeInfo findById(Long changinfoid){
		return myBatisDao.get("businessChangeInfoMapper.findById",changinfoid);
	}
	
	
	/**
	 * 保存或者修改信息
	 * @return
	 */
	public String saveBusinessChangeInfo(BusinessChangeInfo businessChangeInfo,HttpServletRequest request){
		String result = "0";//失败
		/*SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");*/
		if (businessChangeInfo.getChanginfoid()== null){
			
		}else{//修改
			myBatisDao.update("businessChangeInfoMapper.update", businessChangeInfo);
				result = "1";
		}
		return 	result;
	}
	
	/**
	 * 删除操作
	 * 
	 */
	public void del(Long changinfoid){
		myBatisDao.delete("businessChangeInfoMapper.del",changinfoid);
	}

}
