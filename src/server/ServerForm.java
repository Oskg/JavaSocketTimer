package server;

import java.awt.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;

public class ServerForm extends javax.swing.JFrame {

  int flag = 0;
  int port = 0;
  Server ss = null;
  InetAddress ip = null;
  
  public ServerForm() {
    initComponents();
  }



    @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  // Generated using JFormDesigner Evaluation license - oskg
  private void initComponents() {
      button_server = new JButton();
      label_status = new JLabel();
      jLabel2 = new JLabel();
      jLabel3 = new JLabel();
      ta_port = new JTextField();
      scrollPane1 = new JScrollPane();
      ta_events = new JTextArea();
      ip_ta = new JTextField();

      //======== this ========
      setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      var contentPane = getContentPane();

      //---- button_server ----
      button_server.setFont(new Font("Segoe UI", Font.PLAIN, 18));
      button_server.setText("\u0417\u0430\u043f\u0443\u0441\u0442\u0438\u0442\u044c \u0441\u0435\u0440\u0432\u0435\u0440");
      button_server.addActionListener(e -> {
          try {
              button_serverActionPerformed(e);
          } catch (UnknownHostException ex) {
              ex.printStackTrace();
          }
      });

      //---- label_status ----
      label_status.setFont(new Font("Segoe UI", Font.PLAIN, 18));
      label_status.setText("\u0421\u0435\u0440\u0432\u0435\u0440 \u043d\u0435 \u0437\u0430\u043f\u0443\u0449\u0435\u043d ");

      //---- jLabel2 ----
      jLabel2.setFont(new Font("Segoe UI", Font.PLAIN, 18));
      jLabel2.setText("IP:");

      //---- jLabel3 ----
      jLabel3.setFont(new Font("Segoe UI", Font.PLAIN, 18));
      jLabel3.setText("\u041f\u043e\u0440\u0442:");

      //---- ta_port ----
      ta_port.setFont(new Font("Segoe UI", Font.PLAIN, 18));

      //======== scrollPane1 ========
      {
          scrollPane1.setViewportView(ta_events);
      }

      //---- ip_ta ----
      ip_ta.setFont(new Font("Segoe UI", Font.PLAIN, 18));

      GroupLayout contentPaneLayout = new GroupLayout(contentPane);
      contentPane.setLayout(contentPaneLayout);
      contentPaneLayout.setHorizontalGroup(
          contentPaneLayout.createParallelGroup()
              .addGroup(contentPaneLayout.createSequentialGroup()
                  .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                      .addComponent(label_status)
                      .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                          .addGroup(contentPaneLayout.createSequentialGroup()
                              .addContainerGap()
                              .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 352, GroupLayout.PREFERRED_SIZE))
                          .addGroup(contentPaneLayout.createSequentialGroup()
                              .addGap(27, 27, 27)
                              .addComponent(jLabel2)
                              .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                              .addComponent(ip_ta, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)
                              .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                              .addComponent(jLabel3))))
                  .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                  .addGroup(contentPaneLayout.createParallelGroup()
                      .addGroup(contentPaneLayout.createSequentialGroup()
                          .addComponent(button_server, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                          .addGap(353, 353, 353))
                      .addGroup(contentPaneLayout.createSequentialGroup()
                          .addComponent(ta_port, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                          .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
      );
      contentPaneLayout.setVerticalGroup(
          contentPaneLayout.createParallelGroup()
              .addGroup(contentPaneLayout.createSequentialGroup()
                  .addGap(14, 14, 14)
                  .addComponent(label_status)
                  .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                  .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                      .addComponent(ta_port, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                      .addComponent(jLabel3)
                      .addComponent(ip_ta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                      .addComponent(jLabel2))
                  .addGap(21, 21, 21)
                  .addGroup(contentPaneLayout.createParallelGroup()
                      .addComponent(button_server, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                      .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE))
                  .addContainerGap(25, Short.MAX_VALUE))
      );
      setSize(560, 365);
      setLocationRelativeTo(getOwner());
  }// </editor-fold>//GEN-END:initComponents

    private void button_serverActionPerformed(java.awt.event.ActionEvent evt) throws UnknownHostException {//GEN-FIRST:event_button_serverActionPerformed

      if (flag == 0)
      {
        //Запускаем сервер
        ip = InetAddress.getByName(ip_ta.getText());
        port = Integer.parseInt(ta_port.getText());
        ss = new Server(ip, port);
            
        flag = 1;
        label_status.setText("Сервер запущен!");
        button_server.setText("Остановить сервер");
      }
      else if (flag == 1)
      {
        try {
          //Стоп сервер
          ss.shutdown_server();
          flag = 0;
          label_status.setText("Сервер не запущен ");
		  ta_events.setText("");
          button_server.setText("Запустить сервер");
        } catch (InterruptedException ex) {
            Logger.getLogger(ServerForm.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    }//GEN-LAST:event_button_serverActionPerformed



    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ServerForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ServerForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ServerForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ServerForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ServerForm().setVisible(true);
            }
        });
    }
    
    
  // Variables declaration - do not modify//GEN-BEGIN:variables
  // Generated using JFormDesigner Evaluation license - oskg
  private JButton button_server;
  private JLabel label_status;
  private JLabel jLabel2;
  private JLabel jLabel3;
  private JTextField ta_port;
  private JScrollPane scrollPane1;
  public static JTextArea ta_events;
  private JTextField ip_ta;
  // End of variables declaration//GEN-END:variables
}
