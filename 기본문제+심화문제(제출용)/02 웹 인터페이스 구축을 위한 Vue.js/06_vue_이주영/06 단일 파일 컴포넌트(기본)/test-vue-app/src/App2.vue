<template>
  <div>
    <h2>관심있는 K-POP 가수?</h2>
    <hr />
    <ul>
      <CheckboxItem />
    </ul>
  </div>
</template>
<script>
import CheckboxItem from './components/CheckboxItem2.vue';
export default {
name: 'App',
components: {CheckboxItem},

};
</script>
