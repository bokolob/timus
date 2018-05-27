
fun main(args: Array<String>) {
    val cnt = readLine()!!.toInt();

    var globalMax = Int.MIN_VALUE;
    var localMax = 0;

    for (i in 0..cnt-1) {
        val next = readLine()!!.toInt();
        localMax = Math.max(next, localMax+next);
        globalMax = Math.max(localMax, globalMax);
    }
    
    globalMax = if (globalMax < 0) 0 else globalMax;

    println(globalMax.toString());
}
