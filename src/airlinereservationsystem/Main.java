package airlinereservationsystem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author CSEMN(Mahmoud Nasser)
 */
public class Main {

    public static void main(String[] args) {
        ProjectDB.initialize(); // Read all saved data
        print_header();
        main_menu();
        print_footer();
    }

    private static void print_header() {
        for (short i = 0; i < 49; i++) {
            System.out.print("-");
        }
        System.out.println("\n|\tEgypt Airline Reservation System\t|");
        for (short i = 0; i < 49; i++) {
            System.out.print("-");
        }
        System.out.print("\n\n");
    }

    private static void print_footer() {
        for (short i = 0; i < 41; i++) {
            System.out.print("-");
        }
        System.out.println("\n|\tCoded By: Mahmoud Nasser\t|");
        System.out.println("|\tClass   : CSE-54\t\t|");
        System.out.println("|\tSection : 4\t\t\t|");
        for (short i = 0; i < 41; i++) {
            System.out.print("-");
        }
        System.out.print("\n\n");
    }

    private static void main_menu() {
        System.out.println("=> Main Menu <=");
        System.out.println("1- Passengers Menu");
        System.out.println("2- Flight Management Menu");
        System.out.println("3- Exit System");
        System.out.println("---------------");
        short choice;
        Scanner input = new Scanner(System.in);
        do {
            System.out.print("Choice: ");
            choice = input.nextShort();
            switch (choice) {
                case 1:
                    System.out.println();
                    passengers_menu();
                    break;
                case 2:
                    System.out.println();
                    flights_menu();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("ERROR: Choice not valid");
            }
        } while (choice < 1 || choice > 3);
    }

    private static void passengers_menu() {
        System.out.println("=> Passengers Menu <=");
        System.out.println("1- Add Customer");
        System.out.println("2- View All Customers");
        System.out.println("3- Remove Customer");
        System.out.println("4- New Reservation");
        System.out.println("5- view All Reservations");
        System.out.println("6- Cancle Reservation");
        System.out.println("7- Main Menu");
        System.out.println("---------------");
        short choice;
        int index;
        Scanner input = new Scanner(System.in);
        do {
            System.out.print("Choice: ");
            choice = input.nextShort();
            switch (choice) {
                case 1:
                    System.out.println("=> NEW CUSTOMERS <=");
                    input = new Scanner(System.in); // refresh scanner to avoid errors
                    System.out.print("Full Name: ");
                    String name = input.nextLine();
                    System.out.print("Address: ");
                    String address = input.nextLine();
                     {
                        try {
                            ProjectDB.add(new Person(name, address));
                        } catch (IOException ex) {
                            System.out.println("ERROR: File not FOund!");
                        }
                    }
                    System.out.println("Added successfully : " + name + "\n");
                    passengers_menu();
                    break;
                case 2:
                    System.out.println("=> CUSTOMERS TABLE <=");
                    Person.show_all();
                    passengers_menu();
                    break;
                case 3:
                    System.out.println("=> CUSTOMERS TABLE <=");
                    Person.show_all();
                    do {
                        System.out.print("Customer Index to remove : ");
                        index = input.nextInt();
                    } while (index < 1 || index > ProjectDB.person_list.size());
                    ProjectDB.person_list.remove(ProjectDB.person_list.get(index - 1));
                     {
                        try {
                            ProjectDB.updatePersonFile();
                        } catch (IOException ex) {
                            System.out.println("ERROR: File not FOund!");
                        }
                    }
                    System.out.println("Removed Successfully!\n");
                    passengers_menu();
                    break;
                case 4:
                    System.out.println("=> NEW RESERVATION <=");
                    //Choose person
                    Person.show_all();
                    do {
                        System.out.print("Customer Index : ");
                        index = input.nextInt();
                    } while (index < 1 || index > ProjectDB.person_list.size());
                    Person p = ProjectDB.person_list.get(index - 1);
                    //Choose flight
                    ScheduledFlight.show_all();
                    boolean allGood;
                    ScheduledFlight scf;
                    do {
                        do {
                            System.out.print("Flight Index : ");
                            index = input.nextInt();
                        } while (index < 1 || index > ProjectDB.scheduled_flight_list.size());
                        scf = ProjectDB.scheduled_flight_list.get(index - 1);
                        allGood = true;
                        if (scf.capacity == Passenger.getSCFlightPassengersCount(scf.filght_number)) {
                            System.out.println("This flight is at maximum capcity,pick another.");
                            allGood = false;
                        }

                    } while (!allGood);
                    int prevLen = ProjectDB.passenger_list.size();
                     {
                        try {
                            ProjectDB.add(new Passenger(p, scf.filght_number));
                        } catch (IOException ex) {
                            System.out.println("ERROR : FILE NOT FOUND !");
                        }
                    }
                    int afterLen = ProjectDB.passenger_list.size();
                    if (prevLen != afterLen) {
                        System.out.println("Reservation completed : " + p.name + " (" + scf.from + " -> " + scf.to + ")\n");
                    }
                    passengers_menu();
                    break;
                case 5:
                    System.out.println("=> RESERVATIONS TABLE <=");
                    Passenger.show_all();
                    passengers_menu();
                    break;
                case 6:
                    System.out.println("=> RESERVATIONS TABLE <=");
                    Passenger.show_all();
                    do {
                        System.out.print("Passenger Index to Cancle trip for : ");
                        index = input.nextInt();
                    } while (index < 1 || index > ProjectDB.passenger_list.size());
                    ProjectDB.passenger_list.remove(ProjectDB.passenger_list.get(index - 1));
                     {
                        try {
                            ProjectDB.updatePassengerFile();
                        } catch (IOException ex) {
                            System.out.println("ERROR: File not FOund!");
                        }
                    }
                    System.out.println("Reservation Canceled Successfully!\n");
                    passengers_menu();
                    break;
                case 7:
                    System.out.println();
                    main_menu();
                    break;
                default:
                    System.out.println("ERROR: Choice not valid");
            }
        } while (choice < 1 || choice > 7);

    }

    private static void flights_menu() {
        System.out.println("=> Flight Management Menu <=");
        System.out.println("1- Add New Flight Description");
        System.out.println("2- View All Flight Description");
        System.out.println("3- Remove Flight Description");
        System.out.println("4- Schedule New Flight");
        System.out.println("5- view All Scheduled Flights");
        System.out.println("6- Cancle Scheduled Flight");
        System.out.println("7- View Scheduled Flight Passengers");
        System.out.println("8- Main Menu");
        System.out.println("---------------");
        short choice;
        int index;
        Scanner input = new Scanner(System.in);
        do {
            System.out.print("Choice: ");
            choice = input.nextShort();
            switch (choice) {
                case 1:
                    System.out.println("=> NEW FLIGHT DESCRIPTION <=");
                    input = new Scanner(System.in); // refresh scanner to avoid errors
                    System.out.print("From : ");
                    String from = input.nextLine();
                    System.out.print("To   : ");
                    String to = input.nextLine();
                    String depTime,
                     arrTime;
                    do {
                        System.out.print("Departure time (HH:MM): ");
                        depTime = input.nextLine();
                    } while (!FlightDescription.check_time(depTime));
                    do {
                        System.out.print("Arrival   time (HH:MM): ");
                        arrTime = input.nextLine();
                    } while (!FlightDescription.check_time(arrTime));

                    System.out.print("Capacity : ");
                    input = new Scanner(System.in);
                    int cap = input.nextInt();
                    int prevSize = ProjectDB.flight_desc_list.size();
                     {
                        try {
                            ProjectDB.add(new FlightDescription(from, to, depTime, arrTime, cap));
                        } catch (IOException ex) {
                            System.out.println("ERROR: File not FOund!");
                        }
                    }
                    int afterSize = ProjectDB.flight_desc_list.size();
                    if (prevSize != afterSize) {
                        System.out.println("Flight Description added successfully : " + from + " -> " + to + "\n");
                    }
                    flights_menu();
                    break;
                case 2:
                    System.out.println("=> FLIGHT DESCRIPTION TABLE <=");
                    FlightDescription.show_all();
                    flights_menu();
                    break;
                case 3:
                    System.out.println("=> FLIGHT DESCRIPTION TABLE <=");
                    FlightDescription.show_all();
                    do {
                        System.out.print("Flight description index to remove : ");
                        index = input.nextInt();
                    } while (index < 1 || index > ProjectDB.flight_desc_list.size());
                    ProjectDB.flight_desc_list.remove(ProjectDB.flight_desc_list.get(index - 1));
                     {
                        try {
                            ProjectDB.updateFlightDescFile();
                        } catch (IOException ex) {
                            System.out.println("ERROR: File not FOund!");
                        }
                    }
                    System.out.println("Flight description removed Successfully!\n");
                    flights_menu();
                    break;
                case 4:
                    System.out.println("=> FLIGHT DESCRIPTION TABLE <=");
                    FlightDescription.show_all();

                    do {
                        System.out.print("Flight description index to schedule : ");
                        index = input.nextInt();
                    } while (index < 1 || index > ProjectDB.flight_desc_list.size());
                    FlightDescription fd = ProjectDB.flight_desc_list.get(index - 1);
                    input = new Scanner(System.in); // refresh scanner to avoid errors
                    String date;
                    do {
                        System.out.print("Date (YYYY/MM/DD) : ");
                        date = input.nextLine();
                    } while (!ScheduledFlight.check_date_format(date));
                    int prevLen = ProjectDB.scheduled_flight_list.size();
                     {
                        try {
                            ProjectDB.add(new ScheduledFlight(fd, date));
                        } catch (IOException ex) {
                            System.out.println("ERROR : FILE NOT FOUND !");
                        }
                    }
                    int afterLen = ProjectDB.scheduled_flight_list.size();
                    if (prevLen != afterLen) {
                        System.out.println("Scheduled " + date + " for flight : " + fd.from + " -> " + fd.to + "\n");
                    }
                    flights_menu();
                    break;
                case 5:
                    System.out.println("=> SCHEDULED FLIGHTS TABLE <=");
                    ScheduledFlight.show_all();
                    flights_menu();
                    break;
                case 6:
                    System.out.println("=> SCHEDULED FLIGHT TABLE <=");
                    ScheduledFlight.show_all();
                    do {
                        System.out.print("Scheduled Flight index to canceled : ");
                        index = input.nextInt();
                    } while (index < 1 || index > ProjectDB.scheduled_flight_list.size());
                    int f_num = ProjectDB.scheduled_flight_list.get(index - 1).filght_number;
                    ProjectDB.scheduled_flight_list.remove(ProjectDB.scheduled_flight_list.get(index - 1));
                     {
                        try {
                            ProjectDB.updateSCFlightFile();
                            // Also remove all reservations for this flight
                            ArrayList<Passenger> remList=new ArrayList();
                            for (Passenger p : ProjectDB.passenger_list) {
                                if (p.filght_number == f_num) {
                                    remList.add(p);
                                }
                            }
                            for (Passenger p : remList) {
                                ProjectDB.passenger_list.remove(p);
                            }
                            ProjectDB.updatePassengerFile();
                            
                        } catch (IOException ex) {
                            System.out.println("ERROR: File not FOund!");
                        }
                    }
                    System.out.println("Scheduled Flight & Reservations canceled Successfully!\n");
                    flights_menu();
                    break;
                case 7:
                    System.out.println("=> SCHEDULED FLIGHT TABLE <=");
                    ScheduledFlight.show_all();
                    do {
                        System.out.print("Flight Index : ");
                        index = input.nextInt();
                    } while (index < 1 || index > ProjectDB.scheduled_flight_list.size());
                    int flight_num = ProjectDB.scheduled_flight_list.get(index - 1).filght_number;
                    Passenger.show_only_flight_no(flight_num);
                    flights_menu();
                    break;
                case 8:
                    System.out.println();
                    main_menu();
                    break;
                default:
                    System.out.println("ERROR: Choice not valid");
            }
        } while (choice < 1 || choice > 8);
    }
}
