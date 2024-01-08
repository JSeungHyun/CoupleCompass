<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Sign Up</title>

    <style>
        h2 {
            color: #333;
            text-align: center;
        }

        form {
            display: flex;
            flex-direction: column;
            gap: 10px;
        }

        label {
            font-weight: bold;
        }

        input {
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        button {
            background-color: #4caf50;
            color: #fff;
            padding: 10px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        button:hover {
            background-color: #45a049;
        }

        .signup-container {
            background-color: #fff;
            padding: 20px;
            margin-top: 50%;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
    </style>
</head>

<body>
<div class="signup-container">
    <h2>Sign Up</h2>
    <form action="/signup" method="post">
        <label for="username">Username</label>
        <input type="text" id="username" name="username" required>
        <span id="username-error" class="error-message"></span>

        <label for="email">Email</label>
        <input type="email" id="email" name="email" required>
        <span id="email-error" class="error-message"></span>

        <label for="password">Password</label>
        <input type="password" id="password" name="password" required>
        <span id="password-error" class="error-message"></span>

        <label for="confirmPassword">Confirm Password</label>
        <input type="password" id="confirmPassword" name="confirmPassword" required>
        <span id="confirmPassword-error" class="error-message"></span>

        <label for="code">Invitation Code</label>
        <input type="text" id="code" name="code">
        <span id="code-error" class="error-message"></span>

        <button type="button" onclick="signUp()">Sign Up</button>
    </form>
</div>

<script>

    /** 회원가입 */
    function signUp() {

        // 정규식
        var regUsername = /^(?:[가-힣]+|[a-zA-Z]+|[0-9]+|[a-zA-Z0-9]+)$/;
        var regEmail = /(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))/
        var regPassword = /^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*()_+{}\[\]:;<>,.?~\\-]).{4,12}$/;

        let username = document.getElementsByName("username")[0];
        let email = document.getElementsByName("email")[0];
        let password = document.getElementsByName("password")[0];
        let confirmPassword = document.getElementsByName("confirmPassword")[0];
        let code = document.getElementsByName("code")[0];
        let error = document.getElementsByClassName("error-message");

        const inputArr = [
            { key: username.value, value: '이름' },
            { key: email.value, value: '이메일' },
            { key: password.value, value: '비밀번호' },
            { key: confirmPassword.value, value: '2차 비밀번호' }
        ];

        // 빈 input 태그 검사
        let isEmpty = false;

        inputArr.forEach((input, index) => {
            if (input.key == null || input.key === '') {
                error[index].innerText = input.value + '을(를) 작성해주세요.';
                isEmpty = true;
            } else {
                error[index].innerText = '';
            }
        });

        if (!isEmpty) {
            // username 검사
            if (!regUsername.test(username.value)) {
                alert("이름은 한글 + 숫자 조합 혹은 영어 + 숫자 조합의 두 글자 이상만 가능합니다.");
                username.focus();
                return false;
            }
            // 이메일 검사
            if (!regEmail.test(email.value)) {
                alert('이메일 양식을 확인해주세요.');
                email.focus();
                return false;
            }
            // 비밀번호 검사
            if (!regPassword.test(password.value)) {
                alert("비밀번호는 8자 이상 대소문자 / 특수문자를 포함해야 합니다.");
                password.focus();
                return false;
            }
            // 2차 비밀번호 검사
            if (password.value != confirmPassword.value) {
                alert("비밀번호가 일치하지 않습니다.");
                confirmPassword.focus();
                return false;
            }
            // 코드 검사
            if (code.value == null || code.value == '') { // 코드 값이 없다면
                alert('코드 값 없이 진행하겠습니까?')
            }
        }




    }
</script>
</body>
</html>

