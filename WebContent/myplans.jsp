<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import ="java.sql.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
 <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    <style>
       <!-- .fa {
            padding: 20px;
            font-size: 30px;
            width: 30px;
            text-decoration:none;
            margin: 5px 2px;
            border-radius: 50%;
        }

        .fa:hover {
             opacity: 0.7;
        }

        .fa-facebook {
            background: #3B5998;
            color: white;
        }

        .fa-twitter {
            background: #55ACEE;
            color: white;
        }


        .fa-youtube {
            background: #bb0000;
            color: white;
        }

        .fa-instagram {
            background: #125688;
            color: white;
        }
        -->
        .jumbotron {
            
            background-image: url("/FullStackProject/src/build.jpg");
            background-size: 100% 100%;
            height: 50%;
        }

        .form {
          
            border-radius: 1%;
            position:center;
            right: 0;
            margin: 10px;
            max-width: 35%;
            max-height: 65%;
            padding-left: 3%;
            padding-right: 3%;
            padding-top: 2%;
            padding-bottom: 6%;
            background-color: white;
        }
 
    </style>
</head>
<body>
    <nav class="navbar navbar-inverse navbar-fixed-top">
        <div class="container-fluid">

            <div class="navbar-header">
                <a class="navbar-brand" href="#" style="padding-top:1%"><h1 style="color:darkcyan">MyTripPlanner</h1></a>
            </div><br />

            <ul class="nav navbar-nav">
                    <form class="nav navbar-nav" action="/FullStackProject" method="get">
                        <li><input type="submit" value="Home" style="background-color: transparent;background-repeat:no-repeat;color: white;border: none;cursor:pointer;width: 75px;height: 50px;padding-top: 1%;"></li>
                    </form>
                <li><a href="#">About Us</a></li>
                <li><a href="#">My plans</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right" action="/action_page.php">
                    <li>
                        <a href="#">
                            <span class="glyphicon glyphicon-heart"></span>
                        </a>
                    </li>
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                            <span class="glyphicon glyphicon-user"></span>
                            <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu">
                            <form action="login" method="get">
                                <li><input type="submit" value="Login" style="background-color: transparent;background-repeat:no-repeat;border: none;cursor:pointer;overflow: hidden;width: 150px;"></li></li>
                            </form>
                            <form action="regredirect" method="get">
                                <li><input type="submit" value="Register" style="background-color: transparent;background-repeat:no-repeat;border: none;cursor:pointer;overflow: hidden;width: 150px;"></li></li>
                            </form>
                        </ul>
                    </li>
                </ul>
            
            <!--Feedback-->
             <form class="navbar-form navbar" action="#">
                    <div class="input-group">
                        <input type="text" class="form-control" placeholder="Search" name="search">
                        <div class="input-group-btn">
                            <button class="btn btn-inverse" type="submit">
                                <i class="glyphicon glyphicon-search"></i>
                            </button>
                        </div>
                    </div>
                </form>
        </div>
        <h3 style="color:white;">My Plans</h3>
        <%
        try
        {
        	Class.forName("com.mysql.jdbc.Driver");
        	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/fullstackproject?useSSL=false","root","root");
        	Statement s=con.createStatement();
        	int us=(int)request.getAttribute("user");
        	String que="select * from plans where uid="+us+";";
        	ResultSet rs=s.executeQuery(que);
        %>
        <table style="width:100%;background-color:white;">
        <tr>
        <th>From</th>
        <th>To</th>
        <th>Start Date</th>
        <th>End Date</th>
        <th>Number of People</th>
        <% while(rs.next()){
        %>
        </tr>
        <tr>
        <td><%=rs.getString(2) %></td>
        <td><%=rs.getString(3) %></td>
        <td><%=rs.getDate(4) %></td>
        <td><%=rs.getDate(5) %></td>
        <td><%=rs.getInt(6) %></td>
        </tr>
        <br/>
        <%}
        }catch(Exception e)
        {
        	e.printStackTrace();
        }%>
        </table>	
        <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
	<div class="navbar navbar-inverse navbar-static-bottom">
        <div class="container">
            <div class="navbar-text pull-left">
                <p><h3><b>Help</b></h3></p>
                <div class="line"></div>
                <ul class="nav navbar-nav pull-left">
                        <form action="/" method="get">
                            <li><input type="submit" value="FAQs" style="background-color: transparent;background-repeat:no-repeat;border: none;cursor:pointer;overflow: hidden;width: 150px;text-align: left;"></li></li>
                        </form>
                    <br />
                    <form action="/" method="get">
                        <li><input type="submit" value="Contact Us" style="background-color: transparent;background-repeat:no-repeat;border: none;cursor:pointer;overflow: hidden;width: 150px;text-align: left;"></li></li>
                    </form>
                    <br />
                    <form action="/" method="get">
                        <li><input type="submit" value="Privacy Policy" style="background-color: transparent;background-repeat:no-repeat;border: none;cursor:pointer;overflow: hidden;width: 150px;text-align: left;"></li></li>
                    </form>
                    <br />
                    <form action="cookies" method="post">
                        <li><input type="submit" value="Cookie Policy" style="background-color: transparent;background-repeat:no-repeat;border: none;cursor:pointer;overflow: hidden;width: 150px;text-align: left;"></li></li>
                    </form>
                    <br />
                    <form action="/cpr" method="get">
                        <li><input type="submit" value="Copyright" style="background-color: transparent;background-repeat:no-repeat;border: none;cursor:pointer;overflow: hidden;width: 150px;text-align: left;"></li></li>
                    </form>
                    <br />
                    <form action="/" method="get">
                        <li><input type="submit" value="Terms of use" style="background-color: transparent;background-repeat:no-repeat;border: none;cursor:pointer;overflow: hidden;width: 150px;text-align: left;"></li></li>
                    </form>
                    <br />
                </ul>
                <!--<ul class="nav navbar-nav" style="align-content:center">
                     <li><a href="#" class="fa fa-facebook"></a></li>
                     <li><a href="#" class="fa fa-twitter"></a></li>
                     <li><a href="#" class="fa fa-youtube"></a></li>
                     <li><a href="#" class="fa fa-instagram"></a></li>
                 </ul>
                -->
            </div>
        </div>
    </div>

</body>
</html>