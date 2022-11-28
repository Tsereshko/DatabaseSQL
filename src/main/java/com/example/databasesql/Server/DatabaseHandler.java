package com.example.databasesql.Server;

import com.example.databasesql.model.Furniture;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseHandler {
    Connection connection;

    ArrayList list = new ArrayList<Furniture>();
    public DatabaseHandler(){
        try {
            String connectionStr = "jdbc:mysql://localhost:3306/furniture";
            connection = DriverManager.getConnection(connectionStr, "root", "root");
            requestList();
        } catch (SQLException e) {
            System.out.println("Не удалось подключиться к базе данных");
            throw new RuntimeException(e);
        }
    }

    public ArrayList getList() {
        requestList();
        return list;
    }

    public void requestList() {
        try {

            list = new ArrayList<Furniture>();
            System.out.println("Считывание из базы данных");
            String select = "SELECT *  FROM furniture.furniture;";
            Statement st = connection.createStatement();
            ResultSet resultSet = st.executeQuery(select);

            while(resultSet.next()){
                System.out.println();
                list.add(new Furniture(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getDouble(3),
                        resultSet.getString(4),
                        resultSet.getDouble(5),
                        resultSet.getDouble(6),
                        resultSet.getDouble(7)
                ));
            }
            for(int i = 0; i< list.size(); i++){
                System.out.println(list.get(i));
            }
        } catch (SQLException e) {
            System.out.println("Неизвестный запрос");
            throw new RuntimeException(e);
        }
    }
    public void furnitureInsert(Furniture furniture){
        try {
            String insert = "INSERT INTO furniture.furniture (name, price, material, weight, width, length) VALUE (?, ?, ?, ?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(insert);

            preparedStatement.setString(1, furniture.getName());
            preparedStatement.setDouble(2, furniture.getPrice());
            preparedStatement.setString(3, furniture.getMaterial());
            preparedStatement.setDouble(4, furniture.getWeight());
            preparedStatement.setDouble(5, furniture.getWidth());
            preparedStatement.setDouble(6, furniture.getLength());

            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("Не удалось выполнить команду");
            throw new RuntimeException(e);
        }
    }
    public void furnitureUpdate(Furniture furniture, int id){
        System.out.println("\nid: " + id);
        try {
            String update = "UPDATE furniture.furniture " +
                "SET name = \"" + furniture.getName() + "\", " +
                "price = " + furniture.getPrice() + ", " +
                "material = \"" + furniture.getMaterial() + "\", " +
                "weight = " + furniture.getWeight() + ", " +
                "width = " + furniture.getWidth() + ", " +
                "length = " + furniture.getLength() +
                " where idfurniture = " + id + ";";

            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("Не удалось выполнить команду");
            throw new RuntimeException(e);
        }
    }
    public void furnitureDelete(int id){
        try{
            String delete = "delete from furniture.furniture where idfurniture = " + id + ";";

            PreparedStatement preparedStatement = connection.prepareStatement(delete);
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("Не удалось выполнить команду");
            throw new RuntimeException(e);
        }
    }
}
