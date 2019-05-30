package com.chen.annotation.annotationdemo;


import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class FindParent {

    String[] node = {"Emma", "Olivia", "Latin", "Ava", "Isabella", "Sophia", "Greek", "Mia", "Amelia", "Hebrew",
            "Charlotte", "Norse", "Abigail", "Hebrew1", "Emily", "Harper", "English", "Evelyn", "Celtic",
            "Madison", "Englishq", "Victoria", "Latin1", "Sofia", "Scarlett", "Aria", "Latin2",
            "Elizabeth", "Hebrewd2", "Camila", "French1", "Layla", "Arabic", "Ella", "French", "Chloe", "Greek2", "Zoey",
            "Greek1", "Penelope"};


    Map<String, Parent> maps = new HashMap<>();


    @Before
    public void setup() {

        maps.put(node[0], new Parent(node[0]));

        Random random = new Random();


        for (int i = 1; i < node.length; i++) {
            String name = node[i];

            if (random.nextFloat() <= 0.5) {

                maps.put(name, new Parent(name));

            } else {

                int p1 = random.nextInt(maps.size());

                maps.put(name, new Parent(name, node[p1]));
            }
        }


    }

    @Test
    public void findParentTest() {

        maps.forEach((key, it) -> {

            it.setValue(find(maps, it));
        });


        List<Parent> lists = maps.values().stream().collect(Collectors.toList());

        maps.forEach((key, it) -> {
            if (it.getParent() == null) {
                System.out.println(it.getValue());
                printStr(lists, it);
            }
        });
    }

    //print value
    public void printStr(List<Parent> list, Parent parent) {
        list.stream().filter(r ->
                parent.getName().equals(r.getParent())

        ).forEach(r -> {
            System.out.println(r.getValue());
            printStr(list, r);
        });
    }

    //find parent value
    public String find(Map<String, Parent> map, Parent parent) {
        String pa = parent.getParent();

        String value = "";

        if (pa == null) {
            if (parent.getValue() == null) {
//                parent.setValue(parent.getName());

                value = parent.getName();
            } else {
                value = parent.getValue();
            }
        } else {

            Parent p = map.get(pa);

            if (p.getValue() == null) {
                if (p.getParent() != null) {
                    return find(map, p) + "." + parent.getName();
                } else {
                    value = p.getName() + "." + parent.getName();
                }
            } else {
                value = p.getValue() + "." + parent.getName();
            }

        }
        return value;
    }
}
