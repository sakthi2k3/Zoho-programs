<!DOCTYPE html>
<html>
<head>
    <title>Fibonacci Series</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            margin-top: 50px;
        }
        .container {
            width: 50%;
            margin: 0 auto;
        }
        #output {
            margin-top: 20px;
            font-size: 18px;
        }
        input[type="number"] {
            width: 100px;
            padding: 10px;
            font-size: 16px;
            margin-bottom: 10px;
        }
        button {
            padding: 10px 20px;
            font-size: 16px;
            cursor: pointer;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Fibonacci Series</h2>
        <form action="" method="get">
            <label for="numSeries">Number of Series:</label>
            <input type="number" id="numSeries" name="numSeries" min="1" value="10">
            <button type="submit">Generate</button>
        </form>
        <div id="output">
            <% 
                if (request.getParameter("numSeries") != null) {
                    int numSeries = Integer.parseInt(request.getParameter("numSeries"));
                    int a = 0, b = 1;
                    out.print("Fibonacci Series:");
                    for (int i = 0; i < numSeries; i++) {
                        out.print(" " + a);
                        int c = a + b;
                        a = b;
                        b = c;
                    }
                }
            %>
        </div>
    </div>
</body>
</html>
