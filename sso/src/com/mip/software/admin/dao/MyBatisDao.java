package com.mip.software.admin.dao;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

public class MyBatisDao extends SqlSessionDaoSupport {

	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory ) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	public int save(String key, Object object) {
		return getSqlSession().insert(key, object);
	}
	
	public void update(String key, Object object) {
		getSqlSession().update(key, object);
	}
	
	public void delete(String key, Serializable id) {
		getSqlSession().delete(key, id);
	}
	
	public int deleteWithReturnCount(String key, Serializable id) {
		return getSqlSession().delete(key, id);
	}

	public void delete(String key, Object object) {
		getSqlSession().delete(key, object);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T get(String key, Object params) {
		return (T) getSqlSession().selectOne(key, params);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T get(String key) {
		return (T) getSqlSession().selectOne(key);
	}
	
	public <T> List<T> getList(String key) {
		return getSqlSession().selectList(key);
	}
	
	public <T> List<T> getList(String key, Object params) {
		return getSqlSession().selectList(key, params);
	}
	
}
