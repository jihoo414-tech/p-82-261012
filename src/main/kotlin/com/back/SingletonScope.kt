package com.back

object SingletonScope {

    val wiseSayingRepository by lazy { WiseSayingRepository() }
    val wiseSayingService by lazy { WiseSayingService(wiseSayingRepository) }
    val wiseSayingController by lazy { WiseSayingController(wiseSayingService) }
    val systemController by lazy { SystemController() }

}