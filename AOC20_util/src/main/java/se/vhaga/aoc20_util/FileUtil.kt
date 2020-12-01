package se.vhaga.aoc20_util

import java.io.File
import java.nio.file.Paths

object FileUtil {
    fun readFile(assetsDir: String, fileName: String): File {
        val fileDir = Paths.get("").toAbsolutePath().toString() + assetsDir
        return File(fileDir, fileName)
    }
}
