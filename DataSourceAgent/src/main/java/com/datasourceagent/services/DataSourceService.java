package com.datasourceagent.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.datasourceagent.constants.Constants;
import com.datasourceagent.models.FrequencyDistribution;
import com.datasourceagent.models.KafkaMessage;
import com.datasourceagent.models.MESTest;
import com.datasourceagent.models.TestHistory;
import com.datasourceagent.models.TestResults;
import com.datasourceagent.models.TestValueRanges;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.core.JsonGenerator;

@Service
public class DataSourceService {

	@Autowired
	DataIngestionService dataIngestionservice;

	@Value("${data.filePath}")
	public String filePath;

	@Value("${agent.druid.task}")
	public String initialize;

	private static final Logger logger = LoggerFactory.getLogger(DataSourceService.class);

	private static final DecimalFormat df = new DecimalFormat("0.00");

	private List<MESTest> mesTests = new ArrayList<>();

	private List<TestHistory> testHistories = new ArrayList<>();

	private List<TestResults> testResults = new ArrayList<>();

	public void generateData(KafkaMessage message) throws ParseException {

		try {
			TestHistory testHistory;
			String guid = UUID.randomUUID().toString();
			
			for (int i = 1; i <= message.getCount(); i++) {

				testHistory = new TestHistory();
				testHistory.setProductId(message.getProductId());
				testHistory.setProductName(message.getProductName());
				testHistory.setTestBoardId(message.getTestBoardId());
				testHistory.setCareerId(message.getCareerId());
				testHistory.setDeviceId(message.getDeviceId());
				testHistory.setProcessCode(message.getProcessCode());
//				testHistory.setMapId(message.getMapId());
				testHistory.setLotId(message.getLotId());
				testHistory.setLotStartTime(message.getLotStartTime());
				testHistory.setLotEndTime(message.getLotEndTime());
				testHistory.setLotNumber(message.getLotNumber());
				testHistory.setOriginalLotId(message.getOrginalLotId());
				testHistory.setProgramName(message.getProgramName());
				testHistory.setProbeCardId(message.getProbeCardId());

				testHistory.setWaferId(RandomStringUtils.randomAlphanumeric(24).toLowerCase());
				testHistory.setChipArea(message.getChipArea());
				testHistory.setDeviceDesignGroup(message.getDeviceDesignGroup());
				testHistory.setDeviceGroup(message.getDeviceGroup());
				testHistory.setSlotNumber(
						ThreadLocalRandom.current().nextInt(Constants.SLOT_NUMBER_ORIGIN, Constants.SLOT_NUMBER_BOUND));
				testHistory.setTestChipCount(ThreadLocalRandom.current().nextInt(Constants.TEST_CHIP_COUNT_ORIGIN,
						Constants.TEST_CHIP_COUNT_BOUND));
				testHistory.setTestFailCount(ThreadLocalRandom.current().nextInt(Constants.TEST_FAIL_COUNT_BOUND));
				testHistory.setTestPassCount(testHistory.getTestChipCount() - testHistory.getTestFailCount());
				testHistory.setGoodChipCount(testHistory.getTestPassCount());
				testHistory.setTestTime(
						ThreadLocalRandom.current().nextInt(Constants.TEST_TIME_ORIGIN, Constants.TEST_TIME_BOUND));
				testHistory.setWaferEndTime(new Date());
				testHistory.setWaferNumber(Integer.toString(testHistory.getSlotNumber()));
				testHistory.setWaferStartTime(new Date());
				testHistory.setYield(Double.parseDouble(
						df.format(((double) testHistory.getTestPassCount() / testHistory.getTestChipCount()) * 100)));
				testHistory.setFailRatio(Double.parseDouble(df
						.format(((double) testHistory.getTestFailCount() / testHistory.getTestChipCount()) * 100.00)));
				testHistory.setTestChipRatio(Double
						.parseDouble(df.format((double) testHistory.getTestChipCount() / Constants.NUMBER_OF_CHIPS)));
				testHistory.setLastYield(Double.parseDouble(df.format(Math
						.round((double) (testHistory.getGoodChipCount()) / (Constants.NUMBER_OF_CHIPS) * 100.0 * 100.0)
						/ 100.0)));
				
				writeTestHistoryToJsonFile(testHistory,guid);
				generateTestValueRanges(testHistory,guid);
//				testHistories.add(testHistory);
//				generateMESTestData(testHistory);
//				if (i % 2 == 0 || i == 25) {
//					generateCSVForTestHistory(guid);
//					generateCSVForMESTest(guid);
//					generateCSVForTestResults(guid);
//				}
			}
//			if (Boolean.parseBoolean(initialize)) {
//				dataIngestionservice.ingestTestHistoryData(guid);
//				dataIngestionservice.ingestMESTestData(guid);
//				dataIngestionservice.ingestTestResultsData(guid);
//			}
			logger.info("Data created and saved into CSV ");
		} catch (Exception e) {
			logger.error("Message: {} {}", e.getMessage(), e.getStackTrace());
		}

	}

	public void generateMESTestData(TestHistory testHistory) {

		try {
			MESTest mesTest;
			for (int i = 0; i < Constants.CHIP_COUNT; i++) {
				mesTest = new MESTest();
				mesTest.setWaferId(testHistory.getWaferId());
				mesTest.setBinNumber(Integer.toString(
						ThreadLocalRandom.current().nextInt(Constants.BIN_NUMBER_ORIGIN, Constants.BIN_NUMBER_BOUND)));
				mesTest.setDutNumber(
						ThreadLocalRandom.current().nextInt(Constants.DUT_NUMBER_ORIGIN, Constants.DUT_NUMBER_BOUND));
				mesTest.setTestPass(ThreadLocalRandom.current().nextBoolean());

				mesTests.add(mesTest);
				generateTestResultsData(testHistory, mesTest);
			}
		} catch (Exception e) {
			logger.error("Message: {} {}", e.getMessage(), e.getStackTrace());
		}
	}

	public void generateTestResultsData(TestHistory testHistory, MESTest mesTest) {

		try {
			TestResults testResult;
			for (int i = 0; i < Constants.TEST_COUNT; i++) {
				testResult = new TestResults();
				testResult.setWaferId(testHistory.getWaferId());
				testResult.setProductId(testHistory.getProductId());
				testResult.setProductName(testHistory.getProductName());
				testResult.setTestBoardId(testHistory.getTestBoardId());
				testResult.setCareerId(testHistory.getCareerId());
				testResult.setDeviceId(testHistory.getDeviceId());
				testResult.setProcessCode(testHistory.getProcessCode());
//				testResult.setMapId(testHistory.getMapId());
				testResult.setLotId(testHistory.getLotId());
				testResult.setLotStartTime(testHistory.getLotStartTime());
				testResult.setLotEndTime(testHistory.getLotEndTime());
				testResult.setLotNumber(testHistory.getLotNumber());
				testResult.setOriginalLotId(testHistory.getOriginalLotId());
				testResult.setProgramName(testHistory.getProgramName());
				testResult.setProbeCardId(testHistory.getProbeCardId());

				testResult.setChipArea(testHistory.getChipArea());
				testResult.setDeviceDesignGroup(testHistory.getDeviceDesignGroup());
				testResult.setDeviceGroup(testHistory.getDeviceGroup());
				testResult.setSlotNumber(testHistory.getSlotNumber());
				testResult.setTestChipCount(testHistory.getTestChipCount());
				testResult.setTestFailCount(testHistory.getTestFailCount());
				testResult.setTestPassCount(testHistory.getTestPassCount());
				testResult.setGoodChipCount(testHistory.getGoodChipCount());
				testResult.setTestTime(testHistory.getTestTime());
				testResult.setWaferEndTime(testHistory.getWaferEndTime());
				testResult.setWaferNumber(testHistory.getWaferNumber());
				testResult.setWaferStartTime(testHistory.getWaferStartTime());
				testResult.setYield(testHistory.getYield());
				testResult.setFailRatio(testHistory.getFailRatio());
				testResult.setTestChipRatio(testHistory.getTestChipRatio());
				testResult.setLastYield(testHistory.getLastYield());

				testResult.setTestNumber(i + 1);
				testResult.setDutNumber(mesTest.getDutNumber());
				testResult.setBinNumber(Integer.parseInt(mesTest.getBinNumber()));
				testResult.setTestName(Constants.WAFERTESTS[i]);
				testResult.setMeasurementUnit(Constants.UNITS[ThreadLocalRandom.current()
						.nextInt(Constants.UNITS_ORIGIN, Constants.UNITS_BOUND)]);
				testResult.setMeasurementValue(Double.parseDouble(df.format((ThreadLocalRandom.current()
						.nextDouble(Constants.MEASUREMENT_VALUE_ORIGIN, Constants.MEASUREMENT_VALUE_BOUND)))));
				testResult.setTestPass(ThreadLocalRandom.current().nextBoolean());
				testResults.add(testResult);
			}
		} catch (Exception e) {
			logger.error("Message: {} {}", e.getMessage(), e.getStackTrace());
		}
	}

	public void generateTestValueRanges(TestHistory testHistory,String guid) {

		try {
			TestValueRanges testValueRanges;
			FrequencyDistribution frequencyDistribution;
			for (int i = 0; i < Constants.TEST_COUNT; i++) {
				testValueRanges = new TestValueRanges();
				LinkedHashMap<String, FrequencyDistribution> mvRange = new LinkedHashMap<>();
				testValueRanges.setWaferId(testHistory.getWaferId());
				testValueRanges.setMaxVal(Constants.maxVal[i]);
				testValueRanges.setMinVal(Constants.minVal[i]);
				testValueRanges.setTm(Constants.WAFERTESTS[i]);
				testValueRanges.setTn(i + 1);

				for (int j = 1; j <= 10; j++) {
					frequencyDistribution = new FrequencyDistribution();
					if (j == 1) {
						frequencyDistribution.setRangeStart(testValueRanges.getMinVal());
						frequencyDistribution.setRangeEnd(frequencyDistribution.getRangeStart()
								+ ((testValueRanges.getMaxVal() - testValueRanges.getMinVal()) / 10));
					} else {
						frequencyDistribution.setRangeStart(mvRange.get(Integer.toString(j - 1)).getRangeEnd() + 0.01);
						frequencyDistribution.setRangeEnd((frequencyDistribution.getRangeStart() - 0.01)
								+ ((testValueRanges.getMaxVal() - testValueRanges.getMinVal()) / 10));
					}

					if (j % 2 != 0) {
						frequencyDistribution.setFrequency(ThreadLocalRandom.current()
								.nextInt(Constants.FREQUENCY_ORIGIN, Constants.FREQUENCY_BOUND));
					} else {
						frequencyDistribution.setFrequency(10000 - mvRange.get(Integer.toString(j - 1)).getFrequency());
					}
					mvRange.put(Integer.toString(j), frequencyDistribution);
				}
				testValueRanges.setRange(mvRange);
				writeTestValueRangesToJsonFile(testValueRanges,guid);
			}

		} catch (Exception e) {
			logger.error("Message: {} {}", e.getMessage(), e.getStackTrace());
		}
	}

	public void generateCSVForTestHistory(String guid) {
		try {
			String path = filePath + Constants.TESTHISTORY_FILEPATH + Constants.BLACKSLASH + guid;
			Files.createDirectories(Paths.get(path));
			File csvOutputFile = new File(path + Constants.BLACKSLASH + UUID.randomUUID().toString() + ".csv");
			CsvMapper mapper = new CsvMapper();
			mapper.configure(JsonGenerator.Feature.IGNORE_UNKNOWN, true);

			CsvSchema schema = CsvSchema.builder().setUseHeader(true).addColumn("waferId").addColumn("productId")
					.addColumn("waferNumber").addColumn("waferStartTime").addColumn("waferEndTime").addColumn("lotId")
					.addColumn("productName").addColumn("careerId").addColumn("deviceId").addColumn("originalLotId")
					.addColumn("lotNumber").addColumn("programName").addColumn("lotStartTime").addColumn("lotEndTime")
					.addColumn("processCode").addColumn("probeCardId").addColumn("testBoardId").addColumn("slotNumber")
					.addColumn("testTime").addColumn("goodChipCount").addColumn("lastYield").addColumn("yield")
					.addColumn("testFailCount").addColumn("testPassCount").addColumn("testChipCount")
					.addColumn("testChipRatio").addColumn("failRatio").addColumn("deviceDesignGroup")
					.addColumn("deviceGroup").addColumn("chipArea").build();

			ObjectWriter writer = mapper.writerFor(TestHistory.class).with(schema);

			writer.writeValues(csvOutputFile).writeAll(testHistories);
			testHistories.clear();

		} catch (Exception e) {
			logger.error("Message: {} {}", e.getMessage(), e.getStackTrace());
		}
	}

	public void generateCSVForMESTest(String guid) {
		try {
			String path = filePath + Constants.MESTEST_FILEPATH + Constants.BLACKSLASH + guid;
			Files.createDirectories(Paths.get(path));
			File csvOutputFile = new File(path + Constants.BLACKSLASH + UUID.randomUUID().toString() + ".csv");
			CsvMapper mapper = new CsvMapper();
			mapper.configure(JsonGenerator.Feature.IGNORE_UNKNOWN, true);

			CsvSchema schema = CsvSchema.builder().setUseHeader(true).addColumn("waferId").addColumn("dutNumber")
					.addColumn("testPass").addColumn("binNumber").build();

			ObjectWriter writer = mapper.writerFor(MESTest.class).with(schema);

			writer.writeValues(csvOutputFile).writeAll(mesTests);
			mesTests.clear();

		} catch (Exception e) {
			logger.error("Message: {} {}", e.getMessage(), e.getStackTrace());
		}
	}

	public void generateCSVForTestResults(String guid) {
		try {
			String path = filePath + Constants.TESTRESULTS_FILEPATH + Constants.BLACKSLASH + guid;
			Files.createDirectories(Paths.get(path));
			File csvOutputFile = new File(path + Constants.BLACKSLASH + UUID.randomUUID().toString() + ".csv");
			CsvMapper mapper = new CsvMapper();
			mapper.configure(JsonGenerator.Feature.IGNORE_UNKNOWN, true);

			CsvSchema schema = CsvSchema.builder().setUseHeader(true).addColumn("waferId").addColumn("productId")
					.addColumn("waferNumber").addColumn("waferStartTime").addColumn("waferEndTime").addColumn("lotId")
					.addColumn("productName").addColumn("careerId").addColumn("deviceId").addColumn("originalLotId")
					.addColumn("lotNumber").addColumn("programName").addColumn("lotStartTime").addColumn("lotEndTime")
					.addColumn("processCode").addColumn("probeCardId").addColumn("testBoardId").addColumn("slotNumber")
					.addColumn("testTime").addColumn("goodChipCount").addColumn("lastYield").addColumn("yield")
					.addColumn("testFailCount").addColumn("testPassCount").addColumn("testChipCount")
					.addColumn("testChipRatio").addColumn("failRatio").addColumn("deviceDesignGroup")
					.addColumn("deviceGroup").addColumn("chipArea").addColumn("testNumber").addColumn("dutNumber")
					.addColumn("binNumber").addColumn("testName").addColumn("testPass").addColumn("measurementValue")
					.addColumn("measurementUnit").build();

			ObjectWriter writer = mapper.writerFor(TestResults.class).with(schema);

			writer.writeValues(csvOutputFile).writeAll(testResults);
			testResults.clear();

		} catch (Exception e) {
			logger.error("Message: {} {}", e.getMessage(), e.getStackTrace());
		}
	}

	public void writeTestHistoryToJsonFile(TestHistory testHistory,String guid) {
		final ObjectMapper mapper = new ObjectMapper();
		String path = filePath + Constants.TESTHISTORY_FILEPATH + "\\" + guid ;
		try {
			Files.createDirectories(Paths.get(path));
			File file = new File(path + "\\"+ guid + ".json");
			file.createNewFile();
			final String json2 = mapper.writeValueAsString(testHistory);
			Files.write(new File(path + "\\" + guid + ".json").toPath(), Arrays.asList(json2), StandardOpenOption.APPEND);
		} catch (IOException e) {
			logger.error("Message: {} {}", e.getMessage(), e.getStackTrace());
		}
	}
	
	public void writeTestValueRangesToJsonFile(TestValueRanges testValueRanges, String guid) {
		final ObjectMapper mapper = new ObjectMapper();
		String path = filePath + Constants.TESTVALUERANGES_FILEPATH +"\\" + guid;
		try {
			Files.createDirectories(Paths.get(path));
			File file = new File(path + "\\" + guid + ".json");
			file.createNewFile();
			final String json2 = mapper.writeValueAsString(testValueRanges);
			Files.write(new File(path + "\\" + guid + ".json").toPath(), Arrays.asList(json2), StandardOpenOption.APPEND);
		} catch (IOException e) {
			logger.error("Message: {} {}", e.getMessage(), e.getStackTrace());
		}
	}

}
