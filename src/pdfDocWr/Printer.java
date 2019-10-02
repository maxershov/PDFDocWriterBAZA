package pdfDocWr;


import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.printing.PDFPageable;

class Printer {
	/* prints PDF file from PDFWriter */
	void printPDF() throws InvalidPasswordException, IOException, PrinterException {
		String filename = System.getProperty("user.dir") + System.getProperty("file.separator") + "pass.pdf";
		PDDocument document = PDDocument.load(new File(filename));

		// Name of your printer
		PrintService myPrintService = findPrintService("HP LaserJet Pro MFP M125-M126 PCLmS");

		PrinterJob job = PrinterJob.getPrinterJob();
		job.setPageable(new PDFPageable(document));
		job.setPrintService(myPrintService);
		job.print();
	}

	static PrintService findPrintService(String printerName) {
		PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
		for (PrintService printService : printServices) {
			if (printService.getName().trim().equals(printerName)) {
				return printService;
			}
		}
		return null;
	}
}