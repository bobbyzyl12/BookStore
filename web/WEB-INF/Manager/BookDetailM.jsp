<%@page import="java.util.List"%>
<%@page import="Book.Book.Book"%>
<%@page import="org.apache.struts2.ServletActionContext"%>
<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
         <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>详细信息</title>
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
            Book booklist = (Book)ServletActionContext.getRequest().getAttribute("book_search");
        %>
    </head>
    <body>
         <form action="JumpBookAffair" method="post" name="BookAffair">
             </form>
            <p><a href="javascript:document.BookAffair.submit()">返回首页</a></p>
        <div style = "margin: 100px 490px 0px 490px;height:300px;">
            <p>书本id:<%=booklist.getId()%></p>
            <p>书本名:<%=booklist.getBookname()%></p>
            <p>书本作者:<%=booklist.getAuthor()%></p>
            <p>书本价格:<%=booklist.getPrice()%></p>
            <p>书本分类:<%=booklist.getCategory()%></p>
        </div>
    </body>
</html>
