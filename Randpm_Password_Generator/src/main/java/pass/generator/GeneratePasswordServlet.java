package pass.generator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.sql.*;

@WebServlet("/GeneratePasswordServlet")
public class GeneratePasswordServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int length = Integer.parseInt(request.getParameter("length"));
        String[] options = request.getParameterValues("options");
        String username = request.getParameter("username");

        String characters = "";
        if (options != null) {
            for (String option : options) {
                switch (option) {
                    case "uppercase": characters += "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; break;
                    case "lowercase": characters += "abcdefghijklmnopqrstuvwxyz"; break;
                    case "numbers": characters += "0123456789"; break;
                    case "special": characters += "!@#$%^&*()_+[]{}|;:',.<>?/`~"; break;
                }
            }
        }

        if (characters.isEmpty()) {
            characters = "abcdefghijklmnopqrstuvwxyz"; // Default to lowercase
        }

        String password = generatePassword(characters, length);

        // Store in database
        storePassword(username, password);

        // Output password
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Password Generator</title>");
        out.println("<style>");
        out.println("body {");
        out.println("    font-family: Arial, sans-serif;");
        out.println("    margin: 0;");
        out.println("    padding: 0;");
        out.println("    background: linear-gradient(to right, #4facfe, #00f2fe);");
        out.println("    color: #333;");
        out.println("    display: flex;");
        out.println("    justify-content: center;");
        out.println("    align-items: center;");
        out.println("    height: 100vh;");
        out.println("}");
        out.println(".container {");
        out.println("    background: white;");
        out.println("    padding: 2rem;");
        out.println("    border-radius: 10px;");
        out.println("    box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);");
        out.println("    text-align: center;");
        out.println("}");
        out.println("h1 {");
        out.println("    font-size: 1.8rem;");
        out.println("    color: #4facfe;");
        out.println("    margin-bottom: 1rem;");
        out.println("}");
        out.println("p {");
        out.println("    font-size: 1.2rem;");
        out.println("    color: #333;");
        out.println("    margin-bottom: 1.5rem;");
        out.println("}");
        out.println("a {");
        out.println("    display: inline-block;");
        out.println("    text-decoration: none;");
        out.println("    background: #4facfe;");
        out.println("    color: white;");
        out.println("    padding: 0.8rem 1.5rem;");
        out.println("    border-radius: 5px;");
        out.println("    font-size: 1rem;");
        out.println("    transition: background 0.3s ease;");
        out.println("}");
        out.println("a:hover {");
        out.println("    background: #00c6fb;");
        out.println("}");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='container'>");
        out.println("<h1>Your Generated Password:</h1>");
        out.println("<p><strong>" + password + "</strong></p>");
        out.println("<a href='index.jsp'>Generate Another Password</a>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");

    }

    private String generatePassword(String characters, int length) {
        StringBuilder password = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            password.append(characters.charAt(random.nextInt(characters.length())));
        }
        return password.toString();
    }

    private void storePassword(String username, String password) {

    	
    	String url = "jdbc:mysql://localhost:3306/PasswordGenerator";
    	String user = "root";
    	String Pass = "Payal@123";
    	
    	Connection conn = null;
    	PreparedStatement stmt = null;
    	
    	try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url , user , Pass);
			stmt = conn.prepareStatement("INSERT INTO Passwords (username, password) VALUES (?, ?)");
			stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	finally {
    		close(conn, stmt);
			
		}
    }

	private void close(Connection conn, PreparedStatement stmt) {
		try {
			if(stmt != null) {    				
				stmt.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			if(conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
