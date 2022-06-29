package servlet;

import java.io.IOException;
import java.util.List;

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
 * Servlet implementation class ListeLivres
 */
@WebServlet("/liste-livres")
public class ListeLivres extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Dao<Livre> livreDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListeLivres() {
        super();
        livreDao = DaoFactory.getInstance().getLivreDao();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		var idStr=request.getParameter("id");
		if(idStr==null) {
			List<Livre> livres=null;
			try {
				livres = livreDao.lister();
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("livres", livres);
			this.getServletContext().getRequestDispatcher("/WEB-INF/ListeLivres.jsp").forward(request, response);
		} else {
			var id = Long.parseLong(idStr);
			try {
				var livre = livreDao.trouver(id);
				request.setAttribute("livre", livre);
				this.getServletContext().getRequestDispatcher("/WEB-INF/DetailsLivre.jsp").forward(request, response);
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
