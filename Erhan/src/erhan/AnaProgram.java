
package erhan;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class AnaProgram {

	public static void main(String[] args) throws IOException {
		
		int sonuc;
		ArrayList<String> kelimeler = new ArrayList<String>();
                ArrayList<String> kelimeler1 = new ArrayList<String>();
		
		HashMap h1 = new HashMap("dosya.txt");
		h1.DosyaOku();
		h1.DiziyeYerlestir();
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Bir Kelime Giriniz : ");
		String kelimeAdi = scan.nextLine();
		
		sonuc = h1.Arama(kelimeAdi);
		if(sonuc == -1)
		{
			System.out.println("Aradığınız kelime bulunamadı diğer "+"\n"
		+"arama yöntemlerine geçiliyor\n");
			
		}
		if (sonuc == -1)
		{
			kelimeler = h1.HarfSilAra(kelimeAdi);
                        kelimeler1 = h1.HarfYerDegistirAra(kelimeAdi);
		}
		if(!kelimeler.isEmpty())
		{
                    System.out.println("1. arama yönetmi sonuçları :\n");
                    for (String string : kelimeler) {
                            System.out.println(string);
                    }
                }
                if(!kelimeler1.isEmpty()){
                    System.out.println("2. arama yönetmi sonuçları :\n");
                    for (String string : kelimeler1) {
                            System.out.println(string);
                    }
		}
                if(kelimeler.isEmpty() && kelimeler1.isEmpty()){
                    System.out.println("Malesef "+ kelimeAdi+" kelimesi bulunamadı");
                }
		
	}

}
