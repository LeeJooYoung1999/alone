<!-- //슬라이드4:다음 그림과 같이 출력되도록 다음절차에 따라 코드를 완성하세요. -->
<template>
  <li>
    <input type="checkbox" v-model="checked">옵션1
  </li>
</template>

<script>
export default {
  name: 'CheckboxItem2',
  data() {
    return {
        checked: false
    };
  },
};
</script>

