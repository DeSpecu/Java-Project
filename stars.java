import java.util.HashMap;

class Gwiazda{
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
    HashMap<String, Integer> liczKonstelacja = new HashMap<>();

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
}

public class stars {
    public static void main(String[] args) {
            Gwiazda g = new Gwiazda(null, null, null, 0, 0, "W", null, 0, 0);
            System.out.println(g.nazwaKatalogowa);
            Gwiazda g2 = new Gwiazda(null, null, null, 0, 0, "W", null, 0, 0);
            System.out.println(g2.nazwaKatalogowa);
            Gwiazda g3 = new Gwiazda(null, null, null, 0, 0, "W", null, 0, 0);
            System.out.println(g3.nazwaKatalogowa);
    }
}
