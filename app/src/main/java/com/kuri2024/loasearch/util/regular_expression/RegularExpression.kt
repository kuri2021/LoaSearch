package com.kuri2024.loasearch.util.regular_expression

class RegularExpression {
    fun idCheck(str:String): MatchResult?{
        val reg = Regex("^[a-zA-Z0-9]{7,15}\$")
        return reg.matchEntire(str)
    }

    fun pwCheck(str: String): MatchResult?{
        val reg = Regex("^[a-zA-Z0-9]{7,15}\$")
        return reg.matchEntire(str)
    }
}