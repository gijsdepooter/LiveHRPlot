package org.vaadin.example;

public class Main {
    public static void main(String[] args){
        try {
            Patient p = GET.makeGETrequest("HR");
            p.PrintPatient();

        }catch (Exception e){System.out.println(e.getMessage());}
    }
}
