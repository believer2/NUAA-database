// Login.java
package StudentInfo;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login implements ActionListener {
    // 定义主窗口
    private final JFrame jf;
    // 定义输入用户名和密码的标签提示
    private final JLabel InputUserName;
    private final JLabel InputPassWord;
    // 定义输入用户名文本框
    private final JTextField UserName;
    // 定义输入密码框
    private final JPasswordField PassWord;
    // 定义登录和取消按钮
    private final JButton Login;
    private final JButton Cancel;
    private final JButton Switch;

    // 定义一个数据库操作的实例
    private OperationMySql db = null;
    //获取学生信息便于之后显示
    public static String studentName;
    public static String studentID;
    public static String studentClass;
    public static String studentChinese;
    public static String studentMath;
    public static String studentEnglish;
    public static String studentpassword;

    Login() {
        // 各组件实例化过程
        jf = new JFrame("登录opengauss");
        InputUserName = new JLabel("用户名：");
        InputPassWord = new JLabel("密  码：");
        UserName = new JTextField();
        PassWord = new JPasswordField();
        Login = new JButton("登录");
        Cancel = new JButton("退出");
        Switch = new JButton("切换至教师登录界面");

        // 设置主窗口大小、位置和布局
        jf.setSize(600, 200);
        jf.setLocation(300, 300);
        // 设置窗口流式布局
        jf.setLayout(new FlowLayout());
        // 设置用户名和密码框大小
        UserName.setPreferredSize(new Dimension(500, 30));
        PassWord.setPreferredSize(new Dimension(500, 30));
        // 依次向主窗口添加各组件
        jf.getContentPane().add(InputUserName);
        jf.getContentPane().add(UserName);
        jf.getContentPane().add(InputPassWord);
        jf.getContentPane().add(PassWord);
        jf.getContentPane().add(Login);
        jf.getContentPane().add(Cancel);
        jf.getContentPane().add(Switch);
        // 设置主窗口不可调节大小
        jf.setResizable(false);
        // 设置主窗口默认关闭操作
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 给登录、取消转换按钮添加 Action 监听器
        Login.addActionListener(this);
        Cancel.addActionListener(this);
        Switch.addActionListener(this);
        // 设置主窗口可见
        jf.setVisible(true);
    }



    @Override

    public void actionPerformed(ActionEvent e) {
        db=new OperationMySql();
        // 连接数据库
        db.setDburl("jdbc:postgresql://124.70.69.227:26000/studentinfo?ApplicationName=app1");
        // 加载驱动
        db.setDbdriver("org.postgresql.Driver");
        // 这里的用户名和密码是要和你的数据库对应的，也是唯一需要更改的地方
        db.setUsername("dbuser");db.setPassword("Gauss#3demo");
        // 如果单击【退出】按钮则程序退出
        if (e.getSource().equals(Cancel)) {
            System.exit(0);
        } else if (e.getSource().equals(Switch)) {
              jf.dispose();
        //    jf.setVisible(false);
            new teacherLogin();
        }
        // 如果单击【登录】按钮则检查用户名和密码是否匹配
        else if (e.getSource().equals(Login)) {

            // 如果用户名和密码匹配，则打开具体操作面板
            String sname = String.valueOf(UserName.getText());
            String Mi = String.valueOf(PassWord.getPassword());

            try {
                db.setRs(db.executeQuery(sname));
                //System.out.println("到这");
                if(!db.getRs().next())
                {
                    JOptionPane.showOptionDialog(jf, "用户名不存在", "登陆失败", JOptionPane.CLOSED_OPTION,
                            JOptionPane.ERROR_MESSAGE, null, null, null);
                }
                else if(Mi.equals(String.valueOf(db.getRs().getString(7))) == false)
                {
                       JOptionPane.showOptionDialog(jf, "密码错误", "登陆失败", JOptionPane.CLOSED_OPTION,
                            JOptionPane.ERROR_MESSAGE, null, null, null);
                }
                else
                {//获取学生信息
                    studentName = String.valueOf(db.getRs().getString(2));
                    studentID = String.valueOf(db.getRs().getString(1));
                    studentClass = String.valueOf(db.getRs().getString(3));
                    studentChinese = String.valueOf(db.getRs().getString(4));
                    studentMath = String.valueOf(db.getRs().getString(5));
                    studentEnglish = String.valueOf(db.getRs().getString(6));
                    studentpassword = String.valueOf(db.getRs().getString(7));
                    jf.dispose();
                    new studentGUI();
                    
                    System.out.println("打开学生界面");
                }

            } catch (Exception exception) {
                exception.printStackTrace();

            } finally {
                db.CloseRS();
                db.CloseStmt();
                db.CloseConnection();
            }
     /*       if (UserName.getText().equals("admin") && String.valueOf(PassWord.getPassword()).equals("1234")) {
                // MySQLGUI myS = new MySQLGUI();
                // myS.initial();
                new MySQLGUI();
                jf.setVisible(false);
                jf.dispose();
            }
            // 如果用户名和密码不匹配，则给出提示对话框
            else {
                JOptionPane.showOptionDialog(jf, "用户名或密码错误", "登陆失败",
                        JOptionPane.CLOSED_OPTION,
                        JOptionPane.ERROR_MESSAGE, null, null, null);
            }*/
        }
    }
}