package main;

import components.Memory;
import components.Parser;
import components.RegisterFile;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class GUI extends javax.swing.JFrame {
String url;
boolean assembled;
    public GUI() {
assembled = false;
        initComponents();
             
    }
public void initMemory()
{
 int x = 268500992;
        Object rowData[][] = new Object[1250][11];
        for (int i = 0; i < 1250; i++) {
            rowData[i][0] = "0x" + Integer.toHexString(x);
            rowData[i][1] = "0x00000000";
            rowData[i][2] = "0x00000000";
            rowData[i][3] = "0x00000000";
            rowData[i][4] = "0x00000000";
            rowData[i][5] = "0x00000000";
            rowData[i][6] = "0x00000000";
            rowData[i][7] = "0x00000000";
            rowData[i][8] = "0x00000000";
            rowData[i][9] = "0x00000000";
            rowData[i][10] = "0x00000000";
            x += 40;
        }
        Object columnNames[] = {"Address", "Value (+0)", "Value (+4)", "Value (+8)", "Value (+c)", "Value (+10)", "Value (+14)", "Value (+18)", "Value (+1c)",  "Value (+20)",  "Value (+24)"};
         MemoryTable = new JTable(rowData, columnNames);

        JScrollPane scrollPane = new JScrollPane(MemoryTable);
        
       
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        RegisterTable = new javax.swing.JTable();
        openFileButton = new javax.swing.JButton();
        Assemble = new javax.swing.JButton();
        Run = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        console = new javax.swing.JTextArea();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        edit = new javax.swing.JTextArea();
        jScrollPane5 = new javax.swing.JScrollPane();
        /*
        MemoryTable = new javax.swing.JTable();
        */
        initMemory();
        saveButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        RegisterTable.setModel(new javax.swing.table.DefaultTableModel(
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
                {"$t8", "0x00000000000000000000000000000000"},
                {"$t9", "0x00000000000000000000000000000000"},
                {"$k0", "0x00000000000000000000000000000000"},
                {"$k1", "0x00000000000000000000000000000000"},
                {"$gp", "0x00000000000000000000000000000000"},
                {"$sp", "00000000000000001100001101010000"},
                {"$fp", "0x00000000000000000000000000000000"},
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
        jScrollPane2.setViewportView(RegisterTable);
        if (RegisterTable.getColumnModel().getColumnCount() > 0) {
            RegisterTable.getColumnModel().getColumn(0).setResizable(false);
            RegisterTable.getColumnModel().getColumn(0).setPreferredWidth(-20);
            RegisterTable.getColumnModel().getColumn(1).setResizable(false);
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

        console.setColumns(20);
        console.setRows(5);
        jScrollPane3.setViewportView(console);

        edit.setColumns(20);
        edit.setRows(5);
        jScrollPane1.setViewportView(edit);

        jTabbedPane1.addTab("editor", jScrollPane1);

        /*
        MemoryTable.setModel(
        ));
        */
        jScrollPane5.setViewportView(MemoryTable);

        jTabbedPane1.addTab("Memory", jScrollPane5);

        saveButton.setText("Save File");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(openFileButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(saveButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Assemble)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Run))
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 866, Short.MAX_VALUE)
                    .addComponent(jScrollPane3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(188, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(openFileButton)
                            .addComponent(Assemble)
                            .addComponent(Run)
                            .addComponent(saveButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE))
                    .addComponent(jScrollPane2)))
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
    
      
             console.setText("");
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
      
        console.setText("");
       
        MainApp a = new MainApp();
      
        a.start();
        
      for (int i = 0; i < a.log.size(); i++)
      {
          console.append(a.log.get(i));
          console.append("\n");
      }
        RegisterTable.setValueAt(RegisterFile.registersValue.get("at"), 1, 1);
        RegisterTable.setValueAt(RegisterFile.registersValue.get("v0"), 2, 1);
        RegisterTable.setValueAt(RegisterFile.registersValue.get("v1"), 3, 1);
        RegisterTable.setValueAt(RegisterFile.registersValue.get("a0"), 4, 1);
        RegisterTable.setValueAt(RegisterFile.registersValue.get("a1"), 5, 1);
        RegisterTable.setValueAt(RegisterFile.registersValue.get("a2"), 6, 1);
        RegisterTable.setValueAt(RegisterFile.registersValue.get("a3"), 7, 1);
        RegisterTable.setValueAt(RegisterFile.registersValue.get("t0"), 8, 1);
        RegisterTable.setValueAt(RegisterFile.registersValue.get("t1"), 9, 1);
        RegisterTable.setValueAt(RegisterFile.registersValue.get("t2"), 10, 1);
        RegisterTable.setValueAt(RegisterFile.registersValue.get("t3"), 11, 1);
        RegisterTable.setValueAt(RegisterFile.registersValue.get("t4"), 12, 1);
        RegisterTable.setValueAt(RegisterFile.registersValue.get("t5"), 13, 1);
        RegisterTable.setValueAt(RegisterFile.registersValue.get("t6"), 14, 1);
        RegisterTable.setValueAt(RegisterFile.registersValue.get("t7"), 15, 1);
        RegisterTable.setValueAt(RegisterFile.registersValue.get("s0"), 16, 1);
        RegisterTable.setValueAt(RegisterFile.registersValue.get("s1"), 17, 1);
        RegisterTable.setValueAt(RegisterFile.registersValue.get("s2"), 18, 1);
        RegisterTable.setValueAt(RegisterFile.registersValue.get("s3"), 19, 1);
        RegisterTable.setValueAt(RegisterFile.registersValue.get("s4"), 20, 1);
        RegisterTable.setValueAt(RegisterFile.registersValue.get("s5"), 21, 1);
        RegisterTable.setValueAt(RegisterFile.registersValue.get("s6"), 22, 1);
        RegisterTable.setValueAt(RegisterFile.registersValue.get("s7"), 23, 1);
        RegisterTable.setValueAt(RegisterFile.registersValue.get("t8"), 24, 1);
        RegisterTable.setValueAt(RegisterFile.registersValue.get("t9"), 25, 1);
        RegisterTable.setValueAt(RegisterFile.registersValue.get("k0"), 26, 1);
        RegisterTable.setValueAt(RegisterFile.registersValue.get("k1"), 27, 1);
        RegisterTable.setValueAt(RegisterFile.registersValue.get("gp"), 28, 1);
        RegisterTable.setValueAt(RegisterFile.registersValue.get("sp"), 29, 1);
        RegisterTable.setValueAt(RegisterFile.registersValue.get("fp"), 30, 1);
        RegisterTable.setValueAt(RegisterFile.registersValue.get("ra"), 31, 1);
        
        
        int c = 0;
      for (int i = 1; i < 1250; i++)
      {
          for (int j = 1; j < 11; j++)
          {
              String b = "";
              for (int k = 0; k < 4; k++)
              {
                  if (Memory.mem[c] == null)
                  {
                      b += "00";
                  }
                  else
                  {
                  b += Memory.mem[c];
                  c++;
                  }
              }
              MemoryTable.setValueAt(b, i, j);
             
          
       
      
        }
        }
      
    }//GEN-LAST:event_RunActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
       FileWriter fw;
    try {
        fw = new FileWriter(url);
        edit.write(fw);
    } catch (IOException ex) {
        Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
    }

    }//GEN-LAST:event_saveButtonActionPerformed
  
   
   public static void main (String []args)
   {
       GUI r = new GUI();  
       r.setVisible(true);
                
   }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Assemble;
    private javax.swing.JTable MemoryTable;
    private javax.swing.JTable RegisterTable;
    private javax.swing.JButton Run;
    private javax.swing.JTextArea console;
    private javax.swing.JTextArea edit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton openFileButton;
    private javax.swing.JButton saveButton;
    // End of variables declaration//GEN-END:variables


}



