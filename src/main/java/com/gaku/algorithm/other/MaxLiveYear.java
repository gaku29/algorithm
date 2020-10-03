package com.gaku.algorithm.other;

import java.util.Arrays;

public class MaxLiveYear {

    public static void main(String[] args) {
        MaxLiveYear instance = new MaxLiveYear();


        int[] birth = {1963,1972,1970,1961,1976,1973,1957,1960,1921,1967,1938,1950,1969,1932,1901,1977,1905,1958,1929,1956,1926,1942,1980,1918,1906,1914,1945,1901,1969,1906,1939,1901,1939,1975,1906,1910,1915,1982,1908,1988,1937,1943,1903,1982,1923,1996,1964,1973,1946,1937,1948,1959,1909,1929,1905,1930,1928,1931,1948,1926,1919,1938,1946,1900,1987,1939,1921,1918,1903,1955,1943,1940,1914,1974,1937,1935,1901,1939,1911,1914,1928,1977,1982,1933,1947,1906,1913,1965,1920,1916};
        int[] death = {1965,1977,1984,1964,1987,1994,1998,1977,1934,1986,1972,1997,1977,1933,1939,1996,2000,1958,1945,1965,1989,1960,1989,1949,1955,1982,2000,1932,1996,1994,1948,1941,1943,2000,1926,1991,1942,1985,1916,1999,1952,1977,1929,1989,1988,1998,1996,1995,1982,1938,1953,1967,1925,1952,1977,1979,1980,1991,1950,1979,1992,1995,1992,1991,1990,1956,1963,1986,1947,1970,1965,1962,1977,1982,1970,1984,1954,1996,1981,1962,1964,1995,1995,1955,2000,1929,1976,1965,1965,1937};


        System.out.println(instance.maxAliveYear(birth, death));
    }


    public int maxAliveYear(int[] birth, int[] death) {

        Arrays.sort(birth);
        Arrays.sort(death);




        int maxYear = 0;
        int maxLive = 0, curLive = 0;
        int i = 0, j = 0;

        while(i < birth.length && j < death.length){

            if (birth[i] < death[j]){
                curLive++;
                if (curLive > maxLive){
                    maxLive = curLive;
                    maxYear = birth[i];
                    System.out.println("maxLive=" + maxLive + ", " + "maxYear=" + maxYear);
                }
                i++;
            }else if (birth[i] > death[j]){
                curLive--;
                j++;
            }else{

                int m = i;
                while(m < birth.length){
                    if (birth[m] == birth[i]){
                        m++;
                    }else{
                        break;
                    }
                }
                curLive += (m - i);
                if (curLive > maxLive){
                    maxLive = curLive;
                    maxYear = birth[i];
                    System.out.println("maxLive=" + maxLive + ", " + "maxYear=" + maxYear);
                }
                i = m;

                int n = j;
                while (n < death.length){
                    if (death[n] == death[j]){
                        n++;
                    }else{
                        break;
                    }
                }
                curLive -= (n - j);
                j = n;




            }

        }

        return maxYear;

    }
}
