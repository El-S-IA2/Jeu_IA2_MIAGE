public class Mouvement{
    int position;
    boolean ColorRed;

    Mouvement(int position, Boolean Color) {
        this.position = position;
        this.ColorRed = Color;
    }




    Mouvement(String str) {
        // il reçoit 5B 
        String[] part = str.split("(?<=\\D)(?=\\d)"); //    split :  5 et B 
        String[] part2 = part[0].split("(?=\\D)(?<=\\d)");  // Récupère 5 
        this.position = Integer.parseInt(part2[0]) - 1;//play // Converstion 5 en Int   
        this.ColorRed =  part2[1].equals("R") ; //color  
    }

    Mouvement() {
        position = -1;
        ColorRed = false;
    }

    void set(Mouvement move) {
        this.position = move.position;
        this.ColorRed = move.ColorRed;
    }

    
    private String letter() {
        if (ColorRed) return "R";
        else return "B";
    }
    

    @Override
    public String toString() {
        return "" + (position + 1) + letter();
    }
}