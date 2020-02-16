package de.unidue.inf.is;

import de.unidue.inf.is.domain.Projekt;
import de.unidue.inf.is.stores.PFAppStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public final class HomeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PFAppStore projOffen = new PFAppStore();
        PFAppStore projZu = new PFAppStore();
        List<Projekt> proj1 = projOffen.getOpenProjekt();
        request.setAttribute("projOffen", proj1);
        List<Projekt> proj2 = projZu.getClosedProjekt();
        request.setAttribute("projZu", proj2);




        request.getRequestDispatcher("/home.ftl").forward(request, response);
    }
}
