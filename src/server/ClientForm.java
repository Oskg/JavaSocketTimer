package server;

import java.awt.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;

public class ClientForm extends javax.swing.JFrame {

	final static JFrame parent = new JFrame();
  Client cc = null;
  InetAddress ip = null;
  int port = 0;
  boolean flag = false; 
    
  public ClientForm() {
    initComponents();
  }

  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  // Generated using JFormDesigner Evaluation license - oskg
  private void initComponents() {
      bt_connect = new JButton();
      tf_ip = new JTextField();
      tf_port = new JTextField();
      jLabel1 = new JLabel();
      jLabel2 = new JLabel();
      jScrollPane1 = new JScrollPane();
      ta_events = new JTextArea();
      tf_eventname = new JTextField();
      tf_eventtime = new JTextField();
      bt_add = new JButton();
      jLabel3 = new JLabel();
      jLabel4 = new JLabel();
      lb_time = new JLabel();

      //======== this ========
      setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      var contentPane = getContentPane();

      //---- bt_connect ----
      bt_connect.setFont(new Font("Segoe UI", Font.PLAIN, 18));
      bt_connect.setText("\u041f\u043e\u0434\u043a\u043b\u044e\u0447\u0438\u0442\u044c\u0441\u044f");
      bt_connect.addActionListener(e -> bt_connectActionPerformed(e));

      //---- tf_ip ----
      tf_ip.setFont(new Font("Segoe UI", Font.PLAIN, 18));

      //---- tf_port ----
      tf_port.setFont(new Font("Segoe UI", Font.PLAIN, 18));

      //---- jLabel1 ----
      jLabel1.setFont(new Font("Segoe UI", Font.PLAIN, 18));
      jLabel1.setText("IP:");

      //---- jLabel2 ----
      jLabel2.setFont(new Font("Segoe UI", Font.PLAIN, 18));
      jLabel2.setText("\u041f\u043e\u0440\u0442:");

      //======== jScrollPane1 ========
      {

          //---- ta_events ----
          ta_events.setColumns(20);
          ta_events.setRows(5);
          jScrollPane1.setViewportView(ta_events);
      }

      //---- tf_eventname ----
      tf_eventname.setFont(new Font("Segoe UI", Font.PLAIN, 18));

      //---- tf_eventtime ----
      tf_eventtime.setFont(new Font("Segoe UI", Font.PLAIN, 18));

      //---- bt_add ----
      bt_add.setFont(new Font("Segoe UI", Font.PLAIN, 18));
      bt_add.setText("\u0414\u043e\u0431\u0430\u0432\u0438\u0442\u044c \u0441\u043e\u0431\u044b\u0442\u0438\u0435");
      bt_add.setToolTipText("");
      bt_add.addActionListener(e -> bt_addActionPerformed(e));

      //---- jLabel3 ----
      jLabel3.setFont(new Font("Segoe UI", Font.PLAIN, 18));
      jLabel3.setText("\u041d\u0430\u0437\u0432\u0430\u043d\u0438\u0435 \u0441\u043e\u0431\u044b\u0442\u0438\u044f:");

      //---- jLabel4 ----
      jLabel4.setFont(new Font("Segoe UI", Font.PLAIN, 18));
      jLabel4.setText("\u0412\u0440\u0435\u043c\u044f \u0421\u043e\u0431\u044b\u0442\u0438\u044f");

      //---- lb_time ----
      lb_time.setFont(new Font("Segoe UI", Font.PLAIN, 18));
      lb_time.setText("\u0412\u0440\u0435\u043c\u044f");
      lb_time.setHorizontalTextPosition(SwingConstants.CENTER);

      GroupLayout contentPaneLayout = new GroupLayout(contentPane);
      contentPane.setLayout(contentPaneLayout);
      contentPaneLayout.setHorizontalGroup(
          contentPaneLayout.createParallelGroup()
              .addGroup(contentPaneLayout.createSequentialGroup()
                  .addGap(34, 34, 34)
                  .addGroup(contentPaneLayout.createParallelGroup()
                      .addComponent(jLabel1)
                      .addComponent(tf_ip, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
                      .addComponent(tf_port, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)
                      .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                          .addGroup(contentPaneLayout.createSequentialGroup()
                              .addGroup(contentPaneLayout.createParallelGroup()
                                  .addComponent(jLabel3)
                                  .addComponent(tf_eventname, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE))
                              .addGap(34, 34, 34)
                              .addGroup(contentPaneLayout.createParallelGroup()
                                  .addComponent(tf_eventtime, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
                                  .addComponent(jLabel4))
                              .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                              .addComponent(bt_add, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                          .addComponent(jScrollPane1, GroupLayout.Alignment.LEADING)
                          .addGroup(GroupLayout.Alignment.LEADING, contentPaneLayout.createSequentialGroup()
                              .addComponent(jLabel2)
                              .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                              .addComponent(lb_time)
                              .addGap(101, 101, 101)
                              .addComponent(bt_connect, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
                              .addGap(17, 17, 17))))
                  .addContainerGap(22, Short.MAX_VALUE))
      );
      contentPaneLayout.setVerticalGroup(
          contentPaneLayout.createParallelGroup()
              .addGroup(contentPaneLayout.createSequentialGroup()
                  .addContainerGap()
                  .addComponent(jLabel1)
                  .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                  .addComponent(tf_ip, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                  .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                  .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                      .addComponent(jLabel2)
                      .addComponent(bt_connect)
                      .addComponent(lb_time))
                  .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                  .addComponent(tf_port, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                  .addGap(24, 24, 24)
                  .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE)
                  .addGap(18, 18, 18)
                  .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                      .addGroup(contentPaneLayout.createSequentialGroup()
                          .addComponent(jLabel3)
                          .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                          .addComponent(tf_eventname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                      .addGroup(contentPaneLayout.createSequentialGroup()
                          .addComponent(jLabel4)
                          .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                          .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                              .addComponent(tf_eventtime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                              .addComponent(bt_add))))
                  .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
      );
      pack();
      setLocationRelativeTo(getOwner());
  }// </editor-fold>//GEN-END:initComponents

  private void bt_connectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_connectActionPerformed
    
    if (bt_connect.getText().equalsIgnoreCase("Отключиться"))
    {
      try {
        //Отключение от сервера
        cc.shutdown_client();
      } catch (InterruptedException ex) {
        Logger.getLogger(ClientForm.class.getName()).log(Level.SEVERE, null, ex);
      }
      ta_events.setText("");
      bt_connect.setText("Подключиться");
    }
    else
    {
      try {
        //Подключение к серверу
        ip = InetAddress.getByName(tf_ip.getText());
        
     
        if (ip != null && !(tf_port.getText().equalsIgnoreCase("")))
        {
          bt_connect.setText("Отключиться");
          
          port = Integer.parseInt(tf_port.getText());
          cc = new Client(ip, port);
          cc.PassObjects(ta_events, lb_time,bt_connect);
          
          
        }
        else
        {
          ta_events.append("Некорректно заполненные поля\n");
        }
        
       
      } catch (UnknownHostException ex) {
        System.out.println("Некорректный адрес сервера");
        ta_events.setText("Некорректный адрес сервера");
      }catch (IOException ex) {
          Logger.getLogger(ClientForm.class.getName()).log(Level.SEVERE, null, ex);
      }
      
     
    } 
  }//GEN-LAST:event_bt_connectActionPerformed

  private void bt_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_addActionPerformed
    cc.Send(tf_eventname.getText(), tf_eventtime.getText());
  }//GEN-LAST:event_bt_addActionPerformed

  /**
   * @param args the command line arguments
   */
  public static void main(String args[]) {
    try {
      for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
          javax.swing.UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    } catch (ClassNotFoundException ex) {
      java.util.logging.Logger.getLogger(ClientForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
      java.util.logging.Logger.getLogger(ClientForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
      java.util.logging.Logger.getLogger(ClientForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
      java.util.logging.Logger.getLogger(ClientForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    //</editor-fold>

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        new ClientForm().setVisible(true);
      }
    });
  }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  // Generated using JFormDesigner Evaluation license - oskg
  private JButton bt_connect;
  private JTextField tf_ip;
  private JTextField tf_port;
  private JLabel jLabel1;
  private JLabel jLabel2;
  private JScrollPane jScrollPane1;
  private JTextArea ta_events;
  private JTextField tf_eventname;
  private JTextField tf_eventtime;
  private JButton bt_add;
  private JLabel jLabel3;
  private JLabel jLabel4;
  private JLabel lb_time;
  // End of variables declaration//GEN-END:variables
}
