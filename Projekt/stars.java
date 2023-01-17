package Projekt;

public class stars {
    
   
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

            Gwiazda.wyszukajGwiazdozbior("W", gwiazdy);
            Gwiazda.wyszukajOdleglosc(1, gwiazdy);
            Gwiazda.wyszukajTemperatura(3000, 2000, gwiazdy);
            Gwiazda.wyszukajWielkosc(1.23, -1.23, gwiazdy, true);
            Gwiazda.wyszukajPolkola("PD", gwiazdy);
            Gwiazda.wyszukajSupernova(gwiazdy);

            GwiazdaArrayList.zapis("C://Users//szymk//Desktop//java.txt", gwiazdy);  
            GwiazdaArrayList poOdczyt = GwiazdaArrayList.odczyt("C://Users//szymk//Desktop//java.txt");
            for(Gwiazda g : poOdczyt){
                System.out.println(g.nazwa);
            }
    }
}