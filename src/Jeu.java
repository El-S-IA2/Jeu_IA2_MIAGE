import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Jeu {
    int[] redSeeds;
    int[] blueSeeds;
    int score1;
    int score2;

    public Jeu() {
        redSeeds = new int[]{2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,2,2,2,2};
        blueSeeds = new int[]{2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,2,2,2,2};
        score1 = 0;
        score2 = 0;
    }

    

    Jeu(int[] redSeeds, int[] blueSeeds, int score1, int score2) {
        this.redSeeds = redSeeds;
        this.blueSeeds = blueSeeds;
        this.score1 = score1;
        this.score2 = score2;
    }

    List<Mouvement> legalMouvements(int playerNo) {
        ArrayList<Mouvement> res = new ArrayList<>();
        int offset = 6 * (playerNo - 1);

        for (int i = offset; i < offset + 6; i++) {
            if (redSeeds[i] > 0) { 
                res.add(new Mouvement(i, true));
            }
            if (blueSeeds[i] > 0) { 
                res.add(new Mouvement(i, false));
            }
            if (redSeeds[i] == 0 && blueSeeds[i] == 0 ) {
                res.add(new Mouvement(i, true));
                res.add(new Mouvement(i, false));
            }
        }

        return res;
    }

    Jeu applyMouvement(Mouvement Mouvement, int playerNo, boolean print) {
        int tracker = 1;
        int skipped = 0;

        int[] newRedSeeds = redSeeds.clone();
        int[] newblueSeeds = blueSeeds.clone();
        

        int numRedSeeds = newRedSeeds[Mouvement.position];
        int numblueSeeds = newblueSeeds[Mouvement.position];
        int pos = 0;
        
        if (Mouvement.PairFirst) {
            while (numRedSeeds > 0) {
                pos = (Mouvement.position + tracker) % 12;
                if (pos != Mouvement.position) {

                    newRedSeeds[pos]++;
                    numRedSeeds--;

                } else skipped++;
                tracker++;
            }
            while (numblueSeeds > 0) {
                pos = (Mouvement.position + tracker) % 12;
                if (pos != Mouvement.position) {

                    newblueSeeds[pos]++;
                    numblueSeeds--;

                } else skipped++;
                tracker++;
            }
        } else {
            while (numblueSeeds > 0) {
                pos = (Mouvement.position + tracker) % 12;
                if (pos != Mouvement.position) {

                    newblueSeeds[pos]++;
                    numblueSeeds--;

                } else skipped++;
                tracker++;
            }
            while (numRedSeeds > 0) {
                pos = (Mouvement.position + tracker) % 12;
                if (pos != Mouvement.position) {

                    newRedSeeds[pos]++;
                    numRedSeeds--;

                } else skipped++;
                tracker++;
            }
        }

        newRedSeeds[Mouvement.position] = 0;
        newblueSeeds[Mouvement.position] = 0;

        if (print) {
            System.out.printf("Player %d plays Mouvement %s with %d seeds\n", playerNo, Mouvement.toString(), redSeeds[Mouvement.position] + blueSeeds[Mouvement.position]);
        }

        //CAPTURE BEGINS
        Color lastColor = getLastColor(Mouvement);

        boolean fail = false;
        int i = pos - Mouvement.position;
        if (i < 0) i += 12;
        int count = 0;
        while (!fail && (i >= 0)) {
            if (playerNo == 1 && Math.floorMod((Mouvement.position + i), 12) < 6) {
                break;
            } else if (playerNo == 2 && Math.floorMod((Mouvement.position + i), 12) > 5) {
                break;
            }
            int capturePos = Math.floorMod((Mouvement.position + i), 12);
            int hole;
            if (lastColor == Color.Blue) {
                hole = newblueSeeds[capturePos] ;
                if (hole == 2 || hole == 3) {
                    int newcap = 0;
                    newcap += newblueSeeds[capturePos] ;
                    newblueSeeds[capturePos] = 0;
                    if (newcap > 0 && print) System.out.printf("Player %d captures %d black seeds from hole %d\n", playerNo, newcap, capturePos + 1);
                    count += newcap;
                    i--;
                } else fail = true;
            } else if (lastColor == Color.RED){
                hole = newRedSeeds[capturePos] ;
                if (hole == 2 || hole == 3) {
                    int newcap = 0;
                    newcap += newRedSeeds[capturePos];
                    newRedSeeds[capturePos] = 0;
                    if (newcap > 0 && print) System.out.printf("Player %d captures %d red seeds from hole %d\n", playerNo, newcap, capturePos + 1);
                    count += newcap;
                    i--;
                } else fail = true;
            } else {
                int holeRed = newRedSeeds[capturePos] ;
                int holeBlack = newblueSeeds[capturePos];
                int newcap = 0;
                boolean redCap = false;
                boolean blackCap = false;
                
                if (holeRed == 2 || holeRed == 3) {
                    newcap += newRedSeeds[capturePos];
                    newRedSeeds[capturePos] = 0;
                    redCap = true;
                }
                if (holeBlack == 2 || holeBlack == 3) {
                    newcap += newblueSeeds[capturePos];
                    newblueSeeds[capturePos] = 0;
                    blackCap = true;
                }
                if (newcap > 0 && print) System.out.printf("Player %d captures %d red or black seeds from hole %d\n", playerNo, newcap, capturePos);
                count += newcap;
                i--;

                if (redCap) {
                    lastColor = Color.RED;
                } else if (blackCap) {
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
        // CHECK IF TERMINAL OR LEAF NODE AND EVALUATE IF SO
        if (playerNoMouvements(node, nextPlayer(playerNo))) {
            bestMouvement.set(starveMouvement(node, playerNo));
            return evalNoMouvements(node, playerNo, maximisingPlayer);
        }
        //if (depth == 0 || gameOver(node)) return evalNode(node, playerNo, maximisingPlayer);
        if (depth == 0 || gameOver(node)) return advancedEval(node, playerNo, maximisingPlayer);

        // APPLY POSSIBLE MouvementS TO CURRENT NODES TO GENERATE LIST OF CHILD NODES
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

    static boolean gameOver(Jeu node) {
        return (node.score1 > 33 || node.score2 > 33 || node.score1 == 32 && node.score2 == 32);
    }

    Mouvement starveMouvement(Jeu node, int playerNo) {
        List<Mouvement> Mouvements = node.legalMouvements(playerNo);
        for (Mouvement Mouvement : Mouvements) {
            if (playerNoMouvements(node.applyMouvement(Mouvement, nextPlayer(playerNo), false), nextPlayer(playerNo))) {
                return Mouvement;
            }
        }
        return Mouvements.get(1);
    }

    int evalNode(Jeu node, int playerNo, boolean maximisingPlayer) {
        if (playerNo == 1 && maximisingPlayer || playerNo == 2 && !maximisingPlayer) {
            if (node.score1 >= 38) return 10000000;
            else if (node.score2 >= 38) return -10000000;
            else return node.score1 - node.score2;
        } else {
            if (node.score2 >= 38) return 10000000;
            else if (node.score1 >= 38) return -10000000;
            else return node.score2 - node.score1;
        }
    }

    int advancedEval(Jeu node, int playerNo, boolean maximisingPlayer) {
        //if (playerNoMouvements(node, nextPlayer(playerNo))) {
        //    return 100000000;
        //}

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
        for (int i = 6 * (playerNo - 1); i < 6 * playerNo; i++) {
            count = Math.max(count, node.blueSeeds[i] + node.redSeeds[i]);
        }
        return count;
    }

    int countSeedsOnSide(Jeu node, int playerNo) {
        int acc = 0;
        for (int i = 6 * (playerNo - 1); i < 6 * playerNo; i++) {
            acc += node.blueSeeds[i] + node.redSeeds[i] ;
        }
        return acc;
    }

    int countPlayablePits(Jeu node, int playerNo) {
        int count = 0;
        for (int i = 6 * (playerNo - 1); i < 6 * playerNo; i++) {
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
        for (int i = 0; i <= 5; i++) {
            acc1 += node.redSeeds[i] + node.blueSeeds[i];
            int j = i + 6;
            acc2 += node.redSeeds[j] + node.blueSeeds[j];
        }
        if (playerNo == 1) return (acc1 == 0);
        else return (acc2 == 0);
    }

    int evalNoMouvements(Jeu node, int playerNo, boolean maximisingPlayer) {
        int score1 = node.score1;
        int score2 = node.score2;

        for (int i = 0; i <= 5; i++) {
            score1 += node.redSeeds[i] + node.blueSeeds[i] ;
            int j = i + 6;
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
        for (int i = 0; i <= 5; i++) {
            score1 += redSeeds[i] + blueSeeds[i] ;
            redSeeds[i] = 0;
            blueSeeds[i] = 0;
            
            int j = i + 6;
            score2 += redSeeds[j] + blueSeeds[j] ;
            redSeeds[j] = 0;
            blueSeeds[j] = 0;
            
        }
    }

    static int nextPlayer(int playerNo) {
        if (playerNo == 1) return 2;
        else return 1;
    }

    Color getLastColor(Mouvement Mouvement) {
        if (Mouvement.PairFirst) {
            if (blueSeeds[Mouvement.position] > 0) return Color.Blue;
            else return Color.RED;
        } else {
            if (redSeeds[Mouvement.position] > 0) return Color.RED;
            else return Color.Blue;
        }
    }

    @Override
    public String toString() {
        return "Jeu{" +
                "redSeeds=" + Arrays.toString(redSeeds) +
                ", blueSeeds=" + Arrays.toString(blueSeeds) +
                ", score1=" + score1 +
                ", score2=" + score2 +
                '}';
    }
}