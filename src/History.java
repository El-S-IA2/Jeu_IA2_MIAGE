import java.util.ArrayDeque;

/**
 * Creation d'une Variable globale qui sera utile et agira comme une historique pour notre  minimax fonction
 * elle permet d'assurer une complexité en espace linéaire par rapport à la profondeur du minimax
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