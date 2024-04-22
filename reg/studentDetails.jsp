<%@ page import="java.util.*,com.example.model.*,java.util.List,java.util.ArrayList" %>
<%@ page import="java.util.*,com.example.dao.*" %>

<%
String spageid = request.getParameter("page");
int pageid = (spageid != null) ? Integer.parseInt(spageid) : 1;
int total = 5;
if (pageid != 1) {
    pageid = (pageid - 1) * total + 1;
}

List<User> list = UserDao.getRecords(pageid, total);

%>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student Details</title>
    <style>
        body 
		{
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        h1 
		{
            text-align: center;
            margin-bottom: 20px;
        }

        table 
		{
            width: 60%;
            border-collapse: collapse;
            margin: 0 auto;
        }

        th, td 
		{
            padding: 10px;
            text-align: left;
            border: 1px solid #ddd;
        }

        th 
		{
            background-color: #f2f2f2;
        }

        tr:hover 
		{
            background-color: #f2f2f2;
        }

        .pagination 
		{
            display: flex;
            justify-content: center;
            margin-top: 20px;
        }

        .pagination a 
		{
            color: #007bff;
            padding: 8px 16px;
            text-decoration: none;
            transition: background-color 0.3s;
            border: 1px solid #ddd;
            margin: 0 5px;
            border-radius: 3px;
        }

        .pagination a:hover 
		{
            background-color: #ddd;
        }

        .pagination .active 
		{
            background-color: #007bff;
            color: white;
        }
		
		.btn {
            background-color: #007bff;
            color: #fff;
            padding: 10px 20px;
            border: none;
            border-radius: 3px;
            cursor: pointer;
            font-size: 16px;
            text-decoration: none;
            transition: background-color 0.3s ease;
        }
    </style>
</head>
<body>
    <h1>Student Details</h1>
    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Roll No</th>
            <th>Fees</th>
            <th>Status</th>
        </tr>
        <% for (User user : list) { %>
            <tr>
                <td><%= user.getId() %></td>
                <td><%= user.getName() %></td>
                <td><%= user.getRollNo() %></td>
                <td><%= user.getFees() %></td>
                <td><%= user.getStatus() %></td>
            </tr>
        <% } %>
    </table>

    <div class="pagination">
        <% 
        int totalRecords = UserDao.getTotalRecords();
        int totalPages = (int) Math.ceil((double) totalRecords / total);
        for (int i = 1; i <= totalPages; i++) { 
        %>
        <a href="studentDetails.jsp?page=<%= i %>" class="<%= (i == pageid) ? "active" : "" %>"><%= i %></a>
        <% } %>
    </div>
	<div style="width:150px; margin:auto; margin-top:50px">
		<a href="." class="btn">Back to Home</a>
	</div>
</body>
</html>
