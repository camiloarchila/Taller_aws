package co.edu.escuelaing.arep.Service;

import java.util.ArrayList;
import java.util.List;

public class RoundRobin {

    private static List<String> ports;
    private static RoundRobin INSTANCE = new RoundRobin();
    private static  int activePort;


    private RoundRobin(){
        ports = new ArrayList<>();
        activePort = 0;
        ports.add("http://172.31.30.175:42000");
        ports.add("http://172.31.30.175:42001");
        ports.add("http://172.31.30.175:42002");
    }

    public static RoundRobin getInstance(){return INSTANCE; }

    public static String getPort(){
        if(activePort >= ports.size()){
            activePort = 0;
        }
            String port = ports.get(activePort);
            activePort++;

        return port;
    }
}
