public interface Action <T, R> {
    R run (T t) throws Exception;
}
