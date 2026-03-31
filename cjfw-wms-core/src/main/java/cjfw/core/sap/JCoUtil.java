/*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) radix(10) lradix(10) 
// Source File Name:   JCoUtil.java

package cjfw.core.sap;

import java.util.Properties;

import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoTable;
import com.sap.conn.jco.ext.DestinationDataEventListener;
import com.sap.conn.jco.ext.DestinationDataProvider;
import com.sap.conn.jco.ext.Environment;

import cjfw.core.utility.ContextUtil;

public class JCoUtil {
	static class FrameOneJCoDestinationDataProvider implements DestinationDataProvider {

		public Properties getDestinationProperties(String system) {
			return ABAP_AS_properties;
		}

		public void setDestinationDataEventListener(DestinationDataEventListener destinationdataeventlistener) {
			// 사용하지 않음?
		}

		public boolean supportsEvents() {
			return false;
		}

		private final Properties ABAP_AS_properties;

		public FrameOneJCoDestinationDataProvider() {

			Properties connectProperties = new Properties();
			connectProperties.setProperty("jco.client.sysnr", ContextUtil.getProperty("cf.sap.systemNumber"));
			connectProperties.setProperty("jco.client.client", ContextUtil.getProperty("cf.sap.client"));
			connectProperties.setProperty("jco.client.user", ContextUtil.getProperty("cf.sap.userId"));
			connectProperties.setProperty("jco.client.passwd", ContextUtil.getProperty("cf.sap.password"));
			connectProperties.setProperty("jco.client.lang", ContextUtil.getProperty("cf.sap.language"));
			connectProperties.setProperty("jco.destination.pool_capacity", ContextUtil.getProperty("cf.sap.poolCapaciry"));
			connectProperties.setProperty("jco.destination.peak_limit", ContextUtil.getProperty("cf.sap.poolPeakLimit"));
			if ("ashost".equals(ContextUtil.getProperty("cf.sap.hostType"))) {
				connectProperties.setProperty("jco.client.ashost", ContextUtil.getProperty("cf.sap.host"));
			} else {
				connectProperties.setProperty("jco.client.mshost", ContextUtil.getProperty("cf.sap.host"));
				connectProperties.setProperty("jco.client.group", ContextUtil.getProperty("cf.sap.group"));
				connectProperties.setProperty("jco.client.r3name", ContextUtil.getProperty("cf.sap.r3name"));
			}
			ABAP_AS_properties = connectProperties;
		}
	}

	public JCoUtil() {
		// 사용하지 않음?
	}

	public static JCoDestination getDestination() {
		JCoDestination destination = null;
		try {
			destination = JCoDestinationManager.getDestination(abapAsPooled);
		} catch (JCoException e) {
			e.printStackTrace();
		}
		return destination;
	}

	public static JCoFunction getFunction(String functionName) {
		JCoFunction function = null;
		try {
			JCoDestination destination = getDestination();
			function = destination.getRepository().getFunction(functionName);
		} catch (JCoException e) {
			e.printStackTrace();
		}
		return function;
	}

	public static void test() {
		JCoDestination destination = getDestination();
		JCoFunction function = getFunction("STFC_CONNECTION");
		if (function == null)
			throw new RuntimeException("STFC_CONNECTION not found in SAP.");
		double value = Math.random();
		function.getImportParameterList().setValue("REQUTEXT", value);
		try {
			function.execute(destination);
		} catch (JCoException e) {
			e.printStackTrace();
		}
		if (function.getExportParameterList().getDouble("ECHOTEXT") != value)
			throw new RuntimeException("exception occured!!");
		else
			return;
	}

	public static void test2() {
		JCoDestination destination = getDestination();
		JCoFunction function = getFunction("Z_BC_RFC_CONNECT_TEST");
		if (function == null)
			throw new RuntimeException("Z_BC_RFC_CONNECT_TEST not found in SAP.");
		try {
			function.execute(destination);
		} catch (JCoException e) {
			e.printStackTrace();
		}
	}

	public static void testTableType() {
		JCoDestination destination = getDestination();
		JCoFunction function = getFunction("STFC_CONNECTION");
		JCoTable table = function.getTableParameterList().getTable("IS_ZCOSI029");
		if (function == null)
			throw new RuntimeException("BAPI_COMPANYCODE_GETLIST not found in SAP.");
		for (int i = 0; i < 10; i++) {
			table.appendRow();
			table.setValue("value", "PARAM_NAME");
		}

		try {
			function.execute(destination);
		} catch (JCoException e) {
			e.printStackTrace();
		}
	}

	private static String abapAsPooled = "ABAP_AS_WITH_POOL";

	static {
		Environment.registerDestinationDataProvider(new FrameOneJCoDestinationDataProvider());
	}
}

/*
 * DECOMPILATION REPORT
 * 
 * Decompiled from:
 * C:\LOGISONE\workspace\LOGISONE-Batch\inf\lib\frameone-core.jar Total time:
 * 421 ms Jad reported messages/errors: Exit status: 0 Caught exceptions:
 */