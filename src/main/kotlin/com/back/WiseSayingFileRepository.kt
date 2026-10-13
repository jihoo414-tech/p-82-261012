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
                wiseSaying.id = genNextId()
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
        return tableDirPath.toFile()
            .listFiles()
            ?.filter { it.name.endsWith(".json") }
            ?.map { it.readText() }
            ?.map(WiseSaying.Companion::fromJsonStr)
            .orEmpty()
    }

    override fun findById(id: Int): WiseSaying? {
        return tableDirPath
            .toFile()
            .listFiles()
            ?.find { it.name == "${id}.json" }
            ?.let { WiseSaying.fromJsonStr(it.readText()) }
    }

    override fun delete(wiseSaying: WiseSaying): Boolean {
        return tableDirPath
            .resolve("${wiseSaying.id}.json")
            .toFile()
            .takeIf { it.exists() }
            ?.delete() ?: false
    }

    override fun clear() {
        tableDirPath.toFile().deleteRecursively()
    }

    fun saveLastId(lastId: Int) {
        mkTableDirsIfNotExists()

        tableDirPath.resolve("lastId.txt")
            .toFile()
            .writeText(lastId.toString())
    }

    fun loadLastId(): Int {
        return tableDirPath.resolve("lastId.txt")
                .toFile()
                .readText()
                .toIntOrNull() ?: 0
    }

    fun genNextId(): Int {
        return (loadLastId() + 1).also {
            saveLastId(it)
        }
    }
}