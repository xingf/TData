/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toolkit;

import java.awt.geom.Point2D;
import java.lang.reflect.Array;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class Toolkit {

    public static final int xAxisIndex = 1;
    public static final int yAxisIndex = 2;
    public static Object getColumnByColumnNameFromResultSet(String columnName, ResultSet rSet) {
        Object array = null;
        try {
            ResultSetMetaData rsmd = rSet.getMetaData();
            int columnIdx = rSet.findColumn(columnName);
            int columnType = rsmd.getColumnType(columnIdx);
            int rowSize = Toolkit.getRowSizeFromResultSet(rSet);
            rSet.beforeFirst();
            switch (columnType) {
                case Types.INTEGER:
                    array = Array.newInstance(int.class, rowSize);
                    
                     while (rSet.next()) {
                System.out.println("row idx: " + (rSet.getRow() - 1) + ",  value" + rSet.getInt(columnIdx));
                Array.set(array, rSet.getRow() - 1, rSet.getInt(columnIdx));
            }
                    break;
                case Types.FLOAT:
                case Types.DOUBLE:
                    array = Array.newInstance(float.class, rowSize);
                     while (rSet.next()) {
                System.out.println("row idx: " + (rSet.getRow() - 1) + ",  value" + rSet.getFloat(columnIdx));
                Array.set(array, rSet.getRow() - 1, rSet.getFloat(columnIdx));
            }
                    break;
            }

           
        } catch (SQLException ex) {
            Logger.getLogger(Toolkit.class.getName()).log(Level.SEVERE, null, ex);
        }
        return array;
    }
    
    public static int getRowSizeFromResultSet(ResultSet rSet){
        try {
            rSet.last();
            System.out.println("Row Size: " + rSet.getRow());
            return rSet.getRow();
            
        } catch (SQLException ex) {
            Logger.getLogger(Toolkit.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    public static String getMax(Object arr){
        Class classType = arr.getClass();  
         List list = new ArrayList();  
         int length = 0;  
         if (classType.isArray()) { // 判断是不是数组  
                 length = Array.getLength(arr);  
                 Class componentType = classType.getComponentType(); // 获得数组的类型  
                 if (componentType == int.class  
                        || componentType == double.class  
                             || componentType == float.class  
                                 || componentType == long.class) {  
                 for (int i = 0; i < length; i++) {   
                         // 如果是基本类型的数字数组，需要手动添加到集合  
                         list.add(Array.get(arr, i));   
                 }  
         } else if (componentType == Integer.class  
                         || componentType == Float.class  
                             || componentType == Double.class  
                                 ||componentType == Long.class) {  
                 // 如果是包装类型，用Arrays的asList()方法  
                 Object[] newArr = (Object[]) Array.newInstance(componentType,  
                                 length);  
                 System.arraycopy(arr, 0, newArr, 0, length);  
                 list = Arrays.asList(newArr);  
          } else {  
                 throw new RuntimeException("请出入数字数组");  
          }  
  
          } else {  
         throw new RuntimeException("请输入数组");  
        }  
        System.out.println("getMax: " + Collections.max(list));
        return Collections.max(list).toString();  
    }
    
    public static String getMin(Object arr){
        Class classType = arr.getClass();  
         List list = new ArrayList();  
         int length = 0;  
         if (classType.isArray()) { // 判断是不是数组  
                 length = Array.getLength(arr);  
                 Class componentType = classType.getComponentType(); // 获得数组的类型  
                 if (componentType == int.class  
                        || componentType == double.class  
                             || componentType == float.class  
                                 || componentType == long.class 
                                    ) {  
                 for (int i = 0; i < length; i++) {   
                         // 如果是基本类型的数字数组，需要手动添加到集合  
                         list.add(Array.get(arr, i));   
                 }  
         } else if (componentType == Integer.class  
                         || componentType == Float.class  
                             || componentType == Double.class  
                                 ||componentType == Long.class) {  
                 // 如果是包装类型，用Arrays的asList()方法  
                 Object[] newArr = (Object[]) Array.newInstance(componentType,  
                                 length);  
                 System.arraycopy(arr, 0, newArr, 0, length);  
                 list = Arrays.asList(newArr);  
          } else {  
                 throw new RuntimeException("请出入数字数组");  
          }  
  
          } else {  
         throw new RuntimeException("请输入数组");  
        }  
         System.out.println("getMin: " + Collections.min(list));
        return Collections.min(list).toString();  
    }
    
    static public double calAngleToRefPositionByPosition(Point2D position, Point2D refPosition) {
        double diffX = position.getX() - refPosition.getX();
        double diffY = position.getY() - refPosition.getY();
        double diff = Math.hypot(diffX, diffY);
        double angle = Math.toDegrees(Math.asin(diffX / diff));
        if (position.getY() > refPosition.getY()) {
            angle = 180 - angle;
        }
        if (position.getX() < refPosition.getX() && position.getY() <= refPosition.getY()) {
            angle = 360 + angle;
        }
        //System.out.println("angle: " + angle);
        return angle;
    }
    
    static public int yearOf(String timeString){
        
        String[] timeComponent = timeString.split("-");
        int year = Integer.valueOf(timeComponent[0]);
        return year;
    }
    
    static public int monthOf(String timeString){
         String[] timeComponent = timeString.split("-");       
        int month = Integer.valueOf(timeComponent[1]);
        return month;
    }
    
    static public int dayOf(String timeString){
         String[] timeComponent = timeString.split("-");
        int day = Integer.valueOf(timeComponent[2]);
       
        return day;
    }
    
    static public ArrayList yearsOf(ArrayList time){
        Iterator itr = time.iterator();
        ArrayList<Integer> years = new ArrayList();
        while(itr.hasNext()){
            String curTime = (String) itr.next();
            int year = yearOf(curTime);
            if(!years.contains(year)){
                years.add(year);
            }
        }
        return years;
    }
    
    static public ArrayList monthsOf(ArrayList time){
        Iterator itr = time.iterator();
        ArrayList<Integer> months = new ArrayList();
        while(itr.hasNext()){
            
        }
        return null;
    }
    
    static  public int yearCount(ArrayList time){
         
         Iterator itr = time.iterator();
         int lastYear = Integer.MIN_VALUE;
         int yearNo = 0;
         while(itr.hasNext()){
             String curTime = (String)itr.next();
             int year = Toolkit.yearOf(curTime);
             if(lastYear == Integer.MIN_VALUE){
                 lastYear = year;
                 yearNo++;
             }else if(year != lastYear){
                 lastYear = year;
                 yearNo++;
             }
             
         }
         System.out.println("Year No: " + yearNo);
         return yearNo;
     }
     
     static public int monthCount(ArrayList time){

         Iterator itr = time.iterator();
         int lastYear = Integer.MIN_VALUE;
         int lastMonth = Integer.MIN_VALUE;
         int monthCount = 0;
         while(itr.hasNext()){
             String curTime = (String)itr.next();
             int year = Toolkit.yearOf(curTime);
             int month = Toolkit.monthOf(curTime);
             if(lastYear == Integer.MIN_VALUE){
                 lastYear = year;
                 lastMonth = month;
                 monthCount++;
             }else if(year == lastYear && month != lastMonth){
                 lastMonth = month;
                 monthCount++;
             }else if(year != lastYear){
                 lastYear = year;
                 lastMonth = month;
                 monthCount++;
             }
             
         }
         System.out.println("Month No: " + monthCount);
         return monthCount;
     }
      
     static public int dayCount(ArrayList time){
          Iterator itr = time.iterator();
          int lastYear = Integer.MIN_VALUE;
          int lastMonth = Integer.MIN_VALUE;
          int lastDay = Integer.MIN_VALUE;
          int dayCount = 0;
          while(itr.hasNext()){
              String curTime = (String)itr.next();
              int year = Toolkit.yearOf(curTime);
              int month = Toolkit.monthOf(curTime);
              int day = Toolkit.dayOf(curTime);
              
              if(lastYear == Integer.MIN_VALUE){
                  lastYear = year;
                  lastMonth = month;
                  lastDay = day;
                  dayCount++;
              }else if(year == lastYear && lastMonth != month){
                  lastYear = year;
                  lastMonth = month;
                  lastDay = day;
                  dayCount++;
              }else if(year == lastYear && lastMonth == month && lastDay != day){
                  lastYear = year;
                  lastMonth = month;
                  lastDay = day;
                  dayCount++;
              }
          }
          System.out.println("Day Count: " + dayCount);
          return dayCount;
      }
     
     
     static double stringToDouble(String value){
         return Double.valueOf(value);
     }
}
