package unit;

import java.util.List;

import model.Album;
import model.MediaType;
import model.Medium;

import org.junit.Before;
import org.junit.Test;

import control.SQLController;

public class SQLControllerTest {

	SQLController controller;
	
	@Before
	public void setUp() {
		
		controller = new SQLController();
	}

	@Test
	public void getAllMedia() {
		
		List<Medium> result = controller.getAllMedia();
		System.out.println("All media: " + result);
	}
	
	@Test
	public void getAllMediaTypes() {
		
		List<MediaType> result = controller.getAllMediaTypes();
		System.out.println("All media types: " + result);
	}
	
	@Test
	public void getAllAlbums() {
		
		List<Album> result = controller.getAllAlbums();
		System.out.println("All albums: " + result);
	}
	
	@Test
	public void getTopPlayedMedia1() {
		
		List<Medium> result = controller.getTopPlayedMedia(1);
		System.out.println("Top played media (at least " + 1 + "): " + result);
	}
	
	@Test
	public void getTopBoughtMedia1() {
		
		List<Medium> result = controller.getTopBoughtMedia(1);
		System.out.println("Top bought media (at least " + 1 + "): " + result);
	}
	
	@Test
	public void getTopPlayedMedia0() {
		
		List<Medium> result = controller.getTopPlayedMedia(0);
		System.out.println("Top played media (at least " + 0 + "): " + result);
	}
	
	@Test
	public void getTopBoughtMedia0() {
		
		List<Medium> result = controller.getTopBoughtMedia(0);
		System.out.println("Top bought media (at least " + 0 + "): " + result);
	}
}
