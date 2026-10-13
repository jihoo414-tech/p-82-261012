package com.back

class WiseSayingMemRepository: WiseSayingRepository {

    var lastId = 0
    val wiseSayings = mutableListOf<WiseSaying>()

    override fun save(wiseSaying: WiseSaying): WiseSaying {
        return wiseSaying
            .takeIf { it.isNew() }
            ?.also {
                wiseSaying.id = ++lastId
                wiseSayings.add(wiseSaying)
            } ?: wiseSaying
    }

    override fun findListDesc() = wiseSayings.reversed()

    override fun findById(id: Int): WiseSaying? = wiseSayings.firstOrNull { it.id == id }

    override fun delete(wiseSaying: WiseSaying) = wiseSayings.remove(wiseSaying)
    override fun clear() {
        wiseSayings.clear()
        lastId = 0
    }

}