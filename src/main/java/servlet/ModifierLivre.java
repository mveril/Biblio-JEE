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
import model.Livre;

/**
 * Servlet implementation class ModifierLivre
 */
@WebServlet("/modifier-livre")
public class ModifierLivre extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Dao<Livre> livreDao;
	private Dao<Auteur> auteurDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifierLivre() {
        super();
        var factory = DaoFactory.getInstance();
        livreDao = factory.getLivreDao();
        auteurDao = factory.getAuteurDao();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		var idStr = request.getParameter("id");
		try {
			var id = Long.parseLong(idStr);
			var livre = livreDao.trouver(id);
			request.setAttribute("auteurs", auteurDao.lister());
			request.setAttribute("livre", livre);
			this.getServletContext().getRequestDispatcher("/WEB-INF/ModifierLivre.jsp").forward(request, response);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException ex) {
			
		}
		response.sendRedirect( request.getContextPath() + "/liste-livres" );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		var idStr = request.getParameter("id");
		String titre = request.getParameter("titre");
		String nbPagesStr = request.getParameter("nbPages");
		String auteurIdStr = request.getParameter("auteurId");
		String categorie = request.getParameter("categorie");
		try {
			var id = Long.parseLong(idStr);
			var nbPages = Integer.parseInt(nbPagesStr);
			var auteurId = Long.parseLong(auteurIdStr);
			var livre = livreDao.trouver(id);
			livre.setTitre(titre);
			livre.setAuteur(auteurDao.trouver(auteurId));
			livre.setNbPages(nbPages);
			livre.setCategorie(categorie);
	        livreDao.mettreajour(livre);
		} catch (DaoException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}  catch (NumberFormatException ex) {
		    // TODO Auto-generated catch block
		    ex.printStackTrace();
	    }
		response.sendRedirect( request.getContextPath() + "/liste-livres" );
	}

}
