$('#edit').on('show.bs.modal', function (event) {
	var pencil = $(event.relatedTarget); 	// Button that triggered the modal
	var tr = pencil.parent().parent();
	var childrens = tr.find('*');
  
	// get data of the selected row
	var id = childrens[0].innerHTML;
	var name = childrens[1].innerHTML;
	var laboratory = childrens[2].innerHTML;
 
	// update #edit inputs values
	var modal = $(this)
	modal.find('#editId').val(id);
	modal.find('#editName').val(name);
	modal.find('#editLaboratory').val(laboratory);
});

$('#delete').on('show.bs.modal', function (event) {
	var trash = $(event.relatedTarget); 	// Button that triggered the modal
	var tr = trash.parent().parent();
	var childrens = tr.find('*');
	  
	// get data of the selected row
	var id = childrens[0].innerHTML;
	 
	// update #edit inputs values
	var modal = $(this)
	modal.find('#deleteId').val(id);
});