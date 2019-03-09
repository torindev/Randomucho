import java.lang.NumberFormatException

fun main(args: Array<String>) {

    println("enter src dir:")
    val src = readLine()

    println("enter dst dir:")
    val dst = readLine()

    println("enter the number of files:")
    val sumStr = readLine()
    var sum = 0
    try {
        sum = sumStr?.toInt() ?: 0
    } catch (e: NumberFormatException) {
        println("Malformed number")
        return
    }

    if (sum == 0) {
        println("Amount can't be a zero")
        return
    }

    val rm = RanMusic(src, dst, sum, null)
    rm.start()

}