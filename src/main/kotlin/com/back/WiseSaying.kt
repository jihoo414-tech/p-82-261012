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

}