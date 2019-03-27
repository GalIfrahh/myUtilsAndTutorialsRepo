
// please read my explanation for getting started with jenkins & slack - "getting start with slack integaration to jenkins".


package ProjectUtils;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.apache.http.entity.mime.content.FileBody;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;



public class SlackService {
	
	// static final String SLACK_TOKEN = ""; (- use it only for debugging - 
	// the slack token can't be uploaded to the repo att all - thats automatically lead to token expiration.)
	// so make sure you pass the token as param on the jenkins build.
	
	public void send_message(String content) throws UnirestException {

		try {
			
			HttpResponse<String> response = Unirest.post("https://slack.com/api/chat.postMessage")
					
					  .header("accept", "application/json")
					  .header("Authorization", System.getProperty("slack"))
					  .field("channel", "CGTLKU1DH")
					  .field("text", content)
					  .asString();
		
		
		} catch (UnirestException e) {
			
		
			System.out.println(e.getMessage());
		
		}
			
	}
	

	public void uploadImage(String image_path) throws UnirestException {
		
		
		try {
			
			Thread.sleep(10000);
			
			HttpResponse<JsonNode> jsonResponse = Unirest.post("https://slack.com/api/files.upload")

					  .header("accept", "application/json")
					  .header("Authorization", System.getProperty("slack"))
					  .field("channels", "CGTLKU1DH")
					  .field("file", new File(image_path))
					  .asJson();
		
			
		} catch (UnirestException e) {
		
		
			System.out.println(e.getMessage());
	
		}
		
		catch (InterruptedException e) {
			
			
			System.out.println(e.getMessage());
	
		}
		
		
	
	}
	
}
