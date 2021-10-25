package com.example.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.example.model.AppContext;
import com.example.model.LogEvent;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class AnalyseLog {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AnalyseLog.class);
	public void parseLogsAndCreateAlert(AppContext con) {
	
		try {
		FileReader fr=new FileReader(new File("samples/" + con.getLogFilePath()));
		BufferedReader br=new BufferedReader(fr); 
		//StringBuffer sb=new StringBuffer();   
		String line;  
		while((line=br.readLine())!=null)  
		{  
			LogEvent event = new ObjectMapper().readValue(line, LogEvent.class);
            
		}  
		fr.close();
		}catch(Exception e) {
			
		}
	}
}
