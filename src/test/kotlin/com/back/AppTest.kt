package com.back

import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test
import kotlin.test.assertContains

class AppTest {

    @Test
    fun `명언 작성`() {
        val result = TestRunner.run(
            """
            등록
            나의 죽음을 적들에게 알리지 말라.
            충무공 이순신
        """
        )
        println("result: $result")
        assertContains(result, "명언: ")
        assertContains(result, "작가: ")
        assertContains(result, "1번 명언이 등록되었습니다.")
    }
    @Test
    fun `명언 목록`(){
        val result = TestRunner.run(
            """
                등록
                현재를 사랑하라.
                작자미상
                등록
                과거에 집착하지 마라.
                작자미상
                목록
                """
        )
        println("result: $result")

        assertThat(result)
            .contains("번호 / 작가 / 명언")
            .contains("----------------------")
            .contains("2 / 작자미상 / 과거에 집착하지 마라.")
            .contains("1 / 작자미상 / 현재를 사랑하라.");


    }

}