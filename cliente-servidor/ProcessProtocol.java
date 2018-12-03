import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;

public class ProcessProtocol implements Runnable{

    public static final String  VERSION = "0.0.1";

    private boolean active;
    private Socket socket;

    public ProcessProtocol(Socket socket){
        this.socket = socket;
        active = true;
    }

    public void run(){
        while(socket != null && active){
            try{
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String line = in.readLine();
                if(line != null){
                    switch(line){
                        case "CLOSE":
                            out.println("CLOSE CONNECTION");
                            active = false;
                            break;
                        case "HELLO":
                            out.println("WELCOME NEW CLIENT");
                            break;
                        case "VERSION":
                            out.println(VERSION);
                            break;
                        default:
                            out.println("WITHOUT UNDERSTANDING");
                            break;
                    }
                }else{
                    active = false;
                }
            }catch(IOException ioex){
                ioex.printStackTrace();
            }
        }
        closeSocket();
    }

    private void closeSocket(){
        if(socket !=null){
            try{
                socket.close();
            }catch(IOException ioex){
                ioex.printStackTrace();
            }
        }
    }

}
