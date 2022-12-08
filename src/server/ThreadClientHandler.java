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
  SimpleDateFormat df = new SimpleDateFormat("d.MM.yyyy HH:mm"); // ������ ����

  public ThreadClientHandler(Socket client) {
    ThreadClientHandler.clientDialog = client;
  }

    
  public void Shutdown_thread()
  {
    try {
      in.close(); // ��������� ������ ������
      out.flush();
      out.close();
      clientDialog.close(); //��������� ����� �������
    } catch (IOException ex) {
      Logger.getLogger(ThreadClientHandler.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    System.out.println("������ ��������");
    System.out.println("������� � �������� ������ �������");
    
  }
  
  public void UpdateDataEvent(String s)
  {
    events_data = s;
    System.out.println("������� � ������� - " + events_data);
  }

  @Override
  public void run() {
    try {
      out = new DataOutputStream(clientDialog.getOutputStream()); // ����� ������ � �����
      in = new DataInputStream(clientDialog.getInputStream()); // ����� ������ �� ������

      
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
        while (!clientDialog.isClosed()) // �������� ������ � ������������ ��������, ���� ����� �� ������
        {
          String entry = in.readUTF();

          if (entry.equalsIgnoreCase("quit")) { // ���� ������� ����� �������� �� ���������������� ��������
            System.out.println("������ �� �������� ������");
          }

          if (entry.startsWith("add event#"))
          {
            System.out.println("������ �� ���������� ������ � ����");
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
      System.out.println("����� ������!");
    }
  }
}
