import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class PlayerOne extends Entite implements Modulable{
    private final GameContainer container;
    private int vieJoueur;
    private int nbPoints;
    private boolean isDroite;
    private Image imageD;
    private Image imageG;

    public PlayerOne(float x, float y, GameContainer container) throws SlickException {
        super(x, y, 43, 39, "res/skieurd.png");
        imageD = image;
        imageG = new Image("res/skieurg.png");
        this.container = container;
        vieJoueur = 100;
        nbPoints = 0;
        isDroite = true;
    }

    public int getNbPoints() {
        return nbPoints;
    }

    public int getVieJoueur() {
        return vieJoueur;
    }

    public void tuer(){
        vieJoueur = -1;
    }

    public void ajouterPoints(int pts){
        nbPoints += pts;
    }

    public void changerDirection(boolean isDroite){
        this.isDroite = isDroite;
        if(isDroite){
            image = imageD;
        } else{
            image = imageG;
        }
    }

    public void resetPlayer(){
        x = (int)(container.getWidth()/2);
        y = 30;
        vieJoueur = 100;
        nbPoints = 0;
    }

    @Override
    public void update(long delta) throws SlickException {
        if(isDroite){
            x += 1;
        } else {
            x -= 1;
        }
        if(x <= 0){
            x = 0;
        }
        if(x >= container.getWidth() - width){
            x = container.getWidth() - width;
        }
    }

    @Override
    public void render() {
        image.draw(x, y);
    }
}
