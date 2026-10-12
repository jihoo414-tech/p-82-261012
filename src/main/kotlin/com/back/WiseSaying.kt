package com.back

data class WiseSaying(
    val id: Int,
    var content: String,
    var author: String
) {
    fun modify(newContent: String, newAuthor: String): WiseSaying {
        this.content = content
        this.author = newAuthor
        return this
    }
}
