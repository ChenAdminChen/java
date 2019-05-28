package com.chen.annotation.annotationdemo;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class FindParent {

    String[] node = {"company", "user", "role", "sub-role", "department", "sub-department", "channel",
            "sub-channel", "sub-sub-department", "device", "sub-device", "sub-sub-device", "sub-company",
            "specialist", "sub-specialist", "task-defined", "sub-task-defined"};

    Map<String, String> map = new HashMap<>();

    @Test
    public void findParent() {
        Random random = new Random();

        for (int i = 0; i < 10; i++) {

            Integer index = random.nextInt((node.length - 1 - 0) + 1) + 0;

            String name = node[index];

            if (map.containsKey(name)) {
                Integer index1 = random.nextInt((node.length - 1 - 0) + 1) + 0;
                if (index.equals(index1)) {
                    if (index1 == 0) {
                        ++index1;
                    } else if (index1 == node.length - 1) {
                        --index1;
                    }

                    if (!node[index1].equals(map.get(name)) && !node[index1].equals(name))
                        map.put(node[index1], name);

                }

            } else {


                map.put(node[index], null);
            }
        }

        map.forEach((key, it) -> {
            System.out.println("key :" + key + "  ,it:" + it);
        });

    }

}
