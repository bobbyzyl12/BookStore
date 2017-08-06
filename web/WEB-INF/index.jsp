<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="User.User.User"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="org.apache.struts2.ServletActionContext"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.ResourceBundle"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%
      
    String path = request.getContextPath();
    String base = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    String language = (String) ServletActionContext.getRequest().getAttribute("Language");
    String username = (String)ServletActionContext.getRequest().getAttribute("username");
    String register =(String)ServletActionContext.getRequest().getAttribute("register");
    String userLogin =(String)ServletActionContext.getRequest().getAttribute("userLogin");
    String managerLogin =(String)ServletActionContext.getRequest().getAttribute("managerLogin");
    String bookstore =(String)ServletActionContext.getRequest().getAttribute("bookstore");
    String chatroom =(String)ServletActionContext.getRequest().getAttribute("chatroom");
    String luceneSearch = (String)ServletActionContext.getRequest().getAttribute("luceneSearch");
%>
<html>
    <script typet="text/javascript" src="js/jquery-1.6.2.min.js"></script>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>BookStore</title>
       
           <link rel="stylesheet" href="http://apps.bdimg.com/libs/bootstrap/3.3.0/css/bootstrap.min.css">  
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
    <script>
    $(function(){
        var language = $("#language").attr("title");
        if(language==="null"){
            window.location.href='BookStore/JumpToIndex';
        }
    });
    </script>
    </head>

    <body style=" overflow-y: hidden;">
        <%
            if(username != null)
            {
        %>
        
        <%
            }
        %>
        <p id="language" style="display:none;" title="<%=language%>"><%=language%></p>
        <div style="text-align: center;margin-top:200px;">
            <ul>
                <%if((username == null)&&(session.getAttribute("Manager") == null)){%>
                <li><a id="registerD" href="javascript:document.UserRegister.submit();"><%=register%></a></li>
                <li><a id="UloginD" href="javascript:document.UserLogin.submit();"><%=userLogin%></a></li>
                <li><a id="MloginD" href="javascript:document.ManagerLogin.submit()"><%=managerLogin%></a></li>
                <%}else if((username != null)&&(session.getAttribute("Manager") == null)){%>	
                <li><a id="bookstoreD" href="javascript:document.Books.submit();"><%=bookstore%></a></li>
                <li><a id="chatroomD" href="javascript:document.ChatRoom.submit();"><%=chatroom%></a></li>
                <li><a id="chatroomD" href="javascript:document.LuceneSearch.submit();"><%=luceneSearch%></a></li>
                <%}%>
            </ul>
        </div>
        
        <div  style="text-align:center;height:50px;position:fixed;bottom:0px;right:100px;">
            <a href="JumpToIndex?languageFlag=2">中文</a>&nbsp;|&nbsp;<a href="JumpToIndex?languageFlag=1">English</a>
        </div>
    </body>
    <footer>
       
        <form action="JumpUserLogin" method="post" name="UserLogin"></form>
        <form action="JumpUserRegister" method="post" name="UserRegister"></form>
        <form action="JumpManagerLogin" method="post" name="ManagerLogin"></form>
        <form action="JumpBooks" method="post" name="Books">
            <input type="hidden" name="userid" value="<%=session.getAttribute("userid")%>"/>
        </form>
         <form action="JumpChatRoom" method="post" name="ChatRoom"></form>
         <form action="JumpLuceneSearch" method="post" name="LuceneSearch"></form>
    </footer>
        
</html>
