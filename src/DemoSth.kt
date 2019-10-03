import java.util.*
import kotlin.collections.ArrayList
import kotlin.reflect.KFunction
import kotlin.reflect.KFunction1

fun main() {
    //var a: Int = demo("a")
    var list = listOf(1, 2, 3, 4)
    var m = 2.rem(1)
    var intRange = 20..25

    for (i in intRange) print(" $i")
    println(intRange)

    /* this /*  is  */ kotlin*/

    loop1@ for (i in 0 until 10) {
        loop2@ for (j in 0..5) {
            if (j == 4) break@loop2
            print(" $j")
        }
        if (i == 6) break@loop1
        println()
    }
    println()

    println("max: " + maxThreeNumbers(1, 2, 3))

    // anonymous function
    val c: (Int) -> String
    c = ::intToString
    println("var c: " + c(2))

    // call a function which return a function ->
    val d: (Long) -> (Int) -> String
    d = ::longToInt
    println("var d: " + d(3)(5)) //invoke it again to call that function

    val greeting: (Int) -> Unit = { num ->
        println(num)
    }
    greeting(25)

    // use lambda
    val lambda = { num: Int ->
        println("lambda $num")
    }
    lambda(23)

    // not use anonymous
    ham()("1")

    val anonymousVar: (Int) -> String
    anonymousVar = { num -> num.toString() }
    println("annymous Var: ${anonymousVar(69)}")

    // anonymous function
    val producerPrinter = fun() { println("hello 1") }
    val producerPrinter2: (Int) -> Unit = fun(x: Int) { println("hello $x") }

    val producerPrinter3: () -> () -> (Int) -> Unit
    producerPrinter3 = fun() = fun() = fun(x: Int) { println("hi $x") }

    producerPrinter()
    producerPrinter2(2)
    producerPrinter3()()(3)

    val listInt = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13)
    val primeList = listInt.filter12(::isPrime)
    println("top lvFun:$primeList")

    // KFunction
    val age: KFunction<Unit> = ::showAge
    age.call(1997)
    val k2Func: KFunction1<Int, Int> = ::getAge
    k2Func(1998)

    //Extend Function , infix function
    val person1 = Person<Number, A>("Nhat", 22, 69, A())
    person1 eat "Pizza"

    // hierarchy
    val personA: Person<Any, B> = Worker("A", 1, 96, A(),"seller")
    val worker = personA
}

infix fun Person<Any, B>.eat(foodName: String) {
    println("$name eat $foodName")
}

fun getAge(birthYear: Int): Int {
    return Calendar.getInstance().get(Calendar.YEAR) - birthYear
}

fun showAge(birthYear: Int) {
    println("I'm ${Calendar.getInstance().get(Calendar.YEAR) - birthYear} years old!!")
}

fun isPrime(num: Int): Boolean {
    if (num <= 1) return false
    for (i in 2 until num) {
        if (num % i == 0) return false
    }
    return true
}

fun longToInt(long: Long): (Int) -> String {
    return { num: Int -> intToString(num + long.toInt()) }
}

fun ham(): (String) -> Long {
    return { a: String -> a.toLong() }
}

fun intToString(num: Int): String {
    return "$num-string"
}

fun <T> demo(a: String): T {
    return a as T
}

fun sumFourNums(a: Int, b: Int, c: Int, d: Int): Int {
    fun sum(x: Int, y: Int): Int {
        return x + y
    }

    return sum(sum(a, b), sum(c, d))
}

// callable reference
inline fun <T> Iterable<T>.filter12(predicate: (T) -> Boolean): List<T> {
    return filterTo(ArrayList(), predicate)
}

//fun <T> fixType(num: T, string: T) where T : Number, T : Int {
//
//}

/**
    'in' : using for SET value, cannot return. It only can pass ITSELF or its SUPER Class
    'out': using for GET value, cannot set. It only can pass ITSELF or its SUB-Class.
 */
open class Person<out T, in R>(open val name: String, open val age: Int, open val other: T, private var setter: R) {
    fun getOthers(): T {
        return other
    }

    fun setOther(onlySet: R) {
        setter = onlySet
    }
}

class Worker<T, R>(
    override val name: String,
    override val age: Int,
    override val other: T,
    setter: R,
    val department: String
) : Person<T, R>(name, age, other, setter)

open class A
class B : A()
