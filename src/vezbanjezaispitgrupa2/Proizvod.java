
package vezbanjezaispitgrupa2;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Proizvod {
    private String naziv;
    private String proizvodjac;
    private int godinaPro;
    private double masa;
    private Materijal materijal;

    public String getNaziv() {
        return naziv;
    }

    public String getProizvodjac() {
        return proizvodjac;
    }

    public int getGodinaPro() {
        return godinaPro;
    }

    public double getMasa() {
        return masa;
    }

    public Materijal getMaterijal() {
        return materijal;
    }

    public Proizvod(String naziv, String proizvodjac, int godinaPro, double masa, Materijal materijal) {
        this.naziv = naziv;
        this.proizvodjac = proizvodjac;
        this.godinaPro = godinaPro;
        this.masa = masa;
        this.materijal = materijal;
    }
    
    public double getAdekvatnost(){
        double adekvatnost = 0;
        double k = 0;
        if (this.materijal==Materijal.DRUGI_MATERIJAL)
            k=2.12;
        else if (this.materijal==Materijal.DRVO)
            k=0.98;
        else if (this.materijal==Materijal.PLASTIKA)
            k=0.93;
        else if(this.materijal==Materijal.METAL)
            k=0.21;
        else if(this.materijal==Materijal.STAKLO)
            k=1.45;
        else if(this.materijal==Materijal.PLATNO)
            k=1.10; 
        adekvatnost = ((2050-this.godinaPro)*Math.sqrt(this.masa))/(1-k); 
        return adekvatnost;
    }

    @Override
    public String toString() {
        return String.format("|%-12s|%-20s|%-13s|%13.3f kg|\n"
                + "|%-12s|%-20s|%-13s|%7d %s|\n"
                + "|%-12s|%-20s|%-13s|%16.2f|\n", 
                "Naziv:",this.naziv,
                "Masa:", this.masa/1000,
                "Proizvodjac:",this.proizvodjac,
                "Proizvedeno:",this.godinaPro,". godine",
                "Materijal:",this.materijal,
                "Adekvatnost",getAdekvatnost());
    }
    
      public static List<Proizvod> load(String imeDatoteke){
          List<Proizvod> lista = new ArrayList<>();
          try{
              FileInputStream fis = new FileInputStream("ulaz.dat");
              Scanner s = new Scanner(fis);
              while(s.hasNextLine()){
                  String naziv = s.nextLine().trim();
                  String proizvodjac = s.nextLine().trim();
                  int proizvedeno = s.nextInt();
                  double masa = s.nextDouble();
                  String materijalSlovo = s.next().trim();
                  Materijal materijal=null;
                  if (materijalSlovo.equals("D"))
                      materijal = Materijal.DRVO;
                  else if (materijalSlovo.equals("P"))
                      materijal = Materijal.PLASTIKA;
                  else if (materijalSlovo.equals("M"))
                      materijal = Materijal.METAL;
                  else if (materijalSlovo.equals("S"))
                      materijal = Materijal.STAKLO;
                  else if (materijalSlovo.equals("T"))
                      materijal = Materijal.PLATNO;
                  else if (materijalSlovo.equals("O"))
                      materijal = Materijal.DRUGI_MATERIJAL;                
                  lista.add(new Proizvod(naziv, proizvodjac, proizvedeno, masa, materijal));
                  if (s.hasNextLine())
                      s.nextLine();
              }
              fis.close();
              s.close();
              
          }catch(Exception e){
              System.err.print("Greska " + e.getMessage());
          }
          
          
          return lista;
      }
    
}
