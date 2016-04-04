package mongod;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class MF extends JFrame implements ActionListener{
	
	
	private Label label1,label_1,label_2;
	private JPanel panel1,panel2;
	private JTextField text1;
	private JPasswordField textpass;
	private JButton button,button1,button2;
	private Dialog dialog;
	private Label label;
	public MF()
	{

   	 this.setTitle("登录窗口");
   	 this.setSize(400, 200);
   	 this.setLocationRelativeTo(null);
   	 this.setLayout(new GridLayout(3,1));   	 
   	 label1=new Label("登录界面");
   	 label1.setAlignment(Label.CENTER);
   	 label1.setFont(new Font("宋体",Font.ITALIC,18 ));
   	 this.add(label1);
   	 panel1=new JPanel();
   	 panel1.setLayout(new GridLayout(2,3));
   	 label_1=new Label("用户ID:");
   	 label_1.setAlignment(Label.RIGHT);
   	 panel1.add(label_1);
   	 text1=new JTextField(1);
   	 panel1.add(text1);
   	 panel1.add(new Panel());
   	 
   	 label_2=new Label("密     码:");
   	 label_2.setAlignment(Label.RIGHT);
   	 panel1.add(label_2);
   	 textpass=new JPasswordField();
   	 textpass.setEchoChar('*');
   	 panel1.add(textpass);	
   	 panel1.add(new Panel());
   	 
   	 panel2=new JPanel();
   	 button1=new JButton("登录");
   	 button2=new JButton("退出");
   	 panel2.add(button1);
   	 panel2.add(button2);
   	 this.add(panel1);
   	 this.add(panel2);
   	 //this.setVisible(true);
   	 button1.addActionListener(this);
   	 button2.addActionListener(this);
   	 
       dialog=new Dialog(this,"警告");
       dialog.setLayout(new BorderLayout());
    	dialog.setSize(250, 150);
    	dialog.setLocationRelativeTo(null);
    	 label=new Label();
    	label.setAlignment(Label.CENTER);
    	dialog.add(label,BorderLayout.CENTER);
       button=new JButton("确定");
    	dialog.add(button,BorderLayout.SOUTH);
    	button.addActionListener(this);

    	this.setVisible(true);
    
	}
	
	
	 public void actionPerformed(ActionEvent e)
	    {
		  if(e.getSource()==button1)
		  {
			  Mongo mg=new Mongo();
			  boolean bl=mg.mongod();
			  if(bl)
			  {
				  text1.setText("chenggong");
			  }
		  }
	    }
	
	
	
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
          new MF();
	}

}
