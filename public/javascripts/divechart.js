
	$(function() {
			
		var vertex_label = [];
		for (var i = 0 ; i < 20000 ; i++) {
		   vertex_label[i] = "";
		}
		
		var pp = document.getElementById("diveresults");
	
		var str = pp.innerHTML.split(",");
		var count = -1;
		var Data =[];
		for (var i = 0 ; i < str.length-1 ; i++ ) {
			if ((i%7 ==0)) {
				++count;
				Data[count] = [];
			}
			if ((i%7==0)) {
				Data[count].push(0-Number(str[i]));
			} else {
				if ((i%7)%4==0 || (i%7)%6==0) {
					Data[count].push(str[i]);
				} else {
					Data[count].push(Number(str[i]));
				}
			}
		}
	//	var Data = [[-50,55,50,0,"H",0,"E"],
	//			[-30,30,30,69,"G",0,"F"],
	//			[-50,10,20,70,"B",10,"F"]
	//			];
		var plan3 = [];
		var Offset = 15;
		var currentX = 0;
		var plan = [];
		
		var max = 0;
		for (var i = 0 ; i < Data.length; i++) {
			
			var testData  = Data[i].slice(0);
			//First point;
			if (i==0) {
			plan3.push([currentX,0]);
			currentX += Offset;
			}
			//Second point;
			plan3.push([currentX,0]);
			//First Label; 
		//	vertex_label[currentX * 11 +  0 *7]= "Start Point";
				
			currentX += Offset;
			plan3.push([currentX,testData[0]]);

			if (Math.abs(testData[0]) > max) {
				max = Math.abs(testData[0]);
			}

			//Second Label;
			vertex_label[currentX * 11 + Math.abs( testData[0])*7] = "Depth " + Math.abs(testData[0]) + " ft. " + "Bottom Time " + Math.abs(testData[1]) + " mins.";
			
			currentX += testData[1];
			plan3.push([currentX,testData[0]]);
			vertex_label[currentX * 11 + Math.abs(testData[0]) *7] = "Depth " + Math.abs(testData[0]) + " ft. " + "Bottom Time " + Math.abs(testData[1]) + " mins.";

			//When the decompos time equal 0;
			if (testData[5] != 0 ) 
			{
				currentX += testData[5];
				plan3.push([currentX,-15]);
				vertex_label[currentX * 11 + 15 * 7] = "Decompression Time: " + testData[5] + "mins. at 15 ft. ";
				currentX += Offset;
				plan3.push([currentX,-15]);
				vertex_label[currentX * 11 + 15 * 7] = "Decompression Time: " + testData[5] + "mins. at 15 ft. ";
			} 
			currentX += Offset;
			plan3.push([currentX,0]);
			vertex_label[currentX * 11 + 0 * 7] = "End of Dive Pressure Group: " + testData[4];
			
			plan3.push([currentX + testData[2]/2, 0]);
			vertex_label[(currentX + testData[2] /2) *11 + 0 * 7] = "Surface Interval Time: " + testData[2] + " min.";

			currentX += testData[2];
			plan3.push([currentX,0]);
			vertex_label[currentX * 11 + 0 *7] = "Post Surface Interval Time Pressure Group: " + testData[6];
			

		}	
		
				
		var plot = $.plot("#placeholder", [
				{ data: plan3, label: "Real Plan"}
		], {
			series: {
				lines: {
					show: true
				},
				points: {
					show: true
				}
			},
			grid: {
				hoverable: true,
				clickable: true
			},
			yaxis: {
				min: 0-10-max,
				max: 10
			}
		});

		$("<div id='tooltip'></div>").css({	
			position: "absolute",
			display: "none",
			border: "1px solid #fdd",
			padding: "2px",
			"background-color": "#fee",
			opacity: 0.80
		}).appendTo("body");
		
		$("#placeholder").bind("plothover", function (event, pos, item) {

			if ($("#enablePosition:checked").length > 0) {
				var str = "(" + pos.x.toFixed(2) + ", " + pos.y.toFixed(2) + ")";
				$("#hoverdata").text(str);
			}

		//	if ($("#enableTooltip:checked").length > 0) {
				if (item) {
					var x = item.datapoint[0].toFixed(2),
						y = item.datapoint[1].toFixed(2);
					
					$("#tooltip").html(vertex_label[Math.abs(x)*11 + Math.abs(y)*7]+"")
						.css({top: item.pageY+5, left: item.pageX+5})
						.fadeIn(200);
				} else {
					$("#tooltip").hide();
				}
		//	}
		});

		$("#placeholder").bind("plotclick", function (event, pos, item) {
			if (item) {
				$("#clickdata").text(" - click point " + item.dataIndex + " in " + item.series.label);
				plot.highlight(item.series, item.datapoint);
			}
		});
	});


