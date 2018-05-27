import java.util.Arrays
import java.math.BigInteger;

fun kbanacci(n:Int,k:Int):BigInteger {
    if (n < k-1) {
        return BigInteger.ZERO;
    }
    else if (n <= k) {
        return BigInteger.ONE;
    }

    val prevs:Array<BigInteger> = Array(k, {BigInteger.ZERO});

    prevs[k-1] = BigInteger.ONE;
    var result = BigInteger.ONE;

//    println("S "+Arrays.toString(prevs)+" "+result);
    for (i in (k+1)..n) {

        for (j in 0..k-2) {
            prevs[j] = prevs[j+1];
        }

        prevs[k-1] = result;
        result = result + result - prevs[0];
//        println(Arrays.toString(prevs)+" "+result);
    }

    return result;
}

fun ds(la:Int, lb:Int, n:Int,  max_a:Int, max_b:Int):Int {
    var cnt = 0;

    if (n==1) {
        if (la < max_a) {
            cnt++;
        }

        if (lb < max_b) {
            cnt++;
        }
    }
    else {
        if (la < max_a) {
            cnt += ds(la+1, 0, n-1, max_a, max_b);
        }

        if (lb < max_b) {
            cnt += ds(0, lb+1, n-1, max_a, max_b);
        }
    }

    return cnt;
}

fun main(args: Array<String>) {
    val input = readLine()!!.split(' ').map({it.toInt()}).toIntArray();
   
    val h = BigInteger("1000000007");
    
    var sum1:BigInteger = BigInteger.ONE;
    var sum2:BigInteger = BigInteger.ONE;

    var current1:Array<BigInteger> = Array(input[1],{BigInteger.ZERO});
    var current2:Array<BigInteger> = Array(input[2],{BigInteger.ZERO});
    
    current1[0] = BigInteger.ONE;
    current2[0] = BigInteger.ONE;
    val n = input[0];

    for (i in 1..n-1) {
        val sum1Copy = sum1;

        sum1 = sum1 - current1[current1.size - 1] + sum2;
        shift(current1);
        current1[0] = sum2;

        sum2 = sum2 - current2[current2.size - 1] + sum1Copy;
        shift(current2);
        current2[0] = sum1Copy;
    }
    
    println((sum1+sum2).rem(h).toString())
}

fun shift(array:Array<BigInteger>) {
    
    if (array.size <= 1) {
        return;
    }

    for (i in array.size-1 downTo 1) {
        array[i]=array[i-1];
    }
}

