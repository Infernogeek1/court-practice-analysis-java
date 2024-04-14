package ru.inferno_geek.criminal_judicial_practice_analysis;

import java.util.EnumSet;
import java.util.TreeSet;

import lombok.Data;

@Data
public class Case {
    private String caseNumber;
    private String assignedCourt;
    // Yes, I have done an enum with custom Comparator;
    // TODO: Are there any better ways?
    private TreeSet<Charges> assignedCharges = new TreeSet<Charges>();
    private Status caseStatus;
    
    
    public Case(String number, String court, Status status) {
        caseNumber = number;
        assignedCourt = court;
        caseStatus = status;        
    } 
    
}
