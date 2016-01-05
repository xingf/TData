/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.datafile.FileInformation;
import model.datafile.FileFilter;
import model.database.DataBaseIO;
import model.dataProperty.DataProperty;
import model.timeProperty.TimeProperty;

/**
 *
 * @author Administrator
 */
public class DataModel {
    
    private DataBaseIO dataBaseIO = null;
    private DataBaseIO getDataBaseIO(){
        return this.dataBaseIO;
    }
    
    private FileFilter filter;
    private FileFilter getFilter(){
        return this.filter;
    }
   
    //private String pathName = "C:\\STUDY\\MASTERARBEIT\\DATA\\MHSETS\\ASTATKIE\\FISHER.1";
    //private String pathName = "C:\\STUDY\\MASTERARBEIT\\DATA\\MHSETS\\BOXJENK\\SERIESA.1";
    //private String pathName = "C:\\STUDY\\MASTERARBEIT\\DATA\\MHSETS\\MISC\\SAUGEEN.1";
    //private String pathName = "C:\\STUDY\\MASTERARBEIT\\DATA\\MHSETS\\MISC\\QBIRTHS.1";
    //private String pathName = "C:\\STUDY\\MASTERARBEIT\\DATA\\MHSETS\\ANNUAL\\CORN.2";
    private String pathName = "C:\\STUDY\\MASTERARBEIT\\DATA\\OTHERDATA\\saugeen.2";
    private File dataFile = null;
    
    public void setFilePath(String filePath){
        this.pathName = filePath;
        this.dataFile = this.createDataFile();
//        this.reInit();
    }

    public File createDataFile(){
        this.dataFile = new File(this.pathName);
        return this.dataFile;
    }
    public File getDataFile(){
        if(this.dataFile == null){
            this.createDataFile();
        }
        return this.dataFile;
    }
    
    private DataProperty dataProperty;
    public DataProperty getDataProperty(){
        return this.dataProperty;
    }
    private TimeProperty timeProperty;
    public TimeProperty getTimeProperty(){
        return this.timeProperty;
    }
   
    static protected DataModel dataModel = null;
    private DataModel(){
        //Init DataModel
        this.iniProperty();
        this.dataBaseIO = DataBaseIO.getDataBaseIO();
        //this.filter = FileFilter.getFilter();
    }

    static public DataModel getDataModel(){
        if(dataModel == null){
            dataModel = new DataModel();
        }
        return dataModel;
    }
    
    private void iniProperty(){
        System.out.println("IniProperty Start");
        dataProperty = DataProperty.getDataProperty();
        timeProperty = TimeProperty.getTimeProperty();
        System.out.println("IniProperty End");
    }
    
    
    public void createDatabase(){
        String sql = "DROP DATABASE IF EXISTS TIME_SERIES;"
                + "   CREATE DATABASE TIME_SERIES;  ";
        this.dataBaseIO.executeStatement(sql);
    }
    
    public void createDatabaseTables(){
        FileFilter filter = FileFilter.getFilter();
        ArrayList<String> seriesNames = filter.getSeriesNames();
        //String sql = "USE TIME_SERIES; ";
        //String sql = "";
        for (int i = 0; i < seriesNames.size(); i++) {
            String subSql = "CREATE TABLE IF NOT EXISTS " + seriesNames.get(i) + "("
                    + "IDX INT AUTO_INCREMENT PRIMARY KEY,"
                    //+ "DATAVALUE DOUBLE NOT NULL DEFAULT 0,"
                    + "DATAVALUE VARCHAR(255),"
                    + "DATATIME VARCHAR(255) "
                    + ");  ";
          //  sql += subSql;
            this.dataBaseIO.executeStatement(subSql);
        }
        //this.dataBaseIO.executeStatement(sql);
    }
    
    public void insertData(){
        FileFilter filter = FileFilter.getFilter();
        ArrayList<String> tableNames = filter.getSeriesNames();
        //String sql = "";
        for(int i = 0; i < tableNames.size(); i++){
            String subSql = "INSERT INTO " + tableNames.get(i) + " ( DATAVALUE, DATATIME ) VALUES ";
            ArrayList<String> seriesDataValue = filter.getData(tableNames.get(i));
            ArrayList<String> seriesDataTime = filter.getTime(tableNames.get(i));
            String dataSql = "";
            for(int j=0; j< seriesDataTime.size(); j++){
            //for(int j = 0; j < seriesDataValue.size(); j++){
                dataSql += ",('" + seriesDataValue.get(j) + "', '" + seriesDataTime.get(j)+"')";
            }
            dataSql = dataSql.replaceFirst(",", "");
            subSql += dataSql;
            subSql += ";  ";
            //sql += subSql;
            this.dataBaseIO.executeUpdateStatement(subSql);
        }
        //this.dataBaseIO.executeUpdateStatement(sql);
    }
    
    
    public void loadAllData(){
        //this.createDatabase();
        this.createDatabaseTables();
        this.insertData();;
    }
    
    public ArrayList<String> getDatabaseTime(){
        try {
            String sql = "SELECT " + DataBaseIO.COLUMN_TIME + " FROM " + DataBaseIO.TABLE;
            ResultSet rSet = this.dataBaseIO.executeStatement(sql);
            ArrayList<String> result = new ArrayList();
            rSet.beforeFirst();
            while(rSet.next()){
                result.add(rSet.getString(DataBaseIO.COLUMN_TIME));
            }
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(DataModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public ArrayList<ArrayList<String>> getDatabaseData() {
        try {
            ArrayList<ArrayList<String>> results = new ArrayList();
            int columnSize = DataBaseIO.COLUMN_VALUES.size();
            int columnIndex = 0;
            while (columnIndex < columnSize) {
                String sql = "SELECT " + DataBaseIO.COLUMN_VALUES.get(columnIndex) + " FROM " + DataBaseIO.TABLE;
                ResultSet rSet = this.dataBaseIO.executeStatement(sql);
                ArrayList result = new ArrayList<String>();
                rSet.beforeFirst();
                while (rSet.next()) {
                    result.add(rSet.getString(DataBaseIO.COLUMN_VALUES.get(0)));
                }
                results.add(result);
                columnIndex++;
            }
            return results;
        } catch (SQLException ex) {
            Logger.getLogger(DataModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    private ArrayList<String> getSeriesTime(String seriesName){
        try {
            ArrayList<String> times = new ArrayList();
            String sql = "SELECT DATATIME FROM " + seriesName;
            ResultSet rSet = this.dataBaseIO.executeStatement(sql);
            
            rSet.beforeFirst();
            while (rSet.next()) {
                times.add(rSet.getString("DATATIME"));
            }
            return times;
        } catch (SQLException ex) {
            Logger.getLogger(DataModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    private ArrayList<String> getSeriesValue(String seriesName){
        try {
            ArrayList<String> values = new ArrayList();
            String sql = "SELECT DATAVALUE FROM " + seriesName;
            ResultSet rSet = this.dataBaseIO.executeStatement(sql);
            
            rSet.beforeFirst();
            while (rSet.next()) {
                values.add(rSet.getString("DATAVALUE"));
            }
            return values;
        } catch (SQLException ex) {
            Logger.getLogger(DataModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    private SeriesData getSeriesData(String seriesName){
        try {
            SeriesData seriesData = new SeriesData();
            String sql = "SELECT IDX, DATAVALUE, DATATIME FROM " + seriesName;
            ResultSet rSet = this.dataBaseIO.executeStatement(sql);
            TimeFormat timeFormat = this.getTimeFormat(seriesName);
            rSet.beforeFirst();
            while(rSet.next()){
                seriesData.addOrReplaceItemData(rSet.getString("DATATIME"), FileFilter.getFilter().getValueFormat(seriesName), rSet.getString("DATAVALUE"),timeFormat, rSet.getString("DATATIME"), true, false);
            }
            return seriesData;
        } catch (SQLException ex) {
            Logger.getLogger(DataModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public GlobalData getTimeSeriesData(){
        GlobalData globalData = new GlobalData();
        ArrayList<String> seriesNames = FileFilter.getFilter().getSeriesNames();
        for(String seriesName: seriesNames){
            try {
                String sql = "SELECT IDX, DATAVALUE, DATATIME FROM " + seriesName;
                ResultSet rSet = this.dataBaseIO.executeStatement(sql);
                TimeFormat seriesTimeFormat = this.getTimeFormat(seriesName);
                String seriesValueFormat = FileFilter.getFilter().getValueFormat(seriesName);
                rSet.beforeFirst();
                while(rSet.next()){
                    String curretValue = rSet.getString("DATAVALUE");
                    String currentTime = rSet.getString("DATATIME");
                    globalData.addOrReplaceItemData(seriesName,currentTime, seriesValueFormat, curretValue,seriesTimeFormat, currentTime, true, false);
                }
                globalData.getSeriesData(seriesName).setTimeFormat(seriesTimeFormat);
                globalData.getSeriesData(seriesName).setSeriesValueFormat(seriesValueFormat);
            } catch (SQLException ex) {
                Logger.getLogger(DataModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return globalData;
    }
   
    
    public Metadata calExceptionForSeries() {
        Metadata metadata = new Metadata();
        metadata.setForMultipleValue(false);
        metadata.setForSeries(true);
        GlobalData rawData = this.getTimeSeriesData();
        metadata.setRawData(rawData);
        
        ArrayList<String> seriesNames = FileFilter.getFilter().getSeriesNames();
        for (String seriesName : seriesNames) {
            String dataFormat = FileFilter.getFilter().getValueFormat(seriesName);
            SeriesData seriesData = rawData.getSeriesData(seriesName);
            ArrayList<ItemData> seriesDataInArrayList = seriesData.getDataInArrayList();
            if(dataFormat.equals(FileInformation.DATAFORMAT_DOUBLE)){
                double sum = 0.0;
                for(ItemData itemData: seriesDataInArrayList){
                    sum += Double.valueOf(itemData.getValue());
                }
                double avg = sum / seriesDataInArrayList.size();
                metadata.addData(Metadata.EXPECTION_FORSERIES, String.valueOf(avg), seriesName, null);
            }
        }
        return metadata;
    }
    
    public Metadata calExceptionForSeries(GlobalData dataRange) {
        Metadata metadata = new Metadata();
        metadata.setForMultipleValue(false);
        metadata.setForSeries(true);
        GlobalData rawData = this.getTimeSeriesData();
        metadata.setRawData(rawData);
        
        ArrayList<String> seriesNames = FileFilter.getFilter().getSeriesNames();
        for (String seriesName : seriesNames) {
            String dataFormat = FileFilter.getFilter().getValueFormat(seriesName);
            SeriesData seriesData = rawData.getSeriesData(seriesName);
            ArrayList<ItemData> seriesDataInArrayList = seriesData.getDataInArrayList();
            if(dataFormat.equals(FileInformation.DATAFORMAT_DOUBLE)){
                double sum = 0.0;
                for(ItemData itemData: seriesDataInArrayList){
                    String curTime = itemData.getTime();
                    if(dataRange.getSeriesData(seriesName).contains(curTime)){
                        sum += Double.valueOf(itemData.getValue());
                    }
                }
                double avg = sum / seriesDataInArrayList.size();
                metadata.addData(Metadata.EXPECTION_FORSERIES, String.valueOf(avg), seriesName, null);
            }
        }
        return metadata;
    }
    
    public Metadata calTrendencyForSeries(){
        Metadata metadata = new Metadata();
        metadata.setForMultipleValue(false);
        metadata.setForSeries(true);
        GlobalData rawData = this.getTimeSeriesData();
        metadata.setRawData(rawData);
        int m = 10;
        
        ArrayList<String> seriesNames = FileFilter.getFilter().getSeriesNames();
        for (String seriesName : seriesNames) {
            String dataFormat = FileFilter.getFilter().getValueFormat(seriesName);
            SeriesData seriesData = rawData.getSeriesData(seriesName);
            ArrayList<ItemData> seriesDataInArrayList = seriesData.getDataInArrayList();
            if(dataFormat.equals(FileInformation.DATAFORMAT_DOUBLE)){
                
                int itemCount = seriesDataInArrayList.size();
                for(int i = 0; i < itemCount; i++){
                    ItemData idata = seriesDataInArrayList.get(i);
                    if( i < m){
  // public void addData(String operation, String value, String valuesKey, String valueKey){                      
 //metadata.addData(Metadata.LOCALMAX_FORSERIES, seriesDataInArrayList.get(i - 1).getValue(), seriesName, seriesDataInArrayList.get(i - 1).getKey());                  
                        metadata.addData(Metadata.QUERY_TRENDENCY, idata.getValue(), seriesName, idata.getTime());
                    }else{
                        double subSum = 0.0;
                        for(int j = i - m; j <= i; j++){
                            subSum += Double.valueOf(seriesDataInArrayList.get(j).getValue());
                        }
                        double result = subSum /( m + 1); 
                        metadata.addData(Metadata.QUERY_TRENDENCY, String.valueOf(result), seriesName, idata.getTime());
                    }
                }
                
//                double sum = 0.0;
//                
//                
//                for(ItemData itemData: seriesDataInArrayList){
//                    sum += Double.valueOf(itemData.getValue());
//                }
//                double avg = sum / seriesDataInArrayList.size();
//                metadata.addData(Metadata.EXPECTION_FORSERIES, String.valueOf(avg), seriesName, null);
            }
        }
        return metadata;
    }
    
    
    public Metadata calTrendencyForSeries(GlobalData dataRange){
        Metadata metadata = new Metadata();
        metadata.setForMultipleValue(false);
        metadata.setForSeries(true);
        GlobalData rawData = this.getTimeSeriesData();
        metadata.setRawData(rawData);
        int m = 10;
        
        ArrayList<String> seriesNames = FileFilter.getFilter().getSeriesNames();
        for (String seriesName : seriesNames) {
            String dataFormat = FileFilter.getFilter().getValueFormat(seriesName);
            SeriesData seriesData = rawData.getSeriesData(seriesName);
            
            ArrayList<ItemData> rawSeriesDataInArrayList = seriesData.getDataInArrayList();
            ArrayList<ItemData> filterSeriesDataInArrayList = new ArrayList();
            for(ItemData dt:rawSeriesDataInArrayList){
                if(dataRange.getSeriesData(seriesName).contains(dt.getTime()))
                filterSeriesDataInArrayList.add(dt);
            }
            //ArrayList<ItemData> seriesDataInArrayList = seriesData.getDataInArrayList();
            ArrayList<ItemData> seriesDataInArrayList = filterSeriesDataInArrayList;
            if(dataFormat.equals(FileInformation.DATAFORMAT_DOUBLE)){
                
                int itemCount = seriesDataInArrayList.size();
                for(int i = 0; i < itemCount; i++){
                    ItemData idata = seriesDataInArrayList.get(i);
                    if( i < m){
  // public void addData(String operation, String value, String valuesKey, String valueKey){                      
 //metadata.addData(Metadata.LOCALMAX_FORSERIES, seriesDataInArrayList.get(i - 1).getValue(), seriesName, seriesDataInArrayList.get(i - 1).getKey());                  
                        metadata.addData(Metadata.QUERY_TRENDENCY, idata.getValue(), seriesName, idata.getTime());
                    }else{
                        double subSum = 0.0;
                        for(int j = i - m; j <= i; j++){
                            subSum += Double.valueOf(seriesDataInArrayList.get(j).getValue());
                        }
                        double result = subSum /( m + 1); 
                        metadata.addData(Metadata.QUERY_TRENDENCY, String.valueOf(result), seriesName, idata.getTime());
                    }
                }
                
//                double sum = 0.0;
//                
//                
//                for(ItemData itemData: seriesDataInArrayList){
//                    sum += Double.valueOf(itemData.getValue());
//                }
//                double avg = sum / seriesDataInArrayList.size();
//                metadata.addData(Metadata.EXPECTION_FORSERIES, String.valueOf(avg), seriesName, null);
            }
        }
        return metadata;
    }
    public Metadata calVarianceForSeries(){
        Metadata metadata = new Metadata();
        metadata.setForMultipleValue(false);
        metadata.setForSeries(true);
        GlobalData rawData = this.getTimeSeriesData();
        metadata.setRawData(rawData);
        
        ArrayList<String> seriesNames = FileFilter.getFilter().getSeriesNames();
        for (String seriesName : seriesNames) {
            String dataFormat = FileFilter.getFilter().getValueFormat(seriesName);
            SeriesData seriesData = rawData.getSeriesData(seriesName);
            ArrayList<ItemData> seriesDataInArrayList = seriesData.getDataInArrayList();
            if(dataFormat.equals(FileInformation.DATAFORMAT_DOUBLE)){
                double sum = 0.0;
                for(ItemData itemData: seriesDataInArrayList){
                    sum += Double.valueOf(itemData.getValue());
                }
                double avg = sum / seriesDataInArrayList.size();
                for(ItemData itemData: seriesDataInArrayList){
                    double itemValue =  Double.valueOf(itemData.getValue());
                    double distValue =  itemValue - avg;
                    metadata.addData(Metadata.DISTRIBUTION_FORSERIES, String.valueOf(distValue), seriesName, itemData.getTime());
                }
            }
        }
        return metadata;
    }
    
    
    public Metadata calDistributionForSeries(){
        Metadata metadata = new Metadata();
        metadata.setForMultipleValue(false);
        metadata.setForSeries(true);
        GlobalData rawData = this.getTimeSeriesData();
        metadata.setRawData(rawData);
        
        ArrayList<String> seriesNames = FileFilter.getFilter().getSeriesNames();
        for(String seriesName: seriesNames){
            try {
                //select datavalue,count(*) from mean_daily_precipitation group by datavalue;
                String sql = "SELECT DATAVALUE, COUNT(*) AS COUNTOFVALUE FROM " + seriesName + " GROUP BY DATAVALUE";
                ResultSet rSet = this.dataBaseIO.executeStatement(sql);
                rSet.beforeFirst();
                while(rSet.next()){
                    String curValue = rSet.getString("DATAVALUE");
                    String count = rSet.getString("COUNTOFVALUE");
                    //public void addData(String operation, String value, String valuesKey, String valueKey){
                    metadata.addData(Metadata.DISTRIBUTION_FORSERIES, count, seriesName, curValue);
                }
            } catch (SQLException ex) {
                Logger.getLogger(DataModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return metadata;
    }
    
    
    /**
     * This method calculate max value for each series data
     * @return global max value for each series data
     */
//    public Metadata selectGlobalMaxValueForSeries() {
//        Metadata metadata = new Metadata();
//        metadata.setForMultipleValue(true);
//        metadata.setForSeries(true);
//        metadata.setRawData(this.getTimeSeriesData());
//        try {
//            ArrayList<String> seriesNames = FileFilter.getFilter().getSeriesNames();
//            
//            for (String seriesName : seriesNames) {
//                String sql = "SELECT IDX, DATAVALUE, DATATIME FROM " + seriesName + " ORDER BY DATAVALUE DESC;";
//                ResultSet rSet = this.dataBaseIO.executeStatement(sql);
//                rSet.beforeFirst();
//                if(rSet.next()){
//                    String globalValue = rSet.getString("DATAVALUE");
//                    String globalValueTime = rSet.getString("DATATIME");
//                    metadata.addData(Metadata.GLOBALMAX_FORSERIES, globalValue, seriesName, globalValueTime);
//                    while(rSet.next()){
//                        String currentGlobalValue = rSet.getString("DATAVALUE");
//                        String currentGlobalValueTime = rSet.getString("DATATIME");
//                        if(globalValue.equals(currentGlobalValue)){
//                            metadata.addData(Metadata.GLOBALMAX_FORSERIES, currentGlobalValue, seriesName, currentGlobalValueTime);
//                        }else{
//                            break;
//                        }
//                    }
//                }
//            }
//            return metadata;
//        } catch (SQLException ex) {
//            Logger.getLogger(DataModel.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//    }
    
    public Metadata selectGlobalMaxValueForSeries() {
        Metadata metadata = new Metadata();
        metadata.setForMultipleValue(true);
        metadata.setForSeries(true);
        metadata.setRawData(this.getTimeSeriesData());
        try {
            ArrayList<String> seriesNames = FileFilter.getFilter().getSeriesNames();
            
            for (String seriesName : seriesNames) {
                String sql = "SELECT IDX, DATAVALUE, DATATIME FROM " + seriesName + " ORDER BY CAST(DATAVALUE AS DECIMAL(20, 8)) DESC;";
                ResultSet rSet = this.dataBaseIO.executeStatement(sql);
                rSet.beforeFirst();
                if(rSet.next()){
                    String globalValue = rSet.getString("DATAVALUE");
                    String globalValueTime = rSet.getString("DATATIME");
                    metadata.addData(Metadata.GLOBALMAX_FORSERIES, globalValue, seriesName, globalValueTime);
                    while(rSet.next()){
                        String currentGlobalValue = rSet.getString("DATAVALUE");
                        String currentGlobalValueTime = rSet.getString("DATATIME");
                        if(globalValue.equals(currentGlobalValue)){
                            metadata.addData(Metadata.GLOBALMAX_FORSERIES, currentGlobalValue, seriesName, currentGlobalValueTime);
                        }else{
                            break;
                        }
                    }
                }
            }
            return metadata;
        } catch (SQLException ex) {
            Logger.getLogger(DataModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Metadata selectGlobalMaxValueForSeries(GlobalData dataRange) {
        Metadata metadata = new Metadata();
        metadata.setForMultipleValue(true);
        metadata.setForSeries(true);
        metadata.setRawData(this.getTimeSeriesData());
        try {
            ArrayList<String> seriesNames = FileFilter.getFilter().getSeriesNames();
            
            for (String seriesName : seriesNames) {
                String sql = "SELECT IDX, DATAVALUE, DATATIME FROM " + seriesName + " ORDER BY CAST(DATAVALUE AS DECIMAL(20, 8)) DESC;";
                ResultSet rSet = this.dataBaseIO.executeStatement(sql);
                rSet.beforeFirst();
                if(rSet.next()){
                    String globalValue = rSet.getString("DATAVALUE");
                    String globalValueTime = rSet.getString("DATATIME");
                    metadata.addData(Metadata.GLOBALMAX_FORSERIES, globalValue, seriesName, globalValueTime);
                    while(rSet.next()){
                        String currentGlobalValue = rSet.getString("DATAVALUE");
                        String currentGlobalValueTime = rSet.getString("DATATIME");
                        if(globalValue.equals(currentGlobalValue)){
                            if(dataRange.getSeriesData(seriesName).contains(currentGlobalValueTime)){
                                metadata.addData(Metadata.GLOBALMAX_FORSERIES, currentGlobalValue, seriesName, currentGlobalValueTime);
                            }
                        }else{
                            break;
                        }
                    }
                }
            }
            return metadata;
        } catch (SQLException ex) {
            Logger.getLogger(DataModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    public Metadata selectGlobalMinValueForSeries(){
        Metadata metadata = new Metadata();
        metadata.setForMultipleValue(false);
        metadata.setForSeries(true);
        metadata.setRawData(this.getTimeSeriesData());
        try {
            ArrayList<String> seriesNames = FileFilter.getFilter().getSeriesNames();
            for (String seriesName : seriesNames) {
                String sql = "SELECT IDX, DATAVALUE, DATATIME FROM " + seriesName + " ORDER BY CAST(DATAVALUE AS DECIMAL(20,8));";
                ResultSet rSet = this.dataBaseIO.executeStatement(sql);
                rSet.beforeFirst();
                if(rSet.next()){
                    String globalValue = rSet.getString("DATAVALUE");
                    String globalValueTime = rSet.getString("DATATIME");
                    metadata.addData(Metadata.GLOBALMIN_FORSERIES, globalValue, seriesName, globalValueTime);
                    while(rSet.next()){
                        String currentGlobalValue = rSet.getString("DATAVALUE");
                        String currentGlobalValueTime = rSet.getString("DATATIME");
                        if(globalValue.equals(currentGlobalValue)){
                            metadata.addData(Metadata.GLOBALMAX_FORSERIES, currentGlobalValue, seriesName, currentGlobalValueTime);
                        }else{
                            break;
                        }
                    }
                }
            }
            return metadata;
        } catch (SQLException ex) {
            Logger.getLogger(DataModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
     public Metadata selectGlobalMinValueForSeries(GlobalData dataRange){
        Metadata metadata = new Metadata();
        metadata.setForMultipleValue(false);
        metadata.setForSeries(true);
        metadata.setRawData(this.getTimeSeriesData());
        try {
            ArrayList<String> seriesNames = FileFilter.getFilter().getSeriesNames();
            for (String seriesName : seriesNames) {
                String sql = "SELECT IDX, DATAVALUE, DATATIME FROM " + seriesName + " ORDER BY CAST(DATAVALUE AS DECIMAL(20,8));";
                ResultSet rSet = this.dataBaseIO.executeStatement(sql);
                rSet.beforeFirst();
                if(rSet.next()){
                    String globalValue = rSet.getString("DATAVALUE");
                    String globalValueTime = rSet.getString("DATATIME");
                    metadata.addData(Metadata.GLOBALMIN_FORSERIES, globalValue, seriesName, globalValueTime);
                    while(rSet.next()){
                        String currentGlobalValue = rSet.getString("DATAVALUE");
                        String currentGlobalValueTime = rSet.getString("DATATIME");
                        if(globalValue.equals(currentGlobalValue)){
                            if(dataRange.getSeriesData(seriesName).contains(currentGlobalValueTime)){
                                metadata.addData(Metadata.GLOBALMAX_FORSERIES, currentGlobalValue, seriesName, currentGlobalValueTime);
                            }
                            
                        }else{
                            break;
                        }
                    }
                }
            }
            return metadata;
        } catch (SQLException ex) {
            Logger.getLogger(DataModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
   
    
    public Metadata selectLocalMaxValueForSeries(){
        
        Metadata metadata = new Metadata();
        metadata.setForSeries(true);
        metadata.setForMultipleValue(true);
        GlobalData rawData = this.getTimeSeriesData();
        metadata.setRawData(rawData);
        for(String seriesName: rawData.getSeriesNames()){
            SeriesData seriesData = rawData.getSeriesData(seriesName);
            ArrayList<ItemData> seriesDataInArrayList = seriesData.getDataInArrayList();
            String seriesValueFormat = metadata.getRawData().getSeriesData(seriesName).getSeriesValueFormat();
            boolean lastValueIsBiggerOrEqualThanBefore = true;
            for(int i = 1; i < seriesDataInArrayList.size(); i++){
                
                if(seriesValueFormat.equals("double")){
                    double currentValue = Double.valueOf(seriesDataInArrayList.get(i).getValue());
                    double lastValue = Double.valueOf(seriesDataInArrayList.get(i - 1).getValue());
                    if(lastValueIsBiggerOrEqualThanBefore){
                        if(lastValue > currentValue){
                            metadata.addData(Metadata.LOCALMAX_FORSERIES, seriesDataInArrayList.get(i - 1).getValue(), seriesName, seriesDataInArrayList.get(i - 1).getKey());
                            lastValueIsBiggerOrEqualThanBefore = false;
                        }else if(currentValue == lastValue){
                            metadata.addData(Metadata.LOCALMAX_FORSERIES, seriesDataInArrayList.get(i - 1).getValue(), seriesName, seriesDataInArrayList.get(i - 1).getKey());
                            lastValueIsBiggerOrEqualThanBefore = true;
                        }else {
                            lastValueIsBiggerOrEqualThanBefore = true; 
                        }
                    }else{
                        if(currentValue >= lastValue){
                            lastValueIsBiggerOrEqualThanBefore = true; 
                        }
                    }
                }
                
                
            }
            if(lastValueIsBiggerOrEqualThanBefore){
                metadata.addData(Metadata.LOCALMAX_FORSERIES, seriesDataInArrayList.get(seriesDataInArrayList.size() - 1).getValue(), seriesName, seriesDataInArrayList.get(seriesDataInArrayList.size() - 1).getKey());
                
            }
            
            
        }
        return metadata;
    }
    
    
    public Metadata selectLocalMaxValueForSeries(GlobalData dataRange){
        
        Metadata metadata = new Metadata();
        metadata.setForSeries(true);
        metadata.setForMultipleValue(true);
        GlobalData rawData = this.getTimeSeriesData();
        metadata.setRawData(rawData);
        for(String seriesName: rawData.getSeriesNames()){
            SeriesData seriesData = rawData.getSeriesData(seriesName);
            
            ArrayList<ItemData> rawSeriesDataInArrayList = seriesData.getDataInArrayList();
            ArrayList<ItemData> filteredSeriesDataInArrayList = new ArrayList();
            for(ItemData idata:rawSeriesDataInArrayList){
                if(dataRange.getSeriesData(seriesName).contains(idata.getTime())){
                    filteredSeriesDataInArrayList.add(idata);
                }
            }
            
            //ArrayList<ItemData> seriesDataInArrayList = seriesData.getDataInArrayList();
            ArrayList<ItemData> seriesDataInArrayList = filteredSeriesDataInArrayList;
            String seriesValueFormat = metadata.getRawData().getSeriesData(seriesName).getSeriesValueFormat();
            boolean lastValueIsBiggerOrEqualThanBefore = true;
            for(int i = 1; i < seriesDataInArrayList.size(); i++){
                
                if(seriesValueFormat.equals("double")){
                    double currentValue = Double.valueOf(seriesDataInArrayList.get(i).getValue());
                    double lastValue = Double.valueOf(seriesDataInArrayList.get(i - 1).getValue());
                    if(lastValueIsBiggerOrEqualThanBefore){
                        if(lastValue > currentValue){
                            metadata.addData(Metadata.LOCALMAX_FORSERIES, seriesDataInArrayList.get(i - 1).getValue(), seriesName, seriesDataInArrayList.get(i - 1).getKey());
                            lastValueIsBiggerOrEqualThanBefore = false;
                        }else if(currentValue == lastValue){
                            metadata.addData(Metadata.LOCALMAX_FORSERIES, seriesDataInArrayList.get(i - 1).getValue(), seriesName, seriesDataInArrayList.get(i - 1).getKey());
                            lastValueIsBiggerOrEqualThanBefore = true;
                        }else {
                            lastValueIsBiggerOrEqualThanBefore = true; 
                        }
                    }else{
                        if(currentValue >= lastValue){
                            lastValueIsBiggerOrEqualThanBefore = true; 
                        }
                    }
                }
                
                
            }
            if(lastValueIsBiggerOrEqualThanBefore){
                metadata.addData(Metadata.LOCALMAX_FORSERIES, seriesDataInArrayList.get(seriesDataInArrayList.size() - 1).getValue(), seriesName, seriesDataInArrayList.get(seriesDataInArrayList.size() - 1).getKey());
                
            }
            
            
        }
        return metadata;
    }
    
    public Metadata selectLocalMinValueForSeries(){
        Metadata metadata = new Metadata();
        metadata.setForSeries(true);
        metadata.setForMultipleValue(true);
        GlobalData rawData = this.getTimeSeriesData();
        metadata.setRawData(rawData);
        ArrayList<String> seriesNames = rawData.getSeriesNames();
        for(String seriesName: seriesNames){
            SeriesData seriesData = rawData.getSeriesData(seriesName);
            String seriesValueFormat = seriesData.getSeriesValueFormat();
    
            ArrayList<ItemData> seriesDataInArrayList = seriesData.getDataInArrayList();
            boolean lastValueIsSmallerorEqualThanBefore = true;
            for(int i = 1; i < seriesDataInArrayList.size(); i++){
                if(seriesValueFormat.equals("double")){
                    double currentValue = Double.valueOf(seriesDataInArrayList.get(i).getValue());
                    double lastValue = Double.valueOf(seriesDataInArrayList.get(i - 1).getValue());
                    if (lastValueIsSmallerorEqualThanBefore) {
                           if(lastValue < currentValue){
                               metadata.addData(Metadata.LOCALMIN_FORSERIES, seriesDataInArrayList.get(i - 1).getValue(), seriesName, seriesDataInArrayList.get(i - 1).getKey());
                               lastValueIsSmallerorEqualThanBefore = false;
                           }else if(lastValue == currentValue){
                               metadata.addData(Metadata.LOCALMIN_FORSERIES, seriesDataInArrayList.get(i - 1).getValue(), seriesName, seriesDataInArrayList.get(i - 1).getKey());
                               lastValueIsSmallerorEqualThanBefore = true;
                           }else{
                               lastValueIsSmallerorEqualThanBefore = true;
                           }
                           
                    } else {
                        if(currentValue <= lastValue){
                            lastValueIsSmallerorEqualThanBefore = true;
                        }
                    }
                }  
            }
            if(lastValueIsSmallerorEqualThanBefore = true){
                metadata.addData(Metadata.LOCALMIN_FORSERIES, seriesDataInArrayList.get(seriesDataInArrayList.size() - 1).getValue(), seriesName, seriesDataInArrayList.get(seriesDataInArrayList.size() - 1).getKey());
            }
            
        }
        return metadata;
    }
    public Metadata selectLocalMinValueForSeries(GlobalData dataRange){
        Metadata metadata = new Metadata();
        metadata.setForSeries(true);
        metadata.setForMultipleValue(true);
        GlobalData rawData = this.getTimeSeriesData();
        metadata.setRawData(rawData);
        ArrayList<String> seriesNames = rawData.getSeriesNames();
        for(String seriesName: seriesNames){
            SeriesData seriesData = rawData.getSeriesData(seriesName);
            String seriesValueFormat = seriesData.getSeriesValueFormat();
            
            ArrayList<ItemData> rawSeriesDataInArrayList = seriesData.getDataInArrayList();
            ArrayList<ItemData> filteredSeriesDataInArrayList = new ArrayList();
            for(ItemData idata:rawSeriesDataInArrayList){
                if(dataRange.getSeriesData(seriesName).contains(idata.getTime())){
                    filteredSeriesDataInArrayList.add(idata);
                }
            }
            
            //ArrayList<ItemData> seriesDataInArrayList = seriesData.getDataInArrayList();
            ArrayList<ItemData> seriesDataInArrayList = filteredSeriesDataInArrayList;
            
            boolean lastValueIsSmallerorEqualThanBefore = true;
            for(int i = 1; i < seriesDataInArrayList.size(); i++){
                if(seriesValueFormat.equals("double")){
                    double currentValue = Double.valueOf(seriesDataInArrayList.get(i).getValue());
                    double lastValue = Double.valueOf(seriesDataInArrayList.get(i - 1).getValue());
                    if (lastValueIsSmallerorEqualThanBefore) {
                           if(lastValue < currentValue){
                               metadata.addData(Metadata.LOCALMIN_FORSERIES, seriesDataInArrayList.get(i - 1).getValue(), seriesName, seriesDataInArrayList.get(i - 1).getKey());
                               lastValueIsSmallerorEqualThanBefore = false;
                           }else if(lastValue == currentValue){
                               metadata.addData(Metadata.LOCALMIN_FORSERIES, seriesDataInArrayList.get(i - 1).getValue(), seriesName, seriesDataInArrayList.get(i - 1).getKey());
                               lastValueIsSmallerorEqualThanBefore = true;
                           }else{
                               lastValueIsSmallerorEqualThanBefore = true;
                           }
                           
                    } else {
                        if(currentValue <= lastValue){
                            lastValueIsSmallerorEqualThanBefore = true;
                        }
                    }
                }  
            }
            if(lastValueIsSmallerorEqualThanBefore = true){
                metadata.addData(Metadata.LOCALMIN_FORSERIES, seriesDataInArrayList.get(seriesDataInArrayList.size() - 1).getValue(), seriesName, seriesDataInArrayList.get(seriesDataInArrayList.size() - 1).getKey());
            }
            
        }
        return metadata;
    }

    public Metadata selectValueAboveForSeries(String comparedValue){
        Metadata metadata = new Metadata();
        metadata.setForSeries(true);
        metadata.setForMultipleValue(true);
        GlobalData rawData = this.getTimeSeriesData();
        metadata.setRawData(rawData);
        ArrayList<String> seriesNames = rawData.getSeriesNames();
        
        for(String seriesName: seriesNames){
            try {
                String sql = "SELECT IDX,DATAVALUE,DATATIME FROM " + seriesName + " WHERE CAST(DATAVALUE AS DECIMAL(25, 8)) >= " + comparedValue;
                ResultSet rSet = this.dataBaseIO.executeStatement(sql);
                //public void addData(String operation, String value, String valuesKey, String valueKey)
                rSet.beforeFirst();
                while(rSet.next()){
                    metadata.addData(Metadata.QUERY_VALUE_ABOVE, rSet.getString("DATAVALUE"), seriesName, rSet.getString("DATATIME"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(DataModel.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        return metadata;
    }
    
    
    public Metadata selectValueAboveForSeries(String comparedValue, GlobalData dataRange){
        Metadata metadata = new Metadata();
        metadata.setForSeries(true);
        metadata.setForMultipleValue(true);
        GlobalData rawData = this.getTimeSeriesData();
        metadata.setRawData(rawData);
        ArrayList<String> seriesNames = rawData.getSeriesNames();
        
        for(String seriesName: seriesNames){
            try {
                String sql = "SELECT IDX,DATAVALUE,DATATIME FROM " + seriesName + " WHERE CAST(DATAVALUE AS DECIMAL(25, 8)) >= " + comparedValue;
                ResultSet rSet = this.dataBaseIO.executeStatement(sql);
                //public void addData(String operation, String value, String valuesKey, String valueKey)
                rSet.beforeFirst();
                while(rSet.next()){
                    if(dataRange.getSeriesData(seriesName).contains(rSet.getString("DATATIME"))){
                        metadata.addData(Metadata.QUERY_VALUE_ABOVE, rSet.getString("DATAVALUE"), seriesName, rSet.getString("DATATIME"));
                    }
                    
                }
            } catch (SQLException ex) {
                Logger.getLogger(DataModel.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        return metadata;
    }
    
    
    public Metadata selectValueBelowForSeries(String comparedValue){
        Metadata metadata = new Metadata();
        metadata.setForSeries(true);
        metadata.setForMultipleValue(true);
        GlobalData rawData = this.getTimeSeriesData();
        metadata.setRawData(rawData);
        ArrayList<String> seriesNames = rawData.getSeriesNames();
        
        for(String seriesName: seriesNames){
            try {
                String sql = "SELECT IDX,DATAVALUE,DATATIME FROM " + seriesName + " WHERE CAST(DATAVALUE AS DECIMAL(25, 8)) <= " + comparedValue;
                ResultSet rSet = this.dataBaseIO.executeStatement(sql);
                //public void addData(String operation, String value, String valuesKey, String valueKey)
                rSet.beforeFirst();
                while(rSet.next()){
                    metadata.addData(Metadata.QUERY_VALUE_BELOW, rSet.getString("DATAVALUE"), seriesName, rSet.getString("DATATIME"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(DataModel.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        return metadata;
    }
    
    public Metadata selectValueBelowForSeries(String comparedValue, GlobalData dataRange){
        Metadata metadata = new Metadata();
        metadata.setForSeries(true);
        metadata.setForMultipleValue(true);
        GlobalData rawData = this.getTimeSeriesData();
        metadata.setRawData(rawData);
        ArrayList<String> seriesNames = rawData.getSeriesNames();
        
        for(String seriesName: seriesNames){
            try {
                String sql = "SELECT IDX,DATAVALUE,DATATIME FROM " + seriesName + " WHERE CAST(DATAVALUE AS DECIMAL(25, 8)) <= " + comparedValue;
                ResultSet rSet = this.dataBaseIO.executeStatement(sql);
                //public void addData(String operation, String value, String valuesKey, String valueKey)
                rSet.beforeFirst();
                while(rSet.next()){
                    if(dataRange.getSeriesData(seriesName).contains(rSet.getString("DATATIME")));
                    metadata.addData(Metadata.QUERY_VALUE_BELOW, rSet.getString("DATAVALUE"), seriesName, rSet.getString("DATATIME"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(DataModel.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        return metadata;
    }
    
    public Metadata selectValueBetweenForSeries(String smallerValue, String biggerValue){
        Metadata metadata = new Metadata();
        metadata.setForSeries(true);
        metadata.setForMultipleValue(true);
        GlobalData rawData = this.getTimeSeriesData();
        metadata.setRawData(rawData);
        ArrayList<String> seriesNames = rawData.getSeriesNames();
        
        for(String seriesName: seriesNames){
            try {
                String sql = "SELECT IDX,DATAVALUE,DATATIME FROM " + seriesName + " WHERE CAST(DATAVALUE AS DECIMAL(25, 8)) BETWEEN " + smallerValue + " AND  " + biggerValue;
                ResultSet rSet = this.dataBaseIO.executeStatement(sql);
                //public void addData(String operation, String value, String valuesKey, String valueKey)
                rSet.beforeFirst();
                while(rSet.next()){
                    metadata.addData(Metadata.QUERY_VALUE_BETWEEN, rSet.getString("DATAVALUE"), seriesName, rSet.getString("DATATIME"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(DataModel.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        return metadata;
    }
    
    public Metadata selectValueBetweenForSeries(String smallerValue, String biggerValue, GlobalData dataRange){
        Metadata metadata = new Metadata();
        metadata.setForSeries(true);
        metadata.setForMultipleValue(true);
        GlobalData rawData = this.getTimeSeriesData();
        metadata.setRawData(rawData);
        ArrayList<String> seriesNames = rawData.getSeriesNames();
        
        for(String seriesName: seriesNames){
            try {
                String sql = "SELECT IDX,DATAVALUE,DATATIME FROM " + seriesName + " WHERE CAST(DATAVALUE AS DECIMAL(25, 8)) BETWEEN " + smallerValue + " AND  " + biggerValue;
                ResultSet rSet = this.dataBaseIO.executeStatement(sql);
                //public void addData(String operation, String value, String valuesKey, String valueKey)
                rSet.beforeFirst();
                while(rSet.next()){
                    if(dataRange.getSeriesData(seriesName).contains(rSet.getString("DATATIME"))){
                        metadata.addData(Metadata.QUERY_VALUE_BETWEEN, rSet.getString("DATAVALUE"), seriesName, rSet.getString("DATATIME"));
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(DataModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return metadata;
    }
    
    private int compareTime(String time1, String time2){
        String[] time_1 = time1.trim().split("-");
        String[] time_2 = time2.trim().split("-");
        
        int l1 = time_1.length;
        int l2 = time_2.length;
        int shortLength = l1;
        if(l2 < l1){
            shortLength = l2;
        }
        
        for(int i = 0; i < shortLength; i++){
            int t1 = Integer.valueOf(time_1[i]);
            int t2 = Integer.valueOf(time_2[i]);
            if(t1 < t2){
                return -1;
            }else if(t1 > t2){
                return 1;
            }
        }
        return 0;
    }
    
    
    public Metadata selectDataLaterForSeries(String comparedTime){
        Metadata metadata = new Metadata();
        metadata.setForSeries(true);
        metadata.setForMultipleValue(true);
        GlobalData rawData = this.getTimeSeriesData();
        metadata.setRawData(rawData);
        ArrayList<String> seriesNames = rawData.getSeriesNames();
        
        for(String seriesName: seriesNames){
            SeriesData seriesData = rawData.getSeriesData(seriesName);
            String seriesValueFormat = seriesData.getSeriesValueFormat();
            ArrayList<ItemData> seriesDataInArrayList = seriesData.getDataInArrayList();
            for(ItemData idata: seriesDataInArrayList){
                if(compareTime(idata.getTime(),comparedTime) > 0){
                    metadata.addData(Metadata.QUERY_DATA_LATER, idata.getValue(), seriesName, idata.getTime());
                }
            }
        }
        return metadata;
    }
    
    
    public Metadata selectDataLaterForSeries(String comparedTime, GlobalData dataRange){
        Metadata metadata = new Metadata();
        metadata.setForSeries(true);
        metadata.setForMultipleValue(true);
        GlobalData rawData = this.getTimeSeriesData();
        metadata.setRawData(rawData);
        ArrayList<String> seriesNames = rawData.getSeriesNames();
        
        for(String seriesName: seriesNames){
            SeriesData seriesData = rawData.getSeriesData(seriesName);
            String seriesValueFormat = seriesData.getSeriesValueFormat();
            ArrayList<ItemData> seriesDataInArrayList = seriesData.getDataInArrayList();
            for(ItemData idata: seriesDataInArrayList){
                if(compareTime(idata.getTime(),comparedTime) > 0){
                    if(dataRange.getSeriesData(seriesName).contains(idata.getTime())){
                        metadata.addData(Metadata.QUERY_DATA_LATER, idata.getValue(), seriesName, idata.getTime());
                    }
                    
                }
            }
        }
        return metadata;
    }
    
    
     public Metadata selectDataBeforeForSeries(String comparedTime){
        Metadata metadata = new Metadata();
        metadata.setForSeries(true);
        metadata.setForMultipleValue(true);
        GlobalData rawData = this.getTimeSeriesData();
        metadata.setRawData(rawData);
        ArrayList<String> seriesNames = rawData.getSeriesNames();
        
        for(String seriesName: seriesNames){
            SeriesData seriesData = rawData.getSeriesData(seriesName);
            String seriesValueFormat = seriesData.getSeriesValueFormat();
            ArrayList<ItemData> seriesDataInArrayList = seriesData.getDataInArrayList();
            for(ItemData idata: seriesDataInArrayList){
                if(compareTime(idata.getTime(),comparedTime) < 0){
                    metadata.addData(Metadata.QUERY_DATA_BEFORE, idata.getValue(), seriesName, idata.getTime());
                }
            }
        }
        return metadata;
    }
     
     
      public Metadata selectDataBeforeForSeries(String comparedTime, GlobalData dataRange){
        Metadata metadata = new Metadata();
        metadata.setForSeries(true);
        metadata.setForMultipleValue(true);
        GlobalData rawData = this.getTimeSeriesData();
        metadata.setRawData(rawData);
        ArrayList<String> seriesNames = rawData.getSeriesNames();
        
        for(String seriesName: seriesNames){
            SeriesData seriesData = rawData.getSeriesData(seriesName);
            String seriesValueFormat = seriesData.getSeriesValueFormat();
            ArrayList<ItemData> seriesDataInArrayList = seriesData.getDataInArrayList();
            for(ItemData idata: seriesDataInArrayList){
                if(compareTime(idata.getTime(),comparedTime) < 0){
                    if(dataRange.getSeriesData(seriesName).contains(idata.getTime()))
                    metadata.addData(Metadata.QUERY_DATA_BEFORE, idata.getValue(), seriesName, idata.getTime());
                }
            }
        }
        return metadata;
    }
     
     public Metadata selectDataBetweenForSeries(String smallerTime, String biggerTime){
        Metadata metadata = new Metadata();
        metadata.setForSeries(true);
        metadata.setForMultipleValue(true);
        GlobalData rawData = this.getTimeSeriesData();
        metadata.setRawData(rawData);
        ArrayList<String> seriesNames = rawData.getSeriesNames();
        
        for(String seriesName: seriesNames){
            SeriesData seriesData = rawData.getSeriesData(seriesName);
            String seriesValueFormat = seriesData.getSeriesValueFormat();
            ArrayList<ItemData> seriesDataInArrayList = seriesData.getDataInArrayList();
            for(ItemData idata: seriesDataInArrayList){
                if(compareTime(idata.getTime(),biggerTime) < 0 && compareTime(idata.getTime(),smallerTime) > 0){
                    metadata.addData(Metadata.QUERY_DATA_BETWEEN, idata.getValue(), seriesName, idata.getTime());
                }
            }
        }
        return metadata;
    }
    
     
      public Metadata selectDataBetweenForSeries(String smallerTime, String biggerTime, GlobalData dataRange){
        Metadata metadata = new Metadata();
        metadata.setForSeries(true);
        metadata.setForMultipleValue(true);
        GlobalData rawData = this.getTimeSeriesData();
        metadata.setRawData(rawData);
        ArrayList<String> seriesNames = rawData.getSeriesNames();
        
        for(String seriesName: seriesNames){
            SeriesData seriesData = rawData.getSeriesData(seriesName);
            String seriesValueFormat = seriesData.getSeriesValueFormat();
            ArrayList<ItemData> seriesDataInArrayList = seriesData.getDataInArrayList();
            for(ItemData idata: seriesDataInArrayList){
                if(compareTime(idata.getTime(),biggerTime) < 0 && compareTime(idata.getTime(),smallerTime) > 0){
                    if(dataRange.getSeriesData(seriesName).contains(idata.getTime()))
                    metadata.addData(Metadata.QUERY_DATA_BETWEEN, idata.getValue(), seriesName, idata.getTime());
                }
            }
        }
        return metadata;
    }
    
    public boolean deleteTables(){
        
        for(String table: this.getTimeSeriesData().getSeriesNames()){
            String sql = "DROP TABLE ";
            sql += table + "; ";
            this.dataBaseIO.executeStatement(sql);
        }
        
        
        return true;
    }
   
    public ArrayList<String> getValueTableNameArrayList(){
        ArrayList<String> seriesNames = new ArrayList();
        try {

            String sql = "select COLUMN_NAME from information_schema.`COLUMNS` where column_name like 'DATA_VALUE%';";
            ResultSet rSet = this.dataBaseIO.executeStatement(sql);
            rSet.beforeFirst();
            while (rSet.next()) {
                seriesNames.add(rSet.getString("COLUMN_NAME"));
            }
            return seriesNames;
        } catch (SQLException ex) {
            Logger.getLogger(DataModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private ArrayList<String> getTimeTableNameArrayList(){
        ArrayList<String> seriesTime = new ArrayList();
        try {

            String sql = "SELECT COLUMN_NAME from information_schema.`COLUMNS` WHERE COLUMN_NAME LIKE 'TIMEPOINT%';";
            ResultSet rSet = this.dataBaseIO.executeStatement(sql);
            rSet.beforeFirst();
            while (rSet.next()) {
                seriesTime.add(rSet.getString("COLUMN_NAME"));
            }
            return seriesTime;
        } catch (SQLException ex) {
            Logger.getLogger(DataModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return seriesTime;
    }
    
    private ArrayList<String> getValueTableNameWrappedByFunctionArrayList(String sqlFunctionName){
        ArrayList<String> valueTableNameArrayList = this.getValueTableNameArrayList();
        ArrayList<String> valueTableNameWrappedByFunctionArrayList = new ArrayList();
        for(String valueTableName:valueTableNameArrayList){
            valueTableNameWrappedByFunctionArrayList.add(sqlFunctionName + "(" + valueTableName + ")");
        }
        return valueTableNameWrappedByFunctionArrayList;
    }
    
    private String getValueTableNameWrappedByFunctionString(String sqlFunctionName){
        ArrayList<String> valueTableNameWrappedByFunctionArrayList = this.getValueTableNameWrappedByFunctionArrayList(sqlFunctionName);
        String valueTableNameWrappedByFunctionString = "";
        for(String valueTableNameWrappedByFunction:valueTableNameWrappedByFunctionArrayList){
            valueTableNameWrappedByFunctionString += valueTableNameWrappedByFunction + " ";
        }
        valueTableNameWrappedByFunctionString = valueTableNameWrappedByFunctionString.trim();
        return valueTableNameWrappedByFunctionString;
    }
    
    private String getTimeTableNameString(String split){
        ArrayList<String> timeTableNameArrayList = this.getTimeTableNameArrayList();
        String timeTableNameString = "";
        for(String timeTableName: timeTableNameArrayList){
            timeTableNameString += timeTableName + split;
        }
        int length = timeTableNameString.length();
        String result = timeTableNameString.substring(0, length - 1);
        return result;
    }
    
    private String getValueTableNameString(String split){
        ArrayList<String> valueTableNameArrayList = this.getValueTableNameArrayList();
        String valueTableNamesString = "";
        for(String valueTableName: valueTableNameArrayList){
            valueTableNamesString += valueTableName + split;
        }
        int length = valueTableNamesString.length();
        String result = valueTableNamesString.substring(0, length - 1);
        return result;
    }
    
    
    private TimeFormat getTimeFormat(String seriesName){
        String timeComponents = FileFilter.getFilter().getTimeComponents(seriesName);
        String[] components = timeComponents.split(",");
        TimeFormat timeFormat = new TimeFormat();
        for(String component: components){
            if(component.trim().equals(TimeFormat.YEAR)){
                timeFormat.setYear(true);
            }else if(component.trim().equals(TimeFormat.MONTH)){
                timeFormat.setMonth(true);
            }else if(component.trim().equals(TimeFormat.DAY)){
                timeFormat.setDay(true);
            }else if(component.trim().equals(TimeFormat.HOUR)){
                timeFormat.setHour(true);
            }else if(component.trim().equals(TimeFormat.MINUTE)){
                timeFormat.setMinute(true);
            }else if(component.trim().equals(TimeFormat.SECOND)){
                timeFormat.setSecond(true);
            }else if(component.trim().equals(TimeFormat.MILLISECOND)){
                timeFormat.setMillisecond(true);
            }
        }
        String granularity = FileFilter.getFilter().getGranularity(seriesName);
        String granularityMultiple = FileFilter.getFilter().getGranularityMultiple(seriesName);
        timeFormat.setGranularity(granularity);
        timeFormat.setGranularityMultiple(granularityMultiple);
        return timeFormat;
    }
}
