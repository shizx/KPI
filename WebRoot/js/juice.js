/**
b * GoodBy Javascript!
 * @Author: TANTOM
 */

if (typeof(_c)=="undefined") {
   var cs = document.all.tags("SCRIPT");
   for (var i=0; i<cs.length; i++) {
       var t = cs[i].src;
       if (t.indexOf("Juice.js")!=-1) {
           if (t.indexOf("/js")==0) {
             t = "";
           }else {
             t = t.substr(0, t.indexOf("/", 1));
           }
           _c = t;
           break;
       }
   }    
}


var _JUICECLASSES = []; //class references
var _CONFIG = [];
_CONFIG['developer.mode']=false;

if (typeof(_MY_SET)=="undefined") {
  _MY_SET = [];
  _MY_SET['Style'] = "A";
  _MY_SET['ToolBar'] = "A";
}

var EventCache = function(){
	var listEvents = [];
	return {
		listEvents : listEvents,
		add : function(node, sEventName, fHandler){
			listEvents.push(arguments);
		},

		flush : function(){
			var i, item;
			for(i = listEvents.length - 1; i >= 0; i = i - 1){
				item = listEvents[i];
				if(item[0].detachEvent){
					item[0].detachEvent(item[1], item[2]);
				};
				item[0][item[1]] = null;
			};
		}
	};
}();

function __addEvent(oEventTarget, sEventType, fDest){
  oEventTarget.attachEvent(sEventType, fDest);
	EventCache.add(oEventTarget, sEventType, fDest);
};

window.onunload = EventCache.flush;
//http://www.thefutureoftheweb.com/blog/adddomloadevent
addDOMLoadEvent = (function(){    
    var load_events = [],
        load_timer,
        script,
        done,
        exec,
        old_onload,
        init = function () {
            done = true;
            clearInterval(load_timer);
            while (exec = load_events.shift())
                exec();
            if (script) script.onreadystatechange = '';
        };

    return function (func) {
        if (done) return func();
        if (!load_events[0]) {
                script = document.getElementById("__ie_onload");
                script.onreadystatechange = function() {
                    if (this.readyState == "complete")
                        init();
                };
            old_onload = window.onload;
            window.onload = function() {
                init();
                if (old_onload) old_onload();
            };
        }

        load_events.push(func);
    }
})();



var __il = document.getElementById("__ie_onload");
if (__il!=null) {
  addDOMLoadEvent(__start);
  window.attachEvent("onload", __startEd);
}else {
  __addEvent(window, "onload", __startOnload);
}

__addEvent(window, "onunload", __clear);
function __startEd() {
  _pageFullLoaded = true;
}

function __clear() {
  try {
    document.body.oncontextmenu = null;
  }catch(e) {
    return;
  }  
  window.detachEvent("onload", __start);
  _JUICECLASSES = null;
  for (var i in window.LOADSCRIPTS) {
    var c = window.LOADSCRIPTS[i];

    for (var j in c) {
      delete c[j];
    }
    delete window.LOADSCRIPTS[i];
  }
  window.LOADSCRIPTS = null;
  window.STATICCLASS_JWINDOW = null;
  window.JWINDOW_AFTERCLOSE = null;
  window.XWINDOW_AFTERCLOSE = null;
  window.LOADSCRIPT_XML = null;
  window.__xmlpost = null;
  window.__xmlLinkAction = null;
  window.detachEvent("onunload", __clear);
  window.onresize = null;
  window.__closeEvts = null;
  for (var i in __dirtyWins) {
    delete __dirtyWins[i];
  }
  window.__dirtyWins = null;
};

function __fixDocFrame() {
  var d = __get("docFrame");
  if (typeof(_fmt_docMode)!="undefined" && _fmt_docMode==1) { //表单此模式不处理
      return;
  }
  if (d!=null && d.clientHeight!=0) {
     var bH = document.body.clientHeight;
     var dH = d.clientHeight;
     if (bH-dH>=95) {
       d.style.height = bH - 95;
     }else {
       var bsH = document.body.scrollHeight;
       var l = bsH-bH;
       d.style.height = bsH - 95;
     }       
  }
}

/**
 * 测试方法可删除
 */
function getFileNameTest() {
  var kc = event.button;  
  if (event.ctrlKey && kc==2) {
    var url = document.location.toString();
    var idx = url.lastIndexOf("/");
    url = url.substr(idx+1);
    var pIdx = url.indexOf("?");
    if (pIdx!=-1) {
      url = url.substr(0, pIdx);
    }
    window.clipboardData.setData("Text", url);
  }
}

function WABodyInit() {    
   if (!_pageFullLoaded) {
     setTimeout(WABodyInit, 10);
     return;
   }
   if (__waBody!=null) {
    __WABody(__waBody); 
   }     
}
window.attachEvent("onload", WABodyInit);
var _pageFullLoaded = false;
  /**
   * a page in work start, juice coming~~
   */
  var __domWAMenus = []; //工具条集合
  var __waBody = null;
function __startOnload() {
  __start();  
  _pageFullLoaded = true;
}
  function __start() {    
    var d = null;
    try {
      d = window.document;
    }catch(e){      
      return;
    }
    __render(d.all.tags("DIV"));
    __render(d.all.tags("SELECT"));
    __render(d.all['COMP']);
    
    __ipRender("INPUT");
    __ipRender("TEXTAREA");

    /** if it's an jwin then set the background-color */
    if (__isJwindow()) {
      if (document.body.getAttribute("tt.importance")==null) {
        document.body.style.backgroundColor = "#f7f3f7";
      }      
    }
    /** if it's and jwin then call an init function */
    if (__isJwindow()) {
      parent.STATICCLASS_JWINDOW.loadFix();
    }


    /** 处理工工具条 */
    var isHasWFMenu = __get("DomWFMenu")==null?false:true; //页面中是否存在工作流的菜单
    if (isHasWFMenu) {
      var mainMenu = __domWAMenus[0];
      if (__get("DomWFMenu").children.length!=0) {
        mainMenu.insertAdjacentHTML("beforeEnd", "<span tt.type=spliter id=spliter></span>");
        mainMenu.insertAdjacentHTML("beforeEnd", __get("DomWFMenu").innerHTML);
        __get("DomWFMenu").outerHTML = "";
      }
      __initWAMenu(mainMenu);
      if (__domWAMenus.length>1) {
        for (var i=1; i<__domWAMenus.length; i++) {
          __initWAMenu(_domWAMenus[i]);
        }
      }
    }else {
      for (var i=0; i<__domWAMenus.length; i++) {
        __initWAMenu(__domWAMenus[i]);
      }
    }

    if (typeof(top.__unJump)!="undefined") {
      top.__unJump();
    }

    __renderSpecialStyle();
    document.body.style.display = "";
    setTimeout(reCheck,2000);
    function reCheck() {
      if (document.body.style.display!="") {
        document.body.style.display = "";
      }
    }
    if (typeof(window.renderFinish)!="undefined") {
      renderFC();
    }
  };
  function renderFC() {
    if (!_pageFullLoaded) {
     setTimeout(renderFC, 100);
     return;
    }
    window.renderFinish();
  }

  var tbTmp = [];
  /**
   * render the special style now it render the table's tt.autoHeight's style
   */
  function __renderSpecialStyle() {
    var tbs = document.all.tags("TABLE");
    var tb = null;
    for (var i=0, len=tbs.length; i<len; i++) {
      tb = tbs[i];
      if (tb.getAttribute("tt.autoHeight")=="true") {
        tb.style.height = document.body.clientHeight - __getPos(tb).y;
        tbTmp.push(tb);
        tb.style.visibility = "visible";
      }
    }
    setTimeout(checkTB, 200);
  }
     function checkTB() {
      for (var i=0, len=tbTmp.length; i<len; i++) {
        tb = tbTmp[i];
        tb.style.height = document.body.clientHeight - __getPos(tb).y;
      }
    }


  /**
   * render components
   */
  function __render($clDoms) {

    $clDoms = __arrayDoms($clDoms);   
    for (var i=0; i<$clDoms.length; i++) {
      var dom = $clDoms[i];
      var impl = dom.getAttribute("tt.impl");
      if (impl!=null) {
          /** 如果是WAMenu的类需要特殊处理 */
          if (impl=="WAMenu") {
            if (dom.getAttribute("id")!="DomWFMenu") {
              __domWAMenus.push(dom);
            }
          }else if (impl=="FormTable") {
            __initFormTable(dom);
          }else if (impl=="WABody") {
            __waBody = dom;
          }else {
            if (typeof(_JUICECLASSES[impl])=="undefined") { //load it from the dynamice lib
            /** try to load dynamice class */
            try {
              __loadScript(impl);
            }catch(e) {
              alert("没找到实现JS类或者类出错A:" + impl + ":" + e.description);
              throw e;
            }
          }
          var cmpN = window._JUICECLASSES[impl];
            var cmp = new cmpN();
            cmp.init(dom);
          }
        if (dom.getAttribute("tt.name")!=null) {
          dom.id = dom.getAttribute("tt.name");
        }
      }else if (dom.tagName=="SELECT") {
        __initJSelect(dom);
      }
    }//end for
  }

function openerGoNext() {
  _nvpw._Qn.goNext();
  top.close();
}

function openerGoPrev() {
  _nvpw._Qn.goPrev();
  top.close();
}

var _nvpw = null;
function __initWAMenu($dom) {
    var _domMenus = null;
    var sp = $dom.all.spliter;
    var insertDom = $dom;
    var insertMethod = "beforeEnd";
    var bFx = $dom.getAttribute("tt.noclose")==null;
    //如果有spliter就插在spliter前
    if (sp!=null) {
       insertDom = sp;
       insertMethod = "beforeBegin";
    }

    if ($dom.getAttribute("tt.nonav")==null) { //上一页下一页
      var jw = __isJwindow();
      _nvpw = jw?parent:opener;
      try {
      if (_nvpw!=null && _nvpw._Qn!=null) {
        if (_nvpw._Qn.isHasCursor() && (typeof(wfCurrentActInsId)!="undefined" || typeof(_tbNav)!="undefined")) {
          if (_nvpw._Qn.isHasPrev()) {
           var es = jw?"_nvpw._Qn.goPrev()":"openerGoPrev()";
           insertDom.insertAdjacentHTML(insertMethod, "<span name='' img=/img/juice/p.gif event=" + es + " title='上一条记录'></span>");
          }
          if (_nvpw._Qn.isHasNext()) {
           var es = jw?"_nvpw._Qn.goNext()":"openerGoNext()";
           insertDom.insertAdjacentHTML(insertMethod, "<span name='' img=/img/juice/n.gif event=" + es + " title='下一条记录'></span>");
          }
        }
      }
      }catch(e){}
    }

    if ($dom.getAttribute("tt.nohelp")==null) {
      //insertDom.insertAdjacentHTML(insertMethod, "<span name=帮助 img=/help/img/h.gif event=__callHelp()></span>");
    }

    if (__isJwindow()) {

      if (bFx) {
          insertDom.insertAdjacentHTML(insertMethod, "<span name=关闭窗口 img=/img/jwin/co_old2.gif event=parent.__close()></span>");
      }
    }else {
      /**
       * 是否dialog窗口
       */
      if (__isDialog()) {
        if (bFx) {
          $dom.insertAdjacentHTML("beforeEnd", "<span name=关闭窗口 img=/img/jwin/co_old2.gif event=top.close()></span>");
        }
      }else {
        //window.open打开的窗口
        if (typeof(top._bMain)=="undefined") {
          if (bFx) {
          //  insertDom.insertAdjacentHTML(insertMethod, "<span name=关闭窗口 img=/img/jwin/co_old2.gif event=top.__wclose()></span>");
          }
        }
      }
    }

    /** 插入帮助按钮 */
    _domMenus = $dom.children;
    /** 如果没内空不显示操作菜单 */
    if (_domMenus.length==0) {
      $dom.style.display = "none";
      return;
    }

    var domDiv = createDivMenu($dom);

    appendMenu(domDiv, _domMenus);
    $dom.innerHTML = "";
    $dom.insertAdjacentElement("afterBegin", domDiv);
    sp = domDiv.all.spliter;
    if (sp!=null) {
      var newLineMenu = createDivMenu($dom, true);
      var spNext = sp.nextSibling;
      while(spNext!=null) {
        newLineMenu.insertAdjacentElement("beforeEnd", spNext);
        spNext = sp.nextSibling;
      }
      $dom.insertAdjacentElement("afterEnd", newLineMenu);
      sp.outerHTML = "";
    }

}

  function createDivMenu($dom, $wf) {
      var domDiv = document.createElement("DIV");
      __addEvent(domDiv, "onselectstart", function() {return false;});
      domDiv.setAttribute("id", "WAMenuID");
      if (true==$wf) { //wf_toolbar style
        domDiv.style.cssText = "padding:3 3 3 2;width:100%;background-color:#E6EFD9;border-bottom-style:solid;border-bottom-width:1px;border-bottom-color:#D4D0C8";
        domDiv.style.height = '23px';
        return domDiv;
      }
      if ($dom.style.cssText!="") {
        domDiv.style.cssText = $dom.style.cssText;
      }else {
        if (_MY_SET['ToolBar']=="A") {
          //domDiv.style.cssText = "padding:3 3 5 5;background-image:url('" + _c + "/img/wa/mb2.gif');background-repeat:'repeat-x';width:100%";

          domDiv.style.cssText = "padding:0 3 5 5;background-image:url('" + _c + "/img/mbg.gif');background-repeat:'repeat-x';width:100%";
             domDiv.style.height = '32px';
        }else if (_MY_SET['ToolBar']=="B") {
          domDiv.style.cssText = "padding:0 3 4 0;width:100%;background-color:#dddddd;background-image:url('" + _c + "/img/wa/mb3.gif');background-repeat:'repeat-x';";
          domDiv.style.height = '24px';
        }else if (_MY_SET['ToolBar']=="C") {
          domDiv.style.cssText = "padding:0 3 5 5;background-image:url('" + _c + "/img/wa/mb7.gif');background-repeat:'repeat-x';width:100%";
          domDiv.style.height = '24px';
        }
      }
      return domDiv;
  }

 /** 追加菜单 */
  function appendMenu($domDiv, $menus) {
    var menu = null;
    var domSub = null;
    var imgPath = null;
    //var lastDomSub = null;
    for (var i=0; i<$menus.length; i++) {
      menu = $menus[i];

      if (menu.getAttribute("tt.type")=="spliter") {
         domSub = document.createElement("SPAN");
         domSub.setAttribute("id", "spliter");
         domSub.innerHTML = "&nbsp;--- ";
         $domDiv.appendChild(domSub);
         continue;
      }

      imgPath = menu.getAttribute("img");
      if (imgPath.indexOf("/")==0) {
        imgPath = _c + imgPath;
      }

      if (_MY_SET['ToolBar']=="A"||_MY_SET['ToolBar']=="C") {
        domSub = document.createElement("SPAN");
        domSub.className = "WAMenuItem";
      }else if (_MY_SET['ToolBar']=="B") {
        domSub = document.createElement("SPAN");
        domSub.className = "WAMenuItemB";
      }

      var mid = menu.getAttribute("id");
      if (mid!=null) {
        domSub.setAttribute("id", mid);
        menu.removeAttribute("id");
      }

      domSub.style.cssText = menu.style.cssText;

      var imgHtml = "";
      var sw = top._sw;
      /** 如果在800*600的机器就不显示图片 */
      if (typeof(sw)=="undefined") {
        sw = screen.availWidth;
      }

      if (sw>1000) {
        imgHtml = "<img src=" + imgPath + " align=absbottom> ";
      }

      var s = [];
           s.push("<table align=left border=0 cellpadding=0 cellspacing=0 style='margin-left:10px;margin-top:5px;'>");
        s.push("<tr>");
        s.push("<td style='height:3px; width:3px'><img src=" + _c + "/img/btn/1.gif width=3 height=3></td>");
        s.push("<td style='height:3px; background-image:url(" + _c + "/img/btn/2.gif);background-repeat:repeat-x'></td>");
        s.push("<td style='height:3px; width:3px'><img src=" + _c + "/img/btn/3.gif width=3 height=3></td>");
        s.push("</tr><tr>");
        s.push("<td style='width:3px; background-image:url(" + _c + "/img/btn/8.gif);background-repeat:repeat-y'></td>");
        s.push("<td style='background-image:url(" + _c + "/img/btn/19.gif);background-repeat:repeat-x;padding:2 15 2 15' id=ITEXT>");
        s.push(menu.getAttribute("name"));

        s.push("</td>");
        s.push("<td style='width:3px; background-image:url(" + _c + "/img/btn/4.gif); background-repeat:repeat-y'></td>");
        s.push("</tr><tr>");
        s.push("<td style='height:3px; width:3px'><img src=" + _c + "/img/btn/7.gif width=3 height=3></td>");
        s.push("<td style='height:3px; background-image:url(" + _c + "/img/btn/6.gif); background-repeat:repeat-x'></td>");
        s.push("<td style='height:3px; width:3px'><img src=" + _c + "/img/btn/5.gif width=3 height=3></td>");
        s.push("</tr></table>");

      //domSub.innerHTML =   imgHtml + menu.getAttribute("name");
      domSub.innerHTML =   s.join("");
      __addEvent(domSub, "onmouseover", doMOver);
      __addEvent(domSub, "onmouseout", doMOut);
      __addEvent(domSub, "onclick", doMClick);
      domSub.setAttribute("tt.event", menu.getAttribute("event"));
      $domDiv.appendChild(domSub);
      if (menu.getAttribute("title")!=null) {
        domSub.setAttribute("title", menu.getAttribute("title"));
      }
      domSub = null;
    }
  }

  /**
   * mouse click the menu
   */
  function doMClick() {
    var dom = window.event.srcElement;
    dom = __getReal(dom, "tagName", "SPAN");
    var evt = dom.getAttribute("tt.event");
    eval(evt);
  }

  /**
   * mouse over the menu
   */
  function doMOver() {
    var dom = window.event.srcElement;
    dom = __getReal(dom, "tagName", "SPAN");
    if (_MY_SET['ToolBar']=="A"||_MY_SET['ToolBar']=="C") {
      var it = dom.all.ITEXT;
      it.style.textDecoration = "underline";
        it.style.color = "red";
        /**
      dom.style.padding = "1 4 1 4";
      dom.style.color = "#000000";
      dom.style.borderStyle = "solid";
      dom.style.backgroundColor = "#ADB9D8";
      */
    }else if (_MY_SET['ToolBar']=="B"){
      dom.style.color = "#FF0000";
      dom.style.borderStyle = "inset";
    }
  }

  /**
   * mouse out the menu
   */
  function doMOut() {
    var dom = window.event.srcElement;
    dom = __getReal(dom, "tagName", "SPAN");
    if (_MY_SET['ToolBar']=="A"||_MY_SET['ToolBar']=="C") {
         var it = dom.all.ITEXT;
      it.style.textDecoration = "none";
        it.style.color = "black";
        /**
      dom.style.borderStyle = "none";
      dom.style.color = "black";
      dom.style.padding = "2 5 2 5";
      dom.style.backgroundColor = "transparent";
      */
    }else if (_MY_SET['ToolBar']=="B"){
      dom.style.color = "#000000";
      dom.style.borderStyle = "outset";
    }
  }


function __initJSelect($dom) {
    var selected = $dom.getAttribute("tt.selected");
    if (selected!=null) {
      var m = $dom.getAttribute("multiple");
      if (selected!="") {
        if (m==true) {
          var ary = selected.split(",");
          var hm = [];
          for (var i=0, len=ary.length; i<len; i++) {
            hm[ary[i]] = true;
          }
          var ops = $dom.options;
          var op = null;
          for (var i=0, len=ops.length; i<len; i++) {
            op = ops[i];
            if (hm[op.value]==true) {
              op.selected = true;
            }
          }
          return;
        }else {
          $dom.value = selected;
        }
      }else {
        $dom.selectedIndex = 0;
      }
      if ($dom.selectedIndex!=-1) {
        if ($dom.getAttribute("tt.ignore")==null) {
            $dom.options[$dom.selectedIndex].style.backgroundColor = "#CFEBA5";
        }
      }
    }

}


/**
 * all trust the given dom object it's an array type
 */
function __arrayDoms($doms) {
  if ($doms==null)
    return new Array();
  for (i in $doms) {
    if (i=="length") {
      var tmp = new Array();
      for (var i=0; i<$doms.length; i++) {
        tmp.push($doms[i]);
      }
      return tmp;
    }
    break;
  }
  return new Array($doms);
}

/**
 * show and glass
 */
function __glass() {
  var width = 0;
  var height = 0;
  try {
   width = document.body.clientWidth;
   height = document.body.clientHeight;
  }catch(e) {return;};
  if (window.__glassNode==null) {

    /**
    window.__glassNode = document.createElement("<iframe style='position:absolute;left:2px;top:2px;z-index:150;display:none;width:0px;height:0px' scrolling='no' frameborder='0'></iframe>");
    __glassNode.id = "JGlass";
    __glassNode.style.cssText = "position:absolute;left:" + document.body.scrollLeft + ";top:" + document.body.scrollTop + ";z-Index:150;width:" + width + "px;height:" + height + "px";
    __glassNode.style.filter="progid:DXImageTransform.Microsoft.Alpha(style=0,opacity=0)";
    document.body.appendChild(__glassNode);
    */
    var l = document.body.scrollLeft;
    var t = document.body.scrollTop;
    window.__glassNode = document.createElement("DIV");

    //document.body.appendChild(__glassNode);
    document.body.insertAdjacentElement("afterBegin", __glassNode);
    __glassNode.id = "JGlass";
    __glassNode.style.cssText = "position:absolute;left:" + l + ";top:" + t + ";z-Index:150;width:" + width + "px;height:" + height + "px";
    __glassNode.style.backgroundColor = "white";
    __glassNode.style.filter = "alpha(opacity=0)";
  }else {
    __glassNode.style.width = width;
    __glassNode.style.height = height;
    __glassNode.style.display = "";
  }
}

/**
 * hide the glass
 */
function __unglass() {
  if (window.__glassNode!=null) {
    __glassNode.style.display = "none";
  }
}


/**
 * it's jwindow it's opening
 */
function __isOpen() {
  if (window.STATICCLASS_JWINDOW==null) {
    return false;
  }else {
    return window.STATICCLASS_JWINDOW.isOpen();
  }
}

/**
 * check it self it's in an jwin
 */
function __isJwindow() {
  if (parent.STATICCLASS_JWINDOW!=null) {
    return parent.STATICCLASS_JWINDOW.isOpen();
  }
  try {
  return document.body.getAttribute("tt.isJwindow")=="true";
  }catch(e){return false;};    
}

function __isDialog() {
  return top._type=="dialog";
}


/**
 * show an calendar component
 */
function __time($obj) {
  if (window.STATICCLASS_CALENDAR==null) {
    window.STATICCLASS_CALENDAR = __loadScript("CalendarTime", 1);
  }
  window.STATICCLASS_CALENDAR.perform($obj);
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
 * replace the str value
 * example: <b>$test$</b>  if the script code has var test="hello" then wen __replace call
 * will return <b>hello</b>
 */
function __replace($str, $m) {
  return $str.replace(/\$([^\$]*)\$/g, function(){return $m[arguments[1]];});
}

/**
 * check whether the checkbox group or single checkbox it's has at least one checked
 */
function __isCheckboxChecked($name) {
  var domCbs = __getObjs($name);
  for (var i=0, len=domCbs.length; i<len; i++) {
    if (domCbs[i].checked) {
      return true;
    }
  }
  return false;
}



var _waitId = null;
/**
 * link action
 */
function __linkAction($url, $handler) {
  if (window.__xmlLinkAction==null) {
    __loadScript("LinkAction", 7);
  }
  window.__xmlLinkAction($url, $handler);
}



/**
 * render all the input text, it now just render the disable and the readonly attribute
 */
function __ipRender($domName) {
  var doms = document.all.tags($domName);
  var dom = null;
  for (var i=0, len=doms.length; i<len; i++) {
    dom = doms[i];
    if (dom.readOnly==true || dom.disabled==true) {
      __attachClass(dom, "disabled");
    }
  }
}

/**
 * attach and class to the special dom object
 */
function __attachClass($dom, $className) {
  var clsName = $dom.getAttribute("className");
  if (clsName.indexOf($className)==-1) {
    $dom.setAttribute("className", __trim(clsName + " " + $className));

  }
}

function __detachClass($dom, $className) {
  var clsName = $dom.getAttribute("className");
  if (clsName.indexOf($className)!=-1) {
    clsName = clsName.replace($className, "");
  }
  $dom.setAttribute("className", __trim(clsName));
}





//))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))
/**
 * form table implement class
 */
//((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((
function __initFormTable($dom) {
}

function __post($formName, $nc) {

  if (window.__xmlpost==null) {
    __loadScript("Post", 7);
  }
  window.__pageOut = false;
  return window.__xmlpost($formName, $nc);
}

function __postx($formName) {

  if (window.__xmlpostx==null) {
    __loadScript("PostX", 7);
  }
  return window.__xmlpostx($formName);
}

function __back($url) {
	if ($url) {
		document.location = $url;
	} else {
		window.history.back(-1);   
	}
}

function __isJump($fctStr) {
  if ($fctStr.indexOf("\.location")!=-1) { //有跳转操作
    return true;
  }
  if ($fctStr.indexOf("__jump")!=-1) {
    return true;
  }
  return false;
}

function __WABody($dom) {
  __waBody = $dom;
  var pos = __getPos($dom);
  var p = document.body.clientHeight - pos.y;
  if (p>40) {
    var ifct = $dom.getAttribute("tt.iframe")
    if (ifct!=null) {
      p = p - ifct;
    }
    $dom.style.height = p;
    $dom.style.width = "100%";
    $dom.style.overflowY = "auto";
    $dom.style.overflowX = "auto";

    window.onresize = function() {
      __WABody($dom);
    }
  }else {
    document.body.scroll = "yes";
  }
  __fixDocFrame();
}


function __isInWABody($dom) {
  if (__waBody==null) {
    return false;
  }
  return __getReal($dom, "tt.impl", "WABody")!=null;
}



function Error($msg, $dom) {
  this.msg = $msg;
  this.dom = $dom;

  function toString() {
    return this.msg;
  }
  this.toString = toString;
}



/** XLoader moved in ThinClasses.js */
function XLoader() {
  return __loadScript("YLoader", 2);
}


//))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))
/**
 * JPanel it's the global panel that any layer style object will need it
 */
//((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((
function JPanel($bGlass, $dom) {
  var _bGlass = $bGlass || false;
  var _sHtml = "<div style='position:absolute; left:2px; top:2px;z-index:199;color:black;visibility:hidden'></div>";
  var _node = __createElementByHTML(_sHtml);
  if ($dom!=null) {
    $dom.insertAdjacentElement("beforeEnd", _node);
  }else {
    document.body.insertAdjacentElement("beforeEnd", _node);
  }

  /**
   * get the root node
   */
  this.getNode = getNode;
  function getNode() {
    return _node;
  };

  /**
   * show or hidden the panel
   */
  this.setVis = setVis;

  function setVis($bType) {
    _node.style.visibility = $bType?"visible":"hidden";
    if ($bType) {
      if (!_bGlass) {
        __glass();
      }
    }else {
      if (!_bGlass) {
        __unglass();
      }
    }
    _node.style.display = $bType?"":"none";
  };
}

/**
 * kill the page's select object in same bound with the given dom obj
 */

var _floatGFrame = null;
function __killSelects($obj, $rect) {

	if (_floatGFrame == null) {
        _floatGFrame = document.createElement("<iframe style='position:absolute;left:2px;top:2px;z-index:998;display:none;width:0px;height:0px' scrolling='no' frameborder='0'></iframe>");
        _floatGFrame.style.filter="progid:DXImageTransform.Microsoft.Alpha(style=0,opacity=0)";
        $obj.offsetParent.appendChild(_floatGFrame);
        //document.body.insertAdjacentElement("afterBegin",_floatGFrame);
    }

		with(_floatGFrame.style) {

			if ($rect!=null) {
			  width = $rect.w;
			  height = $rect.h;
			}else {
			  width = $obj.offsetWidth;
			  height = $obj.offsetHeight;
			}
		    top = $obj.style.top;
			left = $obj.style.left;
			display = '';
		}
		_floatGFrame.style.zIndex = $obj.style.zIndex - 1;

}

function __hideSelects($node) {
  var s = document.all.tags("SELECT");
  for (var i=0; i<s.length; i++) {
    s[i].style.visibility = "hidden";
  }
}

function __showSelects() {
  var s = document.all.tags("SELECT");
  for (var i=0; i<s.length; i++) {
    s[i].style.visibility = "visible";
  }
}


/**
 * dynamic load an css to the current page
 */
function __loadCss($css) {
  var node = document.createElement("LINK");
  node.rel = "Stylesheet";
  node.type = "text/css";
  node.href = _c + "/css/" + $css;
  document.all.tags("HEAD")[0].appendChild(node);
}

/**
 * release the select dom object
 */

function __releaseSelects($dom) {
   if (_floatGFrame!=null) {
     _floatGFrame.style.display = "none";
   }
}


/** show an alert message */
function __alert($msg) {
    __message({type:1, message:$msg, autoclose:3});
}
//
//function __alert($msg, $time) {
//  if (typeof(top.MessageMgr)!="undefined") {
//    $time = $time || 3;
//    top.__popup($msg, $time, window);
//  }else {
//    alert($msg);
//  }
//}

function __wait($msg) {
  if (typeof(top.MessageMgr)!="undefined") {
    return top.__waitx($msg||"操作正在进行中", window);
  }
}

function __unwait($uuid) {
  if (typeof(top.MessageMgr)!="undefined") {
    top.__unwaitx($uuid);
  }
}


/**
 * get an radio groups selected value
 */
function __getRadioValue($domName) {
  var domRadios = __getObjs($domName);
  for (var i=0; i<domRadios.length; i++) {
    if (domRadios[i].checked) {
      return domRadios[i].value;
    }
  }
  return null;
}

/**
 * set an radio groups selected value
 */
function __setRadioValue($domName, $value) {
  var domRadios = __getObjs($domName);
  for (var i=0; i<domRadios.length; i++) {
    if (domRadios[i].value==$value) {
      domRadios[i].checked = true;
      return;
    }
  }
}


/**
 * recall an user defined function for special component
 */
function __call($dom, $name, $params) {
  var ins = $dom.getAttribute("tt.instance");
  if (ins!=null) {
    var fct = eval("window." + ins + $name);
    if (fct!=null) {
      fct($params);
    }
  }
}

var __dirtyWins = [];
function __wopen($sUrl, $params, $fct) {
  if ($sUrl.indexOf("/")==0 && $sUrl.indexOf(_c)!=0) {
    $sUrl = _c + $sUrl;
  }
  var ps = __parseParams($params);
  var w,h;
  if (ps['relate']!=null) {
    w = Math.round(document.body.clientWidth * ps['relate']);
    h = Math.round(document.body.clientHeight * ps['relate']);
  }else {
    w = ps['width']!=null?ps['width']:screen.availWidth;
    h = ps['height']!=null?ps['height']:screen.availHeight;
  }
  if (ps['max']) {
     w = screen.availWidth;
     h = screen.availHeight;
  }
  var p = "height="+h+",width="+w+",status=yes,toolbar=yes,menubar=yes,location=no,resizable=yes,scrollbars=yes";
  if (ps['center']) {
      var lScreenW = screen.availWidth;
      var lScreenH = screen.availHeight;
      var l = Math.round((lScreenW - w)/2) ;
      var t = Math.round((lScreenH - h)/2);
      p += ",left=" + l + ",top=" + t;
  }
  if (ps['max']) {
    p += ",left=0,top=0";
  }
  var name = ps['name'];
  if (name==null) {
    name = new Date().getTime();
  }
 //   var win = window.open($sUrl);
  var win = window.open($sUrl, name, p);
  if ($fct!=null) {
    __dirtyWins[win] = $fct;
  }
}

function __wclose() {
  top.close();
}

function __wopenToDirty($win) {
  for (var i in __dirtyWins) {
    if (i==$win) {
      __closeEvts.push(__dirtyWins[i]);
    }
  }
}

var __closeEvts = [];
function __onOpenWinClose() {
  if (__closeEvts.length>0) {
    for (var i=0; i<__closeEvts.length; i++) {
      __closeEvts[i]();
    }
    __closeEvts = [];
  }
}

//告诉父窗口数据已经变脏
function __setOpenerDirty() {
  if (__isHasOpener()) {
      try {//bug in not open in lister
      opener.__wopenToDirty(window);
      }catch(e) {}
  }
}

//是否有父窗口
function __isHasOpener() {
  return typeof(opener)!="undefined";
}

/**
 * open an inner window
 */
function __open($sUrl, $params, $fct, $bWOpen) {
  var bInDialog = typeof(top._isModalWin)!='undefined';
  var bForce = false;
  var bWOpen = $bWOpen==null?false:true;
  if (!bWOpen) {
  if (__get("docFrame")!=null || bInDialog || bForce) {
    if (window.__docFrameView==true || bInDialog || bForce) {
      if ($sUrl.indexOf("/")==0 && $sUrl.indexOf(_c)!=0) {
        $sUrl = _c + $sUrl;
      }else {
        if ($sUrl.indexOf(_c)!=0) {
          var curl = document.location.toString();
          curl = curl.substr(0,curl.lastIndexOf("/")+1);
          $sUrl = curl + $sUrl;
        }
      }
      var ps = __parseParams($params);
      var w,h;
      if (ps['relate']!=null) {
        w = document.body.clientWidth * ps['relate'];
        h = document.body.clientHeight * ps['relate'];
      }else {
        w = ps['width']!=null?ps['width']:screen.availWidth;
        h = ps['height']!=null?ps['height']:screen.availHeight;
      }
      h = h*1+24;
      var sOptions = "dialogHeight:" + h + "px;dialogWidth:" + w + "px;resizable:no;status:no;help:no";
      var arg = "";
      if (ps['title']!=null) {
        arg = ps['title'];
      }
      dialog($sUrl, arg, window, sOptions);
      if ($fct!=null) {
        $fct();
      }
      return;
    }
  }
  }    

  if (window.STATICCLASS_JWINDOW==null) {
    window.STATICCLASS_JWINDOW = __loadScript("JWindow", 1);
  }
  window.STATICCLASS_JWINDOW.open($sUrl, $params);
  window.JWINDOW_AFTERCLOSE = $fct;
}

function __xopen($dom, $params, $fix, $fct) {
  if (window.STATICCLASS_XWINDOW==null) {
    window.STATICCLASS_XWINDOW = __loadScript("XWindow", 1);
  }
  window.STATICCLASS_XWINDOW.open($dom, $params, $fix);
  window.XWINDOW_AFTERCLOSE = $fct;
}

/**
 * close the inner window
 */
function __close() {
  if (window.STATICCLASS_JWINDOW!=null) {
    window.STATICCLASS_JWINDOW.close();
    if (window.JWINDOW_AFTERCLOSE!=null) {
      window.JWINDOW_AFTERCLOSE();
      window.JWINDOW_AFTERCLOSE = null;
    }
  }

  if (window.STATICCLASS_XWINDOW!=null) {
    window.STATICCLASS_XWINDOW.close();
    if (window.XWINDOW_AFTERCLOSE!=null) {
      window.XWINDOW_AFTERCLOSE();
      window.XWINDOW_AFTERCLOSE = null;
    }
  }
}


function __setJWinParams($action, $params) {
  if (__isJwindow()) {
    var win = parent.STATICCLASS_JWINDOW;
    win.setJWinParams($action, $params);
  }
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

/**
 * translate the html tr string to a true dom object collection
 */
function __tableRows($trRows) {
  var strTable = "<table>" + $trRows + "</table>";
  var node = document.createElement("SPAN");
  node.innerHTML = strTable;
  var rows = node.children[0].rows;
  var tmp = [];
  for (var i=0, len=rows.length; i<len; i++) {
    tmp.push(rows[i]);
  }
  return tmp;
}



/**
 * look up the dom's parent element when the condition match
 */
function __getReal($dom, $attr, $value) {
  while (($dom != null) && ($dom.tagName != "BODY")) {
    if ($dom.getAttribute($attr) == $value) {
      return $dom;
    }
    $dom = $dom.parentElement;
  }
  return null;
}


/**
 * dynamic load an script xml defined file, why this cause some huge script file some
 * time it do not needed when normal use but for a little time ,i needed so use dynamice
 * load it in the memory and use it，something like template engine, user logs
 * $returnType 0: class pointer
 * $returnType 1: singleton class instance reuse lastest instance
 * $returnType 2: every time it return an new instance
 * $returnType 7: just eval it (i like the seven)
 */
function __loadScript($class, $returnType) {

  if (window.LOADSCRIPT_XML==null) {
    window.LOADSCRIPT_XML = __getXmlObject();
    window.LOADSCRIPTS = [];
  }


  if (window.LOADSCRIPTS[$class]==null) {

    var script = null;
    var bLoadForce = typeof(window._LSBEFORCE)!="undefined";

    if (!bLoadForce) {
      /** first it check the top it's has the xml source,if has then just get it from the top */
      if (typeof(top._XML_SCRIPTS)!="undefined") {
        script = top._XML_SCRIPTS[$class];
      }else {
        /** try to get from opener's top */
        if (typeof(opener)!="undefined") {
          try {
            if (typeof(opener.top._XML_SCRIPTS)!="undefined") {
              script = opener.top._XML_SCRIPTS[$class];
            }
          }catch(e) {
          }
        }
      }
    }
    
    if (script==null) {

      var oXml = window.LOADSCRIPT_XML;
      oXml.async = false;
      oXml.load(_c + "/js/dynamic/" + $class + ".xml");
      script = oXml.documentElement.text;
      
      script = script.replace(/\#\#>/gi, "]]>");
      if (!bLoadForce) {
        if (typeof(top._XML_SCRIPTS)!="undefined") {
          top._XML_SCRIPTS[$class] = script;
        }
        try {
          if (typeof(opener)!="undefined") {
            if (typeof(opener.top._XML_SCRIPTS)!="undefined") {
              opener.top._XML_SCRIPTS[$class] = script;
            }
          }
        }catch(e){
        }
      }
      oXml = null;
    }

    eval(script);


    if ($returnType==7) { //just an loadscript
      return;
    }
    var fct = eval($class);

    var tM = [];
    tM['pointer'] = fct;
    tM['instance'] = new fct();

    window.LOADSCRIPTS[$class] = tM;
    if ($returnType==2) {
      return window.LOADSCRIPTS[$class].instance;
    }
    script = null;
  }

  if ($returnType==1) {
    return window.LOADSCRIPTS[$class].instance;
  }else if ($returnType==2){
    return new window.LOADSCRIPTS[$class]['pointer']();
  }else if ($returnType==0 || $returnType==null) {
    return window.LOADSCRIPTS[$class]['pointer'];
  }else {
    return null;
  }
}

function __getXmlObject() {
  return new ActiveXObject("Microsoft.XMLDOM");
}
function __noCacheUrl($url) {
  $url = __setUrlParam($url, "_JUICE_URL_UUID", __uuid());
  return $url;
}
/**
 * it check the return xml it's the error msg contain
 * if has then out put the errors msg to the outputdom,
 * and return false
 */
function __xmlErrorHandler($xdoc, $outputDom) {
  if ($xdoc.documentElement.tagName=="error") {
    if ($outputDom!=null) {
      $outputDom.innerHTML = "<span style='color:red'>" + $xdoc.documentElement.childNodes[0].text + "</span>";
    }else {
      __alert($xdoc.documentElement.childNodes[0].text);
    }
    return false;
  }
  return true;
}

var _chars = "0123456789abcdef";

/**
* random number
*/
function __random() {
return Math.random() * 10000000000;
};

/**
* translate the number to hex
*/
function __hex($num) {
var str = "";
for(var j = 7; j >= 0; j--)
str += _chars.charAt(($num >> (j * 4)) & 0x0F);
return str;
};

/**
 * get the uuid
 */
function __uuid() {
    var uuid = __hex(new Date().getTime()) + "-" +
               __hex(__random()).substr(0,4) + "-" +
               __hex(__random()).substr(1,4) + "-" +
               __hex(__random()).substr(2,4) + "-" +
               __hex(__random()).substr(3,4) +
               __hex(__random()).substr(4,4) +
               __hex(__random()).substr(2,4);
    return uuid;
}


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
      var s = _errors[i].toString();
      var b = false;
      for (var j in tmp) {
        if (s==tmp[j]) {
            b = true;
            break;
        }
      }
      if (!b) {
        tmp.push(s);
      }
    }
    return tmp.join("\n");
  };
}


/**
 * parse the params for the given string like "width=100;fresh=true;max=true"
 */
function __parseParams($params) {
  var reg = /([^\=\;]*)\=([^\;]*)/gi;
  var rt = [];
  if ($params==null)
    return rt;
  var ar = $params.match(reg);
  var t = null;
  for (var i=0; i<ar.length; i++) {
    t = ar[i].split("=");
    if (t[1].toUpperCase()=="TRUE") {
      rt[t[0]] = true;
    }else {
      rt[t[0]] = t[1];
    }
  }
  return rt;
}

/**
 * get the current document url value, if no return null
 */
function __getUrlParamValue($name, $url) {
  var url = $url || document.location.toString();
  var qIdx = url.indexOf("?");
  if (qIdx==-1)
    return null;
  url = url.substr(qIdx);
  var nIdx = url.indexOf($name + "=");
  if (nIdx==-1)
    return null;
  url = url.substr(nIdx+$name.length+1) + "&";
  var dIdx = url.indexOf("&");
  if (dIdx==-1)
    return null;
  return url.substr(0, dIdx);
}


/**
 * set the url params, when the url already have the value,it change it
 * if no it add it to the last
 */
function __setUrlParam($url, $name, $value) {

  var charFix = $url.indexOf("?")!=-1?"&":"?";
  var nIdxStart = $url.indexOf("&" + $name + "=");
  if (nIdxStart==-1) {
    nIdxStart = $url.indexOf("?" + $name + "=");
  }
  if (nIdxStart==-1) { //no params
    $url = $url + charFix + $name + "=" + $value;
    return $url;
  }
  /**
   * from upper it found the name start and then try to found the value end position
   * the value and be empty or it's the url end
   */
  var nIdxEnd = $url.indexOf("&", nIdxStart+1);
  var urlPreFix = $url.substr(0, nIdxStart+1);
  var urlEndFix = "";
  if (nIdxEnd!=-1) {
    urlEndFix = $url.substr(nIdxEnd);
  }
  $url = urlPreFix + $name + "=" + $value + urlEndFix;
  return $url;
}

/**
 * 用对话窗口显示连接
 */
function __dialog($p) {
  var sFea = "dialogHeight:" + $p.height + ";dialogWidth:" + $p.width + ";center:yes;help:no;resizable:yes;status:no";
  if ($p.less==true) {
    top.showModelessDialog(__noCacheUrl($p.url), $p.argu, sFea);
  }else {
    var ag = "";
    if ($p.argu!=null) {
      ag = $p.argu;
    }
    return top.showModalDialog(__noCacheUrl($p.url), ag, sFea);
  }
}


/**
 * 用普通窗口打开
 */
function __winopen($p) {
   var lScreenW = screen.availWidth;
   var lScreenH = screen.availHeight;
   var l = (lScreenW - $p.width)/2;
   var t = (lScreenH - $p.height)/2;
   var p_mb = $p.menubar?"yes":"no";
   var p_tb = $p.toolbar?"yes":"no";
   var pa = "width=" + $p.width + ",height=" + $p.height + ",left=" + l + ",top=" + t + ",status=no,toolbar=" + p_tb + ",menubar=" + p_mb + ",location=no,resizable=yes,scrollbars=yes";
   var n = $p.name?$p.name:new Date().getTime();
   //对应sp2使用先创建连接再点连接
   var o = window.open($p.url, n, pa);
   try {
     o.focus();
   }catch(e) {
     return __dialog($p.url);
   }
   return o;
}

//最大化的窗口
function __fullwinopen($url, $fct) {
  if ($url.indexOf("/")==0 && $url.indexOf(_c)!=0) {
    $url = _c + $url;
  }
  var p = [];
  p.width = screen.availWidth - 4;
  p.height = screen.availHeight;
  p.menubar = false;
  p.toolbar = true;
  p.url = $url;
  var o = __winopen(p);
  setTimeout(function wo() {try{o.moveTo(0,0)}catch(e){}},50);
  setTimeout(function wr() {try{o.resizeTo(screen.availWidth,screen.availHeight)}catch(e){}}, 50);
  if ($fct!=null) {
    __dirtyWins[o] = $fct;
  }
}

/**
 * position model
 */
function Position(iX, iY) {
  this.x = iX;
  this.y = iY;
}

/**
 * bound model
 */
function Bound(iX1, iY1, iX2, iY2) {
  this.lt = new Position(iX1, iY1);
  this.rb = new Position(iX2, iY2);
}

/**
 * rect model
 */
function Rect(iW, iH) {
  this.w = iW;
  this.h = iH;
}


/**
 * get object position
 */
function __getPos($dom) {
  var iXPos = 0;
  var iYPos = 0;
  while ($dom!=null) {
    iXPos += $dom["offsetLeft"];
    iYPos += $dom["offsetTop"];
    $dom = $dom.offsetParent;
  }
  return new Position(iXPos, iYPos);
}


/**
 * get the dom obj's rect model
 * @return Rect model
 */
function __getRect(domObj) {
  return new Rect(domObj.clientWidth*1, domObj.clientHeight*1);
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

  //check the textarea length
  if ($element.tagName=="TEXTAREA") {
    var mx = $element.getAttribute("maxlength");
    if (mx!=null) { //textarea都当其输入中文来处理
     //按实际的长度计算（laihr）
      if (val.length>mx) {
        $errorList.add(new Error(comment + "中的内容过长，请减少输入的内容，最大值[" + mx + "]", $element));
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

  if (checkType=="int"||checkType=="double"||checkType=="-double") {
    if (__isEmpty(val)) {
       $element.value = "";
    }
  }

  switch(checkType) {
    case "int":
      if (!__isInt(val)) {
        $errorList.add(new Error(comment + "请输入整数", $element));
      }
      var min = $element.getAttribute("tt.min");
      if (min!=null && !__isEmpty(val) && val*1<min*1) {
        $errorList.add(new Error(comment + "不允许小于" + min, $element));
      }
      var max = $element.getAttribute("tt.max");
      if (max!=null && !__isEmpty(val) && val*1>max*1) {
        $errorList.add(new Error(comment + "不允许大于" + max, $element));
      }
      break;
    case "double":
      if (!__isDouble(val, true)) {
        $errorList.add(new Error(comment + "请输入整数或小数", $element));
      }else {
        /** 检查小数点后的限制 */
        var l = $element.getAttribute("tt.acc"); //提供数字
        if (l!=null) {
          var dIdx = val.indexOf(".");
          if (dIdx!=-1) {
            var t = val.substr(dIdx+1);
            if (t.length>l) {
              $errorList.add(new Error(comment + "格式不正格，限制小数点数长度为：" + l, $element));
            }
          }
        }
        var max = $element.getAttribute("tt.max");
        if (max!=null && !__isEmpty(val) && val*1>max*1) {
          $errorList.add(new Error(comment + "不允许大于" + max, $element));
        }
        var min = $element.getAttribute("tt.min");
        if (min!=null && !__isEmpty(val) && val*1<min*1) {
          $errorList.add(new Error(comment + "不允许小于" + min, $element));
        }
        var reg = $element.getAttribute("tt.reg");
        if (reg!=null && !__isEmpty(val)) { //如8,2 6,2之类的
            rs = reg.split(",");
            var a = rs[0]*1-rs[1]*1;
            reg = eval("/^\\d{1," + a + "}$|^\\d{1," + a + "}\\.\\d{1," + rs[1] + "}$/");
            if (!reg.test(val)) {
              if (val.indexOf(".")==-1) {
                $errorList.add(new Error(comment + "不符合规则，只允许" + a + "位整数", $element));
              }else {
                $errorList.add(new Error(comment + "不符合规则，只允许" + rs[1] + "位小数", $element));
              }
            }
        }
      }

      break;
    case "-double":
      if (!__isDouble(val, true)) {
        $errorList.add(new Error(comment + "请输入浮点类型", $element));
      }
      break;
    case "datetime":
      var max = $element.getAttribute("tt.max");
      if (max!=null && !__isEmpty(val)) {
        var m = max.split("|");
        var dTime = new Date();
        dTime.setTime(m[0]);
        var vTime = __parseDateAuto(val);
        if (__compareTimeD(vTime, dTime, ">")) {
          $errorList.add(new Error(comment + "不允许大于" + m[1], $element));
        }
      }
      break;
    case "IDCard":
      if(!__isEmpty(val)) {
          if(val.length!=15 && val.length!=18){
            $errorList.add(new Error(comment + "必须为15位或18位", $element));
          }else if (!__isIDCard(val)) {
            $errorList.add(new Error(comment + "只能由数字组成（18位的可以有字母x）", $element));
          }
      }
      break;
     case "mobile":
      break;
     case "phone":
      if (!__isEmpty(val)) {
         var reg = /^([0-9]|\(|\)|\-)+$/;
         if (!reg.test(val)) {
           $errorList.add(new Error(comment + "中带有不正确的符号只允许\"(\",\"-\",\")\"与数字", $element));
         }
      }
      break;
     //注意只要使用性比较多的才加在通用检查当中，较少使用的不要单独写就行了，Juice是每个jsp都引入的，这个文件小就小一点
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
 * it's the str it's empty
 */
function __isEmpty($str) {
  return __trim($str).length==0;
}

var _regChinese = /^[\u4e00-\u9fa5](\s|[\u4e00-\u9fa5])*$/;
function __isChinese($str) {
  return _regChinese.test($str);
}


/**
 * it's an validate int type
 */
var _reg_check_int = /^\d*$/;
function __isInt($str) {
  return _reg_check_int.test($str);
}

var _reg_spe = /^-0\.\d+$/;
var _reg_spe_no_minus = /^0\.\d+$/;
var _reg_double = /^-?[0-9]{1}\d*(\.\d)?\d*$/;
var _reg_double_no_minus = /^[1-9]{1}\d*(\.\d)?\d*$/;
/**
 * check it's an double value
 */
function __isDouble($str, $isMinusable) {
    if ($isMinusable==true) {
      if (_reg_spe.test($str) || $str*1==0) {
        return true;
      }
      if (_reg_double.test($str)) {      
        return true;
      }
    }else {
      if ($str.substr(0,1)=="-") {
        return false;
      }

      if ($str*1==0) {
        return true;
      }
      if (_reg_spe_no_minus.test($str)) {
        return true;
      }

      if (_reg_double_no_minus.test($str)) {
        return true;
      }
    }

    return false;
}

/**检查邮件地址合法性*/
function __isEmail($m) {
    var pattern = /\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/;
    if (pattern.test($m)) {
        return true;
    } else {
        return false;
    }
}

/**
 * check it's a ID Card No.(loose)
 */
function __isIDCard($str) {
    /*
     * 15位数字
     * 18位数字
     * 17数字+x/X
     */
    var reg = /^([0-9]{15}|[0-9]{18}|[0-9]{17}X|[0-9]{17}x)$/;
    return reg.test($str);
}

var _regLtrim = /^(\s|\u3000)*/;
var _regRtrim = /(\s|\u3000)*$/;
/**
 * trim and string
 * 全角的空格unicode为3000
 */
function __trim($text) {
  if ($text==null)
    return "";
  return $text.replace(_regLtrim, "").replace(_regRtrim, "");
};

function escapeURI(La){
  if(encodeURIComponent) {
    return encodeURIComponent(La);
  }
  if(escape) {
    return escape(La)
  }
}


/**
 * 调用帮助
 */
function __callHelp() {
  var p = [];
  p.argu = document.location.href;
  p.less = true;
  p.width = "1000px";
  p.height = "700px";
  p.url = _c + "/help/index.jsp";
  var sFea = "dialogHeight:" + p.height + ";dialogWidth:" + p.width + ";center:yes;help:no;resizable:yes;status:no";
  window.showModelessDialog(_c + "/help/dialog.jsp?url=" + p.url, p.argu, sFea);
}


function dialog(sUrl, title, model, sOptions) {
    window.showModalDialog(_c + "/common/dialogWin.jsp?xuTitle=" + title + "&url=" + sUrl, model, sOptions);
}

//调用Excel的处理
function __excel($tableId,$title,$Orientation,$columns,$existTotal) {
  if (window.__xmlPagePrint==null) {
    __loadScript("ExcelPrint", 7);
  }
  window.__xmlPagePrint($tableId,$title,$Orientation,$columns,$existTotal);
}
/**
 * Xuice is the XML full framework upgrade from Juice
 * This is a tech test, it should be the next exi framework base
 * @author: TANTOM
 * @contact: tantom2000@gmail.com
 */
function Xuice() {
}

/**
 * 提交一个表单的处理
 * @param $formName 表单的名称
 * @param $action 执行完调用的脚本
 */
Xuice.prototype.post = function($formName, $action, $asy) {
  var dom = g($formName);
  var url = dom.getAttribute("action");
  var xml = this.generateXml(dom);
  this.send(url, xml, $action, $asy);
}

/**
 * 这是一种特殊的提交方式，其不以页面中的FORM为定义而自行组织页面数据来提交
 * @param $dom 提交哪一个对象下的数据内容，这些数据内容不一定是对象
 * @param $url 处理的地址
 * @param $asy
 * @param $excludeList
 */
Xuice.prototype.xpost = function($dom, $url, $asy, $action, $excludeList, $uncheck) {
  var els = this.collectEls($dom);
  if ($uncheck==true) {
  }else {
    var errorList = new ErrorList();
    for (var i=0; i<els.length; i++) {
      if ($excludeList!=null) {
        if ($excludeList[els[i].name]!=null) { //属于除外的内容
          continue;
        }
      }
      __validateElement(els[i], errorList);
    }
    if (errorList.hasErrors()) {
      alert(errorList);
      return;
    }
  }
  var sb = [];
  sb.push("<xuice>");
  for (var i=0; i<els.length; i++) {
    /** 先调用检查合法性 */
    sb.push(this.elXml(els[i]));
  }
  sb.push("</xuice>");
  var xml = sb.join("\n");

  this.send($url, xml, $action, $asy);
}

/**
 * 搜集一个元素下的对象
 */
Xuice.prototype.collectEls = function($dom) {
  var ips = $dom.all.tags("INPUT");
  var txts = $dom.all.tags("TEXTAREA");
  var sls = $dom.all.tags("SELECT");
  var ary = [];
  for (var i=0; i<ips.length; i++) {
    ary.push(ips[i]);
  }
  for (var i=0; i<txts.length; i++) {
    ary.push(txts[i]);
  }
  for (var i=0; i<sls.length; i++) {
    ary.push(sls[i]);
  }
  return ary;
}

/**
 * 收集查询表的数据并生成连接的URL数据
 */
Xuice.prototype.collectValueToUrl = function($dom) {
  var els = this.collectEls($dom);
  var s = [];
  for (var i=0; i<els.length; i++) {
    s.push(els[i].name + "=" + els[i].value);
  }
  return s.join("&");
}

function __getXmlHttp() {
  var xHttp = null;
  var objs = ["Msxml2.XMLHTTP","MSXML2.XMLHTTP.3.0","Microsoft.XMLHTTP"];
  for (var i=0; i<objs.length; i++) {
    try {xHttp = new ActiveXObject(objs[i]);break;}catch(e){}
  }
  return xHttp;
}

/**
 * 发送数据至指定的地址
 */
Xuice.prototype.send = function($url, $xml, $action, $asy, $noErrorAlert) {
  var doc = __getXmlObject();
  doc.loadXML($xml);
  if ($asy==null) {
    $asy = true;
  }
  var xHttp = __getXmlHttp();
  if (xHttp==null) { alert("请下再MSXML包裹，你的浏览器不支持该操作");return;}

  xHttp.open("POST", $url, $asy);
  xHttp.setRequestHeader("Content-Type","text/xml");
  xHttp.setRequestHeader("Content-Type","GBK");
  xHttp.onreadystatechange = doCallBack;
  xHttp.send(doc);
  function doCallBack() {
    if (xHttp.readyState == 4) {
      //由xmlServlet返回不需要处理的就为空白
      if (xHttp.responseText=="") {
        return;
      }
      doc.loadXML(xHttp.responseText);
      if (doc.documentElement==null) {
        alert("系统返回数据错误，请检查网络连接是否正确\n" + $url + "\n" + xHttp.responseText);
        $action(doc, true);
      }else {
        if (doc.documentElement.tagName=="error") {
          if ($noErrorAlert==true) {
          }else {
            alert(doc.selectSingleNode("/error/msg").text);
          }
          var s = $action.toString();
          var i = s.indexOf("(");
          var ie = s.indexOf(")");
          s = s.substr(i, ie);
          if (s.indexOf(",")!=-1) { //如果回调函数有处理错误的情况就交给回掉函数处理
            $action(doc, true);
          }
        }else {
          var nodes = doc.selectNodes("/xuice/msg");
          if (nodes.length>0) {
            for (var i=0; i<nodes.length; i++) {
              if (nodes[i].tagName=="msg") {
                alert(nodes[i].text);
              }
            }
          }
          if ($action!=null) {
            $action(doc);
          }
        }
      }

      doc = null;
      xHttp.onreadystatechange = new Function("return false");
      xHttp = null;
    }
  }
}

/**
 * 提交地址的处理
 * @param $formName 地址
 * @param $action 执行完调用的脚本
 * @param $fix 如果地址太长就使用fix来修正
 */
Xuice.prototype.url = function($url, $action, $asy, $fix, $noErrAlert) {
  var xml = this.generateUrlXml($url);
  if ($fix==true) {
    $url = $url.substr(0, $url.indexOf("?"));
  }
  this.send($url, xml, $action, $asy, $noErrAlert);
}

/**
 * 将地址栏的参数转变为XML格式
 */
Xuice.prototype.generateUrlXml = function($url, $escape) {
  var qIdx = $url.indexOf("?");
  if (qIdx==-1)
    return "<xuice/>";
  $url = $url.substr(qIdx+1);
  var t = $url.split("&");
  var v = null;
  var sb = [];

  sb.push("<xuice>");
  for (var i=0; i<t.length; i++) {
    v = t[i].split("=");
    if (v.length==1) { //没有值，只有名称
      v.push("");
    }
    if ($escape==true) {
      sb.push('<e n="' + v[0] + '"><![CDATA[' + unescape(v[1]) +']]></e>');
    }else {
      sb.push('<e n="' + v[0] + '"><![CDATA[' + v[1] +']]></e>');
    }
  }
  sb.push("</xuice>");
  return sb.join("\n");
}

/**
 * 将表单的内容转变为XML格式
 */
Xuice.prototype.generateXml = function($form) {
  var el = null;
  var sb = [];
  sb.push("<xuice>");
  for (i = 0; i < $form.elements.length; i++) {
    el = $form.elements[i];
    sb.push(this.elXml(el));
  }
  sb.push("</xuice>");
  return sb.join("\n");
}

/**
 * 将页面的表单元素转为XML格式
 */
Xuice.prototype.elXml = function($el) {
    if (""==$el.name) {
      return "";
    }
    var sb = [];
    switch($el.type) {
      case 'hidden':
        sb.push('<e n="' + $el.name + '"><![CDATA[' + __filter($el.value) + ']]></e>');
      break;
      case 'text':
        var v = $el.value;
        if ($el.getAttribute("tt.nofilter")!="true") {
            v = __filter(v);
        }
        sb.push('<e n="' + $el.name + '"><![CDATA[' + v + ']]></e>');
      break;
      case 'select-one':
        sb.push('<e n="' + $el.name + '"><![CDATA[' + $el.value + ']]></e>');
      break;
      case 'checkbox':
        sb.push('<e n="' + $el.name + '"  c="' + $el.checked + '"><![CDATA[' + $el.value + ']]></e>');
      break;
      case 'textarea':
        var v = $el.value;
        if ($el.getAttribute("tt.nofilter")!="true") {
          v = __filter(v);
        }
        sb.push('<e n="' + $el.name + '"><![CDATA[' + v + ']]></e>');
      break;
      case 'radio':
        if ($el.checked) {
          sb.push('<e n="' + $el.name + '"><![CDATA[' + $el.value + ']]></e>');
        }
      break;
    }
    return sb.join("");
}

/**
 * 将一个XML节点转化为XML对象
 */
Xuice.prototype.xmlObj = function($node) {
  var m = [];
  var childs = $node.childNodes;
  for (var i=0; i<childs.length; i++) {
    m[childs[i].nodeName] = childs[i].text;
  }
  return m;
}


//======== global function list =============
/** get an dom object */
function g($n) {
  return document.getElementById($n);
}

var _Xuice = new Xuice();

function __go($url) {
   top.__jump(window);
   var s = $url.indexOf("#");
   if (s!=-1) {
     $url = $url.substr(0, s);
   }
   document.location = $url;
}

//================================================================================== 日期的相关处理 ==============================
var __serverTime;
/**
 * 不要使用客户端的时间，当前时间应该是从服务器端获得的
 */
function __setServerTime($serverTime) {
  __serverTime = $serverTime;
}


/**
 * 和当前时间进行比较
 * sOption ：比较类型 ">" | "<" | "=" | ">=" | "<="
 */
function __compareTimeWithNow(sTime,sOption) {
    var now = new Date();
    now.setTime(__serverTime);
    var sNow = __parseDateToChineseDateAuto(now,sTime);
    return __compareTime(sTime,sNow,sOption);
}

/**
 * 2个时间进行比较
 * sOption ：比较类型 ">" | "<" | "=" | ">=" | "<="
 */
function __compareTime(sTime1,sTime2,sOption){
    var index1 = sTime1.indexOf(":");
    var index2 = sTime2.indexOf(":");

    var dTime1 = new Date();
    var dTime2 = new Date();
    if(index1==-1){
        dTime1 = __parseDateS(sTime1);
    }else{
        dTime1 = __parseDate(sTime1);
    }
    if(index2==-1){
        dTime2 = __parseDateS(sTime2);
    }else{
        dTime2 = __parseDate(sTime2);
    }
    return __compareTimeD(dTime1,dTime2,sOption);
}


/**
 * 2个Date类型的时间进行比较
 */
function __compareTimeD(dTime1,dTime2,sOption) {
    if (dTime1==null || dTime2==null) {
        return false;
    }    
    if(sOption==">") {
        return (dTime1>dTime2);
    }else if(sOption=="<") {
        return (dTime1<dTime2);
    }else if(sOption=="=") {
        return (dTime1==dTime2);
    }else if(sOption==">=") {
        return (dTime1>=dTime2);
    }else if(sOption=="<=") {
        return (dTime1<=dTime2);
    }else{
        alert("无效的比较类型,支持的类型:>、<、=、>=、<=");
        return false;
    }
}

/**
 * 将日期格式化为Date类型
 * 传入的日期格式如下： 2004年11月09日 10:29
 */
var regDate = /(\d*)年(\d*)月(\d*)日\s(\d*)\:(\d*)/;
function __parseDate($dateStr) {
  if ($dateStr=="") {
      return null;
  }
  var t = $dateStr.match(regDate);
  var date = new Date();
  date.setTime(__serverTime);
  date.setYear(t[1]);
  date.setMonth(t[2]*1-1);
  date.setDate(t[3]);
  date.setHours(t[4]);
  date.setMinutes(t[5]);
  return date;
}

var regDateS = /(\d*)年(\d*)月(\d*)日/;
function __parseDateS($dateStr) {
  if ($dateStr=="") {
      return null;
  }    
  var t = $dateStr.match(regDateS);
  var date = new Date();
  date.setTime(__serverTime);
  date.setYear(t[1]);
  date.setMonth(t[2]*1-1);
  date.setDate(t[3]);
  date.setHours(0);
  date.setMinutes(0);
  return date;
}


/** 先分析是出日期还是短日期再解析 */
function __parseDateAuto($dateStr) {
 var idx = $dateStr.indexOf(":");    //长日期
   if(idx==-1){
        return __parseDateS($dateStr);
    }else{
        return __parseDate($dateStr);
    }
}

/**将标准时间转化为中文时间，根据$sample自动判断长、短日期格式*/
function __parseDateToChineseDateAuto($date,$sample) {
 var idx = $sample.indexOf(":");    //长日期
   if(idx==-1){
        return $date.getYear() + "年" + ($date.getMonth()*1+1) + "月" + $date.getDate() + "日";
   }else{
        return $date.getYear() + "年" + ($date.getMonth()*1+1) + "月" + $date.getDate() + "日" + " " + $date.getHours() + ":" + $date.getMinutes();
   }
}

/**
 * 判断日期是否今天，需要提供服务器的时间
 */
function __isToday($date, $serverDate) {
  if ($date.getYear()*1==$serverDate.getYear()*1 &&
      $date.getMonth()*1==$serverDate.getMonth()*1 &&
      $date.getDate()*1==$serverDate.getDate()*1) {
      return true;
  }
  return false;
}

//=================================================================== 日期相关处理结束 =================================
/**
 * 类似form.reset但会同时处理组件值的初始化
 * @todo 如果组件有初始值时要记下来这个就麻烦罗，先不处理
 */
function __reset($form) {
  $form.reset();
  var cs = $form.all.EXI_COMPONENT;
  cs = __arrayDoms(cs);
  for (var i=0; i<cs.length; i++) {
    eval(cs[i].getAttribute("tt.ins.name") + ".doReset()");
  }
}

function doViewWFViso() {
  var procId = __get("ProcessList").value;
  if (__isEmpty(procId)){
    alert("请先选择需要预览的流程！");
    return;
  }
  var url = _c + "/workflow/xwf/xwf_preview.jsp?procId=" + procId;
  var sFea = "dialogHeight:500px;dialogWidth:1000px;center:yes;help:no;resizable:yes;status:no";
  top.showModalDialog(url, "", sFea);
}

var _loopCheck = -1;
//强制不检查
var _bForceUnCheck = false;
var _ched = false;
//离开页面时的检查
function onPageOutCheck() {  
  if (_bForceUnCheck) {
    return;
  }
  if (_ched) {
    return;
  }
  //如果有父窗口方法就在关闭前执行
  if (typeof(window.opener)!="undefined") {
    if (typeof(window.opener.__onOpenWinClose)!="undefined") {
      try {
        window.opener.__onOpenWinClose();
      }catch(e){};
    }
  }
 
  //未定义标记变量时则以页面的地址来猜测
  var bC = false;
  if (typeof(__pageOut)=="undefined") {
    var url = document.location.toString();
    if (url.indexOf("_add")!=-1 || url.indexOf("_edit")!=-1 || url.indexOf("/add")!=-1 || url.indexOf("/edit")!=-1) {
      if (isPageModified()) {
        _ched = true;
        bC = true;
        event.returnValue = "数据还没做保存，如果确认要离开该页面请点确定！";        
      }
    }
  }else {
    if (__pageOut==true) {      
      if (isPageModified()) {
        _ched = true;
        bC = true;
        event.returnValue = "数据还没做保存，如果确认要离开该页面请点确定！";
      }
    }
  }
  
  if (bC) {
    _loopCheck = setInterval(pchk,500);    
  }
}

function pchk() {
  if (typeof(top.__unJump)!="undefined") {
    top.__unJump();
    clearInterval(_loopCheck);
  }  
}

//页面元素是否被修改过
function isPageModified() {
  var ips = document.all.tags("INPUT");
  var bModify = false;
  var ip = null;
  for (var i=0; i<ips.length; i++) {
    ip = ips[i];
    if (ip.getAttribute("type")=="text") {
      if (ip.value!=ip.defaultValue) {
        bModify = true;
        break;
      }
    }
  }
  ips = document.all.tags("TEXTAREA");
  for (var i=0; i<ips.length; i++) {
    ip = ips[i];   
    if (ip.value!=ip.defaultValue) {
      bModify = true;
      break;
    }
  }
  return bModify;
}

window.attachEvent("onbeforeunload", onPageOutCheck);

//虚拟方法，取消之前的MessageMgr当中的处理
function __jump($win) { 
}

//统一提交时的接口
function __submit($form) {
  try {
  __pageOut = false;
  $form.submit();
  }catch(e){}
}

//脚本行为来关闭一个窗口，这里会处理离开时的提示
function __jsClose($win) {
  __pageOut = false;
  $win.close();
}

//统一使用该方法来处理页面的跳转，在页面地址改变后用户不能按后退回到之前的数据填写页面
function __jumpPage($win, $url) {
    $win.location.replace($url);
}

//代替原有的 $win.document.location.reload()
function __jumpReload($win) {
   $win.location.replace($win.location.toString());
}

function __getDefaultTips($msg) {
  return "<TABLE class=rno cellSpacing=0 cellPadding=0><TBODY><TR><TD><B class=rnd><B class=rnd1></B><B class=rnd2></B></B><TR><TD style='padding:2 8 2 8'>" + $msg + "<TR><TD><B class=rnd><B class=rnd2></B><B class=rnd1></B></B></TR></TBODY></TABLE>";
}

//将元素分布至一个表格当中，返回表格对象
function __pushToTable($els, $cols, $tableProps) {
	var domTB = __createListTable($cols, $els.length, $tableProps);
	var rows = domTB.rows;
	for (var i=0; i<rows.length; i++) {
		var cells = rows[i].cells;
		for (var j=0; j<cells.length; j++) {
			if ($els.length>0) {
			  cells[j].innerHTML = $els.shift();
		  }else {
		  	break;
		  }
		}
	}
	return domTB;
}

//指定每行多少列与总存放元素个数，其会生成一个相应大小的Table
function __createListTable($cols, $total, $tableProps) {
	var sb = [];
	if ($total==0) {
		sb.push("<table " + $tableProps + "><tr><td></td></tr></table>");
	}else {
	  var rows = Math.ceil($total/$cols);	
	  sb.push("<table " + $tableProps + ">");
	  for (var i=0; i<rows; i++) {
		  sb.push("<tr>");
		  for (var j=0; j<$cols; j++) {
		  	sb.push("<td></td>");
		  }
		  sb.push("</tr>");
	  }
	  sb.push("</table>");
  }
	return __createElementByHTML(sb.join(""));	
}

//replace textarea newline as html br break
function __br($t) {
	return $t.replace(/\n/g, "<br>");
}

function __unbr($t) {
	return $t.replace(/<br>/g, "\n");
}

/**
 * 修正层超出的位置
 * @param $targetDomPoint
 * @param $node
 */
function __fixPos($targetDomPoint, $node) {
  var st = 0;
  var sl = 0;
  var mh = 0;
  var mw = 0;
  if (typeof(__isInWABody)!="undefined") {
      if (__isInWABody($targetDomPoint)) {
        st = __waBody.scrollTop;
        sl = __waBody.scrollLeft;
        mh = __waBody.clientHeight;
        mw = __waBody.clientWidth;
      }else {
        mh = document.body.clientHeight;
        mw = document.body.clientWidth;
      }
    }else {
      mh = document.body.clientHeight;
      mw = document.body.clientWidth;
    }
    if (mh==0) {
      mh = document.body.clientHeight;      
    }
    if (mw==0) {
       mw = document.body.clientWidth;
    }

    var pos = __getPos($targetDomPoint);
    $node.style.top = pos.y + 20 - st;
    $node.style.left = pos.x - sl;
    var p = pos;
    var r = __getRect($node);
    if (p.x + r.w - sl> mw) {
        $node.style.left = p.x - r.w - sl;
    }

    if (p.y  - st + r.h > mh) {
        $node.style.top = p.y - r.h - st;
    }
}
//use for the formtree component
var FormTreeState = 0;


function __log($msg) {
   var d = __get("DomLog");
   if (d!=null) {
       d.insertAdjacentHTML("beforeEnd", $msg);
   }
}

  /**
   * 将一个对象居中
   */
  function __centerDiv($area, $obj, $posFix) {
    if ($posFix==null)
      $posFix = new Position(0, 0);
    if ($area==null)
      $area = document.body;
    var bodyRect = __getRect($area);
    var objRect = __getRect($obj);
    var nX = (bodyRect.w - objRect.w) / 2;
    var nY = (bodyRect.h - objRect.h) / 2;
    var os = $obj.style;
    var sT = document.body.scrollTop;
    var sL = document.body.scrollLeft;
    os.left = nX - $posFix.x + sL;
    os.top = nY - $posFix.y + sT;
  }

var _cpl = null;
/**
 * 在界面的中间显示正在操作中的提示信息
 * __waitMsg
 * 0:wait
 * @param $msg
 */
function __message($msg) {
  if ($msg==null) {
    if (_cpl!=null) {
        _cpl.node.style.display = "none";
    }
    return;
  }
  if (_cpl==null) {
    var n = document.createElement("DIV");
    n.style.padding = "2px";
    n.style.filter = "DropShadow(Color=#D5D9DA, OffX=2, OffY=2, Positive=45)";
    n.style.position = "absolute";
    n.style.display = "none";
    n.style.zIndex = 9999;

    var s = "<div style='padding:0px;font-family:Tahoma;font-size:12px;border:1px solid #808080;background-image:url(" + _cc + "/img/b.jpg)'>" +
            "<div style='padding:0px;'>" +
            "<div style='padding:1px;'></div><div align=center style='padding:5 5 20 5'><span tt.type=A style=\"padding:2 20 2 20;display:none;border:1px solid #808080;cursor:default\">确 定</span></div></div></div>";

    n.innerHTML = s;
    _cpl = [];
    _cpl.node = n;
    _cpl.content = _cpl.node.children[0].children[0].children[0];
    _cpl.content.innerHTML = "<table width=300><tr><td align=center style='font-size:14px;font-family:宋体;color:#000000;letter-spacing:2px;height:60px' id=msg></td></tr></table>";
    _cpl.container = _cpl.content.children[0];
    _cpl.button = _cpl.node.children[0].children[0].children[1].children[0];
    _cpl.button.attachEvent("onmouseover", overBtnA);
    _cpl.button.attachEvent("onmouseout", outBtnA);
    _cpl.button.attachEvent("onmousedown", btnSureEvent);
    _cpl.msg = _cpl.content.all.msg;
    document.body.insertAdjacentElement("afterBegin", _cpl.node);

      //other inner functions
     function overBtnA() {
       var obj = event.srcElement;
       obj.style.backgroundColor = "#ADB9D8";
     }

      function outBtnA() {
        var obj = event.srcElement;
        obj.style.backgroundColor = "transparent";
      }

      function btnSureEvent() {
         cplClose();
      }
  }
  if ($msg.type==0) {
    _cpl.msg.innerHTML = $msg.message + "<br><img src=" + _cc + "/img/rloading.gif align=absmiddle>";
    _cpl.button.style.display = "none";
  }else if ($msg.type==10) { //错误提示
    _cpl.msg.innerHTML = "<img src=" + _cc + "/img/error.gif align=absmiddle> <span style='color:red'>" + $msg.message + "</span>";
    _cpl.button.style.display = "inline";
  }else if ($msg.type==1) { //信息提示
    _cpl.msg.innerHTML = "<img src=" + _cc + "/img/info.gif align=absmiddle> <span style='color:#003366'>" + $msg.message + "</span>";
    _cpl.button.style.display = "inline";
  }
  _cpl.node.style.display = "";
  _cpl.action = $msg.action;
  __centerDiv(null, _cpl.node);
  if ($msg.autoclose!=null) {
      _cpl.autoclose = setTimeout(cplClose, $msg.autoclose*1000);
  }

  function cplClose() {
     _cpl.node.style.display = "none";
     clearTimeout(_cpl.autoclose);
     if (_cpl.action!=null) {
         _cpl.action();
     }
  }
}

/**渲染tr onmouseover和onmouseleave事件*/
$(function(){
	
	$(".zuyue-table").attr("borderColor","#CCCCCC");
	$(".zuyue-table").attr("border","1");
	
	var trs = $(".zuyue-table tr");

	for (var i=0;i<trs.size();i++) {
		var tr = trs[i];
		var trClass = $(tr).attr("class");
		
		if (i==0) {
			$(tr).attr("class","data_list_thead");
		} else {
			if (i%2!=0) {
				$(tr).attr("class","TBL_A");
			} else {
				$(tr).attr("class","TBL_B");
			}
			
			$(tr).bind('mouseenter mouseleave', function() {
				  $(this).toggleClass('TR_OVER');
			});
		}
	}
});