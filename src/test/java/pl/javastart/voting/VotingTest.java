package pl.javastart.voting;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import pl.javastart.voting.Voting;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

@Timeout(value = 5)
public class VotingTest {

    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final Voting voting = new Voting();

    @Test
    void shouldWorkForSimpleCase() {
        // given
        List<String> voters = Arrays.asList("A", "B");
        provideInput("z", "p");

        // when
        voting.executeVoting(voters, provideInput());

        // then
        assertThat(outContent.toString()).contains("Głosów za: 50");
        assertThat(outContent.toString()).contains("Głosów przeciw: 50");
        assertThat(outContent.toString()).contains("Wstrzymało się: 0");
    }

    @Test
    void shouldRoundTo2DecimalPoints() {
        // given
        List<String> voters = Arrays.asList("A", "B", "C");
        Scanner scanner = provideInput("z", "p", "w");

        // when
        voting.executeVoting(voters, scanner);

        // then
        assertThat(outContent.toString()).satisfiesAnyOf(
                output -> assertThat(output).contains("Głosów za: 33.33%"),
                output -> assertThat(output).contains("Głosów za: 33,33%")
        );

        assertThat(outContent.toString()).satisfiesAnyOf(
                output -> assertThat(output).contains("Głosów przeciw: 33.33%"),
                output -> assertThat(output).contains("Głosów przeciw: 33,33%")
        );

        assertThat(outContent.toString()).satisfiesAnyOf(
                output -> assertThat(output).contains("Wstrzymało się: 33.33%"),
                output -> assertThat(output).contains("Wstrzymało się: 33,33%")
        );
    }

    @Test
    void shouldWorkForNoVoters() {
        // given
        List<String> voters = Collections.emptyList();

        // when
        assertThatNoException().isThrownBy(() -> voting.executeVoting(voters, provideInput()));
    }

    @Test
    void shouldWorkForTwoVotingsSimpleCase() {
        // given
        List<String> voters = Arrays.asList("A", "B");
        provideInput("z", "p");

        // when
        voting.executeVoting(voters, provideInput());

        // then
        assertThat(outContent.toString()).contains("Głosów za: 50");
        assertThat(outContent.toString()).contains("Głosów przeciw: 50");
        assertThat(outContent.toString()).contains("Wstrzymało się: 0");

        // 2nd voting
        // given
        List<String> voters2 = Arrays.asList("A", "B");
        provideInput("z", "z");

        // when
        voting.executeVoting(voters2, provideInput());

        // then
        assertThat(outContent.toString()).contains("Głosów za: 100");
        assertThat(outContent.toString()).contains("Głosów przeciw: 0");
        assertThat(outContent.toString()).contains("Wstrzymało się: 0");
    }

    private Scanner provideInput(String... lines) {
        String input = String.join("\r\n", lines);

        return new Scanner(new ByteArrayInputStream(input.getBytes()));
    }

    @BeforeEach
    void init() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void cleanup() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

}