import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * Tests for the {@link Regular} class.
 *
 * @author Jannis Kramer
 * @since 0.8
 */
public class Blatt08Test {

    Pattern pattern = Pattern.compile("(ab)*c?d");

    /**
     * Provides the arguments for the tests.
     * Returns a stream of arguments that contains every possible combination of a, b, c, d. for lengths up to 6.
     *
     * @return The arguments.
     */
    static Stream<Arguments> argumentProvider() {
        Stream.Builder<Arguments> builder = Stream.builder();
        int letters = 4;
        int maxLength = 6;
        for (int length = 1; length <= maxLength; length++) {
            final int combinations = (int) Math.pow(letters, length);
            StringBuilder sb = new StringBuilder(length);
            for (int i = 0; i < combinations; i++) {
                sb.setLength(0);
                for (int j = 0, i2 = i; j < length; j++, i2 /= letters)
                    sb.insert(0, (char) ('a' + i2 % letters));
                builder.add(Arguments.of(sb.toString()));
            }
        }
        return builder.build();
    }

    /**
     * Tests the {@link Regular#suche(char[])} method.
     * Expects the method to return the same index as the following code:
     * <pre>
     *     Pattern pattern = Pattern.compile("(ab)*c?d");
     *     Matcher matcher = pattern.matcher(text);
     *     int index = matcher.find() ? matcher.start() : -1;
     * </pre>
     * or throw an exception if no match is found.
     *
     * @param text The text to search in.
     */
    @ParameterizedTest(name = "test suche({0})")
    @MethodSource("argumentProvider")
    public void testSuche(String text) throws Exception {
        Regular regular = new Regular();
        Matcher matcher = pattern.matcher(text);
        int index = matcher.find() ? matcher.start() : -1;
        if (index == -1) {
            Assertions.assertThrows(Exception.class, () -> regular.suche(text.toCharArray()));
        } else {
            Assertions.assertEquals(index, regular.suche(text.toCharArray()));
        }
    }
}
