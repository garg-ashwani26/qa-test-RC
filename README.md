# qa-test-RC

Problem Statement: To test(P0 and P1 scenarios for APIs) given set of APIs via Java based automation framework, where
APIs are hosted locally and provides with a swagger instance.


## Implementation: Project implemented is a data driven framework, key implementations involves:

**1. Derby Database(Embedded DB):** 
   used to replicate a project/service database, helps in querying data to match the result with API responses (used 
   mainly for Get API Request). The Database Utility written to perform CRUD operations can be integrated with other 
   relational databases for data validation.


**2. QueryBuilder for Pagination:** 
   To fetch records from derby db replicating pagination and matching the records with API response.


**3. CSV Files, CSVReader Utility and DataProvider:** 
   to store test data required for APIs, CSV files are used. 
   Corresponding to this, utility has been written to read test data from CSV and get JAVA objects. Testng DataProvider
   is used to map JAVA object to iterable list and run individual test for a set of data.


**4. Test Structure: For each API, 4 set of classes are written:**
   1. Test Class - contains all @Test methods
   2. DataProvider Class - provides with test data for the test case
   3. Test Helper Class - provides with necessary helper functions as per the requirement of individual APIs
   4. Test Assertion Class - performs all the necessary assertions on API response.


**5. POJOs:** 
   For mapping APIs json request body and responses, POJOs haven been created to map JSON to JAVA objects. It helps in 
   iterating over the API responses for assertions and writing request payloads for API requests. Jackson JAVA library 
   is used to serialize/map java objects to JSON and vice-versa.


## Project Requirements:
1. JAVA 11 or higher.
2. Maven to build and run the project.


## Steps:
1. Clone or download the project to your desired directory.
2. Initiate the JAR project to bring up the locally hosted service.
3. Go to project root directory and run command: `maven clean test ` //This will run the only defined testng.xml file


## APIs Covered:
**1. findAllUser -> TestCases:**
   1. default case without any request parameters
   2. Page, Size, Sort(Set of TestData which returns data in API) -> (Combination of) Not defining parameter, 0 value 
      for (page, size), sort based on field with asc/desc order.
   3. Empty record set -> using page number which won't contain any records as per pagination


**2. saveUser -> TestCases:**
   1. Valid Data Cases -> Checking boundary ranges of fields (firstName, lastName, dayOfBirth), valid email IDs
   2. Invalid Data Cases -> Checking outside boundary ranges (firstName, lastName, dayOfBirth), invalid email IDs
   3. Duplicate email ID.
   4. Can be covered (Request without body/payload)


**3. deleteUser -> TestCases:**
   1. Delete existing record
   2. Delete non-existing record
   3. Missing the ID in endpoint