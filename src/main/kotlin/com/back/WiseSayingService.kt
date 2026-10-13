package com.back

class WiseSayingService(
    val wiseSayingRepository: WiseSayingMemRepository = SingletonScope.wiseSayingRepository
) {

    fun add(content: String, author: String): WiseSaying = WiseSaying(content = content, author = author).also {
        wiseSayingRepository.save(it)
    }

    fun findListDesc(): List<WiseSaying> = wiseSayingRepository.findListDesc()
    fun findById(id: Int): WiseSaying? = wiseSayingRepository.findById(id)
    fun delete(wiseSaying: WiseSaying) = wiseSayingRepository.delete(wiseSaying)
}