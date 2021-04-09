import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Sapan {

    private BufferedImage sapan;  // Sapan resmi

    private int sapanX = 100; // Sapanın x koordinatı

    private int sapanY = 650; // Sapanın y koordinatı

    public Sapan() {

        try {
            sapan = ImageIO.read(new FileImageInputStream(new File("sapan.png"))); //Burda proje içindeki sapan resmini okuduk ve bu resimi sapan değişkenine aktardık.
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public BufferedImage getSapan() { //Sapan resmini gönderebilmek için kullandık.
        return sapan;
    }

    public void setSapan(BufferedImage sapan) {
        this.sapan = sapan;
    }

    public int getSapanX() {
        return sapanX;
    }

    public void setSapanX(int sapanX) {
        this.sapanX = sapanX;
    }

    public int getSapanY() {
        return sapanY;
    }

    public void setSapanY(int sapanY) {
        this.sapanY = sapanY;
    }

}