package com.tc.labt.sgabs.cokpit.core.dto.handler;

import java.util.List;
import java.util.stream.Collectors;

import com.tc.labt.sgabs.cokpit.core.dto.JiraIssueDTO;
import com.tc.labt.sgabs.cokpit.core.entity.JiraIssue;

public class JiraIssueHandler {
	
	public static List<JiraIssueDTO> retrieveJiraIssues(List<JiraIssue> jIssues){
	
		return jIssues.parallelStream().map(jIssue -> 
			JiraIssueDTO.builder()
				.codeClient(jIssue.getCodeClient())
				.created(jIssue.getCreated())
				.creationType(jIssue.getCreationType())
				.workflowType(jIssue.getWorkflowType())
				.issueid(jIssue.getIssueid())
				.issuekey(jIssue.getIssuekey())
				.lastModified(jIssue.getLastModified())
				.lastValidation(jIssue.getLastValidation())
				.summary(jIssue.getSummary())
				.historizationDate(jIssue.getHistorizationDate())

				.refClassificationRisque(jIssue.getRefClassificationRisque())
				.classificationRisque(jIssue.getClassificationRisque())
				.avisRCOFatca(jIssue.getAvisRCOFatca())
				.avisRCOCRS(jIssue.getAvisRCOCRS())
				.flagABC(jIssue.getFlagABC())
				.flagPEP(jIssue.getFlagPEP())
				
				.cc(jIssue.getCc())
				.validationCC(jIssue.getValidationCC())
				.commentaireCC(jIssue.getCommentaireCC())
				.ra(jIssue.getRa())
				.validationRA(jIssue.getValidationRA())
				.commentaireRA(jIssue.getCommentaireRA())
				.rco(jIssue.getRco())
				.validationRCO(jIssue.getValidationRCO())
				.commentaireRCO(jIssue.getCommentaireRCO())
				.amlo(jIssue.getAmlo())
				.validationAMLO(jIssue.getValidationAMLO())
				.commentaireAMLO(jIssue.getCommentaireAMLO())
				.dc(jIssue.getDc())
				.validationDC(jIssue.getValidationDC())
				.commentaireDC(jIssue.getCommentaireDC())
				.mo(jIssue.getMo())
				.validationMO(jIssue.getValidationMO())
				.commentaireMO(jIssue.getCommentaireMO())
				.cabco(jIssue.getCabco())
				.validationCABCO(jIssue.getValidationCABCO())
				.commentaireCABCO(jIssue.getCommentaireCABCO())
				.dg(jIssue.getDg())
				.validationDG(jIssue.getValidationDG())
				.commentaireDG(jIssue.getCommentaireDG())
				.ctr(jIssue.getCtr())
				.validationCTR(jIssue.getValidationCTR())
				.commentaireCTR(jIssue.getCommentaireCTR())
				.bo(jIssue.getBo())
				.validationBO(jIssue.getValidationBO())
				.commentaireBO(jIssue.getCommentaireBO())
				.build()
				).collect(Collectors.toList());
	}
}
