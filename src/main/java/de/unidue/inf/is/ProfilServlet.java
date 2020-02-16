package de.unidue.inf.is;

import de.unidue.inf.is.domain.Benutzer;
import de.unidue.inf.is.domain.Projekt;
import de.unidue.inf.is.stores.PFAppStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public final class ProfilServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int kennung =  Integer.parseInt(request.getParameter("kennung"));


        PFAppStore userInfo = new PFAppStore();
        Benutzer user = userInfo.getErstellerByKennung(kennung);
        request.setAttribute("userInfo", user);

        PFAppStore anz = new PFAppStore();
        int anzErstellte = anz.getNumberCreatedProjektByErsteller(user.getEmail());
        request.setAttribute("anz", anzErstellte);

        PFAppStore unter = new PFAppStore();
        int anzUnterstuetzte = unter.getNumberSupportedProjektBySpender(user.getEmail());
        request.setAttribute("unter", anzUnterstuetzte);


        PFAppStore erstProj = new PFAppStore();
        PFAppStore untProj = new PFAppStore();
        List<Projekt> proj1 = erstProj.getCreatedProjectByErsteller(user.getEmail());
        request.setAttribute("erstProj", proj1);
        List<Projekt> proj2 = untProj.getSupportedProjectBySpender(user.getEmail());
        request.setAttribute("untProj", proj2);

        request.getRequestDispatcher("/viewProfile.ftl").forward(request, response);
    }
}
