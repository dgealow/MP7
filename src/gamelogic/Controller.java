package gamelogic;

import javax.swing.JFrame;

public class Controller {

    /**
     * Starts the game.
     * 
     * @param args
     *            unused
     */
    public static void main(final String[] args) {
        JFrame listening = new JFrame();
        listening.addKeyListener(new MyKeyListener());
        listening.setVisible(true);
        System.out.print(Util.getFile("Title_Screen.txt"));
        while (started) {

        }
        map = new Map("Map_Data.txt");
        player = map.getPlayer();

        map.printToConsole();
        /*
         * while (player.isAlive()) { // get player's next move int nextMove = -1; while
         * (!map.checkMove(nextMove)) { nextMove = getMove(); } }
         */
    }

    private static boolean started = false;
    private static Map map;
    private static Player player;
    private static MyKeyListener input;

    public static void setStarted(boolean startedNew) {
        started = startedNew;
    }

    public static boolean isStarted() {
        return started;
    }

    /**
     * Tries to move the player in the specified direction. If successful, the
     * player moves, followed by the enemies' turn.
     * 
     * @param direction
     *            the direction to attempt to move
     */
    public static void movePlayer(int direction) {
        if (map.checkMove(direction)) {
            // does some stuff
            map.movePlayer(direction);
            map.printToConsole();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            moveEnemies();
        }
        // don't do anything if the move was invalid
    }

    private static void moveEnemies() {
        map.genHeatMap();
        for (int i = 0; i < map.getEnemies().size(); i += 1) {
            Mob temp = ((Mob) map.getEnemies().get(i));
            temp.attack(temp.x, temp.y, temp.attackRange);
        }
        for (int i = 0; i < map.getEnemies().size(); i += 1) {
            Mob temp = ((Mob) map.getEnemies().get(i));
            temp.move();
        }
        map.printToConsole();
    }

    /*
     * private static int getMove() { return -1; }
     */
}
