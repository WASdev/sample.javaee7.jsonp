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

package org.javaee7.json.object.reader;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/JsonReaderFromStreamTest")
public class JsonReaderFromStreamTest extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    private final String servletName = "JsonReaderFromStreamTest";
    PrintWriter out;

    public void testEmptyObject() throws Exception {
        JsonReader jsonReader = Json.createReader(Thread
                .currentThread()
                .getContextClassLoader()
                .getResourceAsStream("/1.json"));
        JsonObject json = jsonReader.readObject();
        
        if(json == null)
        	throw new Exception("json should not be null but it was");
        
        if(!json.isEmpty())
        	throw new Exception("json should be empty but it was not: " + json.toString());
        
        out.println("Successfully created json: " + json);
    }
    
    public void testSimpleObjectWithTwoElements() throws Exception {
        JsonReader jsonReader = Json.createReader(Thread
                .currentThread()
                .getContextClassLoader()
                .getResourceAsStream("/2.json"));
        JsonObject json = jsonReader.readObject();
        
        if(json == null)
        	throw new Exception("json should not be null but it was");
        
        if(json.isEmpty())
        	throw new Exception("json should not be empty but it was: " + json.toString());
        
        if(!json.containsKey("apple"))
        	throw new Exception("Json should contain 'apple' but it did not: " + json);
        if(!"red".equals(json.getString("apple")))
        	throw new Exception("Json should have apple=red but it did not: " + json);
        if(!json.containsKey("banana"))
        	throw new Exception("Json should contain 'banana' but it did not: " + json);
        if(!"yellow".equals(json.getString("banana")))
        	throw new Exception("Json should contain banana=yellow but it did not: " + json);
        
        out.println("Successfully created json: " + json);
    }
    
    public void testArray() throws Exception {
        JsonReader jsonReader = Json.createReader(Thread
                .currentThread()
                .getContextClassLoader()
                .getResourceAsStream("/3.json"));
        JsonArray jsonArr = jsonReader.readArray();
        
        if(jsonArr == null)
        	throw new Exception("JsonArr should not have been null but it was.");
        if(2 != jsonArr.size())
        	throw new Exception("JsonArr size should be 2 but it was: " + jsonArr.size());

        String expectedJson = "{\"apple\":\"red\"}";
        if(!expectedJson.equals(jsonArr.get(0).toString()))
        	throw new Exception("JsonArr should have been " + expectedJson + " but instead it was " + jsonArr.getInt(0));
        
        expectedJson = "{\"banana\":\"yellow\"}";
        if(!expectedJson.equals(jsonArr.get(1).toString()))
        	throw new Exception("JsonArr should have been " + expectedJson + " but instead it was " + jsonArr.getInt(1));
        
        out.println("Successfully created jsonArr: " + jsonArr);
    }
    
    public void testNestedStructure() throws Exception {
        JsonReader jsonReader = Json.createReader(Thread
                .currentThread()
                .getContextClassLoader()
                .getResourceAsStream("/4.json"));
        JsonObject json = jsonReader.readObject();

        if(json == null)
        	throw new Exception("Json should not be null but it was.");
        if(json.isEmpty())
        	throw new Exception("Json should not be empty but it was.");
        if(!json.containsKey("title"))
        	throw new Exception("Json should contain key 'title' but it did not.");
        if(!"The Matrix".equals(json.getString("title")))
        	throw new Exception("Json should have title=The Matrix but it was title=" + json.getString("title"));
        if(!json.containsKey("year"))
        	throw new Exception("Json should contain key 'year' but it did not.");
        if(1999 != json.getInt("year"))
        	throw new Exception("Json should contain year=1999 but it was year=" + json.getInt("year"));
        if(!json.containsKey("cast"))
        	throw new Exception("Json should contain key 'cast' but it did not.");
        
        out.println("Successfully created json: " + json);
        
        JsonArray jsonArr = json.getJsonArray("cast");
        if(jsonArr == null)
        	throw new Exception("JsonArr should not be null but it was.");
        if(3 != jsonArr.size())
        	throw new Exception("JsonArr size should be 3 but it was: " + jsonArr.size());
        String expectedJson = "[\"Keanu Reaves\",\"Laurence Fishburne\",\"Carrie-Anne Moss\"]";
        if(!expectedJson.equals(jsonArr.toString()))
        	throw new Exception("JsonArr should have been " + expectedJson + " but instead it was " + jsonArr.toString());
        
        out.println("Successfully created jsonArr: " + jsonArr);
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
            //            out.println(" <--- " + test + " " + SUCCESS_MESSAGE);
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
