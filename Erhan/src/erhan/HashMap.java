/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erhan;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class HashMap {
	
	public static File file;	//dosya sınıfı çağrılır 
	public static BufferedReader buff = null;	//okuma yapılması için bufferReader sınıfı tanımlanır
	public static ArrayList<String> dosyaDizisi;
	public static String Dizi[];
	public static String kelime;	//dizideki kelimeleri tutan değişken
	public static byte bytes[];		//her kelimenin ascii kodu hesabını tutan değişken
	
	public static String HashDizisi[];	//Hashmap için dizi tanımlaması yapılır
	public static int hindis;	//hash dizisinin indisi
	public static int indis;
	public static int indisArttir;
	
	public static StringBuilder karakterSil;
        ArrayList<String> kelimeler = new ArrayList<String>();
        
        ArrayList<String> kelimeler1 = new ArrayList<String>();
	
	public HashMap(String dosyaIsmi)
	{//classımızın constructer methodunds dosya okuma işlemi yapılır
		file = new File("dosya.txt");
		
		try {//dosya okunmaz ise catch e düşer
			buff = new BufferedReader(new FileReader(file));
		}catch (Exception e) {
			
		}
	}
	public void DosyaOku() throws IOException {
		
		dosyaDizisi = new ArrayList<String>();	//bir arraylist tanımlanır
		String satir = buff.readLine();		//her satır için okuma işlemi başlatılır
		
		while (satir!=null) {//satırda başka kelime kalmayınca döngüden çıkar
			//arrayliste atılırken stringe dönüşüm yapılır ve küçük harf yapılır
			dosyaDizisi.add(satir.toString().toLowerCase());
			satir = buff.readLine();	//tekrardan yeni satır okurnur
		}
		
		Dizi = new String[dosyaDizisi.size()];//arraylist boyutunda bir static dizi tanımlanır
		int i = 0;	//static bir diziye atılması için indis değişkeni belirlenir
		for (String string : Dizi) {//dinamik dizideki elemanlar static diziye aktarılır
			Dizi[i] = dosyaDizisi.get(i);	//static diziye elemanlar atılır
			i++;//her döngüde indis arttılır
		}
		
		HashDizisi = new String[Dizi.length];
	}
	public void DiziyeYerlestir() throws UnsupportedEncodingException
	{//diziye yerleştir fonksiyonu ile hash dizisi oluşturulur
		
		for (String string : Dizi) {//Dizi içerisinde dönüş yapılır
			
			byte[] bytes = string.getBytes("US-ASCII");//string ifadeyi ascii ye çevirdik
			//System.out.println(bytes[1]);
			
			int i = 1;	//kelimenin her harfinin ascii karşılığını çarpan indis
			int key = 0;	//toplam ascii kodun tutulduğu değişken
			for (byte b : bytes) {//ascii kodun çarpılarak key değişkeninde toplanması
				key += b*i;
				i++;
			}
			//System.out.println(key + " " + Dizi.length + "" + string);
			
			hindis = Hash1(key,Dizi.length);		//hash fonksiyonuna key ile dizi boyutunu yolladık
			//hindis1 = Hash2(key,Dizi.length);
				HashDizisi[hindis] = string;
			//System.out.println(hindis);
		}
		
		System.out.println("Hash Dizisi : ");
		for (String string : HashDizisi) {
			System.out.println(string);
		}
		
	}
	
	//hash1 de lineer yaklaşım kullanarak diziye atama yapılır
	public int Hash1(int key,int boyut)
	{//bu fonksiyonda hash dizisine atama yapılacak
		
		indis = 0;
		//sonsuz döngüye sokulur
		indis = key%boyut;	//indis hesaplanır;
		while(true) {
			if(HashDizisi[indis] != null) {
				//dizide indis dolu ise indis bir arttırlır
				indis = (indis+1)%Dizi.length;
				//System.out.println(indis);
				// 8. indi dolu ise (8+1)%dizi_boyutundan yeni bir indis hesaplanır
			}
			if(HashDizisi[indis]==null)
			{
				return indis;
			}
		}
		
	}
	public int Arama(String aranacakKelime) throws UnsupportedEncodingException {
		
		aranacakKelime = aranacakKelime.toLowerCase();
		
		byte[] bytes = aranacakKelime.getBytes("US-ASCII");
		int i=1;
		int key = 0;
		
		//alınan kelime ascii koda belirtilen biçimde dönüştürülür
		for (byte b : bytes) {
			key+=b*i;
			i++;
		}
		
		//şimdi ascii koda dönüştürlen kelimenin keyi bulunarak dizide arama yapılır
		indis=0;
		indisArttir=1;
		int donguSayisi = 0;
		indis = key%Dizi.length;
		while(true) {
			
			
			if(HashDizisi[indis].equals(aranacakKelime)) {
				return indis;
			}else {
				indis = (indis+indisArttir)%Dizi.length;
			}
			donguSayisi++;
			if (donguSayisi>Dizi.length) {
				return -1;
				
			}
		}
		
	}
	
	public ArrayList<String> HarfSilAra(String aranacakKelime) throws UnsupportedEncodingException {
		
		int sonuc ;
		
		for(int i = 0; i<aranacakKelime.length();i++)
		{
			karakterSil = new StringBuilder(aranacakKelime);//kelime string buildera atılır
			karakterSil.deleteCharAt(i);	//kelimenin sırasıyla bir karakter silinir
			sonuc = Arama(karakterSil.toString());//arama fonksiyonuna yollanır
			if(sonuc!=-1)
				kelimeler.add(HashDizisi[sonuc]);
		}
		return kelimeler;
	}
	public ArrayList<String> HarfYerDegistirAra(String aranacakKelime) throws UnsupportedEncodingException{
		char harfler[] = new char[aranacakKelime.length()];
		char temp;
		String kelime;
		int sonuc;
		
		for(int i=0; i<aranacakKelime.length()-1;i++) {
			harfler = aranacakKelime.toCharArray();
			temp = harfler[i+1];
			harfler[i+1]=harfler[i];
			harfler[i]=temp;
			kelime = new String(harfler);
			//System.out.println(kelime);
			sonuc = Arama(kelime);
			if(sonuc !=-1)
                            kelimeler1.add(kelime);
		}
		
		return kelimeler1;
	}
}
