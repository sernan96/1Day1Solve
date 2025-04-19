import java.io.*
import java.util.*

fun main(args: Array<String>) = with(BufferedReader (InputStreamReader(System.`in`))){
    val token = StringTokenizer(readLine())
    val time :Int = 60*Integer.parseInt(token.nextToken())+Integer.parseInt(token.nextToken()) + Integer.parseInt(readLine())
    val hour = if(time/60>=24) time/60%24 else time/60
    val minute = time%60
    print("${hour} ${minute}")
}