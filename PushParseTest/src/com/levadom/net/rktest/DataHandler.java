package com.levadom.net.rktest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import android.content.Context;
import android.util.Log;

import com.levadom.net.R;

public class DataHandler extends DefaultHandler{

	//string to track each entry
//	private String currBrand = "";
	//flag to keep track of XML processing
//	private boolean isProduct = false;
	//context for user interface
	private Context theContext;
	
	
	
	String nowTagStr;
	String nowTotalTitleTag;
	String txtTotalTilteBuffer;
	String txtBuffer;
	HashMap<String, String> currentItem;
//	int numOfItem;
	String rssTitleString;
	
	
	ArrayList<HashMap<String, String>> allItems;
	//constructor
	public DataHandler(Context cont) {
	    super();
	    
	    theContext = cont;
//	    numOfItem = 0;
	    nowTotalTitleTag = "";
	    txtTotalTilteBuffer = "";
	    txtBuffer = "";
	    
	    allItems = new ArrayList<HashMap<String,String>>();
	    currentItem = new HashMap<String, String>();
	    theContext = cont;
	}
	//start of the XML document
	public void startDocument () { 
		Log.i("DataHandler", "Start of XML document");
		nowTagStr = "";
		}

	//end of the XML document
	public void endDocument () { 
		Log.i("DataHandler", "End of XML document");
		((MainActivity)theContext).onFinishParseData();
	}

	//opening element tag
	public void startElement (String uri, String name, String qName, Attributes atts)
	{
	    //handle the start of an element
		Log.i("DataHandler", "End of XML document");
		if (qName.equals(AppConstants.keywordTITLE)) {
	        nowTotalTitleTag = qName;
	        txtTotalTilteBuffer = "";
	    }
		if (qName.equals(AppConstants.keywordITEM_RSS)) {
	        nowTagStr = qName;
	        txtBuffer = "";
	    }
		if (qName.equals(AppConstants.keywordTITLE)) {
	        txtBuffer = "";
	    }
		if (qName.equals(AppConstants.keywordLINK_RSSCEL)) {
	        txtBuffer = "";
	    }
		if (qName.equals(AppConstants.keywordDESCRIPTION)) {
	        txtBuffer = "";
	    }
	}

	//closing element tag
	boolean firstFindTitle = false;
	public void endElement (String uri, String name, String qName)
	{
	    //handle the end of an element
		Log.i("DataHandler", "End of XML document");
		
		if (!nowTagStr.equals(AppConstants.keywordITEM_RSS)) {
			if(firstFindTitle) return;
	        if (nowTotalTitleTag.equals(AppConstants.keywordTITLE))
	        {
	            rssTitleString = txtTotalTilteBuffer;
	            firstFindTitle = true;
	        }
	        return;
	    }
		
		
	    if (qName.equals(AppConstants.keywordTITLE)) {
	    	currentItem.put(qName, txtBuffer);
	    } else if (qName.equals(AppConstants.keywordLINK_RSSCEL)) {
	    	currentItem.put(qName, txtBuffer);
	    } else if (qName.equals(AppConstants.keywordDESCRIPTION)) {
	    	String imgUrl = getImgUrlFromDesk(txtBuffer);
	    	//currentItem.put(qName, imgUrl);
	    	currentItem.put(AppConstants.keywordPHOTOURL, imgUrl);
	    } else if (qName.equals(AppConstants.keywordITEM_RSS)) {
	        allItems.add(currentItem);
	        currentItem = new HashMap<String, String>();

	    }
	}

	//element content
	public void characters (char ch[], int start, int length)
	{
	    //process the element content
		//string to store the character content
		String currText = "";
		//loop through the character array
		for (int i=start; i<start+length; i++)
		{
		    switch (ch[i]) {
		    case '\\':
		        break;
//		    case '"':
//		        break;
		    case '\n':
		        break;
		    case '\r':
		        break;
		    case '\t':
		        break;
		    default:
		        currText += ch[i];
		        break;
		    }
		}
		//prepare for the next item
//		if(isProduct && currText.length()>0)
//		    currBrand += currText+"\n";
		if (nowTagStr.equals(AppConstants.keywordITEM_RSS)) {
	        txtBuffer = txtBuffer + currText;
	    }
	    else
	    {
	        if (nowTotalTitleTag.equals(AppConstants.keywordTITLE))
	            txtTotalTilteBuffer =txtTotalTilteBuffer + currText;
	    }
	}
	
	public void getData()
	{
	    //take care of SAX, input and parsing errors
	    try
	    {
	            //set the parsing driver
	        System.setProperty("org.xml.sax.driver","org.xmlpull.v1.sax2.Driver");
	            //create a parser
	        SAXParserFactory parseFactory = SAXParserFactory.newInstance();
	        SAXParser xmlParser = parseFactory.newSAXParser();
	            //get an XML reader
	        XMLReader xmlIn = xmlParser.getXMLReader();
	            //instruct the app to use this object as the handler
	        xmlIn.setContentHandler(this);
	            //provide the name and location of the XML file **ALTER THIS FOR YOUR FILE**
	        URL xmlURL = new URL(theContext.getResources().getString(R.string.server_url));
	            //open the connection and get an input stream
	        URLConnection xmlConn = xmlURL.openConnection();
	        InputStreamReader xmlStream = new InputStreamReader(xmlConn.getInputStream());
	            //build a buffered reader
	        BufferedReader xmlBuff = new BufferedReader(xmlStream);
	            //parse the data
	        xmlIn.parse(new InputSource(xmlBuff));
	    }
	    catch(SAXException se) {
	    	Log.e("AndroidTestsActivity", 
	            "SAX Error " + se.getMessage()); }
	    catch(IOException ie) {
	    	Log.e("AndroidTestsActivity", 
	            "Input Error " + ie.getMessage()); }
	    catch(Exception oe) {
	    	Log.e("AndroidTestsActivity", 
	            "Unspecified Error " + oe.getMessage()); }
	        //return the parsed product list	   
	}
	
	public String getRootTitle() {
		return rssTitleString;
	}
	public ArrayList<HashMap<String, String>> getRssDataOfEachItem() {
		return allItems;
	}

	private String getImgUrlFromDesk(String desc) {
        int st_pos = desc.indexOf("src");
        String des_update = desc.substring(st_pos + 5);
        int end_pos = des_update.indexOf("\"");
        String des_final = des_update.substring(0, end_pos);
        return des_final;
	}
}
