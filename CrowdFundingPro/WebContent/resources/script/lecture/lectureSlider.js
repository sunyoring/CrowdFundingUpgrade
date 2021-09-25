/**
 * 
 */
var bannerWidth = (document.querySelector('#top_banner')).clientWidth;
var imageCount = document.querySelectorAll('.slider_item').length;
var index = 0;
var slider = document.querySelector('.slider');
slider.style.width = bannerWidth * imageCount + 'px';
slides()
function slides() {
    for(var i=0;i<imageCount;i++){
        slider.style.left = -(bannerWidth * index) + 'px';    
    }
    index++;
    if (index === imageCount) {
        index = 0;
    }
    setTimeout(slides, 3000); 
}

