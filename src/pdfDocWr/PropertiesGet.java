package pdfDocWr;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.CodeSource;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

class PropertiesGet {
	static String printerName;
	static String pdfBaseName;
	static String filePath;
	
	static void loadProperties()
			throws URISyntaxException, InvalidPropertiesFormatException, FileNotFoundException, IOException {
		Properties loadProps = new Properties();
		String filePath = loadFilePath();
		String fileProp = filePath + "/resource" + "/properties.xml";
		loadProps.loadFromXML(new FileInputStream(fileProp));
		printerName = loadProps.getProperty("printerName");
		pdfBaseName = loadProps.getProperty("baseFileName");
	}

	static String getFilePath() {
		return filePath;
	}
	
	static String getPrinterName() {
		return printerName;
	}

	static String getPdfBaseName() {
		return pdfBaseName;
	}

	static String loadFilePath() throws URISyntaxException {
		CodeSource codeSource = PropertiesGet.class.getProtectionDomain().getCodeSource();
		File jarFile = new File(codeSource.getLocation().toURI().getPath());
		String filePath = jarFile.getParentFile().getPath();
		return filePath;
	}
}