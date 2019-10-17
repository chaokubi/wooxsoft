package com.mip.software.admin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mip.software.admin.dao.MyBatisDao;
import com.mip.software.admin.entity.SysUser;
import com.mip.software.admin.entity.SysUserRole;
import com.mip.software.common.util.AESEnDecoder;
import com.mip.software.common.util.Dto;

/**
 * 用户
 */
@Service
public class SysUserService {
	
	@Autowired
	private MyBatisDao myBatisDao;
	@Autowired
	private SysRoleService tbRoleService;
	public SysUser login(SysUser sysUser){
		if(sysUser.getUser_pass() != null) {
			sysUser.setUser_pass(AESEnDecoder.encrypt(sysUser.getUser_pass(), "HJ"));
		}
		
		return myBatisDao.get("sysUserMapper.login",sysUser);
	}
	
	
	public SysUser login2(SysUser sysUser){
		return myBatisDao.get("sysUserMapper.login2",sysUser);
	}
	/**
	 * 用户列表---不分页
	 * @param map
	 * @return
	 */
	public List<SysUser> findList(Map<String,Object> map){
		return myBatisDao.getList("sysUserMapper.findList",map);
	}
	
	/**
	 * 更新最后一次登录时间和IP
	 * @param map
	 */
	public void updateLastLogin(Map<String,Object> map){
		myBatisDao.update("sysUserMapper.updateLastLogin",map);
	}
	
	public List<SysUser> findListPage(Dto dto){
		return myBatisDao.getList("sysUserMapper.findListPage",dto);
	}
	
	public void save(SysUser sysUser){
		myBatisDao.save("sysUserMapper.save",sysUser);
		insertUserRole(sysUser);
	}
	
	public void update(SysUser sysUser){
		myBatisDao.save("sysUserMapper.update",sysUser);
		insertUserRole(sysUser);
	}
	
	public void insertUserRole(SysUser sysUser){
		if(null!=sysUser.getRole()){
			Long[] roleLongs=sysUser.getRole();
			List<SysUserRole> userRoles=new ArrayList<SysUserRole>();
			SysUserRole tbUserRole=null;
			for (Long long1 : roleLongs) {
				tbUserRole=new SysUserRole();
				tbUserRole.setUserid(sysUser.getUserid());
				tbUserRole.setRoleid(long1);
				userRoles.add(tbUserRole);
			}
			if(userRoles.size()>0){
				//先删除,再保存用户角色
				tbRoleService.delUserRole(sysUser.getUserid());
				tbRoleService.insertUserRole(userRoles);
			}
		}
	}
	
	public SysUser findById(Long userid){
		return myBatisDao.get("sysUserMapper.findById",userid);
	}
	
	/**
	 * 查询登录名是否重复
	 */
	public Integer userLoginIsExist(String username) {
		return myBatisDao.get("sysUserMapper.userLoginIsExist",username);
	}
	
	/**
	 * 更新用户状态
	 * 参数 map 包含status( 0：禁用； 1：启用 ) id（用户ID）
	 */
	public void updateUserStatus(Map<String,Object> map){
		myBatisDao.update("sysUserMapper.updateUserStatus",map);
	}
	
	/**
	 * 修改密码
	 */
	public void updatePsd(SysUser sysUser){
		myBatisDao.update("sysUserMapper.updatePsd",sysUser);
	}
	
	public List<SysUser> findLoginListPage(Dto dto){
		return myBatisDao.getList("sysUserMapper.findLoginListPage",dto);
	}
	
	
	/**
	 * 用户列表---推送用户
	 * @param map
	 * @return
	 */
	public List<SysUser> findTsInfo(Map<String,Object> map){
		return myBatisDao.getList("sysUserMapper.findTsInfo",map);
	}
	
}
