package com.back


fun main() {

    println("== 명언 앱 ==")

    while(true){
        print("명령) ")
        val input = readln().trim()

        when(input){
            "종료" -> break
            "등록" -> {
                print("명언: ")
                val content = readln()
                print("작가: ")
                val author = readln()
            }
        }
    }
}