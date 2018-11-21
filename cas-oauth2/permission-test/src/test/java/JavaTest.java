import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

public class JavaTest {
    @Test
    public void testArrays() {
        String[] t = "test.user.admin".split("\\.");

        for (int i = 0; i < t.length - 1; i++) {
            System.out.println(String.join(".", Arrays.copyOfRange(t, 0, i + 1)));
        }
    }
}
