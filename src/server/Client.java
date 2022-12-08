package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;


public class Client {
  
  Socket socket = null;
  DataOutputStream os = null;
  DataInputStream is = null;
  
  Thread Rec = null;      //����� ���������
  String data = null;
  JTextArea ta_events = null;
  JLabel lb_time = null;
  JButton bt_connect = null;

  public Client(InetAddress ip, int port) throws IOException
  {
    try {
      socket = new Socket(ip, port);
      os = new DataOutputStream(socket.getOutputStream());
      is = new DataInputStream(socket.getInputStream());

      
    }catch(UnknownHostException e) {}
    catch(IOException e){return;}
    
    Runnable Rec_t = () -> {

      while(!socket.isClosed())
      {
        try {
          String msg = is.readUTF();
          
          if (msg.equalsIgnoreCase("quit"))
          {
            System.out.println("������ �� ������� ������� � ����������");
          }
          
          if (msg.startsWith("data#"))
          {
            System.out.println("������ �� ������� ������ � ������� � ��������");
            String data = msg.replaceFirst("data#", "");
            
            String[] split_data = data.split("#");
            lb_time.setText(split_data[0]);
            
            ta_events.setText("");
            for (int i = 1; i < split_data.length; i++)
            {
              String text = split_data[i].replaceFirst("/", "                    ");
              ta_events.append(text + "\n");
            }
          }
          
          if (msg.startsWith("event#"))
          {
            data = msg.replaceFirst("event#", "������� ");
            data = data.replaceFirst("/", "   ��������� �   ");
            JOptionPane.showMessageDialog(ClientForm.parent, data);
          }
          
          if (msg.equalsIgnoreCase("Success"))
          {
            ta_events.append("������� ���������");
          }
          
          if (msg.equalsIgnoreCase("Fail"))
          {
            ta_events.append("������ ��� ���������� �������");
          }
          
        } catch (IOException ex) {
          try {
            shutdown_client();
          } catch (InterruptedException ex1) {
          }
        }
      }
    };
        
    Rec = new Thread(Rec_t);
    Rec.start();
  }
  
  public void Send(String event, String time)
  {
    if (!socket.isClosed())
    {
      String res = "'" + event + "', '" + time + "'";
      try {
        os.writeUTF("add event#" + res);
        System.out.println("������ ���������� �� ������");
        
        
      } catch (IOException ex) {
        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }
  
  public void shutdown_client() throws InterruptedException
  {
    System.out.println("����������� �� �������...");
    if (!socket.isClosed()){
      try {
        os.writeUTF("�������");
        os.flush();
        
        socket.close();
        is.close();
        os.close();
        System.out.println("����� � iostream �������");
        
        bt_connect.setText("������������");
      } catch (IOException ex) {
        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        System.out.println("���������� ��� ������� ����������� �� �������");
      }
    }
  }
  
  public void PassObjects(JTextArea ta, JLabel lb_t,JButton bt)
  {
    ta_events = ta;
    lb_time = lb_t;

    bt_connect = bt;
    
    if (socket == null)
    {
      bt_connect.setText("������������");
    }
  }
}
