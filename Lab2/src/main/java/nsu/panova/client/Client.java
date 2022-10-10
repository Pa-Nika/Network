package nsu.panova.client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {
    private static final Logger log = Logger.getLogger(Client.class.getName());

    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            log.log(Level.SEVERE, "Incorrect count of args");
            System.exit(1);
        }

        Socket socket = null;
        try {
            int portNumber = Integer.parseInt(args[2]);
            InetAddress IP = InetAddress.getByName(args[1]);
            socket = new Socket(IP, portNumber);
            log.log(Level.INFO, "Create client socket" + socket.getInetAddress());

            SenderFile senderFile = new SenderFile(socket, args[0]);
            senderFile.workWithProtocol();
            socket.close();
        } catch (IOException e) {
            log.log(Level.SEVERE, "Can't work with client socket");
            assert socket != null;
            socket.close();

            System.out.println(e.getMessage());
        }
    }
}
