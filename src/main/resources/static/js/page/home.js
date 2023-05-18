function checkSearchInput(el, max) {
	var inputLength = el.value.length;
	if (inputLength > max) {
		alert("검색어는 30자 이내로 작성해주세요.");
		el.value = el.value.substr(0, max);
	}
}

function submitSearchBoard() {
	var type = document.getElementById("select-type");
	var keyword = document.getElementById("search-keyword");
	if (keyword.value == "") {
		alert("검색어를 입력해주세요.")
		return false;
	} else {
		axios({
			method: 'get',
			url: '/board',
			params: {
				type: type.value,
				keyword: keyword.value
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

function enterKey() {
	if (window.event.keyCode == 13) {
		submitSearchBoard();
	}
}