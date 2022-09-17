package nsu.panova;

import java.io.IOException;
import java.net.MulticastSocket;
import java.util.UUID;

public class Main {
    private static final int LOCAL_PORT = 8000;

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Wrong args for program");
            System.exit(0);
        }

        UUID uuid = UUID.randomUUID();

        try {
            MulticastSocket socket = new MulticastSocket(LOCAL_PORT);
            Sender sender = new Sender(socket, args[0], uuid);
            Receiver receiver = new Receiver(socket, args[0]);

            Thread sendThread = new Thread(sender);
            Thread receiveThread = new Thread(receiver);

            receiveThread.start();
            sendThread.start();

            Exit exit = new Exit(sendThread, receiveThread, sender, receiver);
            Thread exitThread = new Thread(exit);
            exitThread.start();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
