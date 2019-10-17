package com.mip.software.admin.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mip.software.admin.dao.MyBatisDao;
import com.mip.software.admin.entity.BusinessTranslate;
import com.mip.software.admin.entity.SysUser;
import com.mip.software.common.util.Dto;

/**
 * 配送管理
 */
@Service
public class BusinessTranslateService {
	
	@Autowired
	private MyBatisDao myBatisDao;
	@Autowired
	private BusinessCustomerService businessCustomerService;
	/**
	 * 配送管理列表
	 * @param dto
	 * @return
	 */
	public List<BusinessTranslate> findListPage(Dto dto){
		return myBatisDao.getList("businessTranslateMapper.findListPage",dto);
	}

	/**
	 * 配送管理列表---不分页
	 * @param map
	 * @return
	 */
	public List<BusinessTranslate> findList(Map<String,Object> map){
		return myBatisDao.getList("businessTranslateMapper.findList",map);
	}
	/**
	 * 根据ID找配送管理
	 * @param translateid
	 * @return
	 */
	public BusinessTranslate findById(Long translateid){
		return myBatisDao.get("businessTranslateMapper.findById",translateid);
	}
	
	/**
	 * 保存或者修改信息
	 * @return
	 */
	public String saveBusinessTranslate(BusinessTranslate businessTranslate,HttpServletRequest request){
		String result = "0";//失败
		SysUser t=(SysUser)request.getSession().getAttribute("loginuser");
		if (businessTranslate.getTranslateid()== null){//增加
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			businessTranslate.setTransdate(dateFormat.format(new Date()));
			businessTranslate.setBranchshopid(t.getBranchshopid());
				myBatisDao.save("businessTranslateMapper.save",businessTranslate);
				result = "1";
		}else{//修改
			String menuStr=request.getParameter("menuStr");
			
			String sss="";
			String customers="";//客户汇总
			int j=0;
			if(!"".equals(menuStr)&&menuStr!=null){
				String str[]=menuStr.split(",");
				for(int i=0;i<str.length;i++){
					if(str[i].contains("_1")) {
						if("".equals(customers)){
							customers=str[i].split("_1")[0];
						}else{
							customers=customers+","+str[i].split("_1")[0];
						}
					}
					else{
						j++;
						if("".equals(sss)){
							sss=str[i].split("_2")[0];
						}else{
							sss=sss+","+str[i].split("_2")[0];
						}
						
						//需要更新掉所有其他配送单中有该调换单的数据
						Map<String,Object> map=new HashMap<String,Object>();
						map.put("branchshopid", t.getBranchshopid());
						map.put("translateid", str[i].split("_2")[0]);
						updateOther(map);
					}
				}
				System.out.println("1===================="+customers);
				customers=businessCustomerService.getDistinctCustomerList(customers);
				System.out.println("2===================="+customers);
				businessTranslate.setChanginfoids(sss);
				businessTranslate.setNochangeids(sss);
				businessTranslate.setTotalnum(j);
				businessTranslate.setNotfinish(j);
				businessTranslate.setCustomers(customers);
			}
			myBatisDao.update("businessTranslateMapper.update", businessTranslate);
				result = "1";
		}
		return 	result;
	}
	
	/**
	 * 删除操作
	 * 
	 */
	public void del(Long translateid){
		myBatisDao.delete("productSupplyMapper.del",translateid);
	}
	/**
	 * 更新掉其他配送单包含该调换id得数据
	 * 
	 */
	public void updateOther(Map<String,Object> map){
		myBatisDao.update("businessTranslateMapper.updateOther", map);
	}

}
