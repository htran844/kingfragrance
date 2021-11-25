var userId =
var name = document.getElementById('name');
var phone = 
var emial = 
var note = 
var address = 
var status = 
var totalCost = 
var createdAt = 
var comfirmAt = 
var finishedAt = 
let order = {
		id
}
asnyc function getIdOrder(){
	
		const orderId = await $.ajax({
			url : `/admin/orders`,
			headers : {
				"Content-Type" : "application/json"
			},
			type : "POST",
			data : order,
			  success: function (response) {
				  
			    }
		 })
}
private String id;
private String userId;
private String name;
private String phone;
private String email;
private String note;
private String address;
private String status;
private int totalCost;
private Date createAt;
private Date comfirmAt;
private Date finishedAt;