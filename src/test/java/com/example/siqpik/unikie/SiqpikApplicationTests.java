package com.example.siqpik;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;

/*@RunWith(SpringRunner.class)
@SpringBootTest*/
public class SiqpikApplicationTests {

	@Test
	public void contextLoads() {
	}

    public String solution(int[] T) {
        // write your code in Java SE 8
        int yearDays = T.length;
        int daysPerSeason = yearDays / 4;

        List<Integer> winter = new ArrayList<>();
        List<Integer> spring = new ArrayList<>();
        List<Integer> summer = new ArrayList<>();
        List<Integer> autum = new ArrayList<>();

        for (int i = 0; i < yearDays; i++){
            if (i < daysPerSeason){
                winter.add(T[i]);
            } else {
                if (i < daysPerSeason * 2){
                    spring.add(T[i]);
                } else {
                    if (i < daysPerSeason * 3){
                        summer.add(T[i]);
                    } else if (i < daysPerSeason * 4){
                        autum.add(T[i]);
                    }
                }
            }
        }

        HashMap<String, Integer> winterAmplitude = new HashMap<String, Integer>(){{
            put("WINTER", getAmplitude(winter));
        }};
        HashMap<String, Integer> springAmplitude = new HashMap<String, Integer>(){{
            put("SPRING", getAmplitude(spring));
        }};
        HashMap<String, Integer> summerAmplitude = new HashMap<String, Integer>(){{
            put("SUMMER", getAmplitude(summer));
        }};
        HashMap<String, Integer> autumAmplitude = new HashMap<String, Integer>(){{
            put("AUTUMN", getAmplitude(autum));
        }};


        ArrayList<HashMap<String, Integer>> amplitudes = new ArrayList<>();

        amplitudes.stream()
                .max((h1, h2) -> h1.values().toArray()[0] > h2.values().toArray()[0])

        String maxAmplitudeSeason = "WINTER";

        if (springAmplitude > winterAmplitude){
            maxAmplitudeSeason = "SPRING";

            if (summerAmplitude > springAmplitude){
                maxAmplitudeSeason = "SUMMER";

                if (autumAmplitude > springAmplitude){
                    maxAmplitudeSeason = "AUTUMN";
                }
            }
        } else if (summerAmplitude > winterAmplitude){
            maxAmplitudeSeason = "SUMMER";

            if (autumAmplitude > springAmplitude){
                maxAmplitudeSeason = "AUTUMN";
            }
        } else if (autumAmplitude > winterAmplitude){
            return "AUTUMN";
        }

        return maxAmplitudeSeason;
    }

    private int getAmplitude(List<Integer> season) {
        return Math.abs(Collections.min(season) - Collections.max(season));
    }

    @Test
    public void probar(){
	    int [] T = {-3, -14, -5, 7, 8, 42, 8, 3};
        assertEquals("SUMMER", solution(T));

        int [] A = {2, -3, 3, 1, 10, 8, 2, 5, 13, -5, 3, -18};
        assertEquals("AUTUMN", solution(A));
    }
}
