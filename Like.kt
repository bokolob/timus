fun main(args: Array<String>) {
    var cnt = readLine()!!.toInt();
    
    while(cnt-- > 0) {
        val request = readLine()!!;
        println(like(request));
    }
}

fun like(request:String):String {
    val ast = parse(SeekableString(request));
    //println(ast.toString());

    return if ( match(ast.nodes[1],0,ast.nodes[0].value?:"", 0)  ) "YES" else "NO";
}

fun parse(request:SeekableString):AST
{
    var root = AST(AST.Token.EXPRESSION, null);

    //println(request.toString());

    getApostrophe(request);
    root.addNode(getString(request));
    getApostrophe(request);

    getSpace(request);
    getOP(request);
    getSpace(request);

    getApostrophe(request);
    root.addNode(getRegExp(request));
    getApostrophe(request);

    return root;
}

fun match(pattern:AST, patternPos:Int, string:String, pos:Int):Boolean
{
    var strPos = pos;

    for (i in patternPos..pattern.nodes.size-1) {
        val node = pattern.nodes[i];

        when(node.type) {
            AST.Token.CHAR -> { if ( strPos >= string.length || node.value!![0] != string[strPos]) { return false }   }
            AST.Token.ANY_SYMBOL -> { if (strPos >= string.length) {return false}  }
            AST.Token.ANY_STRING -> { return match_anystring(pattern, i, string, strPos) }
            AST.Token.RANGE      -> { if (strPos >= string.length || !in_range(node, string[strPos])){return false} }
            AST.Token.INVERSE_RANGE -> { if (strPos >= string.length || in_range(node, string[strPos])){return false} }
            else -> { throw IllegalArgumentException("Unknown type "+node.type.toString());  }
        }

        strPos++;
    }
    
    if (strPos != string.length)
        return false;

    return true;
}

fun match_anystring(pattern:AST, patternPos:Int, string:String, pos:Int ):Boolean
{
    var pp = patternPos;

    while (pp < pattern.nodes.size && pattern.nodes[pp].type == AST.Token.ANY_STRING)
        pp++;
    
    if (pp == pattern.nodes.size - 1 && pattern.nodes[pp].type == AST.Token.ANY_STRING)
        return true;

    for (i in pos..string.length) {
        if (match(pattern, pp, string, i)) {
            return true;
        }
    }

    return false;
}

fun in_range(node:AST, symb:Char):Boolean {
    
    val range:RangeAST = node as RangeAST;
    
    //println("in_range: "+range.compiled.toString());

    return range.compiled.contains(symb);
}

fun getRegExp(request:SeekableString):AST {
    
    var ast:AST=AST(AST.Token.REGEXP,null);

    loop@ while(true) {
        val c = request.nextChar();

        when(c) {
            null -> { break@loop }
            '_'  -> { ast.addNode(AST(AST.Token.ANY_SYMBOL, null)) }
            '%'  -> { ast.addNode(AST(AST.Token.ANY_STRING, null)) }
            '['  -> {
                      ast.addNode(getRange(request));

                      val closingBracket = request.nextChar();

                      if (closingBracket != ']') {
                        throw IllegalArgumentException("No closing bracket");
                      }
                    }
            '\'' -> { if ( request.peek() == '\'' ) {
                        request.nextChar(); 
                        ast.addNode(AST(AST.Token.CHAR, "'"))
                      }
                      else {
                        request.back();
                        break@loop;
                      }
                    }
            else -> {
                        ast.addNode(AST(AST.Token.CHAR, c.toString())) 
                    }
        }
    }

    return ast;
}

//
//RANGE:  CHAR+RANGE|SYMBOL_RANGE+RANGE|EMPTY
//SYMBOL_RANGE:   CHAR-CHAR

fun getRange(request:SeekableString):AST {
   
    val ast:AST;

    val flag = request.nextChar();

    if (flag == '^') {
        ast = RangeAST(AST.Token.INVERSE_RANGE,null);
    }
    else {
        request.back();
        ast = RangeAST(AST.Token.RANGE,null);
    }

    while (request.peek() != ']') {
        val c = request.nextChar();
        
        if (request.peek() == '-' && request.peek(1) != ']') {
            request.back();
            ast.addNode(getSymbolRange(request));
        }
        else {
            ast.addNode(AST(AST.Token.CHAR, c.toString()));
        }
    }

    return ast;
}

fun getSymbolRange(request:SeekableString):AST?
{
    val from = request.nextChar().toString();
    request.nextChar();
    val to   = request.nextChar().toString();

    return AST(AST.Token.SYMBOL_RANGE, null)
           .addNode(AST(AST.Token.CHAR, from))
           .addNode(AST(AST.Token.CHAR, to));
}

fun getOP(request:SeekableString) 
{
    if ( 
            request.nextChar() != 'l' ||
            request.nextChar() != 'i' ||
            request.nextChar() != 'k' ||
            request.nextChar() != 'e'
        ) {
       throw IllegalArgumentException("LIKE keyword required"); 
    }
}

fun getApostrophe(request:SeekableString)
{
    val c = request.nextChar();

    if (c != '\'') {
        throw IllegalArgumentException("Apostrophe required");
    }
}

fun getSpace(request:SeekableString) 
{
    while (request.peek() == ' ') {request.nextChar()};
}

fun getString(request:SeekableString):AST
{
    var result = "";

    while (true) {
        val c = request.nextChar();

        if (c == null) 
            break;

        if (c == '\'') {
            if (request.peek() == '\'') {
                request.nextChar();
                result += '\''
            }
            else {
                request.back();
                break;
            }
        }
        else {
            result += c;
        }
    }

    return AST(AST.Token.STRING, result);
}

class RangeAST(type:Token, value:String?):AST(type, value)
{
    val compiled:Set<Char> by lazy {
        nodes.map {
                    when (it.type) {
                        AST.Token.CHAR -> { listOf(it.value!![0]) }
                        AST.Token.SYMBOL_RANGE -> { ( it.nodes[0].value!![0]..it.nodes[1].value!![0] ).toList() }
                        else -> {throw IllegalArgumentException("Unknown type "+it.type.toString())}
                    }
                  }
             .flatten()
             .toSet()
    }
}

open class AST(type:Token, value:String?)
{
    val nodes:MutableList<AST>;
    val type = type;
    val value=value;

    init {
        nodes = mutableListOf<AST>();
    }
    
    enum class Token {
        ANY_SYMBOL, ANY_STRING, RANGE, STRING, OP, REGEXP, EXPRESSION, CHAR, INVERSE_RANGE, SYMBOL_RANGE
    }

    fun addNode(n:AST?):AST {
        if (n != null) {
            nodes.add(n)
        }

        return this;
    }

    override fun toString():String {
        return "{ " + type.toString() +" "+ value + " "+ nodes.toString()+ "}";
    }
}

class SeekableString(str:String)
{
    var position = 0;
    val str = str;

    fun nextChar():Char? {
        val rc = this.peek();

        if (rc != null)
            position++;

        return rc;
    }
    
    fun back() {
        if (position>0)
            position--;
    }

    fun peek():Char? {
        
        if (position >= str.length) {
            return null;
        }

        return str[position];
    }

    fun peek(n:Int):Char? {
        
        if (position+n >= str.length) {
            return null;
        }

        return str[position+n];
    }

    override fun toString():String {
        return str.toString();
    }

}


