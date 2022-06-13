import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * Tests for the {@link KdBaum} class.
 *
 * @author Jannis Kramer
 * @since 0.7
 */
public class Blatt07Test {

    /**
     * Easier Tuple creation.
     *
     * @param key   The first value.
     * @param value The second value.
     * @return The Tuple.
     */
    private Tuple pair(int key, int value) {
        return new Tuple(new int[]{key, value});
    }

    private final ArrayList<Tuple> initialTree = new ArrayList<>();

    /**
     * Provides the arguments for the tests.
     *
     * @return The arguments.
     */
    static Stream<Arguments> argumentProvider() {
        return Stream.of(
                Arguments.arguments(17, 42),
                Arguments.arguments(5, 97),
                Arguments.arguments(42, 17),
                Arguments.arguments(14, 100),
                Arguments.arguments(50, 15),
                Arguments.arguments(0, 0),
                Arguments.arguments(45, 92),
                Arguments.arguments(73, 63),
                Arguments.arguments(1, 29),
                Arguments.arguments(81, 35),
                Arguments.arguments(128, 53)
        );
    }

    /**
     * Sets up the initial tree. This is done before each test.
     */
    @BeforeEach
    public void setup() {
        initialTree.clear();
        initialTree.add(pair(17, 42));
        initialTree.add(pair(5, 97));
        initialTree.add(pair(42, 17));
        initialTree.add(pair(14, 22));
        initialTree.add(null);
        initialTree.add(null);
        initialTree.add(pair(56, 84));
        initialTree.add(null);
        initialTree.add(null);
        initialTree.add(null);
        initialTree.add(null);
        initialTree.add(null);
        initialTree.add(null);
        initialTree.add(null);
        initialTree.add(pair(69, 20));
    }

    /**
     * Tests whether the {@link KdBaum#einfuegen(Tuple)} method throws an IllegalArgumentException if the given tuple already exists.
     *
     * @param key   The key of the tuple.
     * @param value The value of the tuple.
     */
    @ParameterizedTest(name = "#{index} - Test insert with Tuple ({0}, {1})")
    @MethodSource("argumentProvider")
    @DisplayName("Test KdBaum#einfuegen(tuple), expected throws IllegalArgumentException if tuple already exists")
    public void testEinfuegenThrows(int key, int value) {
        KdBaum tree = new KdBaum();
        Tuple tuple = pair(key, value);
        if (tree.finde(tuple)) {
            Assertions.assertThrows(Exception.class, () -> tree.einfuegen(tuple));
        } else {
            Assertions.assertDoesNotThrow(() -> tree.einfuegen(tuple));
        }
    }

    /**
     * Tests whether the {@link KdBaum#finde(Tuple)} and {@link KdBaum#einfuegen(Tuple)} methods work as expected.
     *
     * @param key   The key of the tuple.
     * @param value The value of the tuple.
     */
    @ParameterizedTest(name = "#{index} - Test finde with Tuple ({0}, {1})")
    @MethodSource("argumentProvider")
    @DisplayName("Test KdBaum#finde(tuple), expected true if tuple exists")
    public void testFindeAndEinfuegen(int key, int value) throws Exception {
        KdBaum tree = new KdBaum();
        Tuple tuple = pair(key, value);
        if (initialTree.stream().anyMatch(t -> t != null && t.get(0) == key && t.get(1) == value)) {
            Assertions.assertTrue(tree.finde(tuple));
        } else {
            Assertions.assertFalse(tree.finde(tuple));
            tree.einfuegen(tuple);
            Assertions.assertTrue(tree.finde(tuple));
        }
    }
}
