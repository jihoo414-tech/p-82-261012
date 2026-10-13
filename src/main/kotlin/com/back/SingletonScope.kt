package com.back

object SingletonScope {

    val wiseSayingMemRepository by lazy { WiseSayingMemRepository() }
    val wiseSayingFileRepository by lazy { WiseSayingFileRepository() }
    val wiseSayingService by lazy { WiseSayingService(wiseSayingMemRepository) }
    val wiseSayingController by lazy { WiseSayingController(wiseSayingService) }
    val systemController by lazy { SystemController() }

}