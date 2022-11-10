package com.seekerscloud.pos.db;

import com.seekerscloud.pos.model.Customer;
import com.seekerscloud.pos.model.Product;
import com.seekerscloud.pos.model.User;

import java.util.ArrayList;

public class Database {
    public static ArrayList<User> userTable = new ArrayList<User>();
    public static ArrayList<Customer> customerTable = new ArrayList<Customer>();
    public static ArrayList<Product> productTable = new ArrayList<Product>();
}
