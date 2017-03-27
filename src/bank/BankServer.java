package bank;

import java.net.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.io.*;
/*
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;
import com.mongodb.MongoCredential;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.model.CreateCollectionOptions;
import com.mongodb.client.model.ValidationOptions;

import java.util.Arrays;
*/

public class BankServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        //MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
        
        try {
            serverSocket = new ServerSocket(4444);
        } catch (IOException ex) {
            System.out.println("Can't setup server on this port number. ");
        }

        Socket socket = null;
        InputStream in = null;
        OutputStream out = null;

        try {
            socket = serverSocket.accept();
        } catch (IOException ex) {
            System.out.println("Can't accept client connection. ");
        }

        try {
            in = socket.getInputStream();
        } catch (IOException ex) {
            System.out.println("Can't get socket input stream. ");
        }

        try {
            out = new FileOutputStream("/Users/ZhanYuhao/Desktop/Scalable-Bank-Server/temp.xml");
        } catch (FileNotFoundException ex) {
            System.out.println("File not found. ");
        }

        byte[] size = new byte[8];
        
        in.read(size,0,size.length);       
        for(byte t : size){     	
        	System.out.println(t);
        }
        long value = ByteBuffer.wrap(size).getLong();
        System.out.println(value);
        
        byte[] bytes = new byte[(int)value];        
        in.read(bytes,0,bytes.length);
        out.write(bytes, 0, bytes.length);

        out.close();
        in.close();
        socket.close();
        serverSocket.close();
    }
}

