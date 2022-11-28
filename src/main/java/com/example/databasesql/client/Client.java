package com.example.databasesql.client;

import com.example.databasesql.model.Furniture;
import com.example.databasesql.model.Request;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

import static com.example.databasesql.client.RequestName.*;

public class Client extends Thread{
    private int port;
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    public Client(int port){
        try {
            socket = new Socket("127.0.0.1", port);
            out = new ObjectOutputStream(new DataOutputStream(socket.getOutputStream()));
            in = new ObjectInputStream(new DataInputStream(socket.getInputStream()));
        } catch (IOException e) {
            System.out.println("нету подключения");
            throw new RuntimeException(e);
        }
    }
    public ObservableList<Furniture> getList(){
        try {
            return FXCollections.observableArrayList((ArrayList<Furniture>) in.readObject());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void sendRequest(RequestName name, Furniture furniture){
        try {
            switch (name){
                case ADD: {
                    out.writeObject(new Request(ADD, 0, furniture));
                    out.flush();
                    break;
                }
                case UPDATE:{
                    out.writeObject(new Request(UPDATE, furniture.getId(), furniture));
                    out.flush();
                    break;
                }
                case DELETE:{
                    out.writeObject(new Request(DELETE, furniture.getId(), null));
                    out.flush();
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error");
        }
    }
}
