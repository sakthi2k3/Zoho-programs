<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student Registration</title>
    <style>
        body 
		{
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column; /* Arrange containers vertically */
            align-items: center;
            height: 100vh;
        }

        .container 
		{
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            width: 300px;
            margin-bottom: 20px; /* Add some space between containers */
        }

        h2 
		{
            text-align: center;
            margin-bottom: 20px;
        }

        form 
		{
            display: flex;
            flex-direction: column;
        }

        label 
		{
            margin-bottom: 10px;
            font-weight: bold;
        }

        input[type="text"],
        input[type="number"],
        select 
		{
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 3px;
            font-size: 16px;
        }

        select 
		{
            width: 100%;
        }

        input[type="submit"] 
		{
            background-color: #007bff;
            color: #fff;
            padding: 10px 20px;
            border: none;
            border-radius: 3px;
            cursor: pointer;
            font-size: 16px;
        }

        input[type="submit"]:hover 
		{
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Student Registration Form</h2>
        <form action="reg-student" method="post">
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" required>
            <label for="rollNo">Roll No:</label>
            <input type="text" id="rollNo" name="rollNo" required>
            <label for="fees">Fees:</label>
            <input type="number" id="fees" name="fees" step="0.01" required>
            <label for="status">Status:</label>
            <select id="status" name="status" required>
                <option value="Paid">Paid</option>
                <option value="Unpaid">Unpaid</option>
            </select>
            <input type="submit" value="Register">
        </form>
    </div>
    <div class="container">
        <h2>View Students Details</h2>
        <form action="studentDetails.jsp" method="post">
            <input type="submit" value="View">
        </form>
    </div>
</body>
</html>
