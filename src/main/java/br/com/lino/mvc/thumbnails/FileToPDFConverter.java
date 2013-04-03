package br.com.lino.mvc.thumbnails;

import java.io.File;

import org.artofsolving.jodconverter.OfficeDocumentConverter;
import org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration;
import org.artofsolving.jodconverter.office.OfficeManager;

public class FileToPDFConverter {

	private OfficeManager officeManager;

	private OfficeDocumentConverter converter;

	public FileToPDFConverter() {
	}

	public void convert(File anyFile, File pdfFile) {
		converter.convert(anyFile, pdfFile);
	}

	@SuppressWarnings("unused")
	private void init() {
		officeManager = new DefaultOfficeManagerConfiguration().buildOfficeManager();
		officeManager.start();

		converter = new OfficeDocumentConverter(officeManager);
	}

	@SuppressWarnings("unused")
	private void destroy() {
		officeManager.stop();
	}
}