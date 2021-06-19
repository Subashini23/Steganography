
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author LENOVO
 */
public class Decrypt extends javax.swing.JFrame {

    /**
     * Creates new form Decrypt
     */
    private File fcCurDir;
    private Path stegPath;
    String msgPath;
    public static String b_msg="";
    public static int len = 0;
    
    public Decrypt() {
        initComponents();
        setSize(1200,650);
        fcCurDir=null;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        imgTf = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        password = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("REVEAL");
        getContentPane().setLayout(null);

        jButton1.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        jButton1.setText("OK");
        jButton1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(160, 250, 63, 29);

        jButton2.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        jButton2.setText("HOME");
        jButton2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(280, 250, 69, 30);

        jLabel1.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("      IMAGE");
        jLabel1.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        jLabel1.setMaximumSize(new java.awt.Dimension(91, 19));
        jLabel1.setMinimumSize(new java.awt.Dimension(91, 19));
        getContentPane().add(jLabel1);
        jLabel1.setBounds(90, 100, 109, 35);
        getContentPane().add(imgTf);
        imgTf.setBounds(240, 100, 148, 30);

        jButton3.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 10)); // NOI18N
        jButton3.setText("UPLOAD");
        jButton3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3);
        jButton3.setBounds(420, 100, 67, 31);

        jLabel2.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("  PASSWORD");
        jLabel2.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        getContentPane().add(jLabel2);
        jLabel2.setBounds(90, 160, 109, 35);
        getContentPane().add(password);
        password.setBounds(240, 160, 148, 30);

        jLabel3.setIcon(new javax.swing.ImageIcon("D:\\project\\IMG-20190831-WA0015.jpg")); // NOI18N
        getContentPane().add(jLabel3);
        jLabel3.setBounds(0, 0, 1210, 610);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        new Home().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String pass = password.getText();
        BufferedImage yImage=readImageFile(stegPath);
          
        DecodeTheMessage(yImage);
        String msg="";
        //System.out.println("len is "+len*8);
        for(int i=0;i<len*8;i=i+8){

                String sub=b_msg.substring(i,i+8);

                int m=Integer.parseInt(sub,2);
                char ch=(char) m;
        //	System.out.println("m "+m+" c "+ch);
                msg+=ch;

        }
        if(!msg.substring(msg.length()-4).equals(pass))
        {
            JFrame frame = new JFrame("Message");
            JOptionPane.showMessageDialog(frame,"Enter Correct Password");
        }
        else
        {

        msg = msg.substring(0,msg.length()-4);
        PrintWriter out = null;
        msgPath =stegPath.getParent()+"\\Msg.txt";
        try {
            out = new PrintWriter(new FileWriter(msgPath, true), true);
        } catch (IOException ex) {
            Logger.getLogger(Decrypt.class.getName()).log(Level.SEVERE, null, ex);
        }
        out.write(msg);
        out.close();
        JFrame frame = new JFrame("Message");
        JOptionPane.showMessageDialog(frame,"Message Decoded at "+msgPath);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        String[] images = new String[] {".png",".jpg",".jpeg",".bmp",".gif"};
				JFileChooser fc = new JFileChooser();
				fc.setCurrentDirectory(fcCurDir);
				fc.setDialogTitle("Choose an Image");
				fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				fc.setFileFilter(new FilterTheFiles("Image Files",images));//File type - Image files(1st parameter)
				if(fc.showOpenDialog(null)==JFileChooser.APPROVE_OPTION) { //Selects the particular file and displays in file name
					imgTf.setText(fc.getSelectedFile().getAbsolutePath().toString());
                                        stegPath = Paths.get(imgTf.getText());
					//log("Top image selected: \""+fc.getSelectedFile().toString()+"\"");
				}
				fcCurDir = fc.getCurrentDirectory();
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(Decrypt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Decrypt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Decrypt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Decrypt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Decrypt().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField imgTf;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPasswordField password;
    // End of variables declaration//GEN-END:variables

    private BufferedImage readImageFile(Path stegPath) {
        BufferedImage theImage = null;
File p = new File (stegPath.toString());
try{
theImage = ImageIO.read(p);
}catch (IOException e){
e.printStackTrace();
System.exit(1);
}
return theImage;
    }

    private void DecodeTheMessage(BufferedImage yImage) {
        int j=0;
int currentBitEntry=0;
String bx_msg="";
for (int x = 0; x < yImage.getWidth(); x++){
for ( int y = 0; y < yImage.getHeight(); y++){
if(x==0&&y<8){
	//System.out.println("enc "+yImage.getRGB(x, y)+" dec "+yImage.getRGB(x, y)+" "+b_msg);
	int currentPixel = yImage.getRGB(x, y);	
	int red = currentPixel>>16;
	red = red & 255;
	int green = currentPixel>>8;
	green = green & 255;
	int blue = currentPixel;
	blue = blue & 255;
	String x_s=Integer.toBinaryString(blue);
	bx_msg+=x_s.charAt(x_s.length()-1);
	len=Integer.parseInt(bx_msg,2);
	
}
else if(currentBitEntry<len*8){
//System.out.println("enc "+yImage.getRGB(x, y)+" dec "+yImage.getRGB(x, y)+" "+b_msg);
	int currentPixel = yImage.getRGB(x, y);	
	int red = currentPixel>>16;
	red = red & 255;
	int green = currentPixel>>8;
	green = green & 255;
	int blue = currentPixel;
	blue = blue & 255;
	String x_s=Integer.toBinaryString(blue);
	b_msg+=x_s.charAt(x_s.length()-1);

	
	currentBitEntry++;	
	//System.out.println("curre "+currentBitEntry);
}
}
}
//System.out.println("bin value of msg hided in img is "+b_msg);
}
    }

