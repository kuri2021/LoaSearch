package com.example.loasearch.util.regex

import java.util.regex.Pattern

class RegexUtil:RegexUtilInterface {

    override fun transcendence(data:String): List<String>{
        val regex = "\\[초월]</FONT> <FONT COLOR='#FFD200'>(.*?)</FONT>"

        val pattern = Pattern.compile(regex)

        val matcher = pattern.matcher(data)

        val matches = mutableListOf<String>()

        while (matcher.find()) {
            matches.add(matcher.group(1) ?: "")
        }

        return matches
    }

    override fun qualityValue(data:String): List<String>{
        val regex = "\"qualityValue\": "

        val pattern = Pattern.compile(regex)

        val matcher = pattern.matcher(data)

        val matches = mutableListOf<String>()

        while (matcher.find()) {
            matches.add(matcher.group(1) ?: "")
        }

        return matches
    }
}