import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class KirmiziKus {

    private BufferedImage red; // Kırmızı kuş resmini red değişkenine atadık

    private int  redx = 90; //Kırmızı kuşun  başlangıçtaki x konumu mause ile birlikte koordinatı değişmeye başlıyor.

    private int redy = 675; //Kırmızı kuşun  başlangıçtaki y konumu mause ile birlikte koordinatı değişmeye başlıyor.

    public KirmiziKus(){

        try {
            red = ImageIO.read(new FileImageInputStream(new File("kirmiziKus.png"))); //Burda proje içindeki kirmiziKus resmini okuduk ve bu dosyayı red değişkenine aktardık.
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setRedx(int redx) {  //kuşun x koordinatını alıp redx değişkenine almak için kullanırız.
        this.redx = redx;
    }

    public int getRedx() {  //Kuşun x korrdinatını göndermek için kullanırız.
        return redx;
    }

    public void setRedy(int redy) {  //kuşun y koordinatını alıp redy değişkenine almak için kullanırız.
        this.redy = redy;
    }

    public int getRedy() {   //Kuşun y korrdinatını göndermek için kullanırız.
        return redy;
    }

    public void setRed(BufferedImage red) {
        this.red = red;
    }

    public BufferedImage getRed() { //projedeki kuş resmini göndermek için kullandık.
        return red;
    }

}