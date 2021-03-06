import org.fusesource.jansi.AnsiConsole;

import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;

import java.util.ArrayList;
import java.util.List;

    public class Jeu {
        int[] redSeeds;
        int[] blueSeeds;
        int score1;
        int score2;
/**
	 * Constructeur créant un nouveau plateau[16][2] avec toutes les cellules
	 * initilisées à 4 graines (2 graines de chaque couleur)
     *  Ne prend pas de parametres
	 */
        public Jeu() {
            redSeeds  = new int[]{2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2};
            blueSeeds = new int[]{2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2};
            score1 = 0;
            score2 = 0;
        }


        /**
         * Constructeur avec paramteres
         * @param redSeeds
         * @param blueSeeds
         * @param score1
         * @param score2
         */
        Jeu(int[] redSeeds, int[] blueSeeds, int score1, int score2) {
            this.redSeeds = redSeeds;
            this.blueSeeds = blueSeeds;
            this.score1 = score1;
            this.score2 = score2;
        }

        /**
         * Permet de s'assurer q'un mouvement est legal vis à vis des regles du jeu
         * @param playerNo le joueur afin de savoir quel cases il a le droit de jouer
         * @return une liste de mouvement
         */
        List<Mouvement> legalMouvements(int playerNo ) { 

            //Permet de vérifier si un mouvement n'est pas effectué sur une case vide. 
            ArrayList<Mouvement> res = new ArrayList<>();
            int[]pair = new int[]{2,4,6,8,10,12,14,16};
            int[]impair = new int[]{1,3,5,7,9,11,13,15};
            
            if (playerNo==2){

            
            for (int number : pair)
             {
                if (redSeeds[number-1] > 0) { 
                    res.add(new Mouvement(number-1,true));
                }
                if (blueSeeds[number-1] > 0) { 
                    res.add(new Mouvement(number-1,false));
                }
                if (redSeeds[number-1] == 0 && blueSeeds[number-1] == 0 ) {
                    res.add(new Mouvement(number-1,true));
                    res.add(new Mouvement(number-1,false));
                }
            }
        }else{
            for (int number : impair)
             {
                if (redSeeds[number-1] > 0) { 
                    res.add(new Mouvement(number-1,true));
                }
                if (blueSeeds[number-1] > 0) { 
                    res.add(new Mouvement(number-1,false));
                }
                if (redSeeds[number-1] == 0 && blueSeeds[number-1] == 0 ) {
                    res.add(new Mouvement(number-1,true));
                    res.add(new Mouvement(number-1,false));
                }
            }
        }

            return res;
        }

        /**
         * Foctio principale de notre jeu, on retrouve les etapes de deplacement de graines et de captures
         * @param Mouvement
         * @param playerNo
         * @param print
         * @return un Jeu
         */
        Jeu applyMouvement(Mouvement Mouvement, int playerNo, boolean print) {
            int tracker = 1;

            int[] newRedSeeds = redSeeds.clone();
            int[] newblueSeeds = blueSeeds.clone();
            

            int numRedSeeds = newRedSeeds[Mouvement.position];
            int numblueSeeds = newblueSeeds[Mouvement.position];
            int pos = 0;
                    /**
	                * Déplace les graines d'une cellules dans les cellules suivantes dependant des regles du jeu.
                    */
                if (Mouvement.ColorRed){
                    while (numRedSeeds > 0) {
                        pos = (Mouvement.position + tracker) % 16;
                        if (pos != Mouvement.position) {
        
                            newRedSeeds[pos]++;
                            numRedSeeds--;
        
                        } 
                    
                        tracker++;
                    }
                    newRedSeeds[Mouvement.position] = 0;
                }
                else{
                    if (playerNo==2 | playerNo==1){ 
                    while (numblueSeeds > 0) {
                        int i = 0;
                        pos = (Mouvement.position + tracker + i ) % 16;
                        if (pos != Mouvement.position) { 
                            newblueSeeds[pos]++;
                            numblueSeeds--;
        
                        } 
                        tracker+= 2;
                        i++ ;
                    }
                    newblueSeeds[Mouvement.position] = 0;
                }
                }

            if (print) {
                if (Mouvement.ColorRed)
                    System.out.printf("Le joueur %d joue le mouvement %s avec %d graines\n", playerNo, Mouvement.toString(), redSeeds[Mouvement.position]);
                else {
                    System.out.printf("Le joueur %d joue le mouvement %s avec %d graines\n", playerNo, Mouvement.toString(), +blueSeeds[Mouvement.position]);
                }
                }

            //LA CAPTURE COMMENCE
            // Capture les graines si possible dependant des regles du jeu

            Color lastColor = getLastColor(Mouvement);

            boolean fail = false;
            int i = pos - Mouvement.position; // Position d'arrivée - Position de départ
            if (i < 0) i += 16; //
            int count = 0;
            while (!fail && (i >= 0)) {
                if (playerNo == 1 && Math.floorMod((Mouvement.position + i), 16) < 8) {
                    break;
                } else if (playerNo == 2 && Math.floorMod((Mouvement.position + i), 16) > 7) {
                    break;
                }
                int capturePos = Math.floorMod((Mouvement.position + i), 16);
                int holeCap,BlueCap,RedCap;
                if (lastColor == Color.Blue ||lastColor == Color.RED ) {
                    BlueCap = newblueSeeds[capturePos];
                    RedCap  = newRedSeeds[capturePos];
                    holeCap=BlueCap+RedCap;
                    if (holeCap == 2 || holeCap == 3) {
                        newblueSeeds[capturePos] = 0;
                        newRedSeeds[capturePos] = 0;
                        if (holeCap > 0 && print)
                            System.out.printf("Le joueur %d capture %d graines bleues et %d graines rouges dans le trou %d\n", playerNo, BlueCap, RedCap, capturePos + 1);
                        count += holeCap;
                        i--;
                    } 
                    else fail = true;
                }
               else {
                    int holeRed = newRedSeeds[capturePos] ;
                    int holeblue = newblueSeeds[capturePos];
                    int newcap = 0;
                    boolean redCap = false;
                    boolean blueCap = false;
                    
                    if (holeRed == 2 || holeRed == 3) {
                        newcap += newRedSeeds[capturePos];
                        newRedSeeds[capturePos] = 0;
                        redCap = true;
                    }
                    if (holeblue == 2 || holeblue == 3) {
                        newcap += newblueSeeds[capturePos];
                        newblueSeeds[capturePos] = 0;
                        blueCap = true;
                    }
                    if (newcap > 0 && print)
                        System.out.printf("Le joueur %d capture %d graines rouges ou bleues du trou %d\n", playerNo, newcap, capturePos);
                    count += newcap;
                    i--;

                    if (redCap) {
                        lastColor = Color.RED;
                    } else if (blueCap) {
                        lastColor = Color.Blue;
                    } else {
                        fail = true;
                    }
                }
            }
            Jeu res;
            if (playerNo == 1) res = new Jeu(newRedSeeds, newblueSeeds, score1 + count, score2);
            else res = new Jeu(newRedSeeds, newblueSeeds, score1, score2 + count);

            return res;
        }

        /**
         * Fonction MinMax
         * @param node
         * @param bestMouvement
         * @param depth
         * @param playerNo
         * @param maximisingPlayer
         * @param alpha
         * @param beta
         * @return un entier
         */
        int minimax(Jeu node, Mouvement bestMouvement, int depth, int playerNo, boolean maximisingPlayer, int alpha, int beta) {
            // VÉRIFIER SI LE NŒUD EST TERMINAL OU FEUILLE ET L'ÉVALUER SI C'EST LE CAS
            if (playerNoMouvements(node, nextPlayer(playerNo))) {
                bestMouvement.set(starveMouvement(node, playerNo));
                return evalNoMouvements(node, playerNo, maximisingPlayer);
            }
            //si (profondeur == 0 || gameOver(node)) return evalNode(node, playerNo, maximisingPlayer) ;
            if (depth == 0 || gameOver(node)) return advancedEval(node, playerNo, maximisingPlayer);

            // APPLIQUER LES MOUVEMENTS POSSIBLES AUX NŒUDS ACTUELS POUR GENERER UNE LISTE DE NŒUDES ENFANTS
            List<Mouvement> Mouvements = node.legalMouvements(playerNo);

            Mouvement garbage = new Mouvement();

            for (Mouvement Mouvement : Mouvements) {
                History.save(node);
                node = node.applyMouvement(Mouvement, playerNo, false);
                int score = - minimax(node, garbage, depth - 1, nextPlayer(playerNo), !maximisingPlayer, -beta, -alpha);
                node = History.restore();

                if (score > alpha) {
                    alpha = score;
                    bestMouvement.set(Mouvement);
                }

                if (alpha >= beta) {
                    break;
                }
            }
            return alpha;
        }
        // Fonction d'arret de jeu ( voir les regles)
        static boolean gameOver(Jeu node) {
            int compPair=0;
            int compImpair=0;


            for (int i = 1; i <16; i=i+2) {
                if (node.redSeeds[i]==0 && node.blueSeeds[i]==0) compPair++;
            }

            for (int j = 0; j < 16; j= j + 2) {
                if (node.redSeeds[j]==0  && node.blueSeeds[j]==0) compImpair++;
            }
            //System.out.println("compte Impair : "+ compImpair +", Compte Pair = "+ compPair);


            return (node.score1 >= 33 || node.score2 >= 33 || node.score1 == 32 && node.score2 == 32 ||compPair>=8 || compImpair>=8) ;
        }

        Mouvement starveMouvement(Jeu node, int playerNo) {
            List<Mouvement> Mouvements = node.legalMouvements(playerNo);
           /* for (Mouvement Mouvement : Mouvements) {
                if (playerNoMouvements(node.applyMouvement(Mouvement, nextPlayer(playerNo), true), nextPlayer(playerNo))) {
                    //System.out.println("-*-***-*-*-*-*-*-*-*-*-*-*-*-*-");
                    return Mouvement;
                }
            }*/
            return Mouvements.get(1);
        }



        int advancedEval(Jeu node, int playerNo, boolean maximisingPlayer) {


            boolean p1max = playerNo == 1 && maximisingPlayer || playerNo == 2 && !maximisingPlayer;

            int staticScore;
            int staticWeight = 100;
            if (p1max) {
                if (node.score1 >= 38) staticScore = 10000000;
                else if (node.score2 >= 38) staticScore = -10000000;
                else staticScore =  staticWeight * node.score1;
            } else {
                if (node.score2 >= 38) staticScore = 10000000;
                else if (node.score1 >= 38) staticScore = -10000000;
                else staticScore =  staticWeight * node.score2;
            }

            int maxPitWeight = 20;
            int maxPit = maxPitWeight * countMaxPit(node, playerNo);

            int seedWeight = 19;
            int seedsOnSide = seedWeight * countSeedsOnSide(node, playerNo);

            int pitsWeight = 37;
            int playablePits = pitsWeight * countPlayablePits(node, playerNo);

            int opponentScoreWeight = 57;
            int opponentScore = opponentScoreWeight * opponentCounterMouvement(node, playerNo);

            return staticScore + maxPit + seedsOnSide + playablePits + opponentScore;
        }

        int countMaxPit(Jeu node, int playerNo) {
            int count = 0;
            for (int i = 8 * (playerNo - 1); i < 8 * playerNo; i++) {
                count = Math.max(count, node.blueSeeds[i] + node.redSeeds[i]);
            }
            return count;
        }

        int countSeedsOnSide(Jeu node, int playerNo) {
            int acc = 0;
            for (int i = 8 * (playerNo - 1); i < 8 * playerNo; i++) {
                acc += node.blueSeeds[i] + node.redSeeds[i] ;
            }
            return acc;
        }

        int countPlayablePits(Jeu node, int playerNo) {
            int count = 0;
            for (int i = 8 * (playerNo - 1); i < 8 * playerNo; i++) {
                if (node.blueSeeds[i] + node.redSeeds[i]  > 0) count++;
            }
            return count;
        }

        int opponentCounterMouvement(Jeu node, int playerNo) {
            List<Mouvement> Mouvements = node.legalMouvements(nextPlayer(playerNo));
            int oppScore = 0;
            for (Mouvement Mouvement : Mouvements) {
                if (playerNo == 1) {
                    oppScore = -Math.max(oppScore, node.applyMouvement(Mouvement, 2, false).score2);
                } else {
                    oppScore = -Math.max(oppScore, node.applyMouvement(Mouvement, 1, false).score1);
                }
            }
            return oppScore;
        }

        static boolean playerNoMouvements(Jeu node, int playerNo) {
            int acc1 = 0;
            int acc2 = 0;
            for (int i = 0; i <= 7; i++) {
                acc1 += node.redSeeds[i] + node.blueSeeds[i];
                int j = i + 8;
                acc2 += node.redSeeds[j] + node.blueSeeds[j];
            }
            if (playerNo == 1) return (acc1 == 0);
            else return (acc2 == 0);
        }

        int evalNoMouvements(Jeu node, int playerNo, boolean maximisingPlayer) {
            int score1 = node.score1;
            int score2 = node.score2;

            for (int i = 0; i <= 7; i++) {
                score1 += node.redSeeds[i] + node.blueSeeds[i] ;
                int j = i + 8;
                score2 += node.redSeeds[j] + node.blueSeeds[j] ;
            }

            if (playerNo == 1 && maximisingPlayer || playerNo == 2 && !maximisingPlayer) {
                if (score1 > score2) return 99999;
                else if (score2 > score1) return -99999;
                else return score1 - score2;
            } else {
                if (score2 > score1) return 99999;
                else if (score1 > score2) return -99999;
                else return score2 - score1;
            }
        }

        void captureNoMouvements() {
            for (int i = 0; i <= 7; i++) {
                score1 += redSeeds[i] + blueSeeds[i] ;
                redSeeds[i] = 0;
                blueSeeds[i] = 0;
                
                int j = i + 8;
                score2 += redSeeds[j] + blueSeeds[j] ;
                redSeeds[j] = 0;
                blueSeeds[j] = 0;
                
            }
        }

        static int nextPlayer(int playerNo) {
            if (playerNo == 1) return 2;
            else return 1;
        }
        Color getLastColor(Mouvement move) {
            
            if (move.ColorRed) {
                if (blueSeeds[move.position] > 0) return Color.Blue;
                else return Color.RED;
            } else {
                if (redSeeds[move.position] > 0) return Color.RED;
                else return Color.Blue;
            }
        }


        public static final String ANSI_RESET = "\u001B[0m";
        public static final String ANSI_BLACK = "\u001B[30m";
        public static final String ANSI_RED = "\u001B[31m";
        public static final String ANSI_GREEN = "\u001B[32m";
        public static final String ANSI_YELLOW = "\u001B[33m";
        public static final String ANSI_BLUE = "\u001B[34m";
        public static final String ANSI_PURPLE = "\u001B[35m";
        public static final String ANSI_CYAN = "\u001B[36m";
        public static final String ANSI_WHITE = "\u001B[37m";
        @Override

       /* public String toString() {
            AnsiConsole.systemInstall();
            AnsiConsole.out.println(ANSI_CYAN +"Jeu:\n------------------------------------------------------------------------------------------------------------------------------------------------\n"+ ANSI_RESET);
            AnsiConsole.out.print(" redSeeds  = [");

            for (int i = 0 ; i < redSeeds.length ; i++) {

                AnsiConsole.out.print(" {"+ANSI_PURPLE+(i+1)+ANSI_RESET+"}:"+ANSI_RED+redSeeds[i]+ANSI_RESET);

            }

            AnsiConsole.out.print(" ] \n blueSeeds = [");
            for (int j = 0 ; j < blueSeeds.length ; j++) {
                AnsiConsole.out.print(" {"+ANSI_PURPLE+(j+1)+ANSI_RESET+"}:"+ANSI_BLUE+redSeeds[j]+ANSI_RESET);

            }

            AnsiConsole.out.print(" ]\n score1="+ ANSI_YELLOW+ score1 +ANSI_RESET+ ", score2=" +ANSI_YELLOW+ score2+ANSI_RESET );



            return ANSI_YELLOW+ score1+ANSI_RESET ;
        
        }*/

        public String toString() {
            AnsiConsole.systemInstall();
            StringBuilder retVal = new StringBuilder(ANSI_CYAN + "Jeu:\n------------------------------------------------------------------------------------------------------------------------------------------------\n" + ANSI_RESET);

            retVal.append(" redSeeds  = [");
            for (int i = 0; i < redSeeds.length; i++) {

                retVal.append(" {" + ANSI_PURPLE + (i + 1) + ANSI_RESET + "}:" + ANSI_RED + redSeeds[i] + ANSI_RESET);


            }
            retVal.append("]");


            retVal.append(" \n blueSeeds = [");
            for (int j = 0; j < blueSeeds.length; j++) {
                retVal.append(" {" + ANSI_PURPLE + (j + 1) + ANSI_RESET + "}:" + ANSI_BLUE + blueSeeds[j] + ANSI_RESET);


            }
            retVal.append("]");
            retVal.append("\n");

            retVal.append(" ]\n score1=" + ANSI_YELLOW + score1 + ANSI_RESET + ", score2=" + ANSI_YELLOW + score2 + ANSI_RESET);
            AnsiConsole.systemInstall();

            return retVal.toString();

        }
    }