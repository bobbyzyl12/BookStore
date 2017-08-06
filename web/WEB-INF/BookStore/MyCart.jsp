<%@page import="Book.Book.Book"%>
<%@page import="Cart.Cart.Cart"%>
<%@page import="User.User.User"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="org.apache.struts2.ServletActionContext"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart Detail</title>
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
            List<Cart> carts = (List<Cart>)ServletActionContext.getRequest().getAttribute("cartlist");
        %>
    </head>
    <body>
        <div style=height:50px;"> 
            <form action="JumpMyCart" method="post" name="MyCart">
             </form>
            <p><a href="javascript:document.MyCart.submit()">我的购物车</a></p>
             <form action="JumpBooks" method="post" name="Books">
             </form>
            <p><a href="javascript:document.Books.submit()">返回首页</a></p>
        </div>
        <div style="text-align: center">
            <h2>
                <strong>我的购物车</strong>
            </h2>
            <div style ="width:400px;height:300px;margin : 50px 825px 50px 825px;">
            <table style="text-align: center">
                <thead style="text-align: center">
                    <tr style="text-align: center">
                        <td width="90px">购物车编号</td>
                        <td width="90px">图书编号</td>
                        <td width="90px">数量</td>
                    </tr>
                </thead>
                <tbody style="text-align: center">
                    <%
                        if(carts!=null)
                        {
                            for(int i=0;i<carts.size();++i)
                            {
                                    int cid =carts.get(i).getCartid();
                    %>
                    <tr style="text-align: center">
                        <td><a href="JumpCartDetail?cid=<%=cid%>"><%=cid%></a></td>
                        <td><%=carts.get(i).getBookid() %></td>
                        <td><%=carts.get(i).getNumber()%></td>
                    </tr>
                    <%
                            }
                        }
                    %>
                </tbody>
            </table>
            </div>
            <div style = "margin: 0px 490px 0px 490px;height:300px;"> 
                <form action="AddOrder" method="post">
                    <input type="submit" value="生成订单" class="btn"/>
                </form>
          </div>
        </div>
        
    </body>
</html>
