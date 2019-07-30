import org.apache.commons.io.FileUtils
import java.io.File
import java.lang.IllegalStateException
import kotlin.random.Random

class RanMusic(
        private val srcDir: String?,
        private val dstDir: String?,
        private var sum: Int,
        private val types: Array<String>?
) {

    fun start() {
        println("looking for files...")

        val srcFile = File(srcDir)
        if (!srcFile.exists()) {
            println("Something wrong with src directory")
            return
        }

        val tempMusicList = FileUtils.listFiles(srcFile, types ?: arrayOf("mp3"), true)
        val musicList = ArrayList<File>()

        for (file in tempMusicList) {
            musicList.add(file)
        }

        musicList.shuffle()
        musicList.shuffle()
        musicList.shuffle()

        println("found ${musicList.size} files")

        val resultMusicList = ArrayList<File>()

        while (sum > 0 && musicList.size > 0) {
            resultMusicList.add(musicList.removeAt(Random.nextInt(musicList.size)))
            sum--
        }

        val dstDir = File(dstDir)

        if (!dstDir.exists()) {
            val itsOk = dstDir.mkdir()
            if (!itsOk) throw IllegalStateException("something wrong with destination directory")

        } else if (!dstDir.isDirectory) {
            throw IllegalStateException("something wrong with destination directory")

        }

        println("Start copying...")

        for (musicFile in resultMusicList) {
            FileUtils.copyFileToDirectory(musicFile, dstDir)
            print(".")
        }

        println("Done!")



    }
}