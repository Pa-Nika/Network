package nsu.panova;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.UUID;

public class Sender extends Thread {
    MulticastSocket socket;
    InetAddress address;
    DatagramPacket sendPacket;
    UUID uuid;

    EncryptPackage packetInfo = new EncryptPackage();

    private static final int LOCAL_PORT = 8000;

    Sender (String addressOfGroup, UUID uuid) {
        try {
            address = InetAddress.getByName(addressOfGroup);
            socket = new MulticastSocket(LOCAL_PORT);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        this.uuid = uuid;
        byte[] packetByte = packetInfo.getIdAsByte(uuid);
        sendPacket = new DatagramPacket(packetByte, packetByte.length, address, LOCAL_PORT);
    }

    @Override
    public void run(){

        while(true)  {
            try {
                socket.send(sendPacket);
            } catch (IOException ignored) { }
        }
    }
}
