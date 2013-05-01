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
 * KPIInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="kpiinfo"
    ,catalog="kpi"
)

public class KPIInfo extends BaseModel{


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 5024360808459791346L;
	private String kpiId;
     private String name;
     private String unit;
     private String aim;
     private String descrition;
     private String formulaDes;
     private String dimension;
     private String type;
     private Integer kpiOrder;
     private String definition;
     private String isRequiredNum;
     private Set<KPIHistoryCompareFormula> KPIHistoryCompareFormulas = new HashSet<KPIHistoryCompareFormula>(0);
     private Set<KPIValue> KPIValues = new HashSet<KPIValue>(0);
     private Set<OrgKPIRelation> orgKPIRelations = new HashSet<OrgKPIRelation>(0);
     private Set<KPIValidateFormula> KPIValidateFormulas = new HashSet<KPIValidateFormula>(0);


    // Constructors

    /** default constructor */
    public KPIInfo() {
    }

	/** minimal constructor */
    public KPIInfo(String name) {
        this.name = name;
    }
    
    /** full constructor */
    public KPIInfo(String name, String unit, String aim, String descrition, String formulaDes, String dimension, String type, Integer kpiOrder, Set<KPIHistoryCompareFormula> KPIHistoryCompareFormulas, Set<KPIValue> KPIValues, Set<OrgKPIRelation> orgKPIRelations, Set<KPIValidateFormula> KPIValidateFormulas,Date createDate, Date updateDate) {
        this.name = name;
        this.unit = unit;
        this.aim = aim;
        this.descrition = descrition;
        this.formulaDes = formulaDes;        
        this.dimension = dimension;
        this.type = type;
        this.kpiOrder = kpiOrder;
        this.KPIHistoryCompareFormulas = KPIHistoryCompareFormulas;
        this.KPIValues = KPIValues;
        this.orgKPIRelations = orgKPIRelations;
        this.KPIValidateFormulas = KPIValidateFormulas;
        
        super.setCreateDate(createDate);
        super.setUpdateDate(updateDate);
    }

   
    // Property accessors
    @GenericGenerator(name="generator", strategy="uuid.hex")@Id @GeneratedValue(generator="generator")
    
    @Column(name="kpiId", unique=true, nullable=false, length=36)

    public String getKpiId() {
        return this.kpiId;
    }
    
    public void setKpiId(String kpiId) {
        this.kpiId = kpiId;
    }
    
    @Column(name="name", nullable=false, length=100)

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Column(name="unit", length=10)

    public String getUnit() {
        return this.unit;
    }
    
    public void setUnit(String unit) {
        this.unit = unit;
    }
    
    @Column(name="aim", length=1000)

    public String getAim() {
        return this.aim;
    }
    
    public void setAim(String aim) {
        this.aim = aim;
    }
    
    @Column(name="descrition", length=1000)

    public String getDescrition() {
        return this.descrition;
    }
    
    public void setDescrition(String descrition) {
        this.descrition = descrition;
    }
    
    @Column(name="formulaDes", length=200)

    public String getFormulaDes() {
        return this.formulaDes;
    }
    
    public void setFormulaDes(String formulaDes) {
        this.formulaDes = formulaDes;
    }
    
    @Column(name="dimension", length=20)

    public String getDimension() {
        return this.dimension;
    }
    
    public void setDimension(String dimension) {
        this.dimension = dimension;
    }
    
    @Column(name="type", length=50)

    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    @Column(name="kpiOrder")

    public Integer getKpiOrder() {
        return this.kpiOrder;
    }
    
    public void setKpiOrder(Integer kpiOrder) {
        this.kpiOrder = kpiOrder;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="KPIInfo")

    public Set<KPIHistoryCompareFormula> getKPIHistoryCompareFormulas() {
        return this.KPIHistoryCompareFormulas;
    }
    
    public void setKPIHistoryCompareFormulas(Set<KPIHistoryCompareFormula> KPIHistoryCompareFormulas) {
        this.KPIHistoryCompareFormulas = KPIHistoryCompareFormulas;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="KPIInfo")

    public Set<KPIValue> getKPIValues() {
        return this.KPIValues;
    }
    
    public void setKPIValues(Set<KPIValue> KPIValues) {
        this.KPIValues = KPIValues;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="KPIInfo")

    public Set<OrgKPIRelation> getOrgKPIRelations() {
        return this.orgKPIRelations;
    }
    
    public void setOrgKPIRelations(Set<OrgKPIRelation> orgKPIRelations) {
        this.orgKPIRelations = orgKPIRelations;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="KPIInfo")

    public Set<KPIValidateFormula> getKPIValidateFormulas() {
        return this.KPIValidateFormulas;
    }
    
    public void setKPIValidateFormulas(Set<KPIValidateFormula> KPIValidateFormulas) {
        this.KPIValidateFormulas = KPIValidateFormulas;
    }


    public String getDefinition() {
        return definition;
    }
    @Column(name="definition", length=200)
    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getIsRequiredNum() {
        return isRequiredNum;
    }

    @Column(name="isRequiredNum",length =1)
    public void setIsRequiredNum(String isRequiredNum) {
        this.isRequiredNum = isRequiredNum;
    }
}