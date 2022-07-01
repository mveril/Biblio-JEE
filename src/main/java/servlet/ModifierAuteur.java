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
 * Servlet implementation class ModifierAuteur
 */
@WebServlet("/modifier-auteur")
public class ModifierAuteur extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Dao<Auteur> auteurDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifierAuteur() {
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
		var id = Long.parseLong(idStr);
		try {
			var auteur = auteurDao.trouver(id);
			request.setAttribute("auteur", auteur);
			this.getServletContext().getRequestDispatcher("/WEB-INF/ModifierAuteur.jsp").forward(request, response);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			response.sendRedirect( request.getContextPath() + "/liste-auteurs" );
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		var idStr = request.getParameter("id");
		var id = Long.parseLong(idStr);
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String telephone = request.getParameter("telephone");
		String email = request.getParameter("email");
		
		Auteur auteur;
		try {
			auteur = auteurDao.trouver(id);
			auteur.setNom(nom);
			auteur.setPrenom(prenom);
			auteur.setTelephone(telephone);
			auteur.setEmail(email);
	        auteurDao.mettreajour(auteur);
		} catch (DaoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		response.sendRedirect( request.getContextPath() + "/liste-auteurs" );
	}

}
