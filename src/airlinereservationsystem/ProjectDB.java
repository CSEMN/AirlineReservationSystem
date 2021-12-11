package airlinereservationsystem;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * @author CSEMN(Mahmoud Nasser)
 * @since Dec 10, 2021
 */
public class ProjectDB {
    private final static String PERSON_FILE="person_file.txt";
    private final static String PASSENGER_FILE="passenger_file.txt";
    private final static String FLIGHT_DESC_FILE="flight_description_file.txt";
    private final static String SCHEDULED_FLIGHT_FILE="scheduled_flight_file.txt";
    
    //Runtime storage
    public static ArrayList<Person> person_list=new ArrayList<Person>();
    public static ArrayList<Passenger> passenger_list=new ArrayList<Passenger>();
    public static ArrayList<FlightDescription> flight_desc_list=new ArrayList<FlightDescription>();
    public static ArrayList<ScheduledFlight> scheduled_flight_list=new ArrayList<ScheduledFlight>();
    
    public static void initialize(){
        getAllPersons();
        getAllPassengers();
        getAllFlightDesc();
        getAllSCFlights();
    }
    
    public static void add(Person person) throws IOException{
        // CHECK if person already exsists
        for (Person p: person_list){
            if(p.name.equals(person.name)){
                System.out.println("Can't save this data!");
                System.out.println(person.name+" : Already saved!");
                return;
            }
        }
        person_list.add(person);
        String data=person.name+"<->"+person.address+"\n";
        File file=new File(PERSON_FILE);
        FileWriter writer=new FileWriter(file,true);
        writer.write(data);
        writer.close();
    }
    
    public static void add(Passenger passenger) throws IOException{
        // CHECK if passenger already exsists
        
        for (Passenger p: passenger_list){
            if(p.filght_number==passenger.filght_number && p.name.equals(passenger.name)){
                System.out.println("Can't save this data!");
                System.out.println(passenger.name+" : Already reserved this flight!");
                return;
            }
        }
        passenger_list.add(passenger);
        String data=passenger.filght_number+"<->"+passenger.name+"<->"+passenger.address+"\n";
        File file=new File(PASSENGER_FILE);
        FileWriter writer=new FileWriter(file,true);
        writer.write(data);
        writer.close();
    }
    
    public static void add(FlightDescription flight_desc) throws IOException{
        // CHECK if Descritpion already exsists
        for (FlightDescription flight: flight_desc_list){
            if(flight.arrival_time.equals(flight_desc.arrival_time) &&
                    flight.departure_time.equals(flight_desc.departure_time) &&
                    flight.from.equals(flight_desc.from) &&
                    flight.to.equals(flight_desc.to) &&
                    flight.capacity==flight_desc.capacity ){
                System.out.println("Can't save this data!");
                System.out.println("This Flight discription Already exists!");
                return;
            }
        }
        flight_desc_list.add(flight_desc);
        String data=
                flight_desc.from+"<->"+
                flight_desc.to+"<->"+
                flight_desc.departure_time+"<->"+
                flight_desc.arrival_time+"<->"+
                flight_desc.capacity+"\n";
        File file=new File(FLIGHT_DESC_FILE);
        FileWriter writer=new FileWriter(file,true);
        writer.write(data);
        writer.close();
    }
    
    public static void add(ScheduledFlight sc_flight) throws IOException{
        // CHECK if Flight already exsists
        for (ScheduledFlight flight: scheduled_flight_list){
            if(flight.arrival_time.equals(sc_flight.arrival_time) &&
                    flight.departure_time.equals(sc_flight.departure_time) &&
                    flight.from.equals(sc_flight.from) &&
                    flight.to.equals(sc_flight.to) &&
                    flight.capacity==sc_flight.capacity &&
                    flight.date.equals(sc_flight.date)){
                System.out.println("Can't save this data!");
                System.out.println("This Flight Already scheduled!");
                return;
            }
        }
        scheduled_flight_list.add(sc_flight);
        String data=
                sc_flight.date+"<->"+
                sc_flight.filght_number+"<->"+
                sc_flight.from+"<->"+
                sc_flight.to+"<->"+
                sc_flight.departure_time+"<->"+
                sc_flight.arrival_time+"<->"+
                sc_flight.capacity+"\n";
        File file=new File(SCHEDULED_FLIGHT_FILE);
        FileWriter writer=new FileWriter(file,true);
        writer.write(data);
        writer.close();
    }
    
    private static void getAllPersons(){
        File file=new File(PERSON_FILE);
        try {
            Scanner reader = new Scanner(file);
            String line;
            String[] splited;
            while(reader.hasNext()){
                line=reader.nextLine();
                splited = line.split("<->"); 
                person_list.add(new Person(splited[0],splited[1]));
            }
            
        } catch (FileNotFoundException ex) {
            System.out.println("Person File Not Found !");
        }
        
    }
    
    private static void getAllPassengers(){
        File file=new File(PASSENGER_FILE);
        try {
            Scanner reader = new Scanner(file);
            String line;
            String[] splited;
            while(reader.hasNext()){
                line=reader.nextLine();
                splited = line.split("<->"); 
                passenger_list.add(new Passenger(splited[1],splited[2],Integer.parseInt(splited[0])));
                
            }
            
        } catch (FileNotFoundException ex) {
            System.out.println("Passenger File Not Found !");
        }
        
    }
    
    private static void getAllFlightDesc(){
        File file=new File(FLIGHT_DESC_FILE);
        try {
            Scanner reader = new Scanner(file);
            String line;
            String[] splited;
            while(reader.hasNext()){
                line=reader.nextLine();
                splited = line.split("<->"); 
                flight_desc_list.add(new FlightDescription(splited[0], splited[1], splited[2], splited[3], Integer.parseInt(splited[4])));
                
            }
            
        } catch (FileNotFoundException ex) {
            System.out.println("Flight Decsription File Not Found !");
        }
        
    }
    
    private static void getAllSCFlights(){
        File file=new File(SCHEDULED_FLIGHT_FILE);
        try {
            Scanner reader = new Scanner(file);
            String line;
            String[] splited;
            while(reader.hasNext()){
                line=reader.nextLine();
                splited = line.split("<->"); 
                FlightDescription fd=new FlightDescription(splited[2], splited[3], splited[4], splited[5], Integer.parseInt(splited[6]));
                scheduled_flight_list.add(new ScheduledFlight(fd, Integer.parseInt(splited[1]), splited[0]));
                
            }
            
        } catch (FileNotFoundException ex) {
            System.out.println("Scheduled flights File Not Found !");
        }
        
    }

    public static void updatePersonFile() throws IOException{
        File file=new File(PERSON_FILE);
        try (FileWriter writer = new FileWriter(file)) {
            String data;
            for (Person p : person_list){
                data=p.name+"<->"+p.address+"\n";
                writer.write(data);
            }
        }
    }
    
    public static void updatePassengerFile() throws IOException{
        File file=new File(PASSENGER_FILE);
        try (FileWriter writer = new FileWriter(file)) {
            String data;
            for (Passenger p : passenger_list){
                data=p.filght_number+"<->"+p.name+"<->"+p.address+"\n";
                writer.write(data);
            }
        }
    }
    
    public static void updateFlightDescFile() throws IOException{
        File file=new File(FLIGHT_DESC_FILE);
        try (FileWriter writer = new FileWriter(file)) {
            String data;
            for (FlightDescription flight_desc : flight_desc_list){
                data=
                flight_desc.from+"<->"+
                flight_desc.to+"<->"+
                flight_desc.departure_time+"<->"+
                flight_desc.arrival_time+"<->"+
                flight_desc.capacity+"\n";
                writer.write(data);
            }
        }
    }
    
    public static void updateSCFlightFile() throws IOException{
        File file=new File(SCHEDULED_FLIGHT_FILE);
        try (FileWriter writer = new FileWriter(file)) {
            String data;
            for (ScheduledFlight sc_flight : scheduled_flight_list){
                data=
                sc_flight.date+"<->"+
                sc_flight.filght_number+"<->"+
                sc_flight.from+"<->"+
                sc_flight.to+"<->"+
                sc_flight.departure_time+"<->"+
                sc_flight.arrival_time+"<->"+
                sc_flight.capacity+"\n";
                writer.write(data);
            }
        }
    }
    
    
}
