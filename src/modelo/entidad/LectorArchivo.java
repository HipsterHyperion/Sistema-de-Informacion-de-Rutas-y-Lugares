
package modelo.entidad;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.jxmapviewer.viewer.GeoPosition;
import repositorio.Repositorio;

public class LectorArchivo {

    private File archivo;
    private FileReader fr;
    private BufferedReader br;
    private String linea;
    private Repositorio repositorio;
    
    
    // 
    public LectorArchivo(String path, Repositorio repositorio) {
        this.repositorio = repositorio;
        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File(path);
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero
            while ((linea = br.readLine()) != null) {////////////////####################
                if (linea.contains("\"placeVisit\"")  ) {
                    nuevoPlaceVisit();
                }

                if (linea.contains("activitySegment")) {
                    nuevoActivitySegment();
                }
            }

        } catch (IOException e) {
            System.out.println(e);
        } finally {
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta 
            // una excepcion.
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (IOException e2) {
            }
        }
    }

    
    private void nuevoPlaceVisit() throws IOException {
        
        PlaceVisit placevisit = new PlaceVisit();
        double latitud;
        double longitud;
        String auxiliar;
        GeoPosition geoposition = null;
        // Definir un DateTimeFormatter con el formato ISO 8601 sin fracciones de segundo
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
        // Cadena de fecha y hora
        String cadenaFechaHora;
        LocalDateTime fechaHora;

        linea = br.readLine();//      "location": {
        linea = br.readLine();//        "latitudeE7": -332984757,
        auxiliar = linea.substring(22, 25) + "."
                + linea.substring(25, 32);//-33.2984757
        latitud = Double.parseDouble(auxiliar);

        linea = br.readLine();//        "longitudeE7": -663373180,
        auxiliar = linea.substring(23, 26) + "."
                + linea.substring(26, 33);//-66.3373180
        longitud = Double.parseDouble(auxiliar);
        geoposition = new GeoPosition(latitud, longitud);
        placevisit.setLocation(geoposition);
        
        linea = br.readLine();
        linea = br.readLine();
        placevisit.setAddress(linea.substring(19));

        while (!linea.contains("duration")) {//duration

            linea = br.readLine();
        }
        if (linea.contains("duration")) {//duration

            linea = br.readLine();//        "startTimestamp": "2022-04-16T06:50:07.580Z",
            cadenaFechaHora = linea.substring(27, 46) +"Z";//2022-04-16T06:50:07Z
            // Analizar la cadena de fecha y hora y crear un objeto LocalDateTime
            fechaHora = LocalDateTime.parse(cadenaFechaHora, formatter);
            fechaHora = fechaHora.minusHours(3);//2022-04-16T03:50:07Z
            placevisit.setStartTimestamp(fechaHora);
            
            linea = br.readLine();
            cadenaFechaHora = linea.substring(25, 44) +"Z";
            // Analizar la cadena de fecha y hora y crear un objeto LocalDateTime
            fechaHora = LocalDateTime.parse(cadenaFechaHora, formatter);
            fechaHora = fechaHora.minusHours(3);
            placevisit.setEndTimestamp(fechaHora);
        }
        
        repositorio.nuevoPlaceVisit(placevisit);
        
    }
    

    private void nuevoActivitySegment() throws IOException {
        
        ActivitySegment activitysegment = new ActivitySegment();
        int distancia;
        int i;
        double latitud;
        double longitud;
        String auxiliar;
        GeoPosition geoposition = null;
        // Definir un DateTimeFormatter con el formato ISO 8601 sin fracciones de segundo
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
        // Cadena de fecha y hora
        String cadenaFechaHora;
        LocalDateTime fechaHora;

        linea = br.readLine();//#      "startLocation": {
        if (linea.contains("startLocation")) {

            linea = br.readLine();//#        "latitudeE7": -333012667,
            auxiliar = linea.substring(22, 25) + "."
                    + linea.substring(25, 32);//-33.3012667
            latitud = Double.parseDouble(auxiliar);

            linea = br.readLine();//#        "longitudeE7": -663322000,
            i = linea.length() - 8;
            auxiliar = linea.substring(23, i) + "."
                    + linea.substring(i, i + 7);//-66.3322000
            longitud = Double.parseDouble(auxiliar);
            geoposition = new GeoPosition(latitud, longitud);
            activitysegment.setStartLocation(geoposition);
        }

        while (!linea.contains("endLocation")) {

            linea = br.readLine();
        }

        if (linea.contains("endLocation")) {

            linea = br.readLine();
            auxiliar = linea.substring(22, 25) + "."
                    + linea.substring(25, 32);
            latitud = Double.parseDouble(auxiliar);

            linea = br.readLine();
            i = linea.length() - 8;
            auxiliar = linea.substring(23, i) + "."
                    + linea.substring(i, i + 7);
            longitud = Double.parseDouble(auxiliar);
            geoposition = new GeoPosition(latitud, longitud);
            activitysegment.setEndLocation(geoposition);
        }

        while (!linea.contains("duration")) {

            linea = br.readLine();
        }
        if (linea.contains("duration")) {
            
            linea = br.readLine();//        "startTimestamp": "2022-04-20T23:06:55.717Z",
            cadenaFechaHora = linea.substring(27, 46) +"Z";//2022-04-20T23:06:55Z
            // Analizar la cadena de fecha y hora y crear un objeto LocalDateTime
            fechaHora = LocalDateTime.parse(cadenaFechaHora, formatter);
            fechaHora = fechaHora.minusHours(3);//2022-04-20T20:06:55Z
            activitysegment.setStartTimestamp(fechaHora);
            linea = br.readLine();
            cadenaFechaHora = linea.substring(25, 44) +"Z";//2022-04-20T23:06:55Z
            // Analizar la cadena de fecha y hora y crear un objeto LocalDateTime
            fechaHora = LocalDateTime.parse(cadenaFechaHora, formatter);
            fechaHora = fechaHora.minusHours(3);//2022-04-20T20:06:55Z
            activitysegment.setEndTimestamp(fechaHora);
            
        }

        while (!linea.contains("distance")) {

            linea = br.readLine();//      "distance": 1116,
        }
        
        i = linea.length() - 1;
        auxiliar = linea.substring(18, i);
        distancia = Integer.parseInt(auxiliar);
        activitysegment.setDistance(distancia);
        
        linea = br.readLine();//      "activityType": "WALKING",
        
        while (!linea.contains("Path") && linea.length()>6) {//waypointPath && PlaceVisit

            linea = br.readLine();
        }
        
        
        activitysegment.getPath().add(activitysegment.getStartLocation());
        //System.out.println(activitysegment.getStartLocation());
        if (linea.contains("waypointPath")) {

            linea = br.readLine();//        "waypoints": [{
            linea = br.readLine();//          "latE7": -332981414,
            auxiliar = linea.substring(19, 22) + "."
                    + linea.substring(22, 29);//-33.2981414
            latitud = Double.parseDouble(auxiliar);

            linea = br.readLine();//          "lngE7": -663372650
            i = linea.length() - 7;
            auxiliar = linea.substring(19, i) + "."
                    + linea.substring(i, i + 7);//-66.3372650
            longitud = Double.parseDouble(auxiliar);
            geoposition = new GeoPosition(latitud, longitud);
            
            activitysegment.getPath().add(geoposition);
            //System.out.println(geoposition);

            linea = br.readLine();//        }, {
            while(linea.contains("}, {")) {

                linea = br.readLine();
                auxiliar = linea.substring(19, 22) + "."
                        + linea.substring(22, 29);
                latitud = Double.parseDouble(auxiliar);

                linea = br.readLine();
                auxiliar = linea.substring(19, 22) + "."
                        + linea.substring(22, 29);
                longitud = Double.parseDouble(auxiliar);
                geoposition = new GeoPosition(latitud, longitud);
                activitysegment.getPath().add(geoposition);
                
                linea = br.readLine();//        }, {
            }//        }],
            //System.out.println(geoposition);
            activitysegment.getPath().add(activitysegment.getEndLocation());
            //System.out.println(activitysegment.getEndLocation());
            
            
            while (!linea.contains("distanceMeters") && linea.length()>6) {

                linea = br.readLine();
            }
            
            if(linea.contains("distanceMeters")){
                
                i = linea.length() - 1;
                auxiliar = linea.substring(26, i);
                distancia = Math.round(Float.parseFloat(auxiliar));
                activitysegment.setDistanceMeters(distancia);
                linea = br.readLine();
                activitysegment.setTravelmode(linea.substring(23));
//                System.out.println(linea.substring(23));
            }
        }
        else{
            System.out.println(linea + "#1");
            System.out.println(activitysegment.getDistance());
            while (!linea.contains("distanceMeters") && linea.length()>6) {

                linea = br.readLine();
            }
            if(linea.contains("distanceMeters")){
                System.out.println(linea + "#2");
                i = linea.length() - 1;
                auxiliar = linea.substring(26, i);
                distancia = Math.round(Float.parseFloat(auxiliar));
                activitysegment.setDistanceMeters(distancia);
                linea = br.readLine();
                //activitysegment.setTravelmode(linea.substring(23));
                System.out.println(linea);
            }
            else{
                System.out.println(linea + "#3");
                System.out.println(activitysegment.getDistanceMeters());
            }
        }
        
        repositorio.nuevoActivitySegment(activitysegment);
    }

}
