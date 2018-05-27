import java.util.Arrays

fun main(args: Array<String>) {
    val cache = mutableMapOf<String, Int>();
    while(true) {
        val values = readLine()!!.split(' ').map({ it.toInt() }).toIntArray();
        
        if (values[0]==0 && values[1]==0)
            break;
        

        println(solve(values[1], values[0], cache))
        println(cache);
    }
}

fun solve(floors:Int, eggs:Int, cache:MutableMap<String,Int>):Int
{
    if (eggs == 0) {
        return 0;
    }

    if (eggs == 1 || floors==1) {
        return floors;
    }

    if (floors == 0)
        return 1;

    val key = floors.toString() + "_" + eggs.toString();

    if (cache.containsKey(key)) {
        return cache.getOrDefault(key,0);
    }

    var result = Int.MIN_VALUE;
    var prev = Int.MIN_VALUE;

    for (i in 1..floors) {
        result = Math.max(result, 1+Math.min(solve(i-1, eggs -1, cache),solve(floors-i, eggs, cache)))

        if (prev >= result)
          break;

        prev = result;
    }

    //println(floors.toString()+" "+eggs.toString()+" "+result );

    cache.put(key, result);

    return result;
}



