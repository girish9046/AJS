package com.restFul;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

//service class which will retrun the countries and states
@Path("/image")
public class ImageService {
	
	public static String galleryPath="/Users/gmartham/Documents/cndtest/";

	//method to get image
	@GET
	@Path("/getImage")
	@Produces(MediaType.MULTIPART_FORM_DATA)
	public Response getImage() {

		File file = new File(galleryPath+"oee_17.jpeg");

		ResponseBuilder response = Response.ok((Object) file);
		response.header("Content-Disposition",
				"attachment; filename=image_from_server.png");
		return response.build();

	}

	//method to get image
	@GET
	@Path("/getImage2")
	@Produces(MediaType.MULTIPART_FORM_DATA)
	public Response getImage2() {

		File file = new File(galleryPath+"/oee_48.bmp");

		ResponseBuilder response = Response.ok((Object) file);
		response.header("Content-Disposition",
				"attachment; filename=image_from_server.png");
		return response.build();

	}
	
	//method to get image
	@GET
	@Path("/getGalleryImage/{selectedFolder}/{fileName}")
	@Produces(MediaType.MULTIPART_FORM_DATA)
	public Response getImage2(@PathParam("selectedFolder") String selectedFolder ,@PathParam("fileName") String fileName) {
System.out.println(".......folder......" + galleryPath+selectedFolder+"/"+fileName);
		File file = new File(galleryPath+selectedFolder+"/"+fileName);

		ResponseBuilder response = Response.ok((Object) file);
		response.header("Content-Disposition",
				"attachment; filename=image_from_server.png");
		return response.build();

	}

	//method to get image Gallery
	@GET
	@Path("/galleryImageList/{param}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response galleryImageList(@PathParam("param") String selectedFolder) {
		ArrayList<String> thumb = new ArrayList<String>();
		try {
			String filePath = "";
			File folderFiles = new File(galleryPath+selectedFolder);
			File[] listOfFiles = folderFiles.listFiles();

			System.out.println(".......folder......" + galleryPath);
			for (File fileName : listOfFiles) {
				if (fileName.isFile()) {
					//System.out.println(fileName.getName());
					if (!fileName.getName().equals(".DS_Store")) {
						//filePath = galleryPath + fileName.getName();

						thumb.add(fileName.getName());
					}
				}
			}
			System.out.println(".......thumb......" + thumb.size());
		} catch (Exception ex) {
			Logger.getLogger(ImageService.class.getName()).log(Level.SEVERE, null, ex);
		}

		return Response.status(200).entity(thumb).build();

	}

}
