<!-- Calc2 컴포넌트를다음처럼정의하고, 필요한경우코드를완성하세요. -->

<template>
	<div>
		X : <input type="text" v-model.number="x" /><br/>   <!-- //x라는 숫자형(by .number)데이터와 --> 
		Y : <input type="text" v-model.number="y" /><br/>   <!-- //y라는 숫자형 데이터를 사용자로부터 입력받아 사용하고 싶으므로, 양방향 바인딩 v-model이용해 바인딩 -->
        <button @click="">계산</button><br>     <!-- 클릭이벤트로 아래서 정의한 연산메소드를 호출하는 버튼 제작 -->
        <div>결과 : {{result}}</div>            <!-- 결과값 result를 무스타치로 호출 -->
	</div>
</template>

<script>
import { ref } from 'vue';    //밑에서 ref라는 함수를 쓰고 싶으므로, vue라이브러리에서 import. 이때 ref는 함수이므로, 중괄호{}잊지말자.

export default {
    name:"Calc2",              //파일명 Calc2와 name Calc2 이름통일.
    setup () {
        const x = ref(10);   //set up메소드 내부에서 ref 함수를 이용하여, 반응성 상태데이터 x,y를 선언함.
        const y = ref(20);
        const result = ref(30); 
        const calcAdd = () => {
        result.value = x.value + y.value;   //요구되는 연산을 이곳에서 처리. 단, setup메소드 내부에서 데이터이용할땐 반드시 value속성을 이용해야 함을 기억할것.
        };
        return {} ;   //x,y를 사용하고 싶으므로, 객체형태로 리턴
    }
}
</script>