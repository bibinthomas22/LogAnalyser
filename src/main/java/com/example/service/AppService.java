package com.example.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LogAnalyser.AppData;
import com.example.model.AppContext;
import com.example.utils.AnalyseLog;
import com.example.utils.VerifyInputFile;

@Service
public class AppService {
	private static final Logger LOGGER = LoggerFactory.getLogger(AppService.class);

	@Autowired
	private VerifyInputFile verifyInputFile;

	@Autowired
	private AppData appData;

	@Autowired
	private AnalyseLog analyseLog;
	
	public void execute(String... args) {
		AppContext context = AppContext.getInstance();
		System.out.println("Context = " + context);
		verifyInputFile.validateFilePath(context, args);
		analyseLog.parseLogsAndCreateAlert(context);
		
	}

}
