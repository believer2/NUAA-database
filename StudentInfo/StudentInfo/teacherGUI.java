// teacherGUI.java
package StudentInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class teacherGUI extends JFrame implements MouseListener, ItemListener {
    /**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private static final String NULL = null;
    private static int InsertStuClass = 0;
    private static int InsertStuChinese = 0;
    private static int InsertStuMath = 0;
    private static int InsertStuEnglish = 0;
    // 定义选项卡
    private JTabbedPane Base;
    // 定义选项卡上的嵌板
    /*
     * jp1, 添加记录 jp2, 删除记录 jp3, 更新记录 jp4, 查找记录 jp5, 选课记录 jp6 课程平均分
     */
    private JPanel jp1, jp2, jp3, jp4, jp5, jp6;
    // 定义各按钮
    /*
     * InsertRecord, 添加记录按钮 InsertReset, 添加取消按钮 DeleteRecord, 删除记录按钮 DeleteReset,
     * 删除取消按钮 QueryRecord, 查询记录按钮 UpdateRecord, 更改记录按钮 UpdateReset, 重置更新框
     * CourseQuery, 选课表查询按钮 GradeQuery, 成绩查询按钮
     */
    private JButton InsertRecord, InsertReset, DeleteRecord, DeleteReset, QueryRecord, UpdateRecord, UpdateReset,
            CourseQuery, GradeQuery;
    // 定义各标签
    /*
     * InsertID1, 插入学号提示标签 InsertName1, 插入姓名提示标签 InsertChinese1, 插入语文成绩提示标签
     * InsertMath1, 插入数学提示标签 InsertEnglish1, 插入英语提示标签 DeleteID1, 删除学号提示标签 UpdateID1,
     * 更新学号提示标签 Tips, 提示标签
     */
    private JLabel InsertID1, InsertName1, InsertChinese1, InsertMath1, InsertEnglish1, DeleteID1, UpdateID1, Tips,
            InsertClass1,Tip2;
    // 定义各文本框
    /*
     * InsertID2, 插入学号文本框 InsertName2, 插入姓名文本框 InsertChinese2, 插入语文文本框 InsertMath2,
     * 插入数学文本框 InsertEnglish2, 插入英语文本框 DeleteID2, 所要删除学号的文本框 UpdateID2, 所要更新学号的文本框
     * UpdateContent, 更新内容填写文本框 IDCondition, 查询ID文本框 NameCondition, 查询姓名文本框
     * ChineseCondition,查询语文文本框 MathCondition, 查询数学文本框 EnglishCondition,查询英语文本框
     */
    private JTextField InsertID2, InsertName2, InsertChinese2, InsertMath2, InsertEnglish2, InsertClass2, DeleteID2,
            UpdateID2, UpdateContent, IDCondition,ClassCondition, NameCondition, ChineseCondition, MathCondition, EnglishCondition;
    // 定义显示结果文本域 显示 jp4 jp5 jp6 的查询结果
    /*
     * QueryRecordResult, 查询学生信息结果文本域 CourseQueryResult, 查询课程信息文本域 GradeQueryResult,
     * 查询课程成绩平均分文本域
     */
    private JTextArea QueryRecordResult, CourseQueryResult, GradeQueryResult;
    // 定义查询选项
    /*
     * ID, 选择学号查询 Name, 选择姓名查询  Class ,选择班级查询 Chinese, 选择语文查询 Math, 选择数学查询 English, 选择英语查询
     */
    private JRadioButton ID, Name,Class ,Chinese, Math, English;
    // 定义一个数据库操作的实例
    private OperationMySql db = null;
    // 定义滚动条
    private JScrollPane scroll = null;
    private JScrollPane CourseScroll = null;
    private JScrollPane GradeScroll = null;
    // 定义一个复选框用于选择更新的项目
    private JComboBox<String> UpdateItem = null;
    // 定义复选框用于选择查询的项目
    private JComboBox<String> CourseItem = null; // 课程信息复选框
    private JComboBox<String> GradeItem = null; // 课程成绩复选框

    teacherGUI() {
        // 设置各按钮信息
        setButton();
        // 设置各标签信息
        setLabel();
        // 设置各文本框信息
        setTextField();
        // 设置各面板信息
        setPanel();
        // 设置布局信息
        setLayout();
        // 设置选项卡信息
        setBase();
        // 设置主窗口信息
        setThis();
        // 设置数据库信息
        setDB();
    }

    // 设置各按钮信息的方法
    private void setButton() {
        // jp1 上的按钮
        InsertRecord = new JButton("添加");
        InsertRecord.setFont(new Font("宋体", 1, 20)); // 1 代表加粗，20 代表字体大小
        InsertRecord.setBackground(Color.CYAN);
        InsertRecord.setBounds(150, 420, 100, 45);
        InsertRecord.setMargin(new Insets(0, 0, 0, 0)); // 设置按钮的边缘空白为四个方向全为0，也即让按钮中的文本与按钮边缘贴齐
        InsertReset = new JButton("重置");
        InsertReset.setFont(new Font("宋体", 1, 20));
        InsertReset.setBackground(Color.CYAN);
        InsertReset.setBounds(300, 420, 100, 45);
        InsertReset.setMargin(new Insets(0, 0, 0, 0));
        // jp2 上的按钮
        DeleteRecord = new JButton("删除信息");
        DeleteRecord.setFont(new Font("宋体", 1, 20));
        DeleteRecord.setBackground(Color.CYAN);
        DeleteRecord.setBounds(150, 350, 100, 45);
        DeleteRecord.setMargin(new Insets(0, 0, 0, 0));
        DeleteReset = new JButton("重置");
        DeleteReset.setFont(new Font("宋体", 1, 20));
        DeleteReset.setBackground(Color.CYAN);
        DeleteReset.setBounds(300, 350, 100, 45);
        DeleteReset.setMargin(new Insets(0, 0, 0, 0));
        // jp3 上的按钮
        UpdateRecord = new JButton("更新");
        UpdateRecord.setFont(new Font("宋体", 1, 20));
        UpdateRecord.setBackground(Color.CYAN);
        UpdateRecord.setBounds(250, 400, 100, 45);
        UpdateReset = new JButton("重置");
        UpdateReset.setFont(new Font("宋体", 1, 20));
        UpdateReset.setBackground(Color.CYAN);
        UpdateReset.setBounds(400, 400, 100, 45);
        // jp4 上的按钮
        ID = new JRadioButton("学号");
        ID.setFont(new Font("宋体", 1, 15));
        ID.setMargin(new Insets(0, 0, 0, 0));
        ID.setBounds(30, 300, 55, 20);
        Name = new JRadioButton("姓名");
        Name.setFont(new Font("宋体", 1, 15));
        Name.setMargin(new Insets(0, 0, 0, 0));
        Name.setBounds(30, 330, 55, 20);
        Class = new JRadioButton("班级");
        Class.setFont(new Font("宋体", 1, 15));
        Class.setMargin(new Insets(0, 0, 0, 0));
        Class.setBounds(30, 360, 55, 20);
        Chinese = new JRadioButton("语文");
        Chinese.setFont(new Font("宋体", 1, 15));
        Chinese.setMargin(new Insets(0, 0, 0, 0));
        Chinese.setBounds(30, 390, 55, 20);
        Math = new JRadioButton("数学");
        Math.setFont(new Font("宋体", 1, 15));
        Math.setMargin(new Insets(0, 0, 0, 0));
        Math.setBounds(30, 420, 55, 20);
        English = new JRadioButton("英语");
        English.setFont(new Font("宋体", 1, 15));
        English.setMargin(new Insets(0, 0, 0, 0));
        English.setBounds(30, 450, 55, 20);
        QueryRecord = new JButton("查询");
        QueryRecord.setFont(new Font("宋体", 1, 20));
        QueryRecord.setBackground(Color.CYAN);
        QueryRecord.setBounds(600, 400, 80, 45);
        // jp5 上的按钮
        CourseQuery = new JButton("查询");
        CourseQuery.setFont(new Font("宋体", 1, 20));
        CourseQuery.setBackground(Color.CYAN);
        CourseQuery.setBounds(600, 400, 80, 45);
        // jp6 上的按钮
        GradeQuery = new JButton("查询");
        GradeQuery.setFont(new Font("宋体", 1, 20));
        GradeQuery.setBackground(Color.PINK);
        GradeQuery.setBounds(600, 400, 80, 45);
        // 按键监听初始化
        initial();
    }

    // 设置各标签信息的方法
    private void setLabel() {
        // jp1 上的标签
        InsertID1 = new JLabel("学    号：");
        InsertID1.setFont(new Font("楷体", 1, 22));
        InsertID1.setBackground(Color.GREEN);
        InsertID1.setBounds(100, 20, 120, 50);
        InsertName1 = new JLabel("姓    名：");
        InsertName1.setFont(new Font("楷体", 1, 22));
        InsertName1.setBackground(Color.GREEN);
        InsertName1.setBounds(100, 80, 120, 50);

        InsertClass1 = new JLabel("班    级");
        InsertClass1.setFont(new Font("楷体", 1, 22));
        InsertClass1.setBackground(Color.GREEN);
        InsertClass1.setBounds(100, 140, 120, 50);

        InsertChinese1 = new JLabel("语文成绩：");
        InsertChinese1.setFont(new Font("楷体", 1, 22));
        InsertChinese1.setBackground(Color.GREEN);
        InsertChinese1.setBounds(100, 200, 120, 50);
        InsertMath1 = new JLabel("数学成绩：");
        InsertMath1.setFont(new Font("楷体", 1, 22));
        InsertMath1.setBackground(Color.GREEN);
        InsertMath1.setBounds(100, 260, 120, 50);
        InsertEnglish1 = new JLabel("英语成绩：");
        InsertEnglish1.setFont(new Font("楷体", 1, 22));
        InsertEnglish1.setBackground(Color.GREEN);
        InsertEnglish1.setBounds(100, 320, 120, 50);
        Tips = new JLabel("★提示： 新建学生的密码默认为学号，请提醒学生及时修改！！");
        Tips.setFont(new Font("楷体", 1, 22));
        Tips.setBackground(Color.GREEN);
        Tips.setBounds(100, 370, 900, 50);

        // jp2 上的标签
        DeleteID1 = new JLabel("学  号：");
        DeleteID1.setBounds(100, 100, 100, 50);
        DeleteID1.setFont(new Font("楷体",1, 22));
        Tip2 = new JLabel("★提示：点击重置可以清除输入框中的内容。");
        Tip2.setBounds(100,160,900,50);
        Tip2.setFont(new Font("楷体",1, 22));


        // jp3 上的标签
        UpdateID1 = new JLabel("学  号：");
        UpdateID1.setFont(new Font("楷体", 1, 22));
        UpdateID1.setBounds(200, 60, 120, 50);
        UpdateItem = new JComboBox<>();
        UpdateItem.setFont(new Font("楷体", 1, 22));
        UpdateItem.setBounds(200, 200, 100, 45);
        UpdateItem.addItem("姓名");
        UpdateItem.addItem("班级");
        UpdateItem.addItem("语文");
        UpdateItem.addItem("数学");
        UpdateItem.addItem("英语");

        // jp4 上的标签
        // ...
        // jp5 上的标签
        CourseItem = new JComboBox<>();
        CourseItem.setFont(new Font("楷体", 1, 22));
        CourseItem.setBounds(100, 40, 140, 45);
        CourseItem.addItem("语文");
        CourseItem.addItem("数学");
        CourseItem.addItem("英语");
        // jp6 上的标签
        GradeItem = new JComboBox<>();
        GradeItem.setFont(new Font("楷体", 1, 22));
        GradeItem.setBounds(100, 40, 140, 45);
        GradeItem.addItem("语文");
        GradeItem.addItem("数学");
        GradeItem.addItem("英语");
    }

    // 设置各文本框信息的方法
    private void setTextField() {
        // jp1 上的文本框
        InsertID2 = new JTextField();
        InsertID2.setFont(new Font("宋体", 1, 23));
        InsertID2.setBounds(210, 20, 200, 35);
        InsertName2 = new JTextField();
        InsertName2.setFont(new Font("宋体", 1, 23));
        InsertName2.setBounds(210, 80, 200, 35);

        InsertClass2 = new JTextField();
        InsertClass2.setText(String.valueOf(teacherLogin.teacherClass));
        InsertClass2.setFont(new Font("宋体", 1, 23));
        InsertClass2.setBounds(210, 140, 200, 35);

        InsertChinese2 = new JTextField();
        InsertChinese2.setFont(new Font("宋体", 1, 23));
        InsertChinese2.setBounds(210, 200, 200, 35);
        InsertMath2 = new JTextField();
        InsertMath2.setFont(new Font("宋体", 1, 23));
        InsertMath2.setBounds(210, 260, 200, 35);
        InsertEnglish2 = new JTextField();
        InsertEnglish2.setFont(new Font("宋体", 1, 23));
        InsertEnglish2.setBounds(210, 320, 200, 35);
        // jp2 上的文本框
        DeleteID2 = new JTextField("输入要删除信息的学号");
        DeleteID2.setFont(new Font("楷体", 1, 25));
        DeleteID2.setBounds(210, 100, 350, 50);
        // jp3 上的文本框
        UpdateID2 = new JTextField();
        UpdateID2.setFont(new Font("楷体", 1, 20));
        UpdateID2.setBounds(310, 60, 200, 45);
        UpdateContent = new JTextField("更新内容");
        UpdateContent.setFont(new Font("楷体", 0, 22));
        UpdateContent.setBounds(310, 200, 200, 45);
        // jp4 上的文本框
        QueryRecordResult = new JTextArea("查询结果：");
        QueryRecordResult.setFont(new Font("楷体", 1, 20));
        // QueryRecordResult.setBounds(30,30,560,260);
        QueryRecordResult.setEditable(false);
        QueryRecordResult.setLineWrap(true); // 当一行文字过多时自动换行
        scroll = new JScrollPane(QueryRecordResult); // 添加滚动条
        scroll.setBounds(30, 30, 560, 260);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED); // 当需要垂直滚动条时显示
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);// 当需要水平滚动条时显示
        IDCondition = new JTextField();
        IDCondition.setFont(new Font("宋体", 1, 18));
        IDCondition.setBounds(90, 300, 100, 21);
        NameCondition = new JTextField();
        NameCondition.setFont(new Font("宋体", 1, 18));
        NameCondition.setBounds(90, 330, 100, 21);
        ClassCondition = new JTextField();
   //     Class.setText(String.valueOf(teacherLogin.teacherClass));
        ClassCondition.setFont(new Font("宋体", 1, 18));
        ClassCondition.setBounds(90, 360, 100, 21);

        ChineseCondition = new JTextField();
        ChineseCondition.setFont(new Font("宋体", 1, 18));
        ChineseCondition.setBounds(90, 390, 100, 21);
        MathCondition = new JTextField();
        MathCondition.setFont(new Font("宋体", 1, 18));
        MathCondition.setBounds(90, 420, 100, 21);
        EnglishCondition = new JTextField();
        EnglishCondition.setFont(new Font("宋体", 1, 18));
        EnglishCondition.setBounds(90, 450, 100, 21);
        IDCondition.setEditable(false);
        NameCondition.setEditable(false);
        ClassCondition.setEditable(false);
        ChineseCondition.setEditable(false);
        MathCondition.setEditable(false);
        EnglishCondition.setEditable(false);
        // jp5 上的文本框
        CourseQueryResult = new JTextArea("查询结果：");
        CourseQueryResult.setFont(new Font("楷体", 1, 20));
        CourseQueryResult.setEditable(false);
        CourseQueryResult.setLineWrap(true);
        CourseScroll = new JScrollPane(CourseQueryResult);
        CourseScroll.setBounds(20, 100, 560, 340);
        CourseScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        CourseScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        // jp6 上的文本框
        GradeQueryResult = new JTextArea("查询结果：");
        GradeQueryResult.setFont(new Font("楷体", 1, 20));
        GradeQueryResult.setEditable(false);
        GradeQueryResult.setLineWrap(true);
        GradeScroll = new JScrollPane(GradeQueryResult);
        GradeScroll.setBounds(20, 100, 560, 340);
        GradeScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        GradeScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    }

    // 设置各面板信息的方法
    private void setPanel() {
        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();
        jp4 = new JPanel();
        jp5 = new JPanel();
        jp6 = new JPanel();
    }

    // 设置布局信息的方法
    private void setLayout() {
        // 添加 jp1 的组件
        jp1.setLayout(null);
        jp1.add(InsertRecord);
        jp1.add(InsertReset);
        jp1.add(InsertID1);
        jp1.add(InsertName1);
        jp1.add(InsertClass1);
        jp1.add(InsertChinese1);
        jp1.add(InsertMath1);
        jp1.add(InsertEnglish1);
        jp1.add(Tips);
        jp1.add(InsertID2);
        jp1.add(InsertName2);
        jp1.add(InsertClass2);
        jp1.add(InsertChinese2);
        jp1.add(InsertMath2);
        jp1.add(InsertEnglish2);
        // 添加 jp2 上的组件
        jp2.setLayout(null);
        jp2.add(DeleteID1);
        jp2.add(DeleteID2);
        jp2.add(Tip2);
        jp2.add(DeleteRecord);
        jp2.add(DeleteReset);
        // 添加 jp3 上的组件
        jp3.setLayout(null);
        jp3.add(UpdateID1);
        jp3.add(UpdateID2);
        jp3.add(UpdateItem);
        jp3.add(UpdateContent);
        jp3.add(UpdateRecord);
        jp3.add(UpdateReset);
        // 添加 jp4 上的组件
        jp4.setLayout(null);
        // jp4.add(QueryRecordResult);
        jp4.add(scroll);
        jp4.add(QueryRecord);
        jp4.add(ID);
        jp4.add(Name);
        jp4.add(Class);
        jp4.add(Chinese);
        jp4.add(Math);
        jp4.add(English);
        jp4.add(IDCondition);
        jp4.add(NameCondition);
        jp4.add(ClassCondition);
        jp4.add(ChineseCondition);
        jp4.add(MathCondition);
        jp4.add(EnglishCondition);
        // 添加 jp5 上的组件
        jp5.setLayout(null);
        jp5.add(CourseItem);
        jp5.add(CourseQuery);
        jp5.add(CourseScroll);
        // 添加 jp6 上的组件
        jp6.setLayout(null);
        jp6.add(GradeQuery);
        jp6.add(GradeItem);
        jp6.add(GradeScroll);
    }

    // 设置选项卡信息的方法
    private void setBase() {
        Base = new JTabbedPane(JTabbedPane.TOP);
        Base.addTab("添加记录", jp1);
        Base.addTab("删除记录", jp2);
        Base.addTab("更新记录", jp3);
        Base.addTab("查找记录", jp4);
//        Base.addTab("选课记录", jp5);
        Base.addTab("课程平均分", jp6);
    }

    // 设置数据库信息的方法
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

    // 设置主窗口信息的方法
    private void setThis() {
        this.add(Base);

        // teacherLogin teacher = new teacherLogin();//获取登录时的老师ID
        // String teachername = String.valueOf(db.getRs().getString(2));

        this.setTitle("你好，" + teacherLogin.teacherName);// 直接使用静态变量获取教师姓名
        this.setLocation(300, 200);
        this.setSize(800, 550);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
    }

    // 初始化
    void initial() {
        // 给各按钮添加监听器
        // InsertRecord, InsertReset, DeleteRecord, DeleteReset, QueryRecord,
        // UpdateRecord, CourseQuery, GradeQuery;
        InsertRecord.addMouseListener(this);
        InsertReset.addMouseListener(this);
        DeleteRecord.addMouseListener(this);
        DeleteReset.addMouseListener(this);
        QueryRecord.addMouseListener(this);
        UpdateRecord.addMouseListener(this);
        UpdateReset.addMouseListener(this);
        CourseQuery.addMouseListener(this);
        GradeQuery.addMouseListener(this);
        // 给各复选按钮添加监听器
        // ID,Name, Chinese, Math, English
        ID.addItemListener(this);
        Name.addItemListener(this);
        Class.addItemListener(this);
        Chinese.addItemListener(this);
        Math.addItemListener(this);
        English.addItemListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // 添加按钮功能
        // 点击重置键则清空文本框
        if (e.getSource().equals(InsertReset)) {
            InsertID2.setText("");
            InsertID2.setFont(new Font("宋体", 1, 23));
            InsertName2.setText("");
            InsertName2.setFont(new Font("宋体", 1, 23));
            InsertClass2.setText("");
            InsertClass2.setFont(new Font("宋体", 1, 23));

            InsertChinese2.setText("");
            InsertChinese2.setFont(new Font("宋体", 1, 23));
            InsertMath2.setText("");
            InsertMath2.setFont(new Font("宋体", 1, 23));
            InsertEnglish2.setText("");
            InsertEnglish2.setFont(new Font("宋体", 1, 23));
        } else if (e.getSource().equals(InsertRecord)) {
            // 添加记录功能
            String InsertStuID = InsertID2.getText();
            String InsertStuName = InsertName2.getText();
            String Class = InsertClass2.getText();
            try { // 转化为int
                InsertStuClass = Integer.parseInt(Class);
            } catch (NumberFormatException f) {
                f.printStackTrace();
            }
            String Chinese = InsertChinese2.getText();
            try { // 转化为int
                if(Chinese == NULL)
                    InsertStuChinese = -1;
                else
                    InsertStuChinese = Integer.parseInt(Chinese);
            } catch (NumberFormatException f) {
                f.printStackTrace();
            }
            String Math = InsertMath2.getText();
            try { // 转化为int
                if(Math == NULL)
                    InsertStuMath = -1;
                else
                    InsertStuMath = Integer.parseInt(Math);
            } catch (NumberFormatException f) {
                f.printStackTrace();
            }
            String English = InsertEnglish2.getText();
            try { // 转化为int
                if(English == NULL)
                    InsertStuEnglish = -1;
                else
                    InsertStuEnglish = Integer.parseInt(English);
            } catch (NumberFormatException f) {
                f.printStackTrace();
            }
            String InsertStuPassword = InsertStuID;

            try {
            //    System.out.println("hello"+InsertStuID);
                db.setRs(db.executeQuery(InsertStuID));
            //    System.out.println("zheli!!");
                if (!db.getRs().next()) {
                    System.out.println(InsertStuName);
                    db.executeInsert(InsertStuID, InsertStuName,InsertStuClass,InsertStuChinese, InsertStuMath, InsertStuEnglish,InsertStuPassword);
                    System.out.println("这里连接成功！！");
                    JOptionPane.showOptionDialog(this, "添加信息成功！", "数据库操作提示",
                            JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
                } else JOptionPane.showOptionDialog(this, "添加失败", "温馨提示",
                        -1, 1, null, null, null);
            } catch (Exception exception) {
                exception.printStackTrace();
            } finally {
                db.CloseRS();
                db.CloseStmt();
                db.CloseConnection();
            }
        } else if (e.getSource().equals(DeleteReset)) {
            // 删除重置功能
            DeleteID2.setText("");
            DeleteID2.setFont(new Font("楷体", 1, 25));
        } else if (e.getSource().equals(DeleteRecord)) {
            // 删除功能
            String DeleteStuID = DeleteID2.getText();
            try {
                db.setRs(db.executeQuery(DeleteStuID));
                if (db.getRs().next()) {
                    db.executeDelete(DeleteStuID);
                    JOptionPane.showOptionDialog(this, "删除成功！", "数据库操作提示",
                            -1, 1, null, null, null);
                } else JOptionPane.showOptionDialog(this, "删除失败", "温馨提示",
                        -1, 1, null, null, null);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        } else if (e.getSource().equals(UpdateReset)) {
            // 重置更新框功能
            UpdateID2.setText("");
            UpdateID2.setFont(new Font("宋体", 1, 20));
            UpdateContent.setText("");
            UpdateContent.setFont(new Font("宋体", 1, 20));
        } else if (e.getSource().equals(UpdateRecord)) {
            // 完成更新功能
            String UpdateStuID = UpdateID2.getText();
            try {
                db.setRs(db.executeQuery(UpdateStuID));
                if (!db.getRs().next()) {
                    JOptionPane.showOptionDialog(this, "没有记录无法更新",
                            "温馨提示", JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE,
                            null, null, null);
                } else {
                    String updateItem = null;
                    // 更新选项是姓名
                    if (UpdateItem.getSelectedItem().toString().equals("姓名")) {
                        updateItem = "Name";
                    }
                    // 更新的是语文成绩
                    else if (UpdateItem.getSelectedItem().toString().equals("语文")) {
                        updateItem = "Chinese";
                    }
                    // 更新的是数学成绩
                    else if (UpdateItem.getSelectedItem().toString().equals("数学")) {
                        updateItem = "Math";
                    }
                    // 更新的是英语成绩
                    else if (UpdateItem.getSelectedItem().toString().equals("英语")) {
                        updateItem = "English";
                    }
                    else if (UpdateItem.getSelectedItem().toString().equals("班级"))
                    {
                        updateItem = "class";
                    }
                    db.executeUpdate(UpdateStuID, updateItem, UpdateContent.getText());
                    JOptionPane.showOptionDialog(this, "更新成功！", "数据库操作提示",
                            -1, 1, null, null, null);
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            } finally {
                db.CloseRS();
                db.CloseStmt();
                db.CloseConnection();
            }
        } else if (e.getSource().equals(QueryRecord)) {
            // 完成查询功能
            try {
                // 默认设置各检索条件均为通配符
                String a = "%", b = "%", c = "%";
                int d = -1, f = -1,g = -1;
                // 如果 ID 选项被选中，则获得该选项的输入内容
                if (ID.isSelected() && !IDCondition.getText().trim().isEmpty()) {
                    a = IDCondition.getText();
                }
                // 如果 Name 选项被选中，则获得该选项的输入内容
                if (Name.isSelected() && !NameCondition.getText().trim().isEmpty()) {
                    b = NameCondition.getText();
                }
                // 如果 Class 选项被选中，则获得该选项的输入内容
                if (Class.isSelected() && !ClassCondition.getText().trim().isEmpty()) {
                    c = ClassCondition.getText();
                }

                // 如果 Math 选项被选中，则获得该选项的输入内容
                if (Math.isSelected() && !MathCondition.getText().trim().isEmpty()) {
                    try {
                        f = Integer.parseInt(MathCondition.getText());
                    } catch (NumberFormatException x) {
                        x.printStackTrace();
                    }
                }
                // 如果 English 选项被选中，则获得该选项的输入内容
                if (English.isSelected() && !EnglishCondition.getText().trim().isEmpty()) {
                    try {
                        g = Integer.parseInt(EnglishCondition.getText());
                    } catch (NumberFormatException x) {
                        x.printStackTrace();
                    }
                }
                // 如果 Chinese 选项被选中，则获得该选项的输入内容
                if (Chinese.isSelected() && !ChineseCondition.getText().trim().isEmpty()) {
                    try {
                        d= Integer.parseInt(ChineseCondition.getText());
                    } catch (NumberFormatException x) {
                        x.printStackTrace();
                    }
                }
                // 根据各选项检索关键字进行查询，并返回结果集
                db.setRs(db.executeQueryByCondition(a, b, c, d, f, g));
                // 定义结果集中记录条数
                int i = 0;
                QueryRecordResult.setText("查询结果：");
                // 输出结果集记录
                while (db.getRs().next()) {
                    ++i;
                    QueryRecordResult.append("\r\n" + "第" + i + "条记录：" + "\r\n"
                            + "学号：" + db.getRs().getString(1) + "\r\n"
                            + "姓名：" + db.getRs().getString(2) + "\r\n"
                            + "班级：" + db.getRs().getString(3) + "\r\n"
                            + "语文：" + db.getRs().getString(4) + "\r\n"
                            + "数学：" + db.getRs().getString(5) + "\r\n"
                            + "英语：" + db.getRs().getString(6) +
                            ("\r\n--------------------------------------"));
                }
                QueryRecordResult.setText(QueryRecordResult.getText() +
                        "\r\n" + "共有" + i + "条学生记录");
            } catch (Exception e1) {
                e1.printStackTrace();
            } finally {
                db.CloseRS();
                db.CloseStmt();
                db.CloseConnection();
            }
        } else if (e.getSource().equals(CourseQuery)) {
            // 完成选课查询
            String Course = CourseItem.getSelectedItem().toString();
            try {
                db.setRs(db.executeQueryByCourse(Course));
                int count = 0;
                CourseQueryResult.setText("查询结果：");
                // 输出结果集记录
                while (db.getRs().next()) {
                    ++count;
                    CourseQueryResult.append("\r\n" + "第" + count + "条记录" + "\r\n"
                            + "学号： " + db.getRs().getString(1) + "\r\n"
                            + "姓名： " + db.getRs().getString(2) + "\r\n"
                            + "课程： " + db.getRs().getString(3) + "\r\n"
                            + "学分： " + db.getRs().getString(4) +
                            ("\r\n--------------------------------------"));
                }
                CourseQueryResult.setText(CourseQueryResult.getText() +
                        "\r\n" + "共有" + count + "条选课记录");
            } catch (Exception exception) {
                exception.printStackTrace();
            } finally {
                db.CloseRS();
                db.CloseStmt();
                db.CloseConnection();
            }
        } else if (e.getSource().equals(GradeQuery)) {
            // 完成课程平均分统计查询
            String Course = GradeItem.getSelectedItem().toString();
            try {
                System.out.println(Course);
                db.setRs(db.executeQueryByGrade(teacherLogin.teacherClass));
                int temp = 0;
                if(Course == "语文")
                {
                    temp = 4;
                }
                else if(Course == "数学")
                {
                    temp = 5;
                }
                else if(Course == "英语")
                {
                    temp = 6;
                }
                int j = 0;
                int sum = 0;
                int max= 0;
                int min = 101;
                GradeQueryResult.setText("查询结果：");
                // 输出查询结果集
                while (db.getRs().next()) {
                    ++j;
                    if(db.getRs().getInt(temp)>max)
                    {
                        max = db.getRs().getInt(temp);
                    }
                    if(db.getRs().getInt(temp)<min)
                    {
                        min = db.getRs().getInt(temp);
                    }
                    sum = sum + db.getRs().getInt(temp);        
                }
                GradeQueryResult.append("\n" + teacherLogin.teacherClass +"班的"+ Course +"课程的"
                        + "的平均分为： " + sum/j + ("\r\n--------------------------------------"));
                GradeQueryResult.append("\n"+teacherLogin.teacherClass+"班的"+Course+"课程的"
                        + "的最高分为： " + max + ("\r\n--------------------------------------"));
                GradeQueryResult.append("\n" + teacherLogin.teacherClass + "班的" + Course + "课程的" + "的最低分为： " + min
                        + ("\r\n--------------------------------------"));
             //   GradeQueryRe("\n"+teacherLogin.teacherClass+"班的"+Course+)sult.setText(GradeQueryResult.getText() +
               //         "\r\n" + "共有" + j + "条记录");
            } catch (Exception exception) {
                exception.printStackTrace();
            } finally {
                db.CloseRS();
                db.CloseStmt();
                db.CloseConnection();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        // 如果查询选项 ID 被选中，则可以输入 ID 进行查询
        if (e.getSource().equals(ID)) {
            IDCondition.setEditable(ID.isSelected());
        }
        // 如果选项姓名被选中，则可以输入姓名进行查询
        else if (e.getSource().equals(Name)) {
            NameCondition.setEditable(Name.isSelected());
        }
        // 如果选项班级被选中，则可以输入班级成绩进行查询
        else if (e.getSource().equals(Class)) {
            ClassCondition.setEditable(Class.isSelected());
        }


        // 如果语文被选中，则可以输入语文成绩进行查询
        else if (e.getSource().equals(Chinese)) {
            ChineseCondition.setEditable(Chinese.isSelected());
        }
        // 如果数学选项被选中，则可以输入数学成绩查询
        else if (e.getSource().equals(Math)) {
            MathCondition.setEditable(Math.isSelected());
        }
        // 如果英语选项被选中，则可以输入英语成绩来查询
        else if (e.getSource().equals(English)) {
            EnglishCondition.setEditable(English.isSelected());
        }
    }
}