package util.helper;

import week15.match.RabinKarp;
import week15.match.SubStringMatch;

public class SubStringMatchHelper {

    private SubStringMatchHelper() {
    }

    public static void matchTest(String matchName, String s, String t) {
        int pos = -1;

        long startTime = System.nanoTime();
        switch (matchName) {
            case "bruteforce":
                pos = SubStringMatch.bruteforce(s, t);
                break;
            case "rabinKarp":
                pos = RabinKarp.rabinKarp(s, t);
                break;
        }
        long endTime = System.nanoTime();

        double time = (endTime - startTime) / 1000000000.0;
        if (s.indexOf(t) != pos) {
            throw new RuntimeException(matchName + " failed!");
        }
        System.out.println(String.format("%s, res = %d, time = %f s", matchName, pos, time));
    }

}
