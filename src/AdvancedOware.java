import java.util.Scanner;


public class AdvancedOware {

    private final int DEPTH = 8;


    /**
     * Permet de lancer une partie de jeu
     * le jeu ca lance avec  une initialisation de jeu et un moteur de jeu
     */
    public void play() {
        play(init(), new Jeu());
    }

    /**
     * etant onné q'un joueur a acces a 8 cases pour jouer, alors notre arbre est de hauteur 8
     * @param node un moteur de jeu
     * @return la hauteur de l'arbre
     */
    private int getDeph(Jeu node){
        return DEPTH;

    }


    /**
     * play function
     *
     * @param computerStart  savoir si c'est l'IA qui joue en premier
     * @param game un moteur de jeu
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
                expected = game.minimax(game, bestMouvement, getDeph(game), currentPlayer, true, -1000000000, 1000000000);
                //System.out.printf("expected value : %d\n", expected);
            } else {
                bestMouvement = nextRequest(game,currentPlayer);
            }

            game = game.applyMouvement(bestMouvement, currentPlayer, true);
            System.out.println(game.toString());
            currentPlayer = Jeu.nextPlayer(currentPlayer);
            robotPlay = !robotPlay;

        }

        System.out.printf("Partie terminée! Score joueur 1: %d, score joueur 2: %d\n", game.score1, game.score2);
    }

    /**
     * Permet d'avoir le mouvement suivant
     * @param Jeu un moteur de jeu
     * @param playerNum le numero du joueur qui joue
     * @return un mouvement
     */
    private Mouvement nextRequest(Jeu Jeu,int playerNum) {

        String res = "";
        Mouvement request;

        while (!res.matches("[0-9]*[a-zA-Z][0-9]*")) {
            System.out.print("Taper le coup à jouer:\n");
            Scanner in = new Scanner(System.in);
            res = in.nextLine();
        }

        request = new Mouvement(res);

        if (!(Jeu.blueSeeds[request.position] + Jeu.redSeeds[request.position] > 0)) {
            System.out.println("[AVERTISSEMENT] Placement illégal");
            return nextRequest(Jeu,playerNum);
        }

        if (playerNum==2){
            if ( request.position %2 == 0 ){
                System.out.println("[AVERTISSEMENT] Placement illégal, seuls les trous impairs sont autorisés.");
                return nextRequest(Jeu,playerNum);
            }
        }
        if(playerNum==1){
            if ( request.position %2 != 0 ){
                System.out.println("[AVERTISSEMENT] Placement illégal, seuls les trous de paires sont autorisés.");
                return nextRequest(Jeu,playerNum);
            }

        }

        return request;
    }



    /**
     * Permet d'initialiser le debut d'une partie
     *
     * @return si le firstplayer est l'IA ou pas
     */
    private boolean init() {
        System.out.print("Initialisation de la partie.......\n");
        System.out.print("Quel  joueur  commence en premier ? Choisir entre : [robot|player]\n");
        Scanner in = new Scanner(System.in);
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