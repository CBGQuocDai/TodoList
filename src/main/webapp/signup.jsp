<%--
  Created by IntelliJ IDEA.
  User: dinhq
  Date: 12/30/2024
  Time: 11:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>Title</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./css/signup.css">
</head>
    <body>
        <div class = "container">
            <form action="/TodoList_war_exploded/signup" method="post">
                <label for="email">
                    Email:
                </label>
                <input type="text" id="email" name="email" placeholder="Email" onchange="validate_email()" required>
                <span id="err_email" style="color: red;display: none">email is invalid </span>
                <label for="password">
                    Password:
                </label>
                <input type= "password" id ="password" name="password" placeholder="Password" required>
                <label for="confirmPassword">Confirm Password</label>
                <input type= "password" id ="confirmPassword" name="confirmPassword" placeholder="Confirm Password" onchange="err_password()" required>
                <span id="err_password" style="color:red;display:none">Confirm password is different from password</span>
                <div>
                    <button type="submit" id="checkall">Sign Up</button>
                </div>
            </form>
        </div>
        <p style ="font-size:1.2em">if you have account, <a href="/TodoList_war_exploded/login">Login</a></p>
        <script>
            function validate_email(){
                let email = document.getElementById("email").value;
                const re = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
                const err_email = document.getElementById('err_email');
                if(!re.test(email)) err_email.style.display = 'flex';
                else err_email.style.display = 'none';
            }
            function validate_password(){
                let password = document.getElementById("password").value;
                let confirmPassword = document.getElementById("confirmPassword").value;
                return password != confirmPassword;
            }
            function err_password(){
                let check = validate_password();
                const err = document.getElementById('err_password');
                if(check) err.style.display = 'flex';
                else err.style.display = 'none';
            }
            const button = document.getElementById('checkall');
            button.addEventListener('click', function(event){
                let email = document.getElementById("email").value;
                const re = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
                let e = !re.test(email);
                let password = validate_password();
                if( e== true || password==true) event.preventDefault();
                })
        </script>
        <script>
            const url = new URL (window.location.href)
            const params = new URLSearchParams(url.search);
            const message = params.get('error')
            const err= document.getElementById('err_email');
            if(message){
                err.innerHTML = message;
                err.style.display = 'flex';
            }
        </script>
    </body>
</html>
