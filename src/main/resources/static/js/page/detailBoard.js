function checkInput(el, max) {
	var inputLength = el.value.length;
	var spacePattern = /\s/g;
	if (inputLength > max) {
		alert("입력가능한 글자수를 초과했습니다. (" + max + "자)");
		el.value = el.value.substr(0, max);
	}
	if (el.value[0].match(spacePattern)) {
		alert("첫 글자는 공백이 될 수 없습니다.")
		el.value = el.value.substr(0, 0);
	}
}

function chkPassword() {
    var id = document.getElementById("id");
    var password = document.getElementById("password");
   	var deletePasswd = prompt("게시글의 비밀번호를 입력하세요. \n(비밀번호는 영문,숫자,특수문자 조합의 8~16자리 입니다.)", "");
   	if (deletePasswd == ""){
   		alert("비밀번호를 입력해주세요. \n(비밀번호는 영문,숫자,특수문자 조합의 8~15자리 입니다.)")
 		chkPassword();
   	} else if (deletePasswd == null) {
   		return false;
   	} else{
   		if (password.value == deletePasswd) {
   			axios({
   				method: 'get',
   				url: '/board/' + id.value +'/edit',
   				params: {}
   			})
   			.then(function(response) {
   				console.log(response);
   				window.location.href = response.request.responseURL;
   			})
   			.catch(function(error) {
   				console.log(error);
   			})
   		} else {
   			alert("일치하지 않는 비빌번호입니다. 비밀번호를 다시 입력해주세요.")
			chkPassword();
   		}
	}
}

function deleteBoard() {
    var id = document.getElementById("id");
    var password = document.getElementById("password");
   	var deletePasswd = prompt("게시글의 비밀번호를 입력하세요. \n(비밀번호는 영문,숫자,특수문자 조합의 8~16자리 입니다.)", "");
   	if (deletePasswd == ""){
   		alert("비밀번호를 입력해주세요. \n(비밀번호는 영문,숫자,특수문자 조합의 8~15자리 입니다.)")
 		deleteBoard();
   	} else if (deletePasswd == null) {
   		return false;
   	} else{
   		if (password.value == deletePasswd) {
   			if(confirm("게시글을 삭제하시겠습니까?")) {
   				axios({
   	   				method: 'post',
   	   				url: '/board/' + id.value,
   	   				params: {}
   	   			})
   	   			.then(function(response) {
   	   				console.log(response);
   	   				window.location.href = response.request.responseURL;
   	   			})
   	   			.catch(function(error) {
   	   				console.log(error);
   	   			})
   			} else {
   				return false;
   			}
   		} else {
   			alert("일치하지 않는 비빌번호입니다. 비밀번호를 다시 입력해주세요.")
			deleteBoard();
   		}
	}
}
	

function viewReplyBoard() {
	var id = document.getElementById("id");
	axios({
		method: 'get',
		url: '/board/'+id.value+'/reply',
		params:{}
	})
	.then(function(response) {
		console.log(response);
		window.location.href = response.request.responseURL;
	})
	.catch(function(error) {
		console.log(error);
	})	
}

function checkInputCommentWriter(el, max) {
	var inputLength = el.value.length;
	document.getElementById("commentWriterLengthCheck").innerHTML = inputLength + "/" +max;
	var spacePattern = /\s/g;
	if (inputLength > max) {
		alert("입력가능한 글자수를 초과했습니다. (" + max + "자)");
		el.value = el.value.substr(0, max);
		document.getElementById("commentWriterLengthCheck").innerHTML = el.value.length +"/"+max;
	}
	if (el.value[0].match(spacePattern)) {
		alert("첫 글자는 공백이 될 수 없습니다.")
		el.value = el.value.substr(0, 0);
		document.getElementById("commentWriterLengthCheck").innerHTML = el.value.length +"/"+max;
	}
}

function checkInputCommentPassword() {
	var passwordPattern = /^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/;
	if (!commentPassword.value.match(passwordPattern)) {
		window.alert("잘못된 형식의 입력입니다. \n(영문, 숫자, 특수문자 조합의 8~15자리를 입력해주세요.)");
		commentPassword.value = commentPassword.value.substr(0, 0);
		commentPassword.focus();
	}
}

function checkInputCommentContents(el, max) {
	var inputLength = el.value.length;
	document.getElementById("commentContentsLengthCheck").innerHTML = inputLength + "/" +max;
	if (inputLength > max) {
		alert("입력가능한 글자수를 초과했습니다. (" + max + "자)");
		el.value = el.value.substr(0, max);
		document.getElementById("commentContentsLengthCheck").innerHTML = el.value.length + "/" +max;
	}
}

function submitComment() {
	var id = document.getElementById("id");
	var commentWriter = document.getElementById("commentWriter");
	var commentPassword = document.getElementById("commentPassword");
	var commentContents = document.getElementById("commentContents");
	if (commentWriter.value == "") {
		alert("작성자를 입력해주세요.");
		commentWriter.focus();
	} else if (commentPassword.value == "") {
		alert("비밀번호를 입력해주세요.");
		commentPassword.focus();
	} else if (commentContents.value == "") {
		alert("내용을 입력해주세요.");
		commentContents.focus();
	} else {
		axios({
			method: 'post',
			url: '/board/'+id.value+'/comment',
			data: {
				writer: commentWriter.value,
				password: commentPassword.value,
				contents: commentContents.value
			}
		})
		.then(function(response) {
			console.log(response);
			window.location.href = response.request.responseURL;
		})
		.catch(function(error) {
			console.log(error);
		})	
	}
}

function deleteComment(commentId, commentPassword) {
	var id = document.getElementById("id");
   	var deleteCommentPasswd = prompt("게시글의 비밀번호를 입력하세요. \n(비밀번호는 영문,숫자,특수문자 조합의 8~16자리 입니다.)", "");
			
   	if (deleteCommentPasswd == ""){
   		alert("비밀번호를 입력해주세요. \n(비밀번호는 영문,숫자,특수문자 조합의 8~15자리 입니다.)")
 		deleteComment();
   	} else if (deleteCommentPasswd == null) {
   		return false;
   	} else{
   		if (commentPassword == deleteCommentPasswd) {
   			if(confirm("게시글을 삭제하시겠습니까?")) {
   				axios({
   	   				method: 'post',
   	   				url: '/board/' + id.value + '/comment/' + commentId,
   	   				params: {}
   	   			})
   	   			.then(function(response) {
   	   				console.log(response);
   	   				window.location.href = response.request.responseURL;
   	   			})
   	   			.catch(function(error) {
   	   				console.log(error);
   	   			})
   			} else {
   				return false;
   			}
   		} else {
   			alert("일치하지 않는 비빌번호입니다. 비밀번호를 다시 입력해주세요.")
			deleteComment();
   		}
	}
}