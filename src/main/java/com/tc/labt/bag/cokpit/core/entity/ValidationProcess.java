package com.tc.labt.sgabs.cokpit.core.entity;

import java.util.Date;

import javax.persistence.MappedSuperclass;

import lombok.Data;
import lombok.EqualsAndHashCode;

@MappedSuperclass
@Data
@EqualsAndHashCode(callSuper = true)
public class ValidationProcess extends HistorizationData{

	private String cc;
	private Date validationCC;
	private String commentaireCC;
	
	private String ra;
	private Date validationRA;
	private String commentaireRA;
	
	private String rco;
	private Date validationRCO;
	private String commentaireRCO;
	
	private String amlo;
	private Date validationAMLO;
	private String commentaireAMLO;
	
	private String dc;
	private Date validationDC;
	private String commentaireDC;
	
	private String mo;
	private Date validationMO;
	private String commentaireMO;
	
	private String cabco;
	private Date validationCABCO;
	private String commentaireCABCO;
	
	private String dg;
	private Date validationDG;
	private String commentaireDG;
	
	private String ctr;
	private Date validationCTR;
	private String commentaireCTR;
	
	private String bo;
	private Date validationBO;
	private String commentaireBO;
}
