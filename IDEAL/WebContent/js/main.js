$(function () {

    /*
     * Slideshow
     */
    // slideshow 클래스를 가진 요소마다 작업을 수행
    $('.slideshow').each(function () {

        var $slides = $(this).find('img'), // 모든 슬라이드
            slideCount = $slides.length,   // 슬라이드 점수
            currentIndex = 0;              // 현재 슬라이드를 나타내는 인덱스

        // 첫 번째 슬라이드에 페이드 인으로 표시
        $slides.eq(currentIndex).fadeIn();

        // 7500 밀리 초마다 showNextSlide 함수를 실행
        setInterval(showNextSlide, 9500);

        // 다음 슬라이드를 표시하는 함수
        function showNextSlide () {

            // 다음 표시 할 슬라이드의 인덱스
            // (만약 마지막 슬라이드이라면 처음으로 돌아 가기)
            var nextIndex = (currentIndex + 1) % slideCount;

            // 현재 슬라이드 페이드 아웃
            $slides.eq(currentIndex).fadeOut();

            // 다음 슬라이드를 페이드 인
            $slides.eq(nextIndex).fadeIn();

            // 현재 슬라이드 인덱스를 업데이트
            currentIndex = nextIndex;
        }

    });

    /*
     * Sticky header - top 부분
     */
    $('.page-header').each(function () {

        var $window = $(window), // 창을 jQuery 오브젝트 화
            $header = $(this),   // 헤더를 jQuery 객체 화
            // 헤더의 기본 위치를 검색
            headerOffsetTop = $header.offset().top;

        // 윈도우의 스크롤 이벤트를 모니터링
        // (창이 스크롤 할 때마다 작업을 수행하기)
        $window.on('scroll', function () {
            // 윈도우의 스크롤양을 확인하고
            // 헤더의 기본 위치를 지나서 있으면 클래스를 부여
            // 그렇지 않으면 삭제
            if ($window.scrollTop() > headerOffsetTop) {
                $header.addClass('sticky');
            } else {
                $header.removeClass('sticky');
            }
        });

        // 윈도우의 스크롤 이벤트를 발생시키는
        // (헤더의 초기 위치를 조정하기 위해)
        $window.trigger('scroll');

    });

    /*
     * Tabs 
     */
    $('#work').each(function () {

        // タブの各要素をjQuery 오브젝트 화
        var $tabList    = $(this).find('.tabs-nav'),   // 탭의 목록
            $tabAnchors = $tabList.find('a'),          // 탭 (링크)
            $tabPanels  = $(this).find('.tabs-panel'); // 패널

        // 탭이 클릭되었을 때의 처리
        // 인자로 이벤트 객체를 받는다.
        $tabList.on('click', 'a', function (event) {

            // 링크 클릭에 대한 기본 동작을 취소
            event.preventDefault();

            // 클릭 된 탭을 jQuery 오브젝트 화
            var $this = $(this);

            // 만약 이미 선택된 탭이라면 아무것도하지 않고 처리를 중지
            if ($this.hasClass('active')) {
                return;
            }

            // 모든 탭의 선택 상태를 해제하고,
            // 클릭 된 탭을 선택 상태로
            $tabAnchors.removeClass('active');
            $this.addClass('active');

            // 모든 패널을 일단 비표시로하고
            // 클릭 된 탭에 대응하는 패널을 표시
            $tabPanels.hide();
            $($this.attr('href')).show();

        });

        // 첫 번째 탭을 선택 상태로
        $tabAnchors.eq(0).trigger('click');

    });

    /*
     * Back-toTop button (Smooth scroll)
     */
    $('.back-to-top').each(function () {

        // html 또는 body 중 스크롤 가능한 요소를 감지
        var $el = $(scrollableElement('html', 'body'));

        // 버튼에 클릭 이벤트를 설정
        $(this).on('click', function (event) {
            event.preventDefault();
            $el.animate({ scrollTop: 0 }, 250);
        });
    });

    // scrollTop을 사용할 수있는 요소를 감지하는 함수
    // http://www.learningjquery.com/2007/10/improved-animated-scrolling-script-for-same-page-links#update4
    function scrollableElement (elements) {
        var i, len, el, $el, scrollable;
        for (i = 0, len = arguments.length; i < len; i++) {
            el = arguments[i],
            $el = $(el);
            if ($el.scrollTop() > 0) {
                return el;
            } else {
                $el.scrollTop(1);
                scrollable = $el.scrollTop() > 0;
                $el.scrollTop(0);
                if (scrollable) {
                    return el;
                }
            }
        }
        return [];
    }

    /*
     * Charts
     */
    
     
    var slideIndex = 0;
    showSlides();

    function showSlides() {
        var i;
        var slides = document.getElementsByClassName("mySlides");
        for (i = 0; i < slides.length; i++) {
            slides[i].style.display = "none"; 
        }
        slideIndex++;
        if (slideIndex> slides.length) {slideIndex = 1} 
        slides[slideIndex-1].style.display = "block"; 
        setTimeout(showSlides, 6000); // Change image every 2 seconds
    }

    
    
	
	/* 1. bar charts */
	FusionCharts.ready(function () {
	    var revenueChart = new FusionCharts({
	        type: 'column2d',
	        renderAt: 'chart-container',
	        width: '604',
	        height: '350',
	        dataFormat: 'json',
	        dataSource: {
	            "chart": {
	                "caption": "가장 투자자가 많은 투자상품",
	                "subCaption": "The most popular investment product",
	                "xAxisName": "Month",
	                "yAxisName": "Revenues (In USD)",
	                "numberPrefix": "$",
	                "paletteColors": "#a50019",
	                "bgColor": "#ffffff",
	                "borderAlpha": "20",
	                "canvasBorderAlpha": "0",
	                "usePlotGradientColor": "0",
	                "plotBorderAlpha": "10",
	                "placevaluesInside": "1",
	                "rotatevalues": "1",
	                "valueFontColor": "#ffffff",                
	                "showXAxisLine": "1",
	                "xAxisLineColor": "#999999",
	                "divlineColor": "#999999",               
	                "divLineIsDashed": "1",
	                "showAlternateHGridColor": "0",
	                "subcaptionFontBold": "0",
	                "subcaptionFontSize": "12"
	            },            
	            "data": [
	                {
	                    "label": "Jan",
	                    "value": "420000"
	                }, 
	                {
	                    "label": "Feb",
	                    "value": "810000"
	                }, 
	                {
	                    "label": "Mar",
	                    "value": "720000"
	                }, 
	                {
	                    "label": "Apr",
	                    "value": "550000"
	                }, 
	                {
	                    "label": "May",
	                    "value": "910000"
	                }, 
	                {
	                    "label": "Jun",
	                    "value": "510000"
	                }, 
	                {
	                    "label": "Jul",
	                    "value": "680000"
	                }, 
	                {
	                    "label": "Aug",
	                    "value": "620000"
	                }, 
	                {
	                    "label": "Sep",
	                    "value": "610000"
	                }, 
	                {
	                    "label": "Oct",
	                    "value": "490000"
	                }, 
	                {
	                    "label": "Nov",
	                    "value": "900000"
	                }, 
	                {
	                    "label": "Dec",
	                    "value": "730000"
	                }
	            ],
	            "trendlines": [
	                {
	                    "line": [
	                        {
	                            "startvalue": "700000",
	                            "color": "#00217c",
	                            "valueOnRight": "1",
	                            "displayvalue": "Average Investor"
	                        }
	                    ]
	                }
	            ]
	        }
	    }).render();
	});

	
	/* 2. google charts */
    google.charts.load('current', {'packages':['corechart']});
    google.charts.setOnLoadCallback(drawChart2);

    function drawChart2() {
      var data = google.visualization.arrayToDataTable([
        ['Year', 'Sales', 'Expenses'],
        ['2013',  1000,      400],
        ['2014',  1170,      460],
        ['2015',  660,       1120],
        ['2016',  1030,      540]
      ]);

      var options = {
        title: '방문자 투자자 수치',
        width : 390,
		height : 280,
		colors: ['#E84B4B', '#6dbcdb'],
        hAxis: {title: 'Year',  titleTextStyle: {color: '#333'}},
        vAxis: {minValue: 0}
      };

      var chart = new google.visualization.AreaChart(document.getElementById('chart_div2'));
      chart.draw(data, options);
    }
	
	
	/* 3. google charts */
	// 로딩 완료시 함수 실행하여 차트 생성
	google.charts.setOnLoadCallback(drawChart3);

	// 차트 옵션을 전역으로 설정했습니다. 설정을 바꿔보는 예제를 만들기 위해서요.
	var chart_options = {
		title : '투자금액 대비 수익율',
		width : 390,
		height : 280,
		colors: ['#6dbcdb', '#E84B4B'],
		bar : {
			groupWidth : '70%'
		},
		isStacked : false, // 그래프 쌓기(스택), 기본값은 false
		legend : {
			position : 'bottom' // 이걸 주석처리 해보면 ..
		}
	};

	function drawChart3(){

		// 차트 데이터
		var data = new google.visualization.arrayToDataTable([
			['년대', '항목1', '항목2', ], // 제목 그리고 항목들
			['2014', 10, 20], // 제목과 항목수를 맞춰주어야 합니다.
			['2015', 15, 30],
			['2016', 20, 25],
			['2017', 10, 30],
			['2018', 5, 10]
		]);

		var chart = new google.visualization.ColumnChart(document.getElementById('chart_div3'));
		chart.draw(data, chart_options);
	}

	
	
	/* 4. google charts */

    google.charts.load('current', {'packages':['corechart']});
    google.charts.setOnLoadCallback(drawVisualization);

    function drawVisualization() {
      // Some raw data (not necessarily accurate)
      var data = google.visualization.arrayToDataTable([
       ['Month', 'Bolivia', 'Ecuador', 'Madagascar', 'Papua New Guinea', 'Rwanda', 'Average'],
       ['2004/05',  165,      938,         522,             998,           450,      614.6],
       ['2005/06',  135,      1120,        599,             1268,          288,      682],
       ['2006/07',  157,      1167,        587,             807,           397,      623],
       ['2007/08',  139,      1110,        615,             968,           215,      609.4],
       ['2008/09',  136,      691,         629,             1026,          366,      569.6]
    ]);

  var options = {
    title : '연도별 투자금액 현황',
    width : 390,
	height : 280,
	colors: ['#383838', '#E84B4B' , '#102e3f', '#6dbcdb', '#a50019', 'purple'],
    vAxis: {title: 'Cups'},
    hAxis: {title: 'Month'},
    seriesType: 'bars',
    series: {5: {type: 'line'}},
    legend : {
		position : 'right' 
	}
  };

  var chart = new google.visualization.ComboChart(document.getElementById('chart_div'));
  chart.draw(data, options);
}
	
	
	/* category fade */
    $(function(){
        // 
        var duration = 300;

        // images ----------------------------------------
        var $images = $('#images p');

       

        // images 이미지
        $images.filter('#images p')
            .on('mouseover', function(){
                $(this).find('strong').stop(true).animate({bottom: '0px'}, duration);
                $(this).find('span').stop(true).animate({opacity: 1}, duration);
                $(this).find('img').stop(true).animate({top: '-20px'}, duration * 1.3);
            })
            .on('mouseout', function(){
                $(this).find('strong').stop(true).animate({bottom: '-80px'}, duration);
                $(this).find('span').stop(true).animate({opacity: 0}, duration);
                $(this).find('img').stop(true).animate({top: '0px'}, duration);
            });

    });
	
    
    // images 세번째 이미지

    $(function(){
        // 
        var duration = 300;

        // img3 ----------------------------------------
        var $images = $('#img3 p');


        // img3 이미지
        $images.filter('#img3 p')
            .on('mouseover', function(){
                $(this).find('strong').stop(true).animate({opacity: 1, left: '0%'}, duration);
                $(this).find('span').stop(true).animate({opacity: 1}, duration);
            })
            .on('mouseout', function(){
                $(this).find('strong').stop(true).animate({opacity: 0, left: '-200%'}, duration);
                $(this).find('span').stop(true).animate({opacity: 0}, duration);
            });

    });
    
    
    
    
    
    
    
    
    
    
    
    
    
    // process button
    
    $(function(){
        // 
        var duration = 300;

        // buttons2 ----------------------------------------
        $('#buttons3 button').each(function(index){
            //var pos = Math.random() * 80 - 40;
            var pos = index * 40 - 40;
            $(this).css('top', pos);
        })
        .on('mouseover', function(){
            var $btn = $(this).stop(true).animate({
                backgroundColor: '#faee00',
                color: '#000'
            }, duration);
            $btn.find('img:first-child').stop(true).animate({opacity: 0}, duration);
            $btn.find('img:nth-child(2)').stop(true).animate({opacity: 1}, duration);
        })
        .on('mouseout', function(){
            var $btn = $(this).stop(true).animate({
                backgroundColor: '#fff',
                color: '#01b169',
            }, duration);
            $btn.find('img:first-child').stop(true).animate({opacity: 1}, duration);
            $btn.find('img:nth-child(2)').stop(true).animate({opacity: 0}, duration);
        });


    });
    
    /*
     * 투자상품들
     */
    
    $(function(){
        // 
        var duration = 300;

        // img3 ----------------------------------------
        var $img3 = $('#img3 p');

        // img3 두번째 이미지
        $img3.filter(':nth-child(2)')
            .on('mouseover', function(){
                $(this).find('strong').stop(true).animate({opacity: 1, left: '0%'}, duration);
                $(this).find('span').stop(true).animate({opacity: 1}, duration);
            })
            .on('mouseout', function(){
                $(this).find('strong').stop(true).animate({opacity: 0, left: '-200%'}, duration);
                $(this).find('span').stop(true).animate({opacity: 0}, duration);
            });
    
    });
    
    

    


});
