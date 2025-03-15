AJS.$(document).ready(function(){
	
	JIRA.bind(JIRA.Events.NEW_CONTENT_ADDED, function(e, context, reason){
		
		var sousEtapeCokpitIDForAllInstances = [/* DEV */"15376", /* HOMOL */"15052"];
		sousEtapeCokpitIDForAllInstances.forEach(sousEtapeCokpitID => {
			var $rowID = `li#rowForcustomfield_${sousEtapeCokpitID}`;
			var $rowIDIssueDetails = `rowForcustomfield_${sousEtapeCokpitID}-issuedetails`;
			if($($rowID).length > 0){
				var $sousEtapeCokpit = $($rowID).clone();
				$sousEtapeCokpit.attr("id", $rowIDIssueDetails);
				$("ul#issuedetails").append($sousEtapeCokpit);
				$($rowID).hide();
			}
		});
	});
});