import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.function.IntSupplier;


public class AdvancedOware {
    int enemyRange;
    int myRange;

    private final int DEPTH = 8;
    private final int SPECIAL_DEPTH = 6;

    public AdvancedOware() {
    }


    /**
     * Permet de jouer
     */
    public void play() {
        play(init(), new Jeu());
    }


    private int getDeph(Jeu node){
        return DEPTH;

    }


    /**
     * play function
     *
     * @param computerStart
     * @param game
     */
    private void play(boolean computerStart, Jeu game) {
        System.out.println(game);
        boolean robotPlay = computerStart;
        int currentPlayer = 1;
        int expected;
        Mouvement bestMouvement = new Mouvement();

        while (!Jeu.gameOver(game)) {
            if (Jeu.playerNoMouvements(game, currentPlayer)) {
                game.captureNoMouvements();
                break;
            }
            if (robotPlay) {
                //System.out.println(Arrays.toString(game.legalMouvements(currentPlayer).toArray()));
                expected = game.minimax(game, bestMouvement, getDeph(game), currentPlayer, true, -1000000000, 1000000000);
                System.out.printf("expected value : %d\n", expected);
            } else {
                //System.out.println(game.toString());
                bestMouvement = nextRequest(game);
            }

            game = game.applyMouvement(bestMouvement, currentPlayer, true);
            System.out.println(game.toString());
            currentPlayer = Jeu.nextPlayer(currentPlayer);
            robotPlay = !robotPlay;

        }

        System.out.printf("Partie terminée! Score joueur 1: %d, score joueur 2: %d\n", game.score1, game.score2);
    }

    /**
     * Permet d'avoir le moove suivant
     *
     * @return
     */
    private Mouvement nextRequest(Jeu Jeu) {

        String res = "";
        Mouvement request;

        while (!res.matches("[0-9]*[a-zA-Z][0-9]*")) {
            System.out.print("Taper le coup à jouer:\n");
            Scanner in = new Scanner(System.in);
            res = in.nextLine();
        }

        request = new Mouvement(res);

        if (!(Jeu.blueSeeds[request.position] + Jeu.redSeeds[request.position] > 0)) {
            System.out.println("[WARNING] Placement illegal");
            return nextRequest(Jeu);
        }

        //}
        return request;
    }

    private int scanInt() {
        int res = -1;

        while (res <= 0) {
            Scanner in = new Scanner(System.in);
            res = in.nextInt();
        }

        return res;
    }


    /**
     * Permet d'initialiser la partie
     *
     * @return
     */
    private boolean init() {
        System.out.print("Initialisation de la partie...\n");
        System.out.print("Quel est le joueur qui commence en premier ? [robot|player]\n");
        Scanner in = new Scanner(System.in);
        String res = in.nextLine();

        System.out.println(res);

        if (res.equalsIgnoreCase("robot")) {
            myRange = 0;
            enemyRange = 6;
            return true;
        } else if (res.equalsIgnoreCase("player")) {
            myRange = 6;
            enemyRange = 0;
            return false;
        } else {
            return init();
        }
    }
}