package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.DocumentJsonPojo;
import pojo.Location;

public class TestData {
	public DocumentJsonPojo add_place_payload() {
		// Write code here that turns the phrase above into concrete actions

		DocumentJsonPojo p = new DocumentJsonPojo();
		p.setAccuracy(50);
		p.setAddress("29, side layout, cohen 09");
		p.setLanguage("French-IN");
		p.setName("Frontline house");
		p.setPhone_number("(+91) 983 893 3937");
		p.setWebsite("http://google.com");

		Location l = new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		p.setLocation(l);

		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");

		p.setTypes(myList);
		return p;
}
}
