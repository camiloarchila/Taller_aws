package co.edu.escuelaing.arep.Service;

import com.mongodb.client.*;
import org.bson.Document;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.util.*;

public class MongoDBService {

        private final MongoClient mongoClient;
        private final MongoDatabase database;
        private final MongoCollection<Document> collection;

        private static MongoDBService INSTANCE = new MongoDBService();
        private  MongoDBService(){
                this.mongoClient = MongoClients.create("mongodb://172.31.30.175:27017/db");
                this.database = mongoClient.getDatabase("db");
                this.collection = database.getCollection("LogsCollection");
        }
        
        public static MongoDBService getInstance(){return INSTANCE; }

        public void insertlog(String log){
                TimeZone zone = TimeZone.getTimeZone("America/Bogota");
                Document document = new Document("Registro", log).append("Fecha", LocalDateTime.now(zone.toZoneId()));
                collection.insertOne(document);
        }


        public JSONObject getLog(){
                JSONObject jsonObject = new JSONObject();
                List<Document> allDocuments = collection.find()
                        .sort(new Document("Fecha", -1))
                        .limit(10)
                        .into(new ArrayList<>());

                for(Document document:allDocuments){
                        Document newDocument = new Document();
                        String id = document.remove("_id").toString();
                        newDocument.append("Registo", document.get("Registro")).append("Fecha", document.get("Fecha").toString());
                        jsonObject.put(id,newDocument.toJson());
                }
                return jsonObject;
        }
}