package ru.inferno_geek.criminal_judicial_practice_analysis;

import java.util.TreeSet;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter 
@NoArgsConstructor
public class Case {
    private String caseNumber;
    private String assignedCourt;
    // TODO: ponder if this could be replaced with something else.
    // ArrayList-ing charges would make it annoying to work with.
    // Maybe, do a sorted set of enums? 
    private TreeSet<Charges> assignedCharges = new TreeSet<Charges>();

    // TODO: ponder if enum is useful
    private Status caseStatus;

    public Case(String number, String court, Status status) {
        caseNumber = number;
        assignedCourt = court;
        caseStatus = status;        
    } 
      
    

}
