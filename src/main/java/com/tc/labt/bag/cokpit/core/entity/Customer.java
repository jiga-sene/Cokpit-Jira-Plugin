package com.tc.labt.sgabs.cokpit.core.entity;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "customer")
@NoArgsConstructor @AllArgsConstructor
@Data @Builder 
@EqualsAndHashCode(callSuper = false)
public class Customer extends HistorizationData implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 738345190235960712L;
	
	@Id
	private String codeClient;
	
	private String nomClient;
	private String prenomClient;
	private String displayName;
	private String dateNaissance;
	private String villeNaissance;
	private String paysNaissance;
	private String nationalite;

	private String segmentClient;
	private String referenceTypeClient;
	private String libelleTypeClient;
	private String libellePieceIdentite;
	private String pieceIdentite;
	private String paysResidence;
	
	private String raisonSociale;
	private String referenceFormeJuridique;
	private String libelleFormeJuridique;
	private String lieuDelivranceRegistreCommerce;
	private String numeroEnregistrement;
	private String dateDeCreation;
	
	private String adresseDomicile;
	private String typePhonePrincipal;
	private String phonePrincipale;
	private String adresseEmail;
	
	private String profession;
	
	private String statutKYC;
	private String sousStatutKYC;
	
	private String statutControle;
	private Date validationKYC;
	private Date validiteKYC;
	private Date lastValidation;
	
	//Contrôles
	private String resultatControleEmbargo;
	private String informationsNegativesClient;
	private String indicateurClientPPE;
	private String resultatControlePPE;
	private String degradationRisque;
	private String raisonDegradation;
	private String statutFATCA;
	private String dateStatutFATCA;
	private String statutCRS;
	private String negativeNewsCorruption;
	private String infoNegative;
	private String sanctionIncident;
	private String clientSousEmbargo;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name="pep")
	private Pep pep;
    
    @ManyToMany(cascade = CascadeType.REFRESH)
    @LazyCollection(LazyCollectionOption.FALSE)
    List<AdditionalInformation> additionalInformations;
    
}
