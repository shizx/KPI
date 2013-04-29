/**
* 提交一个指定的表单
* $formName可以是名字也可以是dom对象
*/
function __post($formName) {
  if (window._formPostingInProcess) {
    alert("有操作正在进行中，请稍后");
   return;
  }
  var _domForm = null;
  var _domPostName = null;
  var _domPostFrame = null;

  if ($formName==null) {
   _domForm = document.forms[0];
  }else {
   if (typeof($formName)=="string") {
     _domForm = __get($formName);
   }else {
     _domForm = $formName;
   }
  }

  //first it need to check the form it's validate, process 1.juice check 2.formName_check
  var errorList = __validate(_domForm);
  var formCheckFct = eval("window." + _domForm.name + "_check");
  if (formCheckFct!=null) { //if use supply the special form check method then call it
   formCheckFct(_domForm, errorList);
  }


  if (errorList.hasErrors()) {
   if (errorList.isAlert()) {
     alert(errorList);
   }else {
     alert(errorList);
   }
   return;
  }

  if (errorList.getReturn()) {
   return;
  }

  if (errorList.getConfirm()!=null) {
   if (!confirm(errorList.getConfirm())) {
     return;
   }
  }


  /** conver all the elements value's special code */
  var elements = _domForm.elements;
  var el = null;
  var elTN = null;
  for (var i=0, len=elements.length; i<len; i++) {
   el = elements[i];
   elTN = el.tagName.toUpperCase();
   if (elTN=="INPUT" || elTN=="TEXTAREA") {
     if (el.getAttribute("tt.nofilter")==null) { //some code need to be use html support
       el.value = __filter(el.value);
     }
   }
  }

  var formBeforeSubmit = eval("window." + _domForm.name + "_onSubmit");
  if (formBeforeSubmit!=null) {
   if (!formBeforeSubmit(_domForm)) {
     return;
   }
  }

  if (_domPostFrame==null) {
   _domPostName = __uuid();
   _domPostFrame = __createElementByHTML("<iframe name='" + _domPostName + "' style='display:none' src=''></iframe>");
   document.body.insertAdjacentElement("beforeEnd", _domPostFrame);
   _domPostFrame.onreadystatechange = formListener;
   _domForm.setAttribute("target", _domPostName);
  }

  _domForm.submit();
  window._formPostingInProcess = true;
  /**
  * 表单递交时的监听器
  */
  function formListener() {
   if (_domPostFrame.readyState=="complete") {
     window._formPostingInProcess = false;
   }
  }
}

/**
 * create and html dom object by html
 */
function __createElementByHTML($html) {
  var node = document.createElement("SPAN");
  node.innerHTML = $html;
  return node.children[0];
}

/**
 * check form's element it's validate
 */
function __validate($domForm) {
  var elements = $domForm.elements;
  var errorList = new ErrorList();
  for (var i=0, len=elements.length; i<len; i++) {
    __validateElement(elements[i], errorList);
  }
  return errorList;
}

/**
 * filter the special char in the str
 */
function __filter($str) {
  $str = $str.replace(/</g,"&lt;");
  $str = $str.replace(/>/g,"&gt;");
  $str = $str.replace(/\"/g,"&quot;");
  return $str;
}

/**
 * check an element it's validate, it put the error object in the list
 */
function __validateElement($element, $errorList) {
  var checkType = $element.getAttribute("tt.checktype");
  var bRequire = $element.getAttribute("tt.require")=="true"?true:false;
  var val = $element.value;
  var comment = $element.getAttribute("tt.comment");
  //if the comment it's null,then try to find the text in the td previouse
  if (comment==null) {
    if ($element.parentElement.tagName=="TD") {
      if ($element.parentElement.previousSibling!=null) {
        comment = $element.parentElement.previousSibling.innerText;
      }
    }
  }

  //required input check
  if (bRequire) {
    if (__isEmpty(val)) {
      $errorList.add(new Error(comment + "是必须输入的", $element));
    }
  }

  //type check date, int, double,if you found the type i do not implements yet, tell me i will implement it at once
  switch(checkType) {
    case "int":
      if (!__isInt(val)) {
        $errorList.add(new Error(comment + "请输入数字类型", $element));
      }
      break;
    case "double":
      if (!__isDouble(val)) {
        $errorList.add(new Error(comment + "请输入浮点类型", $element));
      }
      break;
    case "-double":
      if (!__isDouble(val, true)) {
        $errorList.add(new Error(comment + "请输入浮点类型", $element));
      }
      break;
    case "datetime":
      break;
  }
}


/**
 * get an cookie from the user machine
 */
function __cookieGet($name) {
  var arg = $name + "=";
  var alen = arg.length;
  var clen = document.cookie.length;
  var i = 0;
  while (i < clen) {
    var j = i + alen;
    if (document.cookie.substring(i, j) == arg)
      return getVal (j);
    i = document.cookie.indexOf(" ", i) + 1;
    if (i == 0)
      break;
  }
  return null;

  function getVal(offset) {
    var endstr = document.cookie.indexOf (";", offset);
    if (endstr == -1)
      endstr = document.cookie.length;
    return unescape(document.cookie.substring(offset, endstr));
  }
}

/**
 * set an cookie, it can use hidden params
 */
function __cookieSet($name, $value) {
  var argv = __cookieSet.arguments;
  var argc = __cookieSet.arguments.length;
  var expires = null;
  var exp = new Date();
  if (argc > 2 ){
    exp.setTime(exp.getTime() + argv[2]);
    expires = exp;
  }else { //if no special the time out then use 30 days
    exp.setTime(exp.getTime() + 1000*60*60*24*30);
    expires = exp;
  }

  var path = (argc > 3) ? argv[3] : null;
  var domain = (argc > 4) ? argv[4] : null;
  var secure = (argc > 5) ? argv[5] : false;

  document.cookie = $name + "=" + escape ($value) + ((expires == null) ? "" : ("; expires=" + expires.toGMTString())) + "; path=/";
}

/**
 * del an cookie
 */
function __cookieDel($name) {
  var exp = new Date();
      exp.setTime(exp.getTime() - 1);
  var val = __cookieGet($name);
  document.cookie = $name + "=" + val + "; expires=" + exp.toGMTString() + ";path=/";
}

/**
 * get the object that has the same id name, it alway
 * return an collection, but the collection can be null
 */
function __getObjs($name) {
  return __arrayDoms(document.all[$name]);
}

/** get an dom object */
function __get($name, $dom) {
  $dom = $dom || document;
  return $dom.getElementById($name);
}

//))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))
/**
 * ErrorList Class
 */
//((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((

              function ErrorList() {
                var _errors = [];
                var _confirm = null;
                var _bReturn = false;
                var _bAlert = false;
                this.add = add;

                function add($error) {
                  _errors.push($error);
                };

                this.setReturn = setReturn;

                function setReturn($type) {
                  _bReturn = $type;
                };

                this.getReturn = getReturn;

                function getReturn() {
                  return _bReturn;
                };

                this.setConfirm = setConfirm;

                function setConfirm($msg) {
                  _confirm = $msg;
                };

                this.getConfirm = getConfirm;

                function getConfirm() {
                  return _confirm;
                };

                this.hasErrors = hasErrors;

                function hasErrors() {
                  return _errors.length!=0;
                };

                this.setAlert = setAlert;

                function setAlert($type) {
                  _bAlert = $type;
                };

                this.isAlert = isAlert;
                function isAlert() {
                  return _bAlert;
                };

                this.toString = toString;

                function toString() {
                  var tmp = [];
                  for (var i=0, len=_errors.length; i<len; i++) {
                    tmp.push(_errors[i].toString());
                  }
                  return tmp.join("\n");
                };
              }


function Error($msg, $dom) {
  this.msg = $msg;
  this.dom = $dom;
  this.toString = toString;
  function toString() {
    return this.msg;
  };
}

/**
 * it's the str it's empty
 */
function __isEmpty($str) {
  return __trim($str).length==0;
}

var _regLtrim = /^\s*/;
var _regRtrim = /\s*$/;
/**
 * trim and string
 */
function __trim($text) {
  if ($text==null)
    return "";
  return $text.replace(_regLtrim, "").replace(_regRtrim, "");
};


/**
 * get the uuid
 */
function __uuid() {
  return _Uuid.create();
}

//))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))
/**
 * uuid static class it can generate an global uuid number it's char(36)
 */
//((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((
function Uuid() {
  var _chars = "0123456789abcdef";
  this.create = create;
  function create() {
    var uuid = hex(new Date().getTime()) + "-" +
               hex(random()).substr(0,4) + "-" +
               hex(random()).substr(1,4) + "-" +
               hex(random()).substr(2,4) + "-" +
               hex(random()).substr(3,4) +
               hex(random()).substr(4,4) +
               hex(random()).substr(2,4);
    return uuid;
  };

  /**
   * random number
   */
  function random() {
    return Math.random() * 10000000000;
  };

  /**
   * translate the number to hex
   */
  function hex($num) {
    var str = "";
    for(var j = 7; j >= 0; j--)
      str += _chars.charAt(($num >> (j * 4)) & 0x0F);
    return str;
  };
}
var _Uuid = new Uuid();