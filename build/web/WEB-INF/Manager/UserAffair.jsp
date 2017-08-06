<%@page import="java.util.List"%>
<%@page import="User.User.User"%>
<%@page import="org.apache.struts2.ServletActionContext"%>
<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Affair</title>
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
            List<User> userlist = (List<User>)ServletActionContext.getRequest().getAttribute("userlist");
        %>
    </head>
    <body>
         <form action="JumpBookAffair" method="post" name="BookAffair"></form>
         <p><a href="javascript:document.BookAffair.submit()">返回首页</a></p>
        <div style="text-align: center">
             <table style="margin-left:auto;margin-right:auto;">
                <thead style="text-align: center">
                    <tr style="text-align: center">
                        <td width="90px">ID</td>
                        <td width="90px">用户名</td>
                        <td width="90px">密码</td>
                        <td width="90px">邮箱</td>
                        <td  width="90px">电话</td>
                    </tr>
                </thead>
                <tbody>
                    <%
                        if(userlist!=null)
                        {
                            int size =userlist.size();
                            for(int i=0;i<size;++i)
                            {
                    %>
                    <tr>
                        <td><%=userlist.get(i).getId()%></td>
                        <td><%=userlist.get(i).getUsername()%></td>
                        <td><%=userlist.get(i).getPassword()%></td>
                        <td><%=userlist.get(i).getEmail()%></td>
                        <td><%=userlist.get(i).getMobile()%></td>
                    </tr>
                    <%
                            }
                        }
                    %>
                </tbody>
            </table>
        </div>
    </body>
</html>
