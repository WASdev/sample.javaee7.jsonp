
/*
 * COPYRIGHT LICENSE: This information contains sample code provided in source
 * code form. You may copy, modify, and distribute these sample programs in any 
 * form without payment to IBM for the purposes of developing, using, marketing 
 * or distributing application programs conforming to the application programming 
 * interface for the operating platform for which the sample code is written. 
 * 
 * Notwithstanding anything to the contrary, IBM PROVIDES THE SAMPLE SOURCE CODE 
 * ON AN "AS IS" BASIS AND IBM DISCLAIMS ALL WARRANTIES, EXPRESS OR IMPLIED, INCLUDING, 
 * BUT NOT LIMITED TO, ANY IMPLIED WARRANTIES OR CONDITIONS OF MERCHANTABILITY, 
 * SATISFACTORY QUALITY, FITNESS FOR A PARTICULAR PURPOSE, TITLE, AND ANY WARRANTY OR 
 * CONDITION OF NON-INFRINGEMENT. IBM SHALL NOT BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL OR CONSEQUENTIAL DAMAGES ARISING OUT OF THE USE OR
 * OPERATION OF THE SAMPLE SOURCE CODE. IBM HAS NO OBLIGATION TO PROVIDE
 * MAINTENANCE, SUPPORT, UPDATES, ENHANCEMENTS OR MODIFICATIONS TO THE SAMPLE
 * SOURCE CODE.
 * 
 * (C) Copyright IBM Corp. 2015.
 * 
 * All Rights Reserved. Licensed Materials - Property of IBM.  
 */

package org.javaee7.json.streaming.parser;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;

import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/JsonParserFromStreamTest")
public class JsonParserFromStreamTest extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    private final String servletName = "JsonParserFromStreamTest";
    PrintWriter out;

    public void testEmptyObject() throws Exception {
        JsonParser parser = Json.createParser(Thread
                .currentThread()
                .getContextClassLoader()
                .getResourceAsStream("/1.json"));

        compareEvent(JsonParser.Event.START_OBJECT, parser.next());
        compareEvent(JsonParser.Event.END_OBJECT, parser.next());
    }

    public void testSimpleObject() throws Exception {
        JsonParser parser = Json.createParser(Thread
                .currentThread()
                .getContextClassLoader()
                .getResourceAsStream("/2.json"));

        compareEvent(JsonParser.Event.START_OBJECT, parser.next());
        compareEvent(JsonParser.Event.KEY_NAME, parser.next());
        compareEvent(JsonParser.Event.VALUE_STRING, parser.next());
        compareEvent(JsonParser.Event.KEY_NAME, parser.next());
        compareEvent(JsonParser.Event.VALUE_STRING, parser.next());
        compareEvent(JsonParser.Event.END_OBJECT, parser.next());
    }

    public void testArray() throws Exception {
        JsonParser parser = Json.createParser(Thread
                .currentThread()
                .getContextClassLoader()
                .getResourceAsStream("/3.json"));

        compareEvent(JsonParser.Event.START_ARRAY, parser.next());
        compareEvent(JsonParser.Event.START_OBJECT, parser.next());
        compareEvent(JsonParser.Event.KEY_NAME, parser.next());
        compareEvent(JsonParser.Event.VALUE_STRING, parser.next());
        compareEvent(JsonParser.Event.END_OBJECT, parser.next());
        compareEvent(JsonParser.Event.START_OBJECT, parser.next());
        compareEvent(JsonParser.Event.KEY_NAME, parser.next());
        compareEvent(JsonParser.Event.VALUE_STRING, parser.next());
        compareEvent(JsonParser.Event.END_OBJECT, parser.next());
        compareEvent(JsonParser.Event.END_ARRAY, parser.next());
    }

    public void testNestedStructure() throws Exception {
        JsonParser parser = Json.createParser(Thread
                .currentThread()
                .getContextClassLoader()
                .getResourceAsStream("/4.json"));

        compareEvent(JsonParser.Event.START_OBJECT, parser.next());
        compareEvent(JsonParser.Event.KEY_NAME, parser.next());
        compareEvent(JsonParser.Event.VALUE_STRING, parser.next());
        compareEvent(JsonParser.Event.KEY_NAME, parser.next());
        compareEvent(JsonParser.Event.VALUE_NUMBER, parser.next());
        compareEvent(JsonParser.Event.KEY_NAME, parser.next());
        compareEvent(JsonParser.Event.START_ARRAY, parser.next());
        compareEvent(JsonParser.Event.VALUE_STRING, parser.next());
        compareEvent(JsonParser.Event.VALUE_STRING, parser.next());
        compareEvent(JsonParser.Event.VALUE_STRING, parser.next());
        compareEvent(JsonParser.Event.END_ARRAY, parser.next());
        compareEvent(JsonParser.Event.END_OBJECT, parser.next());
    }
    
	private void compareEvent(Event expected, Event actual) throws Exception{
        if(expected.compareTo(actual) != 0)
        	throw new Exception("Expected event '" + expected + "' but instead got : " + actual);
        out.println("Got event: " + actual);
	}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String SUCCESS_MESSAGE = "COMPLETED SUCCESSFULLY";
        String test = request.getParameter("test");
        out = response.getWriter();
        out.println("<head><style>.frm1{padding: 15px;background-color: #9666af;}.frm2{padding-left: 25px;}.big{font-size: 26px; color: white;}.ruby{color: #dd0077;}.basic{font-family: \"Verdana\";color: #888888;padding: 25px;padding-left:0px;}.rslt{padding-left: 0px;}.grn{color: #00cc66;}</style></head>");
        out.println(" <div class=\"frm1\"><span class=\"big\">" + servletName + " (servlet)</span></div><div class=\"frm2\"><div class=\"basic\">");
        System.out.println(" ---> " + servletName + " is starting test: " + test);

        try {
            getClass().getMethod(test).invoke(this);
            out.println("</div> <div class=\"rslt\">TEST: " + test + "</div><div class=\"grn\">" + SUCCESS_MESSAGE + "</div></div>");
            System.out.println(" <--- " + test + " " + SUCCESS_MESSAGE);
        } catch (Throwable x) {
            if (x instanceof InvocationTargetException)
                x = x.getCause();
            out.println("<pre>ERROR in " + test + ":");
            x.printStackTrace(out);
            out.println("</pre>");
            x.printStackTrace();
            out.println(" <--- " + test + " FAILED");
            System.out.println(" <--- " + test + " FAILED");
        } finally {
            out.flush();
            out.close();
        }
    }
}
