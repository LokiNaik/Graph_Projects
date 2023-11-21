package com.datasourceagent.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.datasourceagent.constants.Constants;
import com.datasourceagent.models.FrequencyDistribution;
import com.datasourceagent.models.KafkaMessage;
import com.datasourceagent.models.MESTest;
import com.datasourceagent.models.TestHistory;
import com.datasourceagent.models.TestValueRanges;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class DataCreationService {

	@Value("${data.filePath}")
	public String filePath;

	private static final Logger logger = LoggerFactory.getLogger(DataCreationService.class);

	private static final DecimalFormat df = new DecimalFormat("0.000");

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

				writeTestHistoryToJsonFile(testHistory, guid);
				generateTestValueRanges(testHistory, guid);
			}

			logger.info("Data created and saved into CSV ");
		} catch (Exception e) {
			logger.error("Message: {} {}", e.getMessage(), e.getStackTrace());
		}

	}

	public void generateMESTestData(TestHistory testHistory, String guid) {

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
				writeMESTestToJsonFile(mesTest, guid);
			}

		} catch (Exception e) {
			logger.error("Message: {} {}", e.getMessage(), e.getStackTrace());
		}
	}

	public void generateTestValueRanges(TestHistory testHistory, String guid) {

		try {
			TestValueRanges testValueRanges;
			FrequencyDistribution frequencyDistribution;
			for (int i = 0; i < Constants.TEST_COUNT; i++) {
				testValueRanges = new TestValueRanges();
				LinkedHashMap<String, FrequencyDistribution> mvRange = new LinkedHashMap<>();
				testValueRanges.setWaferId(testHistory.getWaferId());
				testValueRanges.setMaxVal(Double.parseDouble(df.format((double) Constants.maxVal[i])));
				testValueRanges.setMinVal(Double.parseDouble(df.format((double) Constants.minVal[i])));
				testValueRanges.setTm(Constants.WAFERTESTS[i]);
				testValueRanges.setTn(i + 1);

				for (int j = 1; j <= 10; j++) {
					frequencyDistribution = new FrequencyDistribution();
					if (j == 1) {
						frequencyDistribution
								.setRangeStart(Double.parseDouble(df.format((double) testValueRanges.getMinVal())));
						frequencyDistribution
								.setRangeEnd(Double.parseDouble(df.format((double) frequencyDistribution.getRangeStart()
										+ ((testValueRanges.getMaxVal() - testValueRanges.getMinVal()) / 10))));
					} else {
						frequencyDistribution.setRangeStart(Double.parseDouble(
								df.format((double) mvRange.get(Integer.toString(j - 1)).getRangeEnd() + 0.001)));
						frequencyDistribution.setRangeEnd(
								Double.parseDouble(df.format((double) (frequencyDistribution.getRangeStart() - 0.001)
										+ ((testValueRanges.getMaxVal() - testValueRanges.getMinVal()) / 10))));
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
				writeTestValueRangesToJsonFile(testValueRanges, guid);
			}

		} catch (Exception e) {
			logger.error("Message: {} {}", e.getMessage(), e.getStackTrace());
		}
	}

	public void writeTestHistoryToJsonFile(TestHistory testHistory, String guid) {
		final ObjectMapper mapper = new ObjectMapper();
		String path = filePath + Constants.TESTHISTORY_FILEPATH + Constants.BLACKSLASH + guid;
		try {
			Files.createDirectories(Paths.get(path));
			File file = new File(path + Constants.BLACKSLASH + guid + ".json");
			file.createNewFile();
			final String json2 = mapper.writeValueAsString(testHistory);
			Files.write(new File(path + Constants.BLACKSLASH + guid + ".json").toPath(), Arrays.asList(json2),
					StandardOpenOption.APPEND);
		} catch (IOException e) {
			logger.error("Message: {} {}", e.getMessage(), e.getStackTrace());
		}
	}

	public void writeTestValueRangesToJsonFile(TestValueRanges testValueRanges, String guid) {
		final ObjectMapper mapper = new ObjectMapper();
		String path = filePath + Constants.TESTVALUERANGES_FILEPATH + Constants.BLACKSLASH + guid;
		try {
			Files.createDirectories(Paths.get(path));
			File file = new File(path + Constants.BLACKSLASH + guid + ".json");
			file.createNewFile();
			final String json2 = mapper.writeValueAsString(testValueRanges);
			Files.write(new File(path + Constants.BLACKSLASH + guid + ".json").toPath(), Arrays.asList(json2),
					StandardOpenOption.APPEND);
		} catch (IOException e) {
			logger.error("Message: {} {}", e.getMessage(), e.getStackTrace());
		}
	}

	public void writeMESTestToJsonFile(MESTest mesTest, String guid) {
		final ObjectMapper mapper = new ObjectMapper();
		String path = filePath + Constants.MESTEST_FILEPATH + Constants.BLACKSLASH + guid;
		try {
			Files.createDirectories(Paths.get(path));
			File file = new File(path + Constants.BLACKSLASH + guid + ".json");
			file.createNewFile();
			final String json2 = mapper.writeValueAsString(mesTest);
			Files.write(new File(path + Constants.BLACKSLASH + guid + ".json").toPath(), Arrays.asList(json2),
					StandardOpenOption.APPEND);
		} catch (IOException e) {
			logger.error("Message: {} {}", e.getMessage(), e.getStackTrace());
		}
	}
}
