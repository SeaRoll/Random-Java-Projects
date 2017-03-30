/**
 * Opens two JFrames.
 * When closing old frame. both will close.
 * When new frame is closed. only it will close.
 * Created by Yohan on 3/30/2017.
 */
public class mainProgram {


    public static void main(String[] args) {
        //Instantiate Main Frame
        mainFrame oldFrame = new mainFrame();

        //Instantiate New Frame
        newFrame newframe = new newFrame();
    }
}
