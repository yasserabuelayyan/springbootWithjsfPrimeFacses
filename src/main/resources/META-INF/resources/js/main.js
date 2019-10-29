$(function(){
	
	// Delete confirmation 
	$(".delete").click(function(event){
		
		event.preventDefault();
		
		var confirmMessage = confirm("Are you sure to delete this item?");
		
		if(confirmMessage){
			window.location.href = this.getAttribute("href");
		}
	});
	
	
});