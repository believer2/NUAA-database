// OperationMySql.java
package StudentInfo;

import java.sql.*;

public class OperationMySql {
    // 定义数据库连接url
    private String dburl = null;
    // 定义数据库连接
    private Connection conn = null;
    // 定义数据库状态
    private PreparedStatement stmt = null;
    // 定义数据库返回结果集
    private static ResultSet rs = null;
    // 定义数据库用户名
    private String username = null;
    // 定义数据库连接密码
    private String password = null;
    // 定义数据库驱动方式
    private String dbdriver = null;

    // 设置数据库连接url的方法
    public void setDburl(String dburl) {
        this.dburl = dburl;
    }

    // 返回当前实例数据库连接url
    public String getDburl() {
        return dburl;
    }

    // 返回当前实例结果集的方法
    public ResultSet getRs() {
        return rs;
    }

    // 设置当前实例结果集的方法
    public void setRs(ResultSet rs) {
        OperationMySql.rs = rs;
    }

    // 设置数据库用户名的方法
    public void setUsername(String username) {
        this.username = username;
    }

    // 返回当前实例化数据库用户名
    public String getUsername() {
        return username;
    }

    // 设置数据库连接的方法
    public void setPassword(String password) {
        this.password = password;
    }

    // 返回当前实例数据库连接密码
    public String getPassword() {
        return password;
    }

    // 设置数据库驱动方式的方法
    public void setDbdriver(String dbdriver) {
        this.dbdriver = dbdriver;
    }

    // 返回当前实例数据库驱动方式的方法
    public String getDbdriver() {
        return dbdriver;
    }

    // 创建数据库连接的方法
    Connection CreateConnection(String dburl, String username, String password) throws Exception {
        setDburl(dburl);
        setUsername(username);
        setPassword(password);
        Class.forName(getDbdriver());
        // 根据数据库路径、用户名和密码创建连接并返回该连接
        return DriverManager.getConnection(dburl, username, password);
    }

    // 关闭结果集的方法
    public void CloseRS() {
        try {
            rs.close();
        } catch (SQLException e) {
            System.out.println("关闭结果集时发生错误！");
        }
    }

    // 关闭状态的方法
    public void CloseStmt() {
        try {
            stmt.close();
        } catch (SQLException e) {
            System.out.println("关闭状态时发生错误！");
        }
    }

    // 关闭连接的方法
    public void CloseConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println("关闭连接时发生错误！");
        }
    }

    // 增
    void executeInsert(String InsertID, String InsertName,int Class,int Chinese, int Math,int English,String Password) throws Exception {
        try {
            System.out.println("开始连接");
            conn = CreateConnection(getDburl(), getUsername(), getPassword());
            System.out.println("连接成功！！"); 
            stmt = conn.prepareStatement("insert into student values(?,?,?,?,?,?,?)");
            stmt.setString(1, InsertID);
            stmt.setString(2, InsertName);
            stmt.setInt(3, Class);
            if (Chinese == -1)
                stmt.setString(4,"");
            else
                stmt.setInt(4, Chinese);
            if(Math == -1)
                stmt.setString(5,"");
            else
                stmt.setInt(5, Math);
            if(English == -1)
                stmt.setString(6,"");
            else
                stmt.setInt(6, English);
            stmt.setString(7,Password);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    // 删
    void executeDelete(String DeleteID) throws Exception {
        try {
            conn = CreateConnection(getDburl(), getUsername(), getPassword());
            stmt = conn.prepareStatement("delete from student where ID = ?");
            stmt.setString(1, DeleteID);
            stmt.executeUpdate();
            CloseStmt();
            CloseConnection();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

/*    //查学生账号密码
    ResultSet executepassword() throws Exception {
        try {
            String sql = "select * from grade";
            conn = CreateConnection(getDburl(), getUsername(), getPassword());
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return rs;
    }*/





    // 查 主键 是否在表中
    ResultSet executeQuery(String StuID) throws Exception {
        try {
            System.out.println("连接成功！");
            String sql = "select * from student where ID = ?";
            conn = CreateConnection(getDburl(), getUsername(), getPassword());
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, StuID);
            rs = stmt.executeQuery();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return rs;
    }
//查询老师信息
    ResultSet executeQueryteacher(String tID) throws Exception {
        try {
            System.out.println("连接成功！");
            String sql = "select * from teacher where ID = ?";
            conn = CreateConnection(getDburl(), getUsername(), getPassword());
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, tID);
            rs = stmt.executeQuery();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return rs;
    }
    // 改
    void executeUpdate(String UpdateID, String UpdateItem, String UpdateContent) throws Exception {
        try {
            conn = CreateConnection(getDburl(), getUsername(), getPassword());
            String sql = "update student set " + UpdateItem + " = ? where ID = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, UpdateContent);
            stmt.setString(2, UpdateID);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    // 改
    void ChangePassword(String UpdateID, String password) throws Exception {
        try {
            conn = CreateConnection(getDburl(), getUsername(), getPassword());
            String sql = "update student set " + "password" + " = ? where ID = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, password);
            stmt.setString(2, UpdateID);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            
            System.err.println(ex.getMessage());
        }
    }

    // 按条件查询
    ResultSet executeQueryByCondition(String stuid, String stuname,String stuclass, int chinese, int math, int english) throws Exception {
        try {
            String sql = "select * from student where ID like ? and Name like ? and class like ? and Chinese >= ? " +
                    "and Math >= ? and English >= ? order by ID asc";
            conn = CreateConnection(getDburl(), getUsername(), getPassword());
            stmt = conn.prepareStatement(sql);
            if (stuid.equals("%")) {
                stmt.setString(1, "%");
            } else {
                stmt.setString(1, "%" + stuid + "%");
            }
            if (stuname.equals("%")) {
                stmt.setString(2, "%");
            } else {
                stmt.setString(2, "%" + stuname + "%");
            }
            if (stuclass.equals("%")) {
                stmt.setString(3, "%");
            } else {
                stmt.setString(3, "%" + stuclass + "%");
            }
            if (chinese == -1) {
                stmt.setInt(4,0);
            } else {
                stmt.setInt(4,chinese);
            }
            if (math == -1) {
                stmt.setInt(5, 0);
            } else {
                stmt.setInt(5,math);
            }
            if (english == -1) {
                stmt.setInt(6,0);
            } else {
                stmt.setInt(6,english);
            }
            rs = stmt.executeQuery();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return rs;
    }

    // 选课表查询
    ResultSet executeQueryByCourse(String course) throws Exception {
        try {
            conn = CreateConnection(getDburl(), getUsername(), getPassword());
            String sql = "select * from course where Course = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, course);
            rs = stmt.executeQuery();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return rs;
    }

    // 班级查询
    ResultSet executeQueryByGrade(int Class) throws Exception {
        try {
            conn = CreateConnection(getDburl(), getUsername(), getPassword());
            String sql = "select * from student where class = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Class);
            rs = stmt.executeQuery();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return rs;
    }
}
