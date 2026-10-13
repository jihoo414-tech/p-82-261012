package com.back

class WiseSayingRepository {

    var lastId = 0
    val wiseSayings = mutableListOf<WiseSaying>()

    fun save(wiseSaying: WiseSaying): WiseSaying {

//        if(wiseSaying.isNew()) {
//            wiseSaying.id = ++lastId
//            wiseSayings.add(wiseSaying)
//        }
//
//        return wiseSaying

        return wiseSaying
            .takeIf { it.isNew() }
            ?.also {
                wiseSaying.id = ++lastId
                wiseSayings.add(wiseSaying)
            } ?: wiseSaying
    }

    fun findListDesc() = wiseSayings.reversed()

    fun findById(id: Int): WiseSaying? = wiseSayings.firstOrNull { it.id == id }

    fun delete(wiseSaying: WiseSaying) = wiseSayings.remove(wiseSaying)
    fun clear() {
        wiseSayings.clear()
        lastId = 0
    }

}