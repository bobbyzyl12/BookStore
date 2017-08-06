<%@page import="java.util.List"%>
<%@page import="Book.Book.Book"%>
<%@page import="User.User.User"%>
<%@page import="org.apache.struts2.ServletActionContext"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<script typet="text/javascript" src="http://libs.baidu.com/jquery/1.9.1/jquery.min.js"></script>
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
     
     .btn{
		  padding:0;
                  margin-top:10px;
		  margin-left:10px;
		  margin-right:10px;
		  height:35px;
		  width:140px;
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
            Book book = (Book)ServletActionContext.getRequest().getAttribute("book_search2");
        %>
    </head>
    <script typet="text/javascript">
        $(document).ready(function(){
          $("#button").click(function(){
            var bookid= $("#bid").val();
            $.ajax({                           	  
			url: '/BookStore/TestAjax',       //处理测试页面                 
			type: 'POST',                  
			data: {bookid:bookid},                
			success: function (msg){
                            alert("success:"+msg);
                        }
            });
          });
        });
</script>
    <body>
         <form action="JumpBooks" method="post" name="Books">
             </form>
            <p><a href="javascript:document.Books.submit()">返回首页</a></p>
        <div style = "margin: 50px 490px 0px 490px;height:300px;">
            <p id="bid">书本id:<%=book.getId()%></p>
            <p>书本名:<%=book.getBookname()%></p>
            <p>书本作者:<%=book.getAuthor()%></p>
            <p>书本价格:<%=book.getPrice()%></p>
            <p>书本分类:<%=book.getCategory()%></p>
            
        </div>
          <div style = "margin: 0px 490px 0px 490px;height:200px;"> 
        <form action="AddBookToCart" method="post" name="BookDetail">
                <input type="hidden" name="bid" value="<%=book.getId()%>"/>
                <input type="数量" name="number" id="number" placeholder="数量"/><br> <br>
                <input type="submit" value="加入购物车" class="btn"/>
            </form>
          </div>
          <input type="button" id="button">
    </body>
</html>
