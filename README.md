# talabank

# REQUIRED:
1. Maven 3+
2. MySQL server
3. Java 8
4. Tomcat 9

# INSTRUCTIONS
1. git clone https://github.com/kimando/talabank.git
2. cd /TalaBank
3. mvn install
4. deploy the generated /TalaBank/talabank/TalaBank/target/TalaBank.war file on Tomcat

# DATABASE CONFIG:
1. log in your mysql server run the script below

# YOU SHOULD HAVE THE FOLLOWING TABLES CREATED - THEY ARE RELATED, SO FOLLOWING ORDER 1-5 IS KEY
1. branch - dummy bank branches in various locations
2. account_type - dummy account types
3. account - stores the bank accounts
4. transaction_type - different types of transactions supported
5. transaction - stores all the transactions done against a bank account

# HOW TO TEST THE REST API:
1. deploy the war file
2. there is a preconfigured account number - 121343454
3. TEST GET (check balance): http://localhost:8084/BankRESTAPI/rest/api/balance/121343454
4. TEST POST: DEPOSIT - http://localhost:8084/BankRESTAPI/rest/api/deposit
5. TEST POST: WITHDRAW - http://localhost:8084/BankRESTAPI/rest/api/withdraw

# POSTMAN LINKS CAN BE IMPORTED FROM
https://documenter.getpostman.com/view/534616/talabank-endpoints-kimando/2MsYd3
