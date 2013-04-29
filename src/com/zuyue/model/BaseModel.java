package com.zuyue.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public class BaseModel implements java.io.Serializable {
	private Date createDate = new Date();
	private Date updateDate = new Date();
		
	@Temporal(TemporalType.DATE)
    @Column(name="createDate", length=10)
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	@Temporal(TemporalType.DATE)
    @Column(name="updateDate", length=10)
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
}
