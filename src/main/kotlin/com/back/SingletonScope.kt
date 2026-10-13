package com.back

object SingletonScope {

    val wiseSayingRepository by lazy { WiseSayingMemRepository() }
    val wiseSayingService by lazy { WiseSayingService(wiseSayingRepository) }
    val wiseSayingController by lazy { WiseSayingController(wiseSayingService) }
    val systemController by lazy { SystemController() }

}