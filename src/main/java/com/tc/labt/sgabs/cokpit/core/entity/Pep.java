package com.tc.labt.sgabs.cokpit.core.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "pep")
@NoArgsConstructor @AllArgsConstructor
@Data @Builder 
public class Pep implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8011765988794011334L;
	
	@Id
	private String codeClient;
	
	private String identifiantTiers;
	private String referenceFonction;
	private String libelleFonction;
	private String dateFinStatut;
	private String referenceTypeLien;
	private String libelleTypeLien;
	private String nom;
	private String prenom;
	private String dateNaissance;
	private String lieuNaissance;
	private String categorie;
}
