package club.differentialmanifold.dy.core.spring.boot.autoconfigure.service.crud.list.sort;

import lombok.Data;

import java.util.List;
import java.util.Locale;

@Data
public class SortRequest {

    public static final Direction DEFAULT_DIRECTION = Direction.ASC;

    private List<Order> orders;

    public SortRequest(List<Order> orders) {

        if (null == orders
                //|| orders.isEmpty()
                ) {
            throw new IllegalArgumentException("You have to provide at least one sort property to sort by!");
        }

        this.orders = orders;
    }

    public static enum Direction {

        ASC, DESC;

        public static Direction fromString(String value) {
            try {
                return Direction.valueOf(value.toUpperCase(Locale.US));
            } catch (Exception e) {
                throw new IllegalArgumentException(String.format(
                        "Invalid value '%s' for orders given! Has to be either 'alias' or 'asc' (case insensitive).",
                        value), e);
            }
        }

        public static Direction fromStringOrNull(String value) {

            try {
                return fromString(value);
            } catch (IllegalArgumentException e) {
                return null;
            }
        }
    }

    @Data
    public static class Order {

        private String property;
        private Direction direction;

        public Order(String property) {
            this(DEFAULT_DIRECTION, property);
        }

        public Order(Direction direction, String property) {
            super();
            this.property = property;
            this.direction = direction;
        }
    }
}
