import org.newdawn.slick.SlickException;

public interface Modulable {

    void update(long delta) throws SlickException;
    void render();

}