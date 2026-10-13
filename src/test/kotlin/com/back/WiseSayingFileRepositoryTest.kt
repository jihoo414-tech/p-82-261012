package com.back

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeAll
import kotlin.test.Test

class WiseSayingFileRepositoryTest {

    private val wiseSayingRepository = SingletonScope.wiseSayingFileRepository

    companion object {

        @JvmStatic
        @BeforeAll
        fun setMode() {
            AppConfig.setModeToTest()
        }
    }

//    @BeforeEach
//    fun setUp() {
//        wiseSayingRepository.clear()
//    }

    @Test
    fun `save`() {
        val wiseSaying = wiseSayingRepository
            .save(WiseSaying(content = "나의 죽음을 적들에게 알리지 말라.", author = "충무공 이순신"))

        val isExist = wiseSayingRepository
            .tableDirPath
            .toFile()
            .listFiles()
            ?.find { it.name == "${wiseSaying.id}.json" }

        assertThat(isExist).isNotNull
    }

    @Test
    fun `findById`() {
        val wiseSaying = wiseSayingRepository
            .save(WiseSaying("나의 죽음을 적들에게 알리지 말라.", "충무공 이순신"))

        val foundWiseSaying = wiseSayingRepository.findById(wiseSaying.id)

        assertThat(foundWiseSaying).isEqualTo(wiseSaying)
    }

    @Test
    fun `saveLastId, loadLastId`() {
        wiseSayingRepository.saveLastId(10)
        assertThat(wiseSayingRepository.loadLastId()).isEqualTo(10)
    }

    @Test
    fun `delete`() {
        val wiseSaying = wiseSayingRepository
            .save(WiseSaying("나의 죽음을 적들에게 알리지 말라.", "충무공 이순신"))

        wiseSayingRepository.delete(wiseSaying)

        assertThat(wiseSayingRepository.findById(wiseSaying.id)).isNull()
    }

}