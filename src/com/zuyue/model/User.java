package com.zuyue.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;


/**
 * User entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="user"
    ,catalog="kpi"
)

public class User  extends BaseModel implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 3902483064037042024L;
	private String userId;
     private OrgInfo orgInfo;
     private String userName;
     private String password;
     private String email;
     private String mobile;
     private String qq;
     private Set<UserRole> userRoles = new HashSet<UserRole>(0);


    // Constructors

    /** default constructor */
    public User() {
    }

	/** minimal constructor */
    public User(String userName) {
        this.userName = userName;
    }
    
    /** full constructor */
    public User(OrgInfo orgInfo, String userName, String password, String email, String mobile, String qq, Set<UserRole> userRoles,Date createDate, Date updateDate) {
        this.orgInfo = orgInfo;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.mobile = mobile;
        this.qq = qq;
        this.userRoles = userRoles;
        super.setCreateDate(createDate);
		super.setUpdateDate(updateDate);
    }

   
    // Property accessors
    @GenericGenerator(name="generator", strategy="uuid.hex")@Id @GeneratedValue(generator="generator")
    
    @Column(name="userId", unique=true, nullable=false, length=36)

    public String getUserId() {
        return this.userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
	@ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="OrgId")

    public OrgInfo getOrgInfo() {
        return this.orgInfo;
    }
    
    public void setOrgInfo(OrgInfo orgInfo) {
        this.orgInfo = orgInfo;
    }
    
    @Column(name="UserName", nullable=false, length=100)

    public String getUserName() {
        return this.userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    @Column(name="Password", length=100)

    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    @Column(name="Email", length=100)

    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    @Column(name="Mobile", length=20)

    public String getMobile() {
        return this.mobile;
    }
    
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    
    @Column(name="QQ", length=15)

    public String getQq() {
        return this.qq;
    }
    
    public void setQq(String qq) {
        this.qq = qq;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="user")

    public Set<UserRole> getUserRoles() {
        return this.userRoles;
    }
    
    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }
   








}