import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import java.util.Random;

public class Drapeau extends Entite implements Modulable{
    private final GameContainer container;
    private final GameController gameController;

    public Drapeau(float x, float y, GameContainer container, GameController gameController) {
        super(x, y, 14, 32, "res/drapeau.png");
        this.container = container;
        this.gameController = gameController;
    }

    @Override
    public void update(long delta) throws SlickException {
        y-=1;
        if(y < -height){
            resetDrapeau();
        }
    }

    private void resetDrapeau() {
        if(gameController.drapeaux.size() > 7){
            gameController.drapeauxToDestroy.add(this);
        } else {
            x = new Random().nextInt(700);
            y = (new Random().nextInt(600) + 600);
        }
    }

    @Override
    public void render() {
        image.draw(x, y);
    }
}
