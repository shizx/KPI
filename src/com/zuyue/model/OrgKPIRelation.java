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
 * OrgKPIRelation entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="orgkpirelation"
    ,catalog="kpi"
)

public class OrgKPIRelation  extends BaseModel implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = -52539626700171257L;
	private String relationId;
     private OrgInfo orgInfo;
     private KPIInfo KPIInfo;


    // Constructors

    /** default constructor */
    public OrgKPIRelation() {
    }

	/** minimal constructor */
    public OrgKPIRelation(OrgInfo orgInfo) {
        this.orgInfo = orgInfo;
    }
    
    /** full constructor */
    public OrgKPIRelation(OrgInfo orgInfo, KPIInfo KPIInfo,Date createDate, Date updateDate) {
        this.orgInfo = orgInfo;
        this.KPIInfo = KPIInfo;
        
        super.setCreateDate(createDate);
		super.setUpdateDate(updateDate);
    }

   
    // Property accessors
    @GenericGenerator(name="generator", strategy="uuid.hex")@Id @GeneratedValue(generator="generator")
    
    @Column(name="relationId", unique=true, nullable=false, length=36)

    public String getRelationId() {
        return this.relationId;
    }
    
    public void setRelationId(String relationId) {
        this.relationId = relationId;
    }
	@ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="orgId", nullable=false)

    public OrgInfo getOrgInfo() {
        return this.orgInfo;
    }
    
    public void setOrgInfo(OrgInfo orgInfo) {
        this.orgInfo = orgInfo;
    }
	@ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="kpiId")

    public KPIInfo getKPIInfo() {
        return this.KPIInfo;
    }
    
    public void setKPIInfo(KPIInfo KPIInfo) {
        this.KPIInfo = KPIInfo;
    }
   








}