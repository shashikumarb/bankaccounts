# Bank Accounts 

### Acccount creation
- This application will is used to add customer detials using REST api.
### Rest end point:
- http://localhost:8089/bank/create
- sample json
- {
  "id": 3,
  "accountNumber": 12345,
  "name": "shashi kumar",
  "gender": "male",
  "dob": "26/01/1987",
  "country": "IND",
  "validationStatus": false
  }
- Kafka producer will send this message to accounts topic.


