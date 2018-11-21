import org.junit.Test;

import java.util.HashMap;

public class TestMap {
    @Test
    public void testCompute() {
        HashMap<String, String> m = new HashMap<>();

        m.computeIfPresent("test", (k, v1) -> {
            return v1.concat(m.get(k));
        });
    }
}
