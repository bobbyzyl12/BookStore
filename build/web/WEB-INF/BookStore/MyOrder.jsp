<%@page import="Book.Book.Book"%>
<%@page import="Cart.Cart.Cart"%>
<%@page import="Order.Order.Order"%>
<%@page import="User.User.User"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="org.apache.struts2.ServletActionContext"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order List</title>
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
        <%
            List<Order> orderList = (List<Order>)ServletActionContext.getRequest().getAttribute("orderlist");
        %>
    </head>
    <body>
         <form action="JumpBooks" style="" method="post" name="Books">
        </form>
        <p><a href="javascript:document.Books.submit();">返回首页</a></p>
       
        
        <div style="text-align: center">
            <h2>
                <strong>我的订单</strong>
            </h2>

            <div style ="width:400px;height:300px;margin : 50px 825px 50px 825px;">
            <table style="text-align: center">
                <thead style="text-align: center">
                    <tr style="text-align: center">
                        <td width="90px">订单编号</td>
                        <td width="90px">状态</td>
                        <td width="90px">操作</td>
                    </tr>
                </thead>
                <tbody style="text-align: center">
                    <%
                        if(orderList!=null)
                        {
                            for(int i=0;i<orderList.size();++i)
                            {
                                    int oid =orderList.get(i).getId();
                    %>
                    <tr style="text-align: center">
                        <td><%=oid %></td>
                        <%
                            if(orderList.get(i).getState().equals(1)){ %>
                             <td>未支付</td>
                           <% }
                              else if(orderList.get(i).getState().equals(2)){
                             %>
                                <td>已支付</td>
                             <%
                                }      
                           %>
                            <%
                            if(orderList.get(i).getState().equals(1)){ %>
                             <td><a href="PayOrder?oid=<%=oid%>">支付</a></td>
                           <% }
                             %>
                           
                       
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
