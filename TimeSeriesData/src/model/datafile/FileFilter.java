/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.datafile;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.DataModel;


/**
 *
 * @author Administrator
 */
public class FileFilter {
    private File file;
    private String metaFilePath;
    public void setFile(File file){
        this.file = file;
    }
    
    private FileFilter(){
        this.setFile(DataModel.getDataModel().getDataFile());
    }
   
    protected static FileFilter filter = null;
    public static FileFilter getFilter(){
        
        if(filter == null){
            filter = new FileFilter(); 
        }
        return filter;
    }
   
    public File getMetaDataFile(){
        File metadataFile = new File(file+".metadata");
        return metadataFile;
    }
    public int getSeriesesSize(){
        try {
            Scanner scanner = new Scanner(this.getMetaDataFile());
            while(scanner.hasNextLine()){
                String currentLine = scanner.nextLine();
                if(currentLine.contains("SeriesesSize")){
                    String[] currentComponents = currentLine.split("=");
                    return Integer.valueOf(currentComponents[1]);
                }else{
                    continue;
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileFilter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    
    public ArrayList<String> getSeriesNames(){
        try {
            Scanner scanner = new Scanner(this.getMetaDataFile());
            while(scanner.hasNextLine()){
                String currentLine = scanner.nextLine();
                if(currentLine.contains("SeriesNames")){
                    String[] currentComponents = currentLine.split("=");
                    String seriesNames = currentComponents[1];
                    seriesNames = seriesNames.trim();
                    if(seriesNames.endsWith(";")){
                        seriesNames = seriesNames.substring(0, seriesNames.length() - 1);
                    }
                    ArrayList<String> seriesNamesArrayList = new ArrayList();
                    String[] names = seriesNames.split(",");
                    for(int i= 0; i < names.length; i++){
                        seriesNamesArrayList.add(names[i].trim());
                    }
                    return seriesNamesArrayList;
                }else{
                    continue;
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileFilter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public String getGranularity(String seriesName){
        return this.invokeFunction(seriesName, "getGranularity");
    }
    
    public String getGranularityMultiple(String seriesName){
        return this.invokeFunction(seriesName, "getGranularityMultiple");
    }
    public String getTimeComponents(String seriesName){
        return this.invokeFunction(seriesName, "getTimeComponents");
    }
    
    public String getStartTime(String seriesName){
        String startTime = this.invokeFunction(seriesName, "getStartTime");
        if(startTime.contains(",")){
            startTime = startTime.replace(",", "-");
        }
        return startTime;
    }
    public String getEndTime(String seriesName){
        String endTime = this.invokeFunction(seriesName, "getEndTime");
        if(endTime.contains(",")){
            endTime = endTime.replace(",", "-");
        }
        return endTime;
    }
    
    public String getValueFormat(String seriesName){
        return  this.invokeFunction(seriesName, "getDataFormat");
    }
    
    private String invokeFunction(String seriesName, String functionName){
        ArrayList<FileInformation> informations = this.getSeriesInformations();
        for(FileInformation information:informations){
            if(information.getSeriesName().equals(seriesName)){
                try {
                    Class infClass = information.getClass();
                    Method method = infClass.getDeclaredMethod(functionName);
                    return (String)method.invoke(information);
                } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                    Logger.getLogger(FileFilter.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return null;
    }
    
    public ArrayList<FileInformation> getSeriesInformations(){
        try {
            ArrayList<FileInformation> informations = new ArrayList();
            Scanner scanner = new Scanner(this.getMetaDataFile());

            while(scanner.hasNext()){
                String currentLine = scanner.nextLine();
                currentLine = currentLine.trim();
                
                if(currentLine.startsWith("Information")){
                    FileInformation information = new FileInformation();
                    while(scanner.hasNextLine()){
                        String currentLineInInformation = scanner.nextLine();
                        currentLineInInformation = currentLineInInformation.trim();
                        if(currentLineInInformation.endsWith(";")){
                            currentLineInInformation = currentLineInInformation.substring(0, currentLineInInformation.length() - 1);
                        }
                        String propertyName = "";
                        if(currentLineInInformation.contains("=")){
                            propertyName = currentLineInInformation.split("=")[0].trim();
                        }else if(currentLineInInformation.contains("}")){
                            informations.add(information);
                            break;
                        }else{
                            continue;
                        }
                        if(propertyName.equals("SeriesName")){
                            information.setSeriesName(currentLineInInformation.split("=")[1].trim());
                            continue;
                        }else if(propertyName.equals("DataFormat")){
                            information.setDataFormat(currentLineInInformation.split("=")[1].trim());
                            continue;
                        }
                        else if(propertyName.equals("Granularity")){
                            information.setGranularity(currentLineInInformation.split("=")[1].trim());
                            continue;
                        }else if(propertyName.equals("GranularityMultiple")){
                            information.setGranularityMultiple(currentLineInInformation.split("=")[1].trim());
                            continue;
                        }else if(propertyName.equals("TimeComponents")){
                            information.setTimeComponents(currentLineInInformation.split("=")[1].trim());
                            continue;
                        }else if(propertyName.equals("StartTime")){
                            information.setStartTime(currentLineInInformation.split("=")[1].trim());
                            continue;
                        }else if(propertyName.equals("EndTime")){
                            information.setEndTime(currentLineInInformation.split("=")[1].trim());
                            continue;
                        }
                    }
                }
            }
            return informations;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileFilter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
      
    public ArrayList<String> getData(String seriesName){
        return this.getAllData().get(seriesName);
    }
    
    public LinkedHashMap<String, ArrayList<String>> getAllData(){
        try {
            LinkedHashMap<String, ArrayList<String>> datas = new LinkedHashMap();
            Scanner scanner = new Scanner(DataModel.getDataModel().getDataFile());
            Pattern pattern = Pattern.compile("[+-]?\\d+(\\.\\d*)?");
            while(scanner.hasNextLine()){
                String currentLine = scanner.nextLine().trim();
                if(currentLine.startsWith("Data Start")){
                    int startIndex = currentLine.indexOf("(") + 1;
                    int endIndex = currentLine.indexOf(")");
                    String seriesName = currentLine.substring(startIndex, endIndex).trim();
                    
                    ArrayList<String> data = new ArrayList();
                    while(scanner.hasNext()){
                        String currentToken =  scanner.next().trim();
                        if(currentToken.startsWith("}")){
                            datas.put(seriesName, data);
                            break;
                        }
                        
                        Matcher matcher = pattern.matcher(currentToken);
                        if(matcher.find()){
                            data.add(matcher.group(0));
                        }
                    }
                    
                }            }
            return datas;
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileFilter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    public ArrayList<String> getTime(String seriesTime){
        String startTime = this.getStartTime(seriesTime);
        String endTime = this.getEndTime(seriesTime);
        String granularity = this.getGranularity(seriesTime);
        String granularityMultiple = this.getGranularityMultiple(seriesTime);
        String timeComponents = this.getTimeComponents(seriesTime);
        
        ArrayList<String> times = new ArrayList();
       
        LocalDateTime startLocalDateTime = LocalDateTime.parse(this.formatTimeToLocalDateTimeStr(startTime, timeComponents));
        LocalDateTime endLocalDateTime = LocalDateTime.parse(this.formatTimeToLocalDateTimeStr(endTime, timeComponents));
       
       
        LocalDateTime current = startLocalDateTime;
        
        times.add(this.formatTimeToDatabaseTimeStr(current.getYear(), current.getMonthValue(), current.getDayOfMonth(), current.getHour(), current.getMinute(), current.getSecond(), current.getNano()));
        while(current.isBefore(endLocalDateTime)){
          if(granularity.equals("year")){
                current = current.plusYears(Integer.valueOf(granularityMultiple));
            }else if(granularity.equals("month")){
                current = current.plusMonths(Integer.valueOf(granularityMultiple));
            }else if(granularity.equals("day")){
                current = current.plusDays(Integer.valueOf(granularityMultiple));
            }else if(granularity.equals("hour")){
                current = current.plusHours(Integer.valueOf(granularityMultiple));
            }else if(granularity.equals("minute")){
                current = current.plusMinutes(Integer.valueOf(granularityMultiple));
            }else if(granularity.equals("second")){
                current = current.plusSeconds(Integer.valueOf(granularityMultiple));
            }else if(granularity.equals("millisecond")){
                current = current.plusNanos(Integer.valueOf(granularityMultiple));
            }
            times.add(this.formatTimeToDatabaseTimeStr(current.getYear(), current.getMonthValue(), current.getDayOfMonth(), current.getHour(), current.getMinute(), current.getSecond(), current.getNano()));
        }
        return times;

        
    }

    public String formatTimeToDatabaseTimeStr(int year, int month, int day, int hour, int minute, int second, int millisecond){
        String yearStr = String.valueOf(year);
        String monthStr = String.valueOf(month);
        String dayStr = String.valueOf(day);
        String hourStr = String.valueOf(hour);
        String minuteStr = String.valueOf(minute);
        String secondStr = String.valueOf(second);
        String millisecondStr = String.valueOf(millisecond);
        while(yearStr.length() < 4){
            yearStr = "0" + yearStr;
        }
        while(monthStr.length() < 2){
            monthStr = "0" + monthStr;
        }
        while(dayStr.length() < 2){
            dayStr = "0" + dayStr;
        }
        while(hourStr.length() < 2){
            hourStr = "0" + hourStr;
        }
        while(minuteStr.length() < 2){
            minuteStr = "0" + minuteStr;
        }
        while(secondStr.length() < 2){
            secondStr = "0" + secondStr;
        }
        while(millisecondStr.length() < 3){
            millisecondStr = "0" + millisecondStr;
        }
        return yearStr + "-" + monthStr + "-" + dayStr + "-" + hourStr + "-"+ minuteStr + "-" + secondStr + "-" + millisecondStr;
    }
    
    //把形如1970分隔符07分隔符01T13分隔符12分隔符11分隔符001转化为 1970-07-01T13:12:11.001格式，如果缺少成分就补全
    public String formatTimeToLocalDateTimeStr(String textTime, String timeComponents){
        String[] components = timeComponents.split(",");
        String formattedTimeStr = "";
        int index = 0;
        int componentIndex = 0;
        if(components[componentIndex].equals("year")){
            formattedTimeStr += textTime.substring(0, 4);
            componentIndex++;
        }else{
            formattedTimeStr += "0000";
        }
        index++;
        if(components.length <= index ){
            formattedTimeStr += "-01-01T00:00:00.000";
            return formattedTimeStr;
        }
        
        if(components[componentIndex].equals("month")){
            formattedTimeStr += "-" + textTime.substring(5,7);
            componentIndex++;
        }else{
            formattedTimeStr += "-00";
        }
        index++;
        if(components.length <= index){
            formattedTimeStr += "-01T00:00:00.000";
            return formattedTimeStr;
        }
        
        if(components[componentIndex].equals("day")){
            formattedTimeStr += "-" + textTime.substring(8,10);
            componentIndex++;
        }else{
            formattedTimeStr += "-00";
        }
        index++;
        if(components.length <= index){
            formattedTimeStr += "T00:00:00.000";
            return formattedTimeStr;
        }
        
        if(components[componentIndex].equals("hour")){
            formattedTimeStr += "T" + textTime.substring(11,13);
            componentIndex++;
        }else{
            formattedTimeStr += "T00";
        }
        index++;
        if(components.length <= index){
            formattedTimeStr += ":00:00.000";
            return formattedTimeStr;
        }
        
        if(components[componentIndex].equals("minute")){
            formattedTimeStr += ":" + textTime.substring(14,16);
            componentIndex++;
        }else{
            formattedTimeStr += ":00";
        }
        index++;
        if(components.length <= index){
            formattedTimeStr += ":00.000";
            return formattedTimeStr;
        }
        
        if(components[componentIndex].equals("second")){
            formattedTimeStr += ":" + textTime.substring(17,19);
            componentIndex++;
        }else{
            formattedTimeStr += ":00";
        }
        index++;
        if(components.length <= index){
            formattedTimeStr += ".000";
            return formattedTimeStr;
        }
        
        if(components[componentIndex].equals("millisecond")){
            formattedTimeStr += "." + textTime.substring(20,23);
            componentIndex++;
        }else{
            formattedTimeStr += ".000";
        }
        
        return formattedTimeStr;
    }
     
}


