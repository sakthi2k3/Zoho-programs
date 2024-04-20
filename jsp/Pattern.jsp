<!DOCTYPE html>
<html>
<head>
    <title>Pattern Printing</title>
</head>
<body>
    <%
        for (int i = 5; i >= 1; i--) 
		{
            for (int j = i; j >= 1; j--) 
			{
                if (i <= (5 / 2))
				{
                    out.print(i);
                } 
				else 
				{
                    out.print(5 - i + 1);
                }
            }
            out.println("<br>");
        }
    %>
</body>
</html>
