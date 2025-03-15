package com.tc.labt.sgabs.cokpit.core.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Builder
@Data
public class KycFileDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@XmlElement
	public String codeClient;
	@Default
	public String typeClient="CLIPRI";
	@XmlElement
	public String displayName;
	@XmlElement
	public String status;
	
	public String nomClient;
	public String prenomClient;
	public String nomJeuneFille;
	public String adresseDomicile;
	public String typeTelephonePrincipal;
	public String phonePrincipal;
	public String adresseEmail;
}
