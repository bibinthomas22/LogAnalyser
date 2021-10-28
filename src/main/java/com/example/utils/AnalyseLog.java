package com.example.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.example.LogAnalyser.AppData;
import com.example.db.AlertRepository;
import com.example.model.AppContext;
import com.example.model.LogAlert;
import com.example.model.LogEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class AnalyseLog {

	@Autowired
	private AppData appData;
	
	@Autowired
    private AlertRepository alertRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(AnalyseLog.class);

	Map<String, LogEvent> events = new HashMap<>();
	List<LogAlert> alerts = new ArrayList<>();

	public void parseLogsAndCreateAlert(AppContext con) {

		try {
			LineIterator line = FileUtils
					.lineIterator(new ClassPathResource("Input/" + con.getLogFilePath()).getFile());
			while (line.hasNext()) {
				LogEvent event = new ObjectMapper().readValue(line.nextLine(), LogEvent.class);

				if (events.containsKey(event.getId())) {
					LogEvent startEvent = events.get(event.getId());
					long execTime = event.getTimestamp() - startEvent.getTimestamp();
					LOGGER.info("Time difference between start and finish logs for event id:'{}' is '{}'",
							event.getId(), execTime);

					Boolean alertFlag = execTime > appData.getMaxTimeInSeconds() ? Boolean.TRUE : Boolean.FALSE;

					LogAlert alert = new LogAlert(event, execTime);

					alerts.add(alert);
					events.remove(event.getId());
				} else
					events.put(event.getId(), event);
			}
			generateAlertFile(con);
			alertRepository.saveAll(alerts);
		} catch (IOException e) {
			LOGGER.error("Unable to read the specified file" + e);
		} catch (Exception e) {
			LOGGER.error("Exception Occured while parsing log and creating Alert" + e);
		}

	}

	@SuppressWarnings("deprecation")
	private void generateAlertFile(AppContext con) throws IOException {

		File alertfile = new File(appData.getfilePath());
		FileUtils.writeStringToFile(alertfile, "");
		for (LogAlert alert : alerts) {
			ObjectMapper obj = new ObjectMapper();
			try {
				String jsonStr = obj.writeValueAsString(alert);
				FileUtils.writeStringToFile(alertfile, jsonStr + "\n", true);
				System.out.println(jsonStr);
			} catch (JsonProcessingException e) {
				LOGGER.error("Cannot convert Java object to Json String" + e);
			}
		}

	}
}
