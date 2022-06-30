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
 * Servlet implementation class AjouterLivre
 */
@WebServlet("/ajouter-livre")
public class AjouterLivre extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Dao<Livre> livreDao;
	private Dao<Auteur> auteurDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjouterLivre() {
        super();
        // TODO Auto-generated constructor stub
        livreDao = DaoFactory.getInstance().getLivreDao();
        auteurDao = DaoFactory.getInstance().getAuteurDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
		    request.setAttribute("auteurs", auteurDao.lister());
		} catch (DaoException e) {
			
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/AjouterLivre.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			var title = request.getParameter("titre");
			var nbPagesStr = request.getParameter("nbPages");
			var nbPages = Integer.parseInt(nbPagesStr);
			var categorie = request.getParameter("categorie");
			var auteurIdStr = request.getParameter("auteurId");
		    var auteurId = Long.parseLong(auteurIdStr);
			var	auteur = auteurDao.trouver(auteurId);		
			var livre = new Livre();
			livre.setTitre(title);
			livre.setNbPages(nbPages);
			livre.setCategorie(categorie);
			livre.setAuteur(auteur);
			livreDao.creer(livre);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath() + "/liste-livres");
	}

}
