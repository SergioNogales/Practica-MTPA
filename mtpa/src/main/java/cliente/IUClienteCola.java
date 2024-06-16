package cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class IUClienteCola extends javax.swing.JFrame{

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private String usuario;
    private DefaultListModel<String> listModel;

    public IUClienteCola(String usuario) throws IOException, InterruptedException {
        this.usuario = usuario;
        initComponents();
        connectToServer();
        out.println("jugar");
        Thread.sleep(20);
        out.println(usuario);
        
        listModel = new DefaultListModel<>();
        jList1.setModel(listModel);
        
        char[] buffer = new char[1024];
        int length = in.read(buffer);
        String jugadoresString = new String(buffer, 0, length).trim();
        String[] jugadores = jugadoresString.split(",");

        for (String jugador : jugadores) {
            listModel.addElement(jugador);
        }
        
        jList1.repaint();
        
        // Inicia un hilo para escuchar mensajes del servidor
        new Thread(new Runnable() {
            public void run() {
                try {
                    listenForMessages();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(IUClienteCola.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(IUClienteCola.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();
    }

    private void listenForMessages() throws ClassNotFoundException, InterruptedException {
        try {
            System.out.println("Escuchando...");
            char[] buffer = new char[1024];
            int length = in.read(buffer);
            String response = new String(buffer, 0, length).trim();
            while (response != null) {
                if(response.equals("reto")){
                    buffer = new char[1024];
                    length = in.read(buffer);
                    String usuarioReto = new String(buffer, 0, length).trim();
                    IUClienteReto clienteReto = new IUClienteReto(socket, usuarioReto,usuario);
                    clienteReto.setVisible(true);
                    this.setVisible(false);
                    return;
                }
                if(response.equals("reto aceptado")){
                    System.out.println("siu");
                    length = in.read(buffer);
                    String usuarioReto = new String(buffer, 0, length).trim();
                    IUPrueba jugar = new IUPrueba();
                    jugar.setVisible(true);
                    this.setVisible(false);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Usuarios conectados:");

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "hola1", "hola2", "hola3" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        jButton1.setText("Retar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Salir");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jScrollPane1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 225, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(36, 36, 36)
                .addComponent(jButton2)
                .addGap(27, 27, 27))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(60, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String usuarioReto = jList1.getSelectedValue();
        if (usuarioReto != null) {
            if (!usuarioReto.equals(usuario)) {
                JOptionPane.showMessageDialog(this, "Has seleccionado a " + usuarioReto);
                out.println(usuarioReto);
            } else {
                JOptionPane.showMessageDialog(this, "No puedes retarte a ti mismo.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona un usuario primero.");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            // Eliminar el nombre del usuario de la lista
            listModel.removeElement(usuario);
            jList1.repaint();
            out.println("meboi");
            Thread.sleep(20);
            out.println(usuario);
            IUCliente1 c1 = new IUCliente1();
            c1.setVisible(true);
            this.setVisible(false);
        } catch (InterruptedException ex) {
            Logger.getLogger(IUClienteCola.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void connectToServer() {
        try {
            socket = new Socket("localhost", 7894);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
