package data_access;


import java.util.*;

import com.mongodb.DBObject;
import com.mongodb.client.*;
import entity.User;
import entity.UserFactory;
import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.MongoException;
import com.mongodb.client.result.InsertOneResult;

import com.mongodb.MongoException;
import com.mongodb.client.*;
import java.util.Iterator;
import com.mongodb.client.FindIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.Iterator;
import java.util.stream.Collectors;

import org.bson.Document;

import org.bson.types.ObjectId;

import static com.mongodb.client.model.Filters.eq;
import org.bson.Document;
import com.mongodb.client.result.InsertOneResult;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;


public class UserDAO {

    private final String uri;
    private final String database;
    private final String collection;

    public static final Map<String, String> accounts = new HashMap<>(); // temporary

    private UserFactory userFactory;

    public static void main(String[] args) throws Exception {
        UserFactory userFactory = new UserFactory() {
            @Override
            public User create(String username, String password) {
                return null;
            }
        };
        UserDAO dao = new UserDAO("mongodb+srv://smartsudoku:smartsudoku@cluster0.hbx3f3f.mongodb.net/\n\n",
                "smartsudoku", "user", userFactory);

        System.out.println("here");
    }

    public UserDAO(String uri, String database, String collection, UserFactory userFactory) throws Exception{
        this.uri = uri;
        this.userFactory = userFactory;
        this.database = database;
        this.collection = collection;

        //Create a MongoDB Client -> Database -> Collection
        //TODO: add a try catch statement
        // exception: https://mongodb.github.io/mongo-csharp-driver/2.6/apidocs/html/T_MongoDB_Driver_MongoException.htm
        MongoClient mongoClient = MongoClients.create(uri);
        MongoDatabase smartSudokuDatabase = mongoClient.getDatabase(database);
        MongoCollection<Document> userCollection = smartSudokuDatabase.getCollection(collection);

        InsertOneResult result = userCollection.insertOne(new Document()
                    .append("_id", new ObjectId())
                    .append("username", "marytesting24")
                    .append("password", "123456")

        );

        List<Document> studentList = userCollection.find().into(new ArrayList<>());
        System.out.println("Student list with an ArrayList:");
        for (Document student : studentList) {
            System.out.println(student.toJson());
        }
    }
//        String uri = "mongodb://localhost:27017";
//        // Create a MongoDB Client
//        MongoClient mongoClient = MongoClients.create(uri); //MongoClients.create(uri);
//        // Create a MongoDB database
//        MongoDatabase database = mongoClient.getDatabase("smartsudoku");
//        // Create a MongoDB Collection
//        MongoCollection<Document> collection = database.getCollection("user");
//        System.out.println("Collection Created Successfully!");

//    String uri = "mongodb+srv://smartsudoku:smartsudoku@cluster0.hbx3f3f.mongodb.net/\n" +
//            "\n";
//
//        try (MongoClient mongoClient = MongoClients.create(uri)) {
//
//        MongoDatabase database = mongoClient.getDatabase("SmartSudokuDatabase");
//        MongoCollection<Document> collection = database.getCollection("UserCollection");
//
//        try {
//            InsertOneResult result = collection.insertOne(new Document()
//                    .append("_id", new ObjectId())
//                    .append("username", "marytesting")
//                    .append("password", "123456")
//
//            );
//
//            System.out.println("Success! Inserted document id: " + result.getInsertedId());
//        } catch (MongoException me) {
//            System.err.println("Unable to insert due to an error: " + me);
//        }
//
//        FindIterable<Document> iterDoc = collection.find();
//
//        Iterator it = iterDoc.iterator();
//        while (it.hasNext()) {
//            System.out.println(it.next());
//        }
//    }
}