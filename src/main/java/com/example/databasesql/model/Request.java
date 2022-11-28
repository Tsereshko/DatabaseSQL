package com.example.databasesql.model;

import com.example.databasesql.client.RequestName;

import java.io.Serial;
import java.io.Serializable;

public class Request implements Serializable {
    RequestName code;
    int id;
    Furniture furniture;
    @Serial
    private static final long serialVersionUID = 2L;

    public RequestName getCode() {
        return code;
    }

    public int getId() {
        return id;
    }

    public Furniture getFurniture() {
        return furniture;
    }

    public Request(RequestName code, int id, Furniture furniture) {
        this.code = code;
        this.id = id;
        this.furniture = furniture;
    }
}
