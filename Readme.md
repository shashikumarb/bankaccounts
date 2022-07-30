# Bank Accounts 

### Acccount creation
- This application will is used to create bank account for customer using REST api.
### Rest end point:
-- http://localhost:8089/bank/create
- sample json
{
  "id": 3,
  "accountNumber": 12345,
  "name": "shashi kumar",
  "gender": "male",
  "dob": "26/01/1987",
  "country": "IND",
  "validationStatus": false
  }
- Kafka producer will send this message to topic called accounts.

-- http://localhost:8089/bank/balanceupdate/{accountnumber}/{amount}
  - This end point will upate the customer account balance by calling sending request to accounttransactions application usint RestTemplate.



