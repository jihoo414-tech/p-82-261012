package com.back

class WiseSayingService {

    var lastId = 0
    val wiseSayings = mutableListOf<WiseSaying>()

    fun add(content: String, author: String): WiseSaying {
        val id = ++lastId

        return WiseSaying(id, content, author).also {
            wiseSayings.add(it)
        }

    }

    fun findListDesc(): List<WiseSaying> = this.wiseSayings.reversed()
    fun findById(id: Int): WiseSaying? = wiseSayings.firstOrNull() {it.id == id}
    fun delete(wiseSaying: WiseSaying) = this.wiseSayings.remove(wiseSaying)
}