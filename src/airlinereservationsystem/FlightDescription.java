package airlinereservationsystem;

import java.util.Collections;

/**
 *
 * @author CSEMN
 */
public class FlightDescription {
    public String from;
    public String to;
    public String departure_time;
    public String arrival_time;
    public int    capacity;

    public FlightDescription(String from, String to, String departureTime, String arrivalTime, int capacity) {
        this.from = from;
        this.to = to;
        this.departure_time = departureTime;
        this.arrival_time = arrivalTime;
        this.capacity = capacity;
    }
    public static boolean check_time(String time){
        boolean goodTimeFormat=true;
        if (!(time.length()==4||time.length()==5)){
            goodTimeFormat=false;
        }   
        else{
            String[] splitedTime=time.split(":");
            if(splitedTime.length != 2)
                goodTimeFormat=false;
            else if(splitedTime[1].length()!=2)
                goodTimeFormat=false;
            else if(!((splitedTime[0].compareTo("00")>=0 &&splitedTime[0].compareTo("23")<=0)||
                    (splitedTime[0].compareTo("0")>=0 && splitedTime[0].compareTo("9")<=0)))             
                goodTimeFormat=false;
            else if(splitedTime[1].compareTo("00")<0||splitedTime[1].compareTo("59")>0)
                goodTimeFormat=false; 
        }
        if(goodTimeFormat)
            return true;
        else{
            System.out.println("Bad Time format!");
            return false;
        }
    }
    
    public static void show_all(){
        //sort by Departure city
        Collections.sort(ProjectDB.flight_desc_list, (p1,p2)->p1.departure_time.compareTo(p2.departure_time));
        Collections.sort(ProjectDB.flight_desc_list, (p1,p2)->p1.from.compareTo(p2.from));
        int counter=0;
        for (int i=0;i<90;i++)
                System.out.print("-");
        System.out.println();
        System.out.printf("%5s | %-20s | %-20s | %-10s | %-10s | %-8s |\n","Index","FROM","To","Dep Time","Arr Time","Capacity");
        for (int i=0;i<90;i++)
            if (i==6||i==29||i==52||i==65||i==78||i==89)
                System.out.print("|");
            else
                System.out.print("-");
        System.out.println();
        
        if (ProjectDB.flight_desc_list.isEmpty()){
            System.out.println("\t==> No Flight descriptions added yet <==");
        }
        for (FlightDescription fd :ProjectDB.flight_desc_list){
            System.out.printf("%5d | %-20s | %-20s | %-10s | %-10s | %8d |\n",
                    ++counter,fd.from,fd.to,fd.departure_time,fd.arrival_time,fd.capacity);
        }
        for (int i=0;i<90;i++)
                System.out.print("-");
        System.out.println();
    }
}
