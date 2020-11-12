import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
        voting.executeVoting(voters);

        // then
        assertThat(outContent.toString()).contains("Głosów za: 50");
        assertThat(outContent.toString()).contains("Głosów przeciw: 50");
        assertThat(outContent.toString()).contains("Wstrzymało się: 0");
    }

    @Test
    void shouldRoundTo2DecimalPoints() {
        // given
        List<String> voters = Arrays.asList("A", "B", "C");
        provideInput("z", "p", "w");

        // when
        voting.executeVoting(voters);

        // then
        assertThat(outContent.toString()).contains("Głosów za: 33.33%");
        assertThat(outContent.toString()).contains("Głosów przeciw: 33.33%");
        assertThat(outContent.toString()).contains("Wstrzymało się: 33.33%");
    }

    @Test
    void shouldWorkForNoVoters() {
        // given
        List<String> voters = Collections.emptyList();

        // when
        assertThatNoException().isThrownBy(() -> voting.executeVoting(voters));
    }

    private void provideInput(String... lines) {
        String input = String.join("\r\n", lines);

        ByteArrayInputStream testIn = new ByteArrayInputStream(input.getBytes());
        System.setIn(testIn);
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