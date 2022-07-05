package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
        Map<String, String> erreurs = new HashMap<String, String>();
		
		if(nom != null) {
			if(nom.length() < 2 || nom.length() > 20) {
				erreurs.put("nom", "Un nom d'auteur doit contenir entre 2 et 20 caractères.");
			}
		} else {
			erreurs.put("nom", "Merci d'entrer un nom d'auteur.");
		}
		
		if(prenom != null) {
			if(prenom.length() > 20) {
				erreurs.put("prenom", "Un prénom d'auteur doit avoir au maximum 20 caractères.");
			}
		}
		
		if(telephone != null) {
			if(telephone.length() > 10) {
				erreurs.put("telephone", "Un numéro de téléphone doit avoir au maximum 10 caractères.");
			}
			if(!telephone.matches("^\\d+$")) {
				erreurs.put("telephone", "Un numéro de téléphone doit contenir uniquement des chiffres.");
			}
		} else {
			erreurs.put("telephone", "Merci d'entrer un numéro de téléphone.");
		}
		
		if(email != null) {
			if(email.length() > 60) {
				erreurs.put("email", "Un email doit avoir au maximum 60 caractères.");
			}
			if(!email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
				erreurs.put("email", "merci de saisir une adresse mail valide.");
			}
		}
		Auteur auteur = null;
		try {
			auteur = auteurDao.trouver(id);
		} catch (DaoException e1) {
			auteur = new Auteur();
			erreurs.put("auteur", "Erreur l'auteur n'existe pas.");
		}
		auteur.setNom(nom);
		auteur.setPrenom(prenom);
		auteur.setTelephone(telephone);
		auteur.setEmail(email);
		
		if(erreurs.isEmpty()) { 
			try {
	           auteurDao.mettreajour(auteur);
		    } catch (DaoException e1) {
		    	// TODO Auto-generated catch block
			    e1.printStackTrace();
		    }
		    response.sendRedirect( request.getContextPath() + "/liste-auteurs" );
		} else {
			request.setAttribute("auteur", auteur);
			request.setAttribute("erreurs", erreurs);
			request.setAttribute("resultat", "Echec de la sauvegarde l'auteur.");
			
			this.getServletContext().getRequestDispatcher("/WEB-INF/ModifierAuteur.jsp").forward(request, response);
		}
	}

}
