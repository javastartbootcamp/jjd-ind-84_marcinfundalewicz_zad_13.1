package pl.javastart.voting;

import java.util.ArrayList;
import java.util.List;

/**
 * Możesz dodać kolejne metody i pola do klasy. Nie zmieniaj istniejących metod.
 */
public class VotingResult {
    List<Vote> votes;

    public VotingResult() {
        votes = new ArrayList<>();
    }

    public void add(Vote vote) {
        votes.add(vote);
    }

    /**
     * Metoda powinna drukować wyniki głosowania w takiej postaci:
     * Głosów za: 56.53%
     * Głosów przeciw: 35.00%
     * Wstrzymało się: 8.47%
     */

    public void printResults() {
        int counterFor = 0;
        int counterAgainst = 0;
        int counterHold = 0;
        for (int i = 0; i < votes.size(); i++) {
            if (votes.get(i).getVote()) {
                counterFor++;
            } else if (!votes.get(i).getVote()) {
                counterAgainst++;
            } else if (votes.get(i).getVote() == null) {
                counterHold++;
            }
        }
        System.out.printf("Wstrzymało się: %.2f %% \n", ((double) (counterHold) / votes.size() * 100));
        System.out.printf("Głosów za: %.2f %% \n", ((double) (counterFor) / votes.size() * 100));
        System.out.printf("Głosów przeciw: %.2f %% \n", ((double) (counterAgainst) / votes.size() * 100));
    }

    /**
     * Metoda przyjmuje imię i nazwisko głosującego, np "Zigniew Siobro".
     * Uzupełnij tę metodę, żeby drukowała informację w formie:
     * Zigniew Siobro: ZA
     * Nie zmieniaj sygnatury tej metody!
     */
    public void printVoteForVoter(String voterName) {
        for (int i = 0; i < votes.size(); i++) {
            if (votes.get(i).getVoter().equals(voterName)) {
                if (votes.get(i).getVote()) {
                    System.out.println(voterName + ": ZA");
                }
                if (!votes.get(i).getVote()) {
                    System.out.println(voterName + ": PRZECIW");
                }
                if (votes.get(i).getVote() == null) {
                    System.out.println(voterName + ": WSTRZYMAŁ SIĘ");
                }
            }
        }
    }
}
