package cash;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Optional;



@ThreadSafe
public class AccountStorage {
    @GuardedBy("this")
    private final HashMap<Integer, Account> accounts = new HashMap<>();

    public synchronized boolean add(Account account) {
        return false;
    }

    public synchronized boolean update(Account account) {
        return false;
    }

    public synchronized void delete(int id) {

    }

    public synchronized Optional<Account> getById(int id) {
        return Optional.empty();
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        return false;
    }

}
