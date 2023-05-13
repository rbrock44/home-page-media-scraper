import java.io.File

val sources = listOf(
    """\\10.0.0.50\usbc\TV Shows""",
    """\\10.0.0.50\usbc\Movies"""
)

const val outputFile = """C:\workspace\home-page-media-file\media.txt"""

fun main() {
    val lines = saveFilenames()

    println("Success!")
    println("Media files found: " + lines.size)
}

fun saveFilenames(): List<String> {
    val lines = getFiles(sources)
    if (lines.isNotEmpty())
        writeToFile(lines, outputFile)

    return lines
}

fun getFiles(directories: List<String>): List<String> {
    val fileList = mutableListOf<String>()
    val dirList = mutableListOf<String>()
    dirList.addAll(directories)
    while (dirList.isNotEmpty()) {
        val directory = dirList[0]
        val files = File(directory).list { dir, name -> !File(dir, name).isDirectory}
        files?.forEach {
            if (!it.endsWith(".nfo") && !it.endsWith(".srt") && !it.endsWith(".sub")) {
                fileList.add(it)
            }
        }
        val newDirectories = File(directory).list { dir, name -> File(dir, name).isDirectory}
        newDirectories?.forEach {
            dirList.add("$directory/$it")
        }
        dirList.removeAt(0)
    }
    return fileList
}

fun writeToFile(outputList: List<String>, path: String) {
    val file = File(path)
    file.createNewFile()
    file.writeText(outputList.joinToString(separator = "\n"))
}
