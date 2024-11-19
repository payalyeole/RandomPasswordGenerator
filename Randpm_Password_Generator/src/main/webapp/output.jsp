<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Password Generator</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background: linear-gradient(to right, #4facfe, #00f2fe);
            color: #333;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            background: white;
            padding: 2rem;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
            text-align: center;
        }
        h1 {
            font-size: 1.8rem;
            color: #4facfe;
            margin-bottom: 1rem;
        }
        p {
            font-size: 1.2rem;
            color: #333;
            margin-bottom: 1.5rem;
        }
        a {
            display: inline-block;
            text-decoration: none;
            background: #4facfe;
            color: white;
            padding: 0.8rem 1.5rem;
            border-radius: 5px;
            font-size: 1rem;
            transition: background 0.3s ease;
        }
        a:hover {
            background: #00c6fb;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Your Generated Password:</h1>
        <p><strong>${generatedPassword}</strong></p>
        <a href="index.jsp">Generate Another Password</a>
    </div>
</body>
</html>
