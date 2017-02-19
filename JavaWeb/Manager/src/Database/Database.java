package Database;

import java.sql.*;

import static Utils.Print.print;

/**
 * Created by YocyTang on 2017/1/3.
 * 数据库的连接和关闭
 */
public class Database {
    private final String USER;
    private final String PASSWD;
    private final String JDBC_DRIVER ="com.mysql.jdbc.Driver";
    private  String DB_URL = "jdbc:mysql://localhost:3306/";
    private Connection connection;
    private PreparedStatement preparedStatement;
    public Database(String USER, String PASSWD, String DatabaseName){
        this.USER = USER;
        this.PASSWD = PASSWD;
        DB_URL = DB_URL+DatabaseName;
    }
    public Database(String DatabaseName){
        this("root","", DatabaseName);
    }
    private void setConnection(){
        try{
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,USER, PASSWD);

        }catch (ClassNotFoundException e){
            System.out.println("JDBC_DRIVER import failed");
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
    public Connection getConnection(){
        setConnection();
        return connection;

    }
    public void setPreparedStatement(String sql){
        if(connection == null){
            this.setConnection();
        }
        try{
            preparedStatement = connection.prepareStatement(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
    public PreparedStatement getPreparedStatement(String sql){
        setPreparedStatement(sql);
        return preparedStatement;
    }
    //为了灵活性，需要传入得到的ResultSet
    public void close(ResultSet resultSet){
        try{
            resultSet.close();
            preparedStatement.close();
            connection.close();

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try{
                if(resultSet!=null){
                    resultSet.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
            try{
                if(preparedStatement!=null){
                    preparedStatement.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }

            try{
                if(connection!=null){
                    connection.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }

        }
    }
    //测试用例
    public static void main(String[] args){
        Database database = new Database("cute");
        String sql = "select * from manager";
        PreparedStatement preparedStatement = database.getPreparedStatement(sql);
        ResultSet resultSet=null;
        try{
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){

                String name = resultSet.getString("username");
                String sex = resultSet.getString("sex");
                String tel = resultSet.getString("tel");
                String email  = resultSet.getString("email");
                String address = resultSet.getString("address");
                int age = resultSet.getInt("age");
                String descript = resultSet.getString("descript");
                print("name: "+name+"  sex: "+sex+" tel: "+tel+" email: "+email+" address: "+address+" age: "+age
                        +" descript: "+descript);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        database.close(resultSet);
    }
}
