package com.tc.labt.sgabs.cokpit.core.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "account")
@NoArgsConstructor @AllArgsConstructor
@Data @Builder
public class Account implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1262999781827919471L;
	
	@Id
	private String numeroCompte;
	
	private String typeCompte;
	private String libelleTypeCompte;
	private String dateOuverture;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	//@LazyCollection(LazyCollectionOption.FALSE)
	private Customer customer;
}
