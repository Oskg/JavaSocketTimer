package server;

import javax.swing.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;


import java.util.*;

public class Server {
  
  //-------------------------PostgreSQL------------------------------
  static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/postgres";
  static final String USER = "postgres";
  static final String PASS = "1";
  static Statement statement = null;
  static Connection connection = null;
  
  ThreadClientHandler[] myThread = new ThreadClientHandler[25];

  int count = 0;  
  ServerSocket server = null;
  Thread thread = null;
  Thread thread_2;
  Thread[] clients_th = new Thread[25];

  
  static String events_data = new String();
  
  static boolean access = true;

  public static boolean WriteDataToDatabase(String insertTableSQL)
  {
    System.out.println("Добавление данных в базу...");
    boolean res = false;
    if (access)
    {
      access = false;
      try {
        System.out.println(insertTableSQL);
        statement.executeUpdate(insertTableSQL);
        System.out.println("Успешное добавление данных в базу");
        res = true;
      } catch (SQLException ex) {
        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        System.out.println("Проблема добавления данных в базу");
        res = false;
      }
      
      access = true;
    }
    return res;
  }
  
  public static boolean GetAccess()
  {
    return access;
  }
  
  public  void GetDataFromDatabase()
  {
    try{
      String selectTableSQL = "SELECT name_event, time_event from events";
      ResultSet rs = statement.executeQuery(selectTableSQL);
      
      events_data = "";
      while(rs.next())
      {
        events_data += rs.getString("name_event") + "/" + rs.getString("time_event") + "#";


      }      
    }catch(SQLException e){
      System.out.println(e.getMessage());
    }
    
    System.out.println("DATA: " + events_data);
  }
  
  public void ConnectionDB()
  {
    try {
      Class.forName("org.postgresql.Driver");
      connection = DriverManager.getConnection(DB_URL, USER, PASS);
      statement = connection.createStatement();
	} catch (ClassNotFoundException e) {
      return;
	} catch (SQLException e) {
      System.out.println("Ошибка подключения");
      return;
	}
    
    if (connection != null)
    {
      System.out.println("Успешное подключение к базе данных");
    }
  }
  
  public Server(InetAddress ip, int port) {
    try {
      server = new ServerSocket(port, 0, ip);   //Создание сокета
      System.out.println("Сервер запущен");
      
      ConnectionDB(); //Подключение к базе данных
      GetDataFromDatabase(); //Получение данных из БД
      
      Runnable ClientHandler = () -> {
        try {
          while (!server.isClosed()) {
            Socket client = server.accept();    
            myThread[count] = new ThreadClientHandler(client);
            clients_th[count] = new Thread(myThread[count],"ThreadClientHandler"); 
            clients_th[count].start();
            
            myThread[count].UpdateDataEvent(events_data);
            
            System.out.println("Клиент " + count + " подключен");

            count++;
           }
          }catch (IOException ex) {
            System.out.println("Сокет закрыт!");
            thread.interrupt();
          }
        };
        
        thread = new Thread(ClientHandler);
        thread.start();        
        
    } catch (IOException ex) {
      System.out.println("Не удалось запустить сервер");
    }
    
    
    Runnable update_events = () -> {
       try {
          while(true)
          {
            GetDataFromDatabase(); //Получение данных из БД
        
            for(int i =0; i < count; i++)
            {
              myThread[i].UpdateDataEvent(events_data);
            }
              ServerForm.ta_events.setText("");
              String[] split_events = events_data.split("#");
              for (int i = 0; i < split_events.length; i++) {
                String text = split_events[i].replaceFirst("/", "                    ");
                ServerForm.ta_events.append(text + "\n");
              }
            Thread.sleep(20000);
          }
     
        } catch (InterruptedException ex) {
        }
    };
    
    thread_2 = new Thread(update_events);
    thread_2.start();
  }
  
  public void shutdown_server() throws InterruptedException
  {
    try {
      for (int i =0; i < count;i++) {
        if (clients_th[i] != null) {
          System.out.println(i + " поток клиента завершается...");
          
          myThread[i].Shutdown_thread();
          clients_th[i].join();
          System.out.println(i + " поток клиента завершен");
        }
      }
           
      if (server != null)
      {
        server.close();
        System.out.println("Сокет сервера закрыт");
      }
 
      try {
        thread.join();
        thread_2.interrupt();
        thread_2.join();
        System.out.println("thread_2 завершен");
        connection.close();
        System.out.println("Подключение к базе данных закрыто");
      }catch (InterruptedException ex) {
        System.out.println("Ошибка при завершении основного потока");
      } catch (SQLException ex) {
        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
      }          
    }catch (IOException ex) {
      System.out.println("Ошибка при закрытии сокета или потока клиента");
    }
  }


}
