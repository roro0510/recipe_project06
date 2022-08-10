package com.recipe.project06.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageVO {
	private int totPage;// total page
	private int blockSize;
	private int startPage;
	private int endPage;
	private int currentPage;

	public PageVO(int count, int currentPage, int pageSize) {
		totPage = count / pageSize + (count % pageSize == 0 ? 0 : 1);
		// totPage=(int)Math.ceil((double)count/pageSize);
		blockSize = 5;
		startPage = ((currentPage - 1) / blockSize) * blockSize + 1;
		endPage = startPage + blockSize - 1;

		if (endPage > totPage)
			endPage = totPage;
		setBlockSize(blockSize);
		setCurrentPage(currentPage);
		setEndPage(endPage);
		setStartPage(startPage);
		setTotPage(totPage);

	}

}
