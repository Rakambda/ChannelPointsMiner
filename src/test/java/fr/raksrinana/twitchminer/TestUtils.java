package fr.raksrinana.twitchminer;

import fr.raksrinana.twitchminer.utils.json.JacksonUtils;
import kong.unirest.Unirest;
import kong.unirest.jackson.JacksonObjectMapper;
import lombok.SneakyThrows;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class TestUtils{
	private static final String LF = "\n";
	
	@SneakyThrows
	public static Path getResourcePath(String resource){
		return Paths.get(TestUtils.class.getClassLoader().getResource(resource).toURI());
	}
	
	@SneakyThrows
	public static List<String> getResourceContent(String resource){
		return Files.readAllLines(getResourcePath(resource));
	}
	
	public static String getAllResourceContent(String resource){
		return getAllResourceContent(resource, LF);
	}
	
	public static String getAllResourceContent(String resource, String lineTerminator){
		return String.join(lineTerminator, getResourceContent(resource));
	}
	
	@SneakyThrows
	public static Path copyFromResources(String resource, Path target){
		return Files.copy(getResourcePath(resource), target);
	}
	
	public static void setupUnirest(){
		Unirest.config().reset()
				.clearDefaultHeaders()
				.setObjectMapper(new JacksonObjectMapper(JacksonUtils.getMapper()));
	}
}
