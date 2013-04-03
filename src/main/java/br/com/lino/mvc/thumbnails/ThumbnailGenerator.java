package br.com.lino.mvc.thumbnails;

import java.io.File;
import java.io.IOException;
import java.net.FileNameMap;
import java.net.URLConnection;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class ThumbnailGenerator {

	private File original;

	private File thumbnail;

	private String mimeType;

	private FileToPDFConverter fileToPDF;

	private Logger logger = Logger.getLogger("fourData");

	@Autowired
	public ThumbnailGenerator(FileToPDFConverter converter) {
		this.fileToPDF = converter;
	}

	public File fileToThumbnail(String completePath) {
		original = new File(completePath);
		thumbnail = new File(original.getPath() + ".thumbnail.jpg");

		return geraThumbnail();
	}

	private void extractMimeType() {
		FileNameMap fileNameMap = URLConnection.getFileNameMap();
		String type = fileNameMap.getContentTypeFor("file://" + original.getPath());

		mimeType = (type == null) ? "unknow" : type;
	}

	private File geraThumbnail() {
		extractMimeType();

		try {
			if (isImage()) {
				ImageResizer.resize(original, thumbnail);
			} else if (isPdf()) {
				geraThumbnailDePdf();
			} else {
				convertAnyFileToImage();
			}

			return thumbnail;

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new IllegalStateException("Não foi possivel gerar Thumbnails", e);
		}
	}

	private boolean isImage() {
		return mimeType.contains("image");
	}

	private boolean isPdf() {
		return mimeType.contains("pdf");
	}

	private void geraThumbnailDePdf() throws IOException {
		PDFToImageConverter.convertePdfParathumbnails(original, thumbnail);
	}

	private void convertAnyFileToImage() throws IOException {
		File pdf = new File(createNewPath(original.getPath()));
		fileToPDF.convert(original, pdf);

		PDFToImageConverter.convertePdfParathumbnails(pdf, thumbnail);
		pdf.delete();

		ImageResizer.resize(thumbnail, thumbnail);
	}

	private String createNewPath(String path) {
		return path.substring(0, path.lastIndexOf(".")) + ".pdf";
	}
}