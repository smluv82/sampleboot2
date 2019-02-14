/**
 *
 */
var sseTest = {
		eventSource : null,

		init : function() {
			console.log('sseTest init');
			eventSource = new EventSource('/sse/streamSse');
//			eventSource = new EventSource('/sse/stream-flux');

			eventSource.onerror = function() {
				console.log('EventSource failed.');
			},

			eventSource.onopen = function() {
				console.log('Connection to server opened.');
			},

			eventSource.onmessage = function(event) {
				console.group('onmessage');
				console.dir(event);
				console.groupEnd('onmessage');

				var html = '';
				html += '<span>';
				html += event.data;
				html += '</span>';
				html += '<br />';

				$('#sse_test_div').append(html);
			}
		},

};