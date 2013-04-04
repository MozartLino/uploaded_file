package br.com.lino.controller;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;
import br.com.caelum.vraptor.view.Results;
import br.com.lino.mvc.thumbnails.SaveFileInDisc;
import br.com.lino.mvc.thumbnails.ThumbnailGenerator;

@Resource
public class FileController {

	private Result result;

	private ThumbnailGenerator converter;

	private final SaveFileInDisc saveFileInDisc;

	public FileController(Result result, ThumbnailGenerator converter, SaveFileInDisc saveFileInDisc) {
		this.result = result;
		this.converter = converter;
		this.saveFileInDisc = saveFileInDisc;
	}

	@Post("/file/upload")
	public void upload(UploadedFile file) {

		// for (UploadedFile uploadedFile : files) {
		String completePath = saveFileInDisc.save(file.getFile(), file.getFileName());
		converter.fileToThumbnail(completePath);
		// }

		result.use(Results.json()).from("ok").serialize();
	}

	@Get("/file/download")
	public void download() {

	}

	@Get("/")
	public void form() {
	}

}
