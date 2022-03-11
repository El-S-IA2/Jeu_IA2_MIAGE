class MinMax {
    public int valeur;
    public Mouvement position;

    public MinMax(int valeur, Mouvement position) {
        this.valeur = valeur;
        this.position = position;
    }

    @Override
    public String toString() {
        return "MinimaxResult{" +
                "valeur=" + valeur +
                ", position=" + position +
                '}';
    }
}