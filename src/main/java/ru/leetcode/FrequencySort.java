package ru.leetcode;


import java.util.*;
import java.util.stream.Collectors;

/**
 *  Given a string, sort it in decreasing order based on the frequency of characters.
 *
 * Example 1:
 *
 * Input:
 * "tree" *
 * Output:
 * "eert" *
 * Explanation:
 * 'e' appears twice while 'r' and 't' both appear once.
 * So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
 *
 * ----------
 * Input:
 * "Aabb" *
 * Output:
 * "bbAa" *
 * Explanation:
 * "bbaA" is also a valid answer, but "Aabb" is incorrect.
 * Note that 'A' and 'a' are treated as two different characters.
 */

public class FrequencySort {
    Map<Character, Integer> chMap = new LinkedHashMap<>();

    public String frequencySort(String s) {
        char[] chArray = s.toCharArray();
        for (char ch : chArray) {
            if (chMap.containsKey(ch)) {
                int i = chMap.get(ch);
                i++;
                chMap.put(ch, i);
            } else {
                chMap.put(ch, 1);
            }
        }

        //В обратном порядке, по убыванию Descending
        Comparator<Map.Entry> comparatorInt = new Comparator<Map.Entry>() {
            @Override
            public int compare(Map.Entry o1, Map.Entry o2) {
                return  ((Integer) o2.getValue()) - ((Integer) o1.getValue());
            }
        };

       /* Comparator<Map.Entry> comparatorStr = new Comparator<Map.Entry>() {
            @Override
            public int compare(Map.Entry o1, Map.Entry o2) {
                return  Character.compare((Character)o1.getKey(), (Character)o2.getKey());
            }
        };*/
        List<Map.Entry> list = new ArrayList<>(chMap.entrySet());
        list.sort(comparatorInt);

        return getString(list);

       /* List<Entry<Character, Integer>> expectedList = chMap.entrySet().stream()
                .sorted(Comparator.comparingInt(e -> -e.getValue().get()))
                .collect(Collectors.toList());*/
    }

    String getString(List<Map.Entry> list) {
        StringBuilder builder = new StringBuilder(list.size());
        for (Map.Entry chEntry: list) {
            int value = (Integer) chEntry.getValue();
            if (value > 1) {
                for (int i = 0; i < value; i++) {
                    builder.append(chEntry.getKey());
                }
            } else {
                builder.append(chEntry.getKey());
            }
        }
        return builder.toString();
    }


    public static void main(String[] args) {
        String res = new FrequencySort().frequencySort("tree");
        System.out.println(res);
    }


}