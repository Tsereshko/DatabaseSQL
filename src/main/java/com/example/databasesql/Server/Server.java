package com.example.databasesql.Server;

import com.example.databasesql.model.Furniture;
import com.example.databasesql.model.Request;
import org.w3c.dom.ls.LSOutput;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;

import static java.lang.System.out;

public class Server extends Thread {
    private DatabaseHandler databaseHandler;
    private ServerSocket serverSocket;

    Server(int port) {
        try {
            serverSocket = new ServerSocket(port);
            databaseHandler = new DatabaseHandler();
        } catch (IOException e) {
            out.println("Ошибка создания сервера");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {

        while (true) {
            try {
                out.println("Ожидает");
                ArrayList<Furniture> list = databaseHandler.getList();

                Socket client = serverSocket.accept();
                out.println("подключено");

                ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(client.getInputStream());

                out.writeObject(list);
                out.flush();

                while (true) {
                    Request request = (Request) in.readObject();
                    switch (request.getCode()) {
                        case ADD: {
                            databaseHandler.furnitureInsert(request.getFurniture());

                            out.writeObject(databaseHandler.getList());
                            out.flush();
                            break;
                        }
                        case UPDATE: {
                            databaseHandler.furnitureUpdate(request.getFurniture(), request.getId());

                            out.writeObject(databaseHandler.getList());
                            out.flush();
                            break;
                        }
                        case DELETE: {
                            databaseHandler.furnitureDelete(request.getId());

                            out.writeObject(databaseHandler.getList());
                            out.flush();
                            break;
                        }
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                out.println("Клиент отключился");
            }
        }
    }

    public static void main(String[] args) {
        Server server = new Server(4456);
        server.start();
    }
}
