function checkInput(el, max) {
    var inputLength = el.value.length;
    var spacePattern = /\s/g;
	if (inputLength > max) {
		alert("입력가능한 글자수를 초과했습니다. ("+max+"자)");
		el.value = el.value.substr(0, max);
	}
	if (el.value[0].match(spacePattern)) {
		alert("첫 글자는 공백이 될 수 없습니다.")
		el.value = el.value.substr(0,0);
	}
  }

  

  
