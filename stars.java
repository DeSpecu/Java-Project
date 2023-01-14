import java.util.ArrayList;
import java.util.HashMap;

class Gwiazda extends ArrayList{
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
    static HashMap<String, Integer> liczKonstelacja = new HashMap<>();

    public Gwiazda(String nazwa,  String deklinacja, String rektascencja, double obsWielkoscGwiazdowa,
                    double odleglosc, String gwiazdozbior, String polkola, double temperatura, double masa){

        double r = odleglosc * 3.26;

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
        String nazwaKatalogowa = "";
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
            g.nazwaKatalogowa = g.genNazwaKatalogowa(g.gwiazdozbior);
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
    public static void main(String[] args) {
            GwiazdaArrayList gwiazdy = new GwiazdaArrayList();
            Gwiazda g = new Gwiazda(null, null, null, 0, 0, "W", null, 0, 0);
            gwiazdy.add(g);
            Gwiazda g2 = new Gwiazda(null, null, null, 0, 0, "W", null, 0, 0);
            gwiazdy.add(g2);
            Gwiazda g3 = new Gwiazda(null, null, null, 0, 0, "W", null, 0, 0);
            gwiazdy.add(g3);

            System.out.println(Gwiazda.liczKonstelacja.get(g.gwiazdozbior));
            for(Gwiazda gw : gwiazdy){
                System.out.println(gw.nazwaKatalogowa);
            }
            gwiazdy.remove(g2);
            System.out.println(Gwiazda.liczKonstelacja.get(g.gwiazdozbior));
            for(Gwiazda gw : gwiazdy){
                System.out.println(gw.nazwaKatalogowa);
            }
    }
}