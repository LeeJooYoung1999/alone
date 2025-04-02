<template>
  <div>
    <h2>App 컴포넌트</h2>
    <hr />
    <CheckboxItem />
    <CheckboxItem />
    <CheckboxItem />
    <CheckboxItem />
  </div>
</template>

<script>
import CheckboxItem from './components/CheckboxItem.vue';

export default {
  name: 'App',
  components: { CheckboxItem },
  //App.vue에서 다음과 같은 데이터옵션을 추가하고, 이 데이터수만큼 CheckboxItem을 생성한 후 데이터를 바인딩하세요
  //CheckboxItem에 name, checked 속성을 추가하세요.
  data() {
    return {
      idols: [
        { id: 1, name: 'BTS', checked: true },
        { id: 2, name: 'Black Pink', checked: false },
        { id: 3, name: 'EXO', checked: false },
        { id: 4, name: 'ITZY', checked: false },
      ],
    };
  },
};
</script>
