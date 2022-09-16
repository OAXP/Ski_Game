import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class Main {
    public static final int LARGEUR = 800, HAUTEUR = 600; // Constantes publiques

    public static void main(String[] args) {
        try
        {
            AppGameContainer app = new AppGameContainer(new Jeu("Exam Final"));
            app.setDisplayMode(LARGEUR, HAUTEUR, false);
            app.setShowFPS(false); // true for display the numbers of FPS
            app.setVSync(true); // false for disable the FPS synchronize
            app.start();
        }
        catch (SlickException e)
        {
            e.printStackTrace();
        }
    }
}