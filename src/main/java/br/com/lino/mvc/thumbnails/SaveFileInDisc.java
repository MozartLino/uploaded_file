package br.com.lino.mvc.thumbnails;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

@Component
public class SaveFileInDisc {

	public static void save(InputStream stream, String path) {
		File file = new File(path);

		try {
			IOUtils.copyLarge(stream, new FileOutputStream(file));
		} catch (IOException e) {
			throw new IllegalStateException("Impossível realizar upload", e);
		}
	}
}