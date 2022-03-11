import java.util.ArrayDeque;

/**
 * Variable globale qui sert d'historique pour le minimax
 * permet d'assurer une complexité en espace linéaire par rapport à la profondeur du minimax
 */
class History {
    private static ArrayDeque<Jeu> history = new ArrayDeque<>();

    public static void save(Jeu node) {
        history.push(node);
    }

    public static Jeu restore() {
        return history.pop();
    }

    private History(){}
}