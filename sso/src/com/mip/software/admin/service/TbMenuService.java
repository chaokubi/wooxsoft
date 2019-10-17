package com.mip.software.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mip.software.admin.dao.MyBatisDao;
import com.mip.software.admin.entity.SysMenu;
import com.mip.software.common.util.Dto;





/**
 * 
  * @ClassName: TbMenuService
  * @Description: 菜单
  * @author mip
  * @date 2016年2月16日 下午5:37:36
  *
 */
@Service
public class TbMenuService {
	
	@Autowired
	private MyBatisDao myBatisDao;
	
	
	public List<SysMenu> findList(Dto dto){
		return myBatisDao.getList("tbMenuMapper.findList", dto);
	}
	
	public void save(SysMenu tbMenu){
		myBatisDao.save("tbMenuMapper.insert",tbMenu);
	}
	
	public void update(SysMenu tbMenu){
		myBatisDao.update("tbMenuMapper.update",tbMenu);
	}
	
	public void del(Long id){
		myBatisDao.delete("tbMenuMapper.del",id);
	}
	
	public List<SysMenu> findUserRoleMenuList(Long userId){
		return myBatisDao.getList("tbMenuMapper.findUserRoleMenuList",userId);
	}
}
