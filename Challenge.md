# Challenge observations

## Step 1
Looking throughout the code I see no separation of concerns in packages and the use of only imperative programming.
The code is incomplete.
There is no sign of spring controller code in it.
The service do not use inversion of dependency, but suggest that the posible ioc to use is method injection which I will change to constructor injection to easy the testing.
The model do not use encapsulation, I would use Lombok to write less code (wish it was kotlin).
The PropertyType class is a candidate to be refactored as an enum.
I have never used mybatis, so to speed up my refactor I will not use it. In normal conditions I would investigate and learn about it.
The class PropertyService says: "Sending the alert should be non-blocking (asynchronous)", so instead of only make that part non-blocking I will use spring's proyect reactor to make the entire app non-blocking in conjunction with r2dbc (mysql flavor) to make the access to de db non-blocking also.

## Step 2
I need to create 2 repositories one for properties and one for addresses.
I have to change most of the mybatis mapping automagic things to jpa automagic.
I have to create a configuration for r2dbc, so I create the schema and insert some data.

## Step 3
Thinking about the schema of the db because it is incomplete. Being a relational db, I made the assumption that every property have one address and one address have one property (I don't know if its like that, but it's easier to implement right now). For that I have to create a fk between them. I also need an index in rent price because there is a specific search in the controller that uses that column.
I will decouple the model into a dto for the controller and a dao for the db.

## Step 4
In order to go bottom up from repo to controller I will make the changes that needs to be done (mono/flux everything).
The annotated controller part will be on another step.

# Step 5
Annotate the controller with the right verb, params and req bodies.

# Step 6
Add swagger for easier manual testing.
Manually test every operation and debug.

# Step 7
I have realized that r2dbc for mysql doesn't support relations yet (I always use the MongoDB & Redis flavor). So I had to chain the relations myself, for every operation. 

# Step 8
Manual tests looks good. Now I'm trying to make unit tests at least one for controller/service/repository.
