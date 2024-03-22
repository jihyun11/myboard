const loginInput = document.querySelector("#login-form input");
const loginForm = document.querySelector("#login-form");
const greeting = document.querySelector("#greeting");

const HIDDEN_CLASSNAME = "hidden";
const USERNAME_KEY = "username";


function redirectToWordPage(username) {
    // GET 요청을 보내는 예시
    fetch(`/login?username=${username}`)
        .then(response => {
            if (response.ok) {
                // 페이지로 이동하거나 응답을 처리할 수 있습니다.
                window.location.href = 'http://localhost:8080/word';
            } else {
                throw new Error('Network response was not ok.');
            }
        })
        .catch(error => {
            console.error('There was a problem with the fetch operation:', error);
        });
}

function onLoginSubmit(event) {
    event.preventDefault();
    const username = loginInput.value;
    localStorage.setItem(USERNAME_KEY, username);
    loginForm.classList.add(HIDDEN_CLASSNAME);
    paintGreetings(username);
    redirectToWordPage(username); // 로그인 성공 시 페이지로 이동
}

// function onLoginSubmit(event) {
//     event.preventDefault(); //submit 될때마다 새로고침되는 것을 막음
//     const username = loginInput.value;
//     localStorage.setItem(USERNAME_KEY, username); //localStorage에 username정보 저장 (세션 같은 존재)
//     loginForm.classList.add(HIDDEN_CLASSNAME); //클래스네임에 hidden을 추가하여, 로그인하면 로그인폼이 안보이게 한다
//     paintGreetings(username);
// }

function paintGreetings(username) { //로그인 성공 시, 화면에 성공함을 표시하는 함수
    greeting.classList.remove(HIDDEN_CLASSNAME);
    greeting.innerText = `Hello, ${username}`;

    setTimeout(() => {
        // 환영메시지 3초 후 본 페이지로 이동
        window.location.href = 'http://localhost:8080/word';
    }, 2000);
}

const savedUsername = localStorage.getItem(USERNAME_KEY);
if (savedUsername === null) {
    loginForm.classList.remove(HIDDEN_CLASSNAME);
    loginForm.addEventListener("submit", onLoginSubmit);
} else {
    paintGreetings(savedUsername);
    redirectToWordPage(savedUsername); // 이미 로그인되어 있는 경우 페이지로 이동
}

// const savedUsername = localStorage.getItem(USERNAME_KEY);
// if (savedUsername === null) { //localstorage에 저쟝된 값이 있는지만 검사하는 조건문 (실제 동작은 onLoginSubmit 함수에서 해야한다)
//     loginForm.classList.remove(HIDDEN_CLASSNAME);
//     loginForm.addEventListener("submit", onLoginSubmit);
// } else {
//     paintGreetings(savedUsername);
// }