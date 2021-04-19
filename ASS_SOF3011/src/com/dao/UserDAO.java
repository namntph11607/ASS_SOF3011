package com.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

import com.entity.User;
import com.utils.HibernateUtils;

public class UserDAO {
	private Session hSession;

	public UserDAO() {
		this.hSession = HibernateUtils.getSession();
	}

	public User store(User entity) {

		this.hSession.beginTransaction();
		Integer id = (Integer) this.hSession.save(entity); // them moi vao DB
		this.hSession.getTransaction().commit();

		entity.setId(id);
		return entity;
	}

	public List<User> paginate(int offset, int limit) {
		String hql = "FROM User";
		Query query = this.hSession.createQuery(hql);
		query.setFirstResult(offset);
		query.setMaxResults(offset + limit);

		List<User> listUser = query.getResultList();

		return listUser;
	}

	public User findById(int id) {
		User entity = this.hSession.get(User.class, id);
		return entity;
	}
	
	public void update(User entity) {
		try {
			this.hSession.clear();
			this.hSession.beginTransaction();
			//cap nhat du lieu -> dua vao DB
			this.hSession.update(entity);
			//ghi du lieu vao DB
			this.hSession.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			//huy ket qua neu bi loi
			this.hSession.getTransaction().rollback();
		}
		
	}
	
	public void delete(User entity) {
		try {
			this.hSession.clear();
			this.hSession.beginTransaction();
			
			this.hSession.delete(entity);
			
			this.hSession.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			//huy ket qua neu bi loi
			this.hSession.getTransaction().rollback();
		}
	}

	public User login(String username, String password) {
		
		try {
			String hql = "SELECT objUser FROM User objUser WHERE username = :username AND password = :password";
			Query query = this.hSession.createQuery(hql);
			query.setParameter("username", username);
			query.setParameter("password", password);
			
			User entity = (User) query.getSingleResult();
			if(entity != null) {
				return entity;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public User checkUsername(String Username) {
		try {
			String hql = "SELECT objUser FROM User objUser WHERE username = :username";
			Query query = this.hSession.createQuery(hql);
			query.setParameter("username", Username);
			
			User results = (User) query.getSingleResult();
			
			if(results != null) {
				return results;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
}
