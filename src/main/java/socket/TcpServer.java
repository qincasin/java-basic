package socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by qincasin on 2021/7/7.
 */
public class TcpServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(6666);
            while (true) {
                Socket accept = serverSocket.accept();
                System.out.println(" a client connected !");
                DataInputStream dis = new DataInputStream(accept.getInputStream());
                DataOutputStream dos = new DataOutputStream(accept.getOutputStream());
                String str = null;
                if ((str = dis.readUTF()) != null) {
                    System.out.println(str);
                    System.out.println("from " + accept.getInetAddress() + ",port #" + accept.getPort());
                }
                dos.writeUTF("hello," + accept.getInetAddress() + ", port#" + accept.getPort());
                dis.close();
                dos.close();
                accept.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("server exection!");
        }
    }
}
