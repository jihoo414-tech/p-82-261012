package com.back


fun main() {

    var lastId = 0

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

                val id = ++lastId
                println("${id}번 명언이 등록되었습니다.")
            }
        }
    }
}