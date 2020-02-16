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

public final class BenutzerProfilServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        PFAppStore userInfo = new PFAppStore();
        Benutzer user = userInfo.getErstellerInfo();
        request.setAttribute("userInfo", user);

        PFAppStore anz = new PFAppStore();
        int anzErstellte = anz.getNumberCreatedProjekt();
        request.setAttribute("anz", anzErstellte);

        PFAppStore unter = new PFAppStore();
        int anzUnterstuetzte = unter.getNumberSupportedProjekt();
        request.setAttribute("unter", anzUnterstuetzte);


        PFAppStore erstProj = new PFAppStore();
        PFAppStore untProj = new PFAppStore();
        List<Projekt> proj1 = erstProj.getCreatedProject();
        request.setAttribute("erstProj", proj1);
        List<Projekt> proj2 = untProj.getSupportedProject();
        request.setAttribute("untProj", proj2);

        request.getRequestDispatcher("/myProfile.ftl").forward(request, response);
    }

}
