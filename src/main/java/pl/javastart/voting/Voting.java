package pl.javastart.voting;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Voting {

    public static void main(String[] args) {
        List<String> voters = new ArrayList<>();
        //Pytanie do tego zadania:
        //Nie za bardzo rozumiem dlaczego w ponizszej metodzie jest tak wazna kolejnosc ifow
        //jezeli zamienie miejscami pierwszego ifa z trzecim to dlaczego dostaje blad skoro wtedy warunek w ifie nie
        //powinien sie wykonac i program powinien wskoczyc do kolejnego ifa ?
//        public void printResults() {
//            int counterFor = 0;
//            int counterAgainst = 0;
//            int counterHold = 0;
//            for (Vote vote : votes) {
//                if (vote.getVote() == null) {
//                    counterHold++;
//                } else if (!vote.getVote()) {
//                    counterAgainst++;
//                } else if (vote.getVote()) {
//                    counterFor++;
//                }
//            }

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
            switch (decision) {
                case "z" -> votingResult.add(new Vote(voters.get(i), true));
                case "p" -> votingResult.add(new Vote(voters.get(i), false));
                case "w" -> votingResult.add(new Vote(voters.get(i), null));
                default -> {
                    System.out.println("Głos nieważny. Proszę ponownie zagłosować.");
                    i--;
                }
            }
        }
        return votingResult;
    }
}
