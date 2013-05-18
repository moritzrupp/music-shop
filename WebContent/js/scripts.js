function resetForm(oForm) {
	
	for (var i = 0; i < oForm.length; i++) { 
	
		oForm.elements[i].value = "";
	}
	
	document.location = "index.jsp";
}