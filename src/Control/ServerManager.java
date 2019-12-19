package Control;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class ServerManager {
    private final String TERMINATION = "###";
    private ServerSocket server;
    private ArrayList<Socket> sockets;
    private boolean isBlocked , stopListening;
    private ArrayList<Thread> messageListeners;
    private Thread connectionListener;
    private int noOfPlayer;
    private final String ip = "192.168.43.105";


    public ServerManager(int port) throws IOException{
        //InetAddress address = new InetAddress(ip);
        server = new ServerSocket(port ,4 , InetAddress.getByName(ip));
        messageListeners = new ArrayList<Thread>();
        isBlocked = false;
        noOfPlayer = 1;
        connectionListener = new Thread(new Runnable() {
            @Override
            public void run() {
                listenConnection();
            }
        });
        connectionListener.start();

    }

    private void listenConnection() {
        while ( !stopListening) {
            Socket socket;
            try {
                socket = server.accept();
            } catch (IOException e) {
                break;
            }

            if (stopListening)
                return;
            // Wait until the work on sockets finishes
            while (isBlocked) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            isBlocked = true;
            sockets.add(socket);
            isBlocked = false;
            connectionEstablished(socket);
            messageListeners.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    listenForMessages(socket);
                }
            }));
            messageListeners.get(messageListeners.size() - 1).start();
        }
    }

    private void listenForMessages( Socket socket)
    {
        String message = "";
        boolean isAlive = true;

        while ( isAlive)
        {
            try
            {
                message += (char) socket.getInputStream().read();
            }
            catch (IOException e)
            {
                isAlive = false;
            }
        }
        while ( isBlocked)
        {
            try
            {
                Thread.sleep( 100);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
        isBlocked = true;
        sockets.remove( socket);
        isBlocked = false;
    }
    private void received(String substring, Socket socket) {
    }
    private void connectionEstablished(Socket socket) {
        noOfPlayer++;
        System.out.println("NO of players in the game: " + noOfPlayer);
    }
    public void sendMessageToAll( String msg)
    {
        byte[] data = msg.getBytes();
        for ( Socket socket : sockets)
        {
            try
            {
                socket.getOutputStream().write( data);
            }
            catch (IOException e)
            {

            }
        }

        while ( isBlocked)
        {
            try
            {
                Thread.sleep( 100);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }
    public void sendMessage( String msg, InetAddress address)
    {
        byte[] data = msg.getBytes();
        for ( Socket socket : sockets)
        {
            if ( address.equals( socket.getInetAddress()))
            {
                try
                {
                    socket.getOutputStream().write( data);
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        while ( isBlocked)
        {
            try
            {
                Thread.sleep( 100);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }
    public void stopListeningForConnections()
    {
        stopListening = true;
        connectionListener.interrupt();
    }
}
