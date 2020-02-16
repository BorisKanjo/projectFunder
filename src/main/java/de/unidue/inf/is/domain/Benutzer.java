package de.unidue.inf.is.domain;

import java.sql.Clob;

public class Benutzer {
    private String email;
    private String name;
    private String beschreibung;
    private double spendenbetrag;
    private Clob comment;
    private String text;
    private String ersteller;

    public Benutzer() {}



    public Benutzer(String email, String name, String beschreibung) {
        this.email = email;
        this.name = name;
        this.beschreibung = beschreibung;
    }

    public Benutzer(String email, String name) {
        this.name = name;
        this.email = email;
    }

    public Benutzer(String name, double spendenbetrag) {
        this.name = name;
        this.spendenbetrag = spendenbetrag;
    }



    public Benutzer(String name, Clob comment) {
        this.name = name;
        this.comment = comment;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public double getSpendenbetrag() {
        return spendenbetrag;
    }

    public void setSpendenbetrag(double spendenbetrag) {
        this.spendenbetrag = spendenbetrag;
    }

    public Clob getComment() {
        return comment;
    }

    public void setComment(Clob comment) {
        this.comment = comment;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }
}
