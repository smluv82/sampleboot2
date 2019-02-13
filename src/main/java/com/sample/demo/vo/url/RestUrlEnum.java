package com.sample.demo.vo.url;

public class RestUrlEnum {
	public enum BithumbUrl {
		TICKER("/public/ticker/{0}"),
		;

		private String url;

		private BithumbUrl(String url) {
			this.url = url;
		}

		public String getUrl() {
			return url;
		}
	}

	public enum UpbitUrl {
		TICKER("/v1/ticker?markets={0}"),
		;

		private String url;

		private UpbitUrl(String url) {
			this.url = url;
		}

		public String getUrl() {
			return url;
		}
	}
}
