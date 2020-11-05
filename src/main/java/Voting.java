import java.util.ArrayList;
import java.util.List;

public class Voting {

    public static void main(String[] args) {
        List<String> voters = new ArrayList<>();

        // możesz dodać/usunąć dane testowe
        voters.add("Jan Kowalski");
        voters.add("Zigniew Siobro");
        voters.add("Zbyszek Stonoga");

        Voting voting = new Voting();
        voting.executeVoting(voters);
    }

    void executeVoting(List<String> voters) {
        // uzupełnij metodę metodę. Nie zmieniaj jej sygnatury
    }

}
