/**
 * Classe qui represente un mouvement
 */
public class Mouvement{
    int position;
    boolean ColorRed;

    /**
     * Constructeur
     * @param position
     * @param Color
     */
    Mouvement(int position, Boolean Color) {
        this.position = position;
        this.ColorRed = Color;
    }


    /**
     * Constructeur
     * @param str
     */
    Mouvement(String str) {
        // il reçoit 5B 
        String[] part = str.split("(?<=\\D)(?=\\d)"); //    split :  5 et B 
        String[] part2 = part[0].split("(?=\\D)(?<=\\d)");  // Récupère 5 
        this.position = Integer.parseInt(part2[0]) - 1;//play // Converstion 5 en Int   
        this.ColorRed = part2[1].equals("R") || "r".equals(part2[1]); //color
    }

    /**
     * constructeur sans paramteres
     */
    Mouvement() {
        position = -1;
        ColorRed = false;
    }

    /**
     * Setter
     * @param move
     */
    void set(Mouvement move) {
        this.position = move.position;
        this.ColorRed = move.ColorRed;
    }

    /**
     * Fonctio pour identifier la couleur
     * @return la couleur
     */
    private String letter() {
        if (ColorRed) return "R";
        else return "B";
    }

    /**
     * ToString
     * @return un meilleur affichage d'un mouvement
     */
    @Override
    public String toString() {
        return "" + (position + 1) + letter();
    }
}