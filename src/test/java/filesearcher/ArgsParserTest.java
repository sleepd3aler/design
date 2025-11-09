package filesearcher;

import java.util.Map;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ArgsParserTest {
    @Test
    void whenParseArgsSuccessful() {
        String[] args = {"-d=c:", "-n=*.?xt", "-t=mask", "-o=log.txt"};
        ArgsParser parser = new ArgsParser(args);
        Map<String, String> expected = Map.of(
                "-d", "c:", "-n", "*.?xt", "-t", "mask", "-o", "log.txt"
        );
        Map<String, String> result = parser.getParams();
        String expectedDir = "c:";
        String expectedName = "*.?xt";
        String expectedType = "mask";
        String expectedOut = "log.txt";
        assertThat(result).isEqualTo(expected);
        assertThat(parser.getDir()).isEqualTo(expectedDir);
        assertThat(parser.getName()).isEqualTo(expectedName);
        assertThat(parser.getTypeOfSearch()).isEqualTo(expectedType);
        assertThat(parser.getOutputPath()).isEqualTo(expectedOut);
    }

    @Test
    void whenNotEnoughArgsThenExceptionThrown() {
        String[] args = {"-d=c:", "-n=*.?xt", "-t=mask"};
        assertThatThrownBy(() -> new ArgsParser(args))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Not enough arguments to execute. Must contain: -d, -n, -t, -o");
    }

    @Test
    void whenArgsContainsIllegalDirKeyThenExceptionThrown() {
        String[] args = {"-c=c:", "-n=*.?xt", "-t=mask", "-o=log.txt"};
        assertThatThrownBy(() -> new ArgsParser(args))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Searching directory key \"-d\" is missing");
    }

    @Test
    void whenDirArgDoesNotContainingEqualsSymbolThenExceptionThrown() {
        String[] args = {"-dc:", "-n=*.?xt", "-t=mask", "-o=log.txt"};
        assertThatThrownBy(() -> new ArgsParser(args))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Argument must contain '=' at: " + args[0]);
    }

    @Test
    void whenDirArgDoesNotContainsValueThenExceptionThrown() {
        String[] args = {"-d=", "-n=*.?xt", "-t=mask", "-o=log.txt"};
        assertThatThrownBy(() -> new ArgsParser(args))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Missing value for: " + args[0]);
    }

    @Test
    void whenArgsContainsIllegalNameKeyThenExceptionThrown() {
        String[] args = {"-d=c:", "-c=*.?xt", "-t=mask", "-o=log.txt"};
        assertThatThrownBy(() -> new ArgsParser(args))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Searching file name or mask \"-n\" is missing");
    }

    @Test
    void whenNameArgDoesNotContainEqualsSymbolThenExceptionThrown() {
        String[] args = {"-d=c:", "-n*.?xt", "-t=mask", "-o=log.txt"};
        assertThatThrownBy(() -> new ArgsParser(args))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Argument must contain '=' at: " + args[1]);
    }

    @Test
    void whenNameArgDoesNotContainsValueThenExceptionThrown() {
        String[] args = {"-d=c:", "-n=", "-t=mask", "-o=log.txt"};
        assertThatThrownBy(() -> new ArgsParser(args))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Missing value for: " + args[1]);
    }

    @Test
    void whenArgsContainsIllegalTypeKeyThenExceptionThrown() {
        String[] args = {"-d=c:", "-n=*.?xt", "-=mask", "-o=log.txt"};
        assertThatThrownBy(() -> new ArgsParser(args))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Type of search \"-t\" is missing");
    }

    @Test
    void whenTypeArgDoesNotContainsEqualsSymbolThenExceptionThrown() {
        String[] args = {"-d=c:", "-n=*.?xt", "-tmask", "-o=log.txt"};
        assertThatThrownBy(() -> new ArgsParser(args))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Argument must contain '=' at: " + args[2]);
    }

    @Test
    void whenTypeArgDoesNotContainsValueThenExceptionThrown() {
        String[] args = {"-d=c:", "-n=*.txt", "-t=", "-o=log.txt"};
        assertThatThrownBy(() -> new ArgsParser(args))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Missing value for: " + args[2]);
    }

    @Test
    void whenArgsContainsIllegalOutputKeyThenExceptionThrown() {
        String[] args = {"-d=c:", "-n=*.?xt", "-t=mask", "-=log.txt"};
        assertThatThrownBy(() -> new ArgsParser(args))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("File to write \"-o\" is missing");
    }

    @Test
    void whenOutArgDoesNotContainsEqualsSymbolThenExceptionThrown() {
        String[] args = {"-d=c:", "-n=*.?xt", "-t=mask", "-olog.txt"};
        assertThatThrownBy(() -> new ArgsParser(args))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Argument must contain '=' at: " + args[3]);
    }

    @Test
    void whenOutArgDoesNotContainsValueThenExceptionThrown() {
        String[] args = {"-d=c:", "-n=*.txt", "-t=mask", "-o= "};
        assertThatThrownBy(() -> new ArgsParser(args))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Missing value for: " + args[3]);
    }

}
