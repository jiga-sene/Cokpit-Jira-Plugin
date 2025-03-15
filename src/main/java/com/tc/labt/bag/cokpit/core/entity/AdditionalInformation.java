package com.tc.labt.sgabs.cokpit.core.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Builder;
import lombok.Data;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "additionalinformation")
@Data @Builder
public class AdditionalInformation implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4935295455183549314L;

	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	private String label;
	private String value;
	@Lob
	private String textValue;
}
