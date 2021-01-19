import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.io.IOException;

public class clientHandle implements Runnable{
    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    public clientHandle(Socket clientSocket) throws IOException {
        this.client = clientSocket;
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintWriter(client.getOutputStream(), true);
    }


    @Override
    public void run() {
        try {
            while (true) {
                String request = in.readLine();
                if(request.equals("quit")) break;
                int length = Integer.parseInt(request);
                int array[] = new int[length];

                for(int i =0 ;i<length; i++){
                    String numStr = in.readLine();
                    array[i] = Integer.parseInt(numStr);
                }
                server sv = new server();
                int max = sv.findMax(array);
                out.println(max);
                int min = sv.findMin(array);
                out.println(min);
            }

        } catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
        }finally {
            out.close();
            try{
                in.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
