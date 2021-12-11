/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airlinereservationsystem;

import java.util.Collections;

/**
 *
 * @author CSEMN
 */
public class ScheduledFlight extends FlightDescription{
    public String date;
    public int filght_number;

    public ScheduledFlight(FlightDescription f_desc,int filght_number,String date) {
        super( f_desc.from,  f_desc.to,  f_desc.departure_time,  f_desc.arrival_time,  f_desc.capacity);
        this.date = date;
        this.filght_number=filght_number;
    }
    public ScheduledFlight(FlightDescription f_desc,String date) {
        super( f_desc.from,  f_desc.to,  f_desc.departure_time,  f_desc.arrival_time,  f_desc.capacity);
        this.date = date;
        this.filght_number=generate_flight_num();
    }
    public static boolean check_date_format(String dateToTest){
        // Good format (YYYY/MM/DD)
        boolean goodDate=true;
        String[] splitedDate= dateToTest.split("/");
        if(splitedDate.length!=3 || dateToTest.length()<8||dateToTest.length()>10){
            goodDate=false;
        }else{
            int month=Integer.parseInt(splitedDate[1]);
            int day=Integer.parseInt(splitedDate[2]);
           if(splitedDate[0].length()!=4) {
            goodDate=false;
           }else if (month<1|| month>12) { 
               //Month check   
            goodDate=false;
           }
           else if(day<0||day>31){
               //Day check
            goodDate=false;
           }
        }
        if(goodDate)
            return true;
        else{
            System.out.println("Bad formated date !");
            return false;
        }     
    }
    
    private static int generate_flight_num(){
        int max=0;
        for (ScheduledFlight scf: ProjectDB.scheduled_flight_list){
            if(max<scf.filght_number)
                max=scf.filght_number;
        }
        return max+1;
    }
    
    public static void show_all(){
        //sort by Flight date
        Collections.sort(ProjectDB.scheduled_flight_list, (p1,p2)->p1.departure_time.compareTo(p2.departure_time));
        Collections.sort(ProjectDB.scheduled_flight_list, (p1,p2)->p1.date.compareTo(p2.date));
        int counter=0;
        for (int i=0;i<113;i++)
                System.out.print("-");
        System.out.println();
        System.out.printf("%5s | %-5s | %-10s | %-20s | %-20s | %-10s | %-10s | %-10s |\n","Index","FN","Date","FROM","To","Dep Time","Arr Time","Passengers");
        for (int i=0;i<113;i++)
            if (i==6||i==14||i==27||i==50||i==73||i==86||i==99||i==112)
                System.out.print("|");
            else
                System.out.print("-");
        System.out.println();
        
        if (ProjectDB.scheduled_flight_list.isEmpty()){
            System.out.println("\t==> No Scheduled flights added yet <==");
        }
        
        for (ScheduledFlight scf :ProjectDB.scheduled_flight_list){
            int psgrnum =Passenger.getSCFlightPassengersCount(scf.filght_number);
            String psgrCnt =(psgrnum==scf.capacity)?"Full("+psgrnum+")":Integer.toString(psgrnum);
            System.out.printf("%5d | %5d | %-10s | %-20s | %-20s | %-10s | %-10s | %10s |\n",
                    ++counter,scf.filght_number,scf.date,scf.from,scf.to,scf.departure_time,scf.arrival_time,psgrCnt);
        }
        for (int i=0;i<113;i++)
                System.out.print("-");
        System.out.println();
    }
    
}
