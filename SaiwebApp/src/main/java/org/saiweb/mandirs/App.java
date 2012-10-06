package org.saiweb.mandirs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBElement;

import org.saiweb.mandirs.jaxb.Address;
import org.saiweb.mandirs.jaxb.MandirDetails;
import org.saiweb.mandirs.jaxb.MandirInfo;
import org.saiweb.mandirs.jaxb.ObjectFactory;
import org.saiweb.mandirs.jaxb.SocialActivities;
import org.saiweb.mandirs.jaxb.SpecialEvents;
import org.saiweb.mandirs.jaxb.WeeklyActivities;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	MandirInfo mandir = new MandirInfo();
    	mandir.setMandirCode("hyd001");
    	mandir.setMandirId(10);
    	mandir.setMandirName("Sri Sri Sri Shirdi Saibaba Mandir, Hyderabad");
    	Address address = new Address();
    	address.setLine1("Road No: 2  (EWS centre (or) DhanaLaxmi Centre)");
    	address.setLine2("");
    	address.setLine3("");
    	address.setCity("Hyderabad");
    	address.setState("Andhra Pradesh");
    	address.setCountry("India");
    	address.setLatitude("");
    	address.setLongitude("");
    	address.setDirections("Get down at K.P.H.B (Kukatpally Housing Board colony) main road bus stop.Go Straight to Road No:2");
    	
    	
    	MandirDetails mandirDetails = new MandirDetails();
    	mandirDetails.getLine().add("sai");
    	mandirDetails.getLine().add("baba");
    	mandir.setDetails(mandirDetails);
    	mandir.setAddress(address);
    	SocialActivities sa = new SocialActivities();
    	sa.getPara().add("para1");
    	mandir.setSocialActivities(sa);
    	WeeklyActivities wa = new WeeklyActivities();
    	wa.getWeekly().add("1");
    	
    	mandir.setWeeklyActivities(wa); 
    	SpecialEvents se = new SpecialEvents();
    	se.getEvents().add("event");
    	mandir.setSpecialEvents(se);
    	
    	JAXBElement<MandirInfo> zooInfoElement = (new ObjectFactory()).createMandir(mandir);
    	
        // create a Marshaller and marshal to System.out
        try {
			JAXB.marshal(zooInfoElement, new FileOutputStream(new File("mandirs.xml")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     
    	
        System.out.println( "Hello World!" );
    }
}
