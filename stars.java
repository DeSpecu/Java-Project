
class Gwiazda{
    String nazwa;
    String nazwaKatalogowa;
    String deklinacja;
    String rektascencja;    
    double obsWielkoscGwiazdowa;
    double absWielkoscGwiazdowa;
    double odleglosc; 
    String gwiazdosbior;
    String polkola;
    double temperatura;
    double masa;

    public Gwiazda(String nazwa, String nazwaKatalowgowa, String deklinacja, String rektascencja, double obsWielkoscGwiazdowa,
                    double odleglosc, String gwiazdosbior, String polkola, double temperatura, double masa){

        double r = odleglosc * 3.26;

        this.nazwa = nazwa;
        this.nazwaKatalogowa = nazwaKatalowgowa;
        this.deklinacja = deklinacja;
        this.rektascencja = rektascencja;
        this.obsWielkoscGwiazdowa = obsWielkoscGwiazdowa;
        this.absWielkoscGwiazdowa = obsWielkoscGwiazdowa - 5 * Math.log10(r) + 5;
        this.odleglosc = odleglosc;
        this.gwiazdosbior = gwiazdosbior;
        this. polkola= polkola;
        this.temperatura = temperatura;
        this.masa = masa;
    }

}

public class stars {
    public static void main(String[] args) {
            
    }
}
