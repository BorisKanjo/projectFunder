package de.unidue.inf.is.domain;

public class Spenden {
    private String spender;
    private int projekt;
    private double spendenbetrag;
    private String sichtbarkeit;
    private String benutzername;

    public Spenden() {}

    public Spenden(String spender, int projekt, double spendenbetrag, String sichtbarkeit) {
        this.spender = spender;
        this.projekt = projekt;
        this.spendenbetrag = spendenbetrag;
        this.sichtbarkeit = sichtbarkeit;
    }

    public Spenden(String spender, String benutzername, int projekt, double spendenbetrag, String sichtbarkeit) {
        this.spender = spender;
        this.benutzername = benutzername;
        this.projekt = projekt;
        this.spendenbetrag = spendenbetrag;
        this.sichtbarkeit = sichtbarkeit;
    }

    public Spenden(String spender, double spendenbetrag) {
        this.spender = spender;
        this.spendenbetrag = spendenbetrag;
    }

    public String getBenutzername() {
        return benutzername;
    }

    public void setBenutzername(String benutzername) {
        this.benutzername = benutzername;
    }

    public String getSpender() {
        return spender;
    }

    public void setSpender(String spender) {
        this.spender = spender;
    }

    public int getProjekt() {
        return projekt;
    }

    public void setProjekt(int projekt) {
        this.projekt = projekt;
    }

    public double getSpendenbetrag() {
        return spendenbetrag;
    }

    public void setSpendenbetrag(double spendenbetrag) {
        this.spendenbetrag = spendenbetrag;
    }

    public String getSichtbarkeit() {
        return sichtbarkeit;
    }

    public void setSichtbarkeit(String sichtbarkeit) {
        this.sichtbarkeit = sichtbarkeit;
    }
}
