

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoopingServlet
 */
@WebServlet("/LoopingServlet")
public class LoopingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoopingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = "";

		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:ora1/ora1@localhost:1521:orcl");
			PreparedStatement pstmt = con.prepareStatement("select name, breed from pet");
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				name += rs.getString(1) + "&nbspthe&nbsp" + rs.getString(2) + "<br>";
			}
			rs.close();
			con.close();
		}catch (SQLException e) {
			e.printStackTrace();
			name = "sql error";
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
			name = "class not found error";
		}
		request.setAttribute("messages", name);
		request.getRequestDispatcher("/customer.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
