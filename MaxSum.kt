
fun main(args: Array<String>) {
    val cnt = readLine()!!.toInt();
    
    val matrix: Array<IntArray> = Array(cnt,{IntArray(cnt,{0})});

    for (i in 0..cnt-1) {
        val values = readLine()!!.split(' ').map({ it.toInt() });

        //println(values);
        
        for (j in 0..cnt -1) {

            matrix[i][j] = values[j];

            if (i != 0) {
                matrix[i][j] += matrix[i-1][j];
            }

            if (j != 0) {
                matrix[i][j] += matrix[i][j-1]
            }

            if (i > 0 && j > 0) {
                matrix[i][j] -= matrix[i-1][j-1];
            }

            //print(matrix[i][j].toString()+" ");
        }

        //println("");
    }

    println(solve(matrix).toString());
}

fun solve(matrix:Array<IntArray>):Int
{
    var result:Int = Int.MIN_VALUE;

    for (i in 0..matrix.size-1) {
        for (j in 0..matrix.size-1) {
            for (k in i..matrix.size-1) {
                for (p in j..matrix.size-1) {
                    val s = sum(matrix, i, j, k, p);
                    result = Math.max(s, result);
                }
            }
        }
    }

    return result;
}

fun sum(matrix:Array<IntArray>, upperY:Int, upperX:Int, bottomY:Int, bottomX:Int):Int
{
    var sum = matrix[bottomY][bottomX];
    
    if (upperY > 0) {
        sum -= matrix[upperY-1][bottomX];
    }

    if (upperX > 0) {
        sum -= matrix[bottomY][upperX-1];
    }

    if (upperX > 0 && upperY > 0) {
        sum += matrix[upperY-1][upperX-1];
    }

    return sum;
}

