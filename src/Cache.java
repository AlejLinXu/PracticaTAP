import java.util.HashMap;
import java.util.Map;

public class Cache {

    private static Map<String, Object> cache = new HashMap<>();

    public static Object get(String id) {
        return cache.get(id);
    }

    public static void put(String id, Object resultado) {
        cache.put(id, resultado);
    }
}
