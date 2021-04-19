package com.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

import com.entity.Favorite;
import com.entity.User;
import com.entity.Video;
import com.utils.HibernateUtils;

public class FavoriteDAO {
	private Session hSession;
	
	public FavoriteDAO() {
		this.hSession = HibernateUtils.getSession();
	}
	
	public List<Video> getListFavorite(int userid){
		
		String hql = "SELECT objVid FROM Favorite objFvr, Video objVid"
				+ " WHERE objFvr.Vdid = objVid.id AND objFvr.Usid = :UserID";
		Query query = this.hSession.createQuery(hql);
		query.setParameter("UserID", userid);
		
		List<Video> listFvr = query.getResultList();

		return listFvr;
	}
	
	public List<Favorite> fillAll(){
		String hql = "FROM Favorite";
		Query query = this.hSession.createQuery(hql);
		
		return query.getResultList();
	}
	
	public Favorite findByID(int id) {
		Favorite entity = this.hSession.get(Favorite.class, id);
		return entity;
	}
	
	public void saveLike(int UserID, int VideoID) {
		Favorite favo = new Favorite();
		User u = this.hSession.find(User.class, UserID);
		Video v = this.hSession.find(Video.class, VideoID);
		favo.setUsid(u.getId());
		favo.setVdid(v.getId());
		favo.setLikeDate(new Date());
		
		try {
			this.hSession.beginTransaction();
			this.hSession.save(favo);
			this.hSession.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			this.hSession.getTransaction().rollback();
		}
	}
	
	public Favorite checkFavorite(int vidID, int userID) {
		try {
			String hql = "SELECT objFvr FROM Favorite objFvr WHERE Vdid = :videoid AND Usid = :userid";
			Query query = this.hSession.createQuery(hql);
			query.setParameter("videoid", vidID);
			query.setParameter("userid", userID);
			
			Favorite entity = (Favorite) query.getSingleResult();
			if(entity != null) {
				return entity;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void UnLike(int vidID, int userID) {
		try {
			this.hSession.clear();
			this.hSession.beginTransaction();
			String hql = "SELECT objFvr FROM Favorite objFvr WHERE Vdid = :videoid AND Usid = :userid";
			Query query = this.hSession.createQuery(hql);
			query.setParameter("videoid", vidID);
			query.setParameter("userid", userID);
			
			List<Favorite> list = query.getResultList();
			
			this.hSession.delete(list.get(0));
			
			this.hSession.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			//huy ket qua neu bi loi
			this.hSession.getTransaction().rollback();
		}
	}
	
}
