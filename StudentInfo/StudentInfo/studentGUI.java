package StudentInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;


public class studentGUI implements ActionListener {
	// 定义主窗口
    private final JFrame jf;
    //定义输出各个信息的标签提示
    private JLabel SName;
    private JLabel SID;
    private JLabel SClass;
    private JLabel SChinese;
    private JLabel SMath;
    private JLabel SEnglish;
    private JLabel STotal;
    private JLabel STitle = null;
    private JLabel Rank;

    /*
     * // 定义输入用户名和密码的标签提示 private final JLabel InputUserName; private final JLabel
     * InputPassWord; // 定义输入用户名文本框 private final JTextField UserName; // 定义输入密码框
     * private final JPasswordField PassWord;
     */

    // 定义更改密码按钮
    private final JButton ChangePassword;
    private final JButton Cancel;
    // private final JButton Switch;

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

    studentGUI() {
            setDB();
            jf = new JFrame("你好" + Login.studentName);
            jf.setLayout(null);
            JPanel jp = (JPanel) jf.getContentPane();
            JRootPane jp1 = (JRootPane) jf.getRootPane();
            jp.setOpaque(false);
            jp1.setOpaque(false);

            // 各组件实例化过程
            // JLayeredPane pane = new JLayeredPane();
            /*
             * ImageIcon image; image = new ImageIcon("images.jpg"); JLabel lbBg = new
             * JLabel(image); lbBg.setBounds(0, 0,186,272);
             */
            URL url = this.getClass().getResource("images.jpg");
            ImageIcon icon = new ImageIcon(url);
            final JLabel labelBackground = new JLabel();
            ImageIcon im = icon;
            labelBackground.setIcon(im);
            // 设置label的大小
            labelBackground.setBounds(0, 0, im.getIconWidth(), im.getIconHeight());
            // 将背景图片标签放入面板的最底层

            // 将容器转换为面板设置为透明
            // JPanel panel = (JPanel) container;

            STitle = new JLabel("成绩单");
            STitle.setFont(new Font("楷体", 1, 40));
            STitle.setBackground(Color.GREEN);
            STitle.setBounds(200, 40, 200, 100);

            SName = new JLabel("姓 名：");
            SName.setFont(new Font("楷体", 1, 22));
            SName.setBackground(Color.GREEN);
            SName.setBounds(30, 160, 120, 30);

            SID = new JLabel("学 号：");
            SID.setFont(new Font("楷体", 1, 22));
            SID.setBackground(Color.GREEN);
            SID.setBounds(210, 160, 120, 30);

            SClass = new JLabel("班 级：");
            SClass.setFont(new Font("楷体", 1, 22));
            SClass.setBackground(Color.GREEN);
            SClass.setBounds(380, 160, 120, 30);

            SChinese = new JLabel("语 文：");
            SChinese.setFont(new Font("楷体", 1, 22));
            SChinese.setBackground(Color.GREEN);
            SChinese.setBounds(90, 230, 120, 50);

            SMath = new JLabel("数 学：");
            SMath.setFont(new Font("楷体", 1, 22));
            SMath.setBackground(Color.GREEN);
            SMath.setBounds(90, 290, 120, 50);

            SEnglish = new JLabel("英 语：");
            SEnglish.setFont(new Font("楷体", 1, 22));
            SEnglish.setBackground(Color.GREEN);
            SEnglish.setBounds(90, 350, 120, 50);

            STotal = new JLabel("总 分：");
            STotal.setFont(new Font("楷体", 1, 22));
            STotal.setBackground(Color.GREEN);
            STotal.setBounds(90, 410, 120, 50);
            /*
             * InputUserName = new JLabel("用户名："); InputPassWord = new JLabel("密码：");
             * UserName = new JTextField(); PassWord = new JPasswordField(); login = new
             * JButton("登录"); Cancel = new JButton("退出"); Switch = new JButton("切换至教师登录界面");
             */
            jf.getLayeredPane().add(labelBackground, new Integer(Integer.MIN_VALUE));
            jf.add(STitle);
            jf.add(SName);
            jf.add(SID);
            jf.add(SClass);
            jf.add(SChinese);
            jf.add(SMath);
            jf.add(SEnglish);
            jf.add(STotal);
            // -------------------------------------------------------------
            SName = new JLabel(Login.studentName);
            SName.setFont(new Font("楷体", 1, 22));
            SName.setBackground(Color.GREEN);
            SName.setBounds(100, 160, 120, 30);

            SID = new JLabel(Login.studentID);
            SID.setFont(new Font("楷体", 1, 22));
            SID.setBackground(Color.GREEN);
            SID.setBounds(300, 160, 120, 30);

            SClass = new JLabel(Login.studentClass);
            SClass.setFont(new Font("楷体", 1, 22));
            SClass.setBackground(Color.GREEN);
            SClass.setBounds(470, 160, 120, 30);

            SChinese = new JLabel(Login.studentChinese);
            SChinese.setFont(new Font("楷体", 1, 22));
            SChinese.setBackground(Color.GREEN);
            SChinese.setBounds(190, 230, 120, 50);

            SMath = new JLabel(Login.studentMath);
            SMath.setFont(new Font("楷体", 1, 22));
            SMath.setBackground(Color.GREEN);
            SMath.setBounds(190, 290, 120, 50);

            SEnglish = new JLabel(Login.studentEnglish);
            SEnglish.setFont(new Font("楷体", 1, 22));
            SEnglish.setBackground(Color.GREEN);
            SEnglish.setBounds(190, 350, 120, 50);

            int studentTotal = Integer.parseInt(Login.studentChinese) + Integer.parseInt(Login.studentMath)
                            + Integer.parseInt(Login.studentEnglish);
            STotal = new JLabel(String.valueOf(studentTotal));
            STotal.setFont(new Font("楷体", 1, 22));
            STotal.setBackground(Color.GREEN);
            STotal.setBounds(190, 410, 120, 50);
            jf.add(SName);
            jf.add(SID);
            jf.add(SClass);
            jf.add(SChinese);
            jf.add(SMath);
            jf.add(SEnglish);
            jf.add(STotal);
// --------------------------------------------------------------
        Rank = new JLabel("排 名：");
        Rank.setFont(new Font("楷体", 1, 22));
        Rank.setBackground(Color.GREEN);
        Rank.setBounds(280, 230, 120, 50);
        jf.add(Rank);
        Rank = new JLabel("排 名：");
        Rank.setFont(new Font("楷体", 1, 22));
        Rank.setBackground(Color.GREEN);     
        Rank.setBounds(280, 290, 120, 50);
        jf.add(Rank);
        Rank = new JLabel("排 名：");
        Rank.setFont(new Font("楷体", 1, 22));
        Rank.setBackground(Color.GREEN);
        Rank.setBounds(280, 350, 120, 50);
        jf.add(Rank);
        Rank = new JLabel("排 名：");
        Rank.setFont(new Font("楷体", 1, 22));
        Rank.setBackground(Color.GREEN);
        Rank.setBounds(280, 410, 120, 50);
        jf.add(Rank);
//------------------------------------------------------------------
        int i = 0;
        int chinese_num = 1;
        int math_num = 1;
        int english_num = 1;
        int sum_num = 1;

        System.out.println(Integer.parseInt(Login.studentClass));
        try {
                db.setRs(db.executeQueryByGrade(Integer.parseInt(Login.studentClass)));
                int chinese = Integer.parseInt(Login.studentChinese);
                int math = Integer.parseInt(Login.studentMath);
                int english = Integer.parseInt(Login.studentEnglish);
                int sum = chinese+math+english;

                System.out.println("开始计算排名");
                while (db.getRs().next())
                {
                        i++;
                        if(db.getRs().getInt(4) > chinese)
                        {
                                chinese_num++;
                        }
                        if(db.getRs().getInt(5) > math)
                        {
                                math_num++;
                        }
                        if(db.getRs().getInt(6) > english)
                        {
                                english_num++;
                        }
                        int sum_select = db.getRs().getInt(4)+db.getRs().getInt(5)+db.getRs().getInt(6);
                        if(sum_select>sum)
                        {
                                sum_num++;
                        }

                }
                }
                catch (Exception exception) {
                       exception.printStackTrace();
                } 
                finally {
                       db.CloseRS();
                        db.CloseStmt();
                        db.CloseConnection();
                }
                System.out.println("计算排名结束");
                Rank = new JLabel(String.valueOf(chinese_num) + "/"+ String.valueOf(i));
                Rank.setFont(new Font("楷体", 1, 22));
                Rank.setBackground(Color.GREEN);
                Rank.setBounds(380, 230, 120, 50);
                jf.add(Rank);
                Rank = new JLabel(String.valueOf(math_num) + "/" + String.valueOf(i));
                Rank.setFont(new Font("楷体", 1, 22));
                Rank.setBackground(Color.GREEN);
                Rank.setBounds(380, 290, 120, 50);
                jf.add(Rank);
                Rank = new JLabel(String.valueOf(english_num) + "/"+ String.valueOf(i));
                Rank.setFont(new Font("楷体", 1, 22));
                Rank.setBackground(Color.GREEN);
                Rank.setBounds(380, 350, 120, 50);
                jf.add(Rank);
                Rank = new JLabel(String.valueOf(sum_num) + "/"+ String.valueOf(i));
                Rank.setFont(new Font("楷体", 1, 22));
                Rank.setBackground(Color.GREEN);
                Rank.setBounds(380, 410, 120, 50);
                jf.add(Rank);
//--------------------------------------------------------------------------------------------
        ChangePassword = new JButton("更改密码");
        ChangePassword.setFont(new Font("宋体", 1, 20)); // 1 代表加粗，20 代表字体大小
        ChangePassword.setBackground(Color.ORANGE);
        ChangePassword.setBounds(100, 500, 100, 45);
        ChangePassword.setMargin(new Insets(0, 0, 0, 0)); // 设置按钮的边缘空白为四个方向全为0，也即让按钮中的文本与按钮边缘贴齐
        Cancel = new JButton("退出界面");
        Cancel.setFont(new Font("宋体", 1, 20));
        Cancel.setBackground(Color.ORANGE);
        Cancel.setBounds(320, 500, 100, 45);
        Cancel.setMargin(new Insets(0, 0, 0, 0));
        jf.add(ChangePassword);
        jf.add(Cancel);

        // 设置主窗口大小、位置和布局
        jf.setSize(550,650);
        jf.setLocation(350, 50);
        
        // 设置窗口流式布局
//        jf.setLayout(new FlowLayout());
/*        // 设置用户名和密码框大小
        UserName.setPreferredSize(new Dimension(500, 30));
        PassWord.setPreferredSize(new Dimension(500, 30));*/
        // 依次向主窗口添加各组件
/*        jf.getContentPane().add(InputUserName);
        jf.getContentPane().add(UserName);
        jf.getContentPane().add(InputPassWord);
        jf.getContentPane().add(PassWord);
        jf.getContentPane().add(login);
        jf.getContentPane().add(Cancel);
        jf.getContentPane().add(Switch);*/
        // 设置主窗口不可调节大小
        jf.setResizable(false);
        // 设置主窗口默认关闭操作
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 给登录和取消按钮添加 Action 监听器
//        login.addActionListener(this);
        ChangePassword.addActionListener(this);
        Cancel.addActionListener(this);
        // 设置主窗口可见
        jf.setVisible(true);
    }

    @Override
	public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(Cancel)) {
                 System.exit(0);
                } else if (e.getSource().equals(ChangePassword)) {
                        //jf.dispose();
                        jf.setVisible(false);
                        new changepasswordGUI();
        }
	}
}
