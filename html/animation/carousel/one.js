//import Person from "Person.js";

function getSum(a, b) {
  var sum = a + b;
  alert("sum is " + sum);
}

function myAry() {
	var ary = ['one', 'two', 'three'];
	ary.sort();
	alert(ary);
}

function myPrompt() {
	var name = prompt("div name");
	var text = document.getElementById(name).innerHTML;
	if (text) {
		alert(text);		
	}
}

// browser history
function myHistory() {
	visitedCnt = history.length;
	alert("you visited " + visitedCnt + " sites.");
	// jump forward: history.forward(), history.go(2)
	// jump back: history.go(-3)
	// jump to first matched history.go("news.com")
}

// url and other location props
function myLocation() {
  aryLoc = [ location.href,
  	location.protocol,
  	location.host,
  	location.hostname,
  	location.port,
  	location.pathname,
  	location.sum
  	];
  alert(aryLoc);
  // reload current page: location.reload(), if this cache is not enough, use document.reload(true) 
  console.log(aryLoc);
}

// navigator - omit b/c not all browsers support it the same way
// you can assign a function name (w/o parenthesis) to a class/obj variable

// test class - not work yet, 
function myClass() {
	let p = new Person("Anh", 35);
	p.sayHi();
}