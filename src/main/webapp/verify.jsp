<%--
  Created by IntelliJ IDEA.
  User: dinhq
  Date: 1/7/2025
  Time: 9:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang = "en">
<head>
    <title>Title</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./css/verify.css">
</head>
<body>
    <form class = "container" action = "/TodoList_war_exploded/verify" method = "POST">
      <h3>we send otp to your mail</h3>
      <label for="otp"></label>
      <input type="text" name="otp" id="otp" placeholder="Enter OTP here">
      <button type="submit"> submit </button>
    </form>
    <script>
        const url = new URL (window.location.href)
        const params = new URLSearchParams(url.search);
        const message = params.get('error')
        const err= document.getElementById('err')
        if(message){
            alert(message)
        }
    </script>
</body>
</html>
