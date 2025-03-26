import { createApp } from 'vue'
import App from './App.vue'
import CheckboxItem from './components/CheckboxItem.vue'  //단일 파일 컴포넌트인 CheckboxItem 를 임포트함.

import './assets/main.css'

createApp(App).component('CheckboxItem', CheckboxItem)    //component(name, String)메소드를 이용해 CheckboxItem라는 컴포넌트를 전역컴포넌트로 등록
.mount('#app')                                            //등록한 컴포넌트를 app이라는 아이디를 가진 요소에게(index.html) 마운트함.
