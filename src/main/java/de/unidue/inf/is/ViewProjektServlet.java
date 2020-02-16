package de.unidue.inf.is;

import de.unidue.inf.is.domain.Projekt;
import de.unidue.inf.is.domain.Schreibt;
import de.unidue.inf.is.domain.Spenden;
import de.unidue.inf.is.stores.PFAppStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public final class ViewProjektServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        int kennung =  Integer.parseInt(request.getParameter("kennung"));


        PFAppStore projekt = new PFAppStore();
        Projekt proj = projekt.getProjektInfo(kennung);
        request.setAttribute("projekt", proj);

        PFAppStore projekt1 = new PFAppStore();
        double spendensumme = projekt1.getSpendensummeOfProject(kennung);
        request.setAttribute("projekt1", spendensumme);

        PFAppStore projekt2 = new PFAppStore();
        Projekt proj1 = projekt2.getProjektInfo(proj.getVorgaenger());
        request.setAttribute("projekt2", proj1);


        PFAppStore spender = new PFAppStore();
        List<Spenden> spender1 = spender.getSpender(kennung);
        request.setAttribute("spender", spender1);


        PFAppStore commentator = new PFAppStore();
        List<Schreibt> commentator1 = commentator.getCommentator(kennung);
        request.setAttribute("commentator", commentator1);

        request.getRequestDispatcher("/viewProject.ftl").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        String user = "dummy@dummy.com";
        int kennung =  Integer.parseInt(request.getParameter("kennung"));


        PFAppStore projekt = new PFAppStore();
        Projekt proj = projekt.getProjektInfo(kennung);
        String spender = projekt.checkSpender(user);

        List<Spenden> spenders = projekt.getSpenders(kennung);

       if(proj.getStatus()=="offen" && spender!=user) {
            response.sendRedirect("fundProject");
       }else
       { doGet(request, response); }

       if(projekt.deleteKommentar(kennung) && projekt.giveGuthaben(spenders) && projekt.deleteSpenden(kennung) && projekt.deleteProjekt(kennung)) {
           response.sendRedirect("home");
       }else {doGet(request, response);}

    }
}
