// 현재 작업 디렉토리의 파일 목록을 출력하세요.
// o 동기함수 호출로 처리하세요.


const fs = require('fs');

let files = fs.readdirSync('./');
console.log(files);
