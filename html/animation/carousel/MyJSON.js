// test json stuff
var Mary = '{"name":"Mary", "age":18, "hair":"blonde"}'
// create Dan object
var Dan = new Object();
Dan.name = "Dan";
Dan.age = 30;
Dan.hair = "brown";

function getMary() {
	return this.Mary;
}

// parse json to array of key-value pairs (hashmap)
function parseJSON(jsonStr) {
	var obj = JSON.parse(jsonStr);
	var result = "";
	for (key in obj) {
		result += key + "=" + obj[key] + "\n";
	}
	alert(result);
	return result;
}

// object to json
function makeJSON(obj) {
	var json = JSON.stringify(obj);
	alert(json);
	return json;
}