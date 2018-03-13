# sample.javaee7.jsonp [![Build Status](https://travis-ci.org/WASdev/sample.javaee7.jsonp.svg?branch=master)](https://travis-ci.org/WASdev/sample.javaee7.jsonp)

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

## Running in Eclipse

### Maven

1. Download and install [Eclipse with the WebSphere Developer Tools](https://developer.ibm.com/wasdev/downloads/liberty-profile-using-eclipse/).
2. Clone this repository.
3. Import the sample into Eclipse using *File -> Import -> Maven -> Existing Maven Projects* option.
4. Click Yes to the WebSphere Liberty dialog to automatically create server in the Servers view for this project.
5. Deploy the sample into Liberty server. Right click on the project and select *Run As -> Run on Server* option. Find and select the Liberty server and press *Finish*.
6. Go to: [http://localhost:9080/sample.javaee7.jsonp/](http://localhost:9080/sample.javaee7.jsonp/)

### Gradle

Eclipse will use the Eclipse Buildship Gradle Plugin for Gradle project management and accessibility to tasks.

1. Go to *Help > Eclipse Marketplace > Install Buildship Gradle Integration 2.0*
2. Clone this project and import into Eclipse as an 'Existing Gradle Project'.
3. Go to *Window > Show View > Other > Gradle Executions & Gradle Tasks*
4. Go to Gradle Tasks view and run `clean` in build folder, then `build` in build folder, then `libertyStart` in liberty folder.
5. You should see the following in the console: `Application sample.javaee7.jsonp started in XX.XX seconds.`
6. Go to: [http://localhost:9080/sample.javaee7.jsonp/](http://localhost:9080/sample.javaee7.jsonp/)

## Running with the Command-line

### Maven

This project can be built with [Apache Maven](http://maven.apache.org/). The project uses the [Liberty Maven Plug-in] to automatically download and install Liberty runtime from the [Liberty repository](https://developer.ibm.com/wasdev/downloads/). Liberty Maven Plug-in is also used to create, configure, and run the application on the Liberty server.

Use the following steps to run the application with Maven:

1. Execute full Maven build. This will cause Liberty Maven Plug-in to download and install Liberty profile server.
    ```
    $ mvn clean install
    ```

2. To run the server with the JSONP application execute:
    ```
    $ mvn liberty:run-server
    ```

Once the server is running, the application will be available under [http://localhost:9080/sample.javaee7.jsonp/](http://localhost:9080/sample.javaee7.jsonp/).

### Gradle

This project can also be built and run with [Gradle]. The provided `build.gradle` file applies the [Liberty Gradle Plug-in] and is configured to automatically download and install the Liberty Java EE Web Profile 7 runtime from Maven Central. The Liberty Gradle Plug-in has built-in tasks that can be used to create, configure, and run the application on the Liberty server.

Use the following steps to run the application with Gradle:

1. Execute the full Gradle build. The Liberty Gradle Plug-in will download and install the Liberty server.
    ```bash
    $ ./gradlew clean build
    ```

2. To start the server with the Servlet sample execute:
    ```bash
    $ ./gradlew libertyStart
    ```

    Alternatively, execute the run command:
    ```bash
    $ ./gradlew libertyRun --no-daemon
    ```

Once the server has started, the application will be available under [http://localhost:9080/sample.javaee7.jsonp](http://localhost:9080/sample.javaee7.jsonp).

3. To stop the server, execute:
    ```bash
    $ ./gradlew libertyStop
    ```  

Please refer to the [ci.gradle] repository for documentation about using the Liberty Gradle Plug-in.

## Deploying to Bluemix

Click the button below to deploy your own copy of this application to [Bluemix](https://bluemix.net).

[![Deploy to Bluemix](https://bluemix.net/deploy/button.png)](https://bluemix.net/deploy?repository=https://github.com/WASdev/sample.javaee7.jsonp)

Once the application is deployed and running in Bluemix, it will be available under
[http://MYAPPNAME.mybluemix.net/sample.javaee7.jsonp/](http://MYAPPNAME.mybluemix.net/sample.javaee7.jsonp/).

## Notice

© Copyright IBM Corporation 2015, 2017.

## License

This information contains sample code provided in source code form. You may copy, modify, and distribute these sample programs in any form without payment to IBM for the purposes of developing, using, marketing or distributing application programs conforming to the application programming interface for the operating platform for which the sample code is written.

Notwithstanding anything to the contrary, IBM PROVIDES THE SAMPLE SOURCE CODE ON AN "AS IS" BASIS AND IBM DISCLAIMS ALL WARRANTIES, EXPRESS OR IMPLIED, INCLUDING, BUT NOT LIMITED TO, ANY IMPLIED WARRANTIES OR CONDITIONS OF MERCHANTABILITY, SATISFACTORY QUALITY, FITNESS FOR A PARTICULAR PURPOSE, TITLE, AND ANY WARRANTY OR CONDITION OF NON-INFRINGEMENT. IBM SHALL NOT BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY OR ECONOMIC CONSEQUENTIAL DAMAGES ARISING OUT OF THE USE OR OPERATION OF THE SAMPLE SOURCE CODE. IBM SHALL NOT BE LIABLE FOR LOSS OF, OR DAMAGE TO, DATA, OR FOR LOST PROFITS, BUSINESS REVENUE, GOODWILL, OR ANTICIPATED SAVINGS. IBM HAS NO OBLIGATION TO PROVIDE MAINTENANCE, SUPPORT, UPDATES, ENHANCEMENTS OR MODIFICATIONS TO THE SAMPLE SOURCE CODE.

[Liberty Maven Plug-in]: https://github.com/WASdev/ci.maven
[Liberty Gradle Plug-in]: https://github.com/WASdev/ci.gradle
[ci.gradle]: https://github.com/WASdev/ci.gradle
[Gradle]: https://gradle.org
