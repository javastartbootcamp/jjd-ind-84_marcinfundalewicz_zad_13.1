package pl.javastart.voting;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Voting {

    public static void main(String[] args) {
        List<String> voters = new ArrayList<>();

        // możesz dowolnie dodawać / usuwać dane testowe
        voters.add("Jan Kowalski");
        voters.add("Zigniew Siobro");
        voters.add("Zbyszek Stonoga");

        Voting voting = new Voting();

        VotingResult votingResult = voting.executeVoting(voters, new Scanner(System.in));

        votingResult.printResults();
        System.out.println();
        votingResult.printVoteForVoter("Jan Kowalski");
        votingResult.printVoteForVoter("Zigniew Siobro");
        votingResult.printVoteForVoter("Zbyszek Stonoga");
    }

    /**
     * Uzupełnij metodę metodę, ale nie zmieniaj jej sygnatury! (typu tego, co przyjmuje i zwraca).
     * do wczytywania danych od użytkownika użyj scannera z parametru
     * Metoda powinna pobrać głos dla każdego przekazanego głosującego i zapisać wyniki głosowania do VotingResult
     */
    VotingResult executeVoting(List<String> voters, Scanner scanner) {
        VotingResult votingResult = new VotingResult();
        for (int i = 0; i < voters.size(); i++) {
            System.out.println("Jak glosuje " + voters.get(i) + " ? (z - za, p - przeciw, w - wstrzymanie sie)");
            String decision = scanner.nextLine();
            if (decision.equals("z")) {
                votingResult.add(new Vote(voters.get(i), true));
            } else if (decision.equals("p")) {
                votingResult.add(new Vote(voters.get(i), false));
            } else if (decision.equals("w")) {
                votingResult.add(new Vote(voters.get(i), null));
            } else {
                System.out.println("Głos nieważny. Proszę ponownie zagłosować.");
                i--;
            }
        }
        return votingResult;
    }
}
