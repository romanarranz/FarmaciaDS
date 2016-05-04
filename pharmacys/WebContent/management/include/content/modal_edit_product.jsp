<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- Edit Products Modal -->
<div id="edit" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="gridSystemModalLabel">
	<div class="modal-dialog" role="document">
    <div class="modal-content">
    	
    	<!-- Modal Header -->
    	<div class="modal-header">
        	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        	<h4 class="modal-title">Edit Product</h4>
      	</div>
      	
      	<!-- Form Edit Parameters --> 
      	<form method="post" action="http://localhost:8080/pharmacys/product" role="form" enctype="multipart/form-data">
      	<input type="hidden" name="editId" id="editId" />
      	<input type="hidden" name="editQueryCount" id="editQueryCount" />
      	
      	<!-- Modal Body -->
      	<div class="modal-body">
        	<div class="row">		         	
        		<div class="col-md-5">
        			<img id="editImgViewer" src="http://10.211.55.6/img/products/img_no_aviable.png" style="width: 100%; height: 300px;" alt=""/>
        			<label for="editImg"  class="control-label">Cambiar Imagen</label>    
                    <input type="file" id="editImg" name="editImg" />
        		</div>
          		<div class="col-md-7">          			          			
          			<!-- Category -->
  					<div class="form-group row">
    					<label for="editCategory" class="col-sm-4 form-control-label">Category</label>
    					<div class="col-sm-8">
      						<input type="text" class="form-control" id="editCategory" name="editCategory">
    					</div>
  					</div>
  					<!-- Name -->
  					<div class="form-group row">
    					<label for="editName" class="col-sm-4 form-control-label">Name</label>
    					<div class="col-sm-8">
      						<input type="text" class="form-control" id="editName" name="editName">
    					</div>
  					</div>  						
  					<!-- Laboratory -->
  					<div class="form-group row">
    					<label for="editLaboratory" class="col-sm-4">Laboratory</label>
   						<div class="col-sm-8">
      						<input type="text" class="form-control" id="editLaboratory" name="editLaboratory">
    					</div>
  					</div>
					<!-- Units -->
  					<div class="form-group row">
    					<label for="editUnits" class="col-sm-4">Units</label>
   						<div class="col-sm-8">
   							<input type="text" class="form-control" id="editUnits" name="editUnits">
   						</div>
  					</div>
  					<!-- Expiration Date -->
  					<div class="form-group row">
   						<label for="editExpDate" class="col-sm-4">Expiration Date</label>
  						<div class="col-sm-8">
  							<input type="date" class="form-control" id="editExpDate" name="editExpDate">
    					</div>
  					</div>			
  					<!-- Size -->
  					<div class="form-group row">
    					<label for="editSize" class="col-sm-4">Size</label>
   						<div class="col-sm-8">
      						<input type="number" class="form-control" id="editSize" name="editSize">
    					</div>
  					</div>
  					<!-- Lot -->
  					<div class="form-group row">
    					<label for="editLot" class="col-sm-4">Lot</label>
   						<div class="col-sm-8">
      						<input type="text" class="form-control" id="editLot" name="editLot">
    					</div>
  					</div>					
          		</div>
          		
          	    <!-- Description -->
          	    <div class="col-md-12" style="margin-top: 10px">					
    				<label class="control-label">Description</label>
   					<div class="col-sm-12" style="padding: 0">
      					<textarea id="editDescr" name="editDescr" class="form-control" rows="5"></textarea>
    				</div>
  				</div>
        	</div>
      	</div>
      
      	<!-- Modal Footer -->
      	<div class="modal-footer">
        	<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        	<button type="submit" class="btn btn-primary" name="actionProduct" value="edit">Save changes</button>
      	</div>
      	</form><!-- /.form -->
      	
   	</div><!-- /.modal-content -->
  	</div><!-- /.modal-dialog -->
</div><!-- /.modal -->