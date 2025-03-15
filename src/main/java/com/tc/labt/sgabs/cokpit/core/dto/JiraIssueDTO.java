package com.tc.labt.sgabs.cokpit.core.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor
@Data
@Builder
public class JiraIssueDTO implements Serializable{

	/**
	 * 
	 */
	public static final long serialVersionUID = 1L;
	
	public long issueid;
	public String issuekey;
	public String codeClient;
	public String subsidiary;
	
	public String summary;
	public String creationType;
	public String workflowType;
	
	public Date created;
	
	public Date lastModified;
	public Date lastValidation;
	public Date historizationDate;
	
	// Historization
	public String refClassificationRisque;
	public String classificationRisque;
	public String avisRCOFatca;
	public String avisRCOCRS;
	public String flagABC;
	public String flagPEP;
	
	// Process Validation
	public String cc;
	public Date validationCC;
	public String commentaireCC;
	
	public String ra;
	public Date validationRA;
	public String commentaireRA;
	
	public String rco;
	public Date validationRCO;
	public String commentaireRCO;
	
	public String amlo;
	public Date validationAMLO;
	public String commentaireAMLO;
	
	public String dc;
	public Date validationDC;
	public String commentaireDC;
	
	public String mo;
	public Date validationMO;
	public String commentaireMO;
	
	public String cabco;
	public Date validationCABCO;
	public String commentaireCABCO;
	
	public String dg;
	public Date validationDG;
	public String commentaireDG;
	
	public String ctr;
	public Date validationCTR;
	public String commentaireCTR;
	
	public String bo;
	public Date validationBO;
	public String commentaireBO;
}
