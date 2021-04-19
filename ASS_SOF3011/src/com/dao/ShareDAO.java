package com.dao;

import org.hibernate.Session;

import com.entity.Share;
import com.utils.HibernateUtils;

public class ShareDAO {
	private Session hSession;
	
	public ShareDAO() {
		this.hSession = HibernateUtils.getSession();
	}
	
	public Share insert(Share entity) {

		this.hSession.beginTransaction();
		Integer id = (Integer) this.hSession.save(entity); // them moi vao DB
		this.hSession.getTransaction().commit();

		entity.setId(id);
		return entity;
	}
}
