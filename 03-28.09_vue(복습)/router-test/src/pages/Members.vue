<template>
    <div class="card card-body">
        <h2>Members</h2>
        <p>이 컴포넌트에서 요청된 경로 : {{ currentRoute.fullPath }}</p>
    </div>
</template>

<script>
import { useRoute } from 'vue-router';   //바로this를 쓸수 있었던 옵션api때와는 달리, vue-router에서 제공하는 useRoute함수를 사용하여 리턴된currentRouter객체를 이용해야함. 
export default {
  name : "Members",
  setup(){
    const currentRoute = useRoute()
    return {currentRoute};

  }
}
</script>