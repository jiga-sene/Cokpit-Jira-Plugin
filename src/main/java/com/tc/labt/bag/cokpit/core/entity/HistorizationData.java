package com.tc.labt.sgabs.cokpit.core.entity;

import javax.persistence.MappedSuperclass;

import lombok.Data;

@MappedSuperclass
@Data
public class HistorizationData {
	
	// ### Historization Data
	private String refClassificationRisque;
	private String classificationRisque;
	private String avisRCOFatca;
	private String avisRCOCRS;
	private String flagABC;
	private String flagPEP;
}
