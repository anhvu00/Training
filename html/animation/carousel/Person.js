// ES6 import/export
export default class Person {
	var name = "NA";
	var age = 0;
	constructor(name, age) {
    	this.name = name;
    	this.age = age;
  	}

  	export sayHi() {
    	alert("name = " + this.name + ",age = " + this.age);
  	}

}