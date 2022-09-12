package nsu.panova;

import java.util.UUID;

public class Main {
    static Sender sender;
    static Receiver receiver;

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Wrong args for program");
            System.exit(0);
        }

        UUID uuid = UUID.randomUUID();

        sender = new Sender(args[0], uuid);
        receiver = new Receiver(args[0]);
        receiver.start();
        sender.start();
    }
}
