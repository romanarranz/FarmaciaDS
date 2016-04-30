$(".maincategory").on("click", function(){
	
	//llamada ajax para obtener las subcategorias de ese id
	
	var parentNode = $(this).parent().get(0);
	
	// se borran sus nodos hijos
	while (parentNode.firstChild) {
		parentNode.removeChild(parentNode.firstChild);
	}
	
	// se refresca añadiendo los nodos de las subcategorias
	
	// se crean las nuevas imagenes de las subcategorias
	var mainCategory = document.createElement('div');
	mainCategory.className = "col-md-12";
	mainCategory.style = "height: 300px; background-color: red; padding: 0;";
	parentNode.appendChild(mainCategory);
	
	var blockSubCategory = document.createElement('div');
	blockSubCategory.className = "col-md-12";
	blockSubCategory.style = "margin-top: 30px";	
	
	var subCategory;
	for(i = 0; i<4; i++){
		subCategory = document.createElement('div');
		subCategory.id = i;
		subCategory.className = "col-md-4 subcategory";
		subCategory.style = "height: 220px";
		subCategory.innerHTML = "<img class=\"img-thumbnail\" src=\"https://upload.wikimedia.org/wikipedia/commons/thumb/3/32/Selva_nublada_Colonia_Tovar.JPG/220px-Selva_nublada_Colonia_Tovar.JPG\" alt=\"\" style=\"height: 200px;\"/>";
		blockSubCategory.appendChild(subCategory);
	}	
	parentNode.appendChild(blockSubCategory);
	
});