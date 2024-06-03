
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.JOptionPane;


public class Transactions extends javax.swing.JFrame {
    private String loggedInAccountNumber;

    public Transactions(String accountNumber) {
        this.loggedInAccountNumber = accountNumber;
        initComponents();
        displayTransactions();
    }


   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        transactions = new javax.swing.JButton();
        fundtransfer = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextArea1.setEditable(false);
        jTextArea1.setBackground(new java.awt.Color(255, 255, 255));
        jTextArea1.setColumns(20);
        jTextArea1.setForeground(new java.awt.Color(0, 0, 0));
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 140, 510, 270));

        transactions.setBackground(new java.awt.Color(255, 255, 255));
        transactions.setBorderPainted(false);
        transactions.setContentAreaFilled(false);
        transactions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                transactionsActionPerformed(evt);
            }
        });
        getContentPane().add(transactions, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 450, 130, 100));

        fundtransfer.setBackground(new java.awt.Color(255, 255, 255));
        fundtransfer.setBorderPainted(false);
        fundtransfer.setContentAreaFilled(false);
        fundtransfer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fundtransferActionPerformed(evt);
            }
        });
        getContentPane().add(fundtransfer, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 450, 130, 100));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Transactions.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1024, 576));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fundtransferActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fundtransferActionPerformed
    
        
    }//GEN-LAST:event_fundtransferActionPerformed

    private void transactionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_transactionsActionPerformed
      displayTransactions();
    }//GEN-LAST:event_transactionsActionPerformed


    public static void main(String args[]) {
      
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Transactions("1234567890").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton fundtransfer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JButton transactions;
    // End of variables declaration//GEN-END:variables

private void displayTransactions() {
    try {
        // Define the folders where deposits and withdrawals are stored
        File depositsFolder = new File("DepositsFile");
        File withdrawalsFolder = new File("transaction_history");
        
        // Ensure that the folders exist, otherwise create them
        if (!depositsFolder.exists()) {
            depositsFolder.mkdirs();
        }
        if (!withdrawalsFolder.exists()) {
            withdrawalsFolder.mkdirs();
        }
        
        // Construct the file paths for deposits and withdrawals based on the logged-in account number
        File depositMonitorFile = new File(depositsFolder, loggedInAccountNumber + ".csv");
        File withdrawalMonitorFile = new File(withdrawalsFolder, loggedInAccountNumber + ".csv");
        
        // Create readers for deposits and withdrawals files
        BufferedReader depositReader = new BufferedReader(new FileReader(depositMonitorFile));
        BufferedReader withdrawalReader = new BufferedReader(new FileReader(withdrawalMonitorFile));
        
        // Initialize a StringBuilder to store transaction information
        StringBuilder text = new StringBuilder();
        
        // Read deposits
        String depositLine;
        boolean firstRow = true; // Flag to skip the first row
        while ((depositLine = depositReader.readLine()) != null) {
            if (firstRow) {
                firstRow = false;
                continue; // Skip the first row
            }
            String[] parts = depositLine.split(",");
            if (parts.length >= 3) {
                text.append("Deposit: ").append(parts[2]).append("\n");
            }
        }
        
        // Read withdrawals
        String withdrawalLine;
        firstRow = true; // Reset the flag for withdrawals
        while ((withdrawalLine = withdrawalReader.readLine()) != null) {
            if (firstRow) {
                firstRow = false;
                continue; // Skip the first row
            }
            String[] parts = withdrawalLine.split(",");
            if (parts.length >= 3) {
                text.append("Withdrawal: ").append(parts[2]).append("\n");
            }
        }
        
        // Close the readers
        depositReader.close();
        withdrawalReader.close();
        
        // Set the text to the JTextArea to display transactions
        jTextArea1.setText(text.toString());
    } catch (Exception e) {
        // Handle exceptions
        JOptionPane.showMessageDialog(null, "Error occurred while reading transactions file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}}
