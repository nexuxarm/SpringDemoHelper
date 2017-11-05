package batch.helper;

import org.slf4j.Logger;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;


public class LogHelper<T> {
	
	private Logger logger = null;
	private static final String NEW_LINE_SEPARATOR = "\n";
	
	public LogHelper(Logger logger) {
		this.logger = logger;
	}
	
//	public static String GetTagMessage(String tag, String s) {
//		return tag + " : (" + s + ")";
//	}
	
	public void debug(String message){
		logger.debug("LogHelper.debug() È£Ãâ!!!  ==> " + message);
	}
	
	public void writeCsvFile(String fileName, ICsvHeader header, ICsvRow[] rows) {
		
		FileOutputStream fos = null;
		OutputStreamWriter osw = null;
		BufferedWriter bw = null;
		
		CSVPrinter csvp = null;		
        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);
				        
		try {
			
	        fos = new FileOutputStream(fileName);
	        osw = new OutputStreamWriter(fos, "Shift_JIS");
	        bw = new BufferedWriter(osw);
					 
	        csvp = new CSVPrinter(bw, csvFileFormat);
	        
	        //Create CSV file header
	        csvp.printRecord(header.getHeaders());
			
			//Write a new student object list to the CSV file
			for (ICsvRow row : rows) {
				csvp.printRecord(row.getRow());
			}

			debug("CSV file was created successfully !!!");
			
		} catch (Exception e) {
			logger.error("Error in CsvFileWriter !!!", e);
		} finally {
			try {
				bw.flush();
				bw.close();
				csvp.close();
			} catch (IOException e) {				
                logger.error("Error while flushing/closing fileWriter/csvPrinter !!!", e);
			}
		}
	}
}
