<%--
  Created by IntelliJ IDEA.
  User: dinhq
  Date: 12/30/2024
  Time: 10:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>Title</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./css/login.css">
</head>
<body>
    <div class = "container">
        <form action="/TodoList_war_exploded/login" method="post">
                <span id ="err" style="color:red;font-size: 1.3em"> </span>
              <label for="email">
                  Email
              </label>
              <input type="text" id="email" name="email" placeholder="Email" required>
              <label for="password">
                  Password:
              </label>
              <input type="password" id ="password" name="password" placeholder="Password" required>
                <div class="remember">
                    <input type="checkbox" id ="remember_me" name="remember_me">
                    <label for="remember_me">Remember me a month</label>
                </div>
              <div>
                  <a href="/TodoList_war_exploded/forgot">forget password</a>
                  <button type="submit">Login</button>
              </div>
        </form>

    </div>
    <p style="font-size: 1.2em">if you don't hava account, <a href="/TodoList_war_exploded/signup">Sign Up</a></p>
    <script>
        const url = new URL (window.location.href)
        const params = new URLSearchParams(url.search);
        const message = params.get('error')
        console.log(message);
        const err= document.getElementById('err')
        if(message){
               err.innerHTML = message
        }
    </script>
</body>
</html>
