<%--
  Created by IntelliJ IDEA.
  User: dinhq
  Date: 1/10/2025
  Time: 9:59 AM
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
<h1>Enter your email</h1>
<form action="/TodoList_war_exploded/forgot" method="post">
    <input type="text" id="email" name="email" placeholder="Email" onchange="validate_email()" required>
    <span id="err_email" style="color: red;display: none">email is invalid </span>
    <button type="submit" id ="btn">send</button>
</form>
<script>


    function validate_email(){
        let email = document.getElementById("email").value;
        const re = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
        const err_email = document.getElementById('err_email');
        if(!re.test(email)) err_email.style.display = 'flex';
        else err_email.style.display = 'none';
    }
    const btn = document.getElementById('btn');
    btn.addEventListener('click', function(event){
        let email = document.getElementById("email").value;
        const re = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
      if(!re.test(email)) event.preventDefault();
    })
</script>
<script>
    const url = new URL (window.location.href)
    const params = new URLSearchParams(url.search);
    const message = params.get('message');
    console.log(message);
    const err= document.getElementById('err');
    if(message){
        alert(message)
    }
</script>
</body>
</html>
