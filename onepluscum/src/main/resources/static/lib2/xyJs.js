        window.addEventListener('DOMContentLoaded',function () {
          var selectBanner = document.querySelectorAll('#op-narrow-banner .banner-column');
          for(var i = 0;i < selectBanner.length;i++){
            var itemBanner = selectBanner[i];
            if(itemBanner.getAttribute('data-link')){
              itemBanner.style.cursor = 'pointer';
              (function(itemBanner){
                itemBanner.addEventListener("click",function(e){
                  if(itemBanner.querySelector('.js-button').getAttribute('target') == '_blank'){
                    window.open(itemBanner.getAttribute('data-link'));
                  }else {
                    window.location.href = itemBanner.getAttribute('data-link');
                  }
                });
              })(itemBanner)

            }else{
              itemBanner.style.cursor = 'auto';
            }
          }

          var aLink =  document.querySelectorAll('#op-narrow-banner a');
          for(var a = 0;a < aLink.length;a++){
            var aItem = aLink[a];
            (function(aItem){
              aItem.addEventListener("click",function(e){
                e.stopPropagation();
              });
            })(aItem)
          }
        })