package de.unidue.inf.is.domain;

public class Schreibt {
    private String benutzer;
    private int projekt;
    private int kommentar;
    private String name;
    private String comment;
    private String sichtbarkeit;


    public Schreibt() {}

    public Schreibt(int projekt,int kommentar, String benutzer) {
        this.projekt = projekt;
        this.kommentar = kommentar;
        this.benutzer = benutzer;
    }



    public Schreibt(String benutzer, String name, int projekt, int kommentar) {
        this.benutzer = benutzer;
        this.projekt = projekt;
        this.kommentar = kommentar;
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getSichtbarkeit() {
        return sichtbarkeit;
    }

    public void setSichtbarkeit(String sichtbarkeit) {
        this.sichtbarkeit = sichtbarkeit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBenutzer() {
        return benutzer;
    }

    public void setBenutzer(String benutzer) {
        this.benutzer = benutzer;
    }

    public int getProjekt() {
        return projekt;
    }

    public void setProjekt(int projekt) {
        this.projekt = projekt;
    }

    public int getKommentar() {
        return kommentar;
    }

    public void setKommentar(int kommentar) {
        this.kommentar = kommentar;
    }
}
