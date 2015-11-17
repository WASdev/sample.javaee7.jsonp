# sample.javaee7.jsonp

JSONP follows the [JSR 353 specification](https://jcp.org/en/jsr/detail?id=353).  The sample.javaee7.jsonp sample application is comprised of 5 servlets that will parse and display JSON data using different implementations for getting the JSON code.  There is a web UI page designed to provide the user with an easier, more intuitive way to try out the JSON-P functionality.

JSON is a popular, commercially used data format for transmission of information and it's been around for a while, prior to Java EE 7.
In actuality JSONP, or "JSON with padding", is a technique for using JSON to overcome cross-domain restrictions in certain browsers and is not fully demonstrated in this sample application. 

## About the User Interface

When you visit the home page by pointing your browser at the host:9080/sample.javaee7.jsonp/ there will be a drop-down listbox of different tests and a set of buttons to run the test. The UI code will deactivate the buttons when they are not applicable to a test. Each of the following test options are available in the test selection box:

* *testSimpleObject* - produces a minimal JSON string 
* *testEmptyObject* - no modifications or input and produces a minimal JSON string
* *testSimpleObjectWithTwoElements* - basic JsonObject with two name value pairs
* *testArray* - build JSON arrangement which corresponds to a JavaScript array structure and then produces a JSON string
* *testNestedStructure* - creates 2-level JSON object (hierarchical level of 2) to produce a complex JSON string

Each of the following buttons will execute the tests with a particular implementation. The button labels are the different servlets providing a unique implementation for reading in the JSON code: 

* *JsonReaderFromReaderTest* - uses Reader object to provide input to the JsonObject 
* *JsonReaderFromStreamTest* - uses JsonReader and JsonObject to take stream input via `Thread.currentThread().getcontextClassLoader.getResourceAsStream()`
* *JsonParserFromReaderTest* - uses JsonParser and String reader
* *JsonParserFromStreamTest* - uses JsonParser and JonsObject to take stream input via `Thread.currentThread().getContextClassLoader().getResourceAsStream()`
* *DOMGeneratorTest* - uses the appropriate Json* object and the add() method to build JSON in memory and then produces a JSON string
* *StreamingGeneratorTest* - uses JsonGeneratorFactory and JsonGenerator object

To the right of each button you will see a constructed URL that will be used to run the test and implementation you have chosen. When a test is selected the URLs will be updated appropriately. When a button is clicked the browser location is updated to the corresponding URL, showing the outcome of the the JSON-P processing. The JSON text itself is hard-coded into this sample application, except for the case of the "FromStream" servlets which uses "*.json" input files .
