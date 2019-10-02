package pdfDocWr;

import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

class Writer {
	String name;
	String birthDate;
	String date;

	Writer(String iName, String iBirth, String iDate) throws IOException, URISyntaxException {
		name = iName;
		birthDate = iBirth;
		date = iDate;
		addTextToPDF();
	}

	@SuppressWarnings("deprecation")
	void addTextToPDF() throws IOException, URISyntaxException {
		String filepath = PropertiesGet.getFilePath();
		InputStream file = new FileInputStream(new File(filepath + "/resource/" + PropertiesGet.getPdfBaseName()));
		InputStream fileFont = new FileInputStream(new File(filepath + "/resource/AG_Helvetica.ttf"));

		PDDocument doc = PDDocument.load(file);

		// Create new PDF doc
		PDPage page = doc.getPage(0);
		PDPageContentStream contentStream = new PDPageContentStream(doc, page, true, false);

		PDFont font = PDType0Font.load(doc, fileFont); // Windows Russian font imported to write the Russian text

		// Draw text to PDF
		contentStream.beginText();
		contentStream.setFont(font, 12);
		contentStream.moveTextPositionByAmount(320, 670);
		contentStream.drawString(name);
		contentStream.moveTextPositionByAmount(0, -21);
		contentStream.drawString(birthDate);
		contentStream.moveTextPositionByAmount(-120, -399);
		contentStream.drawString(name);
		contentStream.moveTextPositionByAmount(+225, +83);
		contentStream.drawString(date);
		contentStream.moveTextPositionByAmount(0, -260);
		contentStream.drawString(date);
		contentStream.endText();
		contentStream.close();
		// Save the doc
		String filepathOutput = filepath + "/passOut.pdf";
		System.out.println(filepathOutput);

		doc.save(new File(filepathOutput));

		// Print PDF file
		Printer print = new Printer();
		if (!PropertiesGet.getPrinterName().equals("Empty")) {
			try {
				print.printPDF();
			} catch (PrinterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// Closing the document
		doc.close();
	}
}