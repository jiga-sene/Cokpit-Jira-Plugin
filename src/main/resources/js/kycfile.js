function toggleSection(sectionNumber) {
	const sectionContent = document.getElementById('section' + sectionNumber);
	if(sectionContent.style.display==='none')
		sectionContent.style.display='block';
	else
		sectionContent.style.display='none';
}