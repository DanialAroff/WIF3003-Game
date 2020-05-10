
package wif3003.assignment;

public class generateFloats implements Runnable{
    private Game game;
    
    public generateFloats(int n, int t, int m) {
        game = new Game(n, t, m);
    }

    @Override
    public void run() {
        try {
            Thread.sleep(400);
        } catch (Exception e) {
        }
        game.generate1();
    }
}
