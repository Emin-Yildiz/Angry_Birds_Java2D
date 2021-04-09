import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Domuz {

    private BufferedImage domuz; // Domuz resimi için domuz değişkeni tanımladık

    private int dmzX = 1000; //Domuzun durması gerekn x koordinatı

    private int dmzY = 660; //Domuzun durması gereken y koordinatı.


    public Domuz(){

        try {
            domuz = ImageIO.read(new FileImageInputStream(new File("kralDomuz.png"))); //Proje içindeki png dosyasını okuyup domuz değişkenine aktardık.
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public BufferedImage getDomuz() {
        return domuz;
    }

    public int getDmzX() {
        return dmzX;
    }

    public int getDmzY() {
        return dmzY;
    }

    public void setDmzX(int dmzX) {
        this.dmzX = dmzX;
    }

    public void setDmzY(int dmzY) {
        this.dmzY = dmzY;
    }

    public void setDomuz(BufferedImage domuz) {
        this.domuz = domuz;
    }

}