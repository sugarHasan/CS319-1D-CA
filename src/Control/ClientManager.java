package Control;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.URISyntaxException;
import java.net.UnknownHostException;

public abstract class ClientManager
{
    private Socket socket;
    private Thread msgListenerThread;
    private final String ip = "127.0.0.1";
    private final String TERMINATION = "###";

    public ClientManager( int serverPort) throws UnknownHostException, IOException
    {
        socket = new Socket( ip ,  serverPort);
        msgListenerThread = new Thread(() -> {
            try {
                listenForMessages();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        });
        msgListenerThread.start();
        System.out.println("CLIENT IS CREATED");
    }
    public void sendMessage( String msg)
    {
        byte[] data = msg.getBytes();
        try
        {
            socket.getOutputStream().write( data);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void listenForMessages() throws URISyntaxException {
        String message;
        InputStream stream;
        try
        {
            stream = socket.getInputStream();
        }
        catch (IOException e)
        {
            return;
        }

        while ( true)
        {
            message = "";
            while ( !message.contains( TERMINATION))
            {
                try
                {
                    message += (char) stream.read();
                }
                catch (IOException e)
                {
                    return;
                }
            }

            received( message.substring( 0, message.length() - 3));
        }
    }

    public abstract void received(String msg) throws URISyntaxException;

    public void close()
    {
        msgListenerThread.interrupt();
        try
        {
            socket.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}