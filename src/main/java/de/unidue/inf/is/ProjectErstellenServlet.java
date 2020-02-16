package de.unidue.inf.is;

import de.unidue.inf.is.domain.Kategorie;
import de.unidue.inf.is.domain.Projekt;
import de.unidue.inf.is.stores.PFAppStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public final class ProjectErstellenServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    String user = "dummy@dummy.com";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PFAppStore projPrev = new PFAppStore();
        List<Projekt> proj1 = projPrev.getPreviousProject(user);
        request.setAttribute("projPrev", proj1);

        PFAppStore projekt = new PFAppStore();
        int kennung =projekt.getNumberOfProjects() + 1;
        request.setAttribute("projekt", kennung);

        PFAppStore projekt1 = new PFAppStore();
        List<Kategorie> kategorie = projekt1.getKategorien();
        request.setAttribute("projekt1", kategorie);






        request.getRequestDispatcher("/newProject.ftl").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String titel = request.getParameter("titel");
        String beschreibung = request.getParameter("beschreibung");
        //satus is offen by default
        String finanzlimit1 = request.getParameter("finanzlimit");
        double finanzlimit = Double.parseDouble(finanzlimit1);
        String ersteller = user;
        //how do we convert string to short to get the ids of kat and vorgaenger.
        String kategorie = request.getParameter("kategorie");
        String vorgaenger = request.getParameter("vorgaenger");
        //String kat1= request.getParameter("kategorie");


        int kat = Integer.parseInt(kategorie);
        int vor= Integer.parseInt(vorgaenger);

       /* PFAppStore  katStore = new PFAppStore();
        int kategorie = katStore.getKategorie(kat);*/

        PFAppStore newProj = new PFAppStore();
        /*int vorgaenger = newProj.getVorgaenger(vor);*/
        int kennung = newProj.getNumberOfProjects() + 1;

        Projekt proj2 = new Projekt(kennung, titel, beschreibung, finanzlimit, ersteller, vor, kat);
        if (newProj.addProjekt(proj2)) {
            response.sendRedirect("home");
        }
        else{
                doGet(request, response);
            }


    }

}
