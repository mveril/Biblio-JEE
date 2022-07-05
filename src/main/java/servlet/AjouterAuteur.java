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
		
		Auteur auteur = new Auteur();
		auteur.setNom(nom);
		auteur.setPrenom(prenom);
		auteur.setTelephone(telephone);
		auteur.setEmail(email);
		if(erreurs.isEmpty()) {
		  try {
  		    auteurDao.creer(auteur);
	  	  } catch (DaoException e) {
            e.printStackTrace();
		  }
		response.sendRedirect( request.getContextPath() + "/liste-auteurs" );
		} else {
			request.setAttribute("auteur", auteur);
			request.setAttribute("erreurs", erreurs);
			request.setAttribute("resultat", "Echec de la sauvegarde de l'auteur.");
			
			this.getServletContext().getRequestDispatcher("/WEB-INF/AjouterAuteur.jsp").forward(request, response);
		}
	}

}
