package com.mip.software.admin.service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mip.software.admin.dao.MyBatisDao;
import com.mip.software.admin.entity.Tblanguage;
import com.mip.software.common.util.Dto;
import com.mip.software.common.util.ProjectUtils;

@Service
public class TbLanguageService {
	
	private static AtomicInteger atomicInteger = new AtomicInteger();
	
	@Autowired
	private MyBatisDao myBatisDao;
	
	/**
	 * 列表分页
	 * @param dto
	 * @return
	 */
	public List<Tblanguage> findListPage(Dto dto){
		return myBatisDao.getList("tbLanguageMapper.findListPage",dto);
	}
	
	public void saveOrUpdate(Tblanguage tbNetpoint){
		if(null!=tbNetpoint.getId()){
			myBatisDao.update("tbLanguageMapper.update",tbNetpoint);
		}else {
			tbNetpoint.setCode(this.generateAtomicId());
			myBatisDao.save("tbLanguageMapper.save",tbNetpoint);
		}
	}
	
	public Tblanguage findById(Long id){
		return myBatisDao.get("tbLanguageMapper.findById",id);
	}
	
	/**
	 * 查询名称是否重复
	 */
	public Integer languageIsExist(String name) {
		return myBatisDao.get("tbLanguageMapper.languageIsExist",name);
	}
	
	/**
	 * 删除网点(逻辑删除)
	 */
	public void updateDelStatus(String ids){
		List<Long> list=ProjectUtils.getLongList(ids,",");
		for (Long long1 : list) {
			myBatisDao.update("tbLanguageMapper.updateDelStatus",long1);
		}
	}
	
	/**
	 * @return 生成的原子id
	 */
	public String generateAtomicId() {
		String orderId = ProjectUtils.getDateString() 
				+ String.format("%06d", atomicInteger.getAndIncrement() % 100000);
		return orderId;
	}
}
