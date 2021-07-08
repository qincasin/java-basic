package socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.Socket;

/**
 * Created by qincasin on 2021/7/7.
 */
public class TcpClient {
    public static void main(String[] args) {
        try {
            Socket s = new Socket("127.0.0.1", 6666);
            OutputStream os = s.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);
            dos.writeUTF("hello ,server !");
            DataInputStream dis = new DataInputStream(s.getInputStream());
            System.out.println(dis.readUTF());

            dos.flush();
            dos.close();

            dis.close();
            s.close();

        }catch (ConnectException ex){
            ex.printStackTrace();
            System.out.println("connext fails !");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
