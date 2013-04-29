package com.zuyue.model;

// default package

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
 * KPIHistoryCompareFormula entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="kpihistorycompareformula"
    ,catalog="kpi"
)

public class KPIHistoryCompareFormula  extends BaseModel implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = -3062304908182974916L;
	private String formulaId;
     private KPIInfo KPIInfo;
     private String formula;
     private String kpiYears;
     private String tips;
     private String symbol;
     private String threshod;
     private Integer pointDigit;
     private String exceptionPass;


    // Constructors

    /** default constructor */
    public KPIHistoryCompareFormula() {
    }

	/** minimal constructor */
    public KPIHistoryCompareFormula(String formula) {
        this.formula = formula;
    }
    
    /** full constructor */
    public KPIHistoryCompareFormula(KPIInfo KPIInfo, String formula, String kpiYears, String tips, String symbol, String threshod, Integer pointDigit, String exceptionPass,Date createDate, Date updateDate) {
        this.KPIInfo = KPIInfo;
        this.formula = formula;
        this.kpiYears = kpiYears;
        this.tips = tips;
        this.symbol = symbol;
        this.threshod = threshod;
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
    
    @Column(name="KpiYears", length=500)

    public String getKpiYears() {
        return this.kpiYears;
    }
    
    public void setKpiYears(String kpiYears) {
        this.kpiYears = kpiYears;
    }
    
    @Column(name="Tips", length=200)

    public String getTips() {
        return this.tips;
    }
    
    public void setTips(String tips) {
        this.tips = tips;
    }
    
    @Column(name="Symbol", length=5)

    public String getSymbol() {
        return this.symbol;
    }
    
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
    
    @Column(name="Threshod", length=10)

    public String getThreshod() {
        return this.threshod;
    }
    
    public void setThreshod(String threshod) {
        this.threshod = threshod;
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