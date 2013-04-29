package com.zuyue.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;


/**
 * UserRole entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="userrole"
    ,catalog="kpi"
)

public class UserRole extends BaseModel implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = -6028931862154704014L;
	private String userRoleId;
     private User user;
     private Role role;


    // Constructors

    /** default constructor */
    public UserRole() {
    }

	/** minimal constructor */
    public UserRole(Role role) {
        this.role = role;
    }
    
    /** full constructor */
    public UserRole(User user, Role role,Date createDate, Date updateDate) {
        this.user = user;
        this.role = role;
        
        super.setCreateDate(createDate);
		super.setUpdateDate(updateDate);
    }

   
    // Property accessors
    @GenericGenerator(name="generator", strategy="uuid.hex")@Id @GeneratedValue(generator="generator")
    
    @Column(name="userRoleId", unique=true, nullable=false, length=36)

    public String getUserRoleId() {
        return this.userRoleId;
    }
    
    public void setUserRoleId(String userRoleId) {
        this.userRoleId = userRoleId;
    }
	@ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="UserId")

    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
	@ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="RoleId", nullable=false)

    public Role getRole() {
        return this.role;
    }
    
    public void setRole(Role role) {
        this.role = role;
    }
   








}