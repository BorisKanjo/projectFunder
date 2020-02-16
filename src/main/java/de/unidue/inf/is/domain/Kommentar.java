package de.unidue.inf.is.domain;

import java.sql.Clob;
import java.sql.Timestamp;

public class Kommentar {
    private int id;
    private String text;
    private Timestamp datum;
    private String sichtbarkeit;

    public Kommentar () {}

    public Kommentar(int id, String text, Timestamp datum, String sichtbarkeit) {
        this.id = id;
        this.text = text;
        this.datum = datum;
        this.sichtbarkeit = sichtbarkeit;
    }

    public Kommentar(int id, String text, String sichtbarkeit) {
        this.id = id;
        this.text = text;
        this.sichtbarkeit = sichtbarkeit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Timestamp getDatum() {
        return datum;
    }

    public void setDatum(Timestamp datum) {
        this.datum = datum;
    }

    public String getSichtbarkeit() {
        return sichtbarkeit;
    }

    public void setSichtbarkeit(String sichtbarkeit) {
        this.sichtbarkeit = sichtbarkeit;
    }
}
