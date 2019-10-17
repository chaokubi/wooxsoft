package com.mip.software.admin.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mip.software.admin.dao.MyBatisDao;
import com.mip.software.admin.entity.CommonAttach;
import com.mip.software.common.util.Dto;

/**
 * 附件管理
 */
@Service
public class CommonAttachService {
	
	@Autowired
	private MyBatisDao myBatisDao;
	
	/**
	 * 附件列表
	 * @param dto
	 * @return
	 */
	public List<CommonAttach> findListPage(Dto dto){
		return myBatisDao.getList("commonAttachMapper.findListPage",dto);
	}

	/**
	 * 附件列表---不分页
	 * @param map
	 * @return
	 */
	public List<CommonAttach> findList(Map<String,Object> map){
		return myBatisDao.getList("commonAttachMapper.findList",map);
	}
	/**
	 * 根据ID找附件
	 * @param attachid
	 * @return
	 */
	public CommonAttach findById(Long attachid){
		return myBatisDao.get("commonAttachMapper.findById",attachid);
	}
	
	/**
	 * 保存或者修改信息
	 * @return
	 */
	public String saveCommonAttach(CommonAttach commonAttach,HttpServletRequest request){
		String result = "0";//失败
		if (commonAttach.getAttachid()== null){//增加
				myBatisDao.save("commonAttachMapper.save",commonAttach);
				result = "1";
		}else{//修改
			myBatisDao.update("commonAttachMapper.update", commonAttach);
				result = "1";
		}
		return 	result;
	}
	
	/**
	 * 删除操作
	 * 
	 */
	public void del(Map<String,Object> map){
		myBatisDao.delete("commonAttachMapper.del",map);
	}
	
	/**
	 * 删除操作
	 * 
	 */
	public void delBId(Long attachid){
		myBatisDao.delete("commonAttachMapper.delBId",attachid);
	}
	

}
