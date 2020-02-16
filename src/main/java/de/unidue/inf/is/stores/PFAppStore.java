package de.unidue.inf.is.stores;

import de.unidue.inf.is.domain.*;
import de.unidue.inf.is.utils.DBUtil;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class PFAppStore implements Closeable {

    private Connection connection;
    private boolean complete;

    public PFAppStore () throws StoreException {
        try {
            connection = DBUtil.getExternalConnection();
            connection.setAutoCommit(false);
        }
        catch (SQLException e) {
            throw new StoreException(e);
        }
    }


    public String getBenutzer(int projektKennung) {
        String results="";

        try {
            PreparedStatement ps =connection.prepareStatement("select b.email as ertseller" +
                    "   from dbp052.projekt p, dbp052.benutzer " +
                    "   where p.ersteller = b.email and" +
                    "        p.kennung = ?");
            ps.setInt(1, projektKennung);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                results = rs.getString("email");
            }
        }catch (SQLException e) {
            throw new  StoreException(e);
        }
        return results;
    }


    public Benutzer getErstellerInfo() throws StoreException{
        Benutzer results = new Benutzer();

        try {
            PreparedStatement ps = connection.prepareStatement("select b.email as ersteller, b.name" +
                    "                     from dbp052.projekt p, dbp052.benutzer b" +
                    "                    where p.ersteller = b.email and" +
                    "                          b.name = 'DummyUser' ");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String ersteller = rs.getString("ersteller");
                String name = rs.getString("name");
                results= new Benutzer(ersteller, name);
            }
        }catch (SQLException e) {
            throw new  StoreException(e);
        }
        return results;

    }

    public Benutzer getErstellerByKennung(int projektKennung) throws StoreException{
        Benutzer results = new Benutzer();

        try {
            PreparedStatement ps = connection.prepareStatement("select b.email as ersteller, b.name" +
                    "                     from dbp052.projekt p, dbp052.benutzer b" +
                    "                    where p.ersteller = b.email and" +
                    "                          p.kennung = ?");

            ps.setInt(1, projektKennung);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String ersteller = rs.getString("ersteller");
                String name = rs.getString("name");
                results= new Benutzer(ersteller, name);
            }
        }catch (SQLException e) {
            throw new  StoreException(e);
        }
        return results;

    }




    public ArrayList<Spenden> getSpender(int kennung) {
        ArrayList<Spenden> results = new ArrayList<>();

        try {
            PreparedStatement ps =connection.prepareStatement("select s.spender, b.name as benutzername, s.projekt, s.spendenbetrag, s.sichtbarkeit" +
                    "   from dbp052.spenden s, dbp052.benutzer b" +
                    "   where s.spender = b.email and" +
                    "      s.projekt=?");
            ps.setInt(1, kennung);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String spender = rs.getString("spender");
                String benutzername = rs.getString("benutzername");
                int projekt = rs.getInt("projekt");
                double spendenbetrag = rs.getDouble("spendenbetrag");
                String sichtbarkeit = rs.getString("sichtbarkeit");
                results.add(new Spenden(spender,benutzername, projekt, spendenbetrag, sichtbarkeit));
            }
        }catch (SQLException e) {
            throw new  StoreException(e);
        }
        return results;
    }


    public double getSpendensummeOfProject(int kennung) {
        double results = 0.0;

        try {
            PreparedStatement ps =connection.prepareStatement("select sum(spendenbetrag) as spendensumme from dbp052.spenden where projekt=?");
            ps.setInt(1, kennung);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                results = rs.getDouble("spendensumme");
            }
        }catch (SQLException e) {
            throw new  StoreException(e);
        }
        return results;

    }

    public ArrayList<Schreibt> getCommentator(int kennung) {
        ArrayList<Schreibt> results = new ArrayList<>();

        try {
            PreparedStatement ps =connection.prepareStatement("select s.benutzer, b.name, s.projekt, s.kommentar" +
                    "   from dbp052.schreibt s, dbp052.benutzer b" +
                    "   where s.benutzer = b.email and" +
                    "      s.projekt = ?");
            ps.setInt(1, kennung);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String benutzer = rs.getString("benutzer");
                String name = rs.getString("name");
                int projekt = rs.getInt("projekt");
                int kommentar = rs.getInt("kommentar");
                results.add(new Schreibt(benutzer, name, projekt, kommentar));
            }
        }catch (SQLException e) {
            throw new  StoreException(e);
        }
        return results;
    }


    public ArrayList<Projekt> getOpenProjekt() {
        ArrayList<Projekt> results = new ArrayList<>();

        try {

            PreparedStatement ps = connection.prepareStatement("select icon, kennung, titel, benutzername, max(spendensumme) as Spendensumme" +
                    "     from" +
                    "     (select k.icon, p.kennung, p.titel, b.name as benutzername, sum(s.spendenbetrag) as spendensumme" +
                    "     from dbp052.kategorie k, dbp052.projekt p, dbp052.spenden s, dbp052.benutzer b" +
                    "     where k.id = p.kategorie and" +
                    "         b.email = p.ersteller and " +
                    "         s.projekt = p.kennung and " +
                    "         p.status = 'offen'" +
                    "     group by k.icon,p.kennung, p.titel, b.name" +
                    "     UNION" +
                    "     select k.icon, p.kennung, p.titel, b.name as benutzername, 0 as spendensumme" +
                    "     from dbp052.kategorie k, dbp052.projekt p, dbp052.benutzer b" +
                    "     where k.id = p.kategorie and " +
                    "         b.email = p.ersteller and " +
                    "         p.status = 'offen')" +
                    "     group by icon, kennung, titel, benutzername");
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                String icon = rs.getString("icon");
                int kennung = rs.getInt("kennung");
                String titel = rs.getString("titel");
                String benutzername = rs.getString("benutzername");
                double spendensumme = rs.getDouble("spendensumme");

                results.add(new Projekt(icon,kennung, titel, benutzername, spendensumme));
            }
        } catch (SQLException e) {
            throw new StoreException(e);
        }
        return results;
    }

    public ArrayList<Projekt> getClosedProjekt() {
        ArrayList<Projekt> results = new ArrayList<>();

        try {
            PreparedStatement ps = connection.prepareStatement("select k.icon, p.kennung, p.titel, b.name as benutzername, sum(s.spendenbetrag) as spendensumme" +
                    "                                      from dbp052.kategorie k,dbp052.projekt p, dbp052.benutzer b, dbp052.spenden s " +
                    "                                      where k.id = p.kategorie and" +
                    "                                            b.email = p.ersteller and" +
                    "                                            s.projekt = p.kennung and" +
                    "                                            p.status = 'geschlossen'" +
                    "                                      group by k.icon, p.kennung, p.titel, b.name");
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                String icon = rs.getString("icon");
                int kennung = rs.getInt("kennung");
                String titel = rs.getString("titel");
                String benutzername = rs.getString("benutzername");
                double spendensumme = rs.getDouble("spendensumme");

                results.add(new Projekt(icon, kennung, titel, benutzername, spendensumme));
            }
        } catch (SQLException e) {
            throw new StoreException(e);
        }
        return results;
    }

    public ArrayList<Projekt> getPreviousProject(String email) {
        ArrayList<Projekt> results = new ArrayList<>();

        try {
            PreparedStatement ps =connection.prepareStatement("select p.kennung, p.titel" +
                    "      from dbp052.projekt p, dbp052.benutzer b" +
                    "      where p.ersteller= b.email and" +
                    "            b.email=?");
            ps.setString(1,email);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                results.add(new Projekt(rs.getInt("kennung"),rs.getString("titel")));
            }
        }catch (SQLException e) {
            throw new  StoreException(e);
        }
        return results;
    }

    public int getNumberOfProjects() {
        int results =0;

        try {
            PreparedStatement ps =connection.prepareStatement("select count(kennung) as numberOfProjects from dbp052.projekt");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                results = rs.getInt("numberOfProjects");
            }
        }catch (SQLException e) {
            throw new  StoreException(e);
        }
        return results;
    }



    public boolean addProjekt (Projekt project) throws StoreException {
        boolean result;
        try{PreparedStatement ps = connection.prepareStatement("insert into dbp052.projekt(kennung, titel, beschreibung, finanzierungslimit, ersteller," +
                "           kategorie, vorgaenger) values (?, ?, ?, ?, ?, ?, ?)");

            ps.setInt(1, project.getKennung());
            ps.setString(2, project.getTitel());
            ps.setString(3, project.getBeschreibung());
            ps.setDouble(4, project.getFinanzierungslimit());
            ps.setInt(5, project.getErsteller1());
            ps.setInt(6, project.getKategorie());
            ps.setInt(7, project.getKategorie());

            ps.executeUpdate();

            result = true;

        } catch (SQLException e) {
            throw  new StoreException(e);
        }
        return true;
    }



    public ArrayList<Kategorie> getKategorien() {
        ArrayList<Kategorie> result = new ArrayList<Kategorie>();

        try {


            PreparedStatement ps = connection.prepareStatement("select * from dbp052.kategorie");


            ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String icon = rs.getString("icon");


                    result.add(new Kategorie(id, name, icon));
                }

        }
        catch (SQLException e) {
            throw new StoreException(e);
        }

        return result;
    }




    public boolean updateProjekt (Projekt project) throws StoreException {
        boolean result;
        try{PreparedStatement ps = connection.prepareStatement("update dbp052.projekt set kennung=?, titel=?, beschreibung=?, finanzierungslimit=?, ersteller=?," +
                "           kategorie=?, vorgaenger=?");

            ps.setInt(1, project.getKennung());
            ps.setString(2, project.getTitel());
            ps.setString(3, project.getBeschreibung());
            ps.setDouble(4, project.getFinanzierungslimit());
            ps.setInt(5, project.getErsteller1());
            ps.setInt(6, project.getKategorie());
            ps.setInt(7, project.getKategorie());

            ps.executeUpdate();

            result = true;

        } catch (SQLException e) {
            throw  new StoreException(e);
        }
        return true;
    }

    public int getVorgaenger(String vorgaenger) {
        int results =0;

        try {
            PreparedStatement ps =connection.prepareStatement("select kennung from dbp052.projekt where titel=?");

            ps.setString(1, vorgaenger);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                results = rs.getInt("kennung");
            }
        }catch (SQLException e) {
            throw new  StoreException(e);
        }
        return results;
    }

    public int getKategorie(String kategorie) {
        int results =0;

        try {
            PreparedStatement ps =connection.prepareStatement("select id from dbp052.kategorie where name=?");

            ps.setString(1, kategorie);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                int id = rs.getInt("id");
                results = id;
            }
        }catch (SQLException e) {
            throw new  StoreException(e);
        }
        return results;
    }

    public int getNumberCreatedProjekt() {
        int results= 0;

        try {
            PreparedStatement ps =connection.prepareStatement("select count(titel) as createdProject" +
                    "   from dbp052.projekt" +
                    "   where ersteller='dummy@dummy.com'");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int createdProject = rs.getInt("createdProject");
                results = createdProject;
            }
        }catch (SQLException e) {
            throw new  StoreException(e);
        }
        return results;
    }

    public int getNumberCreatedProjektByErsteller(String ersteller) {
        int results= 0;

        try {
            PreparedStatement ps =connection.prepareStatement("select count(titel) as createdProject" +
                    "   from dbp052.projekt" +
                    "   where ersteller= ?");
            ps.setString(1, ersteller);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int createdProject = rs.getInt("createdProject");
                results = createdProject;
            }
        }catch (SQLException e) {
            throw new  StoreException(e);
        }
        return results;
    }

    public int getNumberSupportedProjekt() {
        int results= 0;

        try {
            PreparedStatement ps =connection.prepareStatement("select count(projekt) as supportedProject" +
                    "   from dbp052.spenden" +
                    "   where spender='dummy@dummy.com'");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int supportedProject = rs.getInt("supportedProject");
                results = supportedProject;
            }
        }catch (SQLException e) {
            throw new  StoreException(e);
        }
        return results;
    }

    public int getNumberSupportedProjektBySpender(String spender) {
        int results= 0;

        try {
            PreparedStatement ps =connection.prepareStatement("select count(projekt) as supportedProject" +
                    "   from dbp052.spenden" +
                    "   where spender= ?");
            ps.setString(1, spender);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int supportedProject = rs.getInt("supportedProject");
                results = supportedProject;
            }
        }catch (SQLException e) {
            throw new  StoreException(e);
        }
        return results;
    }

    public ArrayList<Projekt> getCreatedProject() {
        ArrayList<Projekt> results = new ArrayList<>();

        try {
            PreparedStatement ps =connection.prepareStatement("select icon, kennung, titel, status, max(spendensumme) as Spendensumme" +
                    "   from" +
                    "   (select k.icon, p.kennung, p.titel, p.status, sum(s.spendenbetrag) as spendensumme" +
                    "   from dbp052.kategorie k, dbp052.projekt p, dbp052.spenden s" +
                    "   where k.id = p.kategorie and " +
                    "      s.projekt = p.kennung and" +
                    "      p.ersteller = 'dummy@dummy.com'" +
                    "   group by k.icon, p.kennung, p.titel, p.status" +
                    "   UNION" +
                    "   select k.icon, p.kennung, p.titel, p.status, 0 as spendensumme" +
                    "   from dbp052.kategorie k, dbp052.projekt p" +
                    "   where k.id = p.kategorie and " +
                    "      p.ersteller = 'dummy@dummy.com')" +
                    "   group by icon, kennung, titel, status");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String icon = rs.getString("icon");
                int kennung = rs.getInt("kennung");
                String titel =rs.getString("titel");
                Double spendensumme =rs.getDouble("spendensumme");
                String status =rs.getString("status");
                results.add(new Projekt(icon,kennung, titel, spendensumme, status));
            }
        }catch (SQLException e) {
            throw new  StoreException(e);
        }
        return results;
    }

    public ArrayList<Projekt> getCreatedProjectByErsteller(String ersteller) {
        ArrayList<Projekt> results = new ArrayList<>();

        try {
            PreparedStatement ps =connection.prepareStatement("select icon, kennung, titel, status, max(spendensumme) as Spendensumme" +
                    "   from" +
                    "   (select k.icon, p.kennung, p.titel, p.status, sum(s.spendenbetrag) as spendensumme" +
                    "   from dbp052.kategorie k, dbp052.projekt p, dbp052.spenden s" +
                    "   where k.id = p.kategorie and " +
                    "      s.projekt = p.kennung and" +
                    "      p.ersteller = ?" +
                    "   group by k.icon, p.kennung, p.titel, p.status" +
                    "   UNION" +
                    "   select k.icon, p.kennung, p.titel, p.status, 0 as spendensumme" +
                    "   from dbp052.kategorie k, dbp052.projekt p" +
                    "   where k.id = p.kategorie and " +
                    "      p.ersteller = '?')" +
                    "   group by icon, kennung, titel, status");
            ps.setString(1, ersteller);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String icon = rs.getString("icon");
                int kennung = rs.getInt("kennung");
                String titel =rs.getString("titel");
                Double spendensumme =rs.getDouble("spendensumme");
                String status =rs.getString("status");
                results.add(new Projekt(icon,kennung, titel, spendensumme, status));
            }
        }catch (SQLException e) {
            throw new  StoreException(e);
        }
        return results;
    }

    public ArrayList<Projekt> getSupportedProject() {
        ArrayList<Projekt> results = new ArrayList<>();

        try {
            PreparedStatement ps =connection.prepareStatement("select k.icon, p.kennung, p.titel, p.finanzierungslimit, p.status, s.spendenbetrag" +
                    "   from dbp052.kategorie k, dbp052.projekt p, dbp052.spenden s" +
                    "   where p.kategorie=k.id and" +
                    "      p.kennung=s.projekt and" +
                    "      s.spender='dummy@dummy.com' and" +
                    "      s.sichtbarkeit='oeffentlich'");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String icon = rs.getString("icon");
                int kennung = rs.getInt("kennung");
                String titel =rs.getString("titel");
                Double finanzierungslimit =rs.getDouble("finanzierungslimit");
                String status =rs.getString("status");
                Double spendenbetrag =rs.getDouble("spendenbetrag");
                results.add(new Projekt(icon, kennung, titel, finanzierungslimit, status, spendenbetrag));
            }
        }catch (SQLException e) {
            throw new  StoreException(e);
        }
        return results;
    }

    public ArrayList<Projekt> getSupportedProjectBySpender(String spender) {
        ArrayList<Projekt> results = new ArrayList<>();

        try {
            PreparedStatement ps =connection.prepareStatement("select k.icon, p.kennung, p.titel, p.finanzierungslimit, p.status, s.spendenbetrag" +
                    "   from dbp052.kategorie k, dbp052.projekt p, dbp052.spenden s" +
                    "   where p.kategorie=k.id and" +
                    "      p.kennung=s.projekt and" +
                    "      s.spender=? and" +
                    "      s.sichtbarkeit='oeffentlich'");
            ps.setString(1, spender);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String icon = rs.getString("icon");
                int kennung = rs.getInt("kennung");
                String titel =rs.getString("titel");
                Double finanzierungslimit =rs.getDouble("finanzierungslimit");
                String status =rs.getString("status");
                Double spendenbetrag =rs.getDouble("spendenbetrag");
                results.add(new Projekt(icon, kennung, titel, finanzierungslimit, status, spendenbetrag));
            }
        }catch (SQLException e) {
            throw new  StoreException(e);
        }
        return results;
    }

    public Projekt getProjektInfo(int projektKennung) {
        Projekt results = null;

        try {

            PreparedStatement ps = connection.prepareStatement("select k.icon, p.kennung, p.titel, b.name, p.finanzierungslimit, p.status, p2.titel as vorgaenger, p.beschreibung" +
                    "      from dbp052.kategorie k, dbp052.projekt p, dbp052.benutzer b, dbp052.projekt p2" +
                    "      where p.kategorie = k.id and" +
                    "            p.ersteller = b.email and" +
                    "            p2.kennung =  COALESCE(p.vorgaenger, p.kennung) and" +
                    "            p.kennung = ?");
            ps.setInt(1, projektKennung);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                String icon = rs.getString("icon");
                int kennung = rs.getInt("kennung");
                String titel = rs.getString("titel");
                String name = rs.getString("name");
                double finanzierungslimit = rs.getDouble("finanzierungslimit");
                String status = rs.getString("status");
                String vorgaenger = rs.getString("vorgaenger");
                String beschreibung = rs.getString("beschreibung");

                /*results.setIcon(icon);
                results.setKennung(kennung);
                results.setTitel(titel);
                results.setBenutzername(name);
                results.setFinanzierungslimit(finanzierungslimit);
                results.setStatus(status);
                results.setVorgaenger1(vorgaenger);
                results.setBeschreibung(beschreibung);*/
                results = new Projekt(kennung, titel, beschreibung, status, finanzierungslimit, vorgaenger, icon, name);

            }
        } catch (SQLException e) {
            throw new StoreException(e);
        }
        return results;
    }


    public Projekt getProjektEdit(int projektKennung) {
        Projekt results = null;

        try {

            PreparedStatement ps = connection.prepareStatement("select * from dbp052.projekt where kennung= ?");
            ps.setInt(1, projektKennung);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                int kennung = rs.getInt("kennung");
                String titel = rs.getString("titel");
                double finanzierungslimit = rs.getDouble("finanzierungslimit");
                String status = rs.getString("status");
                int vorgaenger = rs.getInt("vorgaenger");
                String beschreibung = rs.getString("beschreibung");
                String ersteller = rs.getString("ersteller");
                int kategorie = rs.getInt("kategorie");

                results = new Projekt(kennung, titel, beschreibung, status, finanzierungslimit, ersteller, vorgaenger, kategorie);
            }
        } catch (SQLException e) {
            throw new StoreException(e);
        }
        return results;
    }

    public int getNumberOfKomment () {
        int results = 0;

        try {

            PreparedStatement ps = connection.prepareStatement("select count(id) from dbp052.kommentar ");
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {

                results = rs.getInt("id");
            }
        } catch (SQLException e) {
            throw new StoreException(e);
        }
        return results;
    }

    public boolean updateKommentar(Kommentar kommentar) {
        boolean result;
        try{PreparedStatement ps = connection.prepareStatement("insert into dbp052.kommentar(id, text, sichtbarkeit) values (?, ?, ?)");

            ps.setInt(1, kommentar.getId());
            ps.setString(2, kommentar.getText());
            ps.setString(3, kommentar.getSichtbarkeit());


            ps.executeUpdate();

            result = true;

        } catch (SQLException e) {
            throw  new StoreException(e);
        }
        return true;
    }

    public boolean updateSchreibt(Schreibt schreibt) {
        boolean result;
        try{PreparedStatement ps = connection.prepareStatement("insert into dbp052.schreibt(benutzer, projekt, kommenatr) values (?, ?, ?)");

            ps.setString(1, schreibt.getBenutzer());
            ps.setInt(2, schreibt.getProjekt());
            ps.setInt(3, schreibt.getKommentar());


            ps.executeUpdate();

            result = true;

        } catch (SQLException e) {
            throw  new StoreException(e);
        }
        return true;
    }

    public double getGuhaben () {
        double results = 0.0;

        try {

            PreparedStatement ps = connection.prepareStatement("select guthaben from dbp052.konto where inhaber='dummy.dummy.com' ");
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {

                results = rs.getInt("id");
            }
        } catch (SQLException e) {
            throw new StoreException(e);
        }
        return results;
    }

    public String checkSpender(String user) {
        String result = "";

        try{PreparedStatement ps = connection.prepareStatement("select spender from dbp052.spenden  where spender = ?");

            ps.setString(1, user);

            ResultSet rs =ps.executeQuery();

            while(rs.next()) {

                result = rs.getString("spender");}


        } catch (SQLException e) {
            throw  new StoreException(e);
        }
        return result;
    }


    public boolean fundProjekt(Spenden spenden) {
        boolean result;

        try{PreparedStatement ps = connection.prepareStatement("insert into dbp052.spenden(spender, projekt, spendenbetrag, sichtbarkeit) values (?, ?, ?, ?)");

            ps.setString(1, spenden.getSpender());
            ps.setInt(2,spenden.getProjekt());
            ps.setDouble(3, spenden.getSpendenbetrag());
            ps.setString(4,spenden.getSichtbarkeit());

            ps.executeUpdate();

            result = true;

        } catch (SQLException e) {
            throw  new StoreException(e);
        }
        return true;
    }


    public boolean updateKonto(double spendenbetrag) {
        boolean result;

        try{PreparedStatement ps = connection.prepareStatement("update Konto " +
                "   set guthaben = (select guthaben from dbp052.konto where inhaber='dummy@dummy.com')- ?" +
                "   where inhaber = 'dummy@dummy.com'");

            ps.setDouble(1, spendenbetrag);

            ps.executeUpdate();

            result = true;

        } catch (SQLException e) {
            throw  new StoreException(e);
        }
        return true;
    }

    public boolean deleteKommentar(int projektKennung) {
        boolean result;

        try{PreparedStatement ps = connection.prepareStatement("delete from dbp052.kommentar where id=?");

            ps.setInt(1, projektKennung);

            ps.executeUpdate();

            result = true;

        } catch (SQLException e) {
            throw  new StoreException(e);
        }
        return true;
    }

    public boolean deleteSpenden(int projektKennung) {
        boolean result;

        try{PreparedStatement ps = connection.prepareStatement("delete from dbp052.spenden where projekt=?");

            ps.setInt(1, projektKennung);

            ps.executeUpdate();

            result = true;

        } catch (SQLException e) {
            throw  new StoreException(e);
        }
        return true;
    }

    public boolean deleteProjekt(int projektKennung) {
        boolean result;

        try{PreparedStatement ps = connection.prepareStatement("delete from dbp052.projekt where kennung=?");

            ps.setInt(1, projektKennung);

            ps.executeUpdate();

            result = true;

        } catch (SQLException e) {
            throw  new StoreException(e);
        }
        return true;
    }

    public ArrayList<Spenden> getSpenders(int projektKennung) {
        ArrayList<Spenden> result = new ArrayList<>();

        try{PreparedStatement ps = connection.prepareStatement("select spender, spendenbetrag" +
                "   from dbp052.spenden" +
                "   where projekt = ?");

            ps.setInt(1, projektKennung);

            ResultSet rs =ps.executeQuery();

            while(rs.next()) {

                String spender =rs.getString("spender");
                double spendenbetrag = rs.getDouble("spendenbetrag");

                result.add(new Spenden(spender, spendenbetrag)); }
        } catch (SQLException e) {
            throw  new StoreException(e);
        }
        return result;
    }

    public boolean giveGuthaben(List<Spenden> spenders) {
        boolean result;

        try{PreparedStatement ps = connection.prepareStatement("update Konto " +
                    "          set guthaben = (select guthaben from dbp052.konto where inhaber=?)- ?" +
                    "          where inhaber = ?");

                for(int i=0; i < spenders.size(); i++){

                    Spenden spender = spenders.get(i);
                    String spend = spender.getSpender();
                    double betrag= spender.getSpendenbetrag();
                    ps.setString(1, spend);
                    ps.setDouble(2, betrag);

                    ps.executeUpdate();
                }

                result = true;

            } catch (SQLException e) {
                throw  new StoreException(e);
            }
            return  true;

    }

    public ArrayList<Projekt> getProjekts(String  search) {
       ArrayList<Projekt> results = new ArrayList<>();

      String search1 = "%" + search + "%";

        try {

            PreparedStatement ps = connection.prepareStatement("select icon, kennung, titel, benutzername, status, max(spendensumme) as Spendensumme" +
                    "      from(select k.icon, p.kennung, p.titel, b.name as benutzername, p.status, sum(s.spendenbetrag) as spendensumme" +
                    "      from dbp052.kategorie k, dbp052.projekt p, dbp052.spenden s, dbp052.benutzer b" +
                    "      where k.id = p.kategorie and" +
                    "            b.email = p.ersteller and" +
                    "            s.projekt = p.kennung and" +
                    "            p.titel LIKE ?" +
                    "     group by k.icon,p.kennung, p.titel, b.name, p.status" +
                    "     UNION" +
                    "     select k.icon, p.kennung, p.titel, b.name as benutzername, p.status, 0 as spendensumme" +
                    "     from dbp052.kategorie k, dbp052.projekt p, dbp052.benutzer b" +
                    "     where k.id = p.kategorie and" +
                    "     b.email = p.ersteller and" +
                    "     p.titel LIKE ? )" +
                    "     group by icon, kennung, titel, benutzername, status");
            ps.setString(1, search1);
            ps.setString(2, search1);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                String icon = rs.getString("icon");
                int kennung = rs.getInt("kennung");
                String status = rs.getString("status");
                String titel = rs.getString("titel");
                String benutzername = rs.getString("benutzername");
                double spendensumme = rs.getDouble("spendensumme");

                results.add(new Projekt(kennung, titel, status, icon, benutzername, spendensumme));
            }
        } catch (SQLException e) {
            throw new StoreException(e);
        }
        return results;
    }


    public void complete() {
        complete = true;
    }


    @Override
    public void close() throws IOException {
        if (connection != null) {
            try {
                if (complete) {
                    connection.commit();
                }
                else {
                    connection.rollback();
                }
            }
            catch (SQLException e) {
                throw new StoreException(e);
            }
            finally {
                try {
                    connection.close();
                }
                catch (SQLException e) {
                    throw new StoreException(e);
                }
            }
        }
    }
}
