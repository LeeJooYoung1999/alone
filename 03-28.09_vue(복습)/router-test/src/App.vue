<template>
  <div class="container">
    <Header />
    <router-view></router-view>    <!-- //요청된 url경로 <->routes배열 비교하여, 매칭된 라우트의 컴포넌트를 "마운트"__ 교재316페이지 -->
  </div>
</template>

<script>
import Header from '@/components/Header.vue';

export default {
  name: 'App',
  components: { Header },
};
</script>
<style>
.container {
  text-align: center;
  margin-top: 10px;
}
</style>
