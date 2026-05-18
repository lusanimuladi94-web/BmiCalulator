import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

public class UserStore {
    private final Map<String, String> users = new ConcurrentHashMap<>();
    // NOTE: For demo only. In real apps, store hashed passwords in a database.

    public boolean register(String username, String password) {
        if (username == null || username.isBlank()) return false;
        if (password == null || password.length() < 4) return false;
        return users.putIfAbsent(username.trim(), password) == null;
    }

    public boolean authenticate(String username, String password) {
        if (username == null || password == null) return false;
        String saved = users.get(username.trim());
        return saved != null && saved.equals(password);
    }
}
