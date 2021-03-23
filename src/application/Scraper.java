package application;

import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Document;
import java.io.IOException;

public class Scraper {

	String city;
	String province;
	
	public Scraper(String city, String province) {
		this.city = city.toLowerCase();
		this.province = province;
	}
	
	public double mainScraper() throws IOException {
		
		int temp = 0;
		
		String url = "https://weatherfarm.com/weather/current/" + province + "/" + city;
		Document doc = Jsoup.connect(url).get();
		Element elem = doc.getElementById("now-temperature");
		
		String temper = elem.text().substring(0, elem.text().length() - 2);
		
		return Double.parseDouble(temper);
	}
	
}