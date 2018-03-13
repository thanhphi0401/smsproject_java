/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student;

import Model.AccountInfo;
import Model.Semester;
import Model.StudentInfoModel;
import Model.StudentMarkModel;
import Model.Subject;
import teacher.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import login.LoginForm;

/**
 *
 * @author vomin
 */
public class StudentMark extends javax.swing.JFrame {

    /**
     * Creates new form StudentInfo
     */
    AccountInfo account = new AccountInfo();
    StudentDAO studentDao = new StudentDAO();
    TeacherDAO teacherDao = new TeacherDAO();
    static String MSSV = "";

    public StudentMark() {
        initComponents();
        this.setLocationRelativeTo(this);
        this.setResizable(false);

    }

    public StudentMark(AccountInfo account, String MSSV) {
        initComponents();
        this.setLocationRelativeTo(this);
        this.setResizable(false);
        if (account != null && account.Email != null && account.Password != null) {
            this.account = account;
            lbInfo.setText(account.Email.split("@")[0]);
        }
        if (MSSV != null) {
            this.MSSV = MSSV;
        }

        ArrayList<Semester> semesterdlst = new ArrayList<Semester>();
        semesterdlst = teacherDao.getSemesterList();

        LoadToCbbSemester(semesterdlst);

        ArrayList<StudentMarkModel> markList = new ArrayList<StudentMarkModel>();
        markList = studentDao.getListStudentMarkByMSSV(MSSV);

        LoadListMark(markList);

    }

    private void LoadToCbbSemester(ArrayList<Semester> semesterdlst) {
        cbbSemester.addItem("Choose Semester");

        for (int i = 0; i < semesterdlst.size(); i++) {
            cbbSemester.addItem(semesterdlst.get(i).SemesterID + "-" + semesterdlst.get(i).Name);

        }

    }

    private void LoadListMark(ArrayList<StudentMarkModel> markList) {

        DefaultTableModel model = (DefaultTableModel) tableMark.getModel();
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged(); // noti

        String[] cols = {"Semester", "Subject", "NumberCredits", "MidMark", "FinalMark", "AVGMark", "Type"};

        for (int i = 0; i < markList.size(); i++) {
            StudentMarkModel a = markList.get(i);

            //get detail SV by MSSV
            Subject subject = teacherDao.getDetailSubjectInfo(a.MMH);
            Semester semester = teacherDao.getDetailSemester(a.SemesterID);
            Object[] data = {semester.Name, subject.Name, subject.NumberOfCredits, a.MidPoint, a.FinalPoint, a.AVGPoint, a.Type};
            model.addRow(data);

        }

    }

    public Icon load(String linkImage, int k, int m) {/*linkImage là tên icon, k kích thước chiều rộng mình muốn,m chiều dài và hàm này trả về giá trị là 1 icon.*/

        try {
            BufferedImage image = ImageIO.read(new File(linkImage));//đọc ảnh dùng BufferedImage

            int x = k;
            int y = m;
            int ix = image.getWidth();
            int iy = image.getHeight();
            int dx = 0, dy = 0;

            if (x / y > ix / iy) {
                dy = y;
                dx = dy * ix / iy;
            } else {
                dx = x;
                dy = dx * iy / ix;
            }   

            return new ImageIcon(image.getScaledInstance(dx, dy,
                    image.SCALE_SMOOTH));

        } catch (IOException e) {

            e.printStackTrace();
        }

        return null;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lbInfo = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableMark = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        btnViewMarl = new javax.swing.JButton();
        cbbSemester = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setIcon(load("D:\\Aptech\\LapTrinh_AJ1+2\\AJ2\\ProjectHKII\\content\\image\\admin.png",50,50));
        jLabel2.setMaximumSize(new java.awt.Dimension(100, 100));
        jLabel2.setMinimumSize(new java.awt.Dimension(100, 100));
        jLabel2.setName(""); // NOI18N
        jLabel2.setPreferredSize(new java.awt.Dimension(50, 50));
        jLabel2.setVerifyInputWhenFocusTarget(false);

        jLabel3.setBackground(new java.awt.Color(204, 204, 204));
        jLabel3.setIcon(new javax.swing.ImageIcon("D:\\Aptech\\LapTrinh_AJ1+2\\AJ2\\ProjectHKII\\content\\image\\studentmark_student.png")); // NOI18N

        lbInfo.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbInfo.setText("Welcome, Minh Phát");

        btnLogout.setText("Logout");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 14, Short.MAX_VALUE)
        );

        jButton7.setIcon(load("D:\\Aptech\\LapTrinh_AJ1+2\\AJ2\\ProjectHKII\\content\\image\\back.png",50,50));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton7)
                .addGap(28, 28, 28))
        );

        tableMark.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Semester", "Subject", "NumberCredits", "MidMark", "FinalMark", "AVGMark", "Type"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableMark);

        jPanel3.setBackground(new java.awt.Color(153, 153, 153));

        btnViewMarl.setText("ViewMark");
        btnViewMarl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewMarlActionPerformed(evt);
            }
        });

        jLabel4.setText("Semester");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel4)
                .addGap(37, 37, 37)
                .addComponent(cbbSemester, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addComponent(btnViewMarl)
                .addContainerGap(831, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnViewMarl, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbSemester, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jButton5.setBackground(new java.awt.Color(51, 255, 255));
        jButton5.setText("ExportMark");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(679, 679, 679)
                .addComponent(jButton5)
                .addContainerGap(695, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 604, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(260, 260, 260)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnLogout)
                        .addGap(64, 64, 64))
                    .addComponent(lbInfo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 21, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnLogout))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(10, 10, 10)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        // TODO add your handling code here:
        int click = JOptionPane.showConfirmDialog(null, "Do you want logout?");
        if (click == JOptionPane.YES_OPTION) {
            LoginForm home = new LoginForm();
            home.setVisible(true);
            dispose();
        }
        if (click == JOptionPane.NO_OPTION) {

        }
        if (click == JOptionPane.CANCEL_OPTION) {

        }
        if (click == JOptionPane.CLOSED_OPTION) {

        }
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        try {
            Home home = new Home(account);
            home.setVisible(true);
            this.dispose();
        } catch (ParseException ex) {
            Logger.getLogger(StudentMark.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void btnViewMarlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewMarlActionPerformed
        // TODO add your handling code here:
        String SemsterID;
        if (cbbSemester.getSelectedItem().toString().equals("Choose Semester")) {
            SemsterID = null;
            ArrayList<StudentMarkModel> markList = new ArrayList<StudentMarkModel>();
            markList = studentDao.getListStudentMarkByMSSV(MSSV);

            LoadListMark(markList);

            return;

        } else {
            SemsterID = cbbSemester.getSelectedItem().toString().split("-")[0];

        }

        ArrayList<StudentMarkModel> markList = new ArrayList<StudentMarkModel>();
        markList = studentDao.getListStudentMarkByMSSVAndSemester(MSSV, Integer.parseInt(SemsterID));

        LoadListMark(markList);


    }//GEN-LAST:event_btnViewMarlActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        PDFExport export = new PDFExport();

        try {
            export.ExportPDF(MSSV);
            JOptionPane.showMessageDialog(this, "Export success!");
            return;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Something error!");
            return;
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(StudentMark.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StudentMark.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StudentMark.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StudentMark.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StudentMark().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnViewMarl;
    private javax.swing.JComboBox cbbSemester;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbInfo;
    private javax.swing.JTable tableMark;
    // End of variables declaration//GEN-END:variables

}
