package co.edu.escuelaing.arep;

import co.edu.escuelaing.arep.Service.MongoDBService;

import static spark.Spark.*;

public class SparkWebServer {

        private static final MongoDBService mongoService = MongoDBService.getInstance();
        public static void main(String... args){
        port(getPort());
        get("hello/:log", (req,res) -> {
                res.header("Content-Type", "application/json");
                mongoService.insertlog(req.params("log"));
                return mongoService.getLog();
        });
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }

}