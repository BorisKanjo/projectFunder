package de.unidue.inf.is.domain;

public class Projekt {
    private int kennung;
    private String titel;
    private String beschreibung;
    private String status;
    private double finanzierungslimit;
    private int ersteller1;
    private String ersteller;
    private int vorgaenger;
    private String vorgaenger1;
    private int kategorie;
    private String kategorie1;
    private String icon;
    private String benutzername;
    private double spendensumme;
    private double spendenbetrag;
    private String spender;

    public Projekt () {}

    public Projekt(int kennung, String titel, String beschreibung, double finanzierungslimit,
                   String ersteller, int vorgaenger, int kategorie) {
        this.kennung = kennung;
        this.titel = titel;
        this.beschreibung = beschreibung;
        this.finanzierungslimit = finanzierungslimit;
        this.ersteller = ersteller;
        this.vorgaenger = vorgaenger;
        this.kategorie = kategorie;
    }



    public Projekt(String icon, String titel, String benutzername, double spendensumme) {
        this.icon = icon;
        this.titel = titel;
        this.benutzername = benutzername;
        this.spendensumme = spendensumme;
    }

    public Projekt(String icon, int kennung, String titel, double spendensumme, String status) {
        this.icon = icon;
        this.kennung = kennung;
        this.titel = titel;
        this.status = status;
        this.spendensumme = spendensumme;
    }

    public Projekt(int kennung, String titel, String status, String icon, String benutzername, double spendensumme) {
        this.kennung = kennung;
        this.titel = titel;
        this.status = status;
        this.icon = icon;
        this.benutzername = benutzername;
        this.spendensumme = spendensumme;
    }

    public Projekt(String icon, int kennung, String titel, double finanzierungslimit, String status, double spendenbetrag) {
        this.titel = titel;
        this.kennung = kennung;
        this.status = status;
        this.finanzierungslimit = finanzierungslimit;
        this.icon = icon;
        this.spendenbetrag = spendenbetrag;
    }

    public Projekt(int kennung, String titel, String beschreibung, String status, double finanzierungslimit, String vorgaenger1, String icon, String benutzername) {
        this.kennung = kennung;
        this.titel = titel;
        this.beschreibung = beschreibung;
        this.status = status;
        this.finanzierungslimit = finanzierungslimit;
        this.vorgaenger1 = vorgaenger1;
        this.icon = icon;
        this.benutzername = benutzername;
    }

    public Projekt(String titel, String beschreibung, double finanzierungslimit, String vorgaenger1, String kategorie1) {
        this.titel = titel;
        this.beschreibung = beschreibung;
        this.finanzierungslimit = finanzierungslimit;
        this.vorgaenger1 = vorgaenger1;
        this.kategorie1 = kategorie1;
    }

    public Projekt(int kennung, String titel) {
        this.kennung = kennung;
        this.titel = titel;
    }

    public Projekt(int kennung, String titel1, String beschreibung, String status, double finanzierungslimit, String ersteller,  int kategorie, int vorgaenger) {
            this.kennung = kennung;
            this.titel = titel1;
            this.beschreibung = beschreibung;
            this.status = status;
            this.finanzierungslimit = finanzierungslimit;
            this.ersteller = ersteller;
            this.vorgaenger = vorgaenger;
            this.kategorie = kategorie;
    }

    public Projekt(String icon, int kennung, String titel, String benutzername, double spendensumme) {
        this.icon = icon;
        this.kennung = kennung;
        this.titel = titel;
        this.benutzername = benutzername;
        this.spendensumme = spendensumme;
    }


    public int getErsteller1() {
        return ersteller1;
    }

    public void setErsteller1(int ersteller1) {
        this.ersteller1 = ersteller1;
    }

    public String getKategorie1() {
        return kategorie1;
    }

    public void setKategorie1(String kategorie1) {
        this.kategorie1 = kategorie1;
    }

    public String getSpender() {
        return spender;
    }

    public void setSpender(String spender) {
        this.spender = spender;
    }

    public double getSpendenbetrag() {
        return spendenbetrag;
    }

    public void setSpendenbetrag(double spendenbetrag) {
        this.spendenbetrag = spendenbetrag;
    }

    public String getVorgaenger1() {
        return vorgaenger1;
    }

    public void setVorgaenger1(String vorgaenger1) {
        this.vorgaenger1 = vorgaenger1;
    }

    public int getKennung() {
        return kennung;
    }

    public void setKennung(int kennung) {
        this.kennung = kennung;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getFinanzierungslimit() {
        return finanzierungslimit;
    }

    public void setFinanzierungslimit(double finanzierungslimit) {
        this.finanzierungslimit = finanzierungslimit;
    }

    public String getErsteller() {
        return ersteller;
    }

    public void setErsteller(String ersteller) {
        this.ersteller = ersteller;
    }

    public int getVorgaenger() {
        return vorgaenger;
    }

    public void setVorgaenger(int vorgaenger) {
        this.vorgaenger = vorgaenger;
    }

    public int getKategorie() {
        return kategorie;
    }

    public void setKategorie(int kategorie) {
        this.kategorie = kategorie;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getBenutzername() {
        return benutzername;
    }

    public void setBenutzername(String benutzername) {
        this.benutzername = benutzername;
    }

    public double getSpendensumme() {
        return spendensumme;
    }

    public void setSpendensumme(double spendensumme) {
        this.spendensumme = spendensumme;
    }
}
