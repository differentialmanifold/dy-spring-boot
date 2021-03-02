package club.differentialmanifold.dy.core.spring.boot.autoconfigure.service.crud.list;

import club.differentialmanifold.dy.core.spring.boot.autoconfigure.service.crud.list.page.PageRequest;
import club.differentialmanifold.dy.core.spring.boot.autoconfigure.service.crud.list.search.OneCondition;
import club.differentialmanifold.dy.core.spring.boot.autoconfigure.service.crud.list.search.SearchRequest;
import club.differentialmanifold.dy.core.spring.boot.autoconfigure.service.crud.list.sort.SortRequest;
import com.google.common.collect.Lists;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Data
public class ListRequest {

    private PageRequest pageRequest;
    private SortRequest sortRequest;
    private List<SearchRequest> searchRequests;

    public ListRequest(int page, int size) {
        this.pageRequest = parsePage(page, size);
    }

    public ListRequest(int page, int size, LinkedHashMap<String, Object> sortParams) {
        this.pageRequest = parsePage(page, size);
        this.sortRequest = parseSort(sortParams);
    }

    public ListRequest(int page, int size, LinkedHashMap<String, Object> sortParams, Map<String, Object> searchParams) {
        this.pageRequest = parsePage(page, size);
        this.sortRequest = parseSort(sortParams);
        this.searchRequests = parseSearchFilters(searchParams);
    }

    public static PageRequest parsePage(int page, int size) {
        if (page <= 0 || size <= 0) {
            throw new IllegalArgumentException("page or size should be large than 0");
        }

        // set page start with 1
        // page = page -1;

        if (size > PageRequest.MAX_PAGE_SIZE) {
            size = PageRequest.MAX_PAGE_SIZE;
        }
        return new PageRequest(page, size);
    }

    // sortParams中key的格式为FIELDNAME
    public static SortRequest parseSort(Map<String, Object> sortParams) {
        List<SortRequest.Order> orders = Lists.newArrayList();

        for (Map.Entry<String, Object> entry : sortParams.entrySet()) {
            // 过滤掉空值
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value == null) {
                continue;
            }
            if (StringUtils.isBlank(value.toString())) {
                continue;
            }

            SortRequest.Direction direction = SortRequest.Direction.fromStringOrNull(value.toString());
            orders.add(new SortRequest.Order(direction, key));
        }
        if (CollectionUtils.isEmpty(orders)) {
            return new SortRequest(Lists.newArrayList());
        }

        return new SortRequest(orders);
    }

    //searchParams中key的格式为FIELDNAME_OPERATOR
    public static List<SearchRequest> parseSearchFilters(Map<String, Object> searchParams) {
        List<SearchRequest> searchRequests = Lists.newArrayList();

        for (Map.Entry<String, Object> entry : searchParams.entrySet()) {
            // 过滤掉空值
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value == null) {
                continue;
            }
            if (StringUtils.isBlank(value.toString())) {
                continue;
            }

            OneCondition condition = OneCondition.newCondition(key, value);
            searchRequests.add(condition);
        }

        if (CollectionUtils.isEmpty(searchRequests)) {
            return Lists.newArrayList();
        }

        return searchRequests;
    }

}
