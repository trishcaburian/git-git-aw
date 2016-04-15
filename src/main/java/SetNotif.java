import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet(name = "SetNotif", urlPatterns = {"/SetNotif"})
public class SetNotif extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String choice = request.getParameter("notif");

		PrintWriter out = response.getWriter();

		out.println(choice);
		if(choice.equals("location")){

			try{
				PostgreSQLClient db = new PostgreSQLClient();

				String[] loc = request.getParameterValues("loc");

				for(String s: loc){
					//out.println(db.getLocID(s));
					out.println(s);
				}

			}catch(Exception e){
				out.println(e.getMessage());
			}
		}
    	
    }
}