package com.exam.selfmap;

public class SelfMapTest {

	public static void main(String[] args) {
        // Creating the Map 
		SelfMap<String, String> map = new SelfMap<>(); 

        // Inserting elements 
        map.put("aa", "Geeks"); 

        map.put("AA", "forGeeks"); 

        map.put("Aa", "A"); 

        map.put("aA", "Computer"); 
        
        map.put("aaa", "Geeks"); 

        map.put("AAa", "forGeeks"); 

        map.put("Aaa", "A"); 

        map.put("aAa", "Computer");
        
        map.put("aaaa", "Geeks"); 

        map.put("AAaa", "forGeeks"); 

        map.put("Aaaa", "A"); 

        map.put("aAaa", "Computer");
        
        System.out.println(map.remove("Aa"));
        map.printMap();
	}

}
