
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;

public class Servidor implements Runnable{


    public static String ADDRESS = "0.0.0.0";
    private ServerSocket serverSocket;
    private boolean active;

    public Servidor(int port) throws IOException{
        this.serverSocket = new ServerSocket(port);
        active = true;
    }

    public void run(){
        while(active){
            if(serverSocket.isBound()){
                try{
                    Socket socket = serverSocket.accept();
                    if(socket != null){
                        (new Thread(new ProcessProtocol(socket))).start();
                    }
                }catch(IOException ioex){
                    ioex.printStackTrace();
                }
            }
            pauseServer(100);
        }
    }

    public void pauseServer(int milis){
        try{
            Thread.sleep(milis);
        }catch(InterruptedException iex){
            active = false;
        }
    }

    public static void main(String[] pps){
        try{
            Servidor servidor = new Servidor(25001);
            (new Thread(servidor)).start();
        }catch(IOException iex){
            iex.printStackTrace();
        }

    }
}
