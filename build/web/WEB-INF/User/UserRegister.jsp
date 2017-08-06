<%@page import="org.apache.struts2.ServletActionContext"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>注册</title>
        <%
            String cond = ServletActionContext.getRequest().getParameter("cond");
        %>
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
     
    .btn{
		  padding:0;
                  margin-top:10px;
		  margin-left:10px;
		  margin-right:10px;
		  height:30px;
		  width:100px;
		  color:#ffffff;
		  background-color:#4f90fb;
		  font-size:18px;
		  font-weight:normal;
		  border:1px solid #1647e9;
		  -webkit-border-top-left-radius:2px;
		  -moz-border-radius-topleft:2px;
		  border-top-left-radius:2px;
		  -webkit-border-top-right-radius:2px;
		  -moz-border-radius-topright:2px;
		  border-top-right-radius:2px;
		  -webkit-border-bottom-left-radius:2px;
		  -moz-border-radius-bottomleft:2px;
		  border-bottom-left-radius:2px;
		  -webkit-border-bottom-right-radius:2px;
		  -moz-border-radius-bottomright:2px;
		  border-bottom-right-radius:2px;
		  -moz-box-shadow: inset 0px 0px 0px 0px #ffffff;
		  -webkit-box-shadow: inset 0px 0px 0px 0px #ffffff;
		  box-shadow: inset 0px 0px 0px 0px #ffffff;
		  text-align:center;
		  display:inline-block;
		  text-decoration:none;
		}
		
	.btn:hover{
		background-color:#9bc0fd;
	}
         </style>
    </head>
    <body>
        <div style="text-align: center;margin-top:80px;">
            <form role="form" accept-charset="utf-8" action="UserRegister" method="post" style="text-align: center">
                <p for="username">用户名</p>
                <input id="username" name="username" type="text"/><br>
                <p for="password">密码</p>
                <input id="password" name="password" type="password" /><br>
                <p for="c_password">确认密码</p>
                <input id="c_password" name="c_password" type="password" /><br>
                <p for="email">E-mail</p>
                <input id="email" name="email" type="email"/><br>
                <p for="mobile">手机</p>
                <input id="mobile" name="mobile" type="text" placeholder="Mobile Phone"/><br>
                <input type="submit" value="注册" name="Register" class="btn"/>
            </form>
        </div>
    </body>
</html>
