package club.differentialmanifold.dy.core.spring.boot.autoconfigure.service.crud.list.page;

import lombok.Data;

@Data
public class PageRequest {

    /** 默认页码 */
    public static final int DEFAULT_PAGE_NUMBER = 1;

    /** 默认每页记录数 */
    public static final int DEFAULT_PAGE_SIZE = 20;

    /** 最大每页记录数 */
    public static final int MAX_PAGE_SIZE = 1000;

    private int pageNum  = DEFAULT_PAGE_NUMBER;
    private int pageSize = DEFAULT_PAGE_SIZE;

    public PageRequest(int pageNum, int pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }
}
