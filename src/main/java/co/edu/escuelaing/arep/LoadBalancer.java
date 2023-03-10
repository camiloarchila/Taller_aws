package co.edu.escuelaing.arep;

import co.edu.escuelaing.arep.Service.ApiResponse;
import co.edu.escuelaing.arep.Service.MongoDBService;
import co.edu.escuelaing.arep.Service.RoundRobin;

import static spark.Spark.get;
import static spark.Spark.port;

public class LoadBalancer {

    private static final MongoDBService mongoService = MongoDBService.getInstance();

    private static final RoundRobin roundRobin = RoundRobin.getInstance();

    public static void main(String... args) {
        port(getPort());

        get("/", (req,res) ->{
            return getPage();
        });

        get("hello/:log", (req, res) -> {
            String url = roundRobin.getPort()+"/hello/"+req.params("log");
            return ApiResponse.getResponse(url);
        });
    }
    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }

    private static String getPage(){
        return "HTTP/1.1 200 OK\r\n"
                + "Content-Type: text/html\r\n"
                + "\r\n"
                + "<!DOCTYPE html>\n" +
                "<html>\n" +
                "    <head>\n" +
                "        <title>Form Example</title>\n" +
                "        <meta charset=\"UTF-8\">\n" +
                "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    </head>\n" +
                "    <body>\n" +
                "        <h1>Consulta una pelicula GET</h1>\n" +
                "        <form action=\"/hello\">\n" +
                "            <label for=\"name\">Name:</label><br>\n" +
                "            <input type=\"text\" id=\"name\" name=\"name\" value=\"John\"><br><br>\n" +
                "            <input type=\"button\" value=\"Submit\" onclick=\"loadGetMsg()\">\n" +
                "        </form> \n" +
                "        <div id=\"getrespmsg\"></div>\n" +
                "\n" +
                "        <script>\n" +
                "            function loadGetMsg() {\n" +
                "                let nameVar = document.getElementById(\"name\").value;\n" +
                "                const xhttp = new XMLHttpRequest();\n" +
                "                xhttp.onload = function() {\n" +
                "                    document.getElementById(\"getrespmsg\").innerHTML =\n" +
                "                    this.responseText;\n" +
                "                }\n" +
                "                xhttp.open(\"GET\", \"/hello/\"+nameVar);\n" +
                "                xhttp.send();\n" +
                "            }\n" +
                "        </script>\n" +
                "\n" +
                "</html>";
    }
}