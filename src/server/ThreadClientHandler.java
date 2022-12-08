package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;

public class ThreadClientHandler implements Runnable {
  private static Socket clientDialog;
  DataOutputStream out = null;
  DataInputStream in = null;

  String events_data = null;
  SimpleDateFormat df = new SimpleDateFormat("d.MM.yyyy HH:mm"); // ‘ормат даты

  public ThreadClientHandler(Socket client) {
    ThreadClientHandler.clientDialog = client;
  }

    
  public void Shutdown_thread()
  {
    try {
      in.close(); // закрываем каналы сокета
      out.flush();
      out.close();
      clientDialog.close(); //закрываем сокет сервера
    } catch (IOException ex) {
      Logger.getLogger(ThreadClientHandler.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    System.out.println(" лиент отключен");
    System.out.println("¬ходной и выходной каналы закрыты");
    
  }
  
  public void UpdateDataEvent(String s)
  {
    events_data = s;
    System.out.println("ѕрин€то с сервера - " + events_data);
  }

  @Override
  public void run() {
    try {
      out = new DataOutputStream(clientDialog.getOutputStream()); // канал записи в сокет
      in = new DataInputStream(clientDialog.getInputStream()); // канал чтени€ из сокета

      
      Runnable news = () -> {
        while(!Thread.currentThread().isInterrupted())
        {
          try {
            Date date = new Date();
            
            String[] event = events_data.split("#");
            
            for (int i = 0; i < event.length; i++)
            {
              if (event[i].endsWith(df.format(date)))
              {
                out.writeUTF("event#" + event[i]);
              }
            }
            
            
            out.writeUTF("data#" + df.format(date) + "#" + events_data);
            out.flush();
            Thread.sleep(10000);
          } catch (InterruptedException ex) {}
          catch (IOException ex) {
          }
        }
      };

    Thread thread_newsletter = new Thread(news);
    thread_newsletter.start();

      while(!Thread.currentThread().isInterrupted())
      {
        while (!clientDialog.isClosed()) // начинаем диалог с подключенным клиентом, пока сокет не закрыт
        {
          String entry = in.readUTF();

          if (entry.equalsIgnoreCase("quit")) { // если кодовое слово получено то инициализируетс€ закрытие
            System.out.println("«апрос на закрытие сокета");
          }

          if (entry.startsWith("add event#"))
          {
            System.out.println("«апрос на добавление данных в базу");
            if (Server.GetAccess())
            {
              String res[] = entry.split("add event#");
              String sql = "INSERT INTO events (name_event, time_event) VALUES (" + res[1] + ");";

              if (Server.WriteDataToDatabase(sql))
              {
                out.writeUTF("Success");
              }
              else
              {
                out.writeUTF("Fail");
              }
              
              out.flush();
            }
          }
        }

        clientDialog.close();
        break;
      }
    }catch (IOException e) {
      System.out.println("—окет закрыт!");
    }
  }
}
