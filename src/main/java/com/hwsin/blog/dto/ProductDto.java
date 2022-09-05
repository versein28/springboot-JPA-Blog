package com.hwsin.blog.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ProductDto {

	@Getter
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class WishItemResponse {

		private int id;
		private int prodId;
		private String prodName;
		private int prodKrw;
		private String prodBrand;
		private String filePath;
		private int count;

		@Builder
		public WishItemResponse(int id, int prodId, String prodName, int prodKrw, String prodBrand, String filePath, int count) {
			this.id = id;
			this.prodId = prodId;
			this.prodName = prodName;
			this.prodKrw = prodKrw;
			this.prodBrand = prodBrand;
			this.filePath = filePath;
			this.count= count;
		}

	}
}
