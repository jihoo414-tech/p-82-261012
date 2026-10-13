package com.back

data class WiseSaying(
    var id: Int,
    var content: String,
    var author: String
) {

    constructor(content: String, author: String) : this(0, content, author)

    fun modify(newContent: String, newAuthor: String): WiseSaying {
        this.content = newContent
        this.author = newAuthor
        return this
    }

    fun isNew(): Boolean {
        return this.id == 0
    }

    val jsonStr: String
        get() {
            return """
                {
                    "id": $id,
                    "content": "$content",
                    "author": "$author"
                }
            """.trimIndent()
        }

    companion object {
        fun fromJsonStr(jsonStr: String): WiseSaying {
            val map = JsonUtil.jsonStrToMap(jsonStr)

            return WiseSaying(
                id = map["id"] as Int,
                content = map["content"] as String,
                author = map["author"] as String,
            )
        }
    }

}