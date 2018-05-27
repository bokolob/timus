
fun main(args: Array<String>) {
    val cnt = readLine()!!.toInt();

    val matrix: Array<IntArray> = Array(cnt,{
        readLine()!!.split(' ').map({ it.toInt() }).toIntArray();
    });

    println(solve(matrix).toString());
}

fun solve(matrix:Array<IntArray>):Int
{
    var result:Int = Int.MIN_VALUE;

    for (i in 0..matrix.size-1) {
        val temp = IntArray(matrix.size, {0});
        for (j in i..matrix.size-1) {
            
            for (k in 0..matrix.size-1)
                temp[k] += matrix[k][j];

            result = Math.max(result, kadane( temp ));
        }
    }

    return result;
}

fun kadane(list:IntArray):Int 
{
    var globalMax = Int.MIN_VALUE;
    var localMax = 0;

    for (i in 0..list.size-1) {
        localMax = Math.max(list[i], localMax+list[i]);
        globalMax = Math.max(localMax, globalMax);
    }
    
    return globalMax;
}


