import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Oyun extends JPanel implements ActionListener, MouseListener,MouseMotionListener {

    private int ekranGenislik = 1400; // Ekran genişliği

    private final int DELAY = 20;

    private int ekranUzunluk = 900;  // Ekran uzunluğu

    private boolean mKontrol = false; //Mause tuşu basılı tutuluyor iken tuşu bırakıp bıkmadığımızı kontorl etmek amaçlı kullanıyoruz

    private boolean stopRed = false; //Kuşun sapandan fırlatıldıktan sonra yere düşüp düşmediğini kontrol için kullanırız.

    private boolean dKontrol = false; //kuş domuza çarpıp çarpmadığını kontrol etmek için kullanırız.

    private int hiz ;

    private int time;

    private int i = 0; //Burda kullandığımız i hesapladığımız time'ı geçip geçmediğini kontrol amaçlı kullanıyoruz

    KirmiziKus red = new KirmiziKus();

    ArkaPlan aPlan = new ArkaPlan();

    Sapan sSapan = new Sapan();

    Minder minder = new Minder();

    Domuz domuz = new Domuz();

    Timer timer = new Timer(DELAY,this);



    public Oyun() {

        timer.start();

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        doDrawing(g);
    }

    private  void doDrawing(Graphics g){ // Çizimleri yapıcağımız metod bu

        Graphics2D g2d = (Graphics2D) g;   // g'yi grafik 2d'ye çevirdi ve g2d adında bir nesne oluşturdu.


        g2d.drawImage(aPlan.getArkaPlan(), 0,0,getEkranGenislik(),getEkranUzunluk(),this); // Arka planı çizdirdik
        g2d.drawImage(red.getRed(),red.getRedx(),red.getRedy(),getEkranGenislik()/10,getEkranUzunluk()/10,this); // kırmızı kuşu çizdirdik
        g2d.drawImage(sSapan.getSapan(),sSapan.getSapanX(),sSapan.getSapanY(), getEkranGenislik()/5,getEkranUzunluk()/5,this); // sapanı çizdirdik.
        g2d.drawImage(minder.getMinder(),minder.getMndX(),minder.getMndY(), getEkranGenislik()/5,getEkranUzunluk()/5,this); // Minderi(Kuşun oturucağı yeri) çizdirdik çizdirdik.
        g2d.drawLine(minder.getMndX()+40, minder.getMndY()+85, 200, 700);  //Düz bir çizgi çizilir. Yöntemin parametreleri, iki noktanın x, y koordinatlarıdır.
        g2d.drawLine(minder.getMndX()+40, minder.getMndY()+85, 250, 680);  //Düz bir çizgi çizilir. Yöntemin parametreleri, iki noktanın x, y koordinatlarıdır.
        if(!(dKontrol)){ // Burdaki if eğer kuş domuzu vurmamışsa domuzu çizdiriyor vurur ise çizdirmiyor.
            g2d.drawImage(domuz.getDomuz(),domuz.getDmzX(), domuz.getDmzY(), getEkranGenislik()/9,getEkranUzunluk()/6,this);//Domuzu çizmek için kullandık
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        /*
            bu kontrolü yapmamızın sebebi kuşu fırlattığımızı kontrol ediyor
            eğer bu kontrolü yapmazsak kuş biz mause tuşundan elimizi çekmememize rapmen hareket etmeye başlıyor bunun önüne geçmek için bu kontrol şart

        */
        if(mKontrol) {
            hareket();
        }

        /*
            Burdaki kontrol işlemi işe domuzu vurup vurmadığımızı kontrol ediyor.
            eğer vurduysak kontrolEt() metodu true değer döndürüyor ve program sona eriyor.
         */
        if(kontrolEt()){

            timer.stop();
            repaint();
            JOptionPane.showMessageDialog(this, "Tebrikler domuzu vurup yok ettin.");
            System.exit(0);

        }else if(!(kontrolEt()) && stopRed){

            JOptionPane.showMessageDialog(this, "Domuzu vuramadın tekrar dene.");
            hiz = 0;
            i = 0;
            time = 0;
            mKontrol = false;
            red.setRedx(90);
            red.setRedy(675);
            stopRed = false;

        }

        repaint();

    }

    @Override
    public void mouseClicked(MouseEvent e) { //Bir düğmeye basılıp bırakılınca çağrılır.

    }

    @Override
    public void mousePressed(MouseEvent e) { //Bir düğmeye basılınca çağrılır

    }

    @Override
    public void mouseReleased(MouseEvent e) { //Bir mouse düğmesi basılı olarak mouse çekildikten sonra düğme bırakılınca çağrılır.

        //buraya tanımladığımız x ve y bize hız hesaplamada yardımcı olcuak değerler
        double x,y;

        // x Hesaplama
        if(red.getRedx() > sSapan.getSapanX()){
            x = red.getRedx() - sSapan.getSapanX();
        }else{
            x = sSapan.getSapanX() - red.getRedx();
        }

        // y hesaplama
        if (red.getRedy() > sSapan.getSapanY()){
            y = red.getRedy() - sSapan.getSapanY();
        }else{
            y = sSapan.getSapanY() - red.getRedy();
        }

        hizHesap(x,y); // Hızı Hesaplamak için Bu metoda gittik

        time(); // Zamanı hesaplamak için bu metoda gittik.

        // Mause tuşunu bıraktıktan sonra minderin eski yerine geri dönmesi için
        minder.setMndX(100);
        minder.setMndY(650);

        // mause tuşunu bırakıp bırakmadığımızı kontrol ediyor eğer böyle bir kontrol yapmazsak kuş mase tuşunu basılı olması halinde bile herekte geçiyor
        mKontrol = true;


    }

    public void hareket(){

        if(i < time*2) {
            red.setRedx((red.getRedx() + hiz/2));
            red.setRedy((red.getRedy() - hiz/2));
            i++;
        }else if(i < time*4){
            red.setRedx((red.getRedx() + hiz/2));
            red.setRedy((red.getRedy() + hiz/2));
            i++;
        }else if( i == time*4){
            stopRed = true;
        }

    }

    public void time() { // zamanı hesaplamak için kullanıcaz

        /*
            eğik atış zaman formülü : t = (kök içinde) 2*r/g (g = 10/9.8 alınır.)
            yukarıdaki formül kullarak bir zaman referansı aldık bu zaman referansı ile kuş zaman boyunca yukarı, aşşağıya hareket edip hedefe ulaşıcak.

         */


        time = (int) Math.sqrt(2*hiz/10);

    }

    public void hizHesap(double x ,double y) {

        // bu metodu ilk hız hesaplamak için kullanıcaz kusx ile sapan x arası mesafe x , kuşy ile sapany arası mesafe ise y olucak.
        // x ve y değerleri mause düğmesinden elimizi çektiğimiz andaki mouseReleased() metodunda hesaplanıyor.
        // sapan ile kuş arasındaki mesafeyi üçgen olarak düşünürsek hipotenüs bizim hız vektörümüz olur.
        hiz = (int)(Math.sqrt((Math.pow(x,2) + Math.pow(y,2)))); //  x ile y'nin karesini alıp toplayıp karekökünü aldık.

    }

    public boolean kontrolEt(){
        //Kuşun ve domuzun etrafında birer diktörtgen oluşturarak keşişip keşişmediğine bakarız eğer keşişiyorlar ise true değer döndürür ve program sona erer.
        if(new Rectangle(red.getRedx(),red.getRedy(),getEkranGenislik()/15,getEkranUzunluk()/15).intersects(new Rectangle(domuz.getDmzX(),domuz.getDmzY(),getEkranGenislik()/15,getEkranUzunluk()/15))){

            dKontrol = true;
            return true;

        }else{

            return false;

        }

    }

    @Override
    public void mouseEntered(MouseEvent e) { //mouse pencere içine girince çağrılır.

    }

    @Override
    public void mouseExited(MouseEvent e) { //mouse pencere çerçevesinin dışındaysa çağrılır.

    }

    @Override
    public void mouseDragged(MouseEvent e) {//Mouse düğmesi basılı iken mouse hareket ettirilirse çağrılır.

        // Bu if bloğu kuşun sapandayken hareket alanını sınırlıyor böylece sapandaki kuşu alakasız yerlere götüremiyoruz.

            if(!(e.getX()< 50 || e.getX() > 200  || e.getY() > 800 || e.getY() < 600) ) {

                /*
                    burdaki 60,30,58,54 rakamları mause ile kuşun tam ortasından tutmamızı ve
                    minderin kuşu tam olarak oturması için lazımdır.

                */
                red.setRedx(e.getX() - 60);
                red.setRedy(e.getY() - 30);

                minder.setMndX(e.getX() - 58);
                minder.setMndY(e.getY() - 54);

            }
    }

    @Override
    public void mouseMoved(MouseEvent e) {//Mouse hareket ederse çağrılır.

    }

    public int getEkranGenislik() {
        return ekranGenislik;
    }

    public int getEkranUzunluk(){
        return ekranUzunluk;
    }

    public void setEkranGenislik(int ekranGenislik){
        this.ekranGenislik = ekranGenislik;
    }

    public void setEkranUzunluk(int ekranUzunluk){
        this.ekranUzunluk = ekranUzunluk;
    }
}