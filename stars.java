import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

class Gwiazda implements Serializable{

    private static final long serialVersionUID = 1L;

    String nazwa;
    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    String nazwaKatalogowa;
    public String getNazwaKatalogowa() {
        return nazwaKatalogowa;
    }

    public void setNazwaKatalogowa(String nazwaKatalogowa) {
        this.nazwaKatalogowa = nazwaKatalogowa;
    }

    String deklinacja;
    public String getDeklinacja() {
        return deklinacja;
    }

    public void setDeklinacja(String deklinacja) {
        this.deklinacja = deklinacja;
    }

    String rektascencja;    
    public String getRektascencja() {
        return rektascencja;
    }

    public void setRektascencja(String rektascencja) {
        this.rektascencja = rektascencja;
    }

    double obsWielkoscGwiazdowa;
    public double getObsWielkoscGwiazdowa() {
        return obsWielkoscGwiazdowa;
    }

    public void setObsWielkoscGwiazdowa(double obsWielkoscGwiazdowa) {
        this.obsWielkoscGwiazdowa = obsWielkoscGwiazdowa;
    }

    double absWielkoscGwiazdowa;
    public double getAbsWielkoscGwiazdowa() {
        return absWielkoscGwiazdowa;
    }

    public void setAbsWielkoscGwiazdowa(double absWielkoscGwiazdowa) {
        this.absWielkoscGwiazdowa = absWielkoscGwiazdowa;
    }

    double odleglosc; 
    public double getOdleglosc() {
        return odleglosc;
    }

    public void setOdleglosc(double odleglosc) {
        this.odleglosc = odleglosc;
    }

    String gwiazdozbior;
    public String getGwiazdozbior() {
        return gwiazdozbior;
    }

    public void setGwiazdozbior(String gwiazdozbior) {
        this.gwiazdozbior = gwiazdozbior;
    }

    String polkola;
    public String getPolkola() {
        return polkola;
    }

    public void setPolkola(String polkola) {
        this.polkola = polkola;
    }

    double temperatura;
    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    double masa;
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

class GwiazdaArrayList extends ArrayList<Gwiazda> {
    @Override
    public boolean remove(Object o) {
        if (o instanceof Gwiazda) {
            Gwiazda gwiazda = (Gwiazda) o;
            super.remove(o);
            Gwiazda.zmienNazwy(gwiazda, this);
            return true;
        } else {
            return super.remove(o);
        }
    }
}

public class stars {
    
    public static void zapis(String sciezka , GwiazdaArrayList doZapisu) {
        try {
            FileOutputStream fo = new FileOutputStream(sciezka);
            ObjectOutputStream oo = new ObjectOutputStream(fo);
            oo.writeUnshared(doZapisu);
            oo.close();
            fo.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static GwiazdaArrayList odczyt(String sciezka){
        GwiazdaArrayList output = new GwiazdaArrayList();
        try {
            FileInputStream fi = new FileInputStream(new File(sciezka));
            ObjectInputStream oi = new ObjectInputStream(fi);
            output = (GwiazdaArrayList) oi.readObject();

            oi.close();
            fi.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return output;
    }
    public static void main(String[] args) {
            GwiazdaArrayList gwiazdy = new GwiazdaArrayList();
            Gwiazda g1 = new Gwiazda("Gwiazda 1", null, null, -30, 3.26, "W", "PD", 2004, 0.1);
            gwiazdy.add(g1);
            Gwiazda g2 = new Gwiazda("Gwiazda 2", null, null, 12, 4, "W", "PN", 3120, 10);
            gwiazdy.add(g2);
            Gwiazda g3 = new Gwiazda("Gwiazda 3", null, null, 1, 1, "W", "PN", 2145, 1.2);
            gwiazdy.add(g3);
            Gwiazda g4 = new Gwiazda("Gwiazda 4", null, null, 22, 3.26, "Z", "PD", 9871, 0.09);
            gwiazdy.add(g4);
            Gwiazda g5 = new Gwiazda("Gwiazda 5", null, null, 3.5, 1.2, "Z", "PD", 4321, 4.7);
            gwiazdy.add(g5);
            Gwiazda g6 = new Gwiazda("Gwiazda 6", null, null, 1.23, 32.6, "Z", "PN", 1000, 55);
            gwiazdy.add(g6);
            
            System.out.println(Gwiazda.liczKonstelacja.get(g1.gwiazdozbior));
            for(Gwiazda gw : gwiazdy){
                System.out.println(gw.nazwaKatalogowa);
            }
            gwiazdy.remove(g1);
            System.out.println(Gwiazda.liczKonstelacja.get(g1.gwiazdozbior));
            for(Gwiazda gw : gwiazdy){
                System.out.println(gw.nazwaKatalogowa);
            }

            //Gwiazda.wyszukajGwiazdozbior("W", gwiazdy);
            //Gwiazda.wyszukajOdleglosc(1, gwiazdy);
            //Gwiazda.wyszukajTemperatura(3000, 2000, gwiazdy);
            //Gwiazda.wyszukajWielkosc(1.23, -1.23, gwiazdy, true);
            //Gwiazda.wyszukajPolkola("PD", gwiazdy);
            //Gwiazda.wyszukajSupernova(gwiazdy);
            zapis("C://Users//szymk//Desktop//java.txt", gwiazdy);  
            GwiazdaArrayList poOdczyt = odczyt("C://Users//szymk//Desktop//java.txt");
            for(Gwiazda g : poOdczyt){
                System.out.println(g.nazwa);
            }
    }
}