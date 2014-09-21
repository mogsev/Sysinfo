/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sysinfo;

/**
 *
 * @author zhenya mogsev@gmail.com
 */
import java.io.File;
import java.io.FileNotFoundException;

public class Delutil {
    private static String temp = "c://windows//temp//";
    private static String systemtemp;
    private static String userhome;
    private static String internettemp;
    private static StringBuilder result;
    
    /**
     * @return dir the full path on directory "Windows system temp"
     */
    public static String getDirWindowsTemp() {                
        return temp;
    }
    
    /**
     * @return dir the full path on directory "System Temp"
     */
    public static String getDirSystemTemp() {
        systemtemp = System.getProperty("java.io.tmpdir");
        return systemtemp;
    }
    
    /**      
     * @return userhome the full path on directory "User home work"
     */
    public static String getDirUserHome() {
        userhome = System.getProperty("user.home");        
        return userhome;  
    }
    
    /** 
     * @return internettemp dir the full path "User system internet temp" on Windows Vista/7/8
     */
    public static String getDirInternetTemp() {
        result = new StringBuilder();
        internettemp = result.append(Delutil.getDirUserHome() + "//AppData//Local//Microsoft//Windows//Temporary Internet Files//").toString();
        return internettemp;
    }
    
    /** 
     * @return internettemp dir the full path "User system internet temp" on Windows XP
     */
    public static String getDirWinXPInternetTemp() {        
        result = new StringBuilder();
        internettemp = result.append(Delutil.getDirUserHome() + "//Local Settings//Temporary Internet Files//").toString();
        return internettemp;
    }
    
    /**
     * @param dir the full path on directory
     */
    public static void dirfiles(File dir) throws FileNotFoundException {
        if (dir.listFiles() == null) { 
            System.out.println(dir.getAbsolutePath() + " do not have access to the object");
        } else { 
            File[] list = dir.listFiles();
            for (File listto : list) {
                if (listto.isFile()) {                    
                    listto.delete();
                        if (!listto.exists()) {
                            System.out.println(listto.getAbsolutePath() + " this file is deleted");
                        } else {
                            System.out.println(listto.getAbsolutePath() + " this file can not be deleted!");                    
                            }
                }
                if (listto.isDirectory()) {
                    listto.delete();
                    if (!listto.exists()) {
                        System.out.println(listto.getAbsolutePath() + " this directory is deleted");                        
                    } else {
                     System.out.println(listto.getAbsolutePath() + " this directory can not be deleted!");
                     dirfiles(new File(listto.getAbsolutePath()));
                    }
                    listto.delete();
                }            
            } 
        }
    }
}
