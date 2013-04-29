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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;


/**
 * Role entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="role"
    ,catalog="kpi"
)

public class Role  extends BaseModel implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 7254828842149479533L;
	private String roleId;
     private String roleName;
     private String des;
     private Set<UserRole> userRoles = new HashSet<UserRole>(0);


    // Constructors

    /** default constructor */
    public Role() {
    }

	/** minimal constructor */
    public Role(String roleName) {
        this.roleName = roleName;
    }
    
    /** full constructor */
    public Role(String roleName, String des, Set<UserRole> userRoles,Date createDate, Date updateDate) {
        this.roleName = roleName;
        this.des = des;
        this.userRoles = userRoles;
        
        super.setCreateDate(createDate);
		super.setUpdateDate(updateDate);
    }

   
    // Property accessors
    @GenericGenerator(name="generator", strategy="uuid.hex")@Id @GeneratedValue(generator="generator")
    
    @Column(name="RoleId", unique=true, nullable=false, length=36)

    public String getRoleId() {
        return this.roleId;
    }
    
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
    
    @Column(name="RoleName", nullable=false, length=100)

    public String getRoleName() {
        return this.roleName;
    }
    
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    
    @Column(name="Des", length=500)

    public String getDes() {
        return this.des;
    }
    
    public void setDes(String des) {
        this.des = des;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="role")

    public Set<UserRole> getUserRoles() {
        return this.userRoles;
    }
    
    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }
   








}