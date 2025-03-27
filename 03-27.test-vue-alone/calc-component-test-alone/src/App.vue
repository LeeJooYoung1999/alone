<!-- App.vue를다음과같이정의하고, Calc 컴포넌트가올바르게동작하는지확인하세요 -->

<template>
  <div>
    <Calc />     <!-- //Calc이름통일 1 -->
  </div>
</template>

<script>
// import Calc from './components/Calc.vue';    //Calc이름통일 2
import Calc from './components/Calc2.vue';

export default {
  name:"App",
  components : { Calc },        //components 옵션으로 객체 Calc 를 컴포넌트로 등록
}
</script>