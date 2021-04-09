import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/*
    Program başladığında belirtilen koordinatlarda başlıcak ve kuş fırlatılmadığı sürece kuş ile birlikte hareket edicek.
    Kuş fırlatıldığı sırada minder ilk başta belirttiğimiz koordinatlara geri dönücek.

 */

public class Minder {

    private BufferedImage minder;  // Minder resmi

    private int mndX = 95; //Minderin program başladığındaki x koordinatı

    private int mndY = 650; //Minderin program başladığındaki y koordinatı

    public Minder(){

        try {
            minder = ImageIO.read(new FileImageInputStream(new File("minder.png"))); //Proje içindeki minder resmini okuduk.
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public BufferedImage getMinder() { //Minder nesnesini gönderebilmek için kullandık.
        return minder;
    }

    public void setMinder(BufferedImage minder) {
        this.minder = minder;
    }

    public int getMndX() { //Minderin x koordinatını gönderebilmek için kullandık.
        return mndX;
    }

    public int getMndY() { //Minderin y koordinatını gönderebilmek için kullandık.
        return mndY;
    }

    public void setMndX(int mndX) { //Minderin x koordinatını alabilmek için kullandık.
        this.mndX = mndX;
    }

    public void setMndY(int mndY) { //Minderin y koordinatını alabilmek için kullandık.
        this.mndY = mndY;
    }

}