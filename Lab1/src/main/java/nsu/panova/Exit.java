package nsu.panova;

import java.util.Scanner;

public class Exit implements Runnable{
    private final Scanner scanner;
    private final Thread sendThread;
    private final Thread receiveThread;
    private final Sender sender;
    private final Receiver receiver;

    public Exit(Thread sendThread, Thread receiveThread, Sender sender, Receiver receiver) {
        scanner = new Scanner(System.in);
        this.sendThread = sendThread;
        this.receiveThread = receiveThread;
        this.receiver = receiver;
        this.sender = sender;
    }

    @Override
    public void run() {
        while (true) {
            String word = scanner.nextLine();
            if ("exit".equals(word)) {
                receiver.exit();
                sender.exit();
                sendThread.interrupt();
                receiveThread.interrupt();
                System.exit(0);
            }
        }
    }
}
