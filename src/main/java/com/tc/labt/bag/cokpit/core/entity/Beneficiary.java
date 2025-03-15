package com.tc.labt.sgabs.cokpit.core.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "beneficiary")
@NoArgsConstructor @AllArgsConstructor
@Data @Builder
@EqualsAndHashCode(callSuper = true)
public class Beneficiary extends ThirdPartyCustomer implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8606184791105192115L;
	
	private String recueilJustificatifsIdentite;
	private String capitalOrDroitDeVote;
	private String descriptionRelationBeneficiaireClient;
}
