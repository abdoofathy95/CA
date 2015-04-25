package main;

import components.Parser;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintStream;
import javax.swing.JFileChooser;

public class GUI extends javax.swing.JFrame {
String url;
    public GUI() {
        
        initComponents();
      
    }
public void setLabelText(String text)
{
 
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        RegisterFile = new javax.swing.JTable();
        openFileButton = new javax.swing.JButton();
        Assemble = new javax.swing.JButton();
        Run = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        edit = new javax.swing.JEditorPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        console = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        RegisterFile.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"$zero", "0x00000000000000000000000000000000"},
                {"$at", "0x00000000000000000000000000000000"},
                {"$v0", "0x00000000000000000000000000000000"},
                {"$v1", "0x00000000000000000000000000000000"},
                {"$a0", "0x00000000000000000000000000000000"},
                {"$a1", "0x00000000000000000000000000000000"},
                {"$a2", "0x00000000000000000000000000000000"},
                {"$a3", "0x00000000000000000000000000000000"},
                {"$t0", "0x00000000000000000000000000000000"},
                {"$t1", "0x00000000000000000000000000000000"},
                {"$t2", "0x00000000000000000000000000000000"},
                {"$t3", "0x00000000000000000000000000000000"},
                {"$t4", "0x00000000000000000000000000000000"},
                {"$t5", "0x00000000000000000000000000000000"},
                {"$t6", "0x00000000000000000000000000000000"},
                {"$t7", "0x00000000000000000000000000000000"},
                {"$s0", "0x00000000000000000000000000000000"},
                {"$s1", "0x00000000000000000000000000000000"},
                {"$s2", "0x00000000000000000000000000000000"},
                {"$s3", "0x00000000000000000000000000000000"},
                {"$s4", "0x00000000000000000000000000000000"},
                {"$s5", "0x00000000000000000000000000000000"},
                {"$s6", "0x00000000000000000000000000000000"},
                {"$s7", "0x00000000000000000000000000000000"},
                {"$k0", "0x00000000000000000000000000000000"},
                {"$k1", "0x00000000000000000000000000000000"},
                {"$gp", "0x00000000000000000000000000000000"},
                {"$sp", "0x00000000000000000000000000000000"},
                {"$ra", "0x00000000000000000000000000000000"}
            },
            new String [] {
                "Name", "Value"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(RegisterFile);
        if (RegisterFile.getColumnModel().getColumnCount() > 0) {
            RegisterFile.getColumnModel().getColumn(0).setResizable(false);
            RegisterFile.getColumnModel().getColumn(1).setResizable(false);
        }

        openFileButton.setText("Open File");
        openFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openFileButtonActionPerformed(evt);
            }
        });

        Assemble.setText("Assemble");
        Assemble.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AssembleActionPerformed(evt);
            }
        });

        Run.setText("Run");
        Run.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RunActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel1.setText("Console:");

        jScrollPane4.setViewportView(edit);

        console.setColumns(20);
        console.setRows(5);
        jScrollPane3.setViewportView(console);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(openFileButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Assemble)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Run))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)
                    .addComponent(jScrollPane3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(openFileButton)
                    .addComponent(Assemble)
                    .addComponent(Run))
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(56, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void openFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openFileButtonActionPerformed
      try{
        JFileChooser x = new JFileChooser();
        x.showOpenDialog(openFileButton);
         url  = x.getSelectedFile().getAbsolutePath();
            try
                {
                    FileReader reader = new FileReader( url );
                    BufferedReader br = new BufferedReader(reader);
               
                    edit.read(br , null);
                    br.close();
                    edit.requestFocus();
                }
                catch(Exception e2) { 
                    System.out.println(e2); 
                }
      }
      catch (Exception e)
      {
      }
    }//GEN-LAST:event_openFileButtonActionPerformed

    private void AssembleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AssembleActionPerformed
    
      
            
         try
        {
     
        Parser x = new Parser(url);
        int i = 0;
     while (x.message.get(i) != null)
     {
         
         console.append(x.message.get(i));
         console.append("\n");
        i++;
     }
   

        }
        catch(Exception e)
        {
            
        }
            
        
    }//GEN-LAST:event_AssembleActionPerformed

    private void RunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RunActionPerformed
        MainApp a = new MainApp();
        a.start();
    }//GEN-LAST:event_RunActionPerformed

   
   public static void main (String []args)
   {
       GUI r = new GUI();
        r.setVisible(true);
                
   }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Assemble;
    private javax.swing.JTable RegisterFile;
    private javax.swing.JButton Run;
    private javax.swing.JTextArea console;
    private javax.swing.JEditorPane edit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JButton openFileButton;
    // End of variables declaration//GEN-END:variables


}



