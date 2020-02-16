package de.unidue.inf.is;

import de.unidue.inf.is.domain.Projekt;
import de.unidue.inf.is.stores.PFAppStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public final class ProjektEditierenServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int kennung = Integer.parseInt(request.getParameter("kennung"));


        PFAppStore project = new PFAppStore();
        Projekt proj = project.getProjektEdit(kennung);
        request.setAttribute("project", proj);

        PFAppStore projPrev = new PFAppStore();
        List<Projekt> proj1 = projPrev.getPreviousProject(proj.getErsteller());
        request.setAttribute("projPrev", proj1);


        request.getRequestDispatcher("/editProject.ftl").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int kennung = Integer.parseInt(request.getParameter("kennung"));
        PFAppStore project = new PFAppStore();

        String titel = request.getParameter("titel");
        String beschreibung = request.getParameter("beschreibung");
        //satus is offen by default
        String finanzlimit1 = request.getParameter("finanzlimit");
        double finanzlimit = Double.parseDouble(finanzlimit1);
        String ersteller = project.getBenutzer(kennung);
        //how do we convert string to short to get the ids of kat and vorgaenger.
        //String kategorie = request.getParameter("kategorie");
        String kat= request.getParameter("kategorie");
        String vor= request.getParameter("vorgaenger");

        PFAppStore  katStore = new PFAppStore();
        int kategorie = katStore.getKategorie(kat);

        PFAppStore newProj = new PFAppStore();
        int vorgaenger = newProj.getVorgaenger(vor);


        Projekt proj2 = new Projekt(kennung, titel, beschreibung, finanzlimit, ersteller, kategorie, vorgaenger);
        if (newProj.updateProjekt(proj2)) {
            response.sendRedirect("viewProject");
        }
        else{
            doGet(request, response);
        }


    }
}
