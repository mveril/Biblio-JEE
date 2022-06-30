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
import model.Livre;

/**
 * Servlet implementation class SupprimerLivre
 */
@WebServlet("/supprimer-livre")
public class SupprimerLivre extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Dao<Livre> livreDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupprimerLivre() {
        super();
        livreDao = DaoFactory.getInstance().getLivreDao();
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
				livreDao.supprimer(id);
			} catch(NumberFormatException e) {
				
			} catch (DaoException e) {
				// TODO Auto-generated catch block
			}
			response.sendRedirect(request.getContextPath() + "/liste-livres");
		}
	}

}
