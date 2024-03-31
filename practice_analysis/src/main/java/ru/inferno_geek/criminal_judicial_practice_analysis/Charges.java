package ru.inferno_geek.criminal_judicial_practice_analysis;

import java.util.Comparator;

import lombok.Getter;

public enum Charges {
    ARTICLE272PART1(1),
    ARTICLE272PART2(2),
    ARTICLE272PART3(3),
    ARTICLE272PART4(4),

    ARTICLE273PART1(6),
    ARTICLE273PART2(7),
    ARTICLE273PART3(8),

    ARTICLE274PART1(9),
    ARTICLE274PART2(10),

    ARTICLE274DOT1PART1(11),
    ARTICLE274DOT1PART2(12),
    ARTICLE274DOT1PART3(13),
    ARTICLE274DOT1PART4(14),
    ARTICLE274DOT1PART5(15),

    ARTICLE274DOT2PART1(16),
    ARTICLE274DOT2PART2(17);
    
    @Getter private final int order;

    Charges(int ordering) {
        order = ordering;
    }
    
    public static Comparator<Charges> chargesComparator = new Comparator<Charges>() {
        public int compare(Charges c1, Charges c2) {
            return c1.getOrder() - c2.getOrder();
        }
    };
}
