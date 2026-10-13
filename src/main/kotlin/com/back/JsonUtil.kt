package com.back

object JsonUtil {
    fun jsonStrToMap(jsonStr: String): Map<String, Any> {
        return jsonStr
            .removeSurrounding("{", "}")
            .split(",")
            .mapNotNull {
                val keyValue = it
                    .split(":", limit = 2)
                    .map(String::trim)

                if (keyValue.size != 2) return@mapNotNull null

                val key = keyValue[0].removeSurrounding("\"")
                val value = if (keyValue[1].startsWith("\"") && keyValue[1].endsWith("\"")) {
                    keyValue[1].removeSurrounding("\"")
                } else {
                    keyValue[1].toInt()
                }

                key to value
            }.toMap()
    }
}