import java.net.ConnectException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Scanner;

public class Main {
    static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/mydb?serverTimezone=Europe/Kiev";
    static final String DB_USER = "root";
    static final String DB_PASSWORD = "MysqlPass9";
    private static Connection connection;

    public static void main(String[] argv) {
        try {
            connection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            ClientsDao clientsDao = new ClientsDao();

            Scanner sc = new Scanner(System.in);

            while (true) {
                System.out.println("1: create client");
                System.out.println("2: delete client");
                System.out.println("3: change client");
                System.out.println("4: view clients");
                System.out.print("> ");

                String s = sc.nextLine();
                Clients client;

                if ("1".equals(s)) {
                    client = new Clients();

                    System.out.print("Enter name: ");
                    client.setName(sc.next());
                    System.out.print("Enter age: ");
                    client.setAge(sc.nextInt());

                    if (clientsDao.create(client))
                        System.out.println("create success");
                    else
                        System.out.println("create failed");
                } else if ("2".equals(s)) {
                    client = new Clients();

                    System.out.print("Enter id: ");
                    int id = sc.nextInt();

                    if (clientsDao.delete(id))
                        System.out.println("delete success");
                    else
                        System.out.println("delete failed");
                } else if ("3".equals(s)) {
                    client = new Clients();

                    System.out.print("Enter id: ");
                    client.setId(sc.nextInt());
                    System.out.print("Enter name: ");
                    client.setName(sc.next());
                    System.out.print("Enter age: ");
                    client.setAge(sc.nextInt());

                    if (clientsDao.update(client))
                        System.out.println("update success");
                    else
                        System.out.println("update failed");
                } else if ("4".equals(s)) {
                    clientsDao.setConnection(connection);

                    Collection<Clients> collection = clientsDao.findAll();

                    for (Clients c : collection) {
                        System.out.println(c.toString());
                    }
                } else {
                    return;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
