package club.differentialmanifold.dy.core.spring.boot.autoconfigure.service.crud.list.search;

public enum SearchOperator {

    EQ, //等于
    NEQ, //不等于
    GT, //大于
    LT, //小于
    GTE, //大于等于
    LTE, //小于等于
    LIKE, //模糊匹配
    NLIKE, //not like
    NULL, //is null
    NNULL, //is not null

}
