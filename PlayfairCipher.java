import java.awt.Point;  
import java.util.Scanner;  
public class PlayfairCipher  
{  
//MENENTUKAN PANJANG ARRAY
private int length = 0;  
//MEMBUAT MATRIKS UNTUK PLAYFAIR CIPHER 
private String [][] table;  
//MAIN METOD() UNTUK MENGUJI PLAYFAIR METHOD  
public static void main(String args[])  
{  
PlayfairCipher pf = new PlayfairCipher();  
}  
//MENJALANKAN PROGRAM UTAMA YAITU PLAYFAIR METHOD 
//KONSTRUKTOR KELAS
private PlayfairCipher()  
{  
//MEMINTA USER UNTUK MENGINPUTKAAN KATA KUNCI YANG AKAN DIGUNAKAN  
System.out.print("Enter the key for playfair cipher: ");  
Scanner sc = new Scanner(System.in);  
String key = parseString(sc);  
while(key.equals(""))  
key = parseString(sc);  
table = this.cipherTable(key);  
//MEMINTA USER UNTUK MENGINPUTKAN PLAINTEXT 
System.out.print("Enter the plaintext to be encipher: ");  
String input = parseString(sc);  
while(input.equals(""))  
input = parseString(sc);  
//UNTUK MENGKODEKAN DAN MENDEKODEKAN KATA YANG AKAN DI SANDIKAN  
String output = cipher(input);  
String decodedOutput = decode(output);  
//UNTUK MENGELUARKAN HASIL OUTPUT 
this.keyTable(table);  
this.printResults(output,decodedOutput);  
}  
//PARSEN AN INPUT STRING UNTUK MENGHAPUS TANDA BACA DAN ANGKA
//MENGGANTI SEMUA HURUF J MENJADI HURUF I DAN MENJADI KAPITAL SEMUA 
private String parseString(Scanner sc)  
{  
String parse = sc.nextLine();  
parse = parse.toUpperCase();    
parse = parse.replaceAll("[^A-Z]", "");  
parse = parse.replace("J", "I");  
return parse;  
}  
//MEMBUAT TABLE CIPHER 
private String[][] cipherTable(String key)  
{  
//MEMBUAT MATRIKS 5X5   
String[][] playfairTable = new String[5][5];  
String keyString = key + "ABCDEFGHIKLMNOPQRSTUVWXYZ";  
//MENGISI ARRAY STRING DENGAN STRING KOSONG
for(int i = 0; i < 5; i++)  
for(int j = 0; j < 5; j++)  
playfairTable[i][j] = "";  
for(int k = 0; k < keyString.length(); k++)  
{  
boolean repeat = false;  
boolean used = false;  
for(int i = 0; i < 5; i++)  
{  
for(int j = 0; j < 5; j++)  
{  
if(playfairTable[i][j].equals("" + keyString.charAt(k)))  
{  
repeat = true;  
}  
else if(playfairTable[i][j].equals("") && !repeat && !used)  
{  
playfairTable[i][j] = "" + keyString.charAt(k);  
used = true;  
}  
}  
}  
}  
return playfairTable;  
}  
//CIPHER : MENGAMBIL INPUT, MENGKODEKAN, DAN MENGEMBALIKANNYA MENJADI OUTPUT  
private String cipher(String in)  
{  
length = (int) in.length() / 2 + in.length() % 2;  
//UNTUK MENYISIPKAN X DIANTARA HURUF YANG SAMA 
  
for(int i = 0; i < (length - 1); i++)  
{  
if(in.charAt(2 * i) == in.charAt(2 * i + 1))  
{  
in = new StringBuffer(in).insert(2 * i + 1, 'X').toString();  
length = (int) in.length() / 2 + in.length() % 2;  
}  
}  

//MEMBUAT ARRAY  
String[] digraph = new String[length];  
//LOOP BERULANG PADA PLAINTEXT 
for(int j = 0; j < length ; j++)  
{  
//MEMERIKSA PLAINTEXT PANJANGNYA BERJUMLAH GENAP ATAU TIDAK  
if(j == (length - 1) && in.length() / 2 == (length - 1))  
//JIKA TIDAK, SISPKAN X PADA AKHIR PLAINTEXT  
in = in + "X";  
digraph[j] = in.charAt(2 * j) +""+ in.charAt(2 * j + 1);  
}  
//UNTUK MENGKODEKAN DIGRAPH AGAR MENGHASILKAN OUTPUT 
String out = "";  
String[] encDigraphs = new String[length];  
encDigraphs = encodeDigraph(digraph);  
for(int k = 0; k < length; k++)  
out = out + encDigraphs[k];  
return out;  
}  
//---------------encryption logic-----------------  
//MENGKODEKAN INPUT DIGRAPH DENGAN CIPHER
private String[] encodeDigraph(String di[])  
{  
String[] encipher = new String[length];  
for(int i = 0; i < length; i++)  
{  
char a = di[i].charAt(0);  
char b = di[i].charAt(1);  
int r1 = (int) getPoint(a).getX();  
int r2 = (int) getPoint(b).getX();  
int c1 = (int) getPoint(a).getY();  
int c2 = (int) getPoint(b).getY();  
//UNTUK MENGEKSEKUSI JIKA HURUF DIGRAPH BERADA PADA BARIS YANG SAMA 
//UNTUK MENGGESER KOLOM KEKANAN JIKA HURUF DIGRAPH PADA BARIS YANG SAMA
if(r1 == r2)  
{  
c1 = (c1 + 1) % 5;  
c2 = (c2 + 1) % 5;  
}  
//UNTUK MENGEKSEKUSI HURUF JIKA BERADA PADA KOLOM YANG SAMA
//HURF AKAN BERGESER KE BAWAH JIKA HURF DIGRAPH BERADA PADA KOLOM YANG SAMA
else if(c1 == c2)  
{  
r1 = (r1 + 1) % 5;  
r2 = (r2 + 1) % 5;  
}  
//UNTUK MENGEKSESKUSI JIKA HURUF TIDAK BERADA PADA BARIS DAN KOLOM YANG SAMA   
else  
{  
int temp = c1;  
c1 = c2;  
c2 = temp;  
}  
 
encipher[i] = table[r1][c1] + "" + table[r2][c2];  
}  
return encipher;  
}  

// UNTUK MENDESKRIPSIKAN  OUTPUT YANG DIHASILKAN OLEH CIPHERTEXT SEBELUMNYA
private String decode(String out)  
{  
String decoded = "";  
for(int i = 0; i < out.length() / 2; i++)  
{  
char a = out.charAt(2*i);  
char b = out.charAt(2*i+1);  
int r1 = (int) getPoint(a).getX();  
int r2 = (int) getPoint(b).getX();  
int c1 = (int) getPoint(a).getY();  
int c2 = (int) getPoint(b).getY();  
if(r1 == r2)  
{  
c1 = (c1 + 4) % 5;  
c2 = (c2 + 4) % 5;  
}  
else if(c1 == c2)  
{  
r1 = (r1 + 4) % 5;  
r2 = (r2 + 4) % 5;  
}  
else  
{  
      
int temp = c1;  
c1 = c2;  
c2 = temp;  
}  
decoded = decoded + table[r1][c1] + table[r2][c2];  
}  
//MENGEMBALIKAN PESAN YA DI DEKODEKAN 
return decoded;  
}  
 
private Point getPoint(char c)  
{  
Point pt = new Point(0,0);  
for(int i = 0; i < 5; i++)  
for(int j = 0; j < 5; j++)  
if(c == table[i][j].charAt(0))  
pt = new Point(i,j);  
return pt;  
}  
//UNTUK MENCETAK TABEL KUNCI DALAM BENTUK MATRIKS  
private void keyTable(String[][] printTable)  
{  
System.out.println("Playfair Cipher Key Matrix: ");  
System.out.println();  

for(int i = 0; i < 5; i++)  
{  
   
for(int j = 0; j < 5; j++)  
{  
    
System.out.print(printTable[i][j]+" ");  
}  
System.out.println();  
}  
System.out.println();  
}    
//UNTUK MENCETAK HASIL AKHIR
private void printResults(String encipher, String dec)  
{  
System.out.print("Encrypted Message: ");  
//UNTUK MENCETAK PESAN ENKRIPSI 
System.out.println(encipher);  
System.out.println();  
System.out.print("Decrypted Message: ");  
//UNTUK MENCETAK PESAN DESKRIPSI 
System.out.println(dec);  
}  
}  