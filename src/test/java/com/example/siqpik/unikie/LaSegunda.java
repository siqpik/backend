package com.example.siqpik;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class LaSegunda {

    private int /*HashMap<Integer, List<String>>*/ getGrupos(String[] T, String[] R){

        HashMap<String, String> result = new HashMap<>();

        for (int i = 0; i < T.length; i++){

            String groupName = T[i];
            String lastIndex = groupName.substring(groupName.length() - 1);

            String groupNumber = !Character.isDigit(lastIndex.charAt(lastIndex.length() - 1)) ? groupName.substring(groupName.length() - 2, groupName.length() - 1) : lastIndex;

            if (result.containsKey(groupNumber)){
                if (result.get(groupNumber).equals("OK")){
                    result.put(groupNumber, R[i]);
                }

            } else result.put(groupNumber, R[i]);
        }

        List<String> oks = new ArrayList<>();
         result.forEach((key, val) -> {
             if (val.equals("OK"))
                 oks.add(val);
         });

        return (oks.size() * 100) / result.size();
    }

    @Test
    public void probar(){
        String [] T = {
                "test1a",
                "test2",
                "test1b",
                "test1c",
                "test3"};
        String [] R = {
                "Wrong answer",
                "OK",
                "Runtime error",
                "OK",
                "Time limit exceeded"};

        assertEquals(33, getGrupos(T, R));
    }
}