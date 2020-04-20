
(function (){
  function imgLoad(fromOpAdmin){
    var clientHeight = window.innerHeight || document.documentElement.clientHeight || document.body.clientHeight;
    var isViewportXs = window.matchMedia && window.matchMedia('(max-width: 736px)').matches;
    var isViewportMd = window.matchMedia && window.matchMedia('(max-width: 1200px)').matches && window.matchMedia('(min-width: 737px)').matches;
    var lazyloadHeightTop= isViewportXs ? clientHeight * -0.2 : clientHeight * -0.25;
    var lazyloadHeightBottom = clientHeight * 1.5;
    var lazyDom = document.querySelectorAll('.lazyload');
    initLazyLoad();

    if(fromOpAdmin === true){
      var checkLazyDom = function (){
        lazyDom = document.querySelectorAll('.lazyload');
        initLazyLoad(fromOpAdmin);
      }
      setInterval(checkLazyDom, 200);
    }

    function initLazyLoad(dontDelay){
      var rect;
      for (var i = 0;i<lazyDom.length; i ++){
        if(lazyDom[i] && !lazyDom[i].classList.contains('op-loaded')){
          rect = lazyDom[i].getBoundingClientRect();
          if(dontDelay === true || (rect.bottom > lazyloadHeightTop && rect.top < lazyloadHeightBottom) ){
            var dataSrc = lazyDom[i].getAttribute('data-src');
            if(dataSrc && lazyDom[i].src !== dataSrc){
              lazyDom[i].src = dataSrc;
              lazyDom[i].classList.add('op-loaded');
            }
            var dataBackground = lazyDom[i].getAttribute('data-background');
            if(isViewportXs){
              dataBackground = lazyDom[i].getAttribute('data-background-xs');
            }
            if(isViewportMd){
              dataBackground = lazyDom[i].getAttribute('data-background-md');
            }
            if(dataBackground && !lazyDom[i].style.backgroundImage){
              lazyDom[i].style.backgroundImage = 'url(' + dataBackground + ')';
              lazyDom[i].classList.add('op-loaded');
            }
          }
        }
      }
    }

    /***
     * 节流函数
     * @param func 执行的函数
     * @param wait scroll触发的间隔
     * @param mustRun 如果scroll触发的间隔一直短于wait毫秒，那么保证要执行的函数在mustRun毫秒内执行一次
     * @returns {Function}
     */
    function throttle(func, wait, mustRun) {
      var timeout, startTime = new Date();
      return function() {
        var context = this,
          args = arguments,
          curTime = new Date();
        clearTimeout(timeout);
        // 如果达到了规定的触发时间间隔，触发 handler
        if(curTime - startTime >= mustRun){
          func.apply(context,args);
          startTime = curTime;
          // 没达到触发间隔，重新设定定时器
        }else{
          timeout = setTimeout(func, wait);
        }
      };
    };
    document.addEventListener('scroll',throttle(initLazyLoad,50,100));
  }
  
  if(window.top.location.href.indexOf('opadmin') > -1){
    imgLoad(true);
  }else{
    document.addEventListener('DOMContentLoaded', imgLoad);
  }

})();