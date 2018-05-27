
fun main(args: Array<String>) {
    val cnt = readLine()!!.toInt();

    if (cnt == 0) {
        println("0");
    }
    else {
        println(fibn(cnt));
    }
}

fun fibn(n:Int):Long
{
    var a:Long = 2; //1
    var b:Long = 2; //2

    if (n < 3) {
        return 2;
    }

    for (i in 3..n)  {
        val c = a + b;
        a = b;
        b = c;
    }

    return b;
}

