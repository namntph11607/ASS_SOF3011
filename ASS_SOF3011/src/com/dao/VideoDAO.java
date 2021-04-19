package com.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

import com.entity.Video;
import com.utils.HibernateUtils;

public class VideoDAO {
	private Session hSession;
	
	public VideoDAO() {
		this.hSession = HibernateUtils.getSession();
	}
	
	public List<Video> getListDetail(){
		String hql = "FROM Video WHERE active = 1 ORDER BY views DESC";
		Query query = this.hSession.createQuery(hql);
		
		List<Video> listDetail = query.getResultList();

		return listDetail;
	}
	
	public List<Video> paginate(int offset, int limit) {
		String hql = "FROM Video";
		Query query = this.hSession.createQuery(hql);
		query.setFirstResult(offset);
		query.setMaxResults(offset + limit);

		List<Video> listVideo = query.getResultList();

		return listVideo;
	}
	
	public List<Video> paginateTitle(int offset, int limit){
		String hql = "FROM Video WHERE active = 1 ORDER BY views DESC";
		Query query = this.hSession.createQuery(hql);
		query.setFirstResult(offset);
		query.setMaxResults(offset + limit);

		List<Video> listHome = query.getResultList();

		return listHome;
	}

	public Video findById(int id) {
		Video entity = this.hSession.get(Video.class, id);
		return entity;
	}
	
	public Video findByTitle(String title) {
		Video entity = this.hSession.get(Video.class, title);
		return entity;
	}
	
	public Video store(Video entity) {

		this.hSession.beginTransaction();
		Integer id = (Integer) this.hSession.save(entity); // them moi vao DB
		this.hSession.getTransaction().commit();

		entity.setId(id);
		return entity;
	}
	
	public void update(Video entity) {
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
	
	public void delete(Video entity) {
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
}
