INSERT NEW VEHICLE
curl -X POST localhost:8080/api/vehicle -H 'Content-Type: application/json' -d '{"inventoryCode" : "000","name" : "kyries car","make" : "Toyota","model" : "2013","color" : "green"}' 
 - Insert a new vehicle with vehicle data

GET ALL VEHICLES
curl localhost:8080/api/vehicles 
  - Get all vehicles without any sort or pagination
curl localhost:8080/api/vehicles?sortBy=inventoryCode&pageStart=0&pageEnd=3 
  - Get all vehicles with sort and pagination. 
curl localhost:8080/api/vehicles?pageStart=0&pageEnd=3 
  - Get all vehicles without sort but with pagination
curl localhost:8080/api/vehicles?sortBy=inventoryCode 
  - Get all vehicles with sort but without pagination

UPDATE VEHICLE
curl -X PATCH localhost:8080/api/vehicle -H 'Content-Type: application/json' -d '{"inventoryCode" : "000","name" : "New car name","make" : "new Toyota","model" : "2099","color" : "white"}' 
   - Update vehicle based on inventory code.

DELETE VEHICLE
curl -X DELETE localhost:8080/api/vehicle?inventoryCode=000 
  - Delete a vehicle using inventory code
