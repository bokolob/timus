import java.util.Arrays

fun main(args: Array<String>) {
    var cnt = readLine()!!.toInt();
    val values = readLine()!!.split(' ').map({ it.toInt() }).toIntArray();

    var mask = 0;
    var sum = 0;

    for (i in 0..cnt-1) {
    //    println(">> "+(1 shl i).toString(2));
        mask = mask or (1 shl i);
        sum += values[i];
    }
    
    val cache = mutableMapOf<Int, Int>();

    println(recurse(values, mask, sum, cache));
}

fun recurse(values:IntArray, m:Int, totalMonsters:Int, cache: MutableMap<Int,Int>):Int {
    
    //println(m.toString(2)+" "+totalMonsters.toString());

    var mask = m;

    if (mask == 0) {
        return 0;
    }
    
    if (cache.containsKey(mask)) {
        return cache.getOrDefault(mask,0);
    }

    var minDamage = Int.MAX_VALUE;

    for (i in 0..values.size-1) {
        if (0 == (mask and ( 1 shl i )))
            continue;

        val killed = killMonsters(values, i,  mask);
        //println("Killed"+killed.toString())
        val mask_copy = mask;

        mask = destroy(values, i, mask);
        
        val potentialDamage = totalMonsters - killed + recurse(values, mask, totalMonsters - killed, cache);
        minDamage = Math.min(minDamage, potentialDamage)
        
        mask = mask_copy;
    }
    
    cache.put(mask, minDamage);

    return minDamage;
}

fun killMonsters(values:IntArray, n:Int, mask:Int):Int
{
    var sum = 0;

    for (i in 0..2) {
        if ((mask and ( 1 shl ((n+i) % values.size) )) > 0) {
            sum += values[(n+i) % values.size];
        }
    }

    return sum
}

fun destroy(values:IntArray, n:Int, m:Int ):Int 
{
    var mask = m;
    for (i in 0..2) {
        mask = mask and ( 1 shl ((n+i) % values.size) ).inv();
    }
    
    return mask
}




