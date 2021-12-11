/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airlinereservationsystem;

import java.util.Collection;
import java.util.Collections;

/**
 * @author CSEMN
 */
public class Person {
    public String name;
    public String  address;

    public Person(String name, String address) {
        this.name = name;
        this.address = address;
    }
    
    public static void show_all(){
        //sort alphabitically 
        Collections.sort(ProjectDB.person_list, (p1,p2)->p1.name.compareTo(p2.name));
        int counter=0;
        for (int i=0;i<93;i++)
                System.out.print("-");
        System.out.println();
        System.out.printf("%5s | %-30s | %-50s |\n","Index","Full Name","Address");
        for (int i=0;i<93;i++)
            if (i==6||i==39||i==92)
                System.out.print("|");
            else
                System.out.print("-");
        System.out.println();
        
        if (ProjectDB.person_list.isEmpty()){
            System.out.println("\t==> No Customers added yet <==");
        }
        for (Person p :ProjectDB.person_list){
            System.out.printf("%5d | %-30s | %-50s |\n",++counter,p.name,p.address);
        }
        for (int i=0;i<93;i++)
                System.out.print("-");
        System.out.println();
    }
    
}
