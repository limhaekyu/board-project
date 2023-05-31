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

function checkInputPassword() {
	var passwordPattern = /^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/;
	if (!password.value.match(passwordPattern)) {
		window.alert("잘못된 형식의 입력입니다. \n(영문, 숫자, 특수문자 조합의 8~15자리를 입력해주세요.)");
		password.value = password.value.substr(0, 0);
		password.focus();
	}
}

function submitWriteBoard() {
	var title = document.getElementById("title");
	var writer = document.getElementById("writer");
	var password = document.getElementById("password");
	var contents = document.getElementById("contents");
	
	if (title.value == "") {
		alert("제목을 입력해주세요.");
		title.focus();
	} else if (writer.value == "") {
		alert("작성자를 입력해주세요.");
		writer.focus();
	} else if (password.value == "") {
		alert("비밀번호를 입력해주세요.");
		password.focus();
	} else if (contents.value == "") {
		alert("내용을 입력해주세요.");
		contents.focus();
	}
	else {
		axios({
			method: 'post',
			url: '/board/write',
			data: {
				title: title.value,
				writer: writer.value,
				password: password.value,
				contents: contents.value
			}
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
				}
				/*} else if(response.headers["cooltime"] == "1") {
					alert("게시글 생성 후 60초 뒤에 생성이 가능합니다.");
				} */else {
					window.location.href = response.request.responseURL;
				}
			})
			.catch(function(error) {
				console.log(error);
			})
	}
}

function testSubmitWriteBoard() {
	var title = document.getElementById("title");
	var writer = document.getElementById("writer");
	var password = document.getElementById("password");
	var contents = document.getElementById("contents");
	
	var files = document.getElementById('uploadFiles').files;
	var formData = new FormData();
	
	for (var i = 0; i < files.length; i++) {
		formData.append('files', files[i]);
	}
	
	if (title.value == "") {
		alert("제목을 입력해주세요.");
		title.focus();
	} else if (writer.value == "") {
		alert("작성자를 입력해주세요.");
		writer.focus();
	} else if (password.value == "") {
		alert("비밀번호를 입력해주세요.");
		password.focus();
	} else if (contents.value == "") {
		alert("내용을 입력해주세요.");
		contents.focus();
	}
	else {
		formData.append('title', title.value);
		formData.append('writer', writer.value);
		formData.append('password', password.value);
		formData.append('contents', contents.value);
		
		axios({
			method: 'post',
			url: '/board/write',
			headers: {
				"Content-Type": "multipart/form-data",
			},
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
				}
				/*} else if(response.headers["cooltime"] == "1") {
					alert("게시글 생성 후 60초 뒤에 생성이 가능합니다.");
				} */else {
					window.location.href = response.request.responseURL;
				}
			})
			.catch(function(error) {
				console.log(error);
			})
	}
}

var multipleUploadForm = document.querySelector('#multipleUploadForm');
var multipleFileUploadInput = document.querySelector('#multipleFileUploadInput');
var multipleFileUploadError = document.querySelector('#multipleFileUploadError');
var multipleFileUploadSuccess = document.querySelector('#multipleFileUploadSuccess');

function uploadMultipleFiles(files) {
    var formData = new FormData();
    for(var index = 0; index < files.length; index++) {
        formData.append("files", files[index]);
    }
 
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/uploadMultipleFiles");
 
    xhr.onload = function() {
        console.log(xhr.responseText);
        var response = JSON.parse(xhr.responseText);
        if(xhr.status == 200) {
            multipleFileUploadError.style.display = "none";
            var content = "<p>All Files Uploaded Successfully</p>";
            for(var i = 0; i < response.length; i++) {
                content += "<p>DownloadUrl : <a href='" + response[i].fileDownloadUri + "' target='_blank'>" + response[i].fileDownloadUri + "</a></p>";
            }
            multipleFileUploadSuccess.innerHTML = content;
            multipleFileUploadSuccess.style.display = "block";
        } else {
            multipleFileUploadSuccess.style.display = "none";
            multipleFileUploadError.innerHTML = (response && response.message) || "Some Error Occurred";
        }
    }
 
    xhr.send(formData);
}

multipleUploadForm.addEventListener('submit', function(event){
    var files = multipleFileUploadInput.files;
    if(files.length === 0) {
        multipleFileUploadError.innerHTML = "Please select at least one file";
        multipleFileUploadError.style.display = "block";
    }
    uploadMultipleFiles(files);
    event.preventDefault();
}, true);

