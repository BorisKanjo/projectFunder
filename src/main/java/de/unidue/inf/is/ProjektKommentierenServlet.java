package de.unidue.inf.is;

import de.unidue.inf.is.domain.Kommentar;
import de.unidue.inf.is.domain.Projekt;
import de.unidue.inf.is.domain.Schreibt;
import de.unidue.inf.is.stores.PFAppStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public final class ProjektKommentierenServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int kennung = Integer.parseInt(request.getParameter("kennung"));

        PFAppStore projekt = new PFAppStore();
        Projekt proj = projekt.getProjektInfo(kennung);
        request.setAttribute("projekt", proj);


        request.getRequestDispatcher("/commentProject.ftl").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int kennung = Integer.parseInt(request.getParameter("kennung"));
        String user = "dummy@dummy.com";

        String comment = request.getParameter("comment");
        String anonym = request.getParameter("anonoym");

        PFAppStore projekt1 = new PFAppStore();
        Projekt proj = projekt1.getProjektInfo(kennung);

        PFAppStore projekt2 = new PFAppStore();
        int commentId = projekt2.getNumberOfKomment() +1;

        Kommentar kommentar = new Kommentar(commentId, comment, anonym);
        Schreibt schreibt = new Schreibt(kennung, commentId, user);

            

        if (projekt1.updateKommentar(kommentar) && projekt2.updateSchreibt(schreibt)) {
            response.sendRedirect("viewProject");
        }
        else{
            doGet(request, response);
        }


    }

}
