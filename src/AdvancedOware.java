import java.util.Scanner;


public class AdvancedOware {
    int enemyRange;
    int myRange;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    private final int DEPTH = 8;



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
        System.out.println( game);
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
                bestMouvement = nextRequest(game,currentPlayer);
            }

            game = game.applyMouvement(bestMouvement, currentPlayer, true); //suprimer les prints de Elaraus
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
    private Mouvement nextRequest(Jeu Jeu,int playerNum) {

        String res = "";
        Mouvement request;

        while (!res.matches("[0-9]*[a-zA-Z][0-9]*")) {
            System.out.print("Taper le coup à jouer:\n");
            try (Scanner in = new Scanner(System.in)) {
                res = in.nextLine();
            }
        }

        request = new Mouvement(res);

        if (!(Jeu.blueSeeds[request.position] + Jeu.redSeeds[request.position] > 0)) {
            System.out.println("[WARNING] Placement illegal");
            return nextRequest(Jeu,playerNum);
        }

        if (playerNum==2){
            if ( request.position %2 == 0 ){
                System.out.println("[WARNING] Placement illegal, Only Odd hole are allowed");
                return nextRequest(Jeu,playerNum);
        }
    }
        if(playerNum==1){
            if ( request.position %2 != 0 ){
                System.out.println("[WARNING] Placement illegal, Only Pair hole are allowed");
                return nextRequest(Jeu,playerNum);
        }

        }

     
       



        //}
        return request;
    }



    /**
     * Permet d'initialiser la partie
     *
     * @return
     */
    private boolean init() {
        System.out.print("Initialisation de la partie...\n");
        System.out.print("Quel est le joueur qui commence en premier ? [robot|player]\n");
        try (Scanner in = new Scanner(System.in)) {
            String res = in.nextLine();

            System.out.println(res);

            if (res.equalsIgnoreCase("robot")) {
                
                return true;
            } else if (res.equalsIgnoreCase("player")) {
                return false;
            } else {
                return init();
            }
        }
    }
}