package com.example.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.example.model.AppContext;

@Component
public class VerifyInputFile {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(VerifyInputFile.class);
	
    public void validateFilePath(AppContext context, String args[]) {
        String logFilePath = args[0];
        LOGGER.info("Log file Path : {}", logFilePath);
        context.setLogFilePath(logFilePath);

        try {
            File file = new ClassPathResource("Input/" + logFilePath).getFile();
            if (!file.exists()) {
                file = new ClassPathResource(logFilePath).getFile();
                if (!file.exists()) {
                    file = new File(logFilePath);
                }
            }

            if (!file.exists())
                throw new FileNotFoundException("File not found at " + logFilePath);
        } catch (IOException e) {
            LOGGER.error("!!! Unable to find the specified file '{}'", logFilePath);
        }
    }

}
