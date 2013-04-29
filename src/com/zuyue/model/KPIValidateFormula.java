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
 * KPIValidateFormula entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="kpivalidateformula"
    ,catalog="kpi"
)

public class KPIValidateFormula  extends BaseModel implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 5468641872681946905L;
	private String formulaId;
     private KPIInfo KPIInfo;
     private String formula;
     private String kpiIds;
     private String tips;
     private Integer pointDigit;
     private String exceptionPass;


    // Constructors

    /** default constructor */
    public KPIValidateFormula() {
    }

	/** minimal constructor */
    public KPIValidateFormula(String formula) {
        this.formula = formula;
    }
    
    /** full constructor */
    public KPIValidateFormula(KPIInfo KPIInfo, String formula, String kpiIds, String tips, Integer pointDigit, String exceptionPass,Date createDate, Date updateDate) {
        this.KPIInfo = KPIInfo;
        this.formula = formula;
        this.kpiIds = kpiIds;
        this.tips = tips;
        this.pointDigit = pointDigit;
        this.exceptionPass = exceptionPass;
        
        super.setCreateDate(createDate);
		super.setUpdateDate(updateDate);
    }

   
    // Property accessors
    @GenericGenerator(name="generator", strategy="uuid.hex")@Id @GeneratedValue(generator="generator")
    
    @Column(name="FormulaId", unique=true, nullable=false, length=36)

    public String getFormulaId() {
        return this.formulaId;
    }
    
    public void setFormulaId(String formulaId) {
        this.formulaId = formulaId;
    }
	@ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="KpiId")

    public KPIInfo getKPIInfo() {
        return this.KPIInfo;
    }
    
    public void setKPIInfo(KPIInfo KPIInfo) {
        this.KPIInfo = KPIInfo;
    }
    
    @Column(name="Formula", nullable=false, length=200)

    public String getFormula() {
        return this.formula;
    }
    
    public void setFormula(String formula) {
        this.formula = formula;
    }
    
    @Column(name="KpiIds", length=500)

    public String getKpiIds() {
        return this.kpiIds;
    }
    
    public void setKpiIds(String kpiIds) {
        this.kpiIds = kpiIds;
    }
    
    @Column(name="Tips", length=200)

    public String getTips() {
        return this.tips;
    }
    
    public void setTips(String tips) {
        this.tips = tips;
    }
    
    @Column(name="PointDigit")

    public Integer getPointDigit() {
        return this.pointDigit;
    }
    
    public void setPointDigit(Integer pointDigit) {
        this.pointDigit = pointDigit;
    }
    
    @Column(name="ExceptionPass", length=1)

    public String getExceptionPass() {
        return this.exceptionPass;
    }
    
    public void setExceptionPass(String exceptionPass) {
        this.exceptionPass = exceptionPass;
    }
   








}