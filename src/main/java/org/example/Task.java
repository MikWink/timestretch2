package org.example;

public class Task {

    private int Task_ID;
    private String Titel;
    private String Beschreibung;

    public Task (int Task_ID, String Titel, String Beschreibung){
        this.Task_ID = Task_ID;
        this.Titel = Titel;
        this.Beschreibung = Beschreibung;
    }

    public String getTitel() {return Titel;}

    public String getBeschreibung() {return Beschreibung;}

    public int getTask_ID() {return Task_ID;}

    public void setTitel(String titel) {Titel = titel;}

    public void setBeschreibung(String beschreibung) {Beschreibung = beschreibung;}
}
