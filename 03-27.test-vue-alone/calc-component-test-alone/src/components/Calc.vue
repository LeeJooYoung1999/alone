<!-- setup 함수를사용하는Calc컴포넌트를정의하세요 -->

<template>
	<div>
		X : <input type="text" v-model.number="x" /><br/>   <!-- //x라는 데이터와 --> 
		Y : <input type="text" v-model.number="y" /><br/>   <!-- //y라는 데이터를 사용자로부터 입력받아 사용하고 싶으므로, 양방향 바인딩 v-model이용해 바인딩 -->
	</div>
</template>

<script>
import { ref } from 'vue';    //밑에서 ref라는 함수를 쓰고 싶으므로, vue라이브러리에서 import. 이때 ref는 함수이므로, 중괄호{}잊지말자.

export default {
    name:"Calc",              //파일명 Calc와 name Calc 이름통일.
    setup () {
        const x = ref(10);   //set up메소드 내부에서 ref 함수를 이용하여, 반응성 상태데이터 x,y를 선언함.
        const y = ref(20);
        return {x,y}         //x,y를 사용하고 싶으므로, 객체형태로 리턴
    }
}
</script>