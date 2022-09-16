import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;
import java.util.Random;

public class GameController implements Modulable{

    GameContainer container;
    private final PlayerOne mainPlayer;
    private long lastTime;
    public final ArrayList<Arbre> arbres = new ArrayList<>();
    public final ArrayList<Drapeau> drapeaux = new ArrayList<>();
    public final ArrayList<Arbre> arbresToDestroy = new ArrayList<>();
    public final ArrayList<Drapeau> drapeauxToDestroy = new ArrayList<>();

    public GameController(GameContainer container) throws SlickException {
        this.container = container;
        this.mainPlayer = new PlayerOne((int)(container.getWidth()/2), 30, container);
        for (int i = 0; i < 7; i++) {
            Arbre arbre = new Arbre(new Random().nextInt(700), (new Random().nextInt(600) + 600), container, this);
            arbres.add(arbre);
            Drapeau drapeau = new Drapeau(new Random().nextInt(700), (new Random().nextInt(600) + 600), container, this);
            drapeaux.add(drapeau);
        }
        lastTime = System.currentTimeMillis();
    }

    public boolean isCollisionEntre(Entite a, Entite b){
        return a.getRectangle().intersects(b.getRectangle());
    }

    public void resetTime(){
        lastTime = System.currentTimeMillis();
    }

    public void restart(){
        resetTime();
        mainPlayer.resetPlayer();
        arbres.clear();
        drapeaux.clear();
    }

    public void changerDirection(boolean isDroite){
        mainPlayer.changerDirection(isDroite);
    }

    public int getVieJoueur(){
        return mainPlayer.getVieJoueur();
    }

    public int getNbPoints(){
        return mainPlayer.getNbPoints();
    }

    @Override
    public void update(long delta) throws SlickException {
        int ecoule = (int) (System.currentTimeMillis() - lastTime);
        if(ecoule >= 10){
            mainPlayer.ajouterPoints(1);
            resetTime();
        }
        if(arbres.size() < 7){
            int size = 7 - arbres.size();
            for (int i = 0; i < size; i++) {
                Arbre arbre = new Arbre(new Random().nextInt(700), (new Random().nextInt(600) + 600), container, this);
                arbres.add(arbre);
            }
        }
        if(drapeaux.size() < 7){
            int size = 7 - drapeaux.size();
            for (int i = 0; i < size; i++) {
                Drapeau drapeau = new Drapeau(new Random().nextInt(700), (new Random().nextInt(600) + 600), container, this);
                drapeaux.add(drapeau);
            }
        }

        mainPlayer.update(delta);
        for (Drapeau d : drapeaux){
            d.update(delta);
            if(!d.getDetruire() && isCollisionEntre(d, mainPlayer)){
                mainPlayer.ajouterPoints(50);
                drapeauxToDestroy.add(d);
                d.setDetruire(true);
            }
        }
        for (Arbre a : arbres){
            a.update(delta);
            if(!a.getDetruire() && isCollisionEntre(a, mainPlayer)){
                mainPlayer.tuer();
                arbresToDestroy.add(a);
                a.setDetruire(true);
            }
        }
        arbres.removeAll(arbresToDestroy);
        arbresToDestroy.clear();
        drapeaux.removeAll(drapeauxToDestroy);
        drapeauxToDestroy.clear();
    }

    @Override
    public void render() {
        for (Drapeau d : drapeaux){
            d.render();
        }
        for (Arbre a : arbres){
            a.render();
        }
        mainPlayer.render();
    }
}
