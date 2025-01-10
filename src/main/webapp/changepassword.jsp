<%--
  Created by IntelliJ IDEA.
  User: dinhq
  Date: 1/10/2025
  Time: 12:57 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        body{
            padding:0;
            margin:0;
            font-size:12px;
            display:flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
            width:100%;
            height:100vh;
        }
    </style>
</head>
<body>
<form action="/TodoList_war_exploded/changepassword" method="post">
    <input type="password" id ="1" name ="password" placeholder="enter new password"/>
    <input type="password" id="2" placeholder="confirm new password" onchange="check()"/>
    <span id="err_password" style="color:red;display:none">Confirm password is different from password</span>
    <button id="btn" type="submit">change</button>
</form>
<script>
        let password = document.getElementById("1").value;
        let confirm  = document.getElementById("2").value;
        const err= document.getElementById("err_password");
        function check(){
            if(password!==confirm){
                err.style.display="flex";
            }
            else{
                err.style.display="none";
            }
        }
    const button=document.getElementById("btn");
    button.addEventListener("click",(event)=>{
        if(password !== confirm) event.preventDefault();
    })
</script>
</body>
</html>
