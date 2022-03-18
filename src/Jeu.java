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
	 */
        public Jeu() {
            redSeeds  = new int[]{2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2};
            blueSeeds = new int[]{2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2};
            score1 = 0;
            score2 = 0;
        }

        

        Jeu(int[] redSeeds, int[] blueSeeds, int score1, int score2) {
            this.redSeeds = redSeeds;
            this.blueSeeds = blueSeeds;
            this.score1 = score1;
            this.score2 = score2;
        }

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
                if (Mouvement.ColorRed)  System.out.printf("Player %d plays Mouvement %s with %d seeds\n", playerNo, Mouvement.toString(), redSeeds[Mouvement.position]);
                else {
                    System.out.printf("Player %d plays Mouvement %s with %d seeds\n", playerNo, Mouvement.toString(), + blueSeeds[Mouvement.position]);
                }
                }

            //CAPTURE BEGINS
            /**
	                * Capture les graines si possible dependant des regles du jeu 
                    */
            Color lastColor = getLastColor(Mouvement);

            boolean fail = false;
            int i = pos - Mouvement.position; // Position d'arrivée - Pos de départ
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
                        if (holeCap > 0 && print) System.out.printf("Player %d captures %d blue seeds and %d red seeds from hole %d\n", playerNo, BlueCap,RedCap, capturePos + 1);
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
                    if (newcap > 0 && print) System.out.printf("Player %d captures %d red or blue seeds from hole %d\n", playerNo, newcap, capturePos);
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


            for (int i = 1; i <=8; i=i+2) { 
                if (node.redSeeds[i]==0) compPair++;
            }
            for (int j = 0; j <= 8; j= j + 2) {
                if (node.redSeeds[j]==0) compImpair++;
            }
    
            return (node.score1 >= 33 || node.score2 >= 33 || node.score1 == 32 && node.score2 == 32 ||compPair==8 || compImpair==8) ;
        }

        Mouvement starveMouvement(Jeu node, int playerNo) {
            List<Mouvement> Mouvements = node.legalMouvements(playerNo);
            for (Mouvement Mouvement : Mouvements) {
                if (playerNoMouvements(node.applyMouvement(Mouvement, nextPlayer(playerNo), true), nextPlayer(playerNo))) {
                    //System.out.println("-*-***-*-*-*-*-*-*-*-*-*-*-*-*-");
                    return Mouvement;
                }
            }
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
                if (score1 > score2) return 9999999;
                else if (score2 > score1) return -9999999;
                else return score1 - score2;
            } else {
                if (score2 > score1) return 9999999;
                else if (score1 > score2) return -9999999;
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

        @Override
   
        public String toString() {
            StringBuilder retVal = new StringBuilder("Jeu:\n------------------------------------------------------------------------\n" );

            retVal.append(" redSeeds  = [");
            for (int i = 0 ; i < redSeeds.length ; i++) {

                retVal.append("{\uu001B[35m" +(i+1)+"\u001B[0m} : \u001B[31m"+redSeeds[i]+"\u001B[0m  ");
                
        
            }
            retVal.append("]\u001B[0m");
          

            retVal.append(" \n blueSeeds = [");
            for (int j = 0 ; j < blueSeeds.length ; j++) {
                retVal.append("{\uu001B[35m" +(j+1)+"\u001B[0m} : \u001B[34m"+blueSeeds[j]+"\u001B[0m  ");
               
            

            }
            retVal.append("]\u001B[0m");
            retVal.append("\n");

            retVal.append( "score1=" + "\u001B[32m"+ score1 +"\u001B[0m"+
            ", score2=" + "\u001B[32m"+ score2 +"\u001B[0m"
            );

            return retVal.toString();
        
        }
    }