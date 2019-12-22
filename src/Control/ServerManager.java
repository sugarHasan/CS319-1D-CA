package Control;

import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URISyntaxException;
import java.util.ArrayList;

import static View.Main.*;


public abstract class ServerManager {
    private final String TERMINATION = "###";
    private ServerSocket server;
    private ArrayList<Socket> sockets;
    private boolean isBlocked , stopListening;
    private ArrayList<Thread> messageListeners;
    private Thread connectionListener;
    private int noOfPlayer;
    private final String ip = "127.0.0.1";


    public ServerManager(int port) throws IOException{
        //InetAddress address = new InetAddress(ip);
        server = new ServerSocket(port ,4 , InetAddress.getByName(ip));
        messageListeners = new ArrayList<Thread>();
        sockets = new ArrayList<Socket>();
        isBlocked = false;
        noOfPlayer = 1;
        connectionListener = new Thread(new Runnable() {
            @Override
            public void run() {
                listenConnection();
            }
        });
        connectionListener.start();
        System.out.println("SERVER IS CREATED");

    }


    private void listenConnection() {
        while ( !stopListening) {
            Socket socket;
            try {
                socket = server.accept();
            } catch (IOException e) {
                break;
            }
            if(socket == null)  break;

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
            connectionEstablished();
            messageListeners.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        listenForMessages(socket);
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                }
            }));
            messageListeners.get(messageListeners.size() - 1).start();
        }
    }

    public abstract void received(String message) throws URISyntaxException;

    private void listenForMessages( Socket socket) throws URISyntaxException {
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
            if ( message.contains(TERMINATION))
            {
                received( message.substring( 0, message.length() - 3));
                sendMessageToAll(message);
                message = "";
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

    public abstract void connectionEstablished();
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
