package nsu.panova;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.UUID;

public class Receiver extends Thread {
    MulticastSocket socket;
    InetAddress address;
    DatagramPacket receivePacket;

    private static final int LOCAL_PORT = 8000;
    private static final int PACKET_SIZE = 16;

    Receiver(String addressOfGroup) {
        try {
            address = InetAddress.getByName(addressOfGroup);
            socket = new MulticastSocket(LOCAL_PORT);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        receivePacket = new DatagramPacket(new byte[PACKET_SIZE], PACKET_SIZE);
    }

    @Override
    public void run() {
        EncryptPackage packetInfo = new EncryptPackage();
        KeepCopies keepCopies = new KeepCopies();

        try {
            socket.joinGroup(address);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        while (true) {
            try {
                socket.receive(receivePacket);
            } catch (IOException ignored) { }

            UUID receiveUuid = packetInfo.getIdFromByte(receivePacket.getData());
            InetAddress receiveAddress = receivePacket.getAddress();
            keepCopies.CheckMap(receiveAddress, receiveUuid);
        }
    }
}
