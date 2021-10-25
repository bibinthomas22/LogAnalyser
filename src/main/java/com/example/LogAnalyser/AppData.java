package com.example.LogAnalyser;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.service.AppService;
import com.example.utils.AnalyseLog;
import com.example.utils.VerifyInputFile;

@Configuration
public class AppData {

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
