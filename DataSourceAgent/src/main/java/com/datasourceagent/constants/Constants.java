package com.datasourceagent.constants;

public class Constants {

	public static final String[] WAFERTESTS = new String[] { "AA", "BB", "CC", "DD", "EE", "FF", "GG", "HH", "II", "JJ",
			"KK", "LL", "MM", "NN", "OO", "PP", "QQ", "RR", "SS", "TT" ,"UU","VV","WW","XX","YY","ZZ","A1","B1","C1","D1","E1","F1","G1","H1","I1","J1",
			"K1", "L1", "M1", "N1", "O1", "P1", "Q1", "R1", "S1", "T1" ,"U1","V1","W1","X1"};
	public static final String[] UNITS = new String[] { "uA", "V", "nA", "mohm", "unit", "mV" };
	public static final double [] minVal = new double [] {0.000,5.000,10.000,25.000,40.000,27.000,30.000,55.000,100.000,500.000,0.000,5.000,10.000,25.000,40.000,27.000,30.000,55.000,100.000,500.000,0.000,5.000,10.000,25.000,40.000,27.000,30.000,55.000,100.000,500.000,0.000,5.000,10.000,25.000,40.000,27.000,30.000,55.000,100.000,500.000,0.000,5.000,10.000,25.000,40.000,27.000,30.000,55.000,100.000,500.000};
	public static final double [] maxVal = new double[] {10.000,20.000,30.000,30.000,50.000,47.000,70.000,95.000,200.000,700.000,10.000,20.000,30.000,30.000,50.000,47.000,70.000,95.000,200.000,700.000,10.000,20.000,30.000,30.000,50.000,47.000,70.000,95.000,200.000,700.000,10.000,20.000,30.000,30.000,50.000,47.000,70.000,95.000,200.000,700.000,10.000,20.000,30.000,30.000,50.000,47.000,70.000,95.000,200.000,700.000};
	public static final int CHIP_AREA_ORIGIN = 1;
	public static final int CHIP_AREA_BOUND = 10000;
	public static final int ALPHA_NUMERIC_CHAR_LIMIT = 6;
	public static final double FAILED_RATIO_ORIGIN = 0.00;
	public static final double FAILED_RATIO_BOUND = 95.00;
	public static final int SLOT_NUMBER_ORIGIN = 1;
	public static final int SLOT_NUMBER_BOUND = 25;
	public static final int TEST_CHIP_COUNT_ORIGIN = 50000;
	public static final int TEST_CHIP_COUNT_BOUND = 100000;
	public static final int TEST_FAIL_COUNT_BOUND = 45000;
	public static final int TEST_TIME_ORIGIN = 100000;
	public static final int TEST_TIME_BOUND = 7000000;
	public static final int TEST_NUMBER_ORIGIN = 1;
	public static final int TEST_NUMBER_BOUND = 51;
	public static final int CAT_ORIGIN = 1;
	public static final int CAT_BOUND = 20;
	public static final int DUT_NUMBER_ORIGIN = 1;
	public static final int DUT_NUMBER_BOUND = 9;
	public static final int BIN_NUMBER_ORIGIN = 0;
	public static final int BIN_NUMBER_BOUND = 21;
	public static final double MEASUREMENT_VALUE_ORIGIN = -9.99;
	public static final double MEASUREMENT_VALUE_BOUND = 1000.00;
	public static final int NUMBER_OF_CHIPS = 100000;
	public static final int CHIP_COUNT = 50000;
	public static final int WAFER_TEST_ORIGIN =0;
	public static final int WAFER_TEST_BOUND =20;
	public static final int UNITS_ORIGIN =0;
	public static final int UNITS_BOUND =6;
	public static final int TEST_COUNT = 50;
	public static final String[] TESTHISTORY_HEADER = new String[] {"waferId","mapId","productId","waferNumber","waferStartTime","waferEndTime","lotId","productName","careerId","deviceId","originalLotId","lotNumber","programName","lotStartTime","lotEndTime","processCode","probeCardId","testBoardId","status","slotNumber","testTime","goodChipCount","lastYield","yield","testFailCount","testPassCount","testChipCount","testChipRatio","failRatio","deviceDesignGroup","deviceGroup","chipArea"};
	public static final String[] MESTEST_HEADER = new String[] {"waferId","mapId","dutNumber","testPass","binNumber"};
	public static final String[] TESTRESULTS_HEADER = new String[] {"waferId","testNumber","dutNumber","binNumber","testName","testPass","measurementValue","measurementUnit","chipAddressTranspose"};
	public static final String TESTHISTORY_FILEPATH = "TestHistory";
	public static final String MESTEST_FILEPATH = "MESTest";
	public static final String TESTRESULTS_FILEPATH = "TestResults";
	public static final String TESTVALUERANGES_FILEPATH = "TestValueRanges";
	public static final String TESTHISTORY_FILENAME = "TestHistory.csv";
	public static final String MESTEST_FILENAME = "MESTest.csv";
	public static final String TESTRESULTS_FILENAME = "TestResults.csv";
	public static final String BLACKSLASH = "/";
	public static final String DRUID_TASK_PATH = "/druid/indexer/v1/task";
	public static final String DRUID_TESTHISTORY_ROOTPATH= "/opt/druid/local/TestHistory/";
	public static final String DRUID_MESTEST_ROOTPATH= "/opt/druid/local/MESTest/";
	public static final String DRUID_TESTRESULTS_ROOTPATH= "/opt/druid/local/TestResults/";
	public static final String PLACEHOLDER="<PATH>";
	public static final int FREQUENCY_ORIGIN = 0;
	public static final int FREQUENCY_BOUND = 10000;
}
