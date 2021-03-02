package samples.mybatis.base;

import samples.mybatis.base.crud.list.ListRequest;
import samples.mybatis.base.crud.list.search.OneCondition;
import samples.mybatis.base.crud.list.sort.SortRequest;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.ServletRequest;
import java.util.*;

public class DyServiceUtil {
		/**
		 * 取得带相同前缀的Request Parameters, copy from spring WebUtils.
		 * 
		 * 返回的结果的Parameter名已去除前缀.
		 */
		public static Map<String, Object> getParametersStartingWith(ServletRequest request, String prefix) {
			Enumeration paramNames = request.getParameterNames();
			//Map<String, Object> params = new TreeMap<>();
            // 参数有序的
			Map<String, Object> params = new LinkedHashMap<>();
			if (prefix == null) {
				prefix = "";
			}
			while ((paramNames != null) && paramNames.hasMoreElements()) {
				String paramName = (String) paramNames.nextElement();
				if ("".equals(prefix) || paramName.startsWith(prefix)) {
					String unprefixed = paramName.substring(prefix.length());
					String[] values = request.getParameterValues(paramName);
					if ((values == null) || (values.length == 0)) {
						// Do nothing, no values found at all.
					} else if (values.length > 1) {
						for (int i=0; i<values.length; ++i) {
							values[i] = values[i].trim();
						}
						params.put(unprefixed, values);
					} else {
						params.put(unprefixed, values[0].trim());
					}
				}
			}
			return params;
		}


	public static <T> PageInfo<T> doSearch(final ListRequest listRequest, Mapper<T> mapper, Class clazz) {

		Condition condition = new Condition(clazz);
		Example.Criteria criteria = condition.createCriteria();

		// 处理搜索
		listRequest.getSearchRequests().forEach((searchRequest) -> {
			if (searchRequest instanceof OneCondition) {
				OneCondition oneCondition = (OneCondition) searchRequest;

				parseCriteria(criteria, oneCondition);
			}
		});

		// 处理排序
		SortRequest sortRequest = listRequest.getSortRequest();
		if (sortRequest != null) {
			List<SortRequest.Order> orders = sortRequest.getOrders();
			orders.forEach((order) -> {
				if (order.getDirection() == SortRequest.Direction.ASC) {
					condition.orderBy(order.getProperty()).asc();
				} else if (order.getDirection() == SortRequest.Direction.DESC) {
					condition.orderBy(order.getProperty()).desc();
				}
			});
		}

		// 处理分页
		return PageHelper.startPage(
				listRequest.getPageRequest().getPageNum(), listRequest.getPageRequest().getPageSize())
				.doSelectPageInfo(() -> mapper.selectByExample(condition));
	}

	private static Example.Criteria parseCriteria(Example.Criteria criteria, OneCondition oneCondition) {

		switch (oneCondition.getSearchOperator()) {
			case EQ:
				Map<String, Object> eqParams = Maps.newHashMap();
				eqParams.put(oneCondition.getFieldName(), oneCondition.getValue());
				criteria.andEqualTo(eqParams);
				break;
			case NEQ:
				Map<String, Object> neqParams = Maps.newHashMap();
				neqParams.put(oneCondition.getFieldName(), oneCondition.getValue());
				criteria.andNotEqualTo(oneCondition.getFieldName(), oneCondition.getValue());
				break;
			case GT:
				criteria.andGreaterThan(oneCondition.getFieldName(), oneCondition.getValue());
				break;
			case LT:
				criteria.andLessThan(oneCondition.getFieldName(), oneCondition.getValue());
				break;
			case GTE:
				criteria.andGreaterThanOrEqualTo(oneCondition.getFieldName(), oneCondition.getValue());
				break;
			case LTE:
				criteria.andLessThanOrEqualTo(oneCondition.getFieldName(), oneCondition.getValue());
				break;
			case LIKE:
				criteria.andLike(oneCondition.getFieldName(), oneCondition.getValue().toString());
				break;
			case NLIKE:
				criteria.andNotLike(oneCondition.getFieldName(), oneCondition.getValue().toString());
				break;
			case NULL:
				criteria.andIsNull(oneCondition.getFieldName());
				break;
			case NNULL:
				criteria.andIsNotNull(oneCondition.getFieldName());
				break;
		}

		return criteria;
	}
}
