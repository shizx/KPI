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
 * KPIValue entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="kpivalue"
    ,catalog="kpi"
)

public class KPIValue  extends BaseModel implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = -6008020974231882829L;
	private String kpiValueId;
     private KPIInfo KPIInfo;
     private Double value;
     private Integer year;
     private String timeRange;


    // Constructors

    /** default constructor */
    public KPIValue() {
    }

	/** minimal constructor */
    public KPIValue(Double value) {
        this.value = value;
    }
    
    /** full constructor */
    public KPIValue(KPIInfo KPIInfo, Double value, Integer year, String timeRange,Date createDate, Date updateDate) {
        this.KPIInfo = KPIInfo;
        this.value = value;
        this.year = year;
        this.timeRange = timeRange;
        
        super.setCreateDate(createDate);
		super.setUpdateDate(updateDate);
    }

   
    // Property accessors
    @GenericGenerator(name="generator", strategy="uuid.hex")@Id @GeneratedValue(generator="generator")
    
    @Column(name="KpiValueId", unique=true, nullable=false, length=36)

    public String getKpiValueId() {
        return this.kpiValueId;
    }
    
    public void setKpiValueId(String kpiValueId) {
        this.kpiValueId = kpiValueId;
    }
	@ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="KpiId")

    public KPIInfo getKPIInfo() {
        return this.KPIInfo;
    }
    
    public void setKPIInfo(KPIInfo KPIInfo) {
        this.KPIInfo = KPIInfo;
    }
    
    @Column(name="Value", nullable=false, precision=22, scale=0)

    public Double getValue() {
        return this.value;
    }
    
    public void setValue(Double value) {
        this.value = value;
    }
    
    @Column(name="Year")

    public Integer getYear() {
        return this.year;
    }
    
    public void setYear(Integer year) {
        this.year = year;
    }
    
    @Column(name="TimeRange", length=1)

    public String getTimeRange() {
        return this.timeRange;
    }
    
    public void setTimeRange(String timeRange) {
        this.timeRange = timeRange;
    }
   








}