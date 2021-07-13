package StudentInfo;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class changepasswordGUI implements ActionListener {
	// 定义主窗口
	private final JFrame jf;
	// 定义输入用户名和密码的标签提示
	private final JLabel Inputorigin;
	private final JLabel Inputnew;
	private final JLabel Inputnew2;
	// 定义输入密码框
	private final JTextField originPassWord;
	private final JPasswordField newPassWord;
	private final JPasswordField newPassWord2;
	// 定义登录和取消按钮
	private final JButton Change;
	private final JButton Cancel;
	// 定义一个数据库操作的实例
	private OperationMySql db = null;
        
        private void setDB() {
            db = new OperationMySql();
            // 连接 mysql
            db.setDburl("jdbc:postgresql://124.70.69.227:26000/studentinfo?ApplicationName=app1");
            // 加载驱动
            db.setDbdriver("org.postgresql.Driver");
            // 这里的用户名和密码是要和你的 mysql 对应的，也是唯一需要更改的地方
            db.setUsername("dbuser");
            db.setPassword("Gauss#3demo");
    }
	changepasswordGUI() {
        // 各组件实例化过程
        setDB();
        jf = new JFrame("欢迎来到更改密码");
        Inputorigin = new JLabel("请输入你的原密码：");
        Inputnew    = new JLabel("请输入你的新密码：");
	Inputnew2   = new JLabel("请再次输入新密码：");
        originPassWord = new JTextField();
        newPassWord = new JPasswordField();
	newPassWord2 = new JPasswordField();
        Change = new JButton("更改密码");
        Cancel = new JButton("退出界面");

        // 设置主窗口大小、位置和布局
        jf.setSize(700, 200);
        jf.setLocation(300, 300);
        // 设置窗口流式布局
        jf.setLayout(new FlowLayout());
        // 设置用户名和密码框大小
        originPassWord.setPreferredSize(new Dimension(500, 30));
        newPassWord.setPreferredSize(new Dimension(500, 30));
	newPassWord2.setPreferredSize(new Dimension(500, 30));
        // 依次向主窗口添加各组件
        jf.getContentPane().add(Inputorigin);
        jf.getContentPane().add(originPassWord);
        jf.getContentPane().add(Inputnew);
        jf.getContentPane().add(newPassWord);
        jf.getContentPane().add(Inputnew2);
        jf.getContentPane().add(newPassWord2);
        jf.getContentPane().add(Change);
        jf.getContentPane().add(Cancel);
        // 设置主窗口不可调节大小
        jf.setResizable(false);
        // 设置主窗口默认关闭操作
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 给登录、取消转换按钮添加 Action 监听器
        Change.addActionListener(this);
        Cancel.addActionListener(this);
        // 设置主窗口可见
        jf.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(Cancel)) {
                   //System.exit(0);
                   jf.dispose();
                   new Login();
            } else if (e.getSource().equals(Change)) {
                    String origin = String.valueOf(originPassWord.getText());
                    String New = String.valueOf(newPassWord.getPassword());
                    String New2 = String.valueOf(newPassWord2.getPassword());
                    if((origin.equals(Login.studentpassword)) == false)
                    {
                           // System.out.println(origin);
                           // System.out.println(Login.studentpassword);
                            JOptionPane.showOptionDialog(jf, "密码错误", "请重新输入", JOptionPane.CLOSED_OPTION,
                                            JOptionPane.ERROR_MESSAGE, null, null, null);
                    }
                    else{
                        if((New.equals(New2)) == false)
                         {
                                  JOptionPane.showOptionDialog(jf, "两次新密码输入不同", "请重新输入", JOptionPane.CLOSED_OPTION,
                                                   JOptionPane.ERROR_MESSAGE, null, null, null);
                        }
                        else{
                                try {

                                        db.ChangePassword(Login.studentID,New);
                                        JOptionPane.showOptionDialog(jf, "修改成功！", "数据库操作提示", -1, 1, null, null, null);
                                    } catch (Exception exception) {
                                            exception.printStackTrace();
                                    } finally {
                                            //System.out.println("zheli");
                                            db.CloseRS();

                                            db.CloseStmt();
                                            db.CloseConnection();
                                        }
                                }        
                        }
                }
    }
}