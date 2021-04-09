import javax.swing.*;

public class OyunEkrani extends JFrame {


    public static void main(String[]args) {

        OyunEkrani ekran = new OyunEkrani();

        ekran.setResizable(false); // Bu ve aşağıdaki 2 metod bizim yaptığımız işlemlerin JPanel üzerinde olmasını sağlıcak, ayrıca ekranı kenarlardan büyütüp küçültmeyide engelliyecek.
        ekran.setFocusable(false); // mause klavye gibi işlemlerin JPanel üzerinde olmasını sağlar
        ekran.setTitle("Angry Birds"); // Açılan pencerenin adını angru birds yaptık.



        Oyun oyun = new Oyun();
        oyun.requestFocus(); // Klavye mause gibi işlevleri direk bu komut ile oyuna aktardık burda oyun odağı bana ver diyor.

        oyun.addMouseMotionListener(oyun); // Mause hareket dinleyicisini oyun sınıfına ekledik.
        oyun.addMouseListener(oyun); // Mause dinleyicisini oyun sınıfına ekledik.
        oyun.setFocusable(false); // Burda odağı oyuna verdik
        oyun.setFocusTraversalKeysEnabled(false); // Klavye işlemlerinin JPanelin anlaması için gererkli metod

        ekran.add(oyun); // JPaneli JFrame'e ekledik

        //ekran.setSize(oyun.getEkranGenislik(),oyun.getEkranUzunluk());
        ekran.setVisible(true);
        ekran.setSize(oyun.getEkranGenislik(),oyun.getEkranUzunluk()); // Açılaacak pencerenin uzunluğu ve genişliği oyun classında tanımlandı.


    }

}