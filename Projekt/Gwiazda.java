package Projekt;

import java.io.Serializable;
import java.util.HashMap;

class Gwiazda implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    String nazwa;
    String nazwaKatalogowa;
    String deklinacja;
    String rektascencja;    
    double obsWielkoscGwiazdowa;
    double absWielkoscGwiazdowa;
    double odleglosc; 
    String gwiazdozbior;
    String polkola;
    double temperatura;
    double masa;
    
    public String getNazwa() {
        return nazwa;
    }
    
    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }
    
    public String getNazwaKatalogowa() {
        return nazwaKatalogowa;
    }

    public void setNazwaKatalogowa(String nazwaKatalogowa) {
        this.nazwaKatalogowa = nazwaKatalogowa;
    }

    public String getDeklinacja() {
        return deklinacja;
    }

    public void setDeklinacja(String deklinacja) {
        this.deklinacja = deklinacja;
    }
    
    public String getRektascencja() {
        return rektascencja;
    }

    public void setRektascencja(String rektascencja) {
        this.rektascencja = rektascencja;
    }
    
    public double getObsWielkoscGwiazdowa() {
        return obsWielkoscGwiazdowa;
    }
    
    public void setObsWielkoscGwiazdowa(double obsWielkoscGwiazdowa) {
        this.obsWielkoscGwiazdowa = obsWielkoscGwiazdowa;
    }
    
    public double getAbsWielkoscGwiazdowa() {
        return absWielkoscGwiazdowa;
    }
    
    public void setAbsWielkoscGwiazdowa(double absWielkoscGwiazdowa) {
        this.absWielkoscGwiazdowa = absWielkoscGwiazdowa;
    }
    
    public double getOdleglosc() {
        return odleglosc;
    }
    
    public void setOdleglosc(double odleglosc) {
        this.odleglosc = odleglosc;
    }
    
    public String getGwiazdozbior() {
        return gwiazdozbior;
    }

    public void setGwiazdozbior(String gwiazdozbior) {
        this.gwiazdozbior = gwiazdozbior;
    }

    public String getPolkola() {
        return polkola;
    }

    public void setPolkola(String polkola) {
        this.polkola = polkola;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public double getMasa() {
        return masa;
    }

    public void setMasa(double masa) {
        this.masa = masa;
    }

    static HashMap<String, Integer> liczKonstelacja = new HashMap<>();

    public Gwiazda(String nazwa,  String deklinacja, String rektascencja, double obsWielkoscGwiazdowa,
                    double odleglosc, String gwiazdozbior, String polkola, double temperatura, double masa){

        double r = odleglosc / 3.26;

        this.nazwa = nazwa;
        this.nazwaKatalogowa = genNazwaKatalogowa(gwiazdozbior);
        this.deklinacja = deklinacja;
        this.rektascencja = rektascencja;
        if(obsWielkoscGwiazdowa<-26.74 || obsWielkoscGwiazdowa>15){
            this.obsWielkoscGwiazdowa = -26.74;
        }
        else{
            this.obsWielkoscGwiazdowa = obsWielkoscGwiazdowa;
        }
        this.absWielkoscGwiazdowa = obsWielkoscGwiazdowa - 5 * Math.log10(r) + 5;
        this.odleglosc = odleglosc;
        this.gwiazdozbior = gwiazdozbior;
        this.polkola = polkola;
        if(temperatura<2000){
            this.temperatura = 2000;
        }
        else{
            this.temperatura = temperatura;
        }
        if(masa<0.1 || masa>50){
            this.masa = 0.1;
        }
        else{
            this.masa = masa;
        }
    }

    private String genNazwaKatalogowa(String gwiazdozbior) {
        String nazwaKatalogowa;
        String[] alfabetGrecki = {"alpha", "beta", "gamma", "delta", "epsilon", "zeta", "eta", "theta", 
        "iota", "kappa", "lambda", "mu", "nu", "xi", "omicron", "pi", "rho", "sigma", "tau", "upsilon", "phi", "chi", "psi", "omega"};
        if(liczKonstelacja.containsKey(gwiazdozbior)) {
            int index = liczKonstelacja.get(gwiazdozbior);
            nazwaKatalogowa = alfabetGrecki[index] + " " + gwiazdozbior;
            liczKonstelacja.put(gwiazdozbior, index+1);
        } else {
            nazwaKatalogowa = "alpha " + gwiazdozbior;
            liczKonstelacja.put(gwiazdozbior, 1);
        }
        return nazwaKatalogowa;
    }

    public static void zmienNazwy(Gwiazda gwiazda, GwiazdaArrayList dozmiany){
        liczKonstelacja.remove(gwiazda.gwiazdozbior);
        for(Gwiazda g : dozmiany){
            if(gwiazda.gwiazdozbior.equals(g.gwiazdozbior))
                g.nazwaKatalogowa = g.genNazwaKatalogowa(g.gwiazdozbior);
        }
    }

    public static  void wyszukajGwiazdozbior(String gzbior, GwiazdaArrayList listaGwiazd){
        for(Gwiazda g : listaGwiazd){
            if(gzbior.equals(g.gwiazdozbior)){
                System.out.println(g.nazwa);
            }
        }
    }

    public static void wyszukajOdleglosc(double parseki, GwiazdaArrayList listaGwiazd){
        double lataSwietlne = parseki * 3.26;
        for(Gwiazda g : listaGwiazd){
            if(g.odleglosc == lataSwietlne){
                System.out.println(g.nazwa);
            }
        }
    }

    public static void wyszukajTemperatura(double tempMin, double tempMax, GwiazdaArrayList listaGwiazd){
        double help;
        if(tempMin>tempMax){
            help = tempMin;
            tempMin = tempMax;
            tempMax = help;
        }
            
        for(Gwiazda g : listaGwiazd){
            if(g.temperatura>=tempMin && g.temperatura <= tempMax){
                System.out.println(g.nazwa);
            }
        }
    }

    public static void wyszukajWielkosc(double wielkoscMin, double wielkoscMax, GwiazdaArrayList listaGwiazd, Boolean czyAbs){
        double help;
        if(wielkoscMin>wielkoscMax){
            help = wielkoscMin;
            wielkoscMin = wielkoscMax;
            wielkoscMax = help;
        }
        double dlaAbsMin = wielkoscMin;
        double dlaAbsMax = wielkoscMax;

        for(Gwiazda g : listaGwiazd){
            if(czyAbs){
                wielkoscMin = dlaAbsMin - 5 * Math.log10(g.odleglosc/3.26) + 5;
                wielkoscMax = dlaAbsMax - 5 * Math.log10(g.odleglosc/3.26) + 5;
                System.out.println(wielkoscMin);
                System.out.println(wielkoscMax);
            }
            if(g.obsWielkoscGwiazdowa>=wielkoscMin && g.obsWielkoscGwiazdowa<=wielkoscMax){
                System.out.println(g.nazwa);
            }
        }
    }

    public static void wyszukajPolkola(String polkola, GwiazdaArrayList listaGwiazd){
        if(polkola.equalsIgnoreCase("PN") || polkola.equalsIgnoreCase("PD")){
            for(Gwiazda g : listaGwiazd){
                if(g.polkola.equalsIgnoreCase(polkola)){
                    System.out.println(g.nazwa);
                }
            }
        }
    }

    public static void wyszukajSupernova(GwiazdaArrayList listGwiazd){
        for(Gwiazda g : listGwiazd){
            if(g.masa>1.44){
                System.out.println(g.nazwa);
            }
        }
    }
}
