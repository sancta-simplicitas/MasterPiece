fun main() {
    while (true) {
        readLine()!!.splitToSequence(" ").mapNotNull { it.toDoubleOrNull() }.sum().let {
            if (it % 1 == 0.0) it.toInt() else it
        }.let(::println)
    }
}
