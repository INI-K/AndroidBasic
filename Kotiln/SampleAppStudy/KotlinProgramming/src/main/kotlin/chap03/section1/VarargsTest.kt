package chap03.section1

fun main(){
    normaVarargs(1, 2, 3, 4)
    normaVarargs(4,5,6)
}

fun normaVarargs(vararg counts: Int){
    for(num in counts){
        print("$num ")
    }
    print("\n")
}