package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;
import dao.DaoException;
import dao.DaoFactory;
import model.Auteur;

/**
 * Servlet implementation class SupprimerAuteur
 */
@WebServlet("/supprimer-auteur")
public class SupprimerAuteur extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Dao<Auteur> auteurDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupprimerAuteur() {
        super();
        auteurDao = DaoFactory.getInstance().getAuteurDao();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		var idStr = request.getParameter("id");
		if(idStr != null) {
			try {
				var id = Long.parseLong(idStr);
				auteurDao.supprimer(id);
			} catch(NumberFormatException e) {
				
			} catch (DaoException e) {
				// TODO Auto-generated catch block
			}
			response.sendRedirect(request.getContextPath() + "/liste-auteurs");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
