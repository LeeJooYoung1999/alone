<template>
  <div>
    <h2>App 컴포넌트</h2>
    <hr />
    <ul>
      <!-- <CheckboxItem />
      <CheckboxItem />
      <CheckboxItem />
      <CheckboxItem /> -->

      <!-- 반복연산자인 v-for를 이용해, 배열idols안의 item인 idol개수만큼 반복시킴 -->
      <CheckboxItem v-for="idol in idols" 
      :key="idol.id"
      :name="idol.name"
      :checked="idol.checked"/>   <!-- 부모 컴포넌트인 App.vue에서는 v-bind디렉티브(여기서는 축약형인 : 로 사용)를 이용해, 자식컴포넌트에서 정의한 속성에 정보를 전달한다. -->
    </ul>
  </div>
</template>

<script>
import CheckboxItem from './components/CheckboxItem.vue';
export default {
  name: "App",
  components: {CheckboxItem},
  data() {
    return {
      idols: [
        {id:1, name:"BTS", checked:true},          //id, name, checked라는 data를 가진 item인 idol
        {id:2, name:"Black Pink", checked:false},
        {id:3, name:"Aespa", checked:false},
        {id:4, name:"ITZY", checked:false},
      ]
    }
  },
};
</script>

<style scoped></style>
