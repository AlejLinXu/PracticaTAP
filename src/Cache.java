import java.util.HashMap;
import java.util.Map;

public class Cache {

    private static final Map<String, Object> cache = new HashMap<>();

    public Object get(String id) {
        return cache.get(id);
    }

    public void put(String id, Object resultado) {
        cache.put(id, resultado);
    }
}
