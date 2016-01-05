/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.DataModel;

//mysql password 111111
/**
 *
 * @author Xing Wang
 */
public class DataBaseIO {
    public static final String TABLE = "DATA_TBL";
    public static final String COLUMN_INDEX = "IDX";
    //public static final String COLUMN_VALUE = "DATAVALUE";
    public static final String COLUMN_TIME = "TIMEPOINT";
    public static final ArrayList<String> COLUMN_VALUES = new ArrayList();
    static{
        COLUMN_VALUES.add("DATA_VALUE_1");
        //COLUMN_VALUES.add("DATA_VALUE_2");
        //COLUMN_VALUES.add("DATA_VALUE_3");
    }
    
    protected static DataBaseIO dataBaseIO = new DataBaseIO();  
    private Connection connection = null;
    private final String DRIVER = "com.mysql.jdbc.Driver";
    private final String URL = "jdbc:mysql://localhost:3306/timeseriesdata_db";
    private final String USERNAME = "root";
    private final String PASSWORD = "111111";
    public boolean connectDataBase() {
        try {
            Class.forName(DRIVER);
            this.connection = DriverManager.getConnection(
                    URL, USERNAME, PASSWORD 
            );
            return true;
        } catch (Exception e) {
            //System.out.println("Failed to get connection");
            e.printStackTrace();
        }
        return false;
    }
    private Connection getConnection(){
        return this.connection;
    }
    public static DataBaseIO getDataBaseIO(){
       return dataBaseIO;
    }
   
    private DataBaseIO() {
        this.connectDataBase();
    }

    public void setConnectionAutoCommit(boolean on){
        try {
            this.getConnection().setAutoCommit(on);
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseIO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void connectionCommit(){
        try {
            this.getConnection().commit();
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseIO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ResultSet describeTable(String table){
        String sql = "describe"+ table;
        return this.executeStatement(sql);
    }
    /* 
     * Close Database Connection, Watch Out The Close Order 
     */  
    public void close(ResultSet rs, PreparedStatement ps, Connection conn) {  
        //Watch Out, Last Open First Close 
        if(rs!=null){  
            try{  
                rs.close();  
                rs=null;  
            }catch(SQLException e){  
                e.printStackTrace(); 
                System.out.println("Close ResultSet Failure");
                //logger.error("关闭ResultSet失败",e);  
            }  
        }  
        if(ps!=null){  
            try{  
                ps.close();  
                ps=null;  
            }catch(SQLException e){  
                e.printStackTrace();  
                //logger.error("关闭PreparedStatement失败",e);  
                System.out.println("Close PreparedStatement Failure");
            }  
        }  
        if(conn!=null){  
            try{  
                conn.close();  
                conn=null;  
            }catch(SQLException e){  
                e.printStackTrace();  
                //logger.error("关闭Connection失败",e);  
                System.out.println("Close Connection Failure");
            }  
        }  
    }  

    public ResultSet executeUpdateStatement(String sql) {
        Statement statement = null;
        try {
            statement = this.getConnection().createStatement();
            statement.executeUpdate(sql);
            return statement.getResultSet();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet executeStatement(String sql) {
        Statement statement = null;
        try {
            statement = this.getConnection().createStatement();
            statement.execute(sql);
            return statement.getResultSet();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getMaxRowIndex(String columnName){
        String sql = "SELECT " + columnName + " FROM DATA_TBL";
        ResultSet rSet = this.executeStatement(sql);
        int maxRowIndex = -1;
        try {
            rSet.last();
            maxRowIndex = rSet.getRow();
            rSet.first();
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseIO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return maxRowIndex;
    }
    public void createTable(String createTableStatement){
        this.dataBaseIO.executeStatement(createTableStatement);
    }
    
    public PreparedStatement creatPreparedStatement(String sql){
        try {
            return this.getConnection().prepareStatement(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseIO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public Statement createStatement(){
        try {
            return this.getConnection().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseIO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /**
     * This method returns the series size of data
     * @return  
     */
    public int getDataSeriesesCount() {

        //该函数用于返回一共有多少组数据
        try {
            String cName = "VARIABLES_COUNT";
            String sql = "select count(column_name) as " + cName + " from information_schema.`COLUMNS` where column_name like 'DATA_VALUE%';";
            ResultSet rSet = this.dataBaseIO.executeStatement(sql);

            rSet.beforeFirst();
            rSet.next();

            String countString = rSet.getString(cName);
            int count = Integer.valueOf(countString);
            return count;
        } catch (SQLException ex) {
            Logger.getLogger(DataModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return -1;
    }
    
    
    
}
