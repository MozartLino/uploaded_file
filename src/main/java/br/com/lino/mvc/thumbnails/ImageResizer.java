package br.com.lino.mvc.thumbnails;

import java.io.File;

public class ImageResizer {

	public static void resize(File original, File toResize) {
		try {
			net.coobird.thumbnailator.Thumbnails.of(original)
					.size(300, 300)
					.outputQuality(0.9)
					.outputFormat("jpg")
					.toFile(toResize);
		} catch (Exception e) {
			throw new RuntimeException("Erro ao redimensionar imagem", e);
		}
	}
}