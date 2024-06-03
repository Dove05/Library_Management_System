
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFrame;


public class FundTransfer extends javax.swing.JFrame {
  private static String accountNumber;
    private ManageProfile manageProfile;
    private ArrayList<String> accountNumbers = new ArrayList<>();

    public FundTransfer(ManageProfile manageProfile) {
        initComponents();
        this.manageProfile = manageProfile;
        populateComboBox(); // Populate the combo box
    }

    public FundTransfer(String accountNumber) {
        initComponents();
        this.accountNumber = accountNumber;
        // Initialize the ManageProfile object with the provided account number
        this.manageProfile = new ManageProfile(accountNumber);
        populateComboBox(); // Populate the combo box
    }   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Accounts = new javax.swing.JComboBox<>();
        actualamount = new javax.swing.JTextField();
        confirm = new javax.swing.JButton();
        deposit = new javax.swing.JButton();
        fundtransfer = new javax.swing.JButton();
        checkbalance = new javax.swing.JButton();
        withdraw = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Accounts.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        Accounts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AccountsActionPerformed(evt);
            }
        });
        getContentPane().add(Accounts, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 150, 290, 40));
        getContentPane().add(actualamount, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 220, 290, 40));

        confirm.setBorderPainted(false);
        confirm.setContentAreaFilled(false);
        confirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmActionPerformed(evt);
            }
        });
        getContentPane().add(confirm, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 350, 100, 40));

        deposit.setBackground(new java.awt.Color(255, 255, 255));
        deposit.setBorderPainted(false);
        deposit.setContentAreaFilled(false);
        deposit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                depositActionPerformed(evt);
            }
        });
        getContentPane().add(deposit, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 450, 90, 100));

        fundtransfer.setBackground(new java.awt.Color(255, 255, 255));
        fundtransfer.setBorderPainted(false);
        fundtransfer.setContentAreaFilled(false);
        fundtransfer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fundtransferActionPerformed(evt);
            }
        });
        getContentPane().add(fundtransfer, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 450, 130, 100));

        checkbalance.setBackground(new java.awt.Color(255, 255, 255));
        checkbalance.setBorderPainted(false);
        checkbalance.setContentAreaFilled(false);
        checkbalance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkbalanceActionPerformed(evt);
            }
        });
        getContentPane().add(checkbalance, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 450, 130, 100));

        withdraw.setBackground(new java.awt.Color(255, 255, 255));
        withdraw.setBorderPainted(false);
        withdraw.setContentAreaFilled(false);
        withdraw.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                withdrawActionPerformed(evt);
            }
        });
        getContentPane().add(withdraw, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 480, 180, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FundTransfer.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1024, 576));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AccountsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AccountsActionPerformed

    }//GEN-LAST:event_AccountsActionPerformed

    private void confirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmActionPerformed
       // Get the selected account number
        String selectedAccount = (String) Accounts.getSelectedItem();
        // Get the amount to be transferred
        double amount = Double.parseDouble(actualamount.getText());

        // Check if the selected account is different from the current user's account
        if (!selectedAccount.equals(accountNumber)) {
            // Get the current user's account balance
            double currentUserBalance = manageProfile.getCurrentUserBalance();

            // Check if the current user has sufficient balance
            if (currentUserBalance >= amount) {
                // Deduct the amount from the current user's account
                boolean isTransferSuccessful = manageProfile.updateBalance(-amount); // Deduct from current user
                if (isTransferSuccessful) {
                    // Add the amount to the selected account
                    boolean isAddedToSelectedAccount = updateBalance(selectedAccount, amount);
                    if (isAddedToSelectedAccount) {
                        // Show a success message
                        javax.swing.JOptionPane.showMessageDialog(this, "Transfer successful.");
                    } else {
                        // Rollback the deducted amount from the current user's account if adding to the selected account failed
                        manageProfile.updateBalance(amount);
                        javax.swing.JOptionPane.showMessageDialog(this, "Transfer failed. Please try again.");
                    }
                } else {
                    javax.swing.JOptionPane.showMessageDialog(this, "Transfer failed. Please try again.");
                }
            } else {
                // Show an error message if the current user doesn't have sufficient balance
                javax.swing.JOptionPane.showMessageDialog(this, "Insufficient balance. Transfer failed.");
            }
        } else {
            // Show an error message if the selected account is the same as the current user's account
            javax.swing.JOptionPane.showMessageDialog(this, "Cannot transfer to the same account.");
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            // Create a ManageProfile object with the account number
            ManageProfile manageProfile = new ManageProfile(accountNumber);
            // Pass the ManageProfile object to the FundTransfer constructor
            new FundTransfer(manageProfile).setVisible(true);
        });
    
    }//GEN-LAST:event_confirmActionPerformed

    private void depositActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_depositActionPerformed
        // Open the CashDeposit
        openCashDeposit(accountNumber);
    }//GEN-LAST:event_depositActionPerformed

    private void fundtransferActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fundtransferActionPerformed
        // Open FundTransfer frame and pass the ManageProfile object
        openFundTransfer();
    }//GEN-LAST:event_fundtransferActionPerformed

    private void checkbalanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkbalanceActionPerformed
       //open checkbalanceframe and pass the ManageProfile object
       opencheckbalance();
      
    }//GEN-LAST:event_checkbalanceActionPerformed

    private void withdrawActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_withdrawActionPerformed
        openWithDraw(accountNumber);
    }//GEN-LAST:event_withdrawActionPerformed

   
   
 
    private void populateComboBox() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("accounts.csv"));
            String line;
            boolean isFirstLine = true; // Flag to skip the first line
            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue; // Skip the first line
                }
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    String accountNumber = parts[0];
                    // Add the account number to the ArrayList
                    accountNumbers.add(accountNumber);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Clear existing items in the JComboBox
        Accounts.removeAllItems();
        // Add the account numbers from the ArrayList to the JComboBox
        for (String accountNumber : accountNumbers) {
            Accounts.addItem(accountNumber);
        }
    }

    private boolean updateBalance(String accountNumber, double amount) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"));
            String line;
            ArrayList<String> updatedLines = new ArrayList<>();
            boolean isUpdated = false;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    String accNumber = parts[0];
                    if (accNumber.equals(accountNumber)) {
                        double balance = Double.parseDouble(parts[1]);
                        balance += amount;
                        // Convert the updated balance to string format
                        String updatedBalance = String.format("%.2f", balance);
                        // Update the line with the new balance
                        line = accNumber + "," + updatedBalance;
                        isUpdated = true;
                    }
                }
                updatedLines.add(line);
            }
            reader.close();

            BufferedWriter writer = new BufferedWriter(new FileWriter("transactions.csv"));
            for (String updatedLine : updatedLines) {
                writer.write(updatedLine);
                writer.newLine();
            }
            writer.close();

            return isUpdated;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
 private void openFundTransfer() {
       // Open the FundTransfer frame and pass the ManageProfile object
        FundTransfer fundTransfer = new FundTransfer(manageProfile);
        fundTransfer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fundTransfer.setVisible(true);
        dispose(); // Close the current frame
    }
    private String getCurrentUserAccountNumber() {
        // For simplicity, let's assume the current user's account number is hardcoded
        return "current_user_account_number";
    
}
     // Method to display the current balance and account number in jLabel2


private void openWithDraw(String accountNumber) {
        // Open the CashDeposit frame and pass the account number
        WithDraw WithDraw = new WithDraw(accountNumber);
        WithDraw.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        WithDraw.setVisible(true);
        dispose(); // Close the current frame
    }

    private void openCashDeposit(String accountNumber) {
        // Open the CashDeposit frame and pass the account number
        CashDeposit cashDeposit = new CashDeposit(accountNumber);
        cashDeposit.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cashDeposit.setVisible(true);
        dispose(); // Close the current frame
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> Accounts;
    private javax.swing.JTextField actualamount;
    private javax.swing.JButton checkbalance;
    private javax.swing.JButton confirm;
    private javax.swing.JButton deposit;
    private javax.swing.JButton fundtransfer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton withdraw;
    // End of variables declaration//GEN-END:variables

    private void opencheckbalance() {
         // Open BalanceInquiry frame and pass the account number
        BalanceInquiry balanceInquiry = new BalanceInquiry(accountNumber);
        balanceInquiry.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        balanceInquiry.setVisible(true);
        dispose(); // Close the current frame
}}
