package searchalgorithms.binarysearch

fun <T : Comparable<T>> ArrayList<T>.binarySearch(value: T, range: IntRange = indices): Int {

    val size = range.last - range.first + 1
    val middle = (range.last + range.first).ushr(1) //prevent stack overflow
    if(size <= 1 && this[middle]!= value) return  -1
    return when{
        this[middle] == value -> middle
        this[middle] > value -> binarySearch(value, range.first until middle)
        else ->  binarySearch(value, middle +1 .. range.last)
    }
}


