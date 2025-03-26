<!-- 단일 파일 컴포넌트 CheckboxItem -->

<!-- 머리 -->
<template>
    <li>
      <input type="checkbox" v-model="checked" />{{ name }}
    </li>
  </template>
  <!-- 가슴 -->
  <script>
  export default {
    name: "CheckboxItem", //컴포넌트명이므로, 파스칼케이스로 명명
    data(){
      return {
        checked: false,
        props: ["name", "checked"],   //자식컴포넌트인CheckboxItem에서 props옵션에 속성을 정의함.
      };
    }
  };
  </script>
  
  <!-- 배 -->
  <style scoped>
  </style>