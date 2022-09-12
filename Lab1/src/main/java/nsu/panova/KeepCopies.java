package nsu.panova;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class KeepCopies {
    Map<UUID, InetAddress> copies = new HashMap<>();
    Map<UUID, Long> timeLastAnswer = new HashMap<>();

    KeepCopies() {
        checkAlive isAlive = new checkAlive(copies, timeLastAnswer);
        isAlive.checkAliveCopies();
    }

    public void CheckMap(InetAddress address, UUID uuid) {
        if (!copies.containsKey(uuid)) {
            copies.put(uuid, address);
            System.out.println("The number of copies has changed");
            for (InetAddress value : copies.values()) {
                System.out.println(value);
            }

            timeLastAnswer.put(uuid, System.currentTimeMillis());
        }
        else {
            timeLastAnswer.put(uuid, System.currentTimeMillis());
        }

    }
}
