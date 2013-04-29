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
 * OrgInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "orginfo", catalog = "kpi")
public class OrgInfo extends BaseModel implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -3120782395687255851L;
	private String orgId;
	private String orgName;
	private Integer seqNo;
	private String imageUrl;
	private String orgPath;
	private OrgInfo parentOrgInfo;
	private Set<User> users = new HashSet<User>(0);
	private Set<OrgKPIRelation> orgKPIRelations = new HashSet<OrgKPIRelation>(0);

	// Constructors

	/** default constructor */
	public OrgInfo() {
	}

	/** minimal constructor */
	public OrgInfo(String orgName) {
		this.orgName = orgName;
	}

	/** full constructor */
	public OrgInfo(String orgName, Integer seqNo, String imageUrl,
			OrgInfo parentOrgInfo, String orgPath, Set<User> users,
			Set<OrgKPIRelation> orgKPIRelations,Date createDate,Date updateDate) {
		this.orgName = orgName;
		this.seqNo = seqNo;
		this.imageUrl = imageUrl;
		this.parentOrgInfo = parentOrgInfo;
		this.orgPath = orgPath;
		this.users = users;
		this.orgKPIRelations = orgKPIRelations;
		
		super.setCreateDate(createDate);
		super.setUpdateDate(updateDate);
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "orgId", unique = true, nullable = false, length = 36)
	public String getOrgId() {
		return this.orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	@Column(name = "orgName", nullable = false, length = 100)
	public String getOrgName() {
		return this.orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	@Column(name = "seqNo")
	public Integer getSeqNo() {
		return this.seqNo;
	}

	public void setSeqNo(Integer seqNo) {
		this.seqNo = seqNo;
	}

	@Column(name = "imageUrl", length = 100)
	public String getImageUrl() {
		return this.imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	@Column(name = "orgPath", length = 500)
	public String getOrgPath() {
		return this.orgPath;
	}

	public void setOrgPath(String orgPath) {
		this.orgPath = orgPath;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "orgInfo")
	public Set<User> getUsers() {
		return this.users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "orgInfo")
	public Set<OrgKPIRelation> getOrgKPIRelations() {
		return this.orgKPIRelations;
	}

	public void setOrgKPIRelations(Set<OrgKPIRelation> orgKPIRelations) {
		this.orgKPIRelations = orgKPIRelations;
	}
	
	public void setParentOrgInfo(OrgInfo parentOrgInfo) {
		this.parentOrgInfo = parentOrgInfo;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parentOrgId")
	public OrgInfo getParentOrgInfo() {
		return parentOrgInfo;
	}

}