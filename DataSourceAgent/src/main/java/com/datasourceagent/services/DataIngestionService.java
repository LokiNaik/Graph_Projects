package com.datasourceagent.services;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.datasourceagent.constants.Constants;

@Service
public class DataIngestionService {

	@Value("${druid.serverUrl}")
	public String serverUrl;
	
	@Value("${agent.testhistoryindextask.path}")
	private String testHistoryIndexTask;
	
	@Value("${agent.mestestindextask.path}")
	private String mesTestIndexTask;
	
	@Value("${agent.testresultsindextask.path}")
	private String testResultsIndexTask;

	private static final Logger logger = LoggerFactory.getLogger(DataIngestionService.class);

	public void ingestTestHistoryData(String guid) {
		try {
			JSONParser parser = new JSONParser();
			String file = new String(Files.readAllBytes(Paths.get(testHistoryIndexTask)));
			file = file.replace(Constants.PLACEHOLDER, Constants.DRUID_TESTHISTORY_ROOTPATH + guid);
			JSONObject jsonObject = (JSONObject)parser.parse(file);
			submitTask(jsonObject);
		} catch (Exception e) {
			logger.error("Message: {} {}", e.getMessage(), e.getStackTrace());
		}
	}

	public void ingestMESTestData(String guid) {
		try {
			JSONParser parser = new JSONParser();	
			String file = new String(Files.readAllBytes(Paths.get(mesTestIndexTask)));
			file = file.replace(Constants.PLACEHOLDER, Constants.DRUID_MESTEST_ROOTPATH + guid);
			JSONObject jsonObject = (JSONObject)parser.parse(file); 
			submitTask(jsonObject);
		} catch (Exception e) {
			logger.error("Message: {} {}", e.getMessage(), e.getStackTrace());
		}
	}

	public void ingestTestResultsData(String guid) {
		try {
			JSONParser parser = new JSONParser();
			String file = new String(Files.readAllBytes(Paths.get(testResultsIndexTask)));
			file = file.replace(Constants.PLACEHOLDER, Constants.DRUID_TESTRESULTS_ROOTPATH + guid);
			JSONObject jsonObject = (JSONObject)parser.parse(file);
			submitTask(jsonObject);
		} catch (Exception e) {
			logger.error("Message: {} {}", e.getMessage(), e.getStackTrace());
		}
	}

	public void submitTask(JSONObject jsonObject) {
		try {
			String url = serverUrl + Constants.DRUID_TASK_PATH;
			HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url))
					.POST(BodyPublishers.ofString(jsonObject.toJSONString())).header("Content-type", "application/json")
					.build();
			HttpClient client = HttpClient.newHttpClient();
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			logger.info(response.body());
		} catch (Exception e) {
			logger.error("Message: {} {}", e.getMessage(), e.getStackTrace());
		}
	}
}
