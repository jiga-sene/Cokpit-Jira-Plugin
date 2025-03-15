package com.tc.labt.sgabs.cokpit.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.google.common.base.Strings;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "jiraissue")
@NoArgsConstructor @AllArgsConstructor
@Data @Builder
@EqualsAndHashCode(callSuper = true)
public class JiraIssue extends ValidationProcess implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4493800408681871301L;

	@Id
	private long issueid;
	@Column(nullable = false, unique = true)
	private String issuekey;
	@Column(nullable = false, updatable = false)
	private String codeClient;
	@Column(nullable = false, updatable = false)
	private String subsidiary;
	
	private String summary;
	private String creationType;
	private String workflowType;
	
	@Column(updatable = false)
	private Date created;
	
	private Date lastModified;
	private Date lastValidation;
	private Date historizationDate;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name = "customer")
	private Customer customer;
	
	public final String getProjectKey(String issuekey) {
		return Strings.isNullOrEmpty(issuekey)? null : issuekey.split("-")[0];
	}
}