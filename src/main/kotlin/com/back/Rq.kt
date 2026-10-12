package com.back

class Rq(
    val cmd: String
) {
    val actionName: String

    val paramMap: Map<String, String>

    init{
        val cmdBits = cmd.split("?")
        actionName = cmdBits[0]

        paramMap = if (cmdBits.size == 2) {

            cmdBits[1]
                .split("&")
                .map {
                    val paramBits = it.split("=")
                    paramBits[0] to paramBits[1]
                }
                .toMap()
        } else {
            emptyMap()
        }
    }

    fun getParam(key: String, defaultValue: String): String{
        return paramMap[key] ?: defaultValue
    }

    fun getParamAsInt(key: String, defaultValue: Int): Int{
            return paramMap[key]?.toIntOrNull() ?: defaultValue
    }

}