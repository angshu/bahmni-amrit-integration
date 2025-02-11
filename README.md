# bahmni amrit integration example


## Setup
To setup the example app's database, we will use hsqldb, file based and not in-memory.  
You may connect to any database you want and would need to configure properties and setup accordingly.

- Configure AMRIT as identifier in Bahmni. 
    * log onto OpenMRS Admin console 
    * Go to Administration => Manage Identifier Types => Add Patient Identifier Type, 
here create a patient identifier type of Amrit with 'location behaviour'='Not used' and 'Uniqueness Behaviour'='Unique'
    * note down generated UUID (click on Amrit patient identifier in Manage Identifier Types) and update bahmni.amrit.identifierType.uuid in atomfeed.properties file in this application
    * This identifier must also be configured for Bahmni to display and use in search. To do this, go to Administration => Settings => Select Bahmni and edit the property "Extra Patient Identifier Types". 
    Take care of appending to existing values and leave no spaces in betweeen. Eg. uuid1,uuid2
    
- Identify the main Bahmni identifier details. To do that
    *   https://bahmni-server/openmrs/ws/rest/v1/idgen/identifiertype to get identifier type uuid - 
    the one that says "Patient Identifier" is most likely the right one, unless the implementation has overridden the identifier
    *   While at it, also identify the identifier source for Bahmni's own patient identifier and the associated identifier prefix.
    * From the above response you can update the following properties in atomfeed.proeprties file - "bahmni.identifierType.uuid", ""bahmni.identifierSource.uuid", "bahmni.identifier.prefix" 
- Identify the person attributes. To do that
    * check the response to https://bahmni-server/openmrs/ws/rest/v1/personattributetype
    * identify the attribute you are interested in. Eg. this application maps the phone to Bahmni Patient's Secondary Contract attribute.
    * copy the uuid and update "bahmni.attribute.secondaryContact.uuid" in atomfeed.properties (note, this is just example)
- Setup other configuration
    *  Check the atomfeed.properties and change accordingly. Change keys like opemrs.*
 
- Setup Amrit System Configuration
    * Check the atomfeed.properties and set accordingly. E.g "amrit.patient.uri" - E.g. http://localhost:3000/patients
    * For testing purpose, you can setup a mock http server (e.g. mockoon) serving list of patients from http://localhost:3000/patients
    * The API (http://localhost:3000/patients) returns the JSON structure as in test/resources/amrit_patients_search_results.json  
    * if different, then you probably need to change thee model in "AmritPatientSearchResult.java"
     
      

#### Pre-requisite
* mvn 3.6.2. 
* Java 1.8, works on 11 as well (with some warnings). You may need to change (or remove) the "maven.compiler.source" properties in the root pom.xml.  
* download HSQLDB from https://sourceforge.net/projects/hsqldb/files/latest/download (v 2.6.1) and unzip it to a folder.
The doc here assumes a directory "db" under the project directory

#### create db and start the database server
> cd /workspace-dir/bahmni-amrit-integration/

> mkdir db

> cp /download-location/hsqldb-2.6.1.zip /workspace-dir/bahmni-amrit-integration/db/

> cd db/

> unzip hsqldb-2.6.1.zip

> cd hsqldb-2.6.1/hsqldb/data

> java -cp ../lib/hsqldb-jdk8.jar org.hsqldb.server.Server --database.0 file:testdb --dbname.0 bahmni_amrit


####  running the app 
> cd /workspace-dir/bahmni-amrit-integration/feed-integration-webapp/

> cd feed-integration-webapp/

> mvn clean install 

> mvn spring-boot:run

####  check with the hsql db client 
> cd /workspace-dir/bahmni-amrit-integration/db/hsqldb-2.6.1/hsqldb/data
 
> java -cp ../lib/hsqldb-jdk8.jar org.hsqldb.util.DatabaseManager

* The above show a rudimentary UI, specify
- type: HSQL Database engine server 
- url: jdbc:hsqldb:hsql://localhost/bahmni_amrit
- user: SA
- password: 

* Clicking ok, and on left panel you should be able to see tables like - failed_events, markers, etc. 
 
 
 ####  test the app
 * open browser to "http://localhost:8080/health-check" to see "Hello World!"
 
 
 ### useful info
 * Change log level in the log4j2.xml, the log is output at /tmp/bahmni-feed-integration-example.log
 * Atomfeed set the markers to first page if you don't set it. 
 So, Set the markers manually after provisioning and before deployment. Especially openmrs patient/encounter feed as we are reading patient/encounter feed to figure out the appropriate actions.
 
 * Use the following sql query to set the markers manually according to the events in your machine. 
 (change the last_read_entry_id and feed_uri_for_last_read_entry )
 
 > insert into markers (feed_uri, last_read_entry_id, feed_uri_for_last_read_entry) 
     values ('http://loalhost:8080/openmrs/ws/atomfeed/encounter/recent', '?', '?');
 
 ### TODO
 1. Upgrade spring 4 to latest. Right now running on Java 11, gives following error
 >  WARNING: An illegal reflective access operation has occurred
    WARNING: Illegal reflective access by org.springframework.cglib.core.ReflectUtils$2 (file:/Users/angshus/.m2/repository/org/springframework/spring-core/4.1.6.RELEASE/spring-core-4.1.6.RELEASE.jar) to method java.lang.ClassLoader.defineClass(java.lang.String,byte[],int,int,java.security.ProtectionDomain)
    WARNING: Please consider reporting this to the maintainers of org.springframework.cglib.core.ReflectUtils$2
    WARNING: Use --illegal-access=warn to enable warnings of further illegal reflective access operations
    WARNING: All illegal access operations will be denied in a future release 
