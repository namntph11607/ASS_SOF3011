package com.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "favorite")
public class Favorite {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "UserID")
	private Integer Usid;
	
	@Column(name = "VideoID")
	private Integer Vdid;
	
	@Column(name = "LikeDate")
	@Temporal(TemporalType.DATE)
	private Date likeDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUsid() {
		return Usid;
	}

	public void setUsid(Integer usid) {
		Usid = usid;
	}

	public Integer getVdid() {
		return Vdid;
	}

	public void setVdid(Integer vdid) {
		Vdid = vdid;
	}

	public Date getLikeDate() {
		return likeDate;
	}

	public void setLikeDate(Date likeDate) {
		this.likeDate = likeDate;
	}

	@Override
	public String toString() {
		return "Favorite [id=" + id + ", Usid=" + Usid + ", Vdid=" + Vdid + ", likeDate=" + likeDate + "]";
	}
	
}
