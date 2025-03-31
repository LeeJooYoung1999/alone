<template>
  <div>
    <Calc />
  </div>
</template>

<script>
// 슬라이드4: App.vue를다음과같이정의하고, Calc 컴포넌트가 올바르게 동작하는지 확인하세요. -->
//import Calc from './components/Calc.vue';

// 슬라이드6: Calc2 컴포넌트를 사용하도록 App.vue를수정하세요.
//import Calc from './components/Calc2.vue';

// 슬라이드8: Calc3 컴포넌트를 사용하도록 App.vue를수정하세요.
// import Calc from './components/Calc3.vue'; 

//Calc4 컴포넌트를 다음처럼 정의하고, 필요한경우코드를완성하세요. -  버튼을 제거하고, x,y값을 입력한 경우 자동으로 결과가 계산됨.
import Clac from './components/Calc4.vue'; 
export default {
  name: 'App',
  components: { Calc },
};
</script>
