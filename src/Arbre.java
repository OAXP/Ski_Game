import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import java.util.Random;

public class Arbre extends Entite implements Modulable{
    private final GameContainer container;
    private final GameController gameController;

    public Arbre(float x, float y, GameContainer container, GameController gameController) {
        super(x, y, 95, 97, "res/arbre.png");
        this.container = container;
        this.gameController = gameController;
    }

    public void resetArbre(){
        if(gameController.arbres.size() > 7){
            gameController.arbresToDestroy.add(this);
        } else {
            x = new Random().nextInt(700);
            y = (new Random().nextInt(600) + 600);
        }
    }

    @Override
    public void update(long delta) throws SlickException {
        y-=1;
        if(y < -height){
            resetArbre();
        }
    }

    @Override
    public void render() {
        image.draw(x, y);
    }
}
