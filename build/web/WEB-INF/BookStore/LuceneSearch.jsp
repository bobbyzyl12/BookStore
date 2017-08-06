<%@page import="Book.Book.Book"%>
<%@page import="User.User.User"%>
<%@page import="javax.servlet.http.HttpSession" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="org.apache.struts2.ServletActionContext"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://apps.bdimg.com/libs/jquery/1.6.2/jquery.min.js"></script>
        <title>Book Main Page</title>
        <link rel="stylesheet" href="http://apps.bdimg.com/libs/bootstrap/3.3.0/css/bootstrap.min.css">
        <script typet="text/javascript">
        $(document).ready(function(){
          var bookid = -1;
          $(".ids").click(function(){
            bookid= $(this).attr("title");
            $.ajax({                           	  
			url: '/BookStore/BookDetail',       //处理测试页面                 
			type: 'POST',                  
			data: {bookid:bookid},                
			success: function (msg){
                            var bh = $("body").height();
				    var bw = $("body").width();
				    $("#coverbg").css({
				        height: bh,
				        width: bw,
				        display: "block"
				    });
                            $(".dialog").html(msg);
                            $(".dialog").show();
                        }
            });
          });
        });
</script>
<script type="text/javascript">
    //关闭灰色 jQuery 遮罩
    function closeBg() {
        $("#coverbg").hide();
        $(".dialog").hide();
    }
</script>
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
        .dialog{
		display: none;
		z-index: 5;
		
		height:300px;
		width:400px;
		background-color: #FFF;
		border: 1px solid #888;
		
                padding:20px;
		position: fixed !important; /* 浮动对话框 */
		top: 25%;
		left:35%;
		text-align:center;
		margin-left:auto; 
		margin-right:auto;
		
		-moz-border-radius: 10px;
		-webkit-border-radius: 10px;
		border-radius: 10px;
		
		border:2px solid #4f90fb;
	}
	
	.dialog_msg{
		font-size:20px;
		margin-top:30px;
	}
	
	.coverbg{
    	background-color:#333;
    	left: 0px;
    	opacity: 0.5;
		position: absolute;
    	top: 0px;
    	z-index: 3;
    	filter: alpha(opacity=50); /* IE6 */
    	-moz-opacity: 0.5; /* Mozilla */
    	-khtml-opacity: 0.5; /* Safari */
	}
        
        html{
            overflow:hidden;
        }
         </style>
        
    </head>
    
    <body>
         <%
            List<Book> booklist = (List<Book>)ServletActionContext.getRequest().getAttribute("booklist");
        %>
        <div style=height:50px;"> 
            <form action="JumpMyCart" method="post" name="MyCart">
             </form>
             <form action="JumpMyOrder" method="post" name="MyOrder">
             </form>
            <form action="JumpChatRoom" method="post" name="ChatRoom">
             </form>
            <form action="JumpBooks" method="post" name="Books">
             </form>
            <p><a href="javascript:document.MyCart.submit()">我的购物车</a></p>
            <p><a href="javascript:document.MyOrder.submit()">我的订单</a></p>
            <p><a href="javascript:document.ChatRoom.submit()">聊天室</a></p>
             <p><a href="javascript:document.Books.submit()">返回首页</a></p>
        </div>
        <div style="text-align: center;">
            <form accept-charset="utf-8" action="LuceneSearch" method="post">
                <input type="text" name="search"/>&nbsp;
                <input style ="width:400px;height:100px;" type="hidden" name="from" value="user"/>
                <br>
                <input type="submit" value="搜索" class="btn"/>
            </form>
        
        <div style ="width:400px;height:300px;margin : 50px 600px;">
            <table style="text-align: center">
                <thead style="text-align: center">
                    <tr style="text-align: center">
                        <td width="90px">书名</td>
                        <td width="90px">作者</td>
                        <td width="90px">操作</td>
                    </tr>
                </thead>
                <tbody style="text-align: center">
                    <%
                        if(booklist!=null)
                        {
                            for(int i=0;i<booklist.size();++i)
                            {
                                    int bid =booklist.get(i).getId();
                    %>
                    <tr style="text-align: center">
                        <td class="ids" title="<%=bid%>"><%=booklist.get(i).getBookname()%></td>
                        <td><%=booklist.get(i).getAuthor()%></td>
                        <td><a href="JumpBookDetail?bid=<%=bid%>">购买</a></td>
                    </tr>
                    <%
                            }
                        }
                    %>
                </tbody>
            </table>
            </div>
    </div>
    </body>
</html>
