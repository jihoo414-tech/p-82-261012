package com.back

class App {
    fun run(){
        var lastId = 0
        val wiseSayings = mutableListOf<WiseSaying>()

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

                    val wiseSaying = WiseSaying(id, content, author).also{
                        wiseSayings.add(it)
                    }

                    println("${wiseSaying.id}번 명언이 등록되었습니다.")
                }
                "목록" -> {
                    println("번호 / 명언 / 작가")
                    println("--------------------")
                    wiseSayings.reversed().forEach{
                        println("${it.id}/${it.content}/${it.author}")
                    }

                }
            }
        }
    }
}