<!DOCTYPE html>  
<%@page import="javax.servlet.http.HttpSession" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>  
<head>  
<title>websockets</title>  
<style type="text/css">
    html,body,div,ul,li{
        margin: 0;
        padding: 0;
        height: 100%;
        -webkit-box-sizing: border-box;
        -moz-box-sizing: border-box;
        box-sizing: border-box;
        font-family: "Microsoft YaHei",Arial,Helvetica,sans-serif;
     }

     ul, menu, dir {
        display: block;
        list-style-type: disc;
        -webkit-margin-before: 1em;
        -webkit-margin-after: 1em;
        -webkit-margin-start: 0px;
        -webkit-margin-end: 0px;
        -webkit-padding-start: 40px;
     }

     h1, h2, h3, h4, h5, h6, ul, li {
       list-style-type: none;
     }

     li {
       display: list-item;
       text-align: -webkit-match-parent;
     }

     div.middle-area{
        width: 980px;
        margin-left: auto;
        margin-right: auto;
        position: relative;
     }
     
     li{
         margin:10px 0px 10px 0px;
         height:30px;
     }
     
     a:hover,a,a:visited,a:link{
         text-decoration: none;
     }
     a{
         color:#000;
     }
      a:hover{
         color:#666;
     }
     
</style>
<%
   String username = (String) session.getAttribute("username"); 
%>
</head>  
<body>
    <form action="JumpBooks" method="post" name="Books"></form>
    <p><a href="javascript:document.Books.submit()">返回首页</a></p>
   
    <div style="text-align: center;">
         username:<p id="username" title="<%=username%>"><%=username%></p>
        <input type="text" id="txt" style="width:200px;">
        <input type="button" id="sendBtn" value="Send" onclick="sendMessage();">
        <br>
        <textarea id="messages" readonly="readonly" rows="20" cols="50" style="margin:20px;padding:10px 30px;"></textarea>
        <br>
        <input type="button" id="closBtn" value="Close" onclick="closeSocket();">
    </div>
    <script typet="text/javascript" src="js/jquery-1.6.2.min.js"></script>
    <script type="text/javascript">
    var ws = new WebSocket('ws://localhost:8080/BookStore/websocket/chat');
    
    function sendMessage(){
        var username = document.getElementById('username').innerHTML;
        var text = document.getElementById('txt').value;
        var s = username + ":"+ text;
        ws.send(s); 
    }
    
    function closeSocket(){
        ws.close();
    }
    
    ws.onmessage = function(event){
        var messages = document.getElementById("messages");
        
        messages.value +=(event.data+"\n");
    };
</script>

</body>  
</html>  