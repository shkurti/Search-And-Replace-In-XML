import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;


public class Main {

	public static void main(String argv[]) {

	   try {
		String filepath = "C:\\SC.xml";
		DocumentBuilderFactory docBildFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = docBildFactory.newDocumentBuilder();
		Document doc = documentBuilder.parse(filepath);

		doc.getDocumentElement().normalize();

		Scanner sc =  new Scanner(System.in);
		

		for(int i=0; i<doc.getElementsByTagName("limit").getLength();i++) {
			Node zzz = doc.getElementsByTagName("limit").item(i);
			String zz1 = zzz.getAttributes().getNamedItem("id").getNodeValue();
			
			if(zz1.equals("l7E1EE10784374A44B389EDEDFF011B2A")) {				
				Node data = doc.getElementsByTagName("limit").item(i);		        
		        Node xll = data.getChildNodes().item(3);
		        Node xl = data.getChildNodes().item(1);
		        System.out.println("Enter the new value for <" + xll.getNodeName() + "> tag : ");
		        String tag = sc.nextLine();
				xll.setTextContent(tag);
				System.out.println("****************************************");
		        System.out.println("The selsected ID : "+data.getAttributes().getNamedItem("id").getNodeValue());
		        System.out.println();
		        System.out.println("The selsected type : <"+ xl.getNodeName()+">"+xl.getTextContent()+"<"+xl.getNodeName()+">");
		        System.out.println();
				System.out.println("The value has been changed to : <"+xll.getNodeName()+">"+xll.getTextContent()+"<"+xll.getNodeName()+">");
				System.out.println();
			}
		}
		
		// Save changes into the existing xml file
		TransformerFactory tF = TransformerFactory.newInstance();
		Transformer transformer = tF.newTransformer();
		DOMSource domS = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(filepath));
		transformer.transform(domS, result);

		System.out.println("Completed");

	   } catch (ParserConfigurationException pce) {
		pce.printStackTrace();
	   } catch (TransformerException tfe) {
		tfe.printStackTrace();
	   } catch (IOException ioe) {
		ioe.printStackTrace();
	   } catch (SAXException sae) {
		sae.printStackTrace();
	   }
	}
}
