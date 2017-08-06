<%@page import="java.util.List"%>
<%@page import="Book.Book.Book"%>
<%@page import="org.apache.struts2.ServletActionContext"%>
<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>书本管理</title>
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
        <%
            List<Book> booklist = (List<Book>)ServletActionContext.getRequest().getAttribute("booklist");
        %>
    </head>
    <body>
        <form action="JumpUserAffair" method="post" name="UserAffair"></form>
        <form action="CreateBookIndex" method="post" name="CreateBookIndex"></form>
         <p><a href="javascript:document.UserAffair.submit()">用户管理</a></p>
         
         <p><a href="javascript:document.CreateBookIndex.submit()">创建图书索引</a></p>
        <div style="text-align: center;height:400px;">
            <h2>
                <strong>搜索</strong>
            </h2>
            <form accept-charset="utf-8" action="SearchBooks" method="post">
                <select name="type">
                    <option value="id">ID</option>
                    <option value="bookname">书名</option>
                    <option value="author">作者</option>
                    <option value="category">种类</option>
                </select>&nbsp;
                <input type="text" name="search"/>&nbsp;
                <input type="hidden" name="from" value="manager"/>
                <br>
                <input type="submit" value="搜索" class="btn"/>
                 <br>
                 <br>
            </form>
            <table style="margin-left:auto;margin-right:auto;">
                <thead style="text-align: center">
                    <tr style="text-align: center">
                        <td width="90px">ID</td>
                        <td width="90px">书名</td>
                        <td width="90px">作者</td>
                        <td width="90px">价格</td>
                        <td  width="90px">种类</td>
                    </tr>
                </thead>
                <tbody>
                    <%
                        if(booklist!=null)
                        {
                            int size = booklist.size();
                            for(int i=0;i<size;++i)
                            {
                                int bid =booklist.get(i).getId();
                    %>
                    <tr>
                        <td> <a href="JumpBookDetailM?bid=<%=bid%>"><%=bid%></a></td>
                        <td><%=booklist.get(i).getBookname()%></td>
                        <td><%=booklist.get(i).getAuthor()%></td>
                        <td><%=booklist.get(i).getPrice()%></td>
                        <td><%=booklist.get(i).getCategory()%></td>
                    </tr>
                    <%
                            }
                        }
                    %>
                </tbody>
            </table>
        </div>
        <div style="margin-left:auto;margin-right:auto;height:150px;">
            <table style="margin-left:auto;margin-right:auto;height:150px;">
                <thead>
                    <tr>
                        <td>书名</td>
                        <td>作者</td>
                        <td>价格</td>
                        <td>种类</td>
                    </tr>
                </thead>
                <tbody>
                    <form accept-charset="utf-8" action="AddBook" method="post">
                        <tr>
                            <td><input type="text" name="bookname" id="bookname"/></td>
                            <td><input type="text" name="author" id="author"/></td>
                            <td><input type="text" name="price" id="price"/></td>
                             <td><input type="text" name="category" id="category"/></td>
                        </tr>
                        <tr><td><input type="submit" value="Add"/></td></tr>
                    </form>
                </tbody>
            </table>
            </div>
    </body>
    <footer>
        
    </footer>
</html>
