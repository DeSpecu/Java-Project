package Projekt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class GwiazdaArrayList extends ArrayList<Gwiazda> {
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
            System.out.println("Nie znaleziono pliku");
        } catch (IOException e) {
            System.out.println("Błąd inicjalizacji");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return output;
    }
}
