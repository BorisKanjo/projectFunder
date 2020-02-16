package de.unidue.inf.is;

import de.unidue.inf.is.domain.Benutzer;
import de.unidue.inf.is.domain.Projekt;
import de.unidue.inf.is.domain.Spenden;
import de.unidue.inf.is.stores.PFAppStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public final class ProjektSpendenServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int kennung = Integer.parseInt(request.getParameter("kennung"));

        PFAppStore projekt = new PFAppStore();
        Projekt proj = projekt.getProjektInfo(kennung);
        request.setAttribute("projekt", proj);



        request.getRequestDispatcher("/fundProject.ftl").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String user = "dummy@dummy.com";

        int kennung = Integer.parseInt(request.getParameter("kennung"));

        double spendenbetrag = Double.parseDouble(request.getParameter("spendenbetrag"));
        String sichtbarkeit = request.getParameter("privat");

        PFAppStore projekt = new PFAppStore();
        Projekt proj = projekt.getProjektInfo(kennung);

        Spenden spenden = new Spenden(user, kennung, spendenbetrag,sichtbarkeit);
        Benutzer benutzer = new Benutzer();

        double guthaben = projekt.getGuhaben();


        if(spendenbetrag >0.0 && guthaben>spendenbetrag){

        if (projekt.updateKonto(spendenbetrag) && projekt.fundProjekt(spenden) ) {
            response.sendRedirect("viewProject");
        }
        else{
            doGet(request, response);
        }
        }

    }
}
