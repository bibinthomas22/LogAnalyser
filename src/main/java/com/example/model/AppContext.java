package com.example.model;


public class AppContext {
	
    private static AppContext INSTANCE;

    private String logFilePath;
    
    public static AppContext getInstance() {
        if (INSTANCE == null) INSTANCE = new AppContext();
        return INSTANCE;
    }

    public String getLogFilePath() {
        return logFilePath;
    }

    public void setLogFilePath(String logFilePath) {
        this.logFilePath = logFilePath;
    }
}
