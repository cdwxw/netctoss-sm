package wxw.model.page;

public abstract class Page {

	@Override
	public String toString() {
		return "Page [rowsPerPage=" + rowsPerPage + ", currentPage=" + currentPage + ", begin=" + begin + ", end=" + end + ", rows=" + rows + ", totalPages=" + totalPages + ", prePage=" + prePage + ", nextPage=" + nextPage + "]";
	}

	// 分页时输入的参数
	private int rowsPerPage = 5; // 每页显示最大行数
	private int currentPage = 1; // 当前页 给页面

	// 写分页SQL时需要的条件，由输入参数计算而来
	private int begin; // 当前页的起始行
	private int end; // 当前页的终止行

	// 用于计算总页数
	private int rows; // 总行数
	private int totalPages;// 总页数
	private int prePage; // 上一页页码
	private int nextPage; // 下一页页码

	public void setRows(int rows) {
		// Controller显式调用setRows方法
		// totalPages = (rows - 1) / rowsPerPage + 1;
		this.rows = rows;
	}

	public int getTotalPages() {
		// 设置总页数 totalPages>=1,故currentPage!=0
		return totalPages = (rows - 1) / rowsPerPage + 1;
	}

	public int getCurrentPage() {
		// 总行数发生改变时,可能引起总页数的变化, 从而可能导致当前页大于总页数,在此处理一下这个情况.
		return currentPage > totalPages ? (currentPage = totalPages) : currentPage;
	}

	public int getPrePage() {
		// 设置上一页
		return currentPage == 1 ? (prePage = 1) : (prePage = currentPage - 1);
	}

	public int getNextPage() {
		// 设置下一页
		return currentPage == totalPages ? (nextPage = totalPages) : (nextPage = currentPage + 1);
	}

	public int getBegin() {
		// 设置起始行
		begin = (currentPage - 1) * rowsPerPage;// Oracle和MySQL相同
		return begin;
	}

	public int getEnd() {
		// end = currentPage * pageSize + 1;// Oracle
		// end = rowsPerPage;// Mysql
		return end;
	}

	public int getRows() {
		return rows;
	}

	public void setTotalPages(int totalPage) {
		this.totalPages = totalPage;
	}

	public void setPrePage(int prePage) {
		this.prePage = prePage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public void setBegin(int begin) {
		this.begin = begin;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getRowsPerPage() {
		return rowsPerPage;
	}

	public void setRowsPerPage(int rowsPerPage) {
		this.rowsPerPage = rowsPerPage;
	}

}
