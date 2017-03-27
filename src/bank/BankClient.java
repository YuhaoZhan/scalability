//package bank;

import java.net.*;
import java.nio.ByteBuffer;
import java.io.*;

public class BankClient {
    public static void main(String[] args) throws IOException {
        Socket socket = null;
        String host = "127.0.0.1";

        socket = new Socket(host, 4444);

        File file = new File("/Users/ZhanYuhao/Desktop/Scalable-Bank-Server/test.xml");
        // Get the size of the file
        long length = file.length();
        System.out.println(length);
        byte[] len = ByteBuffer.allocate(8).putLong(length).array();
        byte[] bytes = new byte[(int)length];
        InputStream in = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(in);
        bis.read(bytes,0,bytes.length);
        OutputStream out = socket.getOutputStream();
        out.write(len,0,8);
        out.write(bytes,0,bytes.length);
        out.flush();
        bis.close();
        out.close();
        in.close();
        socket.close();
    }
}

