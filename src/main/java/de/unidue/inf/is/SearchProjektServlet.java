package de.unidue.inf.is;

import de.unidue.inf.is.domain.Projekt;
import de.unidue.inf.is.stores.PFAppStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public final class SearchProjektServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String search = request.getParameter("search");

        PFAppStore projekt = new PFAppStore();
        List<Projekt> proj1 = projekt.getProjekts(search);
        request.setAttribute("projekt", proj1);




        request.getRequestDispatcher("/searchProject.ftl").forward(request, response);
    }
}
