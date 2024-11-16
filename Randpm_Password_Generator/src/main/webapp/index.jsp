<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Random Password Generator</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <div class="container">
        <!-- Flex container to align the image on the left and the form on the right -->
        <div class="content">
            <div class="image-section">
                <img src="images/password-generator.webp" alt="Password Generator" class="header-img">
            </div>
            <div class="form-section">
                <h1>Password Generator</h1>
                <form action="GeneratePasswordServlet" method="post" class="password-form">
                    <div class="form-group">
                        <label for="length">Password Length:</label>
                        <input type="number" id="length" name="length" min="4" required>
                    </div>
                    
                    <div class="form-group">
                        <label>Include Characters:</label>
                        <div class="checkbox-group">
                            <input type="checkbox" id="uppercase" name="options" value="uppercase">
                            <label for="uppercase">Uppercase Letters</label><br>
                            <input type="checkbox" id="lowercase" name="options" value="lowercase">
                            <label for="lowercase">Lowercase Letters</label><br>
                            <input type="checkbox" id="numbers" name="options" value="numbers">
                            <label for="numbers">Numbers</label><br>
                            <input type="checkbox" id="special" name="options" value="special">
                            <label for="special">Special Characters</label><br>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label for="username">Your Username:</label>
                        <input type="text" id="username" name="username" required>
                    </div>
                    
                    <button type="submit" class="generate-btn">Generate Password</button>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
