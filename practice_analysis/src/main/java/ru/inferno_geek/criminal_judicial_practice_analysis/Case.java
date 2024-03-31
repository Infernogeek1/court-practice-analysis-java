package ru.inferno_geek.criminal_judicial_practice_analysis;

import java.util.ArrayList;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter 
@NoArgsConstructor
public class Case {

    private String caseNumber;
    private String assignedCourt;
    private ArrayList<String> chargesAssigned;

    // TODO: replace with enum, probably
    private String caseStatus;                       
    

}
