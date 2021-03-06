package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.DeletePayload;
import pojo.Location;

public class TestDataBuild {
	
	public AddPlace addPlacePayload(String name, String lang, String address)
	{
		AddPlace p =new AddPlace();
		p.setAccuracy(50);
		p.setAddress(address);
		p.setLanguage(lang);
		p.setPhone_number("(+91) 983 893 3937");
		p.setWebsite("https://rahulshettyacademy.com");
		p.setName(name);
		List<String> myList =new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");

		p.setTypes(myList);
		Location l =new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);

		p.setLocation(l);
		
		return p;
	}
	
	public DeletePayload deletePlacepayload(String place_id)
	{
		
		DeletePayload d=new DeletePayload();
		d.setPlace_id(place_id);
		
		return d;
	}

}
