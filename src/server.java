import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class server {
    private static ArrayList<clientHandle> clients = new ArrayList<>();
    private static ExecutorService pool = Executors.newFixedThreadPool(4);
    public int findMin(int[] array){
        int min = array[0];
        for(int i=1 ;i< array.length; i++){
            if(array[i]< min){
                min = array[i];
            }
        }
        return min;
    }
    public int findMax(int[] array){
        int max = array[0];
        for(int i=1 ;i< array.length; i++){
            if(array[i] > max){
                max = array[i];
            }
        }
        return max;
    }
    public static void main(String args[]) {

        ServerSocket listener = null;
        String line;
        BufferedReader is;
        BufferedWriter os;
        Scanner sc;
        Socket socketOfServer = null;


        try {
            listener = new ServerSocket(9999);
        } catch (IOException e) {
            System.out.println(e);
            System.exit(1);
        }

        try {
            while(true){
                System.out.println("Server is waiting to accept user...");
                socketOfServer = listener.accept();
                System.out.println("Accept a client!");
                clientHandle clientThread = new clientHandle(socketOfServer);
                clients.add(clientThread);
                pool.execute(clientThread);
            }



            /*// Mở luồng vào ra trên Socket tại Server.
            os = new BufferedWriter(new OutputStreamWriter(socketOfServer.getOutputStream()));
            sc = new Scanner(socketOfServer.getInputStream());
            server sv = new server();

            // Nhận được dữ liệu từ người dùng và gửi lại trả lời.
            while (true) {
                String length = sc.nextLine();
                if (length.equals("QUIT")) {
                    break;
                }
                int lengths = Integer.parseInt(length);
                int array[] = new int[lengths];

                for(int i =0 ;i<lengths; i++){
                    String numStr = sc.nextLine();
                    array[i] = Integer.parseInt(numStr);
                }

                int max = sv.findMax(array);
                os.write("max : " + String.valueOf(max));
                os.newLine();
                os.flush();
                int min = sv.findMin(array);
                os.write("min : "  + String.valueOf(min));
                os.newLine();
                os.flush();
            }*/

        } catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
        }
        System.out.println("Sever stopped!");
    }

}
