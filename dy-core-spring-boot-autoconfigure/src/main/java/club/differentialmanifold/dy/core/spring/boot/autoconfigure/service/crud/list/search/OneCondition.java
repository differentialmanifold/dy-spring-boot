package club.differentialmanifold.dy.core.spring.boot.autoconfigure.service.crud.list.search;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
public class OneCondition implements SearchRequest {

    //查询参数分隔符
    public static final String SEPARATOR = "_";

    private String key;
    private String fieldName; // 字段名称
    private Object value; // 搜索值
    private SearchOperator searchOperator; // 操作符号

    private OneCondition(String key, String fieldName, Object value, SearchOperator searchOperator) {
        super();
        this.key = key;
        this.fieldName = fieldName;
        this.value = value;
        this.searchOperator = searchOperator;
    }

    private OneCondition(final String fieldName, final SearchOperator searchOperator, final Object value) {
        this.fieldName = fieldName;
        this.searchOperator = searchOperator;
        this.value = value;
        this.key = this.searchOperator + SEPARATOR + this.searchOperator;
    }

    public static OneCondition newCondition(final String searchProperty, final SearchOperator operator, final Object value) {
        return new OneCondition(searchProperty, operator, value);
    }

    public static OneCondition newCondition(final String key, final Object value) {
        // 过滤掉空值
        if ((value == null) || (value instanceof String && StringUtils.isBlank((String) value))) {
            return null;
        }

        // 拆分operator与filedAttribute
        String[] names = StringUtils.split(key, SEPARATOR);
        if (names.length != 2) {
            throw new IllegalArgumentException(key + " is not a valid search filter name");
        }
        String filedName = names[0];
        SearchOperator searchOperator = SearchOperator.valueOf(names[1]);

        // 创建Condition
        return new OneCondition(key, filedName, value, searchOperator);
    }

}
