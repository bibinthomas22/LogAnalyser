package com.example.LogAnalyser;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.db.AlertRepository;
import com.example.service.AppService;
import com.example.utils.AnalyseLog;
import com.example.utils.VerifyInputFile;

@Configuration
@ConfigurationProperties("alert")
public class AppData {
	
	private int maxTimeInSeconds;
	private String filePath;
	
	public void setMaxTimeInSeconds(int maxTimeInSeconds) {
	        this.maxTimeInSeconds = maxTimeInSeconds;
	    }
	
	public void setfilePath(String filePath) {
	        this.filePath = filePath;
	    }
	
	public String getfilePath() {
        return filePath;
    }
	
	public int getMaxTimeInSeconds() {
        return maxTimeInSeconds;
    }

	@Bean
	public AppService appServiceConfig(){
		return new AppService();
	}
	
	@Bean
	public VerifyInputFile verifyInFile(){
		return new VerifyInputFile();
		
	}
	
	@Bean
	public AnalyseLog analyseLog(){
		return new AnalyseLog();
		
	}
	
}
