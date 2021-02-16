# Challenge observations

## Step 1
Looking throughout the code I see no separation of concerns in packages and the use of only imperative programming.
The code is incomplete.
There is no sign of spring controller code in it.
The service do not use inversion of dependency, but suggest that the posible ioc to use is method injection which I will change to constructor injection to easy the testing.
The model do not use encapsulation, I would use Lombok to write less code (wish it was kotlin).
The PropertyType class is a candidate to be refactored as an enum.
I have never used mybatis, so to speed up my refactor I will not use it. But in normal conditions I would investigate and learn about it.
The class PropertyService says: "Sending the alert should be non-blocking (asynchronous)", so instead of only make that part non-blocking I will use spring's proyect reactor to make the entire app non-blocking in conjunction with r2dbc (mysql flavor) to make the access to de db non-blocking also.

## Step 2
I need to create 2 repositories one for properties and one for addresses.
I have to change most of the mybatis mapping automagic things to jpa automagic.
I have to create a configuration for r2dbc so I create the schema and insert some data.



