import java.util.HashMap;
import java.util.Map;

public class Cache {

    private static final Map<String, Object> cache = new HashMap<>();

    /**
     * Returns the object associated with the id or null if it does not exist.
     * @param id
     * @return Object
     */
    public Object get(String id) {
        return cache.get(id);
    }

    /**
     * Stores the object in the cache.
     * @param id
     * @param resultado
     */
    public void put(String id, Object resultado) {
        cache.put(id, resultado);
    }
}
