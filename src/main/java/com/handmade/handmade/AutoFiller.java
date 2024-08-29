package com.handmade.handmade;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AutoFiller {

    public static String extractShortDescription(String text, int sentenceCount) {
        StringBuilder result = new StringBuilder();
        Matcher matcher = Pattern.compile("([^.!?]+[.!?]+)").matcher(text);
        boolean isProcessesGeneredFinish = false;

        for (int count = 0; count < sentenceCount && matcher.find(); ) {
            result.append(matcher.group());
            count++;
            if (count>=sentenceCount){
                isProcessesGeneredFinish=true;
            }
        }

        if (text.isEmpty()) {
            return "";
        }

        if (!isProcessesGeneredFinish) {
            return text;
        }

        return result.toString().trim();
    }
}
