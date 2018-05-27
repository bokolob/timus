import java.util.Arrays

fun main(args: Array<String>) {
    val cnt = readLine()!!.toInt();
    val src = readLine()!!;
    val dst = readLine()!!;

    val p = prefixFunc(src+(0.toChar())+dst);

    val charMap:MutableMap<Char,Int> = mutableMapOf();

    for (i in 0..src.length-1) {
        charMap[ src[i] ] =  i;
    }

    var lastPrefix = p[p.size-1];

    for (j in lastPrefix downTo 1) {
        var found = true;
        val shift = src.length - j;

        var i = j;
        var k = 0;

        for (c in charMap.keys) {
            if (dst[ (shift + charMap[c]!!) % dst.length ] != c) {
                found = false;
                break;
            }
        }

        if (!found)
            continue;

        while (i < src.length && k < dst.length - j) {
            if (src[i] != dst[k]) {
                found = false;
                break;
            }
            i++;
            k++;
        }

        if (found == true) {
            println(shift.toString());
            return;
        }
    }

    println("-1");
}

fun prefixFunc(pattern:String):Array<Int> {
    val prefix = Array<Int>(pattern.length, {0});
    //0123456789
    //nanannbanana
    //001231001234
    //[0, 0, 1, 2, 3, 1, 0, 0, 1, 2, 3, 4]

    for (i in 1..prefix.size-1) {
        var j = i-1 ;

        while (pattern[ prefix[j] ] != pattern[i]) {
            if (prefix[j] == 0)
                break;

            j = prefix[j]-1;
        }
        
        if (pattern[ prefix[j] ] == pattern[i]) {
            prefix[i]=prefix[j]+1;
        }
    }
    
    return prefix;
}
