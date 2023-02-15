package com.epam.mjc;

import java.util.*;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source     source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        StringTokenizer tokenizer;
        List<String> list = new ArrayList<>();
        list.add(source);

        for (String d : delimiters) {
            List<String> newList = new ArrayList<>();
            for (String s : list) {
                tokenizer = new StringTokenizer(s, d);
                while (tokenizer.hasMoreTokens()) {
                    newList.add(tokenizer.nextToken());
                }
            }
            list = newList;
        }

        return list;
    }


}
