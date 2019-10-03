// using for java
@file:JvmName("Math")
@file:JvmMultifileClass

fun maxThreeNumbers(n1: Int, n2: Int, n3: Int): Int {
    return if (n1 > n2 && n1 > n3) n1
    else if (n2 > n3 && n2 > n1) n2
    else n3
}
