import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ArkaPlan {

    private BufferedImage arkaPlan; // arka plan resimi için arkaPlan değişkeni tanımladık


    public ArkaPlan(){

        try {
            arkaPlan = ImageIO.read(new FileImageInputStream(new File("arkaPlan.jpg"))); //Burda proje içindeki arkaPlan resmini okuduk ve arkaPlan değişkenine aktardık.
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public BufferedImage getArkaPlan() {  //Arka plan resmini gönderebilmek için kullanılır.
        return arkaPlan;
    }

    public void setArkaPlan(BufferedImage arkaPlan) {
        this.arkaPlan = arkaPlan;
    }

}