package com.back

interface WiseSayingRepository {
    fun save(wiseSaying: WiseSaying): WiseSaying
    fun findListDesc(): List<WiseSaying>
    fun findById(id: Int): WiseSaying?
    fun delete(wiseSaying: WiseSaying): Boolean
    fun clear()
}