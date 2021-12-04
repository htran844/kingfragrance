function setCookie(cname, cvalue, exdays) {
const d = new Date();
d.setTime(d.getTime() + (exdays*24*60*60*1000));
let expires = "expires="+ d.toUTCString();
document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}
function getCookie(cname) {
let name = cname + "=";
let decodedCookie = decodeURIComponent(document.cookie);
let ca = decodedCookie.split(';');
for(let i = 0; i <ca.length; i++) {
  let c = ca[i];
  while (c.charAt(0) == ' ') {
    c = c.substring(1);
  }
  if (c.indexOf(name) == 0) {
    return c.substring(name.length, c.length);
  }
}
return "";
}
function login(){
        // setCookie('token', data.token, 1)

        var user = document.getElementById("username").value;
        if (user.length == 0) {
            alert("Vui lòng nhập tài khoản!");
            return ;
        }
        var pass = document.getElementById("password").value;
        if (pass.length == 0) {
            alert("Vui lòng nhập mật khẩu!");
            return ;
        }
		const users = {
			username: user,
			password: pass
		}
		console.log(users)
        $.ajax({
            url: '/admin/getlogin',
			headers: {
			  		  "Content-Type":"application/json"
			  	  },
            type: 'POST',
            data: JSON.stringify(users)
        }).then(data=>{
			if(data.length >0){
				setCookie('token', data, 1)
              
              window.location.href = 'http://localhost:1010/admin';
			}
			if(data.length == 0){
				alert("sai tên tài khoản hoặc mật khẩu")
			}
        }).catch(err=>{
            console.log(err)
        })
    }