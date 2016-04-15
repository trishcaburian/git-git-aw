import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet(name = "PopulateLocation", urlPatterns = {"/PopulateLocation"})
public class PopulateLocation extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		PostgreSQLClient db = new PostgreSQLClient();

		int row = 0;
		int total = 0;

		try{

			db.createTable();
			row = db.addLocation();
			total = db.getAllLocations();
		}catch(Exception e){
			out.println(e.getMessage());
		}

		out.println(row);
		out.println(total);

	}
}