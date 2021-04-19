package com.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "share")
public class Share {
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "UserID")
	private Integer Usid;

	@Column(name = "VideoID")
	private Integer Vdid;
	
	@Column(name = "Email")
	private String email;
	
	@Column(name = "ShareDate")
	private Date shareDate;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getShareDate() {
		return shareDate;
	}

	public void setShareDate(Date shareDate) {
		this.shareDate = shareDate;
	}
	
}
