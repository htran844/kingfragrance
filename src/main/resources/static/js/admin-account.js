function createAccount(){
	let name = document.getElementById("name").value;
	let pass = document.getElementById("pass").value;
	let repass = document.getElementById("repass").value;
	
	if (name.length == 0) {
            alert("Vui lòng nhập tài khoản!");
            return ;
        }
	if (pass.length == 0) {
	            alert("Vui lòng nhập mật khẩu!");
	            return ;
	        }
	if (repass.length == 0) {
	            alert("Vui lòng nhập lại mật khẩu");
	            return ;
	        }
	if (repass != pass) {
	            alert("Mật khẩu nhập lại không đúng");
	            return ;
	        }
	const users = {
			username: name,
			password: pass
		}
        $.ajax({
            url: '/admin/createuser',
			headers: {
			  		  "Content-Type":"application/json"
			  	  },
            type: 'POST',
            data: JSON.stringify(users)
        }).then(data=>{
			if(data.length >0){
				alert("tạo tài khoản thành công")
			}
			if(data.length == 0){
				alert("tài khoản đã tồn tại")
			}
        }).catch(err=>{
            console.log(err)
        })
}