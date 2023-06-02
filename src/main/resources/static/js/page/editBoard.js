function checkInputTitle(el, max) {
	var inputLength = el.value.length;
	document.getElementById("titleLengthCheck").innerHTML = inputLength + "/" +max;
	var spacePattern = /\s/g;
	if (inputLength > max) {
		alert("입력가능한 글자수를 초과했습니다. (" + max + "자)");
		el.value = el.value.substr(0, max);
		document.getElementById("titleLengthCheck").innerHTML = el.value.length +"/"+max;
	}
	if (el.value[0].match(spacePattern)) {
		alert("첫 글자는 공백이 될 수 없습니다.")
		el.value = el.value.substr(0, 0);
		document.getElementById("titleLengthCheck").innerHTML = el.value.length +"/"+max;
	}
}
function checkInputWriter(el, max) {
	var inputLength = el.value.length;
	document.getElementById("writerLengthCheck").innerHTML = inputLength + "/" +max;
	var spacePattern = /\s/g;
	if (inputLength > max) {
		alert("입력가능한 글자수를 초과했습니다. (" + max + "자)");
		el.value = el.value.substr(0, max);
		document.getElementById("writerLengthCheck").innerHTML = el.value.length +"/"+max;
	}
	if (el.value[0].match(spacePattern)) {
		alert("첫 글자는 공백이 될 수 없습니다.")
		el.value = el.value.substr(0, 0);
		document.getElementById("writerLengthCheck").innerHTML = el.value.length +"/"+max;
	}
}
function checkInputContents(el, max) {
	var inputLength = el.value.length;
	document.getElementById("contentsLengthCheck").innerHTML = inputLength + "/" +max;
	if (inputLength > max) {
		alert("입력가능한 글자수를 초과했습니다. (" + max + "자)");
		el.value = el.value.substr(0, max);
		document.getElementById("contentsLengthCheck").innerHTML = el.value.length + "/" +max;
	}
}

function checkInputPassword(password) {
	var passwordPattern = /^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/;
	if (!password.value.match(passwordPattern)) {
		window.alert("잘못된 형식의 입력입니다. \n(영문, 숫자, 특수문자 조합의 8~15자리를 입력해주세요.)");
		password.value = password.value.substr(0, 0);
		password.focus();
	}
}

function submitEditBoard() {
	var id = document.getElementById("id");
	var title = document.getElementById("title");
	var passwordBefore = document.getElementById("password-before");
	var passwordAfter = document.getElementById("password-after");
	var writer = document.getElementById("writer");
	var contents = document.getElementById("contents");
	
	if (title.value == "") {
		alert("제목을 입력해주세요.");
		title.focus();
	}else if (writer.value == "") {
		alert("작성자를 입력해주세요.");
		writer.focus();
	}else if (contents.value == "") {
		alert("내용을 입력해주세요.");
		contents.focus();
	} else if (passwordAfter.value == passwordBefore.value && passwordAfter.value != "") {
		alert("동일한 비밀번호를 입력하셨습니다.");
		passwordAfter.focus();
	} else if (passwordAfter.value != "" && passwordBefore.value == "") {
		alert("현재 비밀번호를 입력해주세요.");
		passwordBefore.focus();
	} else if (passwordBefore.value != "" && passwordAfter.value == "") {
		alert("변경할 비밀번호를 입력해주세요.");
		passwordAfter.focus();
	} else if (passwordBefore.value !="" && passwordAfter.value != "") {
		var myForm = document.getElementById('formInfo');
		
		var formData = new FormData(myForm);

		axios({
			method: 'post',
			url: '/board/'+id.value+'/edit',
			data: formData
		})
		.then(function(response) {
			console.log(response);
			if (response.headers["bad-word-title"] == "1") {
				alert("제목란에 비속어가 감지되었습니다.");
				title.focus();
			} else if(response.headers["bad-word-writer"] == "1") {
				alert("작성자란에 비속어가 감지되었습니다.")
				writer.focus();
			} else if (response.headers["bad-word-contents"] == "1") {
				alert("내용란에 비속어가 감지되었습니다.")
				contents.focus();
			} else if (response.headers["valid-pw"] == "1") {
				alert("비밀번호가 일치하지 않습니다.")
				passwordBefore.value = passwordBefore.value.substr(0,0);
				passwordBefore.focus();
			} else {
				alert("게시글이 수정되었습니다.")
				window.location.href = response.request.responseURL;
			}
		})
		.catch(function(error) {
			console.log(error);
		})
	}
	else {
		var myForm = document.getElementById('formInfo');
		
		var formData = new FormData(myForm);
		axios({
			method: 'post',
			url: '/board/'+id.value+'/edit',
			data: formData
		})
		.then(function(response) {
			console.log(response);
			if (response.headers["bad-word-title"] == "1") {
				alert("제목란에 비속어가 감지되었습니다.");
				title.focus();
			} else if(response.headers["bad-word-writer"] == "1") {
				alert("작성자란에 비속어가 감지되었습니다.")
				writer.focus();
			} else if (response.headers["bad-word-contents"] == "1") {
				alert("내용란에 비속어가 감지되었습니다.")
				contents.focus();
			} else {
				alert("게시글이 수정되었습니다.")
				window.location.href = response.request.responseURL;
			}
		})
		.catch(function(error) {
			console.log(error);
		})
	}
}
