
fun main(args: Array<String>) {
    var cnt = readLine()!!.toInt();
    val dict = mutableListOf<Int>();
    
    for (i in 1..cnt) {
        dict.add( readLine()!!.toInt() );
    }

    radixSort(dict);

    readLine();

    val reqs = readLine()!!.toInt();

    for (i in 1..reqs) {
        println(dict[readLine()!!.toInt()-1] );
    }
}

fun radixSort(list:MutableList<Int>) {
     val tmp = IntArray(list.size,{0});

     for (i in 0..13) {
        var lCnt = 0;
        var rCnt = tmp.size-1;
        
        val mask = 1 shl i;
        
        for (j in list) {
            if (j and mask == 0) {
                tmp[lCnt++]=j;
            }
            else {
                tmp[rCnt--]=j;
            }
        }
        
        for (k in 0..lCnt-1) {
            list[k]=tmp[k];
        }
        
        var p = lCnt;

        for (k in tmp.size-1 downTo lCnt) {
            list[p++]=tmp[k];
        }
     }
}

