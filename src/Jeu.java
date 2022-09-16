import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;

public class Jeu extends BasicGame {
    private GameContainer container;
    private Image bg;
    private Image playImage;
    private Image quitImage;
    private Image restartImage;
    java.awt.Font font;
    TrueTypeFont ttf;
    GameController gameController;
    private enum GAME_STATE{
        MENU,
        JEU,
        FIN
    }
    private GAME_STATE status = GAME_STATE.MENU;

    public Jeu(String title) {
        super(title);
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        font = new java.awt.Font("Verdana", java.awt.Font.BOLD, 15);
        ttf = new TrueTypeFont(font, true);
        Music bgMusic = new Music("res/song.wav");
        bgMusic.loop();
        bg = new Image("res/blanc.jpg");
        playImage = new Image("res/playButton.png");
        quitImage = new Image("res/quitButton.png");
        restartImage = new Image("res/restartButton.png");
        this.container = gameContainer;
        gameController = new GameController(gameContainer);
    }

    @Override
    public void update(GameContainer gameContainer, int delta) throws SlickException {
        if(gameController.getVieJoueur() < 0){
            status = GAME_STATE.FIN;
        }

        switch(status){
            case MENU:
                int mouseX = Mouse.getX();
                int mouseY = Mouse.getY();

                if((mouseX > 305 && mouseX < 493) && (mouseY > 319 && mouseY < 399)){ // Play
                    if(Mouse.isButtonDown(0)){
                        status = GAME_STATE.JEU;
                        gameController.resetTime();
                    }
                }

                if((mouseX >= 307 && mouseX < 492) && (mouseY > 119 && mouseY < 198)){ // Quit
                    if(Mouse.isButtonDown(0)){
                        container.exit();
                    }
                }
                break;
            case JEU:
                gameController.update(delta);
                break;
            case FIN:
                mouseX = Mouse.getX();
                mouseY = Mouse.getY();

                if((mouseX > 271 && mouseX < 528) && (mouseY > 319 && mouseY < 399)){ // Restart
                    if(Mouse.isButtonDown(0)){
                        status = GAME_STATE.JEU;
                        gameController.restart();
                    }
                }

                if((mouseX >= 307 && mouseX < 492) && (mouseY > 119 && mouseY < 198)){ // Quit
                    if(Mouse.isButtonDown(0)){
                        container.exit();
                    }
                }
                break;
        }

    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        bg.draw();
        switch(status){
            case MENU:
                playImage.draw(306, 200);
                quitImage.draw(307, 400);
                break;
            case JEU:
                gameController.render();
                break;
            case FIN:
                restartImage.draw(271, 200);
                quitImage.draw(307, 400);
                break;
        }
        if(status == GAME_STATE.JEU || status == GAME_STATE.FIN){
            ttf.drawString(570, 10, "Nombre de points : " + gameController.getNbPoints(), Color.cyan);
        }
    }

    @Override
    public void keyPressed(int key, char c) {
        super.keyPressed(key, c);
        if(Input.KEY_D == key || Input.KEY_RIGHT == key){
            gameController.changerDirection(true);
        }
        if(Input.KEY_A == key || Input.KEY_LEFT == key){
            gameController.changerDirection(false);
        }
        if(Input.KEY_ESCAPE == key){
            this.container.exit();
        }
    }
}
