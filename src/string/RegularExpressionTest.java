package string;
import java.awt.Color;
import java.net.URI;
import java.util.Arrays;
import java.util.Collections;

//Enum is used to define collections of constants

public class RegularExpressionTest
{
	
	
	public static void main(String[] args)
	{
		String result = "[{\"orderStatus\": \"PROVISIONED\"}]";
	//	s = s.substring(0, s.length - 1) //removes last character
		
	//	result = result.replaceAll("^. ", "");
		
		//remove step by step
		result = result.replaceAll("^\\[", "");
		result = result.replaceAll("\\]$", "");
		
		System.out.println(result);
		String uri2 = "http://localhost:8085/provisioning/v1/sites/siteUrl/thuystandardsite-test16-new2.eng.webex.com";
		
		System.out.println("Url: " + uri2.toString() );
	     String endpoint = uri2.toString().replaceAll("v1/sites.*", "");
	     System.out.println("Base uri " + endpoint );
	}
}
