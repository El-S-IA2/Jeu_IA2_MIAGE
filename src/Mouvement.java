public class Mouvement{
    int position;
    boolean PairFirst;

    Mouvement(int position, boolean PairFirst) {
        this.position = position;
        this.PairFirst = PairFirst;
    }

    Mouvement(String str) {
        String[] part = str.split("(?<=\\D)(?=\\d)");
        String[] part2 = part[0].split("(?=\\D)(?<=\\d)");
        this.position = Integer.parseInt(part2[0]) - 1;//play
        this.PairFirst =  part2[1].equals("R");//color
    }

    Mouvement() {
        position = -1;
        PairFirst = false;
    }

    void set(Mouvement move) {
        this.position = move.position;
        this.PairFirst = move.PairFirst;
    }

    private String letter() {
        if (PairFirst) return "R";
        else return "B";
    }

    @Override
    public String toString() {
        return "" + (position + 1) + letter();
    }
}