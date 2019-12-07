package com.example.siqpik;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Tercera {


        int solution(int[] A, int X) {
            int N = A.length;
            if (N == 0) {
                return -1;
            }
            int l = 0;
            int r = N - 1;
            while (l < r && !(A[l] == X)) {
                int m = (l + r) / 2;
                if (A[m] > X) {
                    r = m - 1;
                } else {
                    l = m;
                }
            }
            if (A[l] == X) {
                return l;
            }
            return -1;
        }


    @Test
    public void probar(){
        int [] R = {1, 2, 5, 9, 9};

        assertEquals(3, solution(R, 9));
    }
}