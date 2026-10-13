package com.back

import java.nio.file.Path

class WiseSayingFileRepository: WiseSayingRepository {

    var lastId = 0

    val tableDirPath: Path
        get() {
            return AppConfig.dbDirPath.resolve("wiseSaying")
        }


    override fun save(wiseSaying: WiseSaying): WiseSaying {
        return wiseSaying
            .takeIf { it.isNew() }
            .also {
                wiseSaying.id = ++lastId
                saveOnDisk(wiseSaying)
            } ?: wiseSaying
    }

    private fun saveOnDisk(wiseSaying: WiseSaying) {
        mkTableDirsIfNotExists()

        val wiseSayingFilePath = tableDirPath.resolve("${wiseSaying.id}.json")
        wiseSayingFilePath.toFile().writeText(wiseSaying.jsonStr)
    }

    private fun mkTableDirsIfNotExists() {
        tableDirPath.toFile().run {
            if (!exists()) {
                mkdirs()
            }
        }
    }

    override fun findListDesc(): List<WiseSaying> {
        TODO("Not yet implemented")
    }

    override fun findById(id: Int): WiseSaying? {
        return tableDirPath
            .toFile()
            .listFiles()
            ?.find { it.name == "${id}.json" }
            ?.let { WiseSaying.fromJsonStr(it.readText()) }
    }

    override fun delete(wiseSaying: WiseSaying): Boolean {
        TODO("Not yet implemented")
    }

    override fun clear() {
        TODO("Not yet implemented")
    }

    fun saveLastId(lastId: Int) {
        mkTableDirsIfNotExists()

        tableDirPath.resolve("lastId.txt")
            .toFile()
            .writeText(lastId.toString())
    }

    fun loadLastId(): Int {
        return runCatching {
            tableDirPath.resolve("lastId.txt")
                .toFile()
                .readText()
                .toInt()
        }.getOrElse{ 0 }
    }
}