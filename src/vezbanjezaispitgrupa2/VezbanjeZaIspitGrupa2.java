
package vezbanjezaispitgrupa2;

import java.util.ArrayList;
import java.util.List;

public class VezbanjeZaIspitGrupa2 {
    public static void main(String[] args) {
        List<Proizvod> lista = Proizvod.load("ulaz.dat");
        prikaz(lista);
        List<Proizvod> filtriranaLista = new ArrayList<>();
        for(Proizvod p:lista){
            if (p.getAdekvatnost()<240){
                filtriranaLista.add(p);
            }
                
        }
        System.out.println("Filtrirana lista: ");
        prikaz(filtriranaLista);
        double procenat = ((double)filtriranaLista.size()/(double)lista.size()) * 100;
        System.out.printf("%.2f%% \n",procenat);
    }
    
    public static void prikaz(List<Proizvod> lista){
        for(Proizvod p:lista){
            System.out.println(p);
        }
    }
    
}
