package com.tc.labt.sgabs.cokpit.core.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import lombok.Builder;
import lombok.Data;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Builder
@Data
public class CustomerPaginationDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@XmlElement
	public String codeClient;
/*	@XmlTransient
	public String prenomClient;
	@XmlTransient
	public String nomClient;
	@XmlTransient
	public String raisonSociale;
*/	@XmlElement
	public String displayName;
/*	@XmlTransient
	public String typeClient;
*/	public String libelleTypeClient;
	public String statutKYC;
	public String segmentClient;
	public String classificationRisque;
/*	@XmlTransient
	public String avisRCO;
*/	public String avisRCOFatca;
	public String flagPEP;
}
