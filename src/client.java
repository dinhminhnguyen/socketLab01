import java.io.*;
import java.net.*;
import java.util.Scanner;

public class client {
    public static void main(String[] args) throws IOException {

        // Địa chỉ máy chủ.
        final String serverHost = "localhost";
        Socket socket = new Socket(serverHost, 9999);
        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        while(true){
            System.out.println("> ");
            String lengths = keyboard.readLine();
            if(lengths.equals("quit")) break;
            out.println(lengths);
            int length = Integer.parseInt(lengths);
            for(int i = 0 ; i< length; i++) {
                String num = keyboard.readLine();
                out.println(num);
            }

            String serverResponseMax = input.readLine();
            System.out.println("max  " + serverResponseMax);
            String serverResponseMin = input.readLine();
            System.out.println("min  " + serverResponseMin);
        }
    }
}
