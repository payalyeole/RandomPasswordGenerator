package pass.generator;

import javax.servlet.RequestDispatcher;
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

        // Set password as an attribute for the JSP page
        request.setAttribute("generatedPassword", password);

        RequestDispatcher rd = request.getRequestDispatcher("/output.jsp");
        rd.forward(request, response);
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
