package br.com.lino.mvc.thumbnails;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/applicationContext.xml" })
public class ThumbnailGeneratorTest {

	@Autowired
	ThumbnailGenerator generator;

	@Test
	public void shouldGenerateThumbnailFromTxtFile() throws IOException {
		String path = getAbsoluteFilePath("thumbnailGeneratorExamples/txtFile.txt");

		generator.fileToThumbnail(path);

		assertTrue("Thumbnail not generated", new File(path + ".thumbnail.jpg").exists());
	}

	@Test
	public void shouldGenerateThumbnailFromDoc() {
		String path = getAbsoluteFilePath("thumbnailGeneratorExamples/docFile.doc");

		generator.fileToThumbnail(path);

		assertTrue("Thumbnail not generated", new File(path + ".thumbnail.jpg").exists());
	}

	@Test
	public void shouldGenerateThumbnailFromDocx() {
		String path = getAbsoluteFilePath("thumbnailGeneratorExamples/docxFile.docx");

		generator.fileToThumbnail(path);

		assertTrue("Thumbnail not generated", new File(path + ".thumbnail.jpg").exists());
	}

	@Test
	public void shouldGenerateThumbnailFromOds() {
		String path = getAbsoluteFilePath("thumbnailGeneratorExamples/odsFile.ods");

		generator.fileToThumbnail(path);

		assertTrue("Thumbnail not generated", new File(path + ".thumbnail.jpg").exists());
	}

	@Test
	public void shouldGenerateThumbnailFromXls() {
		String path = getAbsoluteFilePath("thumbnailGeneratorExamples/xlsFile.xls");

		generator.fileToThumbnail(path);

		assertTrue("Thumbnail not generated", new File(path + ".thumbnail.jpg").exists());
	}

	@Test
	public void shouldGenerateThumbnailFromPdf() {
		String path = getAbsoluteFilePath("thumbnailGeneratorExamples/pdfFile.pdf");

		generator.fileToThumbnail(path);

		assertTrue("Thumbnail not generated", new File(path + ".thumbnail.jpg").exists());
	}

	@Test
	public void shouldGenerateThumbnailFromImage() {
		String path = getAbsoluteFilePath("thumbnailGeneratorExamples/pngFile.png");

		generator.fileToThumbnail(path);

		assertTrue("Thumbnail not generated", new File(path + ".thumbnail.jpg").exists());
	}

	@Test
	public void shouldDeletePdfUsedToConvertFileToImage() {
		String path = getAbsoluteFilePath("thumbnailGeneratorExamples/docFile.doc");

		generator.fileToThumbnail(path);

		String pathPdf = path.substring(0, path.lastIndexOf(".")) + ".pdf";

		assertFalse(new File(pathPdf).exists());
	}

	@Test(expected = IllegalStateException.class)
	public void shouldntGenerataThumbBecauseIsUnconvertableFile() {
		String path = getAbsoluteFilePath("thumbnailGeneratorExamples/zipFile.zip");

		generator.fileToThumbnail(path);
	}

	private String getAbsoluteFilePath(String relativeFilePath) {
		URL resource = ThumbnailGeneratorTest.class.getClassLoader().getResource(relativeFilePath);

		return resource.getFile();
	}
}