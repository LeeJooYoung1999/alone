<template>
  <div>
    <h2>InputName: 이름을 입력하세요.</h2>
    <hr />
    <ul>
      <InputName @nameChanged="nameChangedHandler" /> 
    </ul>
  </div>
</template>
<script>
import InputName from './components/InputName.vue';
export default {
  name: 'App4',
  components: { InputName },
  data(){re}
};
</script>
