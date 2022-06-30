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
 * Servlet implementation class AjouterAuteur
 */
@WebServlet("/ajouter-auteur")
public class AjouterAuteur extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private Dao<Auteur> auteurDao;
	
    public AjouterAuteur() {
        super();
        auteurDao = DaoFactory.getInstance().getAuteurDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/AjouterAuteur.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String telephone = request.getParameter("telephone");
		String email = request.getParameter("email");
		
		Auteur auteur = new Auteur();
		auteur.setNom(nom);
		auteur.setPrenom(prenom);
		auteur.setTelephone(telephone);
		auteur.setEmail(email);
		
		try {
			auteurDao.creer(auteur);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		response.sendRedirect( request.getContextPath() + "/liste-auteurs" );
	}

}
