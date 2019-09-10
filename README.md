# RetailStoreDiscount

SpringBoot App with REST WebService method

1. Clone the project.
2. Go to project root folder and run "mvn spring-boot:run" via command line.
                    OR
   Start it in eclipse after importing.

3. Please make a post call to "localhost:9090/getDiscountedBill" from any REST client (POSTMAN)

4. SONARQUBE REPORT - https://sonarcloud.io/project/issues?id=asimk1308_RetailStoreDiscount&resolved=false

---------------------------------------
Sample Json Input --
{
   "userType": "AFFILIATE",
   "userRegistrationDate": "2017-08-13",
   "items": [
      {
         "itemName": "Item1",
         "itemType": "GROCERIES",
         "unitPrice": "20",
         "quantity": "16"
      },
      {
         "itemName": "Item2",
         "itemType": "OTHER",
         "unitPrice": "10",
         "quantity": "36"
      }
   ]
}
----------------------------------------

NOTE: Please dont give "userRegistrationDate" greater than current date.
